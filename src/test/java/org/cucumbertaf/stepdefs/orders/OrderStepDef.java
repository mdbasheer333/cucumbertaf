package org.cucumbertaf.stepdefs.orders;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.basecucumbertaf.testlib.context.ITestContext;
import org.cucumbertaf.testbase.BaseStepDef;


public class OrderStepDef extends BaseStepDef {

    public OrderStepDef(ITestContext context) {
        super(context);
    }

    @When("user login to application")
    public void user_login_to_application() {
        System.out.println("-------user_login_to_application " + data);
        loginPage.load("https://demo.nopcommerce.com/login?returnUrl=%2F");
        String username = data.get("email");
        String password = data.get("password");
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
    public void submits_the_order() {
        System.out.println("checks_out_the_order......................");
    }

    @When("submits the order1")
    public void submis_the_order1() {
        System.out.println("-------submis_the_order1");
        assertLogger.log("i am in submis_the_order1");
        assertLogger.assert_contains(new Integer[]{1, 2, 3, 5}, 55, "55 is not there in arr", "55 is there in arr");
    }

    @When("submits the order2")
    public void submis_the_order2() {
        System.out.println("-------submis_the_order2");
        assertLogger.log("i am in submis_the_order2");
        assertLogger.assert_contains(new Integer[]{1, 2, 3, 5}, 5, "5 is not there in arr", "5 is there in arr");
    }

    @When("submits the order3")
    public void submis_the_order3() {
        System.out.println("-------submis_the_order3");
        assertLogger.log("i am in submis_the_order3");
        assertLogger.assert_contains(new Integer[]{1, 2, 3, 5}, 5, "5 is not there in arr", "5 is there in arr");
    }

    @Then("oder should be placed successfully")
    public void oder_should_be_placed_successfully() {
        System.out.println("-------oder_should_be_placed_successfully");
        context.getExl_write_data_map().put("testing","hwllo this is testing");
        assertLogger.assert_equals("basheer", "basheer1", "names not matching", "names matching");
    }

}
