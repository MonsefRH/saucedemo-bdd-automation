package com.example.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutStepTwoPage extends BasePage{

    @FindBy(className = "cart_item")
    WebElement cart_item;

    @FindBy(name = "finish")
    WebElement finishButton;

    @FindBy(name = "cancel")
    WebElement cancelButton;

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }
    public boolean isOnCheckoutTwoPage() {
        return driver.getCurrentUrl().contains("checkout-step-two.html");
    }
    public boolean ProductExists() {
        return cart_item.isDisplayed();
    }
    public void clickOnFinishButton() {
        finishButton.click();
    }
    public void clickOnCancelButton() {
        cancelButton.click();
    }

}
