package org.cucumbertaf.corelib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverClass {

    public static WebDriver getDriverInstance(String browserName) throws Exception {
        WebDriver driver = null;
        switch (browserName) {
            case "chrome":
            case "gc":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;
            case "edge":
            case "msedge":
                System.setProperty("webdriver.edge.driver", "src/test/resources/drivers/msedgedriver.exe");
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                break;
            case "ff":
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;
            default:
                throw new Exception("Invalid browser provided "  + browserName);
        }
        return driver;
    }

}
