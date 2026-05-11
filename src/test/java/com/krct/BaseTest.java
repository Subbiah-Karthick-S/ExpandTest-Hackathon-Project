package com.krct;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest{
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    private ConfigReader config = new ConfigReader();

    @BeforeMethod
    public void setUp(){
        //driverManager.ChromeDriver.setup();
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );

        //System.out.println(config.getBaseUrl());


    }

    public void navigateTo(){
        //driver.get("https://practice.expandtesting.com/");

        driver.get(config.getBaseUrl());

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