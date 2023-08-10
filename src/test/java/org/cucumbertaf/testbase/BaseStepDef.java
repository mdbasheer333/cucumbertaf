package org.cucumbertaf.testbase;

import org.cucumbertaf.pageobjects.loginpage.LoginPage;
import org.cucumbertaf.pageobjects.registerpage.RegisterPage;
import org.cucumbertaf.testlib.context.TestContext;
import org.cucumbertaf.testlib.ctafassert.CTAFAssert;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public class BaseStepDef {

    protected final WebDriver driver;
    protected final Map<String, String> data;
    protected final CTAFAssert assertLogger;
    protected final TestContext context;

    protected RegisterPage registerPage;
    protected LoginPage loginPage;

    public BaseStepDef(TestContext context) {
        this.driver = context.getDriver();
        this.data = context.getData();
        this.assertLogger = new CTAFAssert(context.getExtentTest());
        this.context = context;
        this.registerPage = new RegisterPage(this.driver);
        this.loginPage = new LoginPage(driver);
    }

}
