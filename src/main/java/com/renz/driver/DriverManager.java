package com.renz.driver;

import com.renz.helpers.PropertiesHelpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DriverManager {

    public WebDriver driver;

    public WebDriver initializeDriver() throws IOException {

//        Properties globalProp = new Properties();
//        FileInputStream fis = new FileInputStream(
//                System.getProperty("user.dir") + "\\src\\test\\resources\\config\\config.properties");
//        globalProp.load(fis);

//        String browserName = globalProp.getProperty("browser"); // improve this with ternary condition. if supplying -Dbrowser=firefox in cmd line
//        String browserName = System.getProperty("BROWSER") != null ? System.getProperty("browser"):globalProp.getProperty("browser");
        String browserName = System.getProperty("BROWSER") != null ? System.getProperty("browser"): PropertiesHelpers.getValue("BROWSER");

        if (browserName.contains("chrome")) {
            ChromeOptions options = getChromeOptions();
            if(browserName.contains("headless")) {
                options.addArguments("--headless");
            }
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = getFirefoxOptions();
            if(browserName.contains("headless")) {
                options.addArguments("--headless");
            }
            driver = new FirefoxDriver(options);

        } else if (browserName.equalsIgnoreCase("edge")) {
            EdgeOptions options = getEdgeOptions();
            if(browserName.contains("headless")) {
                options.addArguments("--headless");
            }
            driver = new EdgeDriver();
        }

        return driver;
    }

    public static ChromeOptions getChromeOptions(){
        // Create a HashMap for Chrome preferences
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromePrefs.put("profile.password_manager_leak_detection", false); // important to by pass the google popup for storing password

        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--ignore-ssl-errors=yes");
        options.addArguments("--test-type");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--remote-allow-origins=*");

        return options;
    }

    public static EdgeOptions getEdgeOptions(){
        EdgeOptions options = new EdgeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("autofill.profile_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");

        return options;
    }
    public static FirefoxOptions getFirefoxOptions(){
        FirefoxOptions options = new FirefoxOptions();
        return options;
    }



}
