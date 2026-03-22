Feature: Login and End to End test of Swag Lab

  Scenario: Success checkout of Swag Lab products
    Given user navigate to SauceLab Login Page: "https://www.saucedemo.com/"
    When user enter username: "standard_user"
    And user enter password: "secret_sauce"
    Then click Login button
    Then user is redirected to the "Product page"