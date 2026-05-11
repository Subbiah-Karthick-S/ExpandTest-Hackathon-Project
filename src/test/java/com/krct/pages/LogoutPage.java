package com.krct.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private final By logButton = By.xpath("//a[@href='/login']");
    private final By userField = By.id("username");
    private final By passwordField = By.id("password");

    private final By loginButton = By.xpath("//button[@type='submit']");
    private final By logoutButton = By.xpath("//a[@href='/logout']");

    public LogoutPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
        this.driver = driver;
        this.wait = wait;
        this.js = js;
    }

    public String logoutUser(String username, String password) {
        login();
        fillDetails(username,password);
        loginBtn();
        return Message();

    }

    public void login() {

        closeAdIfPresent();

        WebElement loginElement =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                loginButton
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

    public void closeAdIfPresent() {

        try {

            js.executeScript(
                    "document.querySelectorAll('iframe').forEach(el => el.remove());"
            );

        } catch (Exception e) {

            System.out.println("No Ad Popup Found");
        }
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

    public String Message() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));

        driver.findElement(logoutButton).click();

        WebElement lflash = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));

        return lflash.getText();
    }


}
