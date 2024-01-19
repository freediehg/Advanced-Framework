package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ReportManager {

    public static ExtentReports extentReports;

    public static ExtentSparkReporter sparkReporter;
    public static Map<Long, ExtentTest> extentTest = new HashMap<>();

    /**
     * =============================================================================
     * Method: startReport | Description: This method start extent report |
     * Parameters: none | Return: none
     * =============================================================================
     */
    public static void startReport(){
        if(Objects.isNull(extentReports)) {
            String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
            String dateStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
            extentReports = new ExtentReports();
            sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/"+dateStamp+"/"+"Automation Report-"+timeStamp+".html");
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Host Name", "Reliance");
            extentReports.setSystemInfo("Environment", "Pre-Prod");
            extentReports.setSystemInfo("User Name", "test");
            sparkReporter.config().setDocumentTitle("Reliance POS Application");
            sparkReporter.config().setReportName("Reliance POS Application Report");
            sparkReporter.config().setTheme(Theme.DARK);
        }
    }

    /**
     * =============================================================================
     * Method: startTest| Description: This method will start test with current thread |
     * Parameters: testName, description, categories | Return: none
     * =============================================================================
     */
    public static void startTest(String testName, String description, String categories) {
        ExtentTest Test = extentReports.createTest(testName, description);
        Test.assignCategory(categories);
        extentTest.put(Thread.currentThread().getId(),Test);
    }

    /**
     * =============================================================================
     * Method: logPass | Description: This method will capture screenshot in base64 format |
     * Parameters: message | Return: none
     * =============================================================================
     */
    public static void logScreenshot(WebDriver driver, String message, String status) throws IOException {
        if (status.contains("Info")) {
            getCurrentTest().info(message, MediaEntityBuilder.createScreenCaptureFromBase64String(
                    ScreenshotUtility.takeScreenshot(driver)).build());
        } else if (status.contains("Fail")) {
            getCurrentTest().fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(
                    ScreenshotUtility.takeScreenshot(driver)).build());
        }
    }

    /**
     * =============================================================================
     * Method: logPass | Description: This method will generate pass log message |
     * Parameters: message | Return: none
     * =============================================================================
     */
    public static void logPass(String message) {
        getCurrentTest().log(Status.PASS, message);
    }

    /**
     * =============================================================================
     * Method: logFail | Description: This method will generate failure log method |
     * Parameters: message | Return: none
     * =============================================================================
     */
    public static void logFail(String message) {
        getCurrentTest().log(Status.FAIL, message);
    }

    public static void logSkip(String message) {
        getCurrentTest().log(Status.SKIP, message);
    }

    /**
     * =============================================================================
     * Method: logInfo | Description: This method will attach log message to the report |
     * Parameters: message | Return: none
     * =============================================================================
     */
    public static void logInfo(String message) {
        getCurrentTest().log(Status.INFO, message);
    }

    /**
     * =============================================================================
     * Method: getCurrentTest |
     * Description: This method will return instance of current extent test |
     * Parameters: None | Return: ExtentTest
     * =============================================================================
     */
    public static ExtentTest getCurrentTest(){
        return extentTest.get(Thread.currentThread().getId());
    }

    /**
     * =============================================================================
     * Method: endCurrentTest| Description: This method will end current test |
     * Parameters: none | Return: none
     * =============================================================================
     */
    public static void endCurrentTest(){
        getCurrentTest().getExtent().flush();
        extentTest.remove(Thread.currentThread().getId());
    }

    /**
     * =============================================================================
     * Method: endReport | Description: This method will close the report |
     * Parameters: none | Return: none
     * =============================================================================
     */
    public static void flushReports() {
        if(Objects.nonNull(extentReports)) {
            extentReports.flush();
        }
    }

}
