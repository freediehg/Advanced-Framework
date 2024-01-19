package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.*;
public class DesktopEvent implements ITestListener {

    InitDriver initDriver = new InitDriver();

    /**
     * =============================================================================
     * Method: onTestStart | Description: This method executes before every test starts |
     * Parameters: iTestResult | Return: none
     * =============================================================================
     */
    @Override
    public void onTestStart(ITestResult iTestResult) {
        ReportManager.startTest(iTestResult.getMethod().getMethodName(),
                iTestResult.getMethod().getDescription(), ConfigReader.getValue("Execution_Desktop"));
        try {
            initDriver.startDesktopDriver();
            VideoRecorder.startRecord(iTestResult.getMethod().getMethodName());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * =============================================================================
     * Method: onTestSuccess |
     * Description: This method capture success testcase details generate logs |
     * Parameters: iTestResult | Return: none
     * =============================================================================
     */
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        try {
            LogClass.logInfo("Test Success : " + iTestResult.getMethod().getMethodName());
            ReportManager.logPass("Test Success : " + iTestResult.getMethod().getMethodName());
            ReportManager.endCurrentTest();
            initDriver.tearDownDesktopDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * =============================================================================
     * Method: onTestFailure |
     * Description: This method capture failure testcase details generate logs |
     * Parameters: iTestResult | Return: none
     * =============================================================================
     */
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            LogClass.logError("Test Fail : " + iTestResult.getMethod().getMethodName());
            ReportManager.logFail("Test Fail : " + iTestResult.getMethod().getMethodName());
            ReportManager.logFail(iTestResult.getThrowable().getMessage());
            ReportManager.logScreenshot(DriverFactory.getInstance().getWindowDriver(), "Failure Screenshot", "Fail");
            VideoRecorder.stopRecord();
            initDriver.tearDownDesktopDriver();
            ReportManager.endCurrentTest();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * =============================================================================
     * Method: onTestSkipped |
     * Description: This method capture skipped testcase details generate logs |
     * Parameters: iTestResult | Return: none
     * =============================================================================
     */
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        try {
            ReportManager.logSkip(iTestResult.getMethod().getMethodName() + " is skipped");
            ReportManager.endCurrentTest();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }
}
