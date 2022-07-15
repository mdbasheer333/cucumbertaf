package org.cucumbertaf.pageobjects.registerpage;

import org.cucumbertaf.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(id = "register-button")
    private WebElement regButton;

    public void load(String url) {
        driver.get(url);
    }

    public void fillRegistrationDetails(String firname, String lstname, String mail, String pswd, String cpswd){
        fname.sendKeys(firname);
        lname.sendKeys(lstname);
        email.sendKeys(mail);
        pwd.sendKeys(pswd);
        cpwd.sendKeys(cpswd);
    }
    public void submitRegistrationDetails(){
        regButton.click();
    }

}
