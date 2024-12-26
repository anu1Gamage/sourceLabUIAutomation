package testCases;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.CartBadge;
import pageObject.CartPage;
import pageObject.CheckoutStepOnePage;
import pageObject.CheckoutStepTwoPage;
import utilities.DataProviders;
import utilities.TestCaseMetadata;

import java.lang.reflect.Method;

public class CheckoutOnePageTest extends BaseClass{
    protected CartBadge cartBadge;
    protected CartPage cartPage;
    protected static CheckoutStepOnePage checkoutStepOnePage;
    protected static CheckoutStepTwoPage checkoutStepTwoPage;

   String expectedPageURL = "https://www.saucedemo.com/checkout-step-two.html";

    @BeforeMethod
    public void setUp(ITestContext context) {
        context.setAttribute("module", "checkOutOnePage");
    }

    @Test(testName = "TC_4.1",
          priority = 21,
          dependsOnMethods = "testCases.CartPageTest.verifyCheckoutButtonInCartPageWithCartItems")
    public void verifyProvideUserInfoAndCheckout(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
        try{
            assertCheckoutOnePageTitle();
            verifyCheckoutUserInfoFieldsAreDisplayed();
            CleanCheckOutInputFieldsBefore();
            sendCheckoutUserInfo();
            assertContinueButtonVisibility();
            clickOnContinueButton();
        }catch(Exception e){
            logger.error("Exception is encountered",e);
            throw e;
        }
        logger.info("***********" +testCaseID+ "test execution finished ****************");
    }

    @Test(testName ="TC_4.2",
          priority = 22,
          dependsOnMethods = "testCases.CartPageTest.verifyCheckoutButtonInCartPageWithCartItems"
    )
    public void verifyUserCancelOrderWithoutProvideUserInfoToCheckout(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
        try{
            assertCheckoutOnePageTitle();
            CleanCheckOutInputFieldsBefore();
            assertCancelButtonVisibility();
            clickOnCancelButton();
        }catch(Exception e){
            logger.error("Exception is encountered",e);
            throw e;
        }
        logger.info("*********** " +testCaseID+ "test case execution finished ****************");
    }

@Test(testName = "TC_4.3",
      priority = 23,
      dependsOnMethods = "testCases.CartPageTest.verifyCheckoutButtonInCartPageWithCartItems"
)
    public void verifyUserCancelCheckoutAfterProvideUserInfo(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID,module);
        String description = TestCaseMetadata.getDescription(testCaseID,module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);

        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
    try{
            assertCheckoutOnePageTitle();
            verifyCheckoutUserInfoFieldsAreDisplayed();
            sendCheckoutUserInfo();
            assertCancelButtonVisibility();
            clickOnCancelButton();
        }catch(Exception e){
            logger.error("Exception is encountered",e);
            throw e;
        }
        logger.info("*********** " +testCaseID+ "test execution finished ****************");
    }


    @Test(testName = "TC_4.4",
          priority = 24
    )
    public void verifyUserContinueCheckOutAfterCancelErrorMsg(ITestContext context, String testCaseId, String firstName, String lastName,
                                                              String postalCode, boolean successExpected, String ExpectedError){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID,module);
        String description = TestCaseMetadata.getDescription(testCaseID,module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);

        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");

        try{
            if(firstName != null){
                checkoutStepOnePage.setFirstNameForCheckout(firstName);
            }
            if(lastName != null){
                checkoutStepOnePage.setLastNameForCheckout(lastName);
            }
            if(postalCode != null){
                checkoutStepOnePage.setPostalCodeForCheckOut(postalCode);
            }
            assertContinueButtonVisibility();
            checkoutStepOnePage.clickOnCheckOutStepOnePageContinueBtn();


            if(successExpected){
                String actualDirectedPageURL = checkoutStepTwoPage.getCurrentPageUrl();
                Assert.assertEquals(actualDirectedPageURL,expectedPageURL,"After click on CheckOutOnePage continue button, Actual directed page url and expected page url is different");
            }else{
                String ActualErrorMessage = checkoutStepOnePage.getErrorMessage();
                Assert.assertEquals(ActualErrorMessage,ExpectedError,"Error massage is not matched with expected Error Message");
                assertClickOnCancelButtonOFErrorMsg();
                fillUnfilledCheckoutInputFields(firstName, lastName, postalCode);
                assertContinueButtonVisibility();
                checkoutStepOnePage.clickOnCheckOutStepOnePageContinueBtn();
                assertContinueButtonVisibility();
            }
        }catch(Exception e){
            logger.error("Test failed due to exception: ", e);
            Assert.fail("Test case failed due to unexpected exception: " + e.getMessage());
        }
    }

    // ToDo provide correct user info and continue After receive error message but not click on cancel button on error message  Test Case

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

    public void assertCheckoutOnePageTitle(){
        String actualCheckoutStepOnePageTitle = checkoutStepOnePage.getCheckOutStepOnePageTitle();
        String expectedCheckoutStepOnePageTitle = checkoutStepOnePage.getCheckoutStepOnePageUrlTitle;
        Assert.assertEquals(actualCheckoutStepOnePageTitle, expectedCheckoutStepOnePageTitle, "Checkout Step One Page expected page title is not displayed. Expected title is :" +expectedCheckoutStepOnePageTitle+ "Actual page title is:" +actualCheckoutStepOnePageTitle);
        logger.info("CheckoutStepOnePageTitle is available");
    }

    /*private void enterUserInformation(String firstName, String lastName, String postalCode) {
        if (firstName != null) checkoutStepOnePage.setFirstNameForCheckout(firstName);
        if (lastName != null) checkoutStepOnePage.setLastNameForCheckout(lastName);
        if (postalCode != null) checkoutStepOnePage.setPostalCodeForCheckOut(postalCode);
        checkoutStepOnePage.clickOnCheckOutStepOnePageContinueBtn();
        logger.info("User clicked on checkout step one Continue button.");
    }

     */

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

    public void sendCheckoutUserInfo() {
        checkoutStepOnePage.setFirstNameForCheckout(properties.getProperty("firstName"));
        checkoutStepOnePage.setLastNameForCheckout(properties.getProperty("lastName"));
        checkoutStepOnePage.setPostalCodeForCheckOut(properties.getProperty("postalCode"));
    }

    public void verifyCheckoutUserInfoFieldsAreDisplayed() {
        boolean isFirstNameFieldDisplayed = checkoutStepOnePage.isFirstNameFieldDisplay();
        Assert.assertTrue(isFirstNameFieldDisplayed, "FirstName field is not displayed on the Checkout Step One page");

        boolean isLastNameFieldDisplayed = checkoutStepOnePage.isLastNameFieldDisplay();
        Assert.assertTrue(isLastNameFieldDisplayed, "LastName field is not displayed on the Checkout Step One page");

        boolean isPostalCodeFieldDisplayed = checkoutStepOnePage.isPostalCodeFieldDisplay();
        Assert.assertTrue(isPostalCodeFieldDisplayed, "PostalCode field is not displayed on the Checkout Step One page");
    }

    public void CleanCheckOutInputFieldsBefore(){
        String firstNameValue = CheckoutStepOnePage.getAttributeValueOfFirstNameField();
        String lastNameValue = CheckoutStepOnePage.getAttributeValueOfLastNameField();
        String postalCodeValue = CheckoutStepOnePage.getAttributeValueOfPostalCodeField();

        boolean isFirstNameFilled  = !firstNameValue.trim().isEmpty();
        boolean isLastNameFilled = !lastNameValue.trim().isEmpty();
        boolean isPostalCodeValue = !postalCodeValue.trim().isEmpty();

        if(isFirstNameFilled || isLastNameFilled || isPostalCodeValue ){
            logger.warn("At least one field is filled");
            CheckoutStepOnePage.clearInputField();
        }else {
            logger.info("All fields are empty");
        }
    }


    public void fillUnfilledCheckoutInputFields(String firstName, String lastName, String postalCode){

        if (checkoutStepOnePage.getAttributeValueOfFirstNameField().trim().isEmpty() && firstName != null) {
            checkoutStepOnePage.setFirstNameForCheckout(firstName);
        }
        if (checkoutStepOnePage.getAttributeValueOfLastNameField().trim().isEmpty() && lastName != null) {
            checkoutStepOnePage.setLastNameForCheckout(lastName);
        }
        if (checkoutStepOnePage.getAttributeValueOfPostalCodeField().trim().isEmpty() && postalCode != null) {
            checkoutStepOnePage.setPostalCodeForCheckOut(postalCode);
        }
    }




    private void assertClickOnCancelButtonOFErrorMsg(){
        checkoutStepOnePage.clickOnErrorMessageCancelButton();
        logger.info("Clicked on cancel button of error message");
        boolean isErrorDisplay = checkoutStepOnePage.isErrorMessageDisplay();
        Assert.assertEquals(isErrorDisplay,false,"Error Message not successfully closed");
    }


}
