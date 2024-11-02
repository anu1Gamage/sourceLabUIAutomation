package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BasePage{

    //Constructor
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    //Locators

    //InventoryPageTitle locator
    @FindBy(xpath = "//span[text() ='Products']")
    WebElement InventoryPageTitle;
    //Product 1
    @FindBy(xpath = "(//button[text() = 'Add to cart'])[1]")
    WebElement Product1;
    //product 2
    @FindBy(xpath = "(//button[text() = 'Add to cart'])[2]")
    WebElement product2;
    //product 3
    @FindBy(xpath = "(//button[text() = 'Add to cart'])[3]")
    WebElement product3;
    //Product 4
    @FindBy(xpath = "(//button[text() = 'Add to cart'])[4]")
    WebElement product4;
    //product 5
    @FindBy(xpath = "(//button[text() = 'Add to cart'])[5]")
    WebElement product5;
    //product 6
    @FindBy(xpath = "(//button[text() = 'Add to cart'])[6]")
    WebElement product6;
    //product1Title
    @FindBy(xpath = "//div[@data-test = 'inventory-item-name' and text() = 'Sauce Labs Backpack']")
    WebElement product1Title;
    //product2Title
    @FindBy(xpath = "//div[@data-test = 'inventory-item-name' and text() = 'Sauce Labs Bike Light']")
    WebElement product2Title;
    //product3Title
    @FindBy(xpath = "//div[@data-test = 'inventory-item-name' and text() = 'Sauce Labs Bolt T-Shirt']")
    WebElement product3Title;
    //product4Title
    @FindBy(xpath = "//div[@data-test = 'inventory-item-name' and text() = 'Sauce Labs Fleece Jacket']")
    WebElement product4Title;
    //product1Price
    @FindBy(xpath = "(//div[@class = 'inventory_item_price'])[1]")
    WebElement product1Price;
    //product2price
    @FindBy(xpath = "(//div[@class = 'inventory_item_price'])[2]")
    WebElement product2Price;
    //product3Price
    @FindBy(xpath = "(//div[@class = 'inventory_item_price'])[3]")
    WebElement product3Price;
    //product4Price
    @FindBy(xpath = "(//div[@class = 'inventory_item_price'])[4]")
    WebElement product4Price;
    //product1Image
    @FindBy(xpath = "(//img[@class = 'inventory_item_img'])[1]")
    WebElement product1Image;
    //productSortDDB
    @FindBy(xpath = "//select[@class = 'product_sort_container']")
    WebElement productSortDropDownBtn;
    //ShoppingCartIcon
    @FindBy(xpath = "//a[@class = 'shopping_cart_link']")
    WebElement ShoppingCartIcon;
    //SauceLabsBackPackRemoveBtn
    @FindBy(id = "remove-sauce-labs-backpack")
    WebElement SauceLabsBackPackRemoveBtn;
    //remove-sauce-labs-bike-light
    @FindBy(id = "remove-sauce-labs-bike-light")
    WebElement SauceLabsBikeLightRemoveBtn;
    //remove-sauce-labs-bolt-t-shirt
    @FindBy(id = "remove-sauce-labs-bolt-t-shirt")
    WebElement SauceLabsBoltTShirtRemoveBtn;
    //inventoryItemsNames
    @FindBy(className = "inventory_item_name")
    List<WebElement> inventoryItemNames;

    @FindBy(xpath = "//select[@class = 'product_sort_container']")
    WebElement dropDownBtn;

    @FindBy(xpath = "//button[text() = 'Add to cart']")
    List<WebElement> addToCartButtons;

    @FindBy(xpath = "//button[text() = 'Remove']")
    List<WebElement> RemoveButtonsList;


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

    public void clickOnProduct1AddToCartBtn(){
        Product1.click();
    }

    public void clickOnProduct2AddToCartBtn(){
        product2.click();
    }

    public void clickOnProduct3AddToCartBtn(){
        product3.click();
    }

    public void clickOnProduct4AddToCartBtn(){
        product4.click();
    }

    public void clickOnProduct5AddToCartBtn(){
        product5.click();
    }

    public void clickOnProduct6AddToCartBtn(){
        product6.click();
    }

    public void clickOnProduct1TitleLink(){
        product1Title.click();
    }

    public void clickOnProduct2TitleLink(){
        product2Title.click();
    }

    public void clickOnProduct3TitleLink(){
        product3Title.click();
    }

    public void clickOnProduct4TitleLink(){
        product4Title.click();
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

    public void clickOnSauceLabsBackPackRemoveBtn(){
        SauceLabsBackPackRemoveBtn.click();
    }

    public void clickOnSauceLabsBikeLightRemoveBtn(){
        SauceLabsBikeLightRemoveBtn.click();
    }

    public void clickOnSauceLabsBoltTShirtRemoveBtn(){
        SauceLabsBoltTShirtRemoveBtn.click();
    }

    public void clickOnProduct1Image(){
        product1Image.click();
    }

    public boolean isProduct1AddToCartBtnDisplay(){
       return Product1.isDisplayed();
    }

    public boolean isProduct2AddToCartBtnDisplay(){
        return product2.isDisplayed();
    }

    public boolean isProduct3AddToCartBtnDisplay(){
        return product3.isDisplayed();
    }

    public boolean isProduct4AddToCartBtnDisplay(){
        return product4.isDisplayed();
    }

    public boolean isProduct5AddToCartBtnDisplay(){
        return product5.isDisplayed();
    }

    public boolean isProduct6AddToCartBtnDisplay(){
        return product6.isDisplayed();
    }


    public boolean isSauceLabsBackPackRemoveBtnDisplay(){
        return SauceLabsBackPackRemoveBtn.isDisplayed();
    }

    public boolean isSauceLabsBikeLightRemoveBtnDisplay(){
        return SauceLabsBikeLightRemoveBtn.isDisplayed();
    }

    public boolean isSauceLabsBoltTShirtRemoveBtnDisplay(){
        return SauceLabsBoltTShirtRemoveBtn.isDisplayed();
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

    public void clickOnAvailableAllAddToCartButtonElementsInInventoryPage() {

        // Loop through each button and click it
        for (WebElement button : addToCartButtons) {
            button.click();
        }
    }

    public void clickOnAvailableAllRemoveButtons(){
        for(WebElement button : RemoveButtonsList){
            button.click();
        }
    }

    public List<Double> getTotalAvailableItemPriceListByItemPrice() {
        List<WebElement> availableItems = inventoryItemNames;
        List<Double> displayedItemPrices = new ArrayList<>();
        for (WebElement price : availableItems) {
            String priceText = price.getText().replace("$", "");
            displayedItemPrices.add(Double.parseDouble(priceText));
            return displayedItemPrices;
        }
        return displayedItemPrices;
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
