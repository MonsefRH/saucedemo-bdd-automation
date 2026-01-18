package com.example.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage extends BasePage {
    @FindBy(id="user-name")
    WebElement usernameInput;

    @FindBy(id="password")
    WebElement passwordInput;

    @FindBy(id="login-button")
    WebElement loginButton;

    @FindBy(css = ".error-message-container")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public boolean isLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Attendre que l'URL change
            wait.until(ExpectedConditions.urlContains("/"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void enterUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }
    public void clickLogin() {
        loginButton.click();
    }

    public void login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }
    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
