Feature: Login and End to End test of Swag Lab
TODO
# To execute parallel
# mvn test -D"cucumber.filter.tags=@renztest" -Ddataproviderthreadcount=6
#  mvn test -Dbrowser=edgeheadless -D"cucumber.filter.tags=@renztest" -Ddataproviderthreadcount=6
#  mvn test -Denv=dev -D"cucumber.filter.tags=@renztest" -Ddataproviderthreadcount=6
#  masterthought reporting
#  target/masterthought-report/cucumber-html-reports/report-feature_3623688244.html
# for pipeline
#  mvn test -Denv=qa -Dheadless=true -D"cucumber.filter.tags=@renztest" -Ddataproviderthreadcount=6

#
  @renztest   @smoke
  Scenario: Success login using STANDARD USER
    Given user navigate to SauceLab Login Page: "https://www.saucedemo.com/"
    When user enter username: "standard_user"
    And user enter password: "secret_sauce"
    Then click Login button
    Then I take a screenshot for evidence
#    Then user is redirected to the "Product page"

  @renztest
  Scenario: Success login using LOCKED OUT USER
    Given user navigate to SauceLab Login Page: "https://www.saucedemo.com/"
    When user enter username: "locked_out_user"
    And user enter password: "secret_sauce"
    Then click Login button
    Then I take a screenshot for evidence
#    Then user is redirected to the "Product page"

  @renztest
  Scenario: Success login using PROBLEM USER
    Given user navigate to SauceLab Login Page: "https://www.saucedemo.com/"
    When user enter username: "problem_user"
    And user enter password: "secret_sauce"
    Then click Login button
    Then I take a screenshot for evidence
#    Then user is redirected to the "Product page"

  @renztest
  Scenario: Success login using PERFORMANCE GLITCH USER
    Given user navigate to SauceLab Login Page: "https://www.saucedemo.com/"
    When user enter username: "performance_glitch_user"
    And user enter password: "secret_sauce"
    Then click Login button
    Then I take a screenshot for evidence
#    Then user is redirected to the "Product page"

  @renztest
  Scenario: Success login using ERROR USER
    Given user navigate to SauceLab Login Page: "https://www.saucedemo.com/"
    When user enter username: "error_user"
    And user enter password: "secret_sauce"
    Then click Login button
    Then I take a screenshot for evidence
#    Then user is redirected to the "Product page"

  @renztest
  Scenario: Success login using VISUAL USER
    Given user navigate to SauceLab Login Page: "https://www.saucedemo.com/"
    When user enter username: "visual_user"
    And user enter password: "secret_sauce"
    Then click Login button
    Then I take a screenshot for evidence
#    Then user is redirected to the "Product page"

  @renz
  Scenario: Success checkout of Swag Lab products
    Given user navigate to SauceLab Login Page: "https://www.saucedemo.com/"
    When user enter username: "standard_user"
    And user enter password: "secret_sauce"
    Then click Login button
#    Then user is redirected to the "Product page"

  @renz
  Scenario: Success checkout of Swag Lab products
    Given user navigate to SauceLab Login Page: "https://www.saucedemo.com/"
    When user enter username: "standard_user"
    And user enter password: "secret_sauce"
    Then click Login button
#    Then user is redirected to the "Product page"