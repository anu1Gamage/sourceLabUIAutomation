package pageObject;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testCases.BaseClass;

public class CartBadge extends BasePage{
    public CartBadge(WebDriver driver) {
        super(driver);
    }

    //Locator
    @FindBy(xpath = "//button[text() = 'Add to cart']")
    WebElement cartBtn;
    @FindBy(xpath = "//span[@class= 'shopping_cart_badge']")
    WebElement shoppingCartBadge;

    //Actions
    public boolean isAddToCartBtnDisplay(){
        return cartBtn.isDisplayed();
    }

    public void clickOnCartBtn(){
        cartBtn.click();
    }

    public boolean isShoppingCartBadgeDisplay(){
        try{return shoppingCartBadge.isDisplayed();
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public int getNoOfItemsDisplayedOnShoppingCartBadge(){
        return Integer.parseInt(shoppingCartBadge.getText());
    }

}
