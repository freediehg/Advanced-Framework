package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.DriverFactory;
import utilities.InitDriver;
import utilities.LogClass;
import utilities.ReportManager;

public class MobileEvent implements ITestListener {
    InitDriver initDriver = new InitDriver();

    /**
     * =============================================================================
     * Method: onTestStart | Description: This method executes before every test starts |
     * Parameters: arg0, text | Return: none
     * =============================================================================
     */
    @Override
    public void onTestStart(ITestResult iTestResult) {
        ReportManager.startTest(iTestResult.getMethod().getMethodName(),
                iTestResult.getMethod().getDescription(), "Mobile Application");
        try {
            initDriver.startMobileDriver();  //Initializing mobile driver
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //	This method captures success testcase details and generate the logs
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
            LogClass.logInfo("Test Success : " + iTestResult.getMethod().getMethodName());
            ReportManager.logPass("Test Success : " + iTestResult.getMethod().getMethodName());
            ReportManager.endCurrentTest();
            initDriver.tearDownMobileDriver();
    }

    /**
     * =============================================================================
     * Method: onTestFailure |
     * Description: This method capture failure testcase details generate logs |
     * Parameters: iTestResult, text | Return: none
     * =============================================================================
     */
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            LogClass.logInfo("Test Fail : " + iTestResult.getMethod().getMethodName());
            ReportManager.logFail("Test Fail : " + iTestResult.getMethod().getMethodName());
            ReportManager.logFail(iTestResult.getThrowable().getMessage());
            ReportManager.logScreenshot(DriverFactory.getInstance().getMobileDriver(), "Failure screenshot", "Fail");
            initDriver.tearDownMobileDriver();
            ReportManager.endCurrentTest();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * =============================================================================
     * Method: onTestFailure |
     * Description: This method captures skipped testcase details and generate the logs |
     * Parameters: iTestResult, text | Return: none
     * =============================================================================
     */
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        ReportManager.logSkip(iTestResult.getMethod().getMethodName() + " is skipped");
        ReportManager.endCurrentTest();
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
