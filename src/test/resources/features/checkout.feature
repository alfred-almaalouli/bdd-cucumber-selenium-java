Feature: Checkout
  As a customer
  I want to order the products in my cart
  So that they are delivered to me

  Background:
    Given I am logged in as "standard_user"

  @smoke
  Scenario: Complete an order with three products
    Given I add the following products to the cart:
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
      | Sauce Labs Onesie     |
    When I check out as "Alfred" "Tester" with postal code "45143"
    Then the item total is "$47.97"
    And the tax is "$3.84"
    And the order total is "$51.81"
    When I finish the order
    Then I see the confirmation "Thank you for your order!"
    And the cart is empty

  @regression
  Scenario Outline: Required customer information
    Given I add "Sauce Labs Onesie" to the cart
    When I check out as "<first name>" "<last name>" with postal code "<postal code>"
    Then I see the error message "<error>"

    Examples:
      | first name | last name | postal code | error                          |
      |            | Tester    | 45143       | Error: First Name is required  |
      | Alfred     |           | 45143       | Error: Last Name is required   |
      | Alfred     | Tester    |             | Error: Postal Code is required |
