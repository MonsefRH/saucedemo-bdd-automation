package com.example.Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.example.Pages.InventoryPage;
import com.example.Utils.ExtentManager;
import com.example.Utils.TestListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.example.Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners({TestListener.class})
public class LoginTest {
    protected WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }
//    @Test
//    public void testSuccessLogin() {
//        test = extent.createTest("Valid Login Test");
//        try{
//            loginPage.login("standard_user", "secret_sauce");
//            assert driver.getCurrentUrl().equalsIgnoreCase("https://www.saucedemo.com/inventory.html") : "Test Login failed";
//            test.pass("Login successful");
//        }
//        catch(Exception e){
//            test.fail(e.getMessage());
//        }
//
//    }

    @Test
    public void validLoginAndAddToCart() throws InterruptedException {
        InventoryPage inventory = loginPage
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLogin();

        Assert.assertTrue(inventory.isOnInventoryPage(), "Should be on inventory page");

        String Item1 = "Sauce Labs Backpack";
        inventory.AddToCart(Item1);
        Thread.sleep(5000);
        Assert.assertEquals(inventory.getCartItemCount(), 1, "Cart should have 1 item");
        inventory.RemoveFromCart(Item1);
//        Assert.assertEquals(inventory.getCartItemCount(), 0, "Cart shouldn't have any item");
        Assert.assertFalse(inventory.IsAddedToCart(Item1));


    }

    @Test
    public void invalidLoginShowsError() {
        loginPage.enterUsername("locked_out_user")
                .enterPassword("wrong_pass")
                .clickLogin();

        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("Username and password do not match"));
    }



    @AfterMethod
    void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(2000);
            driver.quit();
        }
    }


}
