package com.example.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(className = "title")
    WebElement pageTitle;

    @FindBy(css = ".cart_item")
    private List<WebElement> cart_items;

    private final WebElement cart_item = !cart_items.isEmpty() ? cart_items.get(0) : null;

    @FindBy(name = "checkout")
    private WebElement checkout;

    @FindBy(name="continue-shopping")
    private WebElement continueShopping;

    @FindBy(name = "remove-sauce-labs-backpack")
    private WebElement RemoveBackpackButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Attendre que l'URL change
            wait.until(ExpectedConditions.urlContains("cart.html"));
            // Attendre que le titre soit visible
            wait.until(ExpectedConditions.visibilityOf(pageTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean ProductExists() {
        try {
            // On réduit le temps d'attente à 5 secondes (par exemple) juste pour cette vérification
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.visibilityOf(cart_item));
            return true;
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException | NullPointerException e) {
            return false;
        }
    }
    public int TotalItemsInCart() {
        if (ProductExists()){
            return cart_items.size();
        }
        return 0;
    }
    public boolean isOnCartPage() {
        return driver.getCurrentUrl().contains("cart.html");
    }

    public void clickOnCheckout() {
        checkout.click();
    }
    public void clickOnContinueShopping() {
        continueShopping.click();
    }
    public void clickOnRemoveBackpack() {
        RemoveBackpackButton.click();
    }


}
