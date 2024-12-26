package pageObject;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.NoSuchElementException;
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
    static
    WebElement firstNameIn;

    @FindBy(id = "last-name")
    static
    WebElement lastNameIn;

    @FindBy(id ="postal-code")
    static
    WebElement postalCodeIn;

    @FindBy(id = "continue")
    WebElement continueBtnOnCheckOutStepOnePage;

    @FindBy(id = "cancel")
    WebElement cancelBtnOnCheckOutStepOnePage;

    @FindBy(xpath = ("//h3[@data-test='error']"))
    private WebElement CheckOutUserInfoErrorMessage;

    @FindBy(xpath = ("//h3[@data-test = 'error-button']"))
    WebElement errorMessageCancelButton;

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

    public boolean isFirstNameFieldDisplay(){
        return firstNameIn.isDisplayed();
    }

    public boolean isLastNameFieldDisplay(){
        return lastNameIn.isDisplayed();
    }

    public boolean isPostalCodeFieldDisplay(){
        return postalCodeIn.isDisplayed();
    }

    public static String getAttributeValueOfFirstNameField(){
        String firstNameValue = firstNameIn.getAttribute("value");
        return firstNameValue;
    }

    public static String getAttributeValueOfLastNameField(){
        String lastNameValue = lastNameIn.getAttribute("value");
        return lastNameValue;
    }

    public static String getAttributeValueOfPostalCodeField(){
        String postalCodeValue = postalCodeIn.getAttribute("value");
        return postalCodeValue;
    }

    public static void clearInputField(){
        firstNameIn.clear();
        lastNameIn.clear();;
        postalCodeIn.clear();
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

    public String getErrorMessage(){
        try {
            return CheckOutUserInfoErrorMessage.getText();
        }catch (NoSuchElementException e){
            return "Error Message Not Found";
        }
    }

    public void clickOnErrorMessageCancelButton(){
        errorMessageCancelButton.click();
    }

    public boolean isErrorMessageDisplay(){
        try{
           boolean isDisplay = CheckOutUserInfoErrorMessage.isDisplayed();
            return isDisplay;
        }catch(NoSuchElementException e){
            return false;
        }

    }

}
