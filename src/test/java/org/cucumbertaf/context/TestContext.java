package org.cucumbertaf.context;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestContext {

    public WebDriver driver;
    public String featureName;
    public String scenarioName;

    public List<Map<String,String>> data;

}
