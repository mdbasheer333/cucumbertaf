package org.cucumbertaf.testlib.context;

import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.Scenario;
import org.cucumbertaf.pageobjects.registerpage.RegisterPage;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public class TestContext {

    private WebDriver driver;
    private String featureName;
    private String scenarioName;
    private List<Map<String, String>> data;
    private Scenario logger;
    private String some_info;
    private ExtentTest extentTest;

    public ExtentTest getExtentTest() {
        return extentTest;
    }

    public void setExtentTest(ExtentTest extentTest) {
        this.extentTest = extentTest;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public void setData(List<Map<String, String>> data) {
        this.data = data;
    }

    public void setLogger(Scenario logger) {
        this.logger = logger;
    }

    public void setSome_info(String some_info) {
        this.some_info = some_info;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getFeatureName() {
        return featureName;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public List<Map<String, String>> getData() {
        return data;
    }

    public Scenario getLogger() {
        return logger;
    }

    public String getSome_info() {
        return some_info;
    }

}
