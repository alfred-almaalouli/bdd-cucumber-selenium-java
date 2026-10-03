package com.alfred.bdd.steps;

import com.alfred.bdd.pages.InventoryPage;
import com.alfred.bdd.pages.LoginPage;
import com.alfred.bdd.support.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private static final String PASSWORD = "secret_sauce";

    private final TestContext context;

    public LoginSteps(TestContext context) {
        this.context = context;
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        new LoginPage(context.driver()).open();
    }

    @Given("I am logged in as {string}")
    public void iAmLoggedInAs(String username) {
        new LoginPage(context.driver()).open().login(username, PASSWORD);
        assertEquals("Products", new InventoryPage(context.driver()).title());
    }

    @When("I log in as {string} with password {string}")
    public void iLogInAs(String username, String password) {
        new LoginPage(context.driver()).login(username, password);
    }

    @Then("I see the products page")
    public void iSeeTheProductsPage() {
        InventoryPage inventory = new InventoryPage(context.driver());
        assertEquals("Products", inventory.title());
        assertEquals("/inventory.html", inventory.currentPath());
    }

    @Then("I see the error message {string}")
    public void iSeeTheErrorMessage(String expected) {
        assertEquals(expected, new LoginPage(context.driver()).errorMessage());
    }

    @Then("I am still on the login page")
    public void iAmStillOnTheLoginPage() {
        LoginPage login = new LoginPage(context.driver());
        assertTrue(login.isDisplayed());
        assertEquals("/", login.currentPath());
    }
}
