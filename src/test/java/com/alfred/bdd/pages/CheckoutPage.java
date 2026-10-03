package com.alfred.bdd.pages;

import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void enterCustomer(String firstName, String lastName, String postalCode) {
        type(dataTest("firstName"), firstName);
        type(dataTest("lastName"), lastName);
        type(dataTest("postalCode"), postalCode);
        click(dataTest("continue"));
    }

    /** "Item total: $47.97" -> "$47.97" */
    private String amount(String dataTestId) {
        String text = visible(dataTest(dataTestId)).getText();
        return text.substring(text.indexOf('$'));
    }

    public String itemTotal() {
        return amount("subtotal-label");
    }

    public String tax() {
        return amount("tax-label");
    }

    public String total() {
        return amount("total-label");
    }

    public void finish() {
        click(dataTest("finish"));
    }

    public String confirmation() {
        return visible(dataTest("complete-header")).getText();
    }
}
