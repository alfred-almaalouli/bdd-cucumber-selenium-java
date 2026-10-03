package com.alfred.bdd.pages;

import com.alfred.bdd.support.TestContext;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(TestContext.BASE_URL);
        return this;
    }

    public void login(String username, String password) {
        type(dataTest("username"), username);
        type(dataTest("password"), password);
        click(dataTest("login-button"));
    }

    public boolean isDisplayed() {
        return visible(dataTest("login-button")).isDisplayed();
    }
}
