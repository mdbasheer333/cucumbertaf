package org.cucumbertaf.hooks;

import io.cucumber.java.*;
import org.cucumbertaf.context.TestContext;
import org.cucumbertaf.corelib.DriverClass;
import org.cucumbertaf.utils.excel.ExcelReader;
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
        this.testContext.driver = DriverClass.getDriverInstance("chrome");
        this.testContext.featureName = String.valueOf(scenario.getUri());
        this.testContext.scenarioName = scenario.getName();
        counterTracker.put(this.testContext.scenarioName, counterTracker.getOrDefault(this.testContext.scenarioName, 0) + 1);
        ExcelReader reader = new ExcelReader(this.testContext.featureName, this.testContext.scenarioName, counterTracker.getOrDefault(this.testContext.scenarioName, 1));
        this.testContext.data = reader.getAllData();
        System.out.println("*************** " + this.testContext.data);
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
        this.testContext.driver.quit();
    }

    @AfterAll
    public static void afterAll() {
    }

}
