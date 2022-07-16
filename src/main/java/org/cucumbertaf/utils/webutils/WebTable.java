package org.cucumbertaf.utils.webutils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class WebTable {

    WebElement element;

    public WebTable(WebElement element) {
        this.element = element;
    }

    public int getNumberOfRows() {
        return element.findElements(By.xpath("//tbody//tr")).size();
    }

    public int getNumberOfColumns() {
        int size = element.findElements(By.xpath("//thead//th")).size();
        if (size == 0) {
            size = element.findElements(By.xpath("//tr[1]//td")).size();
        }
        return size;
    }

    public String getCellValueAt(int row, int col) {
        return element.findElement(By.xpath("//tr[" + row + "]//td[" + col + "]")).getText();
    }

    public void clickAt(int row, int col) {
        element.findElement(By.xpath("//tr[" + row + "]//td[" + col + "]//*[not(@type='text')]")).click();
    }

    public void selectAt(int row, int col, String textToSelect) {
        WebElement dropdown = element.findElement(By.xpath("//tr[" + row + "]//td[" + col + "]//select"));
        Select select = new Select(dropdown);
        select.selectByVisibleText(textToSelect);
    }

    public void enterAt(int row, int col, String valueToEnter) {
        WebElement input = element.findElement(By.xpath("//tr[" + row + "]//td[" + col + "]//input[@type='text']"));
        input.sendKeys(valueToEnter);
    }

    public List<String> getColumnNames() {
        List<String> lst = element.findElements(By.xpath("//thead//th"))
                .stream().map(WebElement::getText)
                .map(String::trim).collect(Collectors.toList());
        if (lst.size() == 0) {
            lst = element.findElements(By.xpath("//tr[1]//td"))
                    .stream().map(WebElement::getText)
                    .map(String::trim).collect(Collectors.toList());
        }
        return lst;
    }

    public int getColumnIndexOf(String colName) {
        List<String> collect = getColumnNames();
        for (int i = 0; i < collect.size(); i++) {
            if (collect.get(i).trim().equals(colName)) {
                return i + 1;
            }
        }
        return -1;
    }

    public int getRowNumberHavingValueAtColumn(String value, String colName) {
        int columnIndexOf = getColumnIndexOf(colName);
        int noOfRows = getNumberOfRows();
        for (int i = 1; i <= noOfRows; i++) {
            String val = getCellValueAt(i, columnIndexOf).trim();
            if (value.equals(val)) {
                return i;
            }
        }
        return -1;
    }

}
