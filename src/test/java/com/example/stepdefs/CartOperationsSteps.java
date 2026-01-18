package com.example.stepdefs;

import com.example.Pages.CartPage;
import com.example.Pages.InventoryPage;
import com.example.Pages.LoginPage;
import com.example.Pages.ProductPage;
import com.example.Utils.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartOperationsSteps {
    private final WebDriver driver;
    private LoginPage loginPage ;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private ProductPage productPage;


    public CartOperationsSteps() {
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
    private CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage(driver);
        }
        return cartPage;
    }

      private ProductPage getProductPage() {
        if (productPage == null) {
            productPage = new ProductPage(driver);
        }
        return productPage;
    }

    @When("I add the {string} to cart")
    public void i_add_the_to_cart(String productName) {
        InventoryPage inventory = getInventoryPage();
        if (!inventory.isLoaded()) {
            throw new RuntimeException("Not on inventory page");
        }
        inventory.AddToCart(productName);
    }


    @When("I go the cart")
    public void i_go_the_cart() {
        InventoryPage inventory = getInventoryPage();
        inventory.GoToCartPage();
    }

    // Single step definition that handles both "item" and "items"
    // Use @When for the "When" keyword in feature files
    @When("The cart should contain single item")
    public void when_the_cart_should_contain_item() {
        validateCartItemCount(1);
    }

    // Use @Then for the "Then" keyword in feature files
    @Then("The cart should contain {int} item")
    public void then_the_cart_should_contain_item(int expected) {
        validateCartItemCount(expected);
    }

    // Common validation logic
    private void validateCartItemCount(int expected) {
        CartPage cart = getCartPage();
        Assert.assertTrue(cart.isLoaded());
        Assert.assertEquals(cart.TotalItemsInCart(), expected, "Cart should have " + expected + " item(s)");
    }

    @When("I add all the items to the cart")
    public void i_add_all_the_to_cart() {
        InventoryPage inventory = getInventoryPage();
        inventory.AddAllItemsToCart();
//        inventory.getCartItemCount();
//        Assert.assertEquals(inventory.getCartItemCount(),6, "Cart should have 6 items ");
//        CartPage cart = getCartPage();
//        Assert.assertEquals(cart.TotalItemsInCart(),6, "Cart should have 6 items ");
    }

    @When("I click on {string} product card")
    public void iClickOnProductCard(String productName) {
        InventoryPage inventory = getInventoryPage();
        Assert.assertTrue(inventory.isLoaded(),"Not on inventory page");
        inventory.GoToProductPage(productName);
    }

    @And("I add the product to cart")
    public void iAddTheProductToCart() {
        ProductPage product = getProductPage();
        Assert.assertTrue(product.isLoaded(),"I should be in the product page");
        product.AddToCart();
    }

    @And("I go back the inventory")
    public void iGoBackTheInventory() {
        CartPage cart = getCartPage();
        cart.clickOnContinueShopping();
    }

    @And("I remove {string} from cart")
    public void iRemoveFromCart(String productName) {
        InventoryPage inventory = getInventoryPage();
        Assert.assertTrue(inventory.isLoaded());
        inventory.RemoveFromCart(productName);
    }

    @Then("the cart should be empty")
    public void theCartShouldBeEmpty() {
        CartPage cart = getCartPage();
        Assert.assertTrue(cart.isLoaded());
        Assert.assertFalse(cart.ProductExists());
    }
}
