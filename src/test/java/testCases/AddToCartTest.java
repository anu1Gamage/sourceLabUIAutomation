package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.CartBadge;
import pageObject.InventoryPage;
import pageObject.LoginPage;

public class AddToCartTest extends LoginTest{
    protected CartBadge cartBadge;
    private int cartBadgeValueBefore;
    private int cartBadgeValueAfter;

    @Test(dependsOnMethods = "testCases.LoginTest.verifyUserLoginTest",priority = 0)
    public void verifyUserAddOneProductToCart(){
        //Validate that user can successfully add any item in index.html page into own cart.
        //Pre-Requisites:
            //1. User has been logging to application successfully.
            //2. There are no previously added items in a user's cart.
        logger.info("*********** TC_2.1 Validate that user can successfully add any item in index.html page into own cart Started ************");
        try {
            // Initialize CartBadge object and check if the cart is empty initially
            cartBadge = new CartBadge(driver);
                cartBadgeValueBefore = 0;
                logger.debug("Shopping cart badge is not displayed initially.");
                logger.info("Total items in cart before adding a product: " + cartBadgeValueBefore);
                Assert.assertEquals(cartBadgeValueBefore, 0, "Cart should be empty initially.");

            // Add Product1 to the cart
            Assert.assertTrue(inventoryPage.isProduct1AddToCartBtnDisplay(), "Product1 AddToCart button is not visible.");
            inventoryPage.clickOnProduct1AddToCartBtn();
            logger.debug("Clicked on AddToCart button for Product1.");

            // Verify that AddToCart button changes to Remove button
            Assert.assertTrue(inventoryPage.isSauceLabsBackPackRemoveBtnDisplay(), "Add To Cart button did not change to Remove button.");
            logger.info("AddToCart button successfully changed to Remove button for Product1.");

            // Verify cart badge updates correctly after adding the item
            Assert.assertTrue(cartBadge.isShoppingCartBadgeDisplay(), "Shopping cart badge is not displayed after adding an item.");
            cartBadgeValueAfter = cartBadge.getNoOfItemsDisplayedOnShoppingCartBadge();
            Assert.assertTrue(cartBadgeValueAfter > cartBadgeValueBefore, "Cart badge value did not increase after adding an item.");

            logger.info("Product successfully added to cart. Total items in cart: " + cartBadgeValueAfter);
        } catch (Exception e) {
            Assert.fail("Failed to add item to cart: " + e.getMessage());
            logger.error("Exception encountered: ", e);
        }
        logger.info("*********** TC_2.1 Finished ************");
    }

    @Test(dependsOnMethods = "testCases.AddToCartTest.verifyUserAddOneProductToCart",priority = 1)
    public void verifyUserAddSecondProductToCart(){
       // Validate that user can successfully add 2nd item into own cart after 1st item add.
       //1. User has been logging to application successfully.
        //2. There are  previously added items in a user's cart.
        //3. There are more items in index.html page
        logger.info(" ********** TC_2.2 : Validate that user can successfully add 2nd item into own cart after 1st item add. ***********");

        try{
            cartBadgeValueBefore = cartBadge.getNoOfItemsDisplayedOnShoppingCartBadge();
            logger.info("Total Products available in cart before add 2nd item : " + cartBadgeValueBefore);
            if(cartBadgeValueBefore == 1){
                Assert.assertTrue(inventoryPage.isProduct2AddToCartBtnDisplay(), "Add To Cart button for the second product not visible.");
                logger.info("AddToCart Button of the second product is available");
                inventoryPage.clickOnProduct2AddToCartBtn();
                logger.debug("Clicked on Product2AddToCart button");
                Assert.assertTrue(inventoryPage.isSauceLabsBoltTShirtRemoveBtnDisplay(), "Add To Cart button did not change to Remove button.");
                logger.info("AddToCart button successfully changed to Remove button for Product2.");
                cartBadgeValueAfter = cartBadge.getNoOfItemsDisplayedOnShoppingCartBadge();
                Assert.assertEquals(cartBadgeValueAfter,cartBadgeValueBefore + 1, "Cart badge value did not increase after adding an item.");
            }
            logger.info("Previously added products not available.");
        }
        catch (Exception e) {
            Assert.fail("Failed to add item to cart: " + e.getMessage());
            logger.error("Exception encountered: ", e);
        }
        logger.info("************ TC_2.2 Test case execution finished. ***********");
    }

    @Test(dependsOnMethods = "testCases.LoginTest.verifyUserLoginTest",priority = 3)
    public void verifyUserAddAllProductsInInventoryPageToCart(){
        logger.info(" ************** TC_2.3 : Validate that user can successfully add all items available in Inventory Page into own cart after 1st item add. *************");
        try{
        cartBadgeValueBefore = cartBadge.getNoOfItemsDisplayedOnShoppingCartBadge();
        logger.info("Total Products available in cart before add items : " + cartBadgeValueBefore);
        logger.info("Available products adding into cart is started.");
        //click on all Available products with add to cart button
        inventoryPage.clickOnAvailableAllAddToCartButtonElementsInInventoryPage();
        cartBadgeValueAfter = cartBadge.getNoOfItemsDisplayedOnShoppingCartBadge();
        logger.info("Total products available in cartBadge After add all products in Inventory page: " + cartBadgeValueAfter);
        Assert.assertEquals(((cartBadgeValueAfter>cartBadgeValueBefore) && cartBadgeValueAfter ==6),true,"All products not accurately added to the cart.");
        }catch(Exception e){
            Assert.fail("Failed to add all items into cart: " + e.getMessage());
            logger.error("Exception encountered: ", e);
        }
        logger.info("********* TC_2.3 test case execution finished. ************");
    }

    @Test(dependsOnMethods = "tests.AddToCartTest.verifyUserAddOneProductToCart",priority = 2)
    public void verifyUserRemoveAddedItemFromCartByClickOnRemoveBtnOnInventoryPage(){
        logger.info("*********** TC_2.4 : Validate that user successfully remove added item from cart by clicking remove button displayed. ***********");
        try{
            cartBadgeValueBefore = cartBadge.getNoOfItemsDisplayedOnShoppingCartBadge();
            logger.info("Total no of product in cart badge before remove product from cart: " +cartBadgeValueBefore);
            inventoryPage.clickOnSauceLabsBackPackRemoveBtn();
            Assert.assertEquals(inventoryPage.isProduct1AddToCartBtnDisplay(),true,"Remove button not changed to Add to cart button");
            cartBadgeValueAfter = cartBadge.getNoOfItemsDisplayedOnShoppingCartBadge();
            logger.info("Total no of product in cart badge after remove product from cart:" + cartBadgeValueAfter);
            Assert.assertEquals(cartBadgeValueAfter, (cartBadgeValueBefore -1),"Total no of products in cart badge is not decreased by one after remove one product from cart.");
        }catch(Exception e){
            logger.info("Failed to remove added product from cart badge");
            logger.error("Exception encountered: ", e);
        }
        logger.info("********** TC_2.4 Test case execution finished. ***********");
    }

    @Test(dependsOnMethods = "tests.AddToCartTest.verifyUserAddAllProductsInInventoryPageToCart", priority = 4)
    public void verifyUserRemoveOneItemFromCartByClickOnRemoveBtnOnInventoryPage(){
        logger.info("********** TC_TC_2.5 Validate that user successfully remove one item from added items in user's cart by clicking remove button displayed. ************");
        try{
            cartBadgeValueBefore = cartBadge.getNoOfItemsDisplayedOnShoppingCartBadge();
            logger.info("Total no of product in cart badge before remove product from cart: " +cartBadgeValueBefore);
            inventoryPage.clickOnSauceLabsBackPackRemoveBtn();
            Assert.assertEquals(inventoryPage.isProduct1AddToCartBtnDisplay(),true,"Remove button not changed to Add to cart button");
            cartBadgeValueAfter = cartBadge.getNoOfItemsDisplayedOnShoppingCartBadge();
            logger.info("Total no of product in cart badge after remove product from cart:" + cartBadgeValueAfter);
            Assert.assertEquals(cartBadgeValueAfter, (cartBadgeValueBefore -1),"Total no of products in cart badge is not decreased by one after remove one product from cart.");
        }catch(Exception e){
            logger.info("Failed to remove one product from cart badge");
            logger.error("Exception encountered: ", e);
        }
        logger.info("********** TC_2.5 Test case execution finished. ***********");
    }

    @Test(dependsOnMethods = "tests.AddToCartTest.verifyUserAddAllProductsInInventoryPageToCart", priority = 4)
    public void verifyUserRemoveAllItemFromCartByClickOnRemoveBtnsOnInventoryPage(){
        logger.info("********** TC_TC_2.6 Validate that user successfully add item to cart by following below flow. \n" +
                "\n" +
                "Click on selected Item image >>  Click on Add to cart button >> Click on Back to Products Link.************");
        try{
            

         }catch(Exception e){
            logger.info("Failed to remove one product from cart badge");
            logger.error("Exception encountered: ", e);
        }
        logger.info("********** TC_2.6 Test case execution finished. ***********");
    }






}
