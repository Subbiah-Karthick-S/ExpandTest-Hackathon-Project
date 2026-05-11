package com.krct.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotesLogin {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;




    private final By notelogButton = By.xpath("//a[@href='/notes/app']");

    private final By logButton = By.xpath("//a[@href='/notes/app/login']");
    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");

    private final By loginButton = By.xpath("//button[@type='submit']");

    private final By logoutButton =By.xpath("//button[contains(text(),'Logout')]");




    public NotesLogin(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
        this.driver = driver;
        this.wait = wait;
        this.js = js;
    }

    public String NoteLoginUser(String email, String password) {
        noteLogin();
        login();
        fillDetails(email,password);
        loginBtn();
        return flashMessage();

    }

    public void noteLogin() {

        WebElement loginElement =
                wait.until(ExpectedConditions.presenceOfElementLocated(notelogButton));

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                loginElement
        );

        wait.until(ExpectedConditions.elementToBeClickable(loginElement));

        js.executeScript("arguments[0].click();", loginElement);
    }

    public void login() {

        WebElement loginElement =
                wait.until(ExpectedConditions.presenceOfElementLocated(logButton));

        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                loginElement
        );

        loginElement.click();
    }

    public void fillDetails(String email,
                            String password) {

        driver.findElement(emailField).sendKeys(email);
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
                wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton)
                );

        return flash.getText();
    }


}
