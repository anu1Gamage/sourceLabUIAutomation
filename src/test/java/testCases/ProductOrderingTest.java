package testCases;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObject.*;
import utilities.CustomListener;
import utilities.DataProviders;
import utilities.TestCaseMetadata;

import java.lang.reflect.Method;

@Listeners(CustomListener.class)
public class ProductOrderingTest extends BaseClass {

    protected LoginPage loginPage;
    protected CartBadge cartBadge;
    protected CartPage cartPage;
    protected InventoryPage inventoryPage;
    protected CheckoutStepOnePage checkoutStepOnePage;
    protected CheckoutStepTwoPage checkoutStepTwoPage;
    protected CheckoutCompletePage checkoutCompletePage;

    @BeforeMethod
    public void setUp(ITestContext context) {
        context.setAttribute("module", "inventory");
    }

    @BeforeMethod
    public void setUpProductOrderTest(){
        cartBadge = new CartBadge(BaseClass.getDriver());
        inventoryPage = new InventoryPage(BaseClass.getDriver());
        checkoutCompletePage = new CheckoutCompletePage(BaseClass.getDriver());
        checkoutStepTwoPage = new CheckoutStepTwoPage(BaseClass.getDriver());
        checkoutCompletePage = new CheckoutCompletePage(BaseClass.getDriver());
    }

    //TODO -- check posibility to get test case id at once for all classes. without write method for it in all cless
    private String getTestCaseId() {
        try {
            String methodName = new Throwable().getStackTrace()[1].getMethodName();
            // Use reflection to find the method
            Method method = this.getClass().getMethod(methodName);
            Test testAnnotation = method.getAnnotation(Test.class);
            return testAnnotation.testName();
        } catch (NoSuchMethodException e) {
            logger.error("Failed to fetch test case ID from @Test annotation.", e);
            return "UnknownTestCaseID";
        }
    }

    @Test(testName = "TC_1.1",
          priority = 26,
          dataProvider = "userCheckoutInfo",dataProviderClass = DataProviders.class)
    public void verifyUserOrderOneItem(ITestContext context) {
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");

        try {
            //login to the application
            LoginTest.verifyUserLoginTest();


        } catch (Exception e) {

        }
    }

    }











