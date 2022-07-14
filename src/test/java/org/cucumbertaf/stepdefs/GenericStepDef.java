package org.cucumbertaf.stepdefs;

import io.cucumber.java.en.Given;
import org.cucumbertaf.context.TestContext;
import org.openqa.selenium.WebDriver;

public class GenericStepDef{

    private final WebDriver driver;

    public GenericStepDef(TestContext context){
        this.driver=context.driver;
    }

    @Given("user launches application")
    public void user_launches_application() {
        System.out.println("-------user_launches_application");
    }

}
