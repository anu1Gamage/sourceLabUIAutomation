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

    @FindBy(xpath = "//div[@data-test = 'complete-text']")
    WebElement checkoutCompletionParagraph;


    //Actions
    public String getCheckoutCompletePageUrl(){
        return driver.getCurrentUrl();
    }
    public boolean isCheckOutCompletePageTitleDisplay(){
        return checkOutCompletePageTitle.isDisplayed();
    }

    public boolean isCheckOutCompletePageCompleteHeaderDisplay(){
        return checkOutCompletePageCompleteHeader.isDisplayed();
    }

    public String getCheckOutCompletePageHeader(){
        return checkOutCompletePageCompleteHeader.getText();
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

    public String getCheckoutCompletionParagraph(){
        return checkoutCompletionParagraph.getText();
    }

}
