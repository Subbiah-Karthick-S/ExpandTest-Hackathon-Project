package com.krct.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DropDownPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private final By dropDownPageButton =
            By.xpath("//a[@href='/dropdown']");

    private final By simpleDropdown =
            By.id("dropdown");

    private final By elementsPerPageDropdown =
            By.id("elementsPerPageSelect");

    private final By countryDropdown =
            By.id("country");

    private final By loginButton =
            By.xpath("//a[@href='/dropdown']");

    public DropDownPage(WebDriver driver,
                        WebDriverWait wait,
                        JavascriptExecutor js) {

        this.driver = driver;
        this.wait = wait;
        this.js = js;
    }

    public boolean dropDownUser(String simpleOption,
                                String elementsPerPage,
                                String country) {

        dropDownPage();

        login();

        selectSimpleDropdown(simpleOption);

        selectElementsPerPage(elementsPerPage);

        selectCountry(country);

        return verifySelections(
                simpleOption,
                elementsPerPage,
                country
        );
    }

    public void dropDownPage() {

        driver.navigate().to(
                "https://practice.expandtesting.com"
        );

        closeAdIfPresent();
    }

    public void login() {

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

    public void selectSimpleDropdown(String option) {

        WebElement dropdownElement =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                simpleDropdown
                        )
                );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                dropdownElement
        );

        Select dropdown =
                new Select(dropdownElement);

        dropdown.selectByVisibleText(option);
    }

    public void selectElementsPerPage(String option) {

        closeAdIfPresent();

        WebElement dropdownElement =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                elementsPerPageDropdown
                        )
                );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                dropdownElement
        );

        Select dropdown =
                new Select(dropdownElement);

        dropdown.selectByVisibleText(option);
    }

    public void selectCountry(String country) {

        closeAdIfPresent();

        WebElement dropdownElement =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                countryDropdown
                        )
                );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                dropdownElement
        );

        Select dropdown =
                new Select(dropdownElement);

        dropdown.selectByVisibleText(country);
    }

    public boolean verifySelections(String expectedSimpleOption,
                                    String expectedElementsPerPage,
                                    String expectedCountry) {

        Select simpleSelect =
                new Select(
                        driver.findElement(simpleDropdown)
                );

        String actualSimpleOption =
                simpleSelect.getFirstSelectedOption()
                        .getText()
                        .trim();

        Select elementsSelect =
                new Select(
                        driver.findElement(elementsPerPageDropdown)
                );

        String actualElements =
                elementsSelect.getFirstSelectedOption()
                        .getText()
                        .trim();

        Select countrySelect =
                new Select(
                        driver.findElement(countryDropdown)
                );

        String actualCountry =
                countrySelect.getFirstSelectedOption()
                        .getText()
                        .trim();

        return actualSimpleOption.equals(expectedSimpleOption)
                && actualElements.equals(expectedElementsPerPage)
                && actualCountry.equals(expectedCountry);
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
}