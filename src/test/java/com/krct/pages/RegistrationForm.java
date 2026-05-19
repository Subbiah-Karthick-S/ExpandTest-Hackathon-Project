package com.krct.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationForm{

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private final By reglogButton =
            By.xpath("//a[@href='/register' and text()='Test Register Page']");

    private final By userField =
            By.id("username");

    private final By passwordField =
            By.id("password");

    private final By confirmPasswordField =
            By.id("confirmPassword");

    private final By registerButton =
            By.xpath("//button[@type='submit' and text()='Register']");

    private final By fMessage = By.xpath("//div[@id='flash']");

    public RegistrationForm(WebDriver driver,
                                       WebDriverWait wait,
                                       JavascriptExecutor js) {

        this.driver = driver;
        this.wait = wait;
        this.js = js;
    }

    public String RegistrationUser(String username,
                                String password) {

        regLogin();

        fillDetails(username, password);

        regBtn();

        return flashMessage();
    }

    public void regLogin() {

        WebElement regElement =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                reglogButton
                        )
                );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                regElement
        );

        js.executeScript(
                "arguments[0].click();",
                regElement
        );
    }

    public void fillDetails(String username, String password) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        userField
                )
        ).sendKeys(username);

        driver.findElement(passwordField)
                .sendKeys(password);

        driver.findElement(confirmPasswordField)
                .sendKeys(password);
    }

    public void regBtn() {

        WebElement register =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                registerButton
                        )
                );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                register
        );

        js.executeScript(
                "arguments[0].click();",
                register
        );
    }

    public String flashMessage() {

        WebElement flash =
                wait.until(ExpectedConditions.visibilityOfElementLocated(fMessage));

        return flash.getText();
    }

    public void closeAdIfPresent() {

        try {

            WebElement adClose =
                    wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    By.xpath("//button[contains(@class,'close')]")
                            )
                    );

            js.executeScript(
                    "arguments[0].click();",
                    adClose
            );

        } catch (Exception e) {

            System.out.println("No Ad Popup Found");
        }
    }
}