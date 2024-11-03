package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage{
    public CartPage(WebDriver driver) {
        super(driver);
    }


    final String pageUrl = "https://www.saucedemo.com/cart.html";
    final String YourCartPageTitle = "Your Cart";
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



















}
