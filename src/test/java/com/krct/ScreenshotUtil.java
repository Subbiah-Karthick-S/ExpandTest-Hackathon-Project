package com.krct;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;

public class ScreenshotUtil {

    public static void capture(WebDriver driver, String name) {

        try {

            File folder = new File("screenshots");

            if(!folder.exists()){
                folder.mkdir();
            }

            String path = "screenshots/" +
                    name.replace(" ", "_") + "_" +
                    System.currentTimeMillis() + ".png";

            File src =
                    ((TakesScreenshot)driver)
                            .getScreenshotAs(OutputType.FILE);

            Files.copy(src.toPath(), new File(path).toPath());

            System.out.println("Screenshot Saved: " + path);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}