package utilities;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testCases.BaseClass;

public class CustomListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ExtendReportManager.initReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtendReportManager.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
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