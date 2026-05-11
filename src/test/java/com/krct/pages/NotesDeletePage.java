package com.krct.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NotesDeletePage {

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

    private final By notesDeleteButton =
            By.xpath("//button[@data-testid='note-delete']");


    private final By deleteButton =
            By.xpath("//button[@data-testid='note-delete-confirm']");

    private final By title =
            By.xpath("(//div[@data-testid='note-card-title'])[1]");

    private final By description =
            By.xpath("(//p[@data-testid='note-card-description'])[1]");

    public NotesDeletePage(WebDriver driver,
                         WebDriverWait wait,
                         JavascriptExecutor js) {

        this.driver = driver;
        this.wait = wait;
        this.js = js;
    }

    public boolean NoteLoginUser(String email,
                                 String password,
                                 String deletedTitle) {

        noteLogin();

        login();

        fillDetails(email, password);

        loginBtn();

        notesDelete(deletedTitle);

        return notesVerify(deletedTitle);
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

    public void notesDelete(String deletedTitle) {

        closeAdIfPresent();

        List<WebElement> notes =
                driver.findElements(
                        By.xpath("//div[@data-testid='note-card']")
                );

        for (WebElement note : notes) {

            String actualTitle =
                    note.findElement(
                            By.xpath(".//div[@data-testid='note-card-title']")
                    ).getText().trim();

            if (actualTitle.equalsIgnoreCase(deletedTitle)) {

                WebElement deleteBtn =
                        note.findElement(
                                By.xpath(".//button[@data-testid='note-delete']")
                        );

                js.executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        deleteBtn
                );

                js.executeScript(
                        "arguments[0].click();",
                        deleteBtn
                );

                break;
            }
        }

        WebElement confirmDelete =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                deleteButton
                        )
                );

        js.executeScript(
                "arguments[0].click();",
                confirmDelete
        );
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

    public boolean notesVerify(String deletedTitle) {

        closeAdIfPresent();

        List<WebElement> noteTitles =
                driver.findElements(
                        By.xpath("//div[@data-testid='note-card-title']")
                );

        for (WebElement title : noteTitles) {

            String actualTitle =
                    title.getText().trim();

            if (actualTitle.equalsIgnoreCase(deletedTitle)) {

                return false;
            }
        }

        return true;
    }
}