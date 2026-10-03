package com.alfred.bdd.support;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    /** Attaches a screenshot to the Cucumber report when a scenario fails, then closes the browser. */
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed() && context.hasDriver()) {
            byte[] png = ((TakesScreenshot) context.driver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(png, "image/png", scenario.getName());
        }
        context.quit();
    }
}
