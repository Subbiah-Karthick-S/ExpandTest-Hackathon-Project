package com.krct.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private final By logButton = By.xpath("//a[@href='/login']");
    private final By userField = By.id("username");
    private final By passwordField = By.id("password");

    private final By loginButton = By.xpath("//button[@type='submit']");

    private final By logoutButton = By.xpath("//a[@href='/logout']");

    public LoginPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
        this.driver = driver;
        this.wait = wait;
        this.js = js;
    }

    public String LoginUser(String username, String password) {
        login();
        fillDetails(username,password);
        loginBtn();
        return flashMessage();

    }

    public void login() {

        WebElement loginElement =
                wait.until(ExpectedConditions.visibilityOfElementLocated(logButton));

        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                loginElement
        );

        loginElement.click();
    }

    public void fillDetails(String username,
                            String password) {

        driver.findElement(userField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
    }

    public void loginBtn() {

        WebElement submit =
                wait.until(ExpectedConditions.elementToBeClickable(loginButton));

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
