package com.example.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(className = "inventory_item")
    List<WebElement> inventory_list;

    @FindBy(className = "shopping_cart_badge")
    WebElement cartBadge;

    @FindBy(name = "add-to-cart-sauce-labs-backpack")
    WebElement AddToCartSauceLabsBackpack;

    @FindBy(className = "shopping_cart_link")
    WebElement shoppingCartLink;


    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void AddToCart(String item_name) {
        for(WebElement item : inventory_list) {
            String TitleItem = item.findElement(By.className("inventory_item_name")).getText();
            if (TitleItem.contains(item_name)) {
                WebElement ItemButton = item.findElement(By.name("add-to-cart-sauce-labs-backpack"));
                ItemButton.click();
            }
        }

    }

    public void RemoveFromCart(String item_name) {
        for(WebElement item : inventory_list) {
            String TitleItem = item.findElement(By.className("inventory_item_name")).getText();
            if (TitleItem.contains(item_name)) {
                WebElement ItemButton = item.findElement(By.name("remove-sauce-labs-backpack"));
                ItemButton.click();
            }
        }
    }

    public boolean IsAddedToCart(String item_name) {
        boolean ItemExists = false;
        for (WebElement item : inventory_list) {
            String TitleItem = item.findElement(By.className("inventory_item_name")).toString();
            if (TitleItem.contains(item_name)) {
                WebElement ItemButton = item.findElement(By.name("remove-sauce-labs-backpack"));
                ItemExists = ItemButton.getText().contains("Add to cart");
            }
        }
        return ItemExists;
    }
    public int getCartItemCount() {
        return cartBadge.isDisplayed() ? Integer.parseInt(cartBadge.getText()) : 0;
    }
    public boolean isOnInventoryPage() {
        return driver.getCurrentUrl().contains("inventory.html");
    }
    public void addBackpackToCart(){
        AddToCartSauceLabsBackpack.click();
    }
    public void GoToCartPage(){
        shoppingCartLink.click();
    }

}
