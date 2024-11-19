package pageObject;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutStepOnePage extends BasePage{

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

   final public String checkoutStepOnePageUrl = "https://www.saucedemo.com/checkout-step-one.html";
   final public String getCheckoutStepOnePageUrlTitle = "Checkout: Your Information";

    //Locators
    @FindBy(xpath = "//span[text() ='Checkout: Your Information']")
    WebElement checkOutStepOnePageTitle;

    @FindBy(id = "first-name")
    WebElement firstNameIn;

    @FindBy(id = "last-name")
    WebElement lastNameIn;

    @FindBy(id ="postal-code")
    WebElement postalCodeIn;

    @FindBy(id = "continue")
    WebElement continueBtnOnCheckOutStepOnePage;

    @FindBy(id = "cancel")
    WebElement cancelBtnOnCheckOutStepOnePage;

    //Actions

    public String getCurrentPageUrl(){
        return driver.getCurrentUrl();
    }
    public boolean isCheckOutStepOnePageTitleDisplay(){
        return checkOutStepOnePageTitle.isDisplayed();
    }

    public String getCheckOutStepOnePageTitle(){
        return checkOutStepOnePageTitle.getText();
    }

    public void setFirstNameForCheckout(String firstName){
        firstNameIn.sendKeys(firstName);
    }

    public void setLastNameForCheckout(String lastName){
        lastNameIn.sendKeys(lastName);
    }

    public void setPostalCodeForCheckOut(String postalCode){
        postalCodeIn.sendKeys(postalCode);
    }

    public boolean isContinueBtnOnCheckOutStepOnePageDisplay(){
        return continueBtnOnCheckOutStepOnePage.isDisplayed();
    }

    public boolean isCancelBtnOnCheckOutStepOnePageDisplay(){
        return cancelBtnOnCheckOutStepOnePage.isDisplayed();
    }

    public void clickOnCheckOutStepOnePageContinueBtn(){
        continueBtnOnCheckOutStepOnePage.click();
    }

    public void clickOnCheckOutStepOnePageCancelBtn(){
        cancelBtnOnCheckOutStepOnePage.click();
    }

}
