package org.cucumbertaf.pageobjects.registerpage;

import org.cucumbertaf.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "FirstName")
    private WebElement fname;

    @FindBy(id = "LastName")
    private WebElement lname;

    @FindBy(id = "Email")
    private WebElement email;

    @FindBy(id = "Password")
    private WebElement pwd;

    @FindBy(id = "ConfirmPassword")
    private WebElement cpwd;

    @FindBy(name = "DateOfBirthMonth")
    private WebElement dob;

    @FindBy(id = "register-button")
    private WebElement regButton;

    public void load(String url) {
        driver.get(url);
    }

    public void fillRegistrationDetails(Map<String, String> registerData) {
        fname.sendKeys(registerData.get("fname"));
        lname.sendKeys(registerData.get("lname"));
        selectByTextFromDropdown(dob, registerData.get("dobmonth"));
        email.sendKeys(registerData.get("mail"));
        pwd.sendKeys(registerData.get("password"));
        cpwd.sendKeys(registerData.get("confirmpassword"));
    }

    public void submitRegistrationDetails() {
        regButton.click();
    }

}
