package com.example.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(className = "title")
    WebElement pageTitle;

    @FindBy(id = "react-burger-menu-btn")
    WebElement MenuBtn;

    @FindBy(id = "logout_sidebar_link")
    WebElement Logout;

    @FindBy(className = "inventory_item")
    List<WebElement> inventory_list;

    @FindBy(className = "inventory_item_name")
    WebElement itemName;

    @FindBy(className = "shopping_cart_badge")
    WebElement cartBadge;

    @FindBy(name = "add-to-cart-sauce-labs-backpack")
    WebElement AddToCartSauceLabsBackpack;

    @FindBy(className = "shopping_cart_link")
    WebElement shoppingCartLink;


    public InventoryPage(WebDriver driver) {
        super(driver);
    }
    public boolean isLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Attendre que l'URL change
            wait.until(ExpectedConditions.urlContains("inventory.html"));
            // Attendre que le titre soit visible
            wait.until(ExpectedConditions.visibilityOf(pageTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void GoToProductPage(String name) {
        for(WebElement item : inventory_list) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(item.findElement(By.cssSelector(".inventory_item_name"))));
            WebElement titleElement = item.findElement(By.cssSelector(".inventory_item_name"));
            String titleItem = titleElement.getText();
            if (titleItem.equalsIgnoreCase(name)) {
                titleElement.click();
                break;
            }
        }
    }

    public void AddToCart(String item_name) {
        for(WebElement item : inventory_list) {
            String titleItem = item.findElement(By.className("inventory_item_name")).getText();
            if (titleItem.equalsIgnoreCase(item_name)) {
                WebElement addToCartButton = item.findElement(By.className("btn_inventory"));
                addToCartButton.click();
            }
        }
    }
    public void AddAllItemsToCart() {
        for (WebElement item : inventory_list) {
            WebElement addToCartButton = item.findElement(By.className("btn_inventory"));
            addToCartButton.click();
        }
    }

    public void RemoveFromCart(String item_name) {
        for(WebElement item : inventory_list) {
            String TitleItem = item.findElement(By.className("inventory_item_name")).getText();
            if (TitleItem.contains(item_name)) {
                WebElement ItemButton = item.findElement(By.className("btn_inventory"));
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

    public void addBackpackToCart(){
        AddToCartSauceLabsBackpack.click();
    }
    public void GoToCartPage(){
        shoppingCartLink.click();
    }

    public void Logout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(MenuBtn));
        MenuBtn.click();
        Logout.click();
    }

}
