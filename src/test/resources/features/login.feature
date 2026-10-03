Feature: Login
  As a customer
  I want to log in to the shop
  So that I can buy products

  Background:
    Given I am on the login page

  @smoke
  Scenario: Successful login with valid credentials
    When I log in as "standard_user" with password "secret_sauce"
    Then I see the products page

  @regression
  Scenario Outline: Login is refused with invalid data
    When I log in as "<username>" with password "<password>"
    Then I see the error message "<error>"
    And I am still on the login page

    Examples:
      | username        | password     | error                                                                     |
      | standard_user   | wrong_pass   | Epic sadface: Username and password do not match any user in this service |
      | unknown_user    | secret_sauce | Epic sadface: Username and password do not match any user in this service |
      |                 | secret_sauce | Epic sadface: Username is required                                        |
      | standard_user   |              | Epic sadface: Password is required                                        |
      | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |
