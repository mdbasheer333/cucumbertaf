package org.cucumbertaf.stepdefs.hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.*;
import io.cucumber.plugin.event.PickleStepTestStep;
import org.cucumbertaf.testlib.context.TestContext;
import org.cucumbertaf.corelib.DriverClass;
import org.cucumbertaf.utils.Globals;
import org.cucumbertaf.utils.excel.ExcelReader;
import org.cucumbertaf.utils.property.PropertyUtil;
import org.cucumbertaf.utils.reporter.ExtentReportingService;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class Hooks {

    private final TestContext testContext;
    private static ExtentReports extent = null;
    private static final ThreadLocal<ExtentTest> eTestThreadLocal = new ThreadLocal<>();
    private int stepCount = 0;
    private int iter = 0;
    private String curr_step_name = "";

    //private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

//    public static synchronized ThreadLocal<ExtentTest> getLocalThreadExtentTest() {
//        return test;
//    }

    @BeforeAll
    public static void beforeAll() {
        extent = ExtentReportingService.createInstance();
    }

    @Before
    public void before(Scenario scenario) throws Exception {
        String browserProp = System.getProperty("browser");
        String browser = browserProp == null ? PropertyUtil.getProperty("browser") : browserProp;
        this.testContext.setDriver(DriverClass.getDriverInstance(browser));
        this.testContext.setFeatureName(String.valueOf(scenario.getUri()));
        this.testContext.setScenarioName(scenario.getName());
        this.testContext.setLogger(scenario);

        String featureNameTemp = this.testContext.getFeatureName().split("/")[this.testContext.getFeatureName().split("/").length - 1].replaceAll(".feature", "");
        int current_iteration = Globals.counterTracker.get().getOrDefault(featureNameTemp, 1);
        int total_scenarios_count = Globals.counterTracker.get().getOrDefault("total_scenarios_count", 1);
        int iteration_select = Globals.counterTracker.get().get("current_iteration");

        ExcelReader reader = new ExcelReader(this.testContext.getFeatureName(), this.testContext.getScenarioName(), iteration_select);
        this.testContext.setData(reader.getAllData());

//        if (current_iteration == 1) {
//            eTestThreadLocal.set(extent.createTest(featureNameTemp, "..........featureNameTemp............."));
//        }
//        this.testContext.setExtentTest(eTestThreadLocal.get());

        String info = "" + this.testContext.getData();

        if (current_iteration == 1 || current_iteration % total_scenarios_count != 0) {
            eTestThreadLocal.set(extent.createTest(featureNameTemp + "_" + iteration_select, info));
            iter = iteration_select;
            this.testContext.setExtentTest(eTestThreadLocal.get());
        }

        this.testContext.setExtentTest(eTestThreadLocal.get().createNode(scenario.getName()));

        if (current_iteration % total_scenarios_count == 0) {
            Globals.counterTracker.get().put("current_iteration", Globals.counterTracker.get().get("current_iteration") + 1);
        }
        Globals.counterTracker.get().put(featureNameTemp, current_iteration + 1);
    }

    @BeforeStep
    public void beforeStep(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
        Field f = scenario.getClass().getDeclaredField("delegate");
        f.setAccessible(true);
        io.cucumber.core.backend.TestCaseState sc = (io.cucumber.core.backend.TestCaseState) f.get(scenario);
        Field f1 = sc.getClass().getDeclaredField("testCase");
        f1.setAccessible(true);
        io.cucumber.plugin.event.TestCase testCase = (io.cucumber.plugin.event.TestCase) f1.get(sc);
        List<PickleStepTestStep> testSteps = testCase.getTestSteps().stream().filter(x -> x instanceof PickleStepTestStep).map(x -> (PickleStepTestStep) x).collect(Collectors.toList());

//        for (PickleStepTestStep ts : testSteps) {
//            System.out.println(ts.getStep().getKeyword() + ts.getStep().getText());
//        }
        curr_step_name = testSteps.get(stepCount++).getStep().getText();
        //this.testContext.getExtentTest().createNode(curr_step_name).log(Status.INFO,  curr_step_name+ " is started.....!");
        this.testContext.getExtentTest().log(Status.INFO, curr_step_name + " is started.....!");
    }

    @AfterStep
    public void afterStep(Scenario scenario) throws IOException {
        if (scenario.isFailed() || !scenario.isFailed()) {
            File screenshot = ((TakesScreenshot) this.testContext.getDriver()).getScreenshotAs(OutputType.FILE);
            //scenario.attach(screenshot, "image/png", scenario.getName());
            String dest_path = ExtentReportingService.getScreenshot_path() + curr_step_name + "_" + iter + ".png";
            FileHandler.copy(screenshot, new File(dest_path));
            //this.testContext.getExtentTest().addScreenCaptureFromBase64String(screenshot, curr_step_name + " -- " + scenario.getStatus());
            this.testContext.getExtentTest().log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(dest_path).build());
        }
        if (scenario.getStatus() == io.cucumber.java.Status.PASSED) {
            this.testContext.getExtentTest().log(Status.PASS, curr_step_name + " step is passed.....!");
        } else if (scenario.getStatus() == io.cucumber.java.Status.FAILED) {
            this.testContext.getExtentTest().log(Status.FAIL, curr_step_name + " step is failed.....!");
        } else if (scenario.getStatus() == io.cucumber.java.Status.SKIPPED) {
            this.testContext.getExtentTest().log(Status.SKIP, curr_step_name + " step is skipped.....!");
        } else if (scenario.getStatus() == io.cucumber.java.Status.UNDEFINED) {
            this.testContext.getExtentTest().log(Status.WARNING, curr_step_name + " step is undefined.....!");
        } else if (scenario.getStatus() == io.cucumber.java.Status.PENDING) {
            this.testContext.getExtentTest().log(Status.WARNING, curr_step_name + " step is pending.....!");
        } else if (scenario.getStatus() == io.cucumber.java.Status.AMBIGUOUS) {
            this.testContext.getExtentTest().log(Status.WARNING, curr_step_name + " step is ambiguous.....!");
        } else {
            this.testContext.getExtentTest().log(Status.WARNING, curr_step_name + " step is not defined.....!");
        }
    }

    @After
    public void after(Scenario scenario) throws IOException {
        if (scenario.isFailed() || !scenario.isFailed()) {
            File screenshot = ((TakesScreenshot) this.testContext.getDriver()).getScreenshotAs(OutputType.FILE);
            //scenario.attach(screenshot, "image/png", scenario.getName());
            String dest_path = ExtentReportingService.getScreenshot_path() + curr_step_name + "_" + iter + ".png";
            FileHandler.copy(screenshot, new File(dest_path));
            //this.testContext.getExtentTest().addScreenCaptureFromBase64String(screenshot, curr_step_name + " -- " + scenario.getStatus());
            this.testContext.getExtentTest().log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(dest_path).build());
        }
        if (scenario.getStatus() == io.cucumber.java.Status.PASSED) {
            this.testContext.getExtentTest().log(Status.PASS, curr_step_name + " scenario is passed.....!");
        } else if (scenario.getStatus() == io.cucumber.java.Status.FAILED) {
            this.testContext.getExtentTest().log(Status.FAIL, curr_step_name + " scenario is failed.....!");
        } else if (scenario.getStatus() == io.cucumber.java.Status.SKIPPED) {
            this.testContext.getExtentTest().log(Status.SKIP, curr_step_name + " scenario is skipped.....!");
        } else if (scenario.getStatus() == io.cucumber.java.Status.UNDEFINED) {
            this.testContext.getExtentTest().log(Status.WARNING, curr_step_name + " scenario is undefined.....!");
        } else if (scenario.getStatus() == io.cucumber.java.Status.PENDING) {
            this.testContext.getExtentTest().log(Status.WARNING, curr_step_name + " scenario is pending.....!");
        } else if (scenario.getStatus() == io.cucumber.java.Status.AMBIGUOUS) {
            this.testContext.getExtentTest().log(Status.WARNING, curr_step_name + " scenario is ambiguous.....!");
        } else {
            this.testContext.getExtentTest().log(Status.WARNING, curr_step_name + " scenario is not defined.....!");
        }
    }

    @AfterAll
    public static void afterAll() {
        extent.flush();
    }

}
