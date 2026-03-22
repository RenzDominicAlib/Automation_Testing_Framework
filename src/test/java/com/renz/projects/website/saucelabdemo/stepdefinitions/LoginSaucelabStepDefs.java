package com.renz.projects.website.saucelabdemo.stepdefinitions;

import com.renz.common.BaseTest;
import com.renz.projects.website.saucelabdemo.pageObjects.SwagLabs_LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class LoginSaucelabStepDefs extends BaseTest {

    SwagLabs_LoginPage swaglab_loginpage = new SwagLabs_LoginPage(driver);


    @Given("user navigate to SauceLab Login Page: {string}")
    public void user_navigate_to_sauce_lab_login_page(String url) throws IOException {
        invokingDriver();
        swaglab_loginpage.navigate_to_saucelab(url);
        throw new io.cucumber.java.PendingException();
    }

    @When("user enter username: {string}")
    public void user_enter_username(String username) {
        swaglab_loginpage.populate_username(username);
        throw new io.cucumber.java.PendingException();
    }
    @When("user enter password: {string}")
    public void user_enter_password(String pass) {
        swaglab_loginpage.populate_password(pass);
        throw new io.cucumber.java.PendingException();
    }
    @Then("click Login button")
    public void click_login_button() {
        swaglab_loginpage.click_login();
        throw new io.cucumber.java.PendingException();
    }
    @Then("user is redirected to the {string}")
    public void user_is_redirected_to_the_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


}
