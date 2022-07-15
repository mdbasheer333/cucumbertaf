package org.cucumbertaf.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(this.driver, 30);
    }

    public void selectByTextFromDropdown(WebElement element, String optionToSelect) {
        Select select = new Select(element);
        select.selectByVisibleText(optionToSelect);
    }

    public void selectByIndexFromDropdown(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public void selectByValueFromDropdown(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public List<String> getAllDropdownValues(WebElement element) {
        Select select = new Select(element);
        return select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
    }

}
