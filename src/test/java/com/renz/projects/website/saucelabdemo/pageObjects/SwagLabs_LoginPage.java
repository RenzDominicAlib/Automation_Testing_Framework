package com.renz.projects.website.saucelabdemo.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwagLabs_LoginPage {

    WebDriver driver;

    public SwagLabs_LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id ="user-name")
    WebElement username_field;

    @FindBy(id ="password")
    WebElement password_field;

    @FindBy(id ="login-button")
    WebElement login_button;

    public void navigate_to_saucelab(String url){
        driver.get(url);
    }

    public void populate_username(String uname){
        username_field.sendKeys(uname);
    }

    public void populate_password(String pw){
        password_field.sendKeys(pw);
    }

    public void click_login(){
        login_button.click();
    }



}
