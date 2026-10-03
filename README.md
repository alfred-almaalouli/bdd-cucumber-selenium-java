# BDD Test Automation with Cucumber (Java)

![BDD Tests](https://github.com/alfred-almaalouli/bdd-cucumber-selenium-java/actions/workflows/tests.yml/badge.svg)

Behaviour-driven tests for the [Sauce Demo](https://www.saucedemo.com) web shop. The test scenarios are written in plain English with **Gherkin** (Given / When / Then), so product owners and business analysts can read them, and are automated with **Cucumber**, **Selenium WebDriver** and **Java**.

## Example scenario

```gherkin
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
```

## Coverage

| Feature | Scenarios |
|---------|-----------|
| [Login](src/test/resources/features/login.feature) | successful login, 5 invalid combinations (Scenario Outline) |
| [Product list and cart](src/test/resources/features/shopping.feature) | add / remove products, 4 sort options (Scenario Outline) |
| [Checkout](src/test/resources/features/checkout.feature) | complete order with totals, 3 required-field checks (Scenario Outline) |

**16 scenarios** in total, tagged `@smoke` (critical path) and `@regression`.

## Gherkin / Cucumber features used

- `Background` for shared preconditions
- `Scenario Outline` with `Examples` tables for data-driven scenarios
- Data tables (`List<String>`) as step input
- Tags (`@smoke`, `@regression`) to run subsets
- Hooks: screenshot attached to the report when a scenario fails
- Dependency injection with PicoContainer: all step classes share one browser per scenario
- HTML and JSON reports

## Project structure

```
src/test/resources/features/   Gherkin feature files
src/test/java/com/alfred/bdd/
  steps/                       step definitions (Login, Shopping, Checkout)
  pages/                       Page Object Model with explicit waits
  support/TestContext.java     browser per scenario (shared via PicoContainer)
  support/Hooks.java           screenshot on failure, close browser
  RunCucumberTest.java         JUnit 5 suite that runs all features
```

## Run the tests

```bash
mvn test                                     # all scenarios, headless Chrome
mvn test -Dcucumber.filter.tags="@smoke"     # only the smoke tests
mvn test -Dheaded=true                       # watch the browser
```

The report is written to `target/cucumber-report.html`. In GitHub Actions the smoke tests run first, then the full regression, and the report is uploaded as an artifact.

## Tech stack

Java 17 · Cucumber 7 · Gherkin · Selenium WebDriver 4 · JUnit 5 Platform · PicoContainer · Maven · GitHub Actions

## Author

Alfred Al Maalouli – [GitHub](https://github.com/alfred-almaalouli)
