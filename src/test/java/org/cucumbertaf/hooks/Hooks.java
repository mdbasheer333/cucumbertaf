package org.cucumbertaf.hooks;

import io.cucumber.java.*;
import org.cucumbertaf.context.TestContext;
import org.cucumbertaf.corelib.DriverClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.sql.DriverManager;

public class Hooks {

    private WebDriver driver;
    private TestContext testContext;

    public Hooks(TestContext testContext){
        this.testContext=testContext;
    }

    @BeforeAll
    public  static void beforeAll() {
        System.out.println("-------beforeAll--------------");
    }

    @Before
    public void before() {
        System.out.println("--------before-------------");
        driver=DriverClass.getDriverInstance("chrome");
        this.testContext.driver=driver;
    }

//    @BeforeStep
//    public  void beforeStep() {
//        System.out.println("------beforeStep---------------");
//    }

    @AfterStep
    public  void afterStep(Scenario scenario) {
        System.out.println("-------afterStep--------------");
        if (scenario.isFailed() || !scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getId());
        }
    }

    @After
    public void after(Scenario scenario) {
        System.out.println("-------after--------------");
        if (scenario.isFailed() || !scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        driver.quit();
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("------afterAll---------------");
    }

}
