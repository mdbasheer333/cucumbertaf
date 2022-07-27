package org.cucumbertaf.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports/cucumber.html", "json:target/cucumber-reports/cucumber.json", "rerun:target/failedrerun.txt", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features = {"src/test/resources"},
        glue = {"org.cucumbertaf.stepdefs", "org.cucumbertaf.hooks"})
public class RunCucumberTest extends AbstractTestNGCucumberTests {

}
