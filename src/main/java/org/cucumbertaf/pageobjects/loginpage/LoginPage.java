package org.cucumbertaf.pageobjects.loginpage;

import org.cucumbertaf.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "Email")
    private WebElement email;

    @FindBy(id = "Password")
    private WebElement password;

    @FindBy(xpath = "//button[text()='Log in']")
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
