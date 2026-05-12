package com.krct;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.lang.reflect.Method;

import static com.krct.ExtentManager.extent;

import java.nio.file.Files;
import java.time.Duration;

public class BaseTest{
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;
    protected ExtentTest test;

    private ConfigReader config = new ConfigReader();


    @BeforeMethod
    public void setUp(Method method){

        test = extent.createTest(method.getName());

        driver = new ChromeDriver();

        js = (JavascriptExecutor) driver;

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );
    }
//    @BeforeMethod
//    public void setUp(){
//        //driverManager.ChromeDriver.setup();
//        test = extent.createTest(method.getName());
//        driver = new ChromeDriver();
//        js = (JavascriptExecutor) driver;
//
//        wait = new WebDriverWait(
//                driver,
//                Duration.ofSeconds(20)
//        );
//
//        //System.out.println(config.getBaseUrl());
//    }

    @BeforeSuite
    public void reportSetup(){
        ExtentSparkReporter reporter =
                new ExtentSparkReporter("reports/report.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
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

//    @AfterMethod
//    public void tearDown(){
//        driver.quit();
//    }

    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        if(result.getStatus() == ITestResult.SUCCESS){
            test.pass("Test Passed");
        } else if(result.getStatus() == ITestResult.FAILURE){
            File folder = new File("screenshots");
            if(!folder.exists()){
                folder.mkdir();
            }
            String path = "screenshots/" + System.currentTimeMillis() + ".png";
            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), new File(path).toPath());
            test.fail(result.getThrowable());
            test.addScreenCaptureFromPath(path);
        }
        driver.quit();
    }


    @AfterSuite
    public void flushReport(){
        extent.flush();
    }
    //WebDriverManager.chromeDriver.setup();


}