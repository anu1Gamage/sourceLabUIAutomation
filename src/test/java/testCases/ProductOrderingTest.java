package testCases;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.CartBadge;
import pageObject.CartPage;
import pageObject.CheckoutStepOnePage;
import pageObject.CheckoutStepTwoPage;

public class ProductOrderingTest extends LoginTest {
    protected CartBadge cartBadge;
    protected CartPage cartPage;
    protected CheckoutStepOnePage checkoutStepOnePage;
    protected CheckoutStepTwoPage checkoutStepTwoPage;

    @Test(priority = 14)
    public void verifyUserOrderOneItem(String firstName, String lastName, String postalCode) {
        logger.info("**************** TC_2.15 Test Case Started *****************");
        logger.info("Test Case: Validate that user order item successfully by checkout.");
        int cartBadgeValueBefore;
        int cartBadgeValueAfter;
        try {
            //check products are available in Inventory page
            Assert.assertEquals(inventoryPage.isItemListEmpty(), false, "Products are not available in Inventory page");
            logger.info("Products are available in Inventory page");
            //Check there is previously added items are available in cart
            try {
                //Check there is added items in cart by checking cartBadge is display on page or not  ---> cartBadge display = There are items in cart
                if (cartBadge.isShoppingCartBadgeDisplay()) {
                    logger.info("There are items in cart");
                }
            } catch (NoSuchElementException e) {
                logger.info("There is no products available in Cart");
                cartBadgeValueBefore = 0;
                //Add item to cart
                inventoryPage.clickOnProduct1AddToCartBtn();
                logger.info("clicked on Product one add to cart button");
                Assert.assertTrue(inventoryPage.isSauceLabsBackPackRemoveBtnDisplay(), "Add To Cart button did not change to Remove button.");
                logger.info("AddToCart button successfully changed to Remove button for Product1.");
                // Verify cart badge updates correctly after adding the item
                Assert.assertTrue(cartBadge.isShoppingCartBadgeDisplay(), "Shopping cart badge is not displayed after adding an item.");
                cartBadgeValueAfter = cartBadge.getNoOfItemsDisplayedOnShoppingCartBadge();
                Assert.assertTrue(((cartBadgeValueAfter > cartBadgeValueBefore) && (cartBadgeValueAfter == cartBadgeValueBefore + 1)), "Cart badge value did not increase after adding an item.");
                logger.info("Product successfully added to cart. Total items in cart: " + cartBadgeValueAfter);
                //Click on Cart Icon in Inventory.html page
                cartBadge.clickOnAddToCartBtn();
                Assert.assertEquals(cartPage.getCartPageCurrentUrl(), "https://www.saucedemo.com/cart.html", "Not directs to cart.html page");
                Assert.assertEquals(cartPage.getCartPageTitle(), "Your Cart", "Not display Your Cart Title. Displayed title is:" + cartPage.getCartPageTitle());
                //Click on checkout button
                cartPage.clickOnCheckOutBtn();
                Assert.assertEquals(checkoutStepOnePage.getCurrentPageUrl(),"https://www.saucedemo.com/checkout-step-one.html","User not directs to checkout-step-one.html page after click on checkout button");
                Assert.assertEquals(checkoutStepOnePage.getCheckOutStepOnePageTitle(),"Checkout: Your Information","Expected page title is not dispalyed. expected Title is Checkout: Your Information.Actual displayed Title is:" + checkoutStepOnePage.getCheckOutStepOnePageTitle());
                //Insert Personal information to continue the order
                if(firstName != null){
                    checkoutStepOnePage.setFirstNameForCheckout(firstName);
                }
                if(lastName != null){
                    checkoutStepOnePage.setLastNameForCheckout(lastName);
                }
                if(postalCode != null){
                    checkoutStepOnePage.setPostalCodeForCheckOut(postalCode);
                }
                //click on continue button
                checkoutStepOnePage.clickOnCheckOutStepOnePageContinueBtn();
                logger.info("User clicked on checkoutOnePage Continue button");
                Assert.assertEquals(checkoutStepTwoPage.getCurrentPageUrl(),"https://www.saucedemo.com/checkout-step-two.html","User not directs to https://www.saucedemo.com/checkout-step-two.html page after click on continue button");
                Assert.assertEquals(checkoutStepTwoPage.getCheckoutStepTwoPageTitle(),"Checkout: Overview","checkoutStepTwoPage expected page title not displayed. Expected page title is Checkout: Overview. Actual displayed title is: " + checkoutStepTwoPage.getCheckoutStepTwoPageTitle());
                //TODO

            } catch (Exception e) {
                logger.info("");
                logger.info("TC_2.15 Test case failed");
                logger.error("Exception encountered: ", e);
            }


        }finally{}

    }
}