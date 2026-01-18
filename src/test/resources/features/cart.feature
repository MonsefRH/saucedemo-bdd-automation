@cart
Feature: Add to cart - SauceDemo

  As a user
  I want to add or remove items
  So that I can ensure the number items in the cart

  @smoke @positive
  Scenario Outline: Successful add one item to cart
    Given I am logged in  as "<username>"
    When I add the "Sauce Labs Backpack" to cart
    And I go the cart
    Then The cart should contain single item

    Examples:
    |  username               |
    |  standard_user          |
    |  problem_user           |
    |  error_user             |
    |  visual_user            |
    |  performance_glitch_user|

  @smoke @positive
  Scenario Outline:  Adding all the items to the cart
    Given I am logged in  as "<username>"
    When I add all the items to the cart
    And I go the cart
    Then The cart should contain 6 item

    Examples:
      |  username               |
      |  standard_user          |
      |  problem_user           |
      |  error_user             |
      |  visual_user            |
      |  performance_glitch_user|

  @smoke @positive
  Scenario Outline: Successful add one item to cart from the product page
    Given I am logged in  as "<username>"
    When I click on "Sauce Labs Backpack" product card
    And I add the product to cart
    And I go the cart
    Then The cart should contain 1 item

    Examples:
      |  username               |
      |  standard_user          |
      |  problem_user           |
      |  error_user             |
      |  visual_user            |
      |  performance_glitch_user|

  @smoke @positive
  Scenario Outline:  Remove item in the inventory page
    Given I am logged in  as "<username>"
    When I add the "Sauce Labs Backpack" to cart
    And I go the cart
    And The cart should contain single item
    And I go back the inventory
    And I remove "Sauce Labs Backpack" from cart
    And I go the cart
    Then the cart should be empty

    Examples:
      |  username               |
      |  standard_user          |
      |  problem_user           |
      |  error_user             |
      |  visual_user            |
      |  performance_glitch_user|