package com.renz.projects.website.saucelabdemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.*;


public class EndToEnd {

    public static void main(String[] args) throws InterruptedException {

        // Create a HashMap for Chrome preferences
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromePrefs.put("profile.password_manager_leak_detection", false); // The important one

        // Configure Chrome options
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", chromePrefs);

        // Initialize the WebDriver with the configured options
        WebDriver driver = new ChromeDriver(chromeOptions);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String[] productsToBuy = {"Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)"};
        ArrayList<String> productsToBuy_list = new ArrayList<>(Arrays.asList(productsToBuy));


       List<WebElement> products_names = driver.findElements(By.xpath("//div[@class='inventory_item_description']//div[@class='inventory_item_name ']"));
        List<WebElement> products_add_buttons = driver.findElements(By.xpath("//div[@class='inventory_item_description']//button"));

        for(int i=0; i<products_names.size(); i++){
            String product_name = products_names.get(i).getText();
            WebElement add_button = products_add_buttons.get(i);
            if(productsToBuy_list.contains(product_name)){
                js.executeScript("arguments[0].scrollIntoView();", products_names.get(i));
                add_button.click();
                System.out.println(product_name+" is ADDED TO CART");
            }
        }

        WebElement cart_icon = driver.findElement(By.xpath("//a[@class=\"shopping_cart_link\"]"));
        cart_icon.click();

        ArrayList<String> cart_productList = new ArrayList<String>();
        List<WebElement> cart_elements = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));

        for (WebElement cartElement : cart_elements) {
            String cart_product_name = cartElement.getText();
            cart_productList.add(cart_product_name);
            System.out.println(cart_product_name + " is FOUND IN THE CART");
        }
        Assert.assertEquals(productsToBuy_list, cart_productList);

        WebElement checkout_button = driver.findElement(By.id("checkout"));
        checkout_button.click();

        WebElement chkoutinfo_fname = driver.findElement(By.id("first-name"));
        WebElement chkoutinfo_lname = driver.findElement(By.id("last-name"));
        WebElement chkoutinfo_zipcode = driver.findElement(By.id("postal-code"));
        WebElement continue_button = driver.findElement(By.id("continue"));
        WebElement cancel_button = driver.findElement(By.id("cancel"));


        try{
            driver.switchTo().alert().accept();
        }catch(Exception e){
            continue_button.click();
        }

        chkoutinfo_fname.sendKeys("TEST");
        chkoutinfo_lname.sendKeys("RENZ");
        chkoutinfo_zipcode.sendKeys("1234");
        continue_button.click();


        ArrayList<String> checkout_productList = new ArrayList<String>();
        List<WebElement> checkout_productNames = driver.findElements(By.xpath("//div[@class='cart_item_label']//div[@class='inventory_item_name']"));
        List<WebElement> checkout_productPrices = driver.findElements(By.xpath("//div[@class='cart_item_label']//div[@class='inventory_item_price']"));
        double actualPriceTotal = 0.0;


        for(int k=0; k<checkout_productNames.size(); k++){
            String checkout_productName = checkout_productNames.get(k).getText();
            checkout_productList.add(checkout_productName);
            String checkout_productPrice = checkout_productPrices.get(k).getText().substring(1);
            System.out.println(checkout_productName + "(" + checkout_productPrice + ") is FOUND IN THE CHECK OUT SUMMARY");
            double price = Double.parseDouble(checkout_productPrice);
            actualPriceTotal = actualPriceTotal + price;
        }
        Assert.assertEquals(productsToBuy_list, checkout_productList);

        WebElement subtotal = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']"));
        js.executeScript("arguments[0].scrollIntoView();", subtotal);
        String[] subtotalText = subtotal.getText().split(": ");
        double displayPriceTotal = Double.parseDouble(subtotalText[1].substring(1));

        System.out.println(displayPriceTotal + " DISPLAYED PRICE TOTAL");
        System.out.println(actualPriceTotal + " ACTUAL PRICE TOTAL");
        Assert.assertEquals(displayPriceTotal, actualPriceTotal);

        WebElement finish_button = driver.findElement(By.id("finish"));
        finish_button.click();

        WebElement thankyou_message = driver.findElement(By.xpath("//h2[@class='complete-header']"));
        thankyou_message.isDisplayed();
        System.out.println(thankyou_message.getText());


        driver.quit();

        System.out.println("END");




    }

}
