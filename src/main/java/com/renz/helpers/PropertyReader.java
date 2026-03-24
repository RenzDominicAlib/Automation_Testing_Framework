package com.renz.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private static Properties prop = new Properties();

    static {
        try {
            // 1. Determine environment from Command Line (-Denv=qa). Default is 'dev'.
            String env = System.getProperty("env") != null ? System.getProperty("env") : "qa";
            String path = "src/test/resources/config/" + env + ".properties";

            FileInputStream fis = new FileInputStream(path);
            prop.load(fis);
        } catch (Exception e) {
            throw new RuntimeException("Could not load environment properties!");
        }
    }

    public static String getProp(String key) {
        // 2. PRIORITY: Command Line (-Ddb.username=new_user) > Property File
        String value = System.getProperty(key);
        if (value == null) {
            value = prop.getProperty(key);
        }
        return value != null ? value : "";
    }
}
