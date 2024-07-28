package utilities;

import utilities.desktopUtilities.DesktopUtilities;
import utilities.mobileUtilities.MobileUtilities;

import java.util.Objects;

public class InitDriver {

    DriverFactory driverFactory = new DriverFactory();
    MobileUtilities mobileUtilities = new MobileUtilities();

    /**
     * =============================================================================
     * Method: startWebDriver | Description: This method start web driver |
     * Parameters: none | Return: none
     * =============================================================================
     */
    public void startWebDriver() throws Exception {
        DriverFactory.getInstance().setWebDriver(ConfigReader.getValue("browser"));
        DriverFactory.getInstance().getWebDriver().get(ConfigReader.getValue("url"));
    }

    /**
     * =============================================================================
     * Method: tearDownWebDriver | Description: This method close the web driver  |
     * Parameters: none | Return: none
     * =============================================================================
     */
    public void tearDownWebDriver() throws Exception {
        if (Objects.nonNull(DriverFactory.getInstance().getWebDriver())) {
            DriverFactory.getInstance().getWebDriver().quit();
            driverFactory.unloadWebDriver();
        }
    }

    /**
     * =============================================================================
     * Method: startMobileDriver |
     * Description: This method start the appium driver respective mobile |
     * Parameters: none | Return: none
     * =============================================================================
     */
    public void startMobileDriver() throws Exception {
        String port = ConfigReader.getValue("port");
        int portValue = Integer.parseInt(port);
        if (mobileUtilities.checkIfServerIsRunning(portValue)) {
            System.out.println("Appium Server already running on Port - " + port);
        } else {
            mobileUtilities.startServer();
            System.out.println("Appium Server started running on Port - " + port);
        }
        DriverFactory.getInstance().setMobileDriver();
    }

    /**
     * =============================================================================
     * Method: tearDownMobileDriver |
     * Description: This method close the app and the driver | Parameters: none | Return: none
     * =============================================================================
     */
    public void tearDownMobileDriver() {
        if (Objects.nonNull(DriverFactory.getInstance().getMobileDriver())) {
            DriverFactory.getInstance().getMobileDriver().closeApp();
            DriverFactory.getInstance().getMobileDriver().quit();
            driverFactory.unloadMobileDriver();
        }
    }

    /**
     * =============================================================================
     * Method: startDesktopDriver |
     * Description: This method start the winium driver respective desktop
     * application | Parameters: none | Return: none
     * =============================================================================
     * @throws Exception
     */
    public void startDesktopDriver() throws Exception {
        DriverFactory.getInstance().setDesktopDriver();
    }

    /**
     * =============================================================================
     * Method: tearDownDesktopDriver | Description: This method close driver |
     * Parameters: none | Return: none
     * =============================================================================
     * @throws Exception
     */
    public void tearDownDesktopDriver() throws Exception {
        if (Objects.nonNull(DriverFactory.getInstance().getWindowDriver())) {
            DesktopUtilities.killProcess("R-POS.exe");
            DriverFactory.getInstance().getWindowDriver().quit();
            driverFactory.unloadDesktopDriver();
        }
    }

}
