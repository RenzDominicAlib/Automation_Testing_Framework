package com.renz.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private static Properties prop;

    static {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load config.properties file!");
        }
    }

    public static String getProp(String key) {
        // 1. Check Command Line (-Dbrowser=edge)
        String value = System.getProperty(key);
        // 2. If null, check config.properties. If still null, return empty string.
        if (value == null) {
            value = prop.getProperty(key);
        }
        return (value != null) ? value : "";
    }
}
