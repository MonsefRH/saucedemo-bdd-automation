package com.example.stepdefs;

import com.example.Pages.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import com.example.Utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Map;

public class CheckoutSteps {

    private final WebDriver driver = DriverFactory.getDriver();


    @When("I login as {string} with password {string}")
    public void i_login_as_with_password(String username, String password) {
        new LoginPage(driver).login(username, password);
    }

    @When("I add the {string} to cart")
    public void i_add_the_to_cart(String productName) {
        InventoryPage inventory = new InventoryPage(driver);
        if (!inventory.isOnInventoryPage()) {
            throw new RuntimeException("Not on inventory page");
        }
        inventory.AddToCart(productName);
    }

    @When("I go the cart")
    public void i_go_the_cart() {
        new InventoryPage(driver).GoToCartPage();
    }

    @Then("The cart should contain {int} item")
    public void the_cart_should_contain_item(Integer expected) {
        CartPage cart = new CartPage(driver);
        Assert.assertTrue(cart.ProductExists(), "Cart should have " + expected + " item(s)");
    }

    @When("I proceed to checkout")
    public void i_proceed_to_checkout() {
        new CartPage(driver).clickOnCheckout();
    }

    @When("I fill the checkout form with:")
    public void i_fill_the_checkout_form_with(DataTable table) {
        CheckoutStepOnePage step1 = new CheckoutStepOnePage(driver);
        if (!step1.isOnCheckoutfirstPage()) {
            throw new RuntimeException("Not on checkout step 1");
        }

        Map<String, String> data = table.asMaps().get(0);
        step1.SetfirstName(getValueOrEmpty(data, "firstName"));
        step1.SetlastName(getValueOrEmpty(data, "lastName"));
        step1.SetpostalCode(getValueOrEmpty(data, "postalCode"));
    }

    @When("I continue to overview")
    public void i_continue_to_overview() {
        new CheckoutStepOnePage(driver).pressContinue();
    }

    @When("I finish the checkout")
    public void i_finish_the_checkout() {
        CheckoutStepTwoPage step2 = new CheckoutStepTwoPage(driver);
        if (!step2.isOnCheckoutTwoPage()) {
            throw new RuntimeException("Not on step 2 of the checkout page. Current URL: " + driver.getCurrentUrl());
        }
        step2.clickOnFinishButton();
    }

    @Then("I should see the confirmation page {string}")
    public void i_should_see_the_confirmation_page(String expectedMessage) {
        CheckoutCompletePage complete = new CheckoutCompletePage(driver);
        String actual = complete.getResultsHeaderText();
        Assert.assertEquals(actual, expectedMessage,
                "Confirmation message mismatch. Expected: " + expectedMessage + " | Actual: " + actual);
    }

    @Then("I should see an error message with {string}")
    public void i_should_see_an_error_message_with(String expectedError) {
        CheckoutStepOnePage step1 = new CheckoutStepOnePage(driver);
        String actual = step1.GetErrorMessage();
        Assert.assertTrue(actual.contains(expectedError),
                "Expected error: '" + expectedError + "' but got: '" + actual + "'");
    }

    // Méthode utilitaire pour éviter les NullPointer
    private String getValueOrEmpty(Map<String, String> map, String key) {
        String value = map.get(key);
        return value == null ? "" : value;
    }
}