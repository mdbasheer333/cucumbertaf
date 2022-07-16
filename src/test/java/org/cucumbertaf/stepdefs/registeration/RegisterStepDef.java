package org.cucumbertaf.stepdefs.registeration;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.cucumbertaf.context.TestContext;
import org.cucumbertaf.pageobjects.registerpage.RegisterPage;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public class RegisterStepDef {

    private final WebDriver driver;
    private final List<Map<String, String>> data;

    public RegisterStepDef(TestContext context) {
        this.driver = context.driver;
        this.data = context.data;
    }

    @When("register page should displayed")
    public void register_page_should_displayed() {
        RegisterPage registerPage = new RegisterPage(this.driver);
        registerPage.load("https://demo.nopcommerce.com/register?returnUrl=%2F");
    }

    @When("fill all the details in registration page")
    public void fill_all_the_details_in_registration_page() {
        RegisterPage registerPage = new RegisterPage(this.driver);
        registerPage.fillRegistrationDetails(data.get(0));
    }

    @When("submits the details")
    public void submis_the_details() {
        RegisterPage registerPage = new RegisterPage(this.driver);
        registerPage.submitRegistrationDetails();
    }

    @Then("registration should be success")
    public void registration_should_be_success() {
        System.out.println("-------registration_should_be_success");
    }

}
