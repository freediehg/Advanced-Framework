package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.ConfigReader;
import utilities.DriverFactory;
import utilities.InitDriver;
import utilities.ReportManager;

public class WebEvent implements ITestListener {

    InitDriver initDriver = new InitDriver();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        ReportManager.startTest(iTestResult.getMethod().getMethodName(),
                iTestResult.getMethod().getDescription(), "WebApplication");
        try {
            initDriver.startWebDriver();//Initializing the Chrome browser & launching URL
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test Success: " + iTestResult.getMethod().getMethodName());
        ReportManager.logPass("Test case passed");
        ReportManager.endCurrentTest();
        try {
            initDriver.tearDownWebDriver();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test Fail: " + iTestResult.getMethod().getMethodName());
        ReportManager.logFail("Test case Fail");
        ReportManager.logInfo(iTestResult.getThrowable().getMessage());

        try {
            ReportManager.logScreenshot(DriverFactory.getInstance().getWebDriver(),"Failure screenshot", "Fail");
            initDriver.tearDownWebDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ReportManager.endCurrentTest();
    }

    @Override
    public void onTestSkipped(ITestResult arg0) {
        // TODO Auto-generated method stub
    }

 @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub
    }

}

