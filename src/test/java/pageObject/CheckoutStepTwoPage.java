package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CheckoutStepTwoPage extends BasePage{
    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    @FindBy(xpath = "//span[text() ='Checkout: Overview']")
    WebElement checkOutStepTwoPageTitle;

    @FindBy(xpath = "//div[@data-test = 'subtotal-label']")
    WebElement ItemsTotalPrice;

    @FindBy(className = "summary_tax_label")
    WebElement totalTax;

    @FindBy(xpath = "//div[@data-test = 'total-label']")
    WebElement totalPrice;

    @FindBy(xpath = "//div[@class = 'inventory_item_price']")
    List<WebElement> productsPrice;

    @FindBy(id = "finish")
    WebElement checkOutStepTwoPageFinishBtn;

    @FindBy(id = "cancel")
    WebElement checkOutStepTwoPageCancelBtn;

    //Action
    public boolean isCheckOutStepTwoPageTitleDisplay(){
        return checkOutStepTwoPageTitle.isDisplayed();
    }
    public boolean isCheckOutStepTwoPageCancelBtnDisplay(){
        return checkOutStepTwoPageCancelBtn.isDisplayed();
    }

    public boolean isCheckOutStepTwoPageFinishBtnDisplay(){
        return checkOutStepTwoPageFinishBtn.isDisplayed();
    }

    public String getCurrentPageUrl(){
       return driver.getCurrentUrl();
    }

    public List<Double> extractItemPrices(){
        return productsPrice.stream()
                .map(price -> price.getText().replace("$", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    Map<String, Double> itemTaxRates  = Map.of(
            "29.9", 2.40,
            "9.99", 0.80,
            "15.99", 1.28,
            "49.99", 4.00,
            "7.99",0.64,
            "15.99", 1.28
    );

    private List<String> productIds = List.of( "29.9","9.99","15.99","49.99","7.99", "15.99");
    public double calculateTotalTax() {
            double totalTax = 0;
            List<Double> itemPrices = extractItemPrices();
            for (int i = 0; i < itemPrices.size(); i++) {
                String productId = productIds.get(i);  // Get the product ID for the current item
                double taxRate = itemTaxRates.getOrDefault(productId, 0.0);  // Get the tax rate from the map
                totalTax += taxRate;
            }
            return totalTax;
        }

    public double calculateTotalPriceInCartItems(){
        double sum = 0;
        List<Double> prices = extractItemPrices();
        for(double price : prices){
            sum += price;
        }
        return sum;
    }

    public String getCheckoutStepTwoPageTitle(){
        return checkOutStepTwoPageTitle.getText();
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
        checkOutStepTwoPageCancelBtn.click();
    }

    public void clickOnCheckOutStepTwoPageFinishBtn(){
        checkOutStepTwoPageFinishBtn.click();
    }
}
