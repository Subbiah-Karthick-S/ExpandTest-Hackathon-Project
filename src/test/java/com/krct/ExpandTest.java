package com.krct;

import com.krct.pages.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExpandTest extends BaseTest {

    @DataProvider
    public Object[][] userData() {

        return new Object[][]{
                {"practice", "SuperSecretPassword!", "You logged into a secure area!"},
                {"practice", "SecretPassword!", "Your password is invalid!"},
                {"", "", "Your username is invalid!"}
        };
    }

    @DataProvider
    public Object[][] logOutData() {

        return new Object[][]{
                {"practice", "SuperSecretPassword!"}
        };
    }

    @DataProvider
    public Object[][] notesData() {

        return new Object[][]{
                {
                        "subbiahkarthickcse@gmail.com",
                        "123456",
                        "Subbiah Karthick",
                        "Subbiah Karthick from HCLTech"
                }
        };
    }

    @DataProvider
    public Object[][] notesEditData() {

        return new Object[][]{
                {
                        "subbiahkarthickcse@gmail.com",
                        "123456",
                        "Subbiah Karthick Saravanan",
                        "Subbiah Karthick Saravanan is an Employee from HCLTech"
                }
        };
    }

    @DataProvider
    public Object[][] notesDeleteData() {

        return new Object[][]{
                {
                        "subbiahkarthickcse@gmail.com",
                        "123456",
                        "Subbiah Karthick Saravanan"
                }
        };
    }

    @DataProvider
    public Object[][] categoryData() {

        return new Object[][]{
                {
                        "subbiahkarthickcse@gmail.com",
                        "123456",
                        "Personal"
                }
        };
    }

    @Test(priority = 1, dataProvider = "userData")
    public void LoginTest(String username,
                          String password,
                          String flash) {

        LoginPage loginPage =
                new LoginPage(driver, wait, js);

        navigateTo();

        adBlockers();

        String txt =
                loginPage.LoginUser(username, password);

        Assert.assertEquals(txt, flash);
    }

    @Test(priority = 2, dataProvider = "logOutData")
    public void logoutTest(String username,
                           String password) {

        LogoutPage logoutPage =
                new LogoutPage(driver, wait, js);

        navigateTo();

        adBlockers();

        String txt =
                logoutPage.logoutUser(username, password);

        Assert.assertEquals(
                txt,
                "You logged out of the secure area!"
        );

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://practice.expandtesting.com/login"
        );
    }

    @Test(priority = 3, dataProvider = "notesData")
    public void NotesLoginTest(String email,
                               String password,
                               String expectedTitle,
                               String expectedDescription) {

        NotesLogin notesLoginPage =
                new NotesLogin(driver, wait, js);

        navigateTo();

        adBlockers();

        String actualTitle =
                notesLoginPage.NoteLoginUser(
                        email,
                        password,
                        expectedTitle,
                        expectedDescription
                );

        Assert.assertEquals(
                actualTitle,
                expectedTitle
        );

        String actualDescription =
                notesLoginPage.notesDescription();

        Assert.assertEquals(
                actualDescription,
                expectedDescription
        );
    }

    @Test(priority = 4, dataProvider = "notesEditData")
    public void NotesEditTest(String email,
                               String password,
                               String modifiedTitle,
                               String modifiedDescription) {

        NotesEditPage notesEditPage =
                new NotesEditPage(driver, wait, js);

        navigateTo();

        adBlockers();

        String actualTitle =
                notesEditPage.NoteLoginUser(
                        email,
                        password,
                        modifiedTitle,
                        modifiedDescription
                );

        Assert.assertEquals(
                actualTitle,
                modifiedTitle
        );

        String actualDescription =
                notesEditPage.notesDescription();

        Assert.assertEquals(
                actualDescription,
                modifiedDescription
        );
    }

    @Test(priority = 5, dataProvider = "notesDeleteData")
    public void NotesDeleteTest(String email,
                                String password,
                                String deletedTitle) {

        NotesDeletePage notesDeletePage =
                new NotesDeletePage(driver, wait, js);

        navigateTo();

        adBlockers();

        boolean isDeleted =
                notesDeletePage.NoteLoginUser(
                        email,
                        password,
                        deletedTitle
                );

        Assert.assertTrue(
                isDeleted,
                "Note was not deleted successfully"
        );
    }

    @Test(priority = 6, dataProvider = "categoryData")
    public void NotesFilterTest(String email,
                                String password,
                                String category) {

        FilterNotesPage notesFilterPage =
                new FilterNotesPage(driver, wait, js);

        navigateTo();

        adBlockers();

        boolean categoryVerified =
                notesFilterPage.NoteLoginUser(
                        email,
                        password,
                        category
                );

        Assert.assertTrue(
                categoryVerified,
                "Filtered notes do not belong to category: " + category
        );
    }
}