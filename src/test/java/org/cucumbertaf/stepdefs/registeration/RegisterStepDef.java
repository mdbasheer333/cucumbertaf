package org.cucumbertaf.stepdefs.registeration;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.cucumbertaf.context.TestContext;
import org.cucumbertaf.pageobjects.registerpage.RegisterPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class RegisterStepDef {

    private final WebDriver driver;
    private final List<Map<String, String>> data;
    private final Scenario log;

    RegisterPage registerPage;

    public RegisterStepDef(TestContext context) {
        this.driver = context.driver;
        this.data = context.data;
        this.log = context.logger;
        registerPage = new RegisterPage(this.driver);
        context.registerPage=registerPage;
    }

    @When("register page should displayed")
    public void register_page_should_displayed() {
        registerPage.load("https://demo.nopcommerce.com/register?returnUrl=%2F");
        log.log("navigated to page");
    }

    @When("fill all the details in registration page")
    public void fill_all_the_details_in_registration_page() {
        registerPage.fillRegistrationDetails(data.get(0));
        log.log("ia m in fill_all_the_details_in_registration_page");
    }

    @When("submits the details")
    public void submis_the_details() {
        registerPage.submitRegistrationDetails();
        Assert.assertEquals("","");
        log.log("ia m in submis_the_details");
    }

    @Then("registration should be success")
    public void registration_should_be_success() {
        System.out.println("-------registration_should_be_success");
    }

}
