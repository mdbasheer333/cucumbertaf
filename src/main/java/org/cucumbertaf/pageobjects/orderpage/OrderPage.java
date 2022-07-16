package org.cucumbertaf.pageobjects.orderpage;

import org.cucumbertaf.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends BasePage {

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "VisitingTable1")
    private WebElement table;

    public WebElement getTable(){
        return table;
    }

}
