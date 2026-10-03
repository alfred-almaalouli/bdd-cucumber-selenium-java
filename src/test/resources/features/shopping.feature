Feature: Product list and shopping cart
  As a logged-in customer
  I want to find products and put them in my cart

  Background:
    Given I am logged in as "standard_user"

  @smoke
  Scenario: Add a product to the cart
    When I add "Sauce Labs Backpack" to the cart
    Then the cart badge shows "1"

  @regression
  Scenario: Remove a product from the cart
    Given I add "Sauce Labs Backpack" to the cart
    And I add "Sauce Labs Bike Light" to the cart
    When I remove "Sauce Labs Backpack" from the cart
    Then the cart badge shows "1"
    And the cart contains only "Sauce Labs Bike Light"

  @regression
  Scenario Outline: Sort the product list
    When I sort the products by "<sort option>"
    Then the first product is "<first product>"

    Examples:
      | sort option         | first product                     |
      | Name (A to Z)       | Sauce Labs Backpack               |
      | Name (Z to A)       | Test.allTheThings() T-Shirt (Red) |
      | Price (low to high) | Sauce Labs Onesie                 |
      | Price (high to low) | Sauce Labs Fleece Jacket          |
