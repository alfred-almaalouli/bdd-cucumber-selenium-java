package com.alfred.bdd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class InventoryPage extends BasePage {

    private final By title = dataTest("title");
    private final By productNames = dataTest("inventory-item-name");
    private final By sortSelect = dataTest("product-sort-container");
    private final By cartBadge = dataTest("shopping-cart-badge");
    private final By cartLink = dataTest("shopping-cart-link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    private static String slug(String productName) {
        return productName.toLowerCase().replace(" ", "-");
    }

    public String title() {
        return visible(title).getText();
    }

    public void addToCart(String product) {
        click(dataTest("add-to-cart-" + slug(product)));
    }

    public void removeFromCart(String product) {
        click(dataTest("remove-" + slug(product)));
    }

    public void sortBy(String visibleText) {
        new Select(visible(sortSelect)).selectByVisibleText(visibleText);
    }

    public List<String> productNames() {
        return texts(productNames);
    }

    public String cartBadge() {
        List<org.openqa.selenium.WebElement> badge = driver.findElements(cartBadge);
        return badge.isEmpty() ? "" : badge.get(0).getText();
    }

    public void openCart() {
        click(cartLink);
    }
}
