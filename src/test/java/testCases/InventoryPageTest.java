package testCases;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObject.CartBadge;
import pageObject.InventoryPage;
import pageObject.Item1Page;
import utilities.CustomListener;
import utilities.TestCaseMetadata;

import java.lang.reflect.Method;
import java.util.List;
import java.util.NoSuchElementException;


@Listeners(CustomListener.class)
public class InventoryPageTest extends BaseClass{
    protected CartBadge cartBadge;
    protected InventoryPage inventoryPage;
    protected Item1Page item1Page;

    int cartBadgeValue;
    private int cartBadgeBeforeValue;
    private int cartBadgeAfterValue;

    private int totalAddToCartButtonsBefore;
    private int totalAddToCartButtonsAfter;

    private int TotalNoOfProductsAvailable;

    @BeforeMethod
    public void setUp(ITestContext context) {
        context.setAttribute("module", "inventory");
        cartBadge = new CartBadge(BaseClass.getDriver());
        inventoryPage = new InventoryPage(BaseClass.getDriver());
        item1Page = new Item1Page(BaseClass.getDriver());
    }

    private String getTestCaseId() {
        try {
            String methodName = new Throwable().getStackTrace()[1].getMethodName();
            // Use reflection to find the method
            Method method = this.getClass().getMethod(methodName);
            Test testAnnotation = method.getAnnotation(Test.class);
            return testAnnotation.testName();
        } catch (NoSuchMethodException e) {
            logger.error("Failed to fetch test case ID from @Test annotation.", e);
            return "UnknownTestCaseID";
        }
    }
    private void verifyItemsAvailabilityBeforeFirstItemAdd(){
        boolean isBadgeDisplayed = cartBadge.isShoppingCartBadgeDisplay();
        if (isBadgeDisplayed) {
            logger.info("Shopping cart badge is displayed. Already added items available in cart.");
            Assert.assertEquals(true,false,"Expected cart is initially empty and not display, before add first item. but, cart badge is displayed. Pre condition not met.");
        } else {
            cartBadgeBeforeValue = 0;
            logger.info("Shopping cart badge is not displayed initially.");
            logger.info("Assign default value (0) to cartBadge. Total items in cart before adding a product: " + cartBadgeBeforeValue);
        }
    }

    private void assertProductsAddToCartButtonsDisplay(){
        boolean productDisplayStatus = inventoryPage.isAddToCartBtnDisplay();
        Assert.assertEquals(productDisplayStatus,true,"'Add to Cart' buttons are not displayed.");
        logger.info("'Add to Cart' button is displayed. No of Add to Cart buttons displayed:" + inventoryPage.countDisplayedAddToCartButtons());
    }

    public void addProductToCart(int productIndex) {
        inventoryPage.clickAddToCartButtonByIndex(productIndex);
        logger.info("Clicked on product's Add to Cart button. Product index is :" +productIndex);
    }

    public void assertAddToCartButtonChangeToRemove(){
        Assert.assertTrue(inventoryPage.isRemoveBtnDisplay(),
                "Add to Cart button not change to Remove button");
    }

    private void verifyCartBadgeUpdate(){
        Assert.assertTrue(cartBadge.isShoppingCartBadgeDisplay(),
                "Shopping cart badge is not displayed after adding an item.");
        cartBadgeAfterValue = cartBadge.getNoOfItemsDisplayedOnShoppingCartBadge();
        Assert.assertTrue(cartBadgeAfterValue > cartBadgeBeforeValue,
                "Cart badge value did not increase after adding an item. CartBadge before value is: "+cartBadgeBeforeValue+ "CartBadge after value is:" +cartBadgeAfterValue);
        logger.info("Product successfully added to cart. Total items in cart: " + cartBadgeAfterValue);
    }

    private int getCartBadgeValue(){
        cartBadgeValue = cartBadge.getNoOfItemsDisplayedOnShoppingCartBadge();
        logger.info("Current cartBadge value is: " + cartBadgeValue);
        return cartBadgeValue;
    }


    private void assertCartBadgeValueIncrement(int cartBadgeAfterValue , int cartBadgeBeforeValue){
            Assert.assertEquals(cartBadgeAfterValue > cartBadgeBeforeValue,true,"CartBadge value has not been increased");
            logger.info("CartBadge value has been increased. cartBadge before value is:" + cartBadgeBeforeValue+ " CartBadge After value is:"+cartBadgeAfterValue);
    }

    private void assertCartBadgeValueDecrease(int cartBadgeAfterValue , int cartBadgeBeforeValue){
        Assert.assertEquals(cartBadgeAfterValue < cartBadgeBeforeValue,true,"Cart Badge value not decreased");
        logger.info("Cart Badge value decrease after remove products. CartBadge Before value is : " + cartBadgeBeforeValue + ", CartBadge After value is: " +cartBadgeAfterValue);
    }

    private void addAllProductsToCart(){
        inventoryPage.clickOnAvailableAllAddToCartButtonElementsInInventoryPage();
        logger.info("Clicked on all add to cart buttons available in inventory page");
    }

    private void removeAddedProductFromCart(int productIndex){
        inventoryPage.clickOnProductRemoveButton(productIndex);
        logger.info("Clicked on product's remove button. product Index is: " + productIndex);
    }

    private void removeAllAddedProducts(){
        inventoryPage.clickOnAvailableAllRemoveButtons();
        logger.info("Clicked on available all remove buttons");
    }

    private int getNoOfAddToCartButtons(){
        TotalNoOfProductsAvailable = inventoryPage.countDisplayedAddToCartButtons();
        return TotalNoOfProductsAvailable;
    }

    private boolean isProductsAvailableInInventoryPage(){
        return inventoryPage.isItemListEmpty();
    }

    private void assertProductsAvailabilityInInventoryPage(){
        Assert.assertEquals(isProductsAvailableInInventoryPage(),true,"Products are not available in Inventory page.");
    }

    private List<String> getTotalAvailableItemListByItemName(){
        List<String> list1 = inventoryPage.getTotalAvailableItemListByItemName();
        return list1;
    }

    private List<String> getAToZSortedProductList(){
        List<String> list2 = inventoryPage.getAToZSortedItemList();
        return list2;
    }

    private void assertProductListAToZSort(List<String>list1, List<String>list2){
        boolean ListInAtoZOrder = list1.equals(list2);
        Assert.assertEquals(ListInAtoZOrder,true,"Default format is not A to Z format");
        logger.info("Products are sorted into A to Z format successfully");
    }

    private List<String> getExpectedZToASortedList(){
      List<String> ExpectedList = inventoryPage.getZToASortedList();
      return ExpectedList;
    }

    private List<String> getExpectedAToZSortedList(){
        List<String> ExpectedList = inventoryPage.getAToZSortedItemList();
        return ExpectedList;
    }

    private List<Double> getTotalAvailableItemPriceListByItemPrice(){
       List<Double> ExpectedList = inventoryPage.getTotalAvailableItemPriceListByItemPrice();
       return ExpectedList;
    }

    private List<Double> getExpectedItemListFromLowToHighPrice(){
        List<Double> ExpectedPriceList = inventoryPage.getItemListFromLowToHighPrice();
        return ExpectedPriceList;
    }

    private List<Double> getExpectedProductListFromHighToLowPrice(){
        List<Double> ExpectedPriceList = inventoryPage.getItemListFromHighToLowPrice();
        return ExpectedPriceList;
    }

    private boolean compareDisplayedListAndExpectedList(List<String> ActualList, List<String> ExpectedList){
        boolean IsSorted = ActualList.equals(ExpectedList);
        return IsSorted;
    }

    private boolean compareDisplayedListAndExpectedListByPrice(List<Double> ActualList, List<Double> ExpectedList){
        boolean IsSorted = ActualList.equals(ExpectedList);
        return IsSorted;
    }

    private void isDropDownBtnAvailable(){
       boolean dropDownBtnAvailability = inventoryPage.isDropDownBtnAvailable();
       Assert.assertEquals(dropDownBtnAvailability,true,"dropdown button not available in inventory page");
    }

    private void sortProductsFromZToAByName(){
        inventoryPage.sortOrFilterProductsFromZtoAByName();
    }

    private void sortProductsFromAToZByName(){
        logger.info("Selecting Name(A to Z) option from drop down");
        inventoryPage.sortOrFilterProductsFromAtoZByName();
    }

    private void sortProductsFromLowPriceToHighPrice(){
        inventoryPage.sortOrFilterProductsFromLowPriceToHighPrice();
    }

    private void sortProductsFromHighPriceToLowPrice(){
        inventoryPage.sortOrFilterProductsFromLowPriceToHighPrice();
    }

    private void assertProductsAvailabilityOnInventoryPage(int totalNoOfProductsAvailable){
        if(totalNoOfProductsAvailable != 0){
            logger.info("Products are available in Inventory page");
            logger.info("Total no of products available in Inventory page is : " + totalNoOfProductsAvailable);
        }
        logger.error("Products are not available in Inventory page. Can not further proceed the execution");
    }

    private void assertRemoveOneProduct(int totalAddToCartButtonsBefore,int totalAddToCartButtonsAfter) {
        if ((totalAddToCartButtonsAfter > totalAddToCartButtonsBefore)
                && (totalAddToCartButtonsAfter == (totalAddToCartButtonsBefore + 1))) {
            logger.info("Total no. of Add to Cart buttons Before removing product: " + totalAddToCartButtonsBefore +
                    ", Total no. of Add to Cart buttons After removing product: " + totalAddToCartButtonsAfter);
            Assert.assertTrue(true, "Successfully changed 'Remove' button to 'Add to Cart' button.");
        } else {
            logger.error("Expected button change did not occur. Total no. of Add to Cart buttons Before: "
                    + totalAddToCartButtonsBefore + ", After: " + totalAddToCartButtonsAfter);
            Assert.fail("Remove button did not correctly change to 'Add to Cart' button.");
        }
    }
    
    private void assertRemoveAllProducts(int totalAddToCartButtonsBefore, int totalAddToCartButtonsAfter){
        if ((totalAddToCartButtonsAfter < totalAddToCartButtonsBefore)
                && (totalAddToCartButtonsAfter == (totalAddToCartButtonsBefore - inventoryPage.maxProductsInInventoryPage))) {
            logger.info("Total no. of Add to Cart buttons Before removing product: " + totalAddToCartButtonsBefore +
                    ", Total no. of Add to Cart buttons After removing product: " + totalAddToCartButtonsAfter);
            Assert.assertTrue(true, "Successfully changed 'Remove' button to 'Add to Cart' button.");
        } else {
            logger.error("Expected button change did not occur. Total no. of Add to Cart buttons Before: "
                    + totalAddToCartButtonsBefore + ", After: " + totalAddToCartButtonsAfter);
            Assert.fail("Remove button did not correctly change to 'Add to Cart' button.");
        }
    }

    private void clickOnProductImage(int index){
        inventoryPage.clickOnProductImageByIndex(index);
    }

    private void clickOnProductName(int index){
        inventoryPage.clickOnProductNameByIndex(index);
    }

    private void clickOnBackToProductBtn(){
        item1Page.clickOnBackToProductsBtn();
        logger.info("click on back to product button.");
    }

    private void assertMoveToProductIndividualPage(){
        String currentPageUrl = item1Page.getCurrentPageUrl();
        String expectedUrl = item1Page.productIndex1ItemPage;
        Assert.assertEquals(currentPageUrl,expectedUrl,"Current Page Url is not match to expected page Url." +
                "Current page url is :" +currentPageUrl + ", expected page url is: " +expectedUrl);
        logger.info("Current Page Url is match to expected page Url.");
    }

