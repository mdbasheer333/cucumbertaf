package org.cucumbertaf.testlib.context;

import io.cucumber.java.Scenario;
import org.cucumbertaf.pageobjects.registerpage.RegisterPage;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public class TestContext {
    public WebDriver driver;
    public String featureName;
    public String scenarioName;
    public List<Map<String, String>> data;
    public Scenario logger;
    public RegisterPage registerPage;

    public String some_info;

//    public String getSome_info() {
//        return some_info;
//    }
//
//    public void setSome_info(String some_info) {
//        this.some_info = some_info;
//    }

}
