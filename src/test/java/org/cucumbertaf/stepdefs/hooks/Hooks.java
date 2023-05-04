package org.cucumbertaf.stepdefs.hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.cucumber.java.*;
import io.cucumber.plugin.event.PickleStepTestStep;
import org.cucumbertaf.corelib.DriverClass;
import org.cucumbertaf.testlib.context.TestContext;
import org.cucumbertaf.utils.Globals;
import org.cucumbertaf.utils.excel.ExcelReader;
import org.cucumbertaf.utils.property.PropertyUtil;
import org.cucumbertaf.utils.reporter.ExtentReportingService;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class Hooks {

    private final TestContext testContext;
    private static ExtentReports extent = null;
    private static final ThreadLocal<ExtentTest> eTestThreadLocal = new ThreadLocal<>();
    private int stepCount = 0;
    private int iter = 0;
    private String curr_step_name = "";

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @BeforeAll
    public static void beforeAll() {
        extent = ExtentReportingService.getInstance();
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

        String info = "" + this.testContext.getData();

        if (current_iteration == 1 || total_scenarios_count == 1 || ((current_iteration - 1) % total_scenarios_count == 0)) {
            eTestThreadLocal.set(extent.createTest(featureNameTemp + "_" + iteration_select, info));
            iter = current_iteration;
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
        curr_step_name = testSteps.get(stepCount++).getStep().getText();
    }

    @AfterStep
    public void afterStep(Scenario scenario) throws IOException {

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String time_stamp = sdf.format(timestamp);

        if (scenario.getStatus() == io.cucumber.java.Status.PASSED) {
            File screenshot = ((TakesScreenshot) this.testContext.getDriver()).getScreenshotAs(OutputType.FILE);
            String dest_path = ExtentReportingService.getScreenshot_path() + curr_step_name + "_" + time_stamp + ".png";
            FileHandler.copy(screenshot, new File(dest_path));
            this.testContext.getExtentTest().log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(dest_path).build());
        } else if (scenario.getStatus() == io.cucumber.java.Status.FAILED) {
            File screenshot = ((TakesScreenshot) this.testContext.getDriver()).getScreenshotAs(OutputType.FILE);
            String dest_path = ExtentReportingService.getScreenshot_path() + curr_step_name + "_" + time_stamp + ".png";
            FileHandler.copy(screenshot, new File(dest_path));
            this.testContext.getExtentTest().log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(dest_path).build());
            this.testContext.getExtentTest().fail(Globals.error);
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

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String time_stamp = sdf.format(timestamp);

        if (scenario.isFailed() || !scenario.isFailed()) {
            File screenshot = ((TakesScreenshot) this.testContext.getDriver()).getScreenshotAs(OutputType.FILE);
            String dest_path = ExtentReportingService.getScreenshot_path() + curr_step_name + "_" + time_stamp + ".png";
            FileHandler.copy(screenshot, new File(dest_path));
            this.testContext.getExtentTest().log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(dest_path).build());
        }
    }

    @AfterAll
    public static void afterAll() {
        extent.flush();
    }

}
