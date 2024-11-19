package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage{
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public final String cartPageUrl = "https://www.saucedemo.com/cart.html";
    public final String YourCartPageTitle = "Your Cart";
    //Locators
    @FindBy(className = "title")
    WebElement cartPageTitle;

    @FindBy(id = "checkout")
    WebElement checkOutBtn;

    @FindBy(id = "continue-shopping")
    WebElement continueShopping;

    @FindBy(id = "remove-sauce-labs-backpack")
    WebElement SauceLabsBackPackRemoveBtn;
    @FindBy(id = "remove-sauce-labs-bike-light")
    WebElement SauceLabsBikeLightRemoveBtn;
    //remove-sauce-labs-bolt-t-shirt
    @FindBy(id = "remove-sauce-labs-bike-light")
    WebElement SauceLabsBoltTShirtRemoveBtn;

    @FindBy(xpath = "//button[text() = 'Remove']")
    List<WebElement> removeBtns;

    //Actions
    public boolean isCartPageTitleDisplay(){
        return cartPageTitle.isDisplayed();
    }

    public String getCartPageTitle(){
        return cartPageTitle.getText();
    }

    public boolean isCheckoutBtnDisplay(){
        return checkOutBtn.isDisplayed();
    }

    public boolean isContinueShoppingBtnDisplay(){
        return continueShopping.isDisplayed();
    }

    public void clickOnCheckOutBtn(){
        checkOutBtn.click();
    }

    public void clickOnContinueShoppingBtn(){
        continueShopping.click();
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

    public String getCartPageCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public int getTotalRemoveBtns(){
        int sum = 0;
        List<WebElement> removeButtons = removeBtns;
        for(int i =0; i<removeButtons.size();i++){
            sum += i;
        }
        return sum;
    }

}
