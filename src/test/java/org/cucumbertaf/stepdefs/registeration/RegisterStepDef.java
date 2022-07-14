package org.cucumbertaf.stepdefs.registeration;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.cucumbertaf.context.TestContext;
import org.cucumbertaf.stepdefs.GenericStepDef;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public class RegisterStepDef{

    private final WebDriver driver;
    private final List<Map<String,String>> data;

    public RegisterStepDef(TestContext context){
        this.driver=context.driver;
        this.data=context.data;
    }

    @When("register page should displayed")
    public void register_page_should_displayed() {
        System.out.println("-------register_page_should_displayed" + this.data);
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
    }

    @When("fill all the details in registration page")
    public void fill_all_the_details_in_registration_page() {
        System.out.println("-------fill_all_the_details_in_registration_page");
        driver.findElement(By.id("FirstName")).sendKeys("fname");
        driver.findElement(By.id("LastName")).sendKeys("lname");
    }

    @When("submits the details")
    public void submis_the_details() {
        driver.findElement(By.id("Email123")).sendKeys("Email@Email.com");
        driver.findElement(By.id("Password")).sendKeys("fname");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("ConfirmPassword");
        System.out.println("-------submis_the_details");
        driver.findElement(By.id("register-button")).click();
    }

    @Then("registration should be success")
    public void registration_should_be_success() {
        System.out.println("-------registration_should_be_success");
    }

}
