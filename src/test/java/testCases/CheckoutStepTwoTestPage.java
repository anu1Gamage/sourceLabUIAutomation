package testCases;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.CheckoutCompletePage;
import pageObject.CheckoutStepTwoPage;
import pageObject.InventoryPage;
import utilities.TestCaseMetadata;

import java.lang.reflect.Method;

public class CheckoutStepTwoTestPage extends BaseClass{

    protected static CheckoutStepTwoPage checkoutStepTwoPage;
    protected static CheckoutCompletePage checkoutCompletePage;
    protected static InventoryPage inventoryPage;

    @BeforeMethod
    public void setUp(ITestContext context) {
        context.setAttribute("module", "checkOutTwoPageMetaData");
    }

    @Test(testName = "TC_5.1",
          priority = 26,
          dependsOnMethods = "testCases.CheckOutOnePageTest.verifyProvideUserInfoAndCheckout"
    )
    public void verifyUserClickOnFinishOrder(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
       try{
           assertCheckoutOverview();
           verifyFinishButtonIsDisplay();
           verifyClickOnFinishBtn();
       }catch(Exception e){
           logger.error("Exception encountered",e);
           throw e;
       }
       logger.info("*********** " +testCaseID+  " test case finished *****************");
    }

    @Test(testName ="TC_5.2",
          priority = 27,
          dependsOnMethods = "testCases.CheckOutOnePageTest.verifyProvideUserInfoAndCheckout"
    )
    public void verifyUserCancelOrderBeforeFinish(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
        try{
            assertCheckoutOverview();
            verifyCheckoutStepTwoPageCancelButtonIsDisplay();
            clickOnCheckoutStepTwoPageCancelBtn();
        }catch(Exception e){
            logger.error("Exception encountered",e);
            throw e;
        }
        logger.info("*********** " +testCaseID+ " test case execution finished *****************");
    }

    private String getTestCaseId() {
        try {
            String methodName = new Throwable().getStackTrace()[1].getMethodName();
            Method method = this.getClass().getMethod(methodName);
            Test testAnnotation = method.getAnnotation(Test.class);
            return testAnnotation.testName();
        } catch (NoSuchMethodException e) {
            logger.error("Failed to fetch test case ID from @Test annotation.", e);
            return "UnknownTestCaseID";
        }
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
