@checkout
Feature: Checkout Process - SauceDemo

  As a user
  I want to complete the checkout flow
  So that I can successfully purchase products

  @smoke @positive
  Scenario Outline: Checkout single product
    Given I am logged in  as "<username>"
    When I add the "Sauce Labs Backpack" to cart
    And I go the cart
    And I proceed to checkout
    And I fill the checkout form with:
      | firstName   | lastName   | postalCode |
      | Mohamed     | Benali     | 20000      |
    And I continue to overview
    And I finish the checkout
    Then I should see the confirmation page "Thank you for your order!"

    Examples:
      |  username               |
      |  standard_user          |
      |  problem_user           |
      |  error_user             |
      |  visual_user            |
      |  performance_glitch_user|

  @negative
  Scenario Outline: Failed product checkout
    Given I am logged in  as "standard_user"
    When I add the "Sauce Labs Backpack" to cart
    And I go the cart
    And I proceed to checkout
    And  I fill the checkout form with:
      | firstName   | lastName   | postalCode |
      | <firstname> | <lastname> | <postalcode>|
    And I continue to overview
    Then I should see an error message with "<error_message>"

    Examples:
      | firstname | lastname   | postalcode | error_message                       |
      | Ahmed     |            | 20000      | Error: Last Name is required        |
      |           | El Idrissi | 20000      | Error: First Name is required       |
      | Karim     | Yassine    |            | Error: Postal Code is required      |






