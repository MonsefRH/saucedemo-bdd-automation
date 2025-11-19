package com.example.Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage {
    @FindBy( className = "complete-header")
    private WebElement ResultsHeader;

    @FindBy( className = "back-to-products")
    private WebElement BackToProducts;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }
    public boolean isOnCheckoutCompletedPage() {
        return driver.getCurrentUrl().contains("checkout-complete.html");
    }
    public String getResultsHeaderText() {
        return ResultsHeader.getText();
    }
    public void clickBackToProducts() {
        BackToProducts.click();
    }

}
