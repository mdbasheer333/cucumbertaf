package org.cucumbertaf.stepdefs.orders;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.cucumbertaf.testlib.context.TestContext;
import org.cucumbertaf.pageobjects.loginpage.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class OrderStepDef{

    private final WebDriver driver;
    private final List<Map<String,String>> data;
    private final Scenario log;

    public OrderStepDef(TestContext context){
        this.driver=context.driver;
        this.data=context.data;
        this.log=context.logger;
    }

    @When("user login to application")
    public void user_login_to_application() {
        System.out.println("-------user_login_to_application " + data);
        LoginPage loginPage=new LoginPage(driver);
        loginPage.load("https://demo.nopcommerce.com/login?returnUrl=%2F");
        String username=data.get(0).get("email");
        String password=data.get(0).get("password");
        loginPage.loginToApp(username,password);
        log.log("i am in user_login_to_application");
    }
    @When("checks out the order")
    public void checks_out_the_order() {
        System.out.println("------checks_out_the_order----------");
        Assert.assertEquals(1, 2, "i am in checks_out_the_order");
        log.log("i am in checks_out_the_order");
    }
    @When("submits the order")
    public void submis_the_order() {
        System.out.println("-------submis_the_order");
        log.log("i am in submis_the_order");
    }
    @Then("oder should be placed successfully")
    public void oder_should_be_placed_successfully() {
        System.out.println("-------oder_should_be_placed_successfully");
    }

}
