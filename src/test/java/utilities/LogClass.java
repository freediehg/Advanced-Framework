package utilities;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogClass {

    private static Logger log=Logger.getLogger(LogClass.class);

    static{
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.setProperty("current.date.time", dateFormat.format(new Date()));
    }

    /**
     * =============================================================================
     * Method: logClass | Description: This method will capture logs at class level |
     * Parameters: Class Name | Return: none
     * =============================================================================
     */
    public static void logClass(String classname) {
        log = Logger.getLogger(classname);
        BasicConfigurator.configure();
        PropertyConfigurator.configure("Log4j.properties");
    }

    /**
     * =============================================================================
     * Method: logMethod | Description: This method will capture logs for each method |
     * Parameters: Method Name | Return: none
     * =============================================================================
     */
    public static void logMethod(String methodName) {
        log = Logger.getLogger(methodName);
        BasicConfigurator.configure();
        PropertyConfigurator.configure("Log4j.properties");
    }

    /**
     * =============================================================================
     * Method: logInfo |
     * Description: logInfo method is used to display customized log message
     * while executing the test Case | Parameters: Log message | Return: none
     * =============================================================================
     */
    public static void logInfo(String info) {
        PropertyConfigurator.configure("Log4j.properties");
        log.info(info);
    }

    /**
     * =============================================================================
     * Method: logError |
     * Description: LogError method is used to display customized error log message
     * while executing the test Case | Parameters: Log message | Return: none
     * =============================================================================
     */
    public static void logError(String msg) {
        PropertyConfigurator.configure("Log4j.properties");
        log.error(msg);
    }

    /**
     * =============================================================================
     * Method: logDebug |
     * Description: logDebug method is used to display customized log message
     * while executing the test Case | Parameters: Log message | Return: none
     * =============================================================================
     */
    public static void logDebug(String msg) {
        log.error(msg);
    }

}
