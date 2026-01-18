@login
Feature: User Login on SauceDemo

  As a user
  I want to log in with valid credentials
  So that I can access the inventory

  Background: Be on the SauceDemo login page
    Given I am on the SauceDemo login page

  @smoke @positive
  Scenario Outline: Successful login
    When I enter username "<username>" and password "secret_sauce"
    And I click on the login button
    Then I should be redirected to the inventory page

    Examples:
    |  username               |
    |  standard_user          |
    |  problem_user           |
    |  error_user             |
    |  visual_user            |
    |  performance_glitch_user|


  @negative
  Scenario Outline: Failed login
    When I enter username "<username>" and password "<password>"
    And I click on the login button
    Then I should see an error message "<error_message>"
  
    Examples:
    |  username        | password        | error_message                                       |
    |  locked_out_user | secret_sauce    | Epic sadface: Sorry, this user has been locked out. |
    |  user_name       | password        | Epic sadface: Username and password do not match any user in this service |
    |                  | secret_sauce    | Epic sadface: Username is required                  |
    |  user_name       |                 | Epic sadface: Password is required                  |
    |                  |                 | Epic sadface: Username is required                  |
