package com.krct.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotesEditPage {

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

    private final By notesEditButton =
            By.xpath("//button[@data-testid='note-edit']");

    private final By categoryDropdown =
            By.id("category");

    private final By status =
            By.id("completed");

    private final By titleField =
            By.id("title");

    private final By descriptionField =
            By.id("description");

    private final By saveButton =
            By.xpath("//button[@data-testid='note-submit']");

    private final By title =
            By.xpath("(//div[@data-testid='note-card-title'])[1]");

    private final By description =
            By.xpath("(//p[@data-testid='note-card-description'])[1]");

    public NotesEditPage(WebDriver driver,
                      WebDriverWait wait,
                      JavascriptExecutor js) {

        this.driver = driver;
        this.wait = wait;
        this.js = js;
    }

    public String NoteLoginUser(String email,
                                String password,
                                String modifiedTitle,
                                String modifiedDescription) {

        noteLogin();

        login();

        fillDetails(email, password);

        loginBtn();

        notesEdit(modifiedTitle, modifiedDescription);

        return notesVerify();
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

    public void notesEdit(String modifiedTitle,
                          String modifiedDescription) {

        WebElement editNote =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                notesEditButton
                        )
                );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                editNote
        );

        js.executeScript(
                "arguments[0].click();",
                editNote
        );

        WebElement titleInput =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                titleField
                        )
                );

        titleInput.clear();

        titleInput.sendKeys(modifiedTitle);

        WebElement descriptionInput =
                driver.findElement(descriptionField);

        descriptionInput.clear();

        descriptionInput.sendKeys(modifiedDescription);

        WebElement save =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                saveButton
                        )
                );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                save
        );

        js.executeScript(
                "arguments[0].click();",
                save
        );

        closeAdIfPresent();

        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        title,
                        modifiedTitle
                )
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

    public String notesVerify() {

        closeAdIfPresent();

        WebElement noteTitle =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                title
                        )
                );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                noteTitle
        );

        return noteTitle.getText().trim();
    }

    public String notesDescription() {

        closeAdIfPresent();

        WebElement noteDescription =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                description
                        )
                );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                noteDescription
        );

        return noteDescription.getText().trim();
    }
}