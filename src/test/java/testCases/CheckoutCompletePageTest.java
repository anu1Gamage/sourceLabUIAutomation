package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import static testCases.CheckoutStepTwoTestPage.checkoutCompletePage;
import static testCases.CheckoutStepTwoTestPage.inventoryPage;

public class CheckoutCompletePageTest extends BaseClass{

@Test
    public void verifyUserBackToInventoryPage(){
        logger.info("************** TC_2.25 Test Case Started **************");
        logger.info("Test Case: Validate that user can successfully back to inventory page by click on Back To Home button in CheckoutComplete page");
        try{
            checkoutCompletePage.clickOnCheckOutCompletePageBackToProductBtn();
            String actualPageUrl = inventoryPage.getCurrentPageUrl();
            String expectedPageUrl = inventoryPage.inventoryPageUrl;
            Assert.assertEquals(actualPageUrl,expectedPageUrl,"Actual page is differ from expected destination page url. Expected page is:"+expectedPageUrl+"Actual page url is:"+actualPageUrl);
            logger.info("TC_2.25 Test case Passed.");
        }catch(Exception e){
            logger.error("Exception encountered",e);
            logger.info("TC_2.25 Test case Failed due to exception");
        }
        logger.info("******************* TC_2.25 test case finished *****************");
    }
    
    public void assertCheckoutCompletePageHeaderMessage(){
        String actualHeaderMessage = checkoutCompletePage.getCheckOutCompletePageHeader();
        String expectedHeaderMessage = checkoutCompletePage.completePageHeader;
        Assert.assertEquals(actualHeaderMessage,expectedHeaderMessage,"checkout complete page actual header message is differ from expected message. Expected message is:" +expectedHeaderMessage+ "Actual Header message is: "+actualHeaderMessage);
        logger.info("Order complete message is displayed.");
    }

    public void verifyCheckoutCompletePageIconVisibility(){
        boolean actualCheckoutCompleteIconDisplayStatus = checkoutCompletePage.isCheckOutCompleteIconDisplay();
        boolean expectedCheckoutCompleteIconDisplayStatus = true;
        Assert.assertEquals(actualCheckoutCompleteIconDisplayStatus,expectedCheckoutCompleteIconDisplayStatus,"Order finished icon not displayed. Expected result is:" +actualCheckoutCompleteIconDisplayStatus+ "Actual result is:" +expectedCheckoutCompleteIconDisplayStatus);
        logger.info("Order completed icon displayed");
    }
    public void verifyCheckoutCompletionParagraph(){
        String actualCheckoutCompletionParagraph = checkoutCompletePage.getCheckoutCompletionParagraph();
        String expectedCheckoutCompletionParagraph = checkoutCompletePage.completePagePara;
        Assert.assertEquals(actualCheckoutCompletionParagraph,expectedCheckoutCompletionParagraph,"Order completion message paragraph is mismatched. Expected paragraph is:" +expectedCheckoutCompletionParagraph + "Actual paragraph displayed is:" +actualCheckoutCompletionParagraph);
        logger.info("Order completion message paragraph displayed.");
        logger.info("Oder successfully completed");
    }
}
