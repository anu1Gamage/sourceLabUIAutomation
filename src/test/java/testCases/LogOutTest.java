package testCases;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.BurgerMenuBar;
import utilities.TestCaseMetadata;

import java.lang.reflect.Method;

public class LogOutTest extends BaseClass {
    @BeforeMethod
    public void setUp(ITestContext context) {
        context.setAttribute("module", "login");
    }
    protected static BurgerMenuBar burgerMenuBar;
    private String expectedURLAfterLogout = "https://www.saucedemo.com/";
    private String actualURLAfterLogout;

    @Test(testName = "TC_7.1")
    public void verifyUserLogout(ITestContext context){
        String testCaseID = getTestCaseId();
        String module = context.getAttribute("module").toString();
        String scenario = TestCaseMetadata.getScenario(testCaseID, module);
        String description = TestCaseMetadata.getDescription(testCaseID, module);
        String priority = TestCaseMetadata.getPriority(testCaseID, module);
        String severity = TestCaseMetadata.getSeverity(testCaseID, module);
        logger.info("Test scenario: " + scenario + ", Priority: " + priority + ", Severity: " + severity);
        logger.info("*********** " + testCaseID + " " + description + " started ************");

        try{
            assertBurgerMenuIconAvailability();
            clickOnBurgerMenu();
            assertOpenedBurgerMenu();
            clickOnLogOutNavLink();
            assertUserLogout();
        }catch(Exception e){
            logger.error("Exception encountered", e);
        }
    }

    private String getTestCaseId() {
        try {
            String methodName = new Throwable().getStackTrace()[1].getMethodName();
            Method method = this.getClass().getMethod(methodName);
            Test testAnnotation = method.getAnnotation(Test.class);
            return testAnnotation.testName();
        } catch (NoSuchMethodException e) {
            logger.error("Failed to fetch test case ID from @Test annotation.", e);
            return "UnknownTestCaseID";
        }
    }

    private void clickOnBurgerMenu(){
        burgerMenuBar.clickOnBurgerMenu();
        logger.info("Clicked on burgerMenu icon");
    }

    private void assertBurgerMenuIconAvailability(){
        boolean isBurgerMenuIconDisplay = burgerMenuBar.isBurgerMenuDisplay();
        Assert.assertEquals(isBurgerMenuIconDisplay,true,"Burger Menu Icon is not display");
    }

    private void assertOpenedBurgerMenu(){
        boolean isLogOutButtonAvailable = burgerMenuBar.isLogoutSideBarLinkDisplay();
        Assert.assertEquals(isLogOutButtonAvailable,true,"Logout sidebar nav element not display");
    }

    private void clickOnLogOutNavLink(){
        burgerMenuBar.clickOnLogoutSideBarLink();
        logger.info("Clicked on Logout Nav Link for logout");
    }

    private void assertUserLogout(){
        actualURLAfterLogout = getDriver().getCurrentUrl();;
        Assert.assertEquals(actualURLAfterLogout,expectedURLAfterLogout,"actualURLAfterLogout and expectedURLAfterLogout are not matched. actualURL is : "+actualURLAfterLogout+ "ExpectedURL is: "+expectedURLAfterLogout);
    }
}
