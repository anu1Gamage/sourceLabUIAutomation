package testCases;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.*;
import utilities.DataProviders;

public class ProductOrderingTest extends LoginTest {
    protected CartBadge cartBadge;
    protected CartPage cartPage;
    protected CheckoutStepOnePage checkoutStepOnePage;
    protected CheckoutStepTwoPage checkoutStepTwoPage;

    protected CheckoutCompletePage checkoutCompletePage;

    @Test(priority = 14,dataProvider = "userCheckoutInfo",dataProviderClass = DataProviders.class)
    public void verifyUserOrderOneItem(String firstName, String lastName, String postalCode) {
        logger.info("**************** TC_2.15 Test Case Started *****************");
        logger.info("Test Case: Validate that user order item successfully by checkout.");
        int cartBadgeValueBefore;
        int cartBadgeValueAfter;
        try {
            logger.info("Check whether the products are available in Inventory page");
            verifyProductsAvailableInInventory();
            cartBadgeValueBefore = checkAndHandleExistingCartItems();

            addItemToCart();
            cartBadgeValueAfter = verifyItemAddedToCart(cartBadgeValueBefore);

            navigateToCart();
            proceedToCheckout();

            enterUserInformation(firstName, lastName, postalCode);
            verifyCheckoutOverview();

            verifyUserBackToInventoryPage();
            logger.info("**************** TC_2.15 Test Case Passed *****************");
        } catch (Exception e) {
            logger.error("Exception encountered during TC_2.15: ", e);
            logger.info("**************** TC_2.15 Test Case Failed *****************");
            throw e; // Rethrow to ensure test fails in case of exceptions
        }
    }












    // Helper methods for each part of the test case

    private void verifyProductsAvailableInInventory() {
        Assert.assertFalse(inventoryPage.isItemListEmpty(), "Products are not available on the Inventory page.");
        logger.info("Products are available on the Inventory page.");
    }

    private int checkAndHandleExistingCartItems() {
        int cartBadgeValue = 0;
        if (cartBadge.isShoppingCartBadgeDisplay()) {
            logger.info("Items are already present in the cart.");
            cartBadgeValue = cartBadge.getNoOfItemsDisplayedOnShoppingCartBadge();
        } else {
            logger.info("No items in the cart.");
        }
        return cartBadgeValue;
    }

    private void addItemToCart() {
        inventoryPage.clickOnProduct1AddToCartBtn();
        logger.info("Clicked on Product 1 'Add to Cart' button.");
        Assert.assertTrue(inventoryPage.isSauceLabsBackPackRemoveBtnDisplay(), "Add to Cart button did not change to Remove button.");
        logger.info("'Add to Cart' button successfully changed to 'Remove' button for Product 1.");
    }

    public int verifyItemAddedToCart(int cartBadgeValueBefore) {
        Assert.assertTrue(cartBadge.isShoppingCartBadgeDisplay(), "Shopping cart badge is not displayed after adding an item.");
        int cartBadgeValueAfter = cartBadge.getNoOfItemsDisplayedOnShoppingCartBadge();
        Assert.assertEquals(cartBadgeValueAfter, cartBadgeValueBefore + 1, "Cart badge value did not increase as expected after adding an item.");
        logger.info("Product successfully added to cart. Total items in cart: " + cartBadgeValueAfter);
        return cartBadgeValueAfter;
    }

    private void navigateToCart() {
        cartBadge.clickOnAddToCartBtn();
        Assert.assertEquals(cartPage.getCartPageCurrentUrl(), "https://www.saucedemo.com/cart.html", "Not redirected to cart.html page.");
        Assert.assertEquals(cartPage.getCartPageTitle(), "Your Cart", "Incorrect title displayed on the cart page.");
    }

    private void proceedToCheckout() {
        cartPage.clickOnCheckOutBtn();
        Assert.assertEquals(checkoutStepOnePage.getCurrentPageUrl(), "https://www.saucedemo.com/checkout-step-one.html", "Not redirected to checkout-step-one.html page.");
        Assert.assertEquals(checkoutStepOnePage.getCheckOutStepOnePageTitle(), "Checkout: Your Information", "Incorrect page title displayed for checkout step one.");
    }

    private void enterUserInformation(String firstName, String lastName, String postalCode) {
        if (firstName != null) checkoutStepOnePage.setFirstNameForCheckout(firstName);
        if (lastName != null) checkoutStepOnePage.setLastNameForCheckout(lastName);
        if (postalCode != null) checkoutStepOnePage.setPostalCodeForCheckOut(postalCode);
        checkoutStepOnePage.clickOnCheckOutStepOnePageContinueBtn();
        logger.info("User clicked on checkout step one Continue button.");
    }

    private void verifyCheckoutOverview() {
        Assert.assertEquals(checkoutStepTwoPage.getCurrentPageUrl(), "https://www.saucedemo.com/checkout-step-two.html", "Not redirected to checkout-step-two.html page.");
        Assert.assertEquals(checkoutStepTwoPage.getCheckoutStepTwoPageTitle(), "Checkout: Overview", "Incorrect title displayed on checkout overview page.");

        double expectedItemsTotalPrices = checkoutStepTwoPage.calculateTotalPriceInCartItems();
        double actualItemsTotalPrice = checkoutStepTwoPage.getItemsTotalPrice();
        Assert.assertEquals(actualItemsTotalPrice, expectedItemsTotalPrices, "Mismatch between actual and expected items total price on checkout overview page.");

        double expectedTotalTax = checkoutStepTwoPage.calculateTotalTax();
        double actualTotalTaxApplied = checkoutStepTwoPage.getTotalTaxApplied();
        Assert.assertEquals(actualTotalTaxApplied, expectedTotalTax, "Mismatch between actual and expected total tax applied on checkout overview page.");

        double actualTotalPrice = checkoutStepTwoPage.getTotalPrice();
        double expectedTotalPrice = actualItemsTotalPrice + actualTotalTaxApplied;
        Assert.assertEquals(actualTotalPrice, expectedTotalPrice, "Total price displayed on checkout overview page does not equal the sum of items total and tax.");
        logger.info("Total price verification on checkout overview page passed.");
    }

    private void verifyOrderCompletion(){
        checkoutStepTwoPage.clickOnCheckOutStepTwoPageFinishBtn();
        logger.info("Clicked on Finish button on checkout step two page");
        Assert.assertEquals(checkoutCompletePage.getCheckOutCompletePageHeader(),"Thank you for your order!","checkout complete page actual header message is differ from expected message");
        logger.info("Order complete message is displayed.");
        Assert.assertEquals(checkoutCompletePage.isCheckOutCompleteIconDisplay(),true,"Order finished icon not displayed");
        logger.info("Order completed icon displayed");
        Assert.assertEquals(checkoutCompletePage.getCheckoutCompletionParagraph(),"Your order has been dispatched, and will arrive just as fast as the pony can get there!","order completion message paragrash is mismatched");
        logger.info("Order completion message paragraph displayed.");
        logger.info("Oder successfully completed");
    }

    private void verifyUserBackToInventoryPage(){
        checkoutCompletePage.clickOnCheckOutCompletePageBackToProductBtn();
        logger.info("User clicked on back to product button in checkout page");
        Assert.assertEquals(inventoryPage.getCurrentPageUrl(),"https://www.saucedemo.com/inventory.html","Not directs to Inventory page after clicked on back to Home button in Checkout complete page");
        logger.info("User successfully back to Inventory page by click on Back to Home button.");
    }

}