package com.example.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage  extends BasePage {

    @FindBy(name = "back-to-products")
    WebElement backToProducts;

    @FindBy(name = "add-to-cart")
    WebElement addToCartButton;

    @FindBy(name = "inventory_details_desc")
    WebElement productDesc;

    @FindBy(name = "inventory_details_name")
    WebElement productName;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Attendre que l'URL change
            wait.until(ExpectedConditions.urlContains("inventory-item.html"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void AddToCart() {
        addToCartButton.click();
    }
    public void BackToProducts() {
        backToProducts.click();
    }
}
