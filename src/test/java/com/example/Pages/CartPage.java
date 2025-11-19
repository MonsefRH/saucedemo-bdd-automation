package com.example.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    @FindBy(className = "cart_item")
    private WebElement cart_item;

    @FindBy(name = "checkout")
    private WebElement checkout;

    @FindBy(name = "remove-sauce-labs-backpack")
    private WebElement RemoveBackpackButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }
    public boolean ProductExists() {
        return cart_item.isDisplayed();
    }
    public boolean isOnCartPage() {
        return driver.getCurrentUrl().contains("cart.html");
    }

    public void clickOnCheckout() {
        checkout.click();
    }
    public void clickOnRemoveBackpack() {
        RemoveBackpackButton.click();
    }

}
