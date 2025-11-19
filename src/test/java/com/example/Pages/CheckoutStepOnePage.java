package com.example.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutStepOnePage extends BasePage{

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement postalCode;

    @FindBy(id = "continue")
    private WebElement continueButton;
    @FindBy(className = "error-message-container")
    private WebElement errorMessage;

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }
    public boolean isOnCheckoutfirstPage() {
        return driver.getCurrentUrl().contains("checkout-step-one.html");
    }
    public void SetfirstName(String firstName) {
        this.firstName.sendKeys(firstName);
    }
    public void SetlastName(String lastName) {
        this.lastName.sendKeys(lastName);
    }
    public void SetpostalCode(String postalCode) {
        this.postalCode.sendKeys(postalCode);
    }
    public void pressContinue() {
        continueButton.click();
    }
    public String GetErrorMessage() {
        return errorMessage.getText();
    }

}