    private void assertInventoryPageUrl(){
        String actualPageUrl = inventoryPage.getCurrentPageUrl();
        String expectedPageUrl = inventoryPage.inventoryPageUrl;
        Assert.assertEquals(actualPageUrl,expectedPageUrl,"Actual page url is mismatched with expected page url");
    }

    @Test(testName = "TC_2.1",
          dependsOnMethods = "testCases.LoginTest.verifyUserLoginTest",
          priority = 1,
          groups = {"Functional", "E2E"})
    public void verifyUserAddOneProductToCart(ITestContext context) {
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
        try {
            verifyItemsAvailabilityBeforeFirstItemAdd();
            assertProductsAddToCartButtonsDisplay();
            addProductToCart(1);
            assertAddToCartButtonChangeToRemove();
            verifyCartBadgeUpdate();
        } catch (Exception e) {
            logger.error("Exception in test case " + testCaseID + ": ", e);
            Assert.fail("Test case " + testCaseID + " failed due to an exception: " + e.getMessage());
        }
        logger.info("*********** " + testCaseID + " test execution finished ************");
    }

    @Test(testName = "TC_2.2", dependsOnMethods = "testCases.inventoryPageTest.verifyUserAddOneProductToCart", priority = 2, groups = {"Functional"})
    public void verifyUserAddSecondProductToCart(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
        try{
            cartBadgeBeforeValue =  getCartBadgeValue();
            if(cartBadgeBeforeValue != 1) {
                Assert.fail("Prerequisite not met: Exactly 1 item should be in the cart before adding the second product.");
                logger.error("Cart badge value before test execution: " + cartBadgeBeforeValue);
                return;
            }
                assertProductsAddToCartButtonsDisplay();
                addProductToCart(2);
                assertAddToCartButtonChangeToRemove();
                cartBadgeAfterValue =  getCartBadgeValue();
                assertCartBadgeValueIncrement(cartBadgeBeforeValue,cartBadgeAfterValue);
                verifyCartBadgeUpdate();
        }
        catch (Exception e) {
            Assert.fail("Failed to add item to cart: " + e.getMessage());
            logger.error("Exception encountered: ", e);
        }
        logger.info("************" +testCaseID+ "Test case execution finished. ***********");
    }

    @Test(testName = "TC_2.3", dependsOnMethods = "testCases.LoginTest.verifyUserLoginTest",priority = 5)
    public void verifyUserAddAllProductsInInventoryPageToCart(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
        try{
        cartBadgeBeforeValue = getCartBadgeValue();
        assertProductsAddToCartButtonsDisplay();
        addAllProductsToCart();
        cartBadgeAfterValue= getCartBadgeValue();
        assertCartBadgeValueIncrement(cartBadgeBeforeValue,cartBadgeAfterValue);
        }catch(Exception e){
            Assert.fail("Failed to add all items into cart: " + e.getMessage());
            logger.error("Exception encountered: ", e);
        }
        logger.info("*********" +testCaseID+ "test case execution finished. ************");
    }

    @Test(testName = "TC_2.4",dependsOnMethods = "tests.AddToCartTest.verifyUserAddOneProductToCart",priority = 3)
    public void verifyUserRemoveAddedItemFromCartByClickOnRemoveBtnOnInventoryPage(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
        try{
            cartBadgeBeforeValue = getCartBadgeValue();
            totalAddToCartButtonsBefore = getNoOfAddToCartButtons();
            removeAddedProductFromCart(1);
            cartBadgeAfterValue = getCartBadgeValue();
            totalAddToCartButtonsAfter = getNoOfAddToCartButtons();
            assertRemoveOneProduct(totalAddToCartButtonsBefore,totalAddToCartButtonsAfter);
            }catch(Exception e){
            logger.info("Failed to remove added product from cart badge");
            logger.error("Exception encountered: ", e);
        }
        logger.info("**********" +testCaseID+ "Test case execution finished. ***********");
    }

