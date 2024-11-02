package testCases;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.InventoryPage;
import pageObject.LoginPage;

public class LoginTest extends BaseClass{
    protected InventoryPage inventoryPage;
    @Test
    public void verifyUserLoginTest() {
        logger.info("*********** Start login test ***********");
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

            String expectedInventoryPageUrl = "https://www.saucedemo.com/inventory.html";
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

