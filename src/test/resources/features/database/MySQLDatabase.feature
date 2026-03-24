Feature: Database User Lifecycle

  Scenario: Validate user CRUD operations
    Given I insert user "Jayson" with email "jayson@test.com" into the DB
    Then the DB should contain user "Jayson"
    When I update the email to "updated_jayson@test.com" for user "Jayson"
    And I delete user "Jayson" from the DB