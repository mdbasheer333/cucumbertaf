package org.cucumbertaf.stepdefs;

import com.google.common.util.concurrent.Uninterruptibles;
import io.cucumber.java.en.Given;
import org.cucumbertaf.context.TestContext;
import org.cucumbertaf.utils.property.PropertyUtil;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class GenericStepDef{

    private final WebDriver driver;

    public GenericStepDef(TestContext context){
        this.driver=context.driver;
    }

    @Given("user launches application")
    public void user_launches_application() {
        driver.get(PropertyUtil.getProperty("appUrl"));
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        System.out.println("-------user_launches_application");
    }

}
