package utilities;


import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import testCases.BaseClass;
import java.lang.reflect.Method;

public class CustomListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ExtendReportManager.initReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
       //org.apache.logging.log4j.Logger logger = BaseClass.logger;

        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        Test testAnnotation = method.getAnnotation(Test.class);
        String testCaseID = testAnnotation.testName();

        String testMethodName = result.getMethod().getMethodName();
        String moduleName = result.getTestContext().getAttribute("module") != null
                ? result.getTestContext().getAttribute("module").toString()
                : "default";
        String description = testAnnotation.description();
        String Scenario = moduleName;
        //logger.info("Starting test: " + testCaseID);
        //logger.info("TestMethod :" + testMethodName);
        //logger.info("Module name: " + moduleName);

        try {
            ExtendReportManager.createTest(testCaseID, moduleName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize test for ID: " + testCaseID + " and Module: " + moduleName);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtendReportManager.getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = BaseClass.getDriver();
        String screenshotPath = ScreenshotUtility.captureScreenshot(driver, result.getMethod().getMethodName());
        ExtendReportManager.getTest()
                .fail("Test Failed: " + result.getThrowable())
                .addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtendReportManager.getTest().skip("Test Skipped: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtendReportManager.flushReport();
    }
}