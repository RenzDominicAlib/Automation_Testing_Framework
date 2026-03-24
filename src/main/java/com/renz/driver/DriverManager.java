package com.renz.driver;

import com.renz.helpers.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.io.FileInputStream;
import java.util.Properties;

public class DriverManager {
    // ThreadLocal ensures thread-safety during parallel execution
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {

        String browser = (System.getProperty("browser") != null)
                ? System.getProperty("browser").toLowerCase()
                : PropertyReader.getProp("browser").toLowerCase();

        String headlessProp = (System.getProperty("headless") != null)
                ? System.getProperty("headless")
                : PropertyReader.getProp("headless");

        boolean isHeadless = headlessProp.equalsIgnoreCase("true") || browser.contains("headless");

        if (browser.contains("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (isHeadless) options.addArguments("--headless=new");
            driver.set(new ChromeDriver(options));

        } else if (browser.contains("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            if (isHeadless) options.addArguments("-headless");
            driver.set(new FirefoxDriver(options));

        } else if (browser.contains("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            if (isHeadless) options.addArguments("--headless");
            driver.set(new EdgeDriver(options));
        }
        getDriver().manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver.get(); // Returns the driver belonging to the current thread
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove(); // Vital: removes the driver from the thread memory
        }
    }


}
