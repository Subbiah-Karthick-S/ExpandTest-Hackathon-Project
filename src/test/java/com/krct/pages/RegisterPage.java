package com.krct.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private final By regButton = By.xpath("//a[@href='/register']");
    private final By userField = By.id("username");
    private final By passwordField = By.id("password");
    private final By confirmPasswordField = By.id("confirmPassword");

    private final By registerButton = By.xpath("//button[@type='submit']");

    public RegisterPage(WebDriver driver,
                        WebDriverWait wait,
                        JavascriptExecutor js) {

        this.driver = driver;
        this.wait = wait;
        this.js = js;
    }

    public String registerUser(String username,
                               String password,
                               String confirmPassword) {

        register();
        fillDetails(username, password, confirmPassword);
        submitBtn();

        return flashMessage();
    }

    public void register() {

        WebElement registerElement =
                wait.until(ExpectedConditions.visibilityOfElementLocated(regButton));

        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                registerElement
        );

        registerElement.click();
    }

    public void fillDetails(String username,
                            String password,
                            String confirmPassword) {

        driver.findElement(userField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
    }

    public void submitBtn() {

        WebElement submit =
                wait.until(ExpectedConditions.elementToBeClickable(registerButton));

        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                submit
        );

        js.executeScript("arguments[0].click();", submit);
    }

    public String flashMessage() {

        WebElement flash =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));

        return flash.getText();
    }
}