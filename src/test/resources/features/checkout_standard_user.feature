Feature: Checkout Process - SauceDemo

  As a standard user
  I want to complete the checkout flow
  So that I can successfully purchase products

  Background: Login as standard user and gets item in the cart
    Given I am on the SauceDemo login page
    When I login as "standard_user" with password "secret_sauce"
    And I add the "Sauce Labs Backpack" to cart
    And I go the cart
    Then The cart should contain 1 item

  @smoke
  Scenario: Successful product checkout
    When I proceed to checkout
    And I fill the checkout form with:
       | firstName   | lastName   | postalCode |
       | Mohamed     | Benali     | 20000      |
    And I continue to overview
    And I finish the checkout
    Then I should see the confirmation page "Thank you for your order!"

  @negative
  Scenario Outline: Failed product checkout
    When I proceed to checkout
    And  I fill the checkout form with:
      | firstName   | lastName   | postalCode |
      | <firstname> | <lastname> | <postalcode>|
    And I continue to overview
    Then I should see an error message "<error_message>"

    Examples:
      | firstname | lastname | postalcode | error_message                       |
      | Ahmed     |          | 20000      | Error: Last Name is required        |
      |           | El Idrissi | 20000    | Error: First Name is required       |
      | Karim     | Yassine  |            | Error: Postal Code is required      |






