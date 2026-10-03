package com.alfred.bdd.steps;

import com.alfred.bdd.pages.CartPage;
import com.alfred.bdd.pages.InventoryPage;
import com.alfred.bdd.support.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingSteps {

    private final TestContext context;

    public ShoppingSteps(TestContext context) {
        this.context = context;
    }

    private InventoryPage inventory() {
        return new InventoryPage(context.driver());
    }

    @When("I add {string} to the cart")
    public void iAddToTheCart(String product) {
        inventory().addToCart(product);
    }

    @Given("I add the following products to the cart:")
    public void iAddTheFollowingProducts(List<String> products) {
        products.forEach(inventory()::addToCart);
    }

    @When("I remove {string} from the cart")
    public void iRemoveFromTheCart(String product) {
        inventory().removeFromCart(product);
    }

    @Then("the cart badge shows {string}")
    public void theCartBadgeShows(String count) {
        assertEquals(count, inventory().cartBadge());
    }

    @Then("the cart contains only {string}")
    public void theCartContainsOnly(String product) {
        inventory().openCart();
        assertEquals(List.of(product), new CartPage(context.driver()).productNames());
    }

    @When("I sort the products by {string}")
    public void iSortTheProductsBy(String option) {
        inventory().sortBy(option);
    }

    @Then("the first product is {string}")
    public void theFirstProductIs(String product) {
        assertEquals(product, inventory().productNames().get(0));
    }
}
