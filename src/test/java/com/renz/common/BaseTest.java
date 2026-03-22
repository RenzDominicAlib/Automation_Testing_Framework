package com.renz.common;

import com.renz.driver.DriverManager;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class BaseTest {

    public WebDriver driver;
    DriverManager invokeDriver = new DriverManager();

    public void invokingDriver() throws IOException {
        driver = invokeDriver.initializeDriver();
    }


}
