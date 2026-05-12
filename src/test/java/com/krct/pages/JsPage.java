package com.krct.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private final By jsAlertlogButton =
            By.xpath("//a[@type='button' and @href='/js-dialogs']");

    private final By jsAlertButton =
            By.xpath("//button[@id='js-alert']");

    private final By jsConfirmButton =
            By.xpath("//button[@id='js-confirm']");

    private final By flash =
            By.xpath("//p[@id='dialog-response']");

    private final By jsPromptButton =
            By.xpath("//button[@id='js-prompt']");



    public JsPage(WebDriver driver,
                  WebDriverWait wait,
                  JavascriptExecutor js) {

        this.driver = driver;
        this.wait = wait;
        this.js = js;
    }


    public void jsAlertLogin() {

        WebElement loginElement =
                wait.until(ExpectedConditions.elementToBeClickable(jsAlertlogButton));

        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                loginElement
        );

        js.executeScript("arguments[0].click();", loginElement);
    }


    public String jsAlertUser() {

        jsAlertLogin();

        return jsAlertClick();
    }

    public String jsAlertClick() {

        WebElement alertButton =
                wait.until(ExpectedConditions.elementToBeClickable(jsAlertButton));

        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                alertButton
        );

        js.executeScript("arguments[0].click();", alertButton);

        Alert alert =
                wait.until(ExpectedConditions.alertIsPresent());

        String text = alert.getText();

        alert.accept();

        return text;
    }

    public String jsConfirmUser(String option) {

        jsAlertLogin();

        return jsConfirmClick(option);
    }

    public String jsConfirmClick(String option) {

        WebElement confirmButton =
                wait.until(ExpectedConditions.elementToBeClickable(jsConfirmButton));

        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                confirmButton
        );

        js.executeScript("arguments[0].click();", confirmButton);

        Alert alert =
                wait.until(ExpectedConditions.alertIsPresent());

        if (option.equalsIgnoreCase("Ok")) {

            alert.accept();

        } else {

            alert.dismiss();
        }

        WebElement flashText =
                wait.until(ExpectedConditions.visibilityOfElementLocated(flash));

        return flashText.getText();
    }

    public String jsPromptUser(String prompt) {

        jsAlertLogin();

        return jsPromptClick(prompt);
    }

    public String jsPromptClick(String prompt) {

        WebElement promptButton =
                wait.until(ExpectedConditions.elementToBeClickable(jsPromptButton));

        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                promptButton
        );

        js.executeScript("arguments[0].click();", promptButton);

        Alert alert =
                wait.until(ExpectedConditions.alertIsPresent());

        alert.sendKeys(prompt);
        alert.accept();

        WebElement flashText =
                wait.until(ExpectedConditions.visibilityOfElementLocated(flash));

        return flashText.getText();
    }
}