package com.krct;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;
    //protected WebDriverManager driverManager;

    @BeforeMethod
    public void setUp(){
        //driverManager.ChromeDriver.setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        js = (JavascriptExecutor) driver;
    }

    public void navigateTo(){
        driver.get("https://practice.expandtesting.com/");
    }

    public void adBlockers(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("""
           document.querySelectorAll('.adsbygoogle')
                .forEach(el => el.remove());
        """);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    //WebDriverManager.chromeDriver.setup();


}
