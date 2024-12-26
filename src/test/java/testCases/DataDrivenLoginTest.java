package testCases;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObject.InventoryPage;
import pageObject.LoginPage;
import utilities.CustomListener;
import utilities.DataProviders;


@Listeners(CustomListener.class)
public class DataDrivenLoginTest extends BaseClass{
    protected InventoryPage inventoryPage;
    String expectedInventoryPageUrl;
    String expectedInventoryPageTitle;

    @BeforeMethod
    public void setUp(ITestContext context) {
        context.setAttribute("module", "dataDrivenLogin");
    }

@Test (dataProvider = "loginData",
        groups = {"Functional","Sanity","E2E"},
        dataProviderClass = DataProviders.class)
    public void DataDrivenLoginTest(String testCaseId, String userName, String password, boolean successExpected, String ExpectedError){
    logger.info("******** Login Scenario : "+ testCaseId +" Data driven test case execution started *********** ");
        //Initialize LoginPage object
        LoginPage loginPage = new LoginPage(BaseClass.getDriver());

        // Set username only if it is not null
        if (userName != null) {
            loginPage.setUserName(userName);
        }

        // Set password only if it is not null
        if (password != null) {
            loginPage.setPassword(password);
        }

        //Click on login Button
        loginPage.clickLoginBtn();

        if(successExpected){
            //verify user directs to Inventory Page
            inventoryPage = new InventoryPage(BaseClass.getDriver());
            expectedInventoryPageUrl = inventoryPage.inventoryPageUrl;
            expectedInventoryPageTitle = inventoryPage.inventoryPageTitle;
            Assert.assertEquals(inventoryPage.getCurrentPageUrl(),expectedInventoryPageUrl,"User is not in the inventory page");
            Assert.assertEquals(inventoryPage.getInventoryPageTitle(),expectedInventoryPageTitle,"Inventory Page Title not match to expected page title");
        }else{
            //Check if the expected error message is displayed
            Assert.assertEquals(loginPage.getErrorMessage(),ExpectedError,"Error massage is not matched with expected Error Message");
        }
        logger.info("******** Login Scenario : "+ testCaseId +" Data driven test case execution finished *********** ");
    }

    @Test(dataProvider = "loginDataForKeyBoardActions",dataProviderClass = DataProviders.class)
    public void verifyUserLoginUsingKeyBoardActions(String testCaseId, String userName, String password, boolean successExpected, String ExpectedError){
        String moduleName = "dataDrivenLogin";
    logger.info("******** Login Scenario : " + testCaseId + " User Login with keyboard actions verification test case Execution started *********** ");
        //Initialize LoginPage object
        LoginPage loginPage = new LoginPage(BaseClass.getDriver());

        // Set username only if it is not null
        if (userName != null) {
            loginPage.setUserName(userName);
        }

        // Set password only if it is not null
        if (password != null) {
            loginPage.setPassword(password);
        }

        //Click on login Button
        loginPage.clickLoginBtn();

        if(successExpected){
            //verify user directs to Inventory Page
            InventoryPage inventoryPage = new InventoryPage(BaseClass.getDriver());
            expectedInventoryPageUrl = inventoryPage.inventoryPageUrl;
            expectedInventoryPageTitle = inventoryPage.inventoryPageTitle;
            Assert.assertEquals(inventoryPage.getCurrentPageUrl(),expectedInventoryPageUrl,"User is not in the inventory page");
            Assert.assertEquals(inventoryPage.getInventoryPageTitle(),expectedInventoryPageTitle,"Inventory Page Title not match to expected page title");
        }else{
            //Check if the expected error message is displayed
            Assert.assertEquals(loginPage.getErrorMessage(),ExpectedError,"Error massage is not matched with expected Error Message");
        }
        logger.info("******** Login Scenario : " + testCaseId + " User Login with keyboard actions verification test case Execution started *********** ");
    }

}
