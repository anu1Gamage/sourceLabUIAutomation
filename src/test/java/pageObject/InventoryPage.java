package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static testCases.BaseClass.logger;

public class InventoryPage extends BasePage{

    //Constructor
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    public final String inventoryPageUrl = "https://www.saucedemo.com/inventory.html";

    public final String inventoryPageTitle = "Products";
    public final int maxProductsInInventoryPage = 6;
    @FindBy(xpath = "//span[text() ='Products']")
    WebElement InventoryPageTitle;

    @FindBy(xpath = "//div[@data-test = 'inventory-item-name' and text() = 'Sauce Labs Backpack']")
    WebElement product1Title;

    @FindBy(xpath = "//div[@data-test = 'inventory-item-name' and text() = 'Sauce Labs Bike Light']")
    WebElement product2Title;

    @FindBy(xpath = "//div[@data-test = 'inventory-item-name' and text() = 'Sauce Labs Bolt T-Shirt']")
    WebElement product3Title;

    @FindBy(xpath = "//div[@data-test = 'inventory-item-name' and text() = 'Sauce Labs Fleece Jacket']")
    WebElement product4Title;

    @FindBy(xpath = "(//div[@class = 'inventory_item_price'])[1]")
    WebElement product1Price;

    @FindBy(xpath = "(//div[@class = 'inventory_item_price'])[2]")
    WebElement product2Price;

    @FindBy(xpath = "(//div[@class = 'inventory_item_price'])[3]")
    WebElement product3Price;

    @FindBy(xpath = "(//div[@class = 'inventory_item_price'])[4]")
    WebElement product4Price;

    @FindBy(xpath = "(//img[@class = 'inventory_item_img'])[1]")
    WebElement product1Image;

    @FindBy(xpath = "(//img[@class = 'inventory_item_img'])")
    List<WebElement> productsImages;

    @FindBy(xpath = "//a[@class = 'shopping_cart_link']")
    WebElement ShoppingCartIcon;

    @FindBy(className = "inventory_item_name")
    List<WebElement> inventoryItemNames;

    @FindBy(xpath = "//select[@class = 'product_sort_container']")
    WebElement dropDownBtn;

    @FindBy(xpath = "//button[text() = 'Add to cart']")
    List<WebElement> addToCartButtons;

    @FindBy(xpath = "//button[text() = 'Remove']")
    List<WebElement> removeButtons;


    //Action methods

    public boolean isDisplayPageTitle(){
        try{
        return (InventoryPageTitle.isDisplayed());
    }catch (Exception e){
        return false;
        }
    }

    public String getCurrentPageUrl(){
        String actualInventoryPageUrl = driver.getCurrentUrl();
        return actualInventoryPageUrl;
    }

    public String getInventoryPageTitle(){
        return InventoryPageTitle.getText();
    }

    public void clickOnProduct1TitleLink(){
        product1Title.click();
    }

    public double getProduct1ItemPrice(){
      return Double.parseDouble(product1Price.getText().replace("Total: $",""));
    }

    public double getProduct2ItemPrice(){
        return Double.parseDouble(product2Price.getText().replace("Total: $",""));
    }

    public double getProduct3ItemPrice(){
        return Double.parseDouble(product3Price.getText().replace("Total: $",""));
    }

    public double getProduct4ItemPrice(){
        return Double.parseDouble(product4Price.getText().replace("Total: $",""));
    }

    public void clickOnProduct1Image(){
        product1Image.click();
    }

    public boolean isDropDownBtnDisplay(){
        return dropDownBtn.isDisplayed();
    }

    public int getTotalNoOfAvailableItems() {
        List<WebElement> availableItems = inventoryItemNames;
        return availableItems.size();
    }

    public List<String> getTotalAvailableItemListByItemName(){
        List<WebElement> availableItems = inventoryItemNames;
        List<String> productNames = availableItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        return productNames;
    }

    public List<String> getAToZSortedItemList(){
        List<WebElement> availableItems = inventoryItemNames;
        List<String> productNames = availableItems.stream()
                .map(WebElement::getText)
                .sorted()
                .collect(Collectors.toList());
       return productNames;
    }

    public List<String> getZToASortedList(){
        List<WebElement> availableItems = inventoryItemNames;
        List<String> productNames = availableItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        List<String> sortedProductNames = new ArrayList<>(productNames);
        sortedProductNames.sort(Comparator.reverseOrder());
        return sortedProductNames;
    }

    public boolean isItemListEmpty(){
      return getTotalAvailableItemListByItemName().isEmpty();
    }

