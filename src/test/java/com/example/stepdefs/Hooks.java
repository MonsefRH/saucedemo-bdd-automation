package com.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import com.example.Utils.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @Before
    public void setUp() {
        System.out.println("Setting up WebDriver...");
        DriverFactory.getDriver();  // initializes ThreadLocal driver
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver();

        // Take screenshot ONLY if scenario failed
        if (scenario.isFailed() && driver != null) {
            try {
                final byte[] screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);

                // Attach to cucumber report (HTML + JSON + Extent)
                // Last parameter = title / description shown in report
                scenario.attach(screenshot, "image/png",
                        "Failure Screenshot - " + scenario.getName());

                System.out.println("Screenshot attached for failed scenario: " + scenario.getName());

            } catch (Exception e) {
                System.err.println("Failed to capture/attach screenshot: " + e.getMessage());
            }
        }

        // Always quit driver at the end
        System.out.println("Closing WebDriver...");
        DriverFactory.quitDriver();
    }
}