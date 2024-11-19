package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.CartBadge;
import pageObject.InventoryPage;
import pageObject.Item1Page;
import java.util.List;
import java.util.NoSuchElementException;


public class InventoryPageTest extends LoginTest{
    protected CartBadge cartBadge;
    protected InventoryPage inventoryPage;
    protected Item1Page item1Page;
    private int cartBadgeValueBefore;
    private int cartBadgeValueAfter;

    @BeforeMethod
    public void setUpInventoryPageTest(){
        cartBadge = new CartBadge(driver);
        inventoryPage = new InventoryPage(driver);
        item1Page = new Item1Page(driver);
    }

    @Test(dependsOnMethods = "testCases.LoginTest.verifyUserLoginTest",priority = 0)
    public void verifyUserAddOneProductToCart(){
        logger.info("*********** TC_2.1 Validate that user can successfully add any item in index.html page into own cart Started ************");
        try {
            // Initialize CartBadge object and check if the cart is empty initially
            cartBadge = new CartBadge(driver);

            if(cartBadge.isShoppingCartBadgeDisplay() == true){
                logger.warn("Shopping cart badge is displayed. Already added items available in cart");
                Assert.assertEquals(true,false,"Expected false from isShoppingCartBadgeDisplay() method.Actual is true");
            }if(cartBadge.isShoppingCartBadgeDisplay() == false){
                cartBadgeValueBefore = 0;
                logger.info("Shopping cart badge is not displayed initially");
                logger.info("Assign default value (0) to cartBadge. Total items in cart before adding a product: " + cartBadgeValueBefore);
            }
            // Add Product1 to the cart
            Assert.assertTrue(inventoryPage.isProduct1AddToCartBtnDisplay(), "Product1 AddToCart button is not visible.Expected value is: true. Actual value is: " + inventoryPage.isProduct1AddToCartBtnDisplay());
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
            logger.info("Previously added products not available");
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

    @Test(dependsOnMethods = "tests.AddToCartTest.verifyUserAddAllProductsInInventoryPageToCart", priority = 5)
    public void verifyUserAddItemByClickOnItemImage(){
        logger.info("********** TC_TC_2.6 Validate that user successfully add item to cart by following below flow. \n" +
                "\n" +
                "Click on selected Item image >>  Click on Add to cart button >> Click on Back to Products Link.************");
        try{
            logger.info("Check already added items are available.");
            if(inventoryPage.isAlreadyAddedItemsAvailable() == true){
                logger.info("Already added items are available in cart. Item count before remove :" + inventoryPage.getAvailableRemoveBtnCount());
                //remove added items
                logger.info("Already added items remove process is started.");
                inventoryPage.clickOnAvailableAllRemoveButtons();
                int AfterRemove = inventoryPage.getAvailableRemoveBtnCount();
                logger.info("Item count after removed :" + inventoryPage.getAvailableRemoveBtnCount());
                Assert.assertEquals(AfterRemove,0,"Already added items are not successfully removed.");
                //check item count before cart
                int cartBadgeValueBefore = 0;
                logger.info("Total Products available in cart before add items : " + cartBadgeValueBefore);
                logger.info("Add product into cart is started.");
                //add item by clicking on image
                inventoryPage.clickOnProduct1Image();
                logger.info("Clicked on product image.");
                Assert.assertEquals(inventoryPage.getCurrentPageUrl(),"https://www.saucedemo.com/inventory-item.html?id=4","Particular item not filtered and not displayed on same single window/page ");
                //check item count in cartBadge after add item to cart
                int cartBadgeValueAfter = cartBadge.getNoOfItemsDisplayedOnShoppingCartBadge();
                logger.info("Total Products available in cart After add items : " + cartBadgeValueAfter);
                Assert.assertEquals(cartBadgeValueAfter,(cartBadgeValueBefore + 1),"cartBade not increased by 1.");
                //check add to cart button change to Remove btn after click on add to cart button
                Assert.assertEquals(inventoryPage.isSauceLabsBackPackRemoveBtnDisplay(),true,"Add to cart button not changed to Remove button after adding a item into a cart.");
                //click on back to products link
                logger.info("click on back to product button.");
                item1Page.clickOnBackToProductsBtn();
                Assert.assertEquals(inventoryPage.getCurrentPageUrl(),"https://www.saucedemo.com/inventory.html","Page not changed to inventory.html (products) page");
            }
         }catch(Exception e){
            logger.info("Failed to remove one product from cart badge");
            logger.error("Exception encountered: ", e);
        }
        logger.info("********** TC_2.6 Test case execution finished. ***********");
    }

    @Test(priority = 6)
    public void verifyUserAddProductByClickingOnImageAndRemoveProduct(){
        logger.info(" *********** TC_2.7 Validate that user successfully add item to cart by following below flow. \n" +
                "\n" +
                "Click on selected Item image >>  Click on Add to cart button >> Click on Back to Products Link  *****************");
        try {
            if (inventoryPage.getTotalNoOfAvailableItems() > 0) {
                if(inventoryPage.isAlreadyAddedItemsAvailable() == true){
                    logger.info("Already added items are available in cart. Item count before remove :" + inventoryPage.getAvailableRemoveBtnCount());
                    //remove added items
                    logger.info("Already added items remove process is started.");
                    inventoryPage.clickOnAvailableAllRemoveButtons();
                    int AfterRemove = inventoryPage.getAvailableRemoveBtnCount();
                    logger.info("Item count after removed :" + inventoryPage.getAvailableRemoveBtnCount());
                    Assert.assertEquals(AfterRemove,0,"Already added items are not successfully removed.");
                    //check item count before cart
                    int cartBadgeValueBefore = 0;
                    logger.info("Total Products available in cart before add items : " + cartBadgeValueBefore);
                    logger.info("Add product into cart is started.");
                    //add item by clicking on image
                    inventoryPage.clickOnProduct1Image();
                    logger.info("Clicked on product image.");
                    Assert.assertEquals(inventoryPage.getCurrentPageUrl(),"https://www.saucedemo.com/inventory-item.html?id=4","Particular item not filtered and not displayed on same single window/page ");
                    //check item count in cartBadge after add item to cart
                    int cartBadgeValueAfter = cartBadge.getNoOfItemsDisplayedOnShoppingCartBadge();
                    logger.info("Total Products available in cart After add items : " + cartBadgeValueAfter);
                    Assert.assertEquals(cartBadgeValueAfter,(cartBadgeValueBefore + 1),"cartBade not increased by 1.");
                    //check add to cart button change to Remove btn after click on add to cart button
                    Assert.assertEquals(inventoryPage.isSauceLabsBackPackRemoveBtnDisplay(),true,"Add to cart button not changed to Remove button after adding a item into a cart.");
                    //click on Remove button
                    logger.info("Click on remove button.");
                    inventoryPage.clickOnSauceLabsBackPackRemoveBtn();
                    Assert.assertEquals(inventoryPage.isProduct1AddToCartBtnDisplay(),true,"Remove button not change to Add to cart button.");
                    //click on back to products link
                    logger.info("click on back to product button.");
                    item1Page.clickOnBackToProductsBtn();
                    Assert.assertEquals(inventoryPage.getCurrentPageUrl(),"https://www.saucedemo.com/inventory.html","Page not changed to inventory.html (products) page");
                    logger.info("TC_2.7 Test case Passed");
                    }
                logger.info("Already added items are not available.");
                //check item count before cart
                int cartBadgeValueBefore = 0;
                logger.info("Total Products available in cart before add items : " + cartBadgeValueBefore);
                logger.info("Add product into cart is started.");
                //add item by clicking on image
                inventoryPage.clickOnProduct1Image();
                logger.info("Clicked on product image.");
                Assert.assertEquals(inventoryPage.getCurrentPageUrl(),"https://www.saucedemo.com/inventory-item.html?id=4","Particular item not filtered and not displayed on same single window/page ");
                //check item count in cartBadge after add item to cart
                int cartBadgeValueAfter = cartBadge.getNoOfItemsDisplayedOnShoppingCartBadge();
                logger.info("Total Products available in cart After add items : " + cartBadgeValueAfter);
                Assert.assertEquals(cartBadgeValueAfter,(cartBadgeValueBefore + 1),"cartBade not increased by 1.");
                //check add to cart button change to Remove btn after click on add to cart button
                Assert.assertEquals(inventoryPage.isSauceLabsBackPackRemoveBtnDisplay(),true,"Add to cart button not changed to Remove button after adding a item into a cart.");
                //click on Remove button
                logger.info("Click on remove button.");
                inventoryPage.clickOnSauceLabsBackPackRemoveBtn();
                Assert.assertEquals(inventoryPage.isProduct1AddToCartBtnDisplay(),true,"Remove button not change to Add to cart button.");
                //click on back to products link
                logger.info("click on back to product button.");
                item1Page.clickOnBackToProductsBtn();
                Assert.assertEquals(inventoryPage.getCurrentPageUrl(),"https://www.saucedemo.com/inventory.html","Page not changed to inventory.html (products) page");
                logger.info("TC_2.7 Test case Passed");
            }
            logger.info("Items are not available in Inventory.html page.");
        }catch (Exception e){
            logger.info("Failed to add item by clicking on item image and remove item.");
            logger.error("Exception encountered: ", e);
        }
        logger.info("*********** TC_2.7 Test case execution finished. ************");
    }

@Test(priority = 7)
    public void verifyUserAddProductByClickOnProductName() {
    logger.info("*********** TC_2.8 Validate that user successfully add item to cart by following below flow. \n" +
            "\n" +
            "Click on selected Item name >>  Click on Add to cart button >> Click on Back to Products Link ***************");
        try {
        //Check Product are available in Inventory Page.
            if (!(inventoryPage.isItemListEmpty())) {
                if (inventoryPage.isAlreadyAddedItemsAvailable() == true) {
                    logger.info("Already added items are available in cart. Item count before remove :" + inventoryPage.getAvailableRemoveBtnCount());
                    //remove added items
                    logger.info("Already added items remove process is started.");
                    inventoryPage.clickOnAvailableAllRemoveButtons();
                    int AfterRemove = inventoryPage.getAvailableRemoveBtnCount();
                    logger.info("Item count after removed :" + inventoryPage.getAvailableRemoveBtnCount());
                    Assert.assertEquals(AfterRemove, 0, "Already added items are not successfully removed.");
                    //click on product Name
                    cartBadgeValueBefore = 0;
                    logger.info("Click on Product1 title.");
                    inventoryPage.clickOnProduct1TitleLink();
                    Assert.assertEquals(inventoryPage.getCurrentPageUrl(), "https://www.saucedemo.com/inventory-item.html?id=4", "Particular item not filtered and not displayed on same single window/page ");
                    //check item count in cartBadge after add item to cart
                    int cartBadgeValueAfter = cartBadge.getNoOfItemsDisplayedOnShoppingCartBadge();
                    logger.info("Total Products available in cart After add items : " + cartBadgeValueAfter);
                    Assert.assertEquals(cartBadgeValueAfter, (cartBadgeValueBefore + 1), "cartBade not increased by 1.");
                    //check add to cart button change to Remove btn after click on add to cart button
                    Assert.assertEquals(inventoryPage.isSauceLabsBackPackRemoveBtnDisplay(), true, "Add to cart button not changed to Remove button after adding a item into a cart.");
                    //click on Back to products button
                    logger.info("click on back to product button.");
                    item1Page.clickOnBackToProductsBtn();
                    Assert.assertEquals(inventoryPage.getCurrentPageUrl(), "https://www.saucedemo.com/inventory.html", "Page not changed to inventory.html (products) page");
                    logger.info("TC_2.8 Test case Passed");
                }
                logger.info("Already added items are not available");
                cartBadgeValueBefore = 0;
                logger.info("Click on Product1 title.");
                inventoryPage.clickOnProduct1TitleLink();
                Assert.assertEquals(inventoryPage.getCurrentPageUrl(), "https://www.saucedemo.com/inventory-item.html?id=4", "Particular item not filtered and not displayed on same single window/page ");
                //check item count in cartBadge after add item to cart
                int cartBadgeValueAfter = cartBadge.getNoOfItemsDisplayedOnShoppingCartBadge();
                logger.info("Total Products available in cart After add items : " + cartBadgeValueAfter);
                Assert.assertEquals(cartBadgeValueAfter, (cartBadgeValueBefore + 1), "cartBade not increased by 1.");
                //check add to cart button change to Remove btn after click on add to cart button
                Assert.assertEquals(inventoryPage.isSauceLabsBackPackRemoveBtnDisplay(), true, "Add to cart button not changed to Remove button after adding a item into a cart.");
                //click on Back to products button
                logger.info("click on back to product button.");
                item1Page.clickOnBackToProductsBtn();
                Assert.assertEquals(inventoryPage.getCurrentPageUrl(), "https://www.saucedemo.com/inventory.html", "Page not changed to inventory.html (products) page");
                logger.info("TC_2.8 Test case Passed");
            }
        logger.info("Products are not available in Inventory page.");
        }catch(Exception e){
            logger.info("Failed to add item by clicking product title.");
            logger.error("Exception encountered: ", e);
        }
    logger.info("*********** TC_2.8 Test case execution finished. ************");
    }

    @Test(priority = 8)
    public void verifyApplicationDefaultProductSortMethodIsAToZ(){
        logger.info("************** TC_2.9 Started *************");
        logger.info("Test Case: Validate that application products displaying  default method is Name(A to Z) ");
        try{
            //check products are available in inventory page
            Assert.assertEquals(inventoryPage.isItemListEmpty(),false,"Products are not available in Inventory.html page");
            logger.info("Products are available in Inventory page.");
            //get available default product list
            logger.info("Available Products default sorting format: " +  inventoryPage.getTotalAvailableItemListByItemName());
            List<String> list1 = inventoryPage.getTotalAvailableItemListByItemName();
            //get excepted default sorting order
            List<String> list2 = inventoryPage.getAToZSortedItemList();
            //compare 2 lists
            boolean ListInAtoZOrder = list1.equals(list2);
            Assert.assertEquals(ListInAtoZOrder,true,"Default format is not A to Z format");
        }catch(Exception e){
            logger.info("Failed to check default format of products is A to Z format or not.");
            logger.error("Exception encountered: ", e);
        }
        logger.info("************ TC_2.9 Test case finished. ***************");
    }

    @Test(priority = 9)
    public void verifyUserSortProductsFromZToA(){
        logger.info("************** TC_2.11 Started *************");
        logger.info("Test Case: Validate that user sort/filter products From Z to A by Name.");
        try{
            //check products are available in inventory page
            Assert.assertEquals(inventoryPage.isItemListEmpty(),false,"Products are not available in Inventory.html page");
            logger.info("Products are available in Inventory page.");
            //get available item list
            List<String> list1 = inventoryPage.getTotalAvailableItemListByItemName();
            logger.info("Available Products default sorting format:" + list1);
            //check products are already in Z to A format
            List<String> ExpectedList = inventoryPage.getZToASortedList();
            //compare current display format and expected format
            boolean IsActualFormatZToA = ExpectedList.equals(list1);
            Assert.assertEquals(IsActualFormatZToA,false,"Products are already in Z to A format.");
            //change to Z to A format
            inventoryPage.sortOrFilterProductsFromZtoAByName();
            logger.info("Select Name(Z to A) from drop down menu");
            List<String> listAfterChaneToAToZ = inventoryPage.getTotalAvailableItemListByItemName();
            //compare list After click option Z to A and Expected
            IsActualFormatZToA = ExpectedList.equals(listAfterChaneToAToZ);
            Assert.assertEquals(IsActualFormatZToA,true,"Products are not sorted into Name(Z to A) format");
            logger.info("Products are sorted into Z to A format");
            logger.info("TC_2.10 Test Case Passed");
        }catch(Exception e){
            logger.info("Failed to Sort products from Z to A format.");
            logger.error("Exception encountered: ", e);
        }
        logger.info("************ TC_2.11 Test case finished. ***************");
    }


    @Test(priority = 10,dependsOnMethods ="verifyUserSortProductsFromZToA" )
    public void verifyUserSortProductsFromAtoZ(){
        logger.info("************** TC_2.10 Started *************");
        logger.info("Test Case: Validate that user sort/filter products From A to Z by Name.");
        try{
            //check products are available in inventory page
            Assert.assertEquals(inventoryPage.isItemListEmpty(),false,"Products are not available in Inventory.html page");
            logger.info("Products are available in Inventory page.");
            //get available item list
            List<String> list1 = inventoryPage.getTotalAvailableItemListByItemName();
            logger.info("Available Products before sorting format into A to Z format:" + list1);
            //Check Products are already in A to Z format or not
            List<String> list2 = inventoryPage.getAToZSortedItemList();
            //compare 2 lists
            logger.info("Compare products in A to Z format or not");
            boolean ListInAtoZOrder = list1.equals(list2);
            Assert.assertEquals(ListInAtoZOrder,false,"Products in A to Z sorting format.");
            logger.info("Products are not in A to Z sorting format");
            //Sort into A to Z format
            logger.info("Selecting Name(A to Z) option from drop down");
            inventoryPage.sortOrFilterProductsFromAtoZByName();
            List<String> listAfterSortFromAToZ = inventoryPage.getTotalAvailableItemListByItemName();
            logger.info("Verifying products are display as A to Z sorted format after select Name(A to Z) format");
            //compare Expected Format and Actual display after click on drop down option
            ListInAtoZOrder = list2.equals(listAfterSortFromAToZ);
            Assert.assertEquals(ListInAtoZOrder,true,"Products not sort into A to Z after select option Name(A to Z) from dropdown.)");
            logger.info("Products are displaying according to Name(A to Z) format");
            logger.info("TC_2.10 Test case passed");
        }catch(Exception e){
            logger.info("Failed to Sort products from A to Z format.");
            logger.info("TC_2.10 Test case failed");
            logger.error("Exception encountered: ", e);
        }
        logger.info("************ TC_2.10 Test case finished. ***************");
    }

    @Test(priority = 11)
    public void verifyUserSortProductsFromLowToHighPrice(){
        logger.info("************** TC_2.12 Started *************");
        logger.info("Test Case: Validate that user sort/filter products From Low to High price.");
        try{
            //check products are available in inventory page
            Assert.assertEquals(inventoryPage.isItemListEmpty(),false,"Products are not available in Inventory.html page");
            logger.info("Products are available in Inventory page.");
            //get available item list
            List<Double> list1 = inventoryPage.getTotalAvailableItemPriceListByItemPrice();
            logger.info("Available Products before sorting format into Low Price to High Price format:" + list1);
            //Check Products are already in Low Price to High Price format or not
            List<Double> ExpectedPriceList = inventoryPage.getItemListFromLowToHighPrice();
            boolean IsListInLowPriceToHigh = ExpectedPriceList.equals(list1);
            Assert.assertEquals(IsListInLowPriceToHigh,false,"Products are display according to Low to High price format");
            //select Price(Low to High)
            logger.info("Selecting Price(Low to High) option from drop down");
            inventoryPage.sortOrFilterProductsFromLowPriceToHighPrice();
            List<String> listAfterSortFromLowToHighPrice = inventoryPage.getTotalAvailableItemListByItemName();
            IsListInLowPriceToHigh = ExpectedPriceList.equals(listAfterSortFromLowToHighPrice);
            Assert.assertEquals(IsListInLowPriceToHigh,true,"Products are not display after select on Price(Low to High) option from drop down");
            logger.info("Products are sorted according to Low price to High price");
            logger.info("TC_2.11 Test Case Passed");
        }catch(Exception e){
            logger.info("Failed to Sort products from low price to High format.");
            logger.info("TC_2.12 Test case failed");
            logger.error("Exception encountered: ", e);
        }
        logger.info("************ TC_2.12 Test case finished. ***************");
    }

    @Test(priority = 12)
    public void verifyUserSortProductsFromHighToLowPrice(){
        logger.info("************** TC_2.13 Started *************");
        logger.info("Test Case: Validate that user sort/filter products From High to Low price.");
        try{
            //check products are available in inventory page
            Assert.assertEquals(inventoryPage.isItemListEmpty(),false,"Products are not available in Inventory.html page");
            logger.info("Products are available in Inventory page.");
            //get available item list
            List<Double> list1 = inventoryPage.getTotalAvailableItemPriceListByItemPrice();
            logger.info("Available Products before sorting format into Low Price to High Price format:" + list1);
            //Check Products are already in High Price to Low Price format or not
            List<Double> expectedPriceList = inventoryPage.getItemListFromHighToLowPrice();
            boolean IsListInHighPriceToLow = expectedPriceList.equals(list1);
            Assert.assertEquals(IsListInHighPriceToLow,false,"Products are display according to High to Low price format");
            //select Price(High to Low)
            logger.info("Selecting Price(High to Low) option from drop down");
            inventoryPage.sortOrFilterProductsFromLowPriceToHighPrice();
            List<String> listAfterSortFromHighToLowPrice = inventoryPage.getTotalAvailableItemListByItemName();
            IsListInHighPriceToLow = expectedPriceList.equals(listAfterSortFromHighToLowPrice);
            Assert.assertEquals(IsListInHighPriceToLow,true,"Products are not display after select on Price(High to Low) option from drop down");
            logger.info("Products are sorted according to High price to Low price");
            logger.info("TC_2.13 Test Case Passed");
        }catch(Exception e){
            logger.info("Failed to Sort products from A to Z format.");
            logger.info("TC_2.13 Test case failed");
            logger.error("Exception encountered: ", e);
        }
        logger.info("************ TC_2.13 Test case finished. ***************");
    }

}
