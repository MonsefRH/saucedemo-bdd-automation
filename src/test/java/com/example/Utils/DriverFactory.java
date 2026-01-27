package com.example.Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();

            // Headless mode
            options.addArguments("--headless=new");

// -----------------------------------------------------------
            // 1. SOLUTION POUR LA POPUP MOT DE PASSE
            // -----------------------------------------------------------
            HashMap<Object, Object> prefs = new HashMap<>();
            // Désactiver la suggestion de sauvegarde des mots de passe
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            // Désactiver l'alerte de fuite de mot de passe (Leak Detection)
            prefs.put("profile.password_manager_leak_detection", false);
            prefs.put("safebrowsing.enabled", true); // Garder la sécu de base mais sans le password manager

            options.setExperimentalOption("prefs", prefs);

            // Désactiver la feature spécifique côté arguments aussi
            options.addArguments("--disable-features=PasswordLeakDetection");

            // Essential security flags
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            // Stability improvements
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-software-rasterizer");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-background-networking");
            options.addArguments("--disable-background-timer-throttling");
            options.addArguments("--disable-backgrounding-occluded-windows");
            options.addArguments("--disable-renderer-backgrounding");
            options.addArguments("--disable-breakpad");
            options.addArguments("--disable-client-side-phishing-detection");
            options.addArguments("--disable-hang-monitor");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-prompt-on-repost");
            options.addArguments("--disable-sync");
            options.addArguments("--disable-translate");
            options.addArguments("--metrics-recording-only");
            options.addArguments("--no-first-run");
            options.addArguments("--safebrowsing-disable-auto-update");
            options.addArguments("--enable-automation");
            options.addArguments("--password-store=basic");
            options.addArguments("--use-mock-keychain");

            // Window size
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--start-maximized");

            // Logging
            options.addArguments("--enable-logging");
            options.addArguments("--v=1");

            // User agent
            options.addArguments("--user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
            WebDriver webDriver = new ChromeDriver(options);

            // Set timeouts
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            webDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
            driver.set(webDriver);
        }
        return driver.get();
    }

    public static void quitDriver() {
        WebDriver d = driver.get();
        if (d != null) {
            try {
                d.quit();
            } catch (Exception e) {
                System.err.println("Error quitting driver: " + e.getMessage());
            } finally {
                driver.remove();
            }
        }
    }
}