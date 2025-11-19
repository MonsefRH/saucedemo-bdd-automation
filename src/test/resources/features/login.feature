Feature: User Login on SauceDemo

  As a user
  I want to log in with valid credentials
  So that I can access the inventory

  @smoke
  Scenario: Successful login with standard user
    Given I am on the SauceDemo login page
    When I enter username "standard_user"
    And I enter password "secret_sauce"
    And I click the login button
    Then I should be redirected to the inventory page

  @negative
  Scenario: Failed login with invalid password
    Given I am on the SauceDemo login page
    When I enter username "standard_user"
    And I enter password "wrong_password"
    And I click the login button
    Then I should see an error message "Epic sadface: Username and password do not match"