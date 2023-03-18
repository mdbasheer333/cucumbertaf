package org.cucumbertaf.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(this.driver, 60);
    }

    public void selectByTextFromDropdown(WebElement element, String optionToSelect) {
        new Select(element).selectByVisibleText(optionToSelect);
    }

    public void selectByIndexFromDropdown(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    public void selectByValueFromDropdown(WebElement element, String value) {
        new Select(element).selectByValue(value);
    }

    public List<String> getAllDropdownValues(WebElement element) {
        Select select = new Select(element);
        return select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void waitForElement(WebElement element) {
        wait.until(visibilityOf(element));
    }

    public void waitForAllElements(List<WebElement> element) {
        wait.until(visibilityOfAllElements(element));
    }

    public List<String> getListOfValues(List<WebElement> elements) {
        return elements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getElementText(WebElement element) {
        return element.getText();
    }

    public String getInputBoxValue(WebElement element) {
        return element.getAttribute("value");
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementEnabled(WebElement element) {
        return element.isEnabled();
    }

    public boolean isElementSelected(WebElement element) {
        return element.isSelected();
    }

    public void selectCheckBox(WebElement element) {
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void unSelectCheckBox(WebElement element) {
        if (element.isSelected()) {
            element.click();
        }
    }

    public void selectRadioButton(WebElement element) {
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void unSelectRadioButton(WebElement element) {
        if (element.isSelected()) {
            element.click();
        }
    }

    public void switchToFrameWithIndex(int index) {
        driver.switchTo().frame(index);
    }

    public void switchToFrameWithNameOrId(String nameOrid) {
        driver.switchTo().frame(nameOrid);
    }

    public void switchToFrameByElement(WebElement element) {
        driver.switchTo().frame(element);
    }

    public void switchToFrameWithIndexWithWait(int index) {
        wait.until(frameToBeAvailableAndSwitchToIt(index));
        switchToFrameWithIndex(index);
    }

    public void switchToFrameWithNameOrIdWithWait(String nameOrid) {
        wait.until(frameToBeAvailableAndSwitchToIt(nameOrid));
        switchToFrameWithNameOrId(nameOrid);
    }

    public void switchToFrameByElementWithWait(WebElement element) {
        wait.until(frameToBeAvailableAndSwitchToIt(element));
        switchToFrameByElement(element);
    }

    public void switchToWindowByTitle(String windowTitle) {
        String parentWindowName = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        boolean isWindowFound = false;
        for (String eachWindow : windowHandles) {
            driver.switchTo().window(eachWindow);
            if (driver.getTitle().equals(windowTitle)) {
                isWindowFound = true;
                break;
            }
        }
        if (!isWindowFound) {
            driver.switchTo().window(parentWindowName);
            throw new RuntimeException("window with title " + windowTitle + " not found");
        }
    }

    public void switchToWindowByPartialTitle(String windowTitle) {
        String parentWindowName = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        boolean isWindowFound = false;
        for (String eachWindow : windowHandles) {
            driver.switchTo().window(eachWindow);
            if (driver.getTitle().contains(windowTitle)) {
                isWindowFound = true;
                break;
            }
        }
        if (!isWindowFound) {
            driver.switchTo().window(parentWindowName);
            throw new RuntimeException("window with title " + windowTitle + " not found");
        }
    }

    public void switchToWindowByUrl(String url) {
        String parentWindowName = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        boolean isWindowFound = false;
        for (String eachWindow : windowHandles) {
            driver.switchTo().window(eachWindow);
            if (driver.getCurrentUrl().equals(url)) {
                isWindowFound = true;
                break;
            }
        }
        if (!isWindowFound) {
            driver.switchTo().window(parentWindowName);
            throw new RuntimeException("window with url " + url + " not found");
        }
    }

    public void switchToWindowByPartialUrl(String url) {
        String parentWindowName = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        boolean isWindowFound = false;
        for (String eachWindow : windowHandles) {
            driver.switchTo().window(eachWindow);
            if (driver.getCurrentUrl().contains(url)) {
                isWindowFound = true;
                break;
            }
        }
        if (!isWindowFound) {
            driver.switchTo().window(parentWindowName);
            throw new RuntimeException("window with url " + url + " not found");
        }
    }

    public String getCssProperty(WebElement element, String propertyName) {
        return element.getCssValue(propertyName);
    }

    public String getAttribute(WebElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    public boolean hasAttribute(WebElement element, String attributeName) {
        try {
            element.getAttribute(attributeName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeAsyncScript("arguments[0].scrollIntoView(true);", element);
    }

}
