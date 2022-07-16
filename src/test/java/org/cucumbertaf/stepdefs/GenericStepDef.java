package org.cucumbertaf.stepdefs;

import com.google.common.util.concurrent.Uninterruptibles;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.cucumbertaf.context.TestContext;
import org.cucumbertaf.pageobjects.orderpage.OrderPage;
import org.cucumbertaf.utils.property.PropertyUtil;
import org.cucumbertaf.utils.webutils.WebTable;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class GenericStepDef {

    private final WebDriver driver;
    private final List<Map<String, String>> data;
    private final Scenario log;

    public GenericStepDef(TestContext context) {
        this.driver = context.driver;
        this.data = context.data;
        this.log = context.logger;
    }

    @Given("user launches application")
    public void user_launches_application() {
        driver.get(PropertyUtil.getProperty("appUrl"));
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        System.out.println("-------user_launches_application");
    }

    @When("user perform web table testing")
    public void user_webtable_testing() {
        OrderPage orderPage = new OrderPage(driver);
        WebTable table = new WebTable(orderPage.getTable());
        System.out.println(table.getNumberOfRows());
        System.out.println(table.getNumberOfColumns());
        System.out.println(table.getColumnNames());
        System.out.println(table.getCellValueAt(3, 3));
        System.out.println(table.getColumnIndexOf("Designation"));
        System.out.println(table.getRowNumberHavingValueAtColumn("S148106", "Employee ID"));
    }

}
