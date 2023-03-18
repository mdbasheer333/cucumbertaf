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
//    static Map<String, Integer> counterTracker;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;

    }

    @BeforeAll
    public static void beforeAll() {
        //Globals.counterTracker = new HashMap<>();
    }

    @Before
    public void before(Scenario scenario) throws Exception {
        String browserProp = System.getProperty("browser");
        String browser = browserProp == null ? PropertyUtil.getProperty("browser") : browserProp;
        this.testContext.driver = DriverClass.getDriverInstance(browser);
        this.testContext.featureName = String.valueOf(scenario.getUri());
        this.testContext.scenarioName = scenario.getName();
        this.testContext.logger = scenario;

        String featureNameTemp = this.testContext.featureName.split("/")[this.testContext.featureName.split("/").length - 1].replaceAll(".feature", "");
        int iteraration = Globals.counterTracker.get().getOrDefault(featureNameTemp, 1);
        System.out.println("iterarationiterarationiteraration " + iteraration);

        ExcelReader reader = new ExcelReader(this.testContext.featureName, this.testContext.scenarioName, iteraration);
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
//        if (this.testContext.driver != null) {
//            this.testContext.driver.quit();
//        }
    }

    @AfterAll
    public static void afterAll() {
    }

}
