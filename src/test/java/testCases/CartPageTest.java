package testCases;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.CartBadge;
import pageObject.CartPage;
import pageObject.CheckoutStepOnePage;

public class CartPageTest extends LoginTest{
    protected CartBadge cartBadge;
    protected CartPage cartPage;
    protected CheckoutStepOnePage checkoutStepOnePage;
    String expectedCartPageUrl = cartPage.cartPageUrl;
    String expectedCartPageTitle = cartPage.YourCartPageTitle;

    @Test(priority = 13)
    public void verifyUserViewEmptyCart(){
        logger.info("************** TC_2.14 Started *************");
        logger.info("Test Case: Validate that user successfully view own cart when there is no any item in cart.");
        try{
            try{
            //Check there is added items in cart by checking cartBadge is display on page or not  ---> cartBadge display = There are items in cart
            if(cartBadge.isShoppingCartBadgeDisplay()){
                logger.info("There are items in cart");
            }
            }catch(NoSuchElementException e){
                logger.info("There is no elements available in Cart");
                //click on cart btn functionality validation
                clickOnCartButtonAssertion(expectedCartPageUrl,expectedCartPageTitle);
                logger.info("User successfully view cart page without adding any item to cart");
                logger.info("TC_2.14 Test Case passed");
            }
        }catch(Exception e){
            logger.info("Failed to view User's cart when Cart is empty");
            logger.info("TC_2.14 Test case failed");
            logger.error("Exception encountered: ", e);
        }
        logger.info("************ TC_2.14 Test case finished. ***************");
    }

    @Test
    public void verifyUserViewCartWithItems() {
        logger.info("************** TC_2.15 Started *************");
        logger.info("Test Case: Validate that user successfully views their cart when there are items in the cart.");
        try {
            // Check if there are items in the cart by checking if cartBadge is displayed
            if (cartBadge.isShoppingCartBadgeDisplay()) {
                logger.info("There are items in the cart.");
                // Click on "Cart" button and cart page validation
                clickOnCartButtonAssertion(expectedCartPageUrl,expectedCartPageTitle);
                logger.info("TC_2.15 Test Case passed");
            } else {
                logger.warn("No items in the cart. Cart badge is not displayed.");
                logger.info("TC_2.15 Test Case failed.");
            }
        } catch (NoSuchElementException e) {
            logger.error("No cart badge found; there might be no items in the cart.", e);
            logger.info("TC_2.15 Test Case failed.");
        } catch (Exception e) {
            logger.error("An unexpected error occurred during TC_2.15:", e);
            logger.info("TC_2.15 Test Case failed.");
            throw e; // Rethrow to ensure test runner catches the failure
        }finally {
            logger.info("************ TC_2.15 Test case finished. ***************");
        }
    }

    @Test(dependsOnMethods = "testCases.InventoryPageTest.verifyUserAddAllProductsInInventoryPageToCart")
    public void verifyAllAddedItemsDisplayInCartPage(){
        logger.info("*************** TC_2.16 is started ****************");
        logger.info("Test case: Validate that user added all products are displayed in cart page");
        try{
            int expectedItems = cartBadge.getNoOfItemsDisplayedOnShoppingCartBadge();
            logger.info("Total no of expected items displayed in cart is: " + expectedItems);
            int actualItemsAvailableInCartPage = cartPage.getTotalRemoveBtns();
            logger.info("Total products available in cart is : " +actualItemsAvailableInCartPage);
            Assert.assertEquals(actualItemsAvailableInCartPage,expectedItems,"Actual and expected total products in cart is mismatched.");
            logger.info("TC_2.16 Test case passed");
        }catch(Exception e){
            logger.info("TC_2.16 Test case failed");
            logger.error("An unexpected error occurred during TC_2.15:", e);
            throw e;
        }
        logger.info("************ TC_2.16 Test case finished. ***************");
    }

@Test(dependsOnMethods = "testCases.LoginTest.verifyUserLoginTest,")
    public void verifyCheckoutButtonInCartPageWithCartItems(){
        logger.info("**************** TC_2.17 Test Case Started ***************");
        logger.info("Validate that Checkout button is available and user directs to checkout step one page after clicked on checkout button");
        try{
            //click on cart icon and validation
            clickOnCartButtonAssertion(expectedCartPageUrl,expectedCartPageTitle);
            //check checkout button is available/visible
            verifyCheckoutBtnIsDisplay();
            //check added items are available in cart
            int actualItemsAvailableInCartPage = cartPage.getTotalRemoveBtns();
            if(actualItemsAvailableInCartPage > 0){
                logger.info("Added products are available in cart");
                cartPage.clickOnCheckOutBtn();
                clickOnCheckoutBtnFunctionalityAssertion();
                logger.info("TC_2.17 test case passed");
            }
            logger.info("There are no previously added products in cart");
            logger.info("TC_2.17 test case can not execute due to there is no previously added products in cart");
        }catch(Exception e){
            logger.error(" TC_2.17 test case failed.");
            logger.error("Exception encountered" ,e);
            throw e;
        }
        logger.info("************** TC_2.17 test case execution finished ****************");
    }


    @Test(dependsOnMethods = "testCases.LoginTest.verifyUserLoginTest,")
    public void verifyCheckoutButtonInCartPageWithoutCartItems(){
        logger.info("**************** TC_2.18 Test Case Started ***************");
        logger.info("Validate that Checkout button is available and user not directs to checkout step one page after clicked on checkout button without cart items");
        try{
            //click on cart icon
            clickOnCartButtonAssertion(expectedCartPageUrl,expectedCartPageTitle);
            //check checkout button is available/visible
            verifyCheckoutBtnIsDisplay();
            //check added items are available in cart
            int actualItemsAvailableInCartPage = cartPage.getTotalRemoveBtns();
            if(actualItemsAvailableInCartPage > 0){
                logger.info("Added products are available in cart");
                logger.error("TC_2.18 test case can not execute due to previously added items are available in cart");
            }
            logger.info("There are no previously added products in cart");
            cartPage.clickOnCheckOutBtn();
            logger.info("Clicked on checkout button");
            Assert.assertEquals("","Can not checkout without adding at least one item to the cart.","Error message not displayed after clicked on checkout button");
            logger.info("TC_2.18 test case passed");
        }catch(Exception e){
            logger.error(" TC_2.18 test case failed.");
            logger.error("Exception encountered" ,e);
            throw e;
        }
        logger.info("************** TC_2.18 test case execution finished ****************");
    }

    @Test
    public void verifyContinueShoppingButtonFunctionInCartPage(){
        logger.info("************* TC_2.19 Test case started ******************");
        logger.info("Test Case: Validate that user can continue shopping by click on Continue Shopping button on cart page");
        try{
            //click on cart button and assert
            clickOnCartButtonAssertion(expectedCartPageUrl,expectedCartPageTitle);
            //check continue shopping button available in cart page
            cartPage.isContinueShoppingBtnDisplay();
            //Click on continue shopping btn and assert
            cartPage.clickOnContinueShoppingBtn();
            clickOnContinueShoppingBtnAssertion();
            logger.info("TC_2.19 test case passed");
        }catch(Exception e){
            logger.error("Exception encountered",e);
            logger.error("TC_2.19 Test Case failed");
            throw e;
        }
        logger.info("*************** TC_2.19 test case finished *****************");
    }


    private void clickOnCartButtonAssertion(String expectedCartPageUrl, String expectedCartPageTitle){
        cartBadge.clickOnCartBtn();
        logger.info("Clicked on cart icon button");
        //cart page url Validation
        Assert.assertEquals(cartPage.getCartPageCurrentUrl(),expectedCartPageUrl,"Not directs to user's cart page. Expected page is : " + cartPage.cartPageUrl+ "Actual page is :" + cartPage.getCartPageCurrentUrl());
        logger.info("Successfully directed to user's cart page");
        //cart page title assertion
        String actualCartPageTitle = cartPage.getCartPageTitle();
        Assert.assertEquals(actualCartPageTitle,expectedCartPageTitle,"Cart Page Expected title is not dispalyed. Expected title is:" + expectedCartPageTitle + "Actual cart page title displayed is : " + actualCartPageTitle);
        logger.info("Expected cart page title displayed");
    }

    private void verifyCheckoutBtnIsDisplay() {
        Assert.assertEquals(cartPage.isCheckoutBtnDisplay(), true, "Checkout button is not displayed in cart page. Expected value is true. But receive " + cartPage.isCheckoutBtnDisplay());
        logger.info("Checkout button is visible/displayed");
    }

    private void clickOnCheckoutBtnFunctionalityAssertion(){
        logger.info("Clicked on checkout button");
        Assert.assertEquals(checkoutStepOnePage.getCurrentPageUrl(),checkoutStepOnePage.checkoutStepOnePageUrl,"user not directs to checkoutStepOnePage. Expected page url is :" + checkoutStepOnePage.checkoutStepOnePageUrl + "Actual current page url is :" + checkoutStepOnePage.getCurrentPageUrl());
        Assert.assertEquals(checkoutStepOnePage.getCheckOutStepOnePageTitle(),checkoutStepOnePage.getCheckoutStepOnePageUrlTitle,"Page Title is mismatched. expected" + checkoutStepOnePage.getCheckoutStepOnePageUrlTitle);
        logger.info("Successfully directs to checkout one page by clicking on checkout button");
    }

    private void clickOnContinueShoppingBtnAssertion(){
        Assert.assertEquals(inventoryPage.getCurrentPageUrl(), inventoryPage.inventoryPageUrl,"User not successfully directs to inventory page after clicked on continues shopping button on cart page");
        logger.info("User successfully directs to inventory page.");
    }
}
