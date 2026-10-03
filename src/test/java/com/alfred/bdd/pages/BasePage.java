package com.alfred.bdd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected static By dataTest(String value) {
        return By.cssSelector("[data-test='" + value + "']");
    }

    protected WebElement visible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        WebElement field = visible(locator);
        field.clear();
        field.sendKeys(text);
    }

    protected List<String> texts(By locator) {
        visible(locator);
        return driver.findElements(locator).stream().map(WebElement::getText).toList();
    }

    public String errorMessage() {
        return visible(dataTest("error")).getText();
    }

    public String currentPath() {
        return driver.getCurrentUrl().replaceFirst("^https?://[^/]+", "");
    }
}
