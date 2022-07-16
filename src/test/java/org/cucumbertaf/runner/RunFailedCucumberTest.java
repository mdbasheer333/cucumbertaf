package org.cucumbertaf.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports/cucumber.html", "json:target/cucumber-reports/cucumber.json", "rerun:target/failedrerun.txt"},
        features = {"@target/failedrerun.txt"},
        glue = {"org.cucumbertaf.stepdefs", "org.cucumbertaf.hooks"})
public class RunFailedCucumberTest extends AbstractTestNGCucumberTests {

}
