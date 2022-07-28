package org.cucumbertaf.corelib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverClass {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return driverThreadLocal.get();
    }

    public static void setDriverNull(){
        if(getDriver()!=null)
            getDriver().quit();
        driverThreadLocal.set(null);
    }

    public static WebDriver getDriverInstance(String browserName) throws Exception {

        if(driverThreadLocal.get()!=null){
            return getDriver();
        }

        switch (browserName) {
            case "chrome":
            case "gc":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
                driverThreadLocal.set(new ChromeDriver());
                //driverThreadLocal.get().manage().window().maximize();
                break;
            case "edge":
            case "msedge":
                System.setProperty("webdriver.edge.driver", "src/test/resources/drivers/msedgedriver.exe");
                driverThreadLocal.set(new EdgeDriver());
//                driver = new EdgeDriver();
//                driver.manage().window().maximize();
                break;
            case "ff":
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
                driverThreadLocal.set(new FirefoxDriver());
//                driver = new FirefoxDriver();
//                driver.manage().window().maximize();
                break;
            default:
                throw new Exception("Invalid browser provided "  + browserName);
        }
        driverThreadLocal.get().manage().window().maximize();
        return driverThreadLocal.get();
    }

}
