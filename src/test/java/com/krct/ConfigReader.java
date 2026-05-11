package com.krct.pages;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    Properties prop;

    public ConfigReader() {

        try {

            prop = new Properties();

            FileInputStream fis =
                    new FileInputStream(
                            System.getProperty("user.dir")
                                    + "src/test/java/com/krct/config.properties");

            prop.load(fis);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public String getBaseUrl() {
        String url = prop.getProperty("baseUrl");


        if(url == null || url.trim().isEmpty()) {
            throw new RuntimeException("baseUrl is NOT found in config.properties! " +
                    "Checked file at: src/test/java/com/krct/pages/config.properties");
        }


        return url;
    }

    public String getBrowser() {

        return prop.getProperty("browser");
    }

    public int getTimeout() {

        String value = prop.getProperty("timeout");

        if(value == null) {

            return 10;
        }

        return Integer.parseInt(value);
    }
}