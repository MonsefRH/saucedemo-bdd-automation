package com.example.stepdefs;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.example.Pages.InventoryPage;
import com.example.Pages.LoginPage;
import com.example.Utils.DriverFactory;

public class LoginSteps {

    private final WebDriver driver = DriverFactory.getDriver();
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @Given("I am on the SauceDemo login page")
    public void iAmOnLoginPage() {
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
    }

    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("I enter password {string}")
    public void iEnterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("I click the login button")
    public void iClickLogin() {
        inventoryPage = loginPage.clickLogin();
    }

    @Then("I should be redirected to the inventory page")
    public void iShouldBeOnInventory() {
        Assert.assertTrue(inventoryPage.isOnInventoryPage(),
                "User should be on inventory page");
    }

    @Then("I should see an error message {string}")
    public void iShouldSeeError(String expectedError) {
        String actualError = loginPage.getErrorMessage();
        Assert.assertTrue(actualError.contains(expectedError),
                "Error message mismatch");
    }
}
