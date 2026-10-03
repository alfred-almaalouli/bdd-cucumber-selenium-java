package com.alfred.bdd.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
            options.addArguments("--window-size=1366,900", "--no-sandbox", "--disable-dev-shm-usage");
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
