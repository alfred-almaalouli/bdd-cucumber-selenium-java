package com.alfred.bdd.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

/**
 * Shared state for one scenario. PicoContainer creates one instance per scenario
 * and injects it into every step class, so all steps use the same browser.
 */
public class TestContext {

    public static final String BASE_URL = System.getProperty("baseUrl", "https://www.saucedemo.com");

    private WebDriver driver;

    public WebDriver driver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            if (!Boolean.getBoolean("headed")) {
                options.addArguments("--headless=new");
            }
            options.addArguments("--window-size=1366,900", "--no-sandbox", "--disable-dev-shm-usage",
                    "--disable-features=PasswordLeakDetection,PasswordCheck");
            // The demo password is public, so Chrome's "change your password" warning
            // would pop up after login and block clicks. Turn the password manager off.
            options.setExperimentalOption("prefs", Map.of(
                    "credentials_enable_service", false,
                    "profile.password_manager_enabled", false,
                    "profile.password_manager_leak_detection", false));
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    public boolean hasDriver() {
        return driver != null;
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
