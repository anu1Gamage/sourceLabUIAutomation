package testCases;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObject.CartBadge;
import pageObject.CartPage;
import pageObject.CheckoutStepOnePage;
import utilities.CustomListener;
import utilities.TestCaseMetadata;

import java.lang.reflect.Method;

@Listeners(CustomListener.class)
public class CartPageTest extends LoginTest{
    protected CartBadge cartBadge;
    protected CartPage cartPage;
    protected CheckoutStepOnePage checkoutStepOnePage;
    String expectedCartPageUrl = cartPage.cartPageUrl;
    String expectedCartPageTitle = cartPage.YourCartPageTitle;

    @BeforeMethod
    public void setUp(ITestContext context) {
        context.setAttribute("module", "cartPage");
    }

    @Test(testName = "TC_3.1",
          priority = 15,
          dependsOnMethods = "InventoryPageTest.verifyUserRemoveAllProductsFromCartByClickOnRemoveButtonsOnInventoryPage"
    )
    public void verifyUserViewEmptyCart(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
        try{
            verifyItemsAvailableInCart();
            clickOnCartButton();
            cartPageAssertion(expectedCartPageUrl,expectedCartPageTitle);
        }catch(Exception e){
            logger.error("Exception encountered, Failed to view User's cart when Cart is empty ", e);
            throw e;
        }
        logger.info("*********** " + testCaseID + " test execution finished ************");
    }

    @Test(testName = "TC_3.2",
          priority = 17,
          dependsOnMethods = "testCases.inventoryPageTest.verifyUserAddOneProductToCart"
    )
    public void verifyUserViewCartWithItems(ITestContext context) {
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
        try {
            verifyItemsAvailableInCart();
            assertItemsAvailableInCart();
            clickOnCartButton();
            cartPageAssertion(expectedCartPageUrl,expectedCartPageTitle);
        } catch (Exception e) {
            logger.error("An unexpected error occurred during test execution:", e);
            throw e;
        }finally {
            logger.info("*********** " + testCaseID + " test execution finished ************");
        }
    }

    @Test(testName ="TC_3.3",
          dependsOnMethods = "testCases.InventoryPageTest.verifyUserAddAllProductsInInventoryPageToCart",
          priority = 18
    )
    public void verifyAllAddedItemsDisplayInCartPage(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
        try{
            verifyItemsAvailableInCart();
            assertItemsAvailableInCart();
            clickOnCartButton();
            cartPageAssertion(expectedCartPageUrl,expectedCartPageTitle);
        }catch(Exception e){
            logger.error("An unexpected error occurred during TC_2.15:", e);
            throw e;
        }
        logger.info("*********** " + testCaseID + " test execution finished ************");
    }

@Test(testName = "TC_3.4",
      dependsOnMethods = "testCases.CartPageTest.verifyAllAddedItemsDisplayInCartPage",
      priority = 19
    )
    public void verifyCheckoutButtonInCartPageWithCartItems(ITestContext context){
    String testCaseID = getTestCaseId();
    String module = context.getAttribute("module").toString();
    String scenario = TestCaseMetadata.getScenario(testCaseID, module);
    String description = TestCaseMetadata.getDescription(testCaseID, module);
    String priority = TestCaseMetadata.getPriority(testCaseID, module);
    String severity = TestCaseMetadata.getSeverity(testCaseID, module);
    logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
    logger.info("*********** " + testCaseID + " " + description + " started ************");
       try{
            verifyCheckoutBtnIsDisplay();
            clickOnCheckoutButton();
            assertClickOnCheckoutBtn();
       }catch(Exception e){
            logger.error("Exception encountered" ,e);
            throw e;
        }
        logger.info("*********** " + testCaseID + " test execution finished ************");
    }

    @Test(testName = "TC_3.5",
            priority = 20
    )
    public void verifyContinueShoppingButtonFunctionInCartPage(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
        try{
            cartPageAssertion(expectedCartPageUrl,expectedCartPageTitle);
            assertContinueShoppingBtnAvailability();
            clickOnContinueShoppingBtn();
            continueShoppingBtnAssertion();
        }catch(Exception e){
            logger.error("Exception encountered",e);
            throw e;
        }
        logger.info("*********** " + testCaseID + " test execution finished ************");
    }


