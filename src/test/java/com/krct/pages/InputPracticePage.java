package com.krct.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InputPracticePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private final By inputLogButton =
            By.xpath("//a[@href='/inputs']");

    private final By numberField =
            By.id("input-number");

    private final By textField =
            By.id("input-text");

    private final By passwordField =
            By.id("input-password");

    private final By dateField =
            By.id("input-date");

    private final By displayButton =
            By.id("btn-display-inputs");

    private final By outputNumber =
            By.id("output-number");

    private final By outputText =
            By.id("output-text");

    private final By outputPassword =
            By.id("output-password");

    private final By outputDate =
            By.id("output-date");

    public InputPracticePage(WebDriver driver,
                             WebDriverWait wait,
                             JavascriptExecutor js) {

        this.driver = driver;
        this.wait = wait;
        this.js = js;
    }

    public boolean inputLoginUser(int number,
                                  String text,
                                  String password,
                                  String date) {

        inputPage();

        fillDetails(number, text, password, date);

        displayBtn();

        return displayVerify(
                String.valueOf(number),
                text,
                password,
                date
        );
    }

    public void inputPage() {

        WebElement inputElement =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                inputLogButton
                        )
                );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                inputElement
        );

        js.executeScript(
                "arguments[0].click();",
                inputElement
        );
    }

    public void fillDetails(int number,
                            String text,
                            String password,
                            String date) {

        closeAdIfPresent();

        WebElement numberInput =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                numberField
                        )
                );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                numberInput
        );

        numberInput.clear();

        numberInput.sendKeys(String.valueOf(number));

        WebElement textInput =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                textField
                        )
                );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                textInput
        );

        textInput.clear();

        textInput.sendKeys(text);

        WebElement passwordInput =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                passwordField
                        )
                );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                passwordInput
        );

        passwordInput.clear();

        passwordInput.sendKeys(password);

        closeAdIfPresent();

        WebElement dateInput =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                dateField
                        )
                );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                dateInput
        );

        closeAdIfPresent();

        js.executeScript(
                "arguments[0].click();",
                dateInput
        );

        dateInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));

        dateInput.sendKeys(date);

        dateInput.sendKeys(Keys.TAB);
    }

    public void displayBtn() {

        WebElement display =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                displayButton
                        )
                );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                display
        );

        js.executeScript(
                "arguments[0].click();",
                display
        );
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

    public boolean displayVerify(String expectedNumber,
                                 String expectedText,
                                 String expectedPassword,
                                 String expectedDate) {

        closeAdIfPresent();

        String actualNumber =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                outputNumber
                        )
                ).getText().trim();

        String actualText =
                driver.findElement(outputText)
                        .getText().trim();

        String actualPassword =
                driver.findElement(outputPassword)
                        .getText().trim();

        String actualDate =
                driver.findElement(outputDate)
                        .getText().trim();

        String formattedDate =
                expectedDate.substring(6, 10)
                        + "-"
                        + expectedDate.substring(0, 2)
                        + "-"
                        + expectedDate.substring(3, 5);

        return actualNumber.equals(expectedNumber)
                && actualText.equals(expectedText)
                && actualPassword.equals(expectedPassword)
                && actualDate.equals(formattedDate);
    }
}