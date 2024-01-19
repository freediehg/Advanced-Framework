package listeners;

import org.testng.IExecutionListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import utilities.ReportManager;

public class SuiteEvent implements ISuiteListener, IExecutionListener {

    /**
     * =============================================================================
     * Method: onExecutionStart | Author: Swapnil Ingale | Date:17 Jan 2023 |
     * Description: This method start the report capturing | Parameters:none |
     * Return: none
     * =============================================================================
     */
    @Override
    public void onExecutionStart() {
        ReportManager.startReport();
    }

    /**
     * =============================================================================
     * Method: onExecutionFinish | Author: Swapnil Ingale | Date:17 Jan 2023 |
     * Description: This method end the report capturing | Parameters:none | Return:
     * none
     * =============================================================================
     */
    @Override
    public void onExecutionFinish() {
        ReportManager.flushReports();
    }

    @Override
    public void onStart(ISuite suite) {

    }

    @Override
    public void onFinish(ISuite suite) {

    }
}
