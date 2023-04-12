package org.cucumbertaf.stepdefs.hooks;

import io.cucumber.java.*;
import org.cucumbertaf.testlib.context.TestContext;
import org.cucumbertaf.corelib.DriverClass;
import org.cucumbertaf.utils.Globals;
import org.cucumbertaf.utils.excel.ExcelReader;
import org.cucumbertaf.utils.property.PropertyUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    private final TestContext testContext;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @BeforeAll
    public static void beforeAll() {
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

        if (current_iteration % total_scenarios_count == 0) {
            Globals.counterTracker.get().put("current_iteration", Globals.counterTracker.get().get("current_iteration") + 1);
        }

        Globals.counterTracker.get().put(featureNameTemp, current_iteration + 1);

    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed() || !scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) this.testContext.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }

    @After
    public void after(Scenario scenario) {
        System.out.println("-------after--------------");
        if (scenario.isFailed() || !scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) this.testContext.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }

    @AfterAll
    public static void afterAll() {
    }

}
