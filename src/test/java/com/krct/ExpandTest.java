package com.krct;

import com.krct.pages.LoginPage;
import com.krct.pages.LogoutPage;
import com.krct.pages.NotesLogin;
import com.krct.pages.RegisterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExpandTest extends BaseTest{
    @DataProvider
    public Object[][] registerData(){
        Object[][] testData = new Object[][]{
                {"subbiahkarthicksaravanan","123456","123456"},
        };
        return testData;
    }

    @DataProvider
    public Object[][] userData(){
        Object[][] testData = new Object[][]{
                {"subbiahkarthicksaravanan","123456","You logged into a secure area!"},
                {"subbiahkarthicksaravanan","123456789","Your password is invalid!"},
                {"","","Your username is invalid!"}
        };
        return testData;
    }

    @DataProvider
    public Object[][] logOutData(){
        Object[][] testData = new Object[][]{
                {"subbiahkarthicksaravanan","123456"},
        };
        return testData;
    }

    @Test(priority=1,dataProvider="registerData")
    public void RegisterUserTest(String username,String password,String confirmPassword){
        RegisterPage registerPage = new RegisterPage(driver,wait,js);
        navigateTo();
        adBlockers();
        String txt = registerPage.registerUser(username,password,confirmPassword);
        Assert.assertEquals(txt,"Successfully registered, you can log in now.");
    }

    @Test(priority=2,dataProvider="userData")
    public void LoginTest(String username,String password,String flash){
        LoginPage loginPage = new LoginPage(driver,wait,js);
        navigateTo();
        adBlockers();
        String txt = loginPage.LoginUser(username,password);
        Assert.assertEquals(txt,flash);
    }

    @Test(priority=3,dataProvider="logOutData")
    public void logoutTest(String username,String password){
        LogoutPage logoutPage = new LogoutPage(driver,wait,js);
        navigateTo();
        adBlockers();
        String txt = logoutPage.logoutUser(username,password);
        Assert.assertEquals(txt,"You logged out of the secure area!");
        Assert.assertEquals(driver.getCurrentUrl(),"https://practice.expandtesting.com/login");
    }

    @Test(priority=4)
    public void NotesLoginTest(){
            NotesLogin notesLoginPage = new NotesLogin(driver,wait,js);
            navigateTo();
            adBlockers();
            String txt = notesLoginPage.NoteLoginUser("subbiahkarthickcse@gmail.com","123456");
            Assert.assertEquals(txt,"Logout");
    }
}
