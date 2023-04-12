package org.cucumbertaf.stepdefs.orders;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.cucumbertaf.testbase.BaseStepDef;
import org.cucumbertaf.testlib.context.TestContext;

public class OrderStepDef extends BaseStepDef {

    public OrderStepDef(TestContext context) {
        super(context);
    }

    @When("user login to application")
    public void user_login_to_application() {
        System.out.println("-------user_login_to_application " + data);
        loginPage.load("https://demo.nopcommerce.com/login?returnUrl=%2F");
        String username = data.get(0).get("email");
        String password = data.get(0).get("password");
        loginPage.loginToApp(username, password);
        assertLogger.log("i am in user_login_to_application");
    }

    @When("checks out the order")
    public void checks_out_the_order() {
        System.out.println("------checks_out_the_order----------");
        this.context.setSome_info("...............basheer...........");
        assertLogger.assert_equals(1, 1, "both are not same", "both are same");
    }

    @When("submits the order")
    public void submis_the_order() {
        System.out.println("-------submis_the_order");
        assertLogger.log("i am in submis_the_order");
        assertLogger.assert_contains(new Integer[]{1, 2, 3, 5}, 5, "5 is not there in arr", "5 is there in arr");
    }

    @Then("oder should be placed successfully")
    public void oder_should_be_placed_successfully() {
        System.out.println("-------oder_should_be_placed_successfully");
        assertLogger.assert_equals("basheer", "basheer1", "names not matching", "names matching");
    }

}
