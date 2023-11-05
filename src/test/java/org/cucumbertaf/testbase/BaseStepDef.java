package org.cucumbertaf.testbase;

import java.util.Map;

import org.basecucumbertaf.testlib.context.ITestContext;
import org.basecucumbertaf.testlib.ctafassert.ICTAFAssert;
import org.cucumbertaf.pageobjects.loginpage.LoginPage;
import org.cucumbertaf.pageobjects.registerpage.RegisterPage;
import org.openqa.selenium.WebDriver;

public class BaseStepDef {

    protected final WebDriver driver;
    protected final Map<String, String> data;
    protected final ICTAFAssert assertLogger;
    protected final ITestContext context;

    protected RegisterPage registerPage;
    protected LoginPage loginPage;

    public BaseStepDef(ITestContext context) {
        this.driver = context.getDriver();
        this.data = context.getData();
        this.assertLogger = new ICTAFAssert(context.getExtentTest());
        this.context = context;
        this.registerPage = new RegisterPage(this.driver);
        this.loginPage = new LoginPage(driver);
    }

}
