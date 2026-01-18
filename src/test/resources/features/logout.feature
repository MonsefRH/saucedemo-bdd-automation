@logout
Feature: Logout - SauceDemo

    As a user
    I can log out normally

  @smoke @positive
  Scenario Outline: Successful logout
    Given I am logged in  as "<username>"
    And I click on Logout
    Then I should be redirected to the login page

    Examples:
      |  username               |
      |  standard_user          |
      |  problem_user           |
      |  error_user             |
      |  visual_user            |
      |  performance_glitch_user|