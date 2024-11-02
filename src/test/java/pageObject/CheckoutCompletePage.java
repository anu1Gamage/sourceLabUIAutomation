package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage{
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    //Locators
    @FindBy(xpath = "//span[@ddata-test = 'title']")
    WebElement checkOutCompletePageTitle;

    @FindBy(xpath = "//h2[@data-test = 'complete-header']")
    WebElement checkOutCompletePageCompleteHeader;

    @FindBy(id = "back-to-products")
    WebElement checkOutCompletePageBackToProductBtn;

    @FindBy(xpath = "//img[@data-test = 'pony-express']")
    WebElement checkOutCompleteIcon;


    //Actions
    public boolean isCheckOutCompletePageTitleDisplay(){
        return checkOutCompletePageTitle.isDisplayed();
    }

    public boolean isCheckOutCompletePageCompleteHeaderDisplay(){
        return checkOutCompletePageCompleteHeader.isDisplayed();
    }

    public boolean isCheckOutCompletePageBackToProductBtnDisplay(){
        return checkOutCompletePageBackToProductBtn.isDisplayed();
    }

    public boolean isCheckOutCompleteIconDisplay(){
        return checkOutCompleteIcon.isDisplayed();
    }

    public void clickOnCheckOutCompletePageBackToProductBtn(){
        checkOutCompletePageBackToProductBtn.click();
    }




}