    @Test(testName = "TC_2.5", dependsOnMethods = "tests.AddToCartTest.verifyUserAddAllProductsInInventoryPageToCart", priority = 4)
    public void verifyUserRemoveOneItemFromCartByClickOnRemoveBtnOnInventoryPage(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
        try{
            cartBadgeBeforeValue = getCartBadgeValue();
            totalAddToCartButtonsBefore = getNoOfAddToCartButtons();
            removeAddedProductFromCart(2);
            cartBadgeAfterValue = getCartBadgeValue();
            totalAddToCartButtonsAfter = getNoOfAddToCartButtons();
            assertRemoveOneProduct(totalAddToCartButtonsBefore,totalAddToCartButtonsAfter);
        }catch(Exception e){
            logger.info("Failed to remove added product from cart badge");
            logger.error("Exception encountered: ", e);
        }
        logger.info("**********" +testCaseID+ "Test case execution finished. ***********");
    }

    @Test(testName = "TC_2.6", dependsOnMethods = "tests.AddToCartTest.verifyUserAddAllProductsInInventoryPageToCart", priority = 4)
    public void verifyUserRemoveAllProductsFromCartByClickOnRemoveButtonsOnInventoryPage(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
        try{
            cartBadgeBeforeValue = getCartBadgeValue();
            totalAddToCartButtonsBefore = getNoOfAddToCartButtons();
            removeAllAddedProducts();
            cartBadgeAfterValue = getCartBadgeValue();
            totalAddToCartButtonsAfter = getNoOfAddToCartButtons();
            assertRemoveAllProducts(totalAddToCartButtonsBefore, totalAddToCartButtonsAfter);
            assertCartBadgeValueDecrease(cartBadgeAfterValue,cartBadgeBeforeValue);
        }catch(Exception e){
            logger.info("Failed to remove added product from cart badge");
            logger.error("Exception encountered: ", e);
        }
        logger.info("**********" +testCaseID+ "Test case execution finished. ***********");
    }

    @Test(testName = "TC_2.7", dependsOnMethods = "tests.AddToCartTest.verifyUserAddAllProductsInInventoryPageToCart", priority = 5)
    public void verifyUserAddItemByClickOnItemImage(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
        try{
            cartBadgeBeforeValue = getCartBadgeValue();
            if(cartBadgeBeforeValue > 0){
                removeAllAddedProducts();
                cartBadgeAfterValue = getCartBadgeValue();
                assertRemoveAllProducts(cartBadgeBeforeValue,cartBadgeAfterValue);
            }
                clickOnProductImage(1);
                assertMoveToProductIndividualPage();
                addProductToCart(1);
                cartBadgeAfterValue = getCartBadgeValue();
                assertCartBadgeValueIncrement(cartBadgeBeforeValue,cartBadgeAfterValue);
                assertAddToCartButtonChangeToRemove();
                verifyCartBadgeUpdate();
                item1Page.clickOnBackToProductsBtn();
                //click on back to products link
                logger.info("click on back to product button.");
                item1Page.clickOnBackToProductsBtn();
                assertInventoryPageUrl();
        }catch(Exception e){
            logger.info("Failed to remove one product from cart badge");
            logger.error("Exception encountered: ", e);
        }
        logger.info("**********" +testCaseID+ "Test case execution finished. ***********");
    }

