package com.alfred.bdd.pages;

import org.openqa.selenium.WebDriver;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> productNames() {
        return texts(dataTest("inventory-item-name"));
    }

    public void checkout() {
        click(dataTest("checkout"));
    }
}
