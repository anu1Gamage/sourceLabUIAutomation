package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.CartBadge;
import pageObject.CartPage;
import pageObject.CheckoutStepOnePage;
import pageObject.CheckoutStepTwoPage;
import utilities.DataProviders;

public class CheckoutOnePageTest extends BaseClass{
    protected CartBadge cartBadge;
    protected CartPage cartPage;
    protected static CheckoutStepOnePage checkoutStepOnePage;
    protected static CheckoutStepTwoPage checkoutStepTwoPage;
    @Test(dependsOnMethods = "testCases.CartPageTest.verifyCheckoutButtonInCartPageWithCartItems")
    public void verifyProvideUserInfoAndCheckout(String firstName,String lastName, String postalCode){
        logger.info("**************** TC_2.20 Test case Started ****************");
        logger.info("Test Case: Validate that user successfully provide user information for checkout and continue successfully");

        try{
            assertCheckoutOnePageTitle();
            //sendUserInfoForCheckout();
            assertContinueButtonVisibility();
            clickOnContinueButton();
            logger.info("TC_2.20 test case passed");
        }catch(Exception e){
            logger.error("Exception is encountered",e);
            logger.info("TC_2.20 test case failed");
        }
        logger.info("*********** TC_2.20 Test case finished ****************");
    }

    @Test
    public void verifyUserCancelOrderWithoutProvideUserInfoToCheckout(){
        logger.info("**************** TC_2.21 Test case Started ****************");
        logger.info("Test Case: Validate that user successfully cancel order checkout by not providing user info and click on cancel button");
        try{
            assertCheckoutOnePageTitle();
            assertCancelButtonVisibility();
            clickOnCancelButton();
            logger.info("TC_2.21 test case passed");
        }catch(Exception e){
            logger.error("Exception is encountered",e);
            logger.info("TC_2.21 test case failed");
        }
        logger.info("*********** TC_2.21 Test case finished ****************");
    }

@Test(dataProvider ="userCheckoutInfo",dataProviderClass = DataProviders.class)
    public void verifyUserCancelCheckoutAfterProvideUserInfo(String firstName, String lastName, String postalCode){
        logger.info("**************** TC_2.22 Test case Started ****************");
        logger.info("Test Case: Validate that user successfully cancel order checkout by not providing user info and click on cancel button");
        try{
            assertCheckoutOnePageTitle();
            enterUserInformation(firstName, lastName, postalCode);
            assertCancelButtonVisibility();
            clickOnCancelButton();
        }catch(Exception e){
            logger.error("Exception is encountered",e);
            logger.info("TC_2.22 test case failed");
        }
        logger.info("*********** TC_2.22 Test case finished ****************");
    }

    public void assertCheckoutOnePageTitle(){
        String actualCheckoutStepOnePageTitle = checkoutStepOnePage.getCheckOutStepOnePageTitle();
        String expectedCheckoutStepOnePageTitle = checkoutStepOnePage.getCheckoutStepOnePageUrlTitle;
        Assert.assertEquals(actualCheckoutStepOnePageTitle, expectedCheckoutStepOnePageTitle, "Checkout Step One Page expected page title is not displayed. Expected title is :" +expectedCheckoutStepOnePageTitle+ "Actual page title is:" +actualCheckoutStepOnePageTitle);
        logger.info("CheckoutStepOnePageTitle is available");
    }

    private void enterUserInformation(String firstName, String lastName, String postalCode) {
        if (firstName != null) checkoutStepOnePage.setFirstNameForCheckout(firstName);
        if (lastName != null) checkoutStepOnePage.setLastNameForCheckout(lastName);
        if (postalCode != null) checkoutStepOnePage.setPostalCodeForCheckOut(postalCode);
        checkoutStepOnePage.clickOnCheckOutStepOnePageContinueBtn();
        logger.info("User clicked on checkout step one Continue button.");
    }

    public void assertContinueButtonVisibility(){
        boolean actual = checkoutStepOnePage.isContinueBtnOnCheckOutStepOnePageDisplay();
        boolean expected = true;
        Assert.assertEquals(actual,expected,"Actual result is differ from the Expected results");
        logger.info("Continue button is display in checkoutStepOnePage");
    }

    public void assertCancelButtonVisibility(){
        boolean actual = checkoutStepOnePage.isCancelBtnOnCheckOutStepOnePageDisplay();
        boolean expected = true;
        Assert.assertEquals(actual,expected,"Actual result is differ from the Expected results");
        logger.info("Cancel button is display in checkoutStepOnePage");
    }

    public void clickOnContinueButton(){
        checkoutStepOnePage.clickOnCheckOutStepOnePageContinueBtn();
        String actualCheckoutStepTwoPageUrl = checkoutStepTwoPage.getCurrentPageUrl();
        String expectedCheckoutStepTwoPageUrl = checkoutStepTwoPage.checkoutStepTwoPageUrl;
        Assert.assertEquals(actualCheckoutStepTwoPageUrl,expectedCheckoutStepTwoPageUrl,"Actual page url after click on continue button is differ from expected url. Expected page is: " + expectedCheckoutStepTwoPageUrl + "Actual page url is : " +actualCheckoutStepTwoPageUrl);
        logger.info("Successfully directs to next page after clicking on continue button on checkoutStepTwo page");
    }

    public void clickOnCancelButton(){
        checkoutStepOnePage.clickOnCheckOutStepOnePageCancelBtn();
        String expectedPageUrl = cartPage.cartPageUrl;
        String actualPageUrl = cartPage.getCartPageCurrentUrl();
        Assert.assertEquals(actualPageUrl,expectedPageUrl,"actual page and expected page is mis matched.");
        logger.info("Successfully cancel the order checkout");
    }

}
