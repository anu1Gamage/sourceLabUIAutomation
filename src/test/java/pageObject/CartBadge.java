package pageObject;

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
    WebElement addToCartBtn;
    @FindBy(xpath = "//span[@class= 'shopping_cart_badge']")
    WebElement shoppingCartBadge;

    //Actions
    public boolean isAddToCartBtnDisplay(){
        return addToCartBtn.isDisplayed();
    }

    public void clickOnAddToCartBtn(){
        addToCartBtn.click();
    }

    public boolean isShoppingCartBadgeDisplay(){
        return shoppingCartBadge.isDisplayed();
    }

    public int getNoOfItemsDisplayedOnShoppingCartBadge(){
        return Integer.parseInt(shoppingCartBadge.getText());
    }

}
