package testCases;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.CartBadge;
import pageObject.CartPage;

public class CartTest extends LoginTest{
    protected CartBadge cartBadge;
    protected CartPage cartPage;

    String expectedCartPageTitle = "Your Cart";


    @Test(priority = 13)
    public void verifyUserViewCartWithEmptyCartItems(){
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
                //click on add to cart btn
                cartBadge.clickOnAddToCartBtn();
                Assert.assertEquals(cartPage.getCartPageCurrentUrl(),"https://www.saucedemo.com/cart.html","Not directs to user's cart page.");
                logger.info("Successfully directed to user's cart page");
                String actualCartPageTitle = cartPage.getCartPageTitle();
                Assert.assertEquals(actualCartPageTitle,expectedCartPageTitle,"Cart Page Expected title is not dispalyed. Expected title is:" + expectedCartPageTitle + "Actual cart page title displayed is : " + actualCartPageTitle);
                logger.info("Expected cart page title displayed");
                logger.info("TC_2.14 Test Case passed");
            }
        }catch(Exception e){
            logger.info("Failed to view User's cart when Cart is empty");
            logger.info("TC_2.14 Test case failed");
            logger.error("Exception encountered: ", e);
        }
        logger.info("************ TC_2.14 Test case finished. ***************");
    }

}
