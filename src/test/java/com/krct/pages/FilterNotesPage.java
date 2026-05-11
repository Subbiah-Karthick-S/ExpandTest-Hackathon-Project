package com.krct.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FilterNotesPage {

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

    private final By displayedCategories =
            By.xpath("//span[@data-testid='note-card-category']");

    private final By notesCards =
            By.xpath("//div[@data-testid='note-card']");

    public FilterNotesPage(WebDriver driver,
                           WebDriverWait wait,
                           JavascriptExecutor js) {

        this.driver = driver;
        this.wait = wait;
        this.js = js;
    }

    public boolean NoteLoginUser(String email,
                                 String password,
                                 String category) {

        noteLogin();

        login();

        fillDetails(email, password);

        loginBtn();

        notesCategory(category);

        return notesVerify(category);
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

    public void notesCategory(String category) {

        By categoryButton =
                By.xpath(
                        "//button[@data-testid='category-"
                                + category.toLowerCase()
                                + "']"
                );

        WebElement categoryElement =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                categoryButton
                        )
                );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                categoryElement
        );

        js.executeScript(
                "arguments[0].click();",
                categoryElement
        );

        closeAdIfPresent();
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

    public boolean notesVerify(String expectedCategory) {

        closeAdIfPresent();

        By activeCategory =
                By.xpath(
                        "//button[@data-testid='category-"
                                + expectedCategory.toLowerCase()
                                + "']"
                                + "[contains(@class,'btnx-success')]"
                );

        WebElement activeButton =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                activeCategory
                        )
                );

        java.util.List<WebElement> visibleNotes =
                driver.findElements(
                        By.xpath("//div[@data-testid='note-card']")
                );

        System.out.println(
                "Visible Notes Count : " + visibleNotes.size()
        );

        return activeButton.isDisplayed()
                && visibleNotes.size() > 0;
    }
}