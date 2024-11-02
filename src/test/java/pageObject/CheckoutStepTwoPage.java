package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutStepTwoPage extends BasePage{
    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    @FindBy(xpath = "//span[text() ='Checkout: Overview']")
    WebElement checkOutStepOnePageTitle;

    @FindBy(xpath = "//div[@data-test = 'subtotal-label']")
    WebElement ItemsTotalPrice;

    @FindBy(className = "summary_tax_label")
    WebElement totalTax;

    @FindBy(xpath = "//div[@data-test = 'total-label']")
    WebElement totalPrice;

    @FindBy(id = "finish")
    WebElement checkOutStepOnePageFinishBtn;

    @FindBy(id = "cancel")
    WebElement checkOutStepOnePageCancelBtn;

    //Action
    public boolean isCheckOutStepOnePageTitleDisplay(){
        return checkOutStepOnePageTitle.isDisplayed();
    }
    public boolean isCheckOutStepOnePageCancelBtnDisplay(){
        return checkOutStepOnePageCancelBtn.isDisplayed();
    }

    public boolean isCheckOutStepOnePageFinishBtnDisplay(){
        return checkOutStepOnePageFinishBtn.isDisplayed();
    }


    public String getCheckOutStepOnePageTitle(){
        return checkOutStepOnePageTitle.getText();
    }

    public double getItemsTotalPrice(){
        return Double.parseDouble(ItemsTotalPrice.getText().replace("$",""));
    }

    public double getTotalTaxApplied(){
        return Double.parseDouble(totalTax.getText().replace("$", ""));
    }

    public double getTotalPrice(){
        return Double.parseDouble(totalPrice.getText().replace("$",""));
    }

    public void clickOnCancelBtn(){
        checkOutStepOnePageCancelBtn.click();
    }

    public void clickOnCheckOutStepOnePageFinishBtn(){
        checkOutStepOnePageFinishBtn.click();
    }
}
