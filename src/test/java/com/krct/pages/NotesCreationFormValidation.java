package com.krct.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotesCreationFormValidation {

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

    private final By notesCreationButton =
            By.xpath("//button[@data-testid='add-new-note']");

    private final By categoryDropdown =
            By.id("category");

    private final By status =
            By.id("completed");

    private final By titleField =
            By.id("title");

    private final By descriptionField =
            By.id("description");

    private final By createButton =
            By.xpath("//button[contains(text(),'Create')]");

    private final By validationMessage =
            By.xpath("//div[contains(text(),'Title is required')]");

    public NotesCreationFormValidation(WebDriver driver,
                                       WebDriverWait wait,
                                       JavascriptExecutor js) {

        this.driver = driver;
        this.wait = wait;
        this.js = js;
    }

    public String NoteLoginUser(String email,
                                String password,
                                String noteDescription) {

        noteLogin();

        login();

        fillDetails(email, password);

        loginBtn();

        return notesCreate(noteDescription);
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

    public String notesCreate(String noteDescription) {

        WebElement addNote =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                notesCreationButton
                        )
                );

        js.executeScript(
                "arguments[0].click();",
                addNote
        );

        WebElement dropdownElement =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                categoryDropdown
                        )
                );

        Select dropdown =
                new Select(dropdownElement);

        dropdown.selectByVisibleText("Personal");

        driver.findElement(status).click();

        WebElement description =
                driver.findElement(descriptionField);

        description.sendKeys(noteDescription);

        WebElement create =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                createButton
                        )
                );

        js.executeScript(
                "arguments[0].click();",
                create
        );

        try {

            WebElement validation =
                    wait.until(
                            ExpectedConditions.presenceOfElementLocated(
                                    By.xpath("//*[contains(text(),'Title is required')]")
                            )
                    );

            return validation.getText().trim();

        } catch (Exception e) {

            WebElement titleInput =
                    driver.findElement(titleField);

            String classValue =
                    titleInput.getAttribute("class");

            if (classValue.contains("is-invalid")) {

                return "Title is required";
            }

            return "";
        }
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