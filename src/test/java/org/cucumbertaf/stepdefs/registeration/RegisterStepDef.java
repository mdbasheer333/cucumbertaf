package org.cucumbertaf.stepdefs.registeration;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.cucumbertaf.testlib.context.TestContext;
import org.cucumbertaf.pageobjects.registerpage.RegisterPage;
import org.cucumbertaf.testlib.ctafassert.CTAFAssert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class RegisterStepDef {

    private final WebDriver driver;
    private final List<Map<String, String>> data;
    private final CTAFAssert assertLogger;


    private final TestContext testContext;

    RegisterPage registerPage;

    public RegisterStepDef(TestContext context) {
        this.driver = context.driver;
        this.data = context.data;
        registerPage = new RegisterPage(this.driver);
        context.registerPage = registerPage;
        this.testContext=context;
        assertLogger = new CTAFAssert(context.logger);
    }

    @When("register page should displayed")
    public void register_page_should_displayed() {
        registerPage.load("https://demo.nopcommerce.com/register?returnUrl=%2F");
        this.testContext.someVariable="ajsdlksajdlk lkjasd lkjasdlk lkjasdlkasjd ";
    }

    @When("fill all the details in registration page")
    public void fill_all_the_details_in_registration_page() {
        registerPage.fillRegistrationDetails(data.get(0));
        assertLogger.log("ia m in fill_all_the_details_in_registration_page");
    }

    @When("submits the details")
    public void submis_the_details() {
        registerPage.submitRegistrationDetails();
        Assert.assertEquals("", "");
        assertLogger.log("ia m in submis_the_details");
    }

    @Then("registration should be success")
    public void registration_should_be_success() {
        System.out.println("-------registration_should_be_success");
    }

}