    @Test(testName = "TC_2.8",priority = 7)
    public void verifyUserAddProductByClickingOnImageAndRemoveProduct(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
        try {
            cartBadgeBeforeValue = getCartBadgeValue();
            if(cartBadgeBeforeValue > 0){
                removeAllAddedProducts();
                cartBadgeAfterValue = getCartBadgeValue();
                assertRemoveAllProducts(cartBadgeBeforeValue,cartBadgeAfterValue);
            }
            clickOnProductImage(1);
            assertMoveToProductIndividualPage();
            addProductToCart(1);
            cartBadgeAfterValue = getCartBadgeValue();
            assertCartBadgeValueIncrement(cartBadgeBeforeValue,cartBadgeAfterValue);
            assertAddToCartButtonChangeToRemove();
            verifyCartBadgeUpdate();
            cartBadgeBeforeValue = getCartBadgeValue();
            totalAddToCartButtonsBefore = getNoOfAddToCartButtons();
            removeAddedProductFromCart(1);
            cartBadgeAfterValue = getCartBadgeValue();
            totalAddToCartButtonsAfter = getNoOfAddToCartButtons();
            assertRemoveOneProduct(totalAddToCartButtonsBefore, totalAddToCartButtonsAfter);
            assertCartBadgeValueDecrease(cartBadgeAfterValue,cartBadgeBeforeValue);
            item1Page.clickOnBackToProductsBtn();
            //click on back to products link
            logger.info("click on back to product button.");
            item1Page.clickOnBackToProductsBtn();
            assertInventoryPageUrl();
        }catch(Exception e){
            logger.info("Failed to remove one product from cart badge by click on image");
            logger.error("Exception encountered: ", e);
        }
        logger.info("**********" +testCaseID+ "Test case execution finished. ***********");
    }

@Test(testName = "TC_2.9",priority = 7)
    public void verifyUserAddProductByClickOnProductName(ITestContext context) {
    String testCaseID = getTestCaseId();
    String module = context.getAttribute("module").toString();
    String scenario = TestCaseMetadata.getScenario(testCaseID, module);
    String description = TestCaseMetadata.getDescription(testCaseID, module);
    String priority = TestCaseMetadata.getPriority(testCaseID, module);
    String severity = TestCaseMetadata.getSeverity(testCaseID, module);
    logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
    logger.info("*********** " + testCaseID + " " + description + " started ************");
        try {
            cartBadgeBeforeValue = getCartBadgeValue();
            if(cartBadgeBeforeValue > 0){
                removeAllAddedProducts();
                cartBadgeAfterValue = getCartBadgeValue();
                assertRemoveAllProducts(cartBadgeBeforeValue,cartBadgeAfterValue);
            }
            cartBadgeBeforeValue = getCartBadgeValue();
            clickOnProductName(1);
            assertMoveToProductIndividualPage();
            addProductToCart(1);
            assertAddToCartButtonChangeToRemove();
            cartBadgeAfterValue = getCartBadgeValue();
            assertCartBadgeValueIncrement(cartBadgeAfterValue,cartBadgeBeforeValue);
            clickOnBackToProductBtn();
            assertInventoryPageUrl();
        }catch(Exception e){
            logger.info("Failed to add product by filtering product by click on product Name");
            logger.error("Exception encountered: ", e);
        }
    logger.info("**********" +testCaseID+ "Test case execution finished. ***********");
    }

    @Test(testName = "TC_2.10", priority = 8)
    public void verifyApplicationDefaultProductSortMethodIsAToZ(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
        try{
            TotalNoOfProductsAvailable = getNoOfAddToCartButtons();
            assertProductsAvailabilityOnInventoryPage(TotalNoOfProductsAvailable);
            List<String> list1 = getTotalAvailableItemListByItemName();
            List<String> list2 = getAToZSortedProductList();
            assertProductListAToZSort(list1, list2);
            }catch(Exception e){
            logger.info("Failed to add product by filtering product by click on product Name");
            logger.error("Exception encountered: ", e);
        }
        logger.info("**********" +testCaseID+ "Test case execution finished. ***********");
    }

    @Test(testName = "TC_2.11", priority = 9)
    public void verifyUserSortProductsFromZToA(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
        try{
            TotalNoOfProductsAvailable = getNoOfAddToCartButtons();
            assertProductsAvailabilityOnInventoryPage(TotalNoOfProductsAvailable);
            List<String> ActualList = getTotalAvailableItemListByItemName();
            List<String> ExpectedList = getExpectedZToASortedList();
            boolean sorted =  compareDisplayedListAndExpectedList(ActualList,ExpectedList);
            if(!sorted){
                logger.info("Products are not already in Z to A format");
                isDropDownBtnAvailable();
                sortProductsFromZToAByName();
                ActualList = getTotalAvailableItemListByItemName();
                sorted =  compareDisplayedListAndExpectedList(ActualList,ExpectedList);
            }else{
                logger.info("Products are already in Z to A format");
            }
        }catch(Exception e){
            logger.info("Failed to Sort products from Z to A format.");
            logger.error("Exception encountered: ", e);
        }
        logger.info("**********" +testCaseID+ "Test case execution finished. ***********");
    }

