package com.renz.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DriverManager {

    public WebDriver driver;

    public WebDriver initializeDriver() throws IOException {

        Properties globalProp = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "\\src\\test\\resources\\config\\config.properties");
        globalProp.load(fis);

//        String browserName = globalProp.getProperty("browser"); // improve this with ternary condition. if supplying -Dbrowser=firefox in cmd line
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser"):globalProp.getProperty("browser");

        if (browserName.contains("chrome")) {
            ChromeOptions options = getChromeOptions();
            if(browserName.contains("headless")) {
                options.addArguments("--headless");
            }
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\main\\java\\driver\\webdriver\\geckodriver.exe");
            driver = new FirefoxDriver();

        } else if (browserName.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\main\\java\\driver\\webdriver\\msedgedriver.exe");
            driver = new EdgeDriver();
        }

        return driver;
    }

    public static ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();

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
}
