package testCases;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObject.InventoryPage;
import pageObject.LoginPage;
import utilities.CustomListener;
import utilities.TestCaseMetadata;


@Listeners(CustomListener.class)
public class LoginTest extends BaseClass{
    protected static InventoryPage inventoryPage;

    @BeforeMethod
    public void setUp(ITestContext context) {
        context.setAttribute("module", "login");
    }
    @Test(testName = "TC_1.1",priority = 0,groups = {"Smoke","E2E"})
    public static void verifyUserLoginTest() {
        String testCaseID = "TC_1.1";
        String module = "login";
        String scenario = TestCaseMetadata.getScenario(testCaseID,module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID,module);

        // Log metadata
        logger.info("************" + testCaseID + " Test Case Execution started ************");
        logger.info("module: "+ module);
        logger.info("Scenario:" + scenario);
        logger.info("Description: " + description);
        logger.info("Priority: " + priority);
        logger.info("Severity: " + severity);
        try {
            //Login Page Object
            LoginPage loginPage = new LoginPage(BaseClass.getDriver());
            loginPage.setUserName(properties.getProperty("userName"));
            loginPage.setPassword(properties.getProperty("password"));
            loginPage.clickLoginBtn();

            //InventoryProduct page object
            inventoryPage = new InventoryPage(BaseClass.getDriver());
            boolean targetPage = inventoryPage.isDisplayPageTitle();
            Assert.assertTrue(targetPage);

            String expectedInventoryPageUrl = inventoryPage.inventoryPageUrl;
            String actualPageUrl = inventoryPage.getCurrentPageUrl();
            Assert.assertEquals(actualPageUrl, expectedInventoryPageUrl);
        }
        catch(Exception e){
            Assert.fail();
            logger.error("User not successfully login to the inventory page");
        }
        logger.info("******** Finish login test ************");
        }
    }

