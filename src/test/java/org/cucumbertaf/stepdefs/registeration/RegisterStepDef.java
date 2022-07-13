package org.cucumbertaf.stepdefs.registeration;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.cucumbertaf.context.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterStepDef {

    private WebDriver driver;

    public RegisterStepDef(TestContext testContext){
        this.driver=testContext.driver;
    }

    @When("register page should displayed")
    public void register_page_should_displayed() {
        System.out.println("-------register_page_should_displayed");
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
