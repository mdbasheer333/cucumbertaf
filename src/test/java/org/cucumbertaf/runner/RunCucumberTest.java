package org.cucumbertaf.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.cucumbertaf.corelib.DriverClass;
import org.testng.annotations.AfterTest;

@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports/cucumber.html", "json:target/cucumber-reports/cucumber.json", "rerun:target/failedrerun.txt", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features = {"src/test/resources"},
        glue = {"org.cucumbertaf.stepdefs", "org.cucumbertaf.hooks"})
public class RunCucumberTest extends AbstractTestNGCucumberTests {

    @AfterTest
    public void afterTest(){
        DriverClass.setDriverNull();
    }

}
