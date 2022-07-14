package org.cucumbertaf.runner;

import io.cucumber.java.*;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports/cucumber.html", "json:target/cucumber-reports/cucumber.json"},
        features = {"src/test/resources"},
        glue = {"org.cucumbertaf.stepdefs", "org.cucumbertaf.hooks"})
public class RunCucumberTest extends AbstractTestNGCucumberTests {

}
