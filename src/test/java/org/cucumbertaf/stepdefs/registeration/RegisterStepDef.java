package org.cucumbertaf.stepdefs.registeration;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.basecucumbertaf.testlib.context.ITestContext;
import org.cucumbertaf.testbase.BaseStepDef;


public class RegisterStepDef extends BaseStepDef {

    public RegisterStepDef(ITestContext context) {
        super(context);
    }

    @When("register page should displayed")
    public void register_page_should_displayed() {
        registerPage.load("https://demo.nopcommerce.com/register?returnUrl=%2F");
        assertLogger.log("navigated to page");
    }

    @When("fill all the details in registration page")
    public void fill_all_the_details_in_registration_page() {
        registerPage.fillRegistrationDetails(data);
        assertLogger.log("ia m in fill_all_the_details_in_registration_page ");
    }

    @When("submits the details")
    public void submis_the_details() {
        registerPage.submitRegistrationDetails();
        assertLogger.assert_equals("a", "a","values are not same", "values are same");
        assertLogger.log("ia m in submis_the_details");
        context.getExl_write_data_map().put("message", "registration success");
    }

    @Then("registration should be success")
    public void registration_should_be_success() {
        System.out.println("-------registration_should_be_success");
        assertLogger.log("registration_should_be_success and also " + this.context.getSome_info());
    }

}
