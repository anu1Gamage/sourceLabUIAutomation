package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ExtendReportManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void initReport() {
        cleanupOldReports("./report", 10);

        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./report/ExecutionSummaryReport" + timestamp + ".html");
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Test Execution Report");
            sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("Tester", "Your Name"); // Replace with your name
            extent.setSystemInfo("Environment", "QA"); // Replace with your environment details
        }
    }

    // Create a test node
    public static void createTest(String testName, String description) {
        ExtentTest extentTest = extent.createTest(testName, description);
        test.set(extentTest);
    }

    // Get the current test node
    public static ExtentTest getTest() {
        return test.get();
    }

    // Flush the report
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    // Cleanup old reports
    public static void cleanupOldReports(String directoryPath, int maxFilesToKeep) {
        File reportsDir = new File(directoryPath);
        File[] files = reportsDir.listFiles();

        if (files != null && files.length > maxFilesToKeep) {
            // Sort files by last modified date (most recent first)
            Arrays.sort(files, (f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified()));

            // Delete older files beyond the allowed limit
            for (int i = maxFilesToKeep; i < files.length; i++) {
                if (files[i].isFile()) { // Ensure it's a file before deleting
                    files[i].delete();
                }
            }
        }
    }

}