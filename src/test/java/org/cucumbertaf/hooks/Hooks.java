package org.cucumbertaf.hooks;

import io.cucumber.java.*;
import org.cucumbertaf.context.TestContext;
import org.cucumbertaf.corelib.DriverClass;
import org.cucumbertaf.utils.excel.ExcelReader;
import org.cucumbertaf.utils.property.PropertyUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.HashMap;
import java.util.Map;

public class Hooks {

    private final TestContext testContext;
    static Map<String, Integer> counterTracker;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;

    }

    @BeforeAll
    public static void beforeAll() {
        counterTracker = new HashMap<>();
    }

    @Before
    public void before(Scenario scenario) throws Exception {
        String browserProp = System.getProperty("browser");
        String browser = browserProp == null ? PropertyUtil.getProperty("browser") : browserProp;
        this.testContext.driver = DriverClass.getDriverInstance(browser);
        this.testContext.featureName = String.valueOf(scenario.getUri());
        this.testContext.scenarioName = scenario.getName();
        this.testContext.logger = scenario;
        counterTracker.put(this.testContext.scenarioName, counterTracker.getOrDefault(this.testContext.scenarioName, 0) + 1);
        ExcelReader reader = new ExcelReader(this.testContext.featureName, this.testContext.scenarioName, counterTracker.getOrDefault(this.testContext.scenarioName, 1));
        this.testContext.data = reader.getAllData();
        scenario.log("browser name: " + browser);
        scenario.log("feature name: " + this.testContext.featureName);
        scenario.log("scenario name: " + this.testContext.scenarioName);
        scenario.log("data used is: " + this.testContext.data);
    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed() || !scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) this.testContext.driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getId());
        }
    }

    @After
    public void after(Scenario scenario) {
        System.out.println("-------after--------------");
        if (scenario.isFailed() || !scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) this.testContext.driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        if (this.testContext.driver != null) {
            this.testContext.driver.quit();
        }
    }

    @AfterAll
    public static void afterAll() {
    }

}