    @Test(testName = "TC_3.6",
         dependsOnMethods = "testCases.CartPageTest.verifyUserViewEmptyCart",
         priority = 16)
    public void verifyCheckoutButtonInCartPageWithoutCartItems(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
        try{
            verifyCheckoutBtnIsDisplay();
            clickOnCheckoutButton();
            assertClickOnCheckoutBtn();
        }catch(Exception e){
            logger.error("Exception encountered" ,e);
            throw e;
        }
        logger.info("*********** " + testCaseID + " test execution finished ************");
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

    private void verifyItemsAvailableInCart(){
        try{
            if(cartBadge.isShoppingCartBadgeDisplay()){
                logger.info("There are items in cart");
            }
        }catch(NoSuchElementException e){
            logger.info("There is no elements available in Cart" , e);
        }
    }

    private void assertItemsAvailableInCart(){
        Assert.assertEquals(cartBadge.isShoppingCartBadgeDisplay(),true,"Items not available in cart.");
    }

    private void clickOnCartButton(){
        cartBadge.clickOnCartBtn();
        logger.info("Clicked on cart icon button");
    }

    private void cartPageAssertion(String expectedCartPageUrl, String expectedCartPageTitle){
        Assert.assertEquals(cartPage.getCartPageCurrentUrl(),expectedCartPageUrl,"Not directs to user's cart page. Expected page is : " + cartPage.cartPageUrl+ "Actual page is :" + cartPage.getCartPageCurrentUrl());
        logger.info("Successfully directed to user's cart page");
        String actualCartPageTitle = cartPage.getCartPageTitle();
        Assert.assertEquals(actualCartPageTitle,expectedCartPageTitle,"Cart Page Expected title is not dispalyed. Expected title is:" + expectedCartPageTitle + "Actual cart page title displayed is : " + actualCartPageTitle);
        logger.info("Expected cart page title displayed");
    }

    private void verifyCheckoutBtnIsDisplay() {
        Assert.assertEquals(cartPage.isCheckoutBtnDisplay(), true, "Checkout button is not displayed in cart page. Expected value is true. But receive " + cartPage.isCheckoutBtnDisplay());
        logger.info("Checkout button is visible/displayed");
    }

    private void clickOnCheckoutButton(){
        cartPage.clickOnCheckOutBtn();
        logger.info("Clicked on checkout button");
    }

    private void assertClickOnCheckoutBtn(){
        Assert.assertEquals(checkoutStepOnePage.getCurrentPageUrl(),checkoutStepOnePage.checkoutStepOnePageUrl,"user not directs to checkoutStepOnePage. Expected page url is :" + checkoutStepOnePage.checkoutStepOnePageUrl + "Actual current page url is :" + checkoutStepOnePage.getCurrentPageUrl());
        Assert.assertEquals(checkoutStepOnePage.getCheckOutStepOnePageTitle(),checkoutStepOnePage.getCheckoutStepOnePageUrlTitle,"Page Title is mismatched. expected" + checkoutStepOnePage.getCheckoutStepOnePageUrlTitle);
        logger.info("Successfully directs to checkout one page by clicking on checkout button");
    }

    private void assertContinueShoppingBtnAvailability(){
        boolean continueShoppingBtnAvailability = cartPage.isContinueShoppingBtnDisplay();
        if(continueShoppingBtnAvailability == true){
            Assert.assertTrue(true,"Continue Shopping button available in cart page");
        }else
            Assert.assertFalse(false,"Continue Shopping button is not available in cart page");
    }

    private void clickOnContinueShoppingBtn(){
        cartPage.clickOnContinueShoppingBtn();
        logger.debug("Clicked on continue shopping button in cart page");
    }

    private void continueShoppingBtnAssertion(){
        Assert.assertEquals(inventoryPage.getCurrentPageUrl(), inventoryPage.inventoryPageUrl,"User not successfully directs to inventory page after clicked on continues shopping button on cart page");
        logger.info("User successfully directs to inventory page.");
    }
}
