package org.cucumbertaf.stepdefs.orders;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import org.cucumbertaf.context.TestContext;
import org.cucumbertaf.stepdefs.GenericStepDef;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public class OrderStepDef{

    private final WebDriver driver;
    private final List<Map<String,String>> data;

    public OrderStepDef(TestContext context){
        this.driver=context.driver;
        this.data=context.data;
    }

    @When("user login to application")
    public void user_login_to_application() {

        System.out.println("-------user_login_to_application " + this.data);
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
        driver.findElement(By.id("Email")).sendKeys("jaskjdk");
        driver.findElement(By.id("Password")).sendKeys("jaskjdk");
    }
    @When("checks out the order")
    public void checks_out_the_order() {
        System.out.println("-------checks_out_the_order");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
    }
    @When("submits the order")
    public void submis_the_order() {
        System.out.println("-------submis_the_order");
    }
    @Then("oder should be placed successfully")
    public void oder_should_be_placed_successfully() {
        System.out.println("-------oder_should_be_placed_successfully");
    }

}
