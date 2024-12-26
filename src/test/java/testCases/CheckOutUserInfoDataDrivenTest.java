package testCases;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.CheckoutStepOnePage;
import pageObject.CheckoutStepTwoPage;

public class CheckOutUserInfoDataDrivenTest extends BaseClass {

    protected CheckoutStepOnePage checkoutStepOnePage;

    protected CheckoutStepTwoPage checkoutStepTwoPage;

    protected String expectedPageURL = "https://www.saucedemo.com/checkout-step-two.html";

    @BeforeMethod
    public void setUp(ITestContext context) {
        context.setAttribute("module", "checkOutOnePage");
    }

    @Test
    public void checkOutUserInfoDataDrivenTest(String testCaseId, String firstName, String lastName,
                                               String postalCode, boolean successExpected, String ExpectedError){

        logger.info("********** CheckOutUserInfo data driven test execution Started **********");

        checkoutStepOnePage = new CheckoutStepOnePage(BaseClass.getDriver());

        if(firstName != null){
            checkoutStepOnePage.setFirstNameForCheckout(firstName);
        }

        if(lastName != null){
            checkoutStepOnePage.setLastNameForCheckout(lastName);
        }

        if(postalCode != null){
            checkoutStepOnePage.setPostalCodeForCheckOut(postalCode);
        }

        checkoutStepOnePage.clickOnCheckOutStepOnePageContinueBtn();


        if(successExpected){
            String actualDirectedPageURL = checkoutStepTwoPage.getCurrentPageUrl();
            Assert.assertEquals(actualDirectedPageURL,expectedPageURL,"After click on CheckOutOnePage continue button, Actual directed page url and expected page url is different");
        }else{
            Assert.assertEquals(checkoutStepOnePage.getErrorMessage(),ExpectedError,"Error massage is not matched with expected Error Message");
        }
    }

}
