package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Item1Page extends BasePage {
    public Item1Page(WebDriver driver) {
        super(driver);
    }

    public final String productIndex1ItemPage = "https://www.saucedemo.com/inventory-item.html?id=4";
    //locators
    @FindBy(id = "back-to-products")
    WebElement backToProductsLinkBtn;

    public void clickOnBackToProductsBtn(){
        backToProductsLinkBtn.click();
    }

    public String getCurrentPageUrl(){
        return driver.getCurrentUrl();
    }

}
