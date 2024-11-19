package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.InventoryPage;
import pageObject.LoginPage;
import utilities.TestCaseMetadata;

public class LoginTest extends BaseClass{
    protected InventoryPage inventoryPage;
    @Test(testName = "TC_1.1",priority = 0)
    public void verifyUserLoginTest() {
        String testCaseID = "TC_1.1";
        String module = "Login";
        String scenario = TestCaseMetadata.getScenario(testCaseID,module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID,module);

        // Log metadata
        logger.info("************" + testCaseID + " Test Case Execution started ************");
        logger.info("Scenario:" + scenario);
        logger.info("Description: " + description);
        logger.info("Priority: " + priority);
        logger.info("Severity: " + severity);
        try {
            //Login Page Object
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setUserName(properties.getProperty("userName"));
            loginPage.setPassword(properties.getProperty("password"));
            loginPage.clickLoginBtn();

            //InventoryProduct page object
            inventoryPage = new InventoryPage(driver);
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

