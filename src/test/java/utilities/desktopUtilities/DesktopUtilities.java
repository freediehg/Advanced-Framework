package utilities.desktopUtilities;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class DesktopUtilities {

    private static final String TASKLIST = "tasklist";
    private static final String KILL = "taskkill /F /IM ";
    static Desktop desktop;

    /**
     * =============================================================================
     * Method: isProcessRunning |
     * Description: This method checks if winium driver is running or not|
     * Parameters: serviceName | Return: true or false
     * =============================================================================
     */
    public static boolean isProcessRunning(String serviceName) throws Exception {
        Process p = Runtime.getRuntime().exec(TASKLIST);
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains(serviceName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * =============================================================================
     * Method: killProcess |
     * Description: This method is used to kill the driver process if already running |
     * Parameters: serviceName | Return: none
     * =============================================================================
     * @throws Exception
     */
    public static void killProcess(String serviceName) throws Exception {
        if (isProcessRunning(serviceName)) {
            Runtime.getRuntime().exec(KILL + serviceName);
            System.out.println("*****************" + serviceName + " killed successfully*********************");

        } else
            System.out.println("*****************" + serviceName + " killed successfully*********************");
    }

    /**
     * startApplication
     *
     * @Param serviceName
     *
     * @Param appPath
     */
    /**
     * =============================================================================
     * Method: startApplication | Description: This method is used to start application |
     * Parameters: serviceName | Return: none
     * =============================================================================
     */
    public static void startApplication(String serviceName, String appPath) throws Exception {
        if (!isProcessRunning(serviceName)) {
            desktop = Desktop.getDesktop();
            desktop.open(new File(appPath));
            System.out.println("*****************winium launched*********************");
            Thread.sleep(3000);
        } else
            System.out.println("*****************winium already launched*********************");
    }

}
