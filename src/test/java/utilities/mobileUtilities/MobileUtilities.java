package utilities.mobileUtilities;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import utilities.ConfigReader;

import java.io.IOException;
import java.net.ServerSocket;

public class MobileUtilities {

    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;
    private DesiredCapabilities cap;

    /**
     * =============================================================================
     * Method: startServer | Description: This method starts appium server |
     * Parameters: None | Return:None
     * =============================================================================
     */

    public void startServer() {
        // Set Capabilities
        cap = new DesiredCapabilities();
        cap.setCapability("noReset", "false");
        String port = ConfigReader.getValue("port");
        int portValue = Integer.parseInt(port);
        // Build the Appium service
        builder = new AppiumServiceBuilder();
        builder.withIPAddress("0.0.0.0");
        builder.usingPort(portValue);
        builder.withCapabilities(cap);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

        // Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        service.start();

    }

    /**
     * =============================================================================
     * Method: stopServer | Description: This method stops appium server |
     * Parameters: None | Return:None
     * =============================================================================
     */

    public void stopServer() {
        service.stop();
    }

    /**
     * =============================================================================
     * Method: checkIfServerIsRunning |
     * Description: This method checks appium server is running on port |
     * Parameters: port | Return: boolean value
     * =============================================================================
     */
    public boolean checkIfServerIsRunning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            // If control comes here, then it means that the port is in use
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

}
