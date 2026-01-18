package com.example.stepdefs;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.example.Pages.InventoryPage;
import com.example.Pages.LoginPage;
import com.example.Utils.DriverFactory;

public class LoginSteps {

    private final WebDriver driver ;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    public LoginSteps() {
        driver = DriverFactory.getDriver();
    }

    private LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    private InventoryPage getInventoryPage() {
        if (inventoryPage == null) {
            inventoryPage = new InventoryPage(driver);
        }
        return inventoryPage;
    }

    @Given("I am on the SauceDemo login page")
    public void iAmOnLoginPage() {
        driver.get("https://www.saucedemo.com");
        getLoginPage();
    }

    @Given("I am logged in  as {string}")
    public void iAmLoggedIn(String username) {
        iAmOnLoginPage();
        LoginPage login = getLoginPage();
        login.login(username,"secret_sauce");
    }

    @When("I enter username {string} and password {string}")
    public void iEnterUsernamAndPassword(String username,String password) {
        LoginPage login = getLoginPage();
        login.enterUsername(username);
        login.enterPassword(password);
    }


    @When("I click on the login button")
    public void iClickLogin() {
        LoginPage login = getLoginPage();
        login.clickLogin();
    }

    @Then("I should be redirected to the inventory page")
    public void iShouldBeOnInventory() {
        Assert.assertTrue(getInventoryPage().isLoaded(),
                "User should be on inventory page");
    }

    @Then("I should see an error message {string}")
    public void iShouldSeeError(String expectedError) {
        String actualError = getLoginPage().getErrorMessage();
        Assert.assertTrue(actualError.contains(expectedError),
                "Error message mismatch");
    }
// Logout
    @When("I click on Logout")
    public void iClickOnLogout() {
        InventoryPage inventory = getInventoryPage();
        inventory.Logout();
    }

    @Then("I should be redirected to the login page")
    public void iShouldBeRedirectedToTheLoginPage() {
        LoginPage login = getLoginPage();
        Assert.assertTrue(login.isLoaded());
    }
}
