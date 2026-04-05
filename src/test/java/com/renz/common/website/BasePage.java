package com.renz.common.website;

import com.renz.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class BasePage {
    WebDriver driver;

    public BasePage() {
        // Grab the driver for the current thread
        this.driver = DriverManager.getDriver();
    }

    // This method captures the screen as a byte array
    public byte[] captureScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