    public boolean isAddToCartBtnDisplay(){
        for (WebElement button : addToCartButtons) {
            if (button.isDisplayed()) {
                return true;
            }
        }
            return false;
    }

    public int countDisplayedAddToCartButtons() {
        int count = 0;
        for (WebElement button : addToCartButtons) {
            if (button.isDisplayed()) {
                count++;
            }
        }
        return count;
    }

    public void clickOnAvailableAllAddToCartButtonElementsInInventoryPage() {
        // Loop through each button and click it
        for (WebElement button : addToCartButtons) {
            button.click();
        }
    }

    public void clickOnFirstProductAddToCartBtn(){
        addToCartButtons.get(0).click();
    }

    public void clickOnSecondProductAddToCartBtn(){
        addToCartButtons.get(1).click();
    }

    public void clickAddToCartButtonByIndex(int index) {
        try {
            addToCartButtons.get(index).click();
            logger.info("Clicked 'Add to Cart' button for product at index: " + index);
        } catch (IndexOutOfBoundsException e) {
            logger.error("No product found at index: " + index);
            throw new NoSuchElementException("Add to Cart button not found for product at index: " + index, e);
        }
    }

    public void clickOnProductImageByIndex(int index){
        try{
           productsImages.get(index).click();
            logger.info("Clicked product's image of product at index: " + index);
        }catch(IndexOutOfBoundsException e){
            logger.error("No product found at index: " + index);
            throw new NoSuchElementException("Add to Cart button not found for product at index: " + index, e);
        }
    }


    public void clickOnProductNameByIndex(int index){
       try{
           inventoryItemNames.get(index).click();
           logger.info("Clicked on Product Name");
       }catch(IndexOutOfBoundsException e){
        logger.error("No product found at index : " +index);
        throw new NoSuchElementException("Product Name not found for product at given index: \" + index, e");
       }
    }


    public boolean isAlreadyAddedItemsAvailable(){
        if(removeButtons.isEmpty()){
            return false;
        }
        return true;
    }

    public int getAvailableRemoveBtnCount(){
       return removeButtons.size();
    }

    public boolean isRemoveBtnDisplay(){
        for (WebElement button : removeButtons) {
            if (button.isDisplayed()) {
                return true;
            }
        }
        return false;
    }

    public void clickOnFirstProductRemoveBtn(){
        addToCartButtons.get(0).click();
    }

    public void clickOnSecondProductRemoveBtn(){
        addToCartButtons.get(1).click();
    }

    public void clickOnAvailableAllRemoveButtons(){
        for(WebElement button : removeButtons){
            button.click();
        }
    }
    public void clickOnProductRemoveButton(int index){
        try {
            removeButtons.get(index).click();
            logger.info("Clicked on 'Remove' button for product at index: " + index);
        } catch (IndexOutOfBoundsException e) {
            logger.error("No product found at index: " + index);
            throw new NoSuchElementException("Remove button not found for product at index: " + index, e);
        }
    }

    public List<Double> extractItemPrices(){
        return inventoryItemNames.stream()
                .map(price -> price.getText().replace("$", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    public List<Double> getTotalAvailableItemPriceListByItemPrice() {
        return extractItemPrices();
    }

    public List<Double> getItemListFromLowToHighPrice(){
        List<Double> displayedItemPrices = extractItemPrices();
        displayedItemPrices.sort(Comparator.naturalOrder());
        return displayedItemPrices;
    }

    public List<Double> getItemListFromHighToLowPrice(){
        List<Double> displayedItemPrices = extractItemPrices();
        displayedItemPrices.sort(Comparator.reverseOrder());
        return displayedItemPrices;
    }

    public boolean isDropDownBtnAvailable(){
      return dropDownBtn.isDisplayed();
    }

    public void sortOrFilterProductsFromAtoZByName(){
        Select select = new Select(dropDownBtn);
        select.selectByVisibleText("Name (A to z)");
    }

    public void sortOrFilterProductsFromZtoAByName(){
        Select select = new Select(dropDownBtn);
        select.selectByVisibleText("Name (Z to A)");
    }

    public void sortOrFilterProductsFromLowPriceToHighPrice() {
        Select select = new Select(dropDownBtn);
        select.selectByVisibleText("Price (low to high)");
    }

    public void sortOrFilterProductsFromHighPriceToLowPrice() {
        Select select = new Select(dropDownBtn);
        select.selectByVisibleText("Price (high to low)");
    }

}
