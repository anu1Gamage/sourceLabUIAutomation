package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.CheckoutCompletePage;
import pageObject.CheckoutStepTwoPage;
import pageObject.InventoryPage;

public class CheckoutStepTwoTestPage extends BaseClass{

    protected static CheckoutStepTwoPage checkoutStepTwoPage;
    protected static CheckoutCompletePage checkoutCompletePage;

    protected static InventoryPage inventoryPage;

    @Test
    public void verifyUserClickOnFinishOrder(){
       logger.info("************** TC_2.23 Test Case Started **************");
       logger.info("Test Case : Validate that user finish order by click on Finish button in checkoutStepTwo page");
       try{
           assertCheckoutOverview();
           verifyFinishButtonIsDisplay();
           verifyClickOnFinishBtn();
           logger.info("TC_2.23 test case passed");
       }catch(Exception e){
           logger.error("Exception encountered",e);
           logger.info("TC_2.23 Test Case failed due to exception");
       }
       logger.info("*********** TC_2.23 Test case finished *****************");
    }

    public void verifyUserCancelOrderBeforeFinish(){
        logger.info("************** TC_2.24 Test Case Started **************");
        logger.info("Test Case : Validate that user cancel order by click on Cancel button in checkoutStepTwo page");
        try{
            assertCheckoutOverview();
            verifyCheckoutStepTwoPageCancelButtonIsDisplay();
            clickOnCheckoutStepTwoPageCancelBtn();
            logger.info("TC_2.24 Test case passed");
        }catch(Exception e){
            logger.error("Exception encountered",e);
            logger.info("TC_2.24 Test Case failed due to exception");
        }
        logger.info("*********** TC_2.24 Test case finished *****************");
    }

    public void assertCheckoutOverview() {
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


    public void verifyFinishButtonIsDisplay(){
        boolean actualFinishBtnStatus = checkoutStepTwoPage.isCheckOutStepTwoPageFinishBtnDisplay();
        boolean expectedFinishBtnStatus = true;
        Assert.assertEquals(actualFinishBtnStatus,expectedFinishBtnStatus,"Finish button is not displayed.expected results is " + expectedFinishBtnStatus + "Actual results is : " +actualFinishBtnStatus);
        logger.info("Checkout step two page Finish button is displayed");
    }

    public void verifyCheckoutStepTwoPageCancelButtonIsDisplay(){
        boolean actualCancelBtnStatus = checkoutStepTwoPage.isCheckOutStepTwoPageCancelBtnDisplay();
        boolean expectedCancelBtnStatus = true;
        Assert.assertEquals(actualCancelBtnStatus,expectedCancelBtnStatus,"Cancel button is not displayed on CheckoutStepTwo Page. Expected resulte is: "+actualCancelBtnStatus+ "Actual result is:" +actualCancelBtnStatus);
        logger.info("Checkout step two page Cancel button is displayed");
    }

    public void verifyClickOnFinishBtn(){
        checkoutStepTwoPage.clickOnCheckOutStepTwoPageFinishBtn();
        logger.info("Clicked on Finish button on checkout step two page");
        String actualHeaderMessage = checkoutCompletePage.getCheckOutCompletePageHeader();
        String expectedHeaderMessage = checkoutCompletePage.completePageHeader;
        Assert.assertEquals(actualHeaderMessage,expectedHeaderMessage,"checkout complete page actual header message is differ from expected message. Expected message is:" +expectedHeaderMessage+ "Actual Header message is: "+actualHeaderMessage);
        logger.info("Order complete message is displayed.");
        boolean actualCheckoutCompleteIconDisplayStatus = checkoutCompletePage.isCheckOutCompleteIconDisplay();
        boolean expectedCheckoutCompleteIconDisplayStatus = true;
        Assert.assertEquals(actualCheckoutCompleteIconDisplayStatus,expectedCheckoutCompleteIconDisplayStatus,"Order finished icon not displayed. Expected result is:" +actualCheckoutCompleteIconDisplayStatus+ "Actual result is:" +expectedCheckoutCompleteIconDisplayStatus);
        logger.info("Order completed icon displayed");
        String actualCheckoutCompletionParagraph = checkoutCompletePage.getCheckoutCompletionParagraph();
        String expectedCheckoutCompletionParagraph = checkoutCompletePage.completePagePara;
        Assert.assertEquals(actualCheckoutCompletionParagraph,expectedCheckoutCompletionParagraph,"Order completion message paragraph is mismatched. Expected paragraph is:" +expectedCheckoutCompletionParagraph + "Actual paragraph displayed is:" +actualCheckoutCompletionParagraph);
        logger.info("Order completion message paragraph displayed.");
        logger.info("Oder successfully completed");
    }

    public void clickOnCheckoutStepTwoPageCancelBtn(){
        checkoutStepTwoPage.clickOnCancelBtn();
        logger.info("Clicked on Cancel button on checkout step two page");
        String actualPageUrl = inventoryPage.getCurrentPageUrl();
        String expectedPageUrl = inventoryPage.inventoryPageUrl;
        Assert.assertEquals(actualPageUrl,expectedPageUrl,"After clicked on checkoutStepTwoPage Cancel button user not successfully directs to Inventory page. Expected page is:" +actualPageUrl+ "Actual page url is:" +expectedPageUrl);
        logger.info("User successfully directs to Inventory page after click on checkoutStepTwoPage Cancel button.");
    }


}