    @Test(testName = "TC_2.12", priority = 10,dependsOnMethods ="verifyUserSortProductsFromZToA" )
    public void verifyUserSortProductsFromAtoZ(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
        try{
            TotalNoOfProductsAvailable = getNoOfAddToCartButtons();
            assertProductsAvailabilityOnInventoryPage(TotalNoOfProductsAvailable);
            List<String> ActualList = getTotalAvailableItemListByItemName();
            //logger.info("Available Products before sorting format into A to Z format:" + ActualList);
            List<String> ExpectedList = getExpectedAToZSortedList();
            boolean sorted =  compareDisplayedListAndExpectedList(ActualList,ExpectedList);
            if(!sorted){
                isDropDownBtnAvailable();
                sortProductsFromAToZByName();
                ActualList = getTotalAvailableItemListByItemName();
                sorted =  compareDisplayedListAndExpectedList(ActualList,ExpectedList);
            }else  logger.info("Products are already in Name(A to Z) format");
        }catch(Exception e){
            logger.info("Failed to Sort products from A to Z format.");
            logger.error("Exception encountered: ", e);
        }
        logger.info("**********" +testCaseID+ "Test case execution finished. ***********");
    }

    @Test(testName = "TC_2.13", priority = 11)
    public void verifyUserSortProductsFromLowToHighPrice(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
        try{
            TotalNoOfProductsAvailable = getNoOfAddToCartButtons();
            assertProductsAvailabilityOnInventoryPage(TotalNoOfProductsAvailable);
            List<Double> ActualList = getTotalAvailableItemPriceListByItemPrice();
            //logger.info("Available Products before sorting format into Low Price to High Price format:" + ActualList);
            List<Double> ExpectedList = getExpectedItemListFromLowToHighPrice();
            boolean sorted = compareDisplayedListAndExpectedListByPrice(ActualList,ExpectedList);
            if(!sorted){
                isDropDownBtnAvailable();
                sortProductsFromLowPriceToHighPrice();
                ActualList = getTotalAvailableItemPriceListByItemPrice();
                sorted = compareDisplayedListAndExpectedListByPrice(ActualList,ExpectedList);
            }else logger.info("Products are already in low price to High format");
        }catch(Exception e){
            logger.info("Failed to Sort products from low price to High format.");
            logger.error("Exception encountered: ", e);
        }
        logger.info("**********" +testCaseID+ "Test case execution finished. ***********");
    }

    @Test(testName = "TC_2.14", priority = 12)
    public void verifyUserSortProductsFromHighToLowPrice(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");
        try{
            TotalNoOfProductsAvailable = getNoOfAddToCartButtons();
            assertProductsAvailabilityOnInventoryPage(TotalNoOfProductsAvailable);
            List<Double> ActualList = getTotalAvailableItemPriceListByItemPrice();
            //logger.info("Available Products before sorting format into Low Price to High Price format:" + ActualList);
            List<Double> ExpectedList = getExpectedProductListFromHighToLowPrice();
            boolean sorted = compareDisplayedListAndExpectedListByPrice(ActualList,ExpectedList);
            if(!sorted){
                isDropDownBtnAvailable();
                sortProductsFromHighPriceToLowPrice();
                ActualList = getTotalAvailableItemPriceListByItemPrice();
                sorted = compareDisplayedListAndExpectedListByPrice(ActualList,ExpectedList);
            }else logger.info("Products are already in High price to Low format");
        }catch(Exception e){
            logger.info("Failed to Sort products from A to Z format.");
            logger.info("TC_2.13 Test case failed");
            logger.error("Exception encountered: ", e);
        }
        logger.info("**********" +testCaseID+ "Test case execution finished. ***********");
    }


}
