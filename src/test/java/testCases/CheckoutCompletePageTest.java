package testCases;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.TestCaseMetadata;

import java.lang.reflect.Method;

import static testCases.CheckoutStepTwoTestPage.checkoutCompletePage;
import static testCases.CheckoutStepTwoTestPage.inventoryPage;

public class CheckoutCompletePageTest extends BaseClass{

    @BeforeMethod
    public void setUp(ITestContext context) {
        context.setAttribute("module", "checkOutTwoPageMetaData");
    }

    @Test(testName ="TC_6.1"

    )
    public void verifyUserBackToInventoryPage(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
        try{
            assertCheckoutCompletePageHeaderMessage();
            verifyCheckoutCompletePageIconVisibility();
            verifyCheckoutCompletionParagraph();
            clickOnCheckOutCompletePageBackToProductsButton();
            assertClickOnCheckOutCompletePageBackToProductsButton();
        }catch(Exception e){
            logger.error("Exception encountered",e);
        }
        logger.info("******************* " +testCaseID+  " test case execution finished *****************");
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

    public void clickOnCheckOutCompletePageBackToProductsButton(){
        checkoutCompletePage.clickOnCheckOutCompletePageBackToProductBtn();
        logger.info("Clicked on checkout complete page back to product button");
    }

    public void assertClickOnCheckOutCompletePageBackToProductsButton(){
        String actualPageUrl = inventoryPage.getCurrentPageUrl();
        String expectedPageUrl = inventoryPage.inventoryPageUrl;
        Assert.assertEquals(actualPageUrl,expectedPageUrl,"Actual page is differ from expected destination page url. Expected page is:"+expectedPageUrl+"Actual page url is:"+actualPageUrl);
    }

}
