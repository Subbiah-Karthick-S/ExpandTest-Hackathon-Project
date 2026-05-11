package com.krct.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckBoxPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private final By checkBoxPageButton =
            By.xpath("//a[@href='/checkboxes']");

    private final By checkBox1 =
            By.id("checkbox1");

    private final By checkBox2 =
            By.id("checkbox2");

    public CheckBoxPage(WebDriver driver,
                        WebDriverWait wait,
                        JavascriptExecutor js) {

        this.driver = driver;
        this.wait = wait;
        this.js = js;
    }

    public boolean checkBoxUser(boolean checkbox1Expected,
                                boolean checkbox2Expected) {

        checkBoxPage();

        setCheckbox(
                checkBox1,
                checkbox1Expected
        );

        setCheckbox(
                checkBox2,
                checkbox2Expected
        );

        return verifyCheckBoxes(
                checkbox1Expected,
                checkbox2Expected
        );
    }

    public void checkBoxPage() {

        driver.navigate().to(
                "https://practice.expandtesting.com/checkboxes"
        );

        closeAdIfPresent();
    }

    public void setCheckbox(By locator,
                            boolean shouldBeChecked) {

        closeAdIfPresent();

        WebElement checkbox =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                locator
                        )
                );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                checkbox
        );

        boolean currentState =
                checkbox.isSelected();

        if (currentState != shouldBeChecked) {

            js.executeScript(
                    "arguments[0].click();",
                    checkbox
            );
        }
    }

    public boolean verifyCheckBoxes(boolean expectedCheckbox1,
                                    boolean expectedCheckbox2) {

        boolean actualCheckbox1 =
                driver.findElement(checkBox1)
                        .isSelected();

        boolean actualCheckbox2 =
                driver.findElement(checkBox2)
                        .isSelected();

        return actualCheckbox1 == expectedCheckbox1
                && actualCheckbox2 == expectedCheckbox2;
    }

    public void closeAdIfPresent() {

        try {

            js.executeScript(
                    "document.querySelectorAll('iframe').forEach(el => el.remove());"
            );

        } catch (Exception e) {

            System.out.println("No Ad Popup Found");
        }
    }
}