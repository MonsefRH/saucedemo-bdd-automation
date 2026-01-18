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
    private LoginPage loginPage;
    private CartPage cartPage;
    private CheckoutStepOnePage checkoutStepOnePage;
    private CheckoutStepTwoPage checkoutStepTwoPage;
    private CheckoutCompletePage checkoutCompletePage;

    public LoginPage getLoginPage() {
        if(loginPage == null){
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }
    public CartPage getCartPage() {
        if(cartPage == null){
            cartPage = new CartPage(driver);
        }
        return cartPage;
    }
    public CheckoutStepOnePage getCheckoutStepOnePage() {
        if(checkoutStepOnePage == null){
            checkoutStepOnePage = new CheckoutStepOnePage(driver);
        }
        return checkoutStepOnePage;
    }
    public CheckoutStepTwoPage getCheckoutStepTwoPage() {
        if(checkoutStepTwoPage == null){
            checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        }
        return checkoutStepTwoPage;
    }
    public CheckoutCompletePage getCheckoutCompletePage() {
        if(checkoutCompletePage == null){
            checkoutCompletePage = new CheckoutCompletePage(driver);
        }
        return checkoutCompletePage;
    }


    @When("I login as {string} with password {string}")
    public void i_login_as_with_password(String username, String password) {
        LoginPage login = getLoginPage();
        login.login(username, password);
    }



    @When("I proceed to checkout")
    public void i_proceed_to_checkout() {
        CartPage cart = getCartPage();
        cart.clickOnCheckout();
    }

    @When("I fill the checkout form with:")
    public void i_fill_the_checkout_form_with(DataTable table) {
        CheckoutStepOnePage step1 = getCheckoutStepOnePage();
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
        CheckoutStepOnePage step1 = getCheckoutStepOnePage();
        step1.pressContinue();
    }

    @When("I finish the checkout")
    public void i_finish_the_checkout() {
        CheckoutStepTwoPage step2 = getCheckoutStepTwoPage();
        Assert.assertTrue(step2.isOnCheckoutTwoPage(),"Not on checkout step 2");
        step2.clickOnFinishButton();
    }

    @Then("I should see the confirmation page {string}")
    public void i_should_see_the_confirmation_page(String expectedMessage) {
        CheckoutCompletePage complete = getCheckoutCompletePage();
        Assert.assertTrue(complete.isOnCheckoutCompletedPage(),"Not on checkout complete page");
        String actual = complete.getResultsHeaderText();
        Assert.assertEquals(actual, expectedMessage,
                "Confirmation message mismatch. Expected: " + expectedMessage + " | Actual: " + actual);
    }

    @Then("I should see an error message with {string}")
    public void i_should_see_an_error_message_with(String expectedError) {
        CheckoutStepOnePage step1 = getCheckoutStepOnePage();
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