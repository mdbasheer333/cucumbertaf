package org.cucumbertaf.pageobjects.loginpage;

import org.cucumbertaf.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage implements LoginLocators {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = email_field)
    private WebElement email;

    @FindBy(id = password_filed)
    private WebElement password;

    @FindBy(xpath = login_field)
    private WebElement logIn;

    public void loginToApp(String mail, String pwd) {
        email.sendKeys(mail);
        password.sendKeys(pwd);
        logIn.click();
    }

    public void load(String url) {
        driver.get(url);
    }

}
