package com.alfred.bdd.steps;

import com.alfred.bdd.pages.CartPage;
import com.alfred.bdd.pages.CheckoutPage;
import com.alfred.bdd.pages.InventoryPage;
import com.alfred.bdd.support.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutSteps {

    private final TestContext context;

    public CheckoutSteps(TestContext context) {
        this.context = context;
    }

    private CheckoutPage checkout() {
        return new CheckoutPage(context.driver());
    }

    @When("I check out as {string} {string} with postal code {string}")
    public void iCheckOutAs(String firstName, String lastName, String postalCode) {
        new InventoryPage(context.driver()).openCart();
        new CartPage(context.driver()).checkout();
        checkout().enterCustomer(firstName, lastName, postalCode);
    }

    @Then("the item total is {string}")
    public void theItemTotalIs(String amount) {
        assertEquals(amount, checkout().itemTotal());
    }

    @Then("the tax is {string}")
    public void theTaxIs(String amount) {
        assertEquals(amount, checkout().tax());
    }

    @Then("the order total is {string}")
    public void theOrderTotalIs(String amount) {
        assertEquals(amount, checkout().total());
    }

    @When("I finish the order")
    public void iFinishTheOrder() {
        checkout().finish();
    }

    @Then("I see the confirmation {string}")
    public void iSeeTheConfirmation(String message) {
        assertEquals(message, checkout().confirmation());
    }

    @Then("the cart is empty")
    public void theCartIsEmpty() {
        assertEquals("", new InventoryPage(context.driver()).cartBadge());
    }
}
