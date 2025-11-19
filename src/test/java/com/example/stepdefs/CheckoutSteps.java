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
    private LoginPage loginPage = new LoginPage(driver);
    private InventoryPage inventoryPage = new InventoryPage(driver);
    private CartPage cartPage = new CartPage(driver);
    private CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(driver);
    private CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
    private CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);

    @When("I login as {string} with password {string}")
    public void i_login_as_with_password(String string, String string2) {
        loginPage.login(string, string2);
    }
    @When("I add the {string} to cart")
    public void i_add_the_to_cart(String ProductName) {
        if (!inventoryPage.isOnInventoryPage()){
            throw new Error("Not on inventory page");
        }
        inventoryPage.AddToCart(ProductName);
    }
    @When("I go the cart")
    public void i_go_the_cart() {
        inventoryPage.GoToCartPage();
    }
    @Then("The cart should contain {int} item")
    public void the_cart_should_contain_item(Integer int1) {
        Assert.assertTrue(cartPage.ProductExists(), "The cart should contain " + int1);
    }
    @When("I proceed to checkout")
    public void i_proceed_to_checkout() {
        cartPage.clickOnCheckout();
    }
    @When("I fill the checkout form with:")
    public void i_fill_the_checkout_form_with(DataTable table) {
        if (!checkoutStepOnePage.isOnCheckoutfirstPage()){
            throw new Error("Not on step 1 of the checkout  page");
        }
        Map<String, String> row = table.asMaps().get(0);
        String first = row.get("firstName");
        String last = row.get("lastName");
        String postal = row.get("postalCode");

        checkoutStepOnePage.SetfirstName(first != null ? first : "");
        checkoutStepOnePage.SetlastName(last != null ? last : "");
        checkoutStepOnePage.SetpostalCode(postal != null ? postal : "");



    }
    @When("I continue to overview")
    public void i_continue_to_overview() {
        checkoutStepOnePage.pressContinue();
    }
    @When("I finish the checkout")
    public void i_finish_the_checkout() {
        if (!checkoutStepTwoPage.isOnCheckoutTwoPage()){
            throw new Error("Not on step 2 of the checkout  page");
        }
        checkoutStepTwoPage.clickOnFinishButton();
    }
    @Then("I should see the confirmation page {string}")
    public void i_should_see_the_confirmation_page(String msg) {
        Assert.assertEquals(checkoutCompletePage.getResultsHeaderText(),msg , "There is a difference between the existed and the expected messages  ");
    }


}
