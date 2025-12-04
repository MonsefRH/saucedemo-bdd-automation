package com.example.Tests;

import com.example.Pages.*;
import com.example.Utils.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class CheckoutTest {
    protected WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutStepOnePage checkoutStepOnePage;
    private CheckoutStepTwoPage checkoutStepTwoPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        this.driver = DriverFactory.getDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testCheckout() {
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage = new InventoryPage(driver);
        inventoryPage.addBackpackToCart();
        if (inventoryPage.getCartItemCount() == 0 ){
            throw new RuntimeException("The product not added in the card");
        }
        inventoryPage.GoToCartPage();
        cartPage = new CartPage(driver);
        cartPage.clickOnCheckout();
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepOnePage.SetfirstName("Mohamed");
        checkoutStepOnePage.SetpostalCode("55");
        checkoutStepOnePage.pressContinue();
        if (checkoutStepOnePage.isOnCheckoutfirstPage()){
            System.out.println(checkoutStepOnePage.GetErrorMessage());
        }
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
