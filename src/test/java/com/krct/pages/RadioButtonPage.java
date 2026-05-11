package com.krct.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RadioButtonPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private final By radioButtonPageButton =
            By.xpath("//a[@href='/radio-buttons']");

    private final By colorRadioButtons =
            By.name("color");

    private final By sportRadioButtons =
            By.name("sport");

    public RadioButtonPage(WebDriver driver,
                           WebDriverWait wait,
                           JavascriptExecutor js) {

        this.driver = driver;
        this.wait = wait;
        this.js = js;
    }

    public boolean radioButtonUser(String color,
                                   String sport) {

        radioButtonPage();

        selectRadioButton(
                colorRadioButtons,
                color
        );

        selectRadioButton(
                sportRadioButtons,
                sport
        );

        return verifyRadioButtons(
                color,
                sport
        );
    }

    public void radioButtonPage() {

        driver.navigate().to(
                "https://practice.expandtesting.com/radio-buttons"
        );

        closeAdIfPresent();
    }

    public void selectRadioButton(By locator,
                                  String value) {

        closeAdIfPresent();

        List<WebElement> radioButtons =
                wait.until(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(
                                locator
                        )
                );

        for (WebElement radio : radioButtons) {

            String radioValue =
                    radio.getAttribute("value");

            if (radioValue.equalsIgnoreCase(value)) {

                js.executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        radio
                );

                js.executeScript(
                        "arguments[0].click();",
                        radio
                );

                break;
            }
        }
    }

    public boolean verifyRadioButtons(String expectedColor,
                                      String expectedSport) {

        boolean colorVerified =
                verifySingleSelection(
                        colorRadioButtons,
                        expectedColor
                );

        boolean sportVerified =
                verifySingleSelection(
                        sportRadioButtons,
                        expectedSport
                );

        return colorVerified && sportVerified;
    }

    public boolean verifySingleSelection(By locator,
                                         String expectedValue) {

        List<WebElement> radioButtons =
                driver.findElements(locator);

        int selectedCount = 0;

        boolean correctSelected = false;

        for (WebElement radio : radioButtons) {

            if (radio.isSelected()) {

                selectedCount++;

                String actualValue =
                        radio.getAttribute("value");

                if (actualValue.equalsIgnoreCase(expectedValue)) {

                    correctSelected = true;
                }
            }
        }

        return selectedCount == 1 && correctSelected;
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