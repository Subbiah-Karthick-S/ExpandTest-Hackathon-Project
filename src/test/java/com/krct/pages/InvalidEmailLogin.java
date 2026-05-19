package com.krct.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InvalidEmailLogin {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private final By notelogButton =
            By.xpath("//a[@href='/notes/app']");

    private final By logButton =
            By.xpath("//a[@href='/notes/app/login']");

    private final By emailField =
            By.id("email");

    private final By passwordField =
            By.id("password");

    private final By loginButton =
            By.xpath("//button[@type='submit']");

    private final By fMessage =
            By.xpath("//div[@class='invalid-feedback' and text()='Email address is invalid']");

    public InvalidEmailLogin(WebDriver driver,
                      WebDriverWait wait,
                      JavascriptExecutor js) {

        this.driver = driver;
        this.wait = wait;
        this.js = js;
    }

    public String LoginUser(String email,String password) {

        noteLogin();

        login();

        fillDetails(email, password);

        loginBtn();

        return flashMessage();
    }

    public void noteLogin() {

        WebElement noteElement =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                notelogButton
                        )
                );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                noteElement
        );

        js.executeScript(
                "arguments[0].click();",
                noteElement
        );
    }

    public void login() {

        WebElement loginElement =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                logButton
                        )
                );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                loginElement
        );

        js.executeScript(
                "arguments[0].click();",
                loginElement
        );
    }

    public void fillDetails(String email,
                            String password) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        emailField
                )
        ).sendKeys(email);

        driver.findElement(passwordField)
                .sendKeys(password);
    }

    public void loginBtn() {

        WebElement submit =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                loginButton
                        )
                );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                submit
        );

        js.executeScript(
                "arguments[0].click();",
                submit
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