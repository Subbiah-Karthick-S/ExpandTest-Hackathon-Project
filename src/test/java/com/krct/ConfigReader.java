package com.krct;

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
                                    + "/src/test/resources/config.properties"
                    );

            prop.load(fis);

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException("Failed to load config.properties");
        }
    }

    public String getBaseUrl() {

        String url = prop.getProperty("baseUrl");

        if (url == null || url.trim().isEmpty()) {

            throw new RuntimeException(
                    "baseUrl NOT found in config.properties"
            );
        }

        return url;
    }

    public String getBrowser() {

        return prop.getProperty("browser");
    }

    public int getTimeout() {

        String value = prop.getProperty("timeout");

        if (value == null) {

            return 10;
        }

        return Integer.parseInt(value);
    }
}