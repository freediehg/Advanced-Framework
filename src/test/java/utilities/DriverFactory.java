package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import setup.Constants;
import utilities.desktopUtilities.DesktopUtilities;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static DriverFactory instance = null;
    ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    ThreadLocal<AndroidDriver<AndroidElement>> appiumDriver = new ThreadLocal<>();
    ThreadLocal<WiniumDriver> windowDriver = new ThreadLocal<WiniumDriver>();
    public static DriverFactory getInstance() {
        if (instance == null) {
            instance = new DriverFactory();
        }
        return instance;
    }

    /**
     * =============================================================================
     * Method: setWebDriver |
     * Description: This method starts the web driver |
     * Parameters: none | Return: none
     * =============================================================================
     */
    public final void setWebDriver(String browser) throws Exception {
        switch (browser) {
            case "Chrome":
                ChromeOptions options = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                webDriver.set(new ChromeDriver());
                getWebDriver().manage().window().maximize();
                getWebDriver().manage().deleteAllCookies();
                checkHTTPResponse();
                break;

                case "Firefox":
                    WebDriverManager.firefoxdriver().setup();
                    webDriver.set(new FirefoxDriver());
                    getWebDriver().manage().window().maximize();
                    getWebDriver().manage().deleteAllCookies();
                    checkHTTPResponse();
                    break;

                case "Edge":
                    WebDriverManager.edgedriver().setup();
                    webDriver.set(new EdgeDriver());
                    getWebDriver().manage().window().maximize();
                    getWebDriver().manage().deleteAllCookies();
                    checkHTTPResponse();
                    break;

                case "IE":
                    WebDriverManager.iedriver().setup();
                    webDriver.set(new InternetExplorerDriver());
                    getWebDriver().manage().window().maximize();
                    getWebDriver().manage().deleteAllCookies();
                    checkHTTPResponse();
                    break;
            }
    }

    /**
     * =============================================================================
     * Method: getWebDriver |
     * Description: This method will return web driver instance | Parameters: none
     * | Return: WebDriver
     * =============================================================================
     */
    public WebDriver getWebDriver() {
        return webDriver.get();
    }

    /**
     * =============================================================================
     * Method: unloadWebDriver |
     * Description: This method will remove the current instance of web driver |
     * Parameters: none | Return: WebDriver
     * =============================================================================
     */
    public void unloadWebDriver(){
        webDriver.remove();
    }

    /**
     * =============================================================================
     * Method: checkHTTPResponse |
     * Description: This method will validate the http status code |
     * Parameters: none | Return: WebDriver
     * =============================================================================
     */
    public void checkHTTPResponse() {
        int statusCode;
        try {
            URL url = new URL(ConfigReader.getValue("url"));
            getWebDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            statusCode = httpURLConnection.getResponseCode();
            if (statusCode == 200) {
                ReportManager.logInfo("Page loaded successfully");
            } else {
                ReportManager.logFail("Page did not load successfully: Response code " + statusCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * =============================================================================
     * Method: setMobileDriver |
     * Description: This method start the appium driver respective mobile |
     * Parameters: none | Return: none
     * =============================================================================
     */
    public final void setMobileDriver() throws Exception {
        String platform = ConfigReader.getValue("platform");
        String udid = ConfigReader.getValue("udid");
        String systemPort = ConfigReader.getValue("port");
        String[] platformInfo = platform.split(" ");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformInfo[0]);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformInfo[1]);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigReader.getValue("deviceName"));
        capabilities.setCapability(MobileCapabilityType.UDID, udid);
        capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
        capabilities.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, false);
        capabilities.setCapability("appPackage", ConfigReader.getValue("appPackage"));
        capabilities.setCapability("appActivity", ConfigReader.getValue("appActivity"));
        appiumDriver.set(new AndroidDriver<AndroidElement>((new URL(Constants.appiumPath)), capabilities));
        getMobileDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    /**
     * =============================================================================
     * Method: getMobileDriver |
     * Description: This method will return AppiumDriver instance | Parameters: none
     * | Return: AppiumDriver
     * =============================================================================
     */
    public AndroidDriver<AndroidElement> getMobileDriver() {
        return appiumDriver.get();
    }

    /**
     * =============================================================================
     * Method: unloadMobileDriver |
     * Description: This method will remove the current instance of android driver |
     * Parameters: none | Return: none
     * =============================================================================
     */
    public void unloadMobileDriver(){
        appiumDriver.remove();
    }

    /**
     * =============================================================================
     * Method: setDesktopDriver | Description: This method will return a Winium Driver instance|
     * Parameters: none | Return: WiniumDriver
     * =============================================================================
     */
    public WiniumDriver getWindowDriver() {
        return windowDriver.get();
    }

    /**
     * =============================================================================
     * Method: setDesktopDriver |
     * Description: This method start the winium driver respective desktop
     * application | Parameters: none | Return: none
     * =============================================================================
     */
    public final void setDesktopDriver() throws Exception {
        DesktopUtilities.startApplication("Winium.Desktop.Driver.exe",Constants.winiumDriverPath);
        DesktopOptions options = new DesktopOptions();
        options.setApplicationPath(ConfigReader.getValue("ReliancePOSPath"));
        windowDriver.set(new WiniumDriver(new URL(Constants.localHost), options));
    }

    /**
     * =============================================================================
     * Method: unloadDesktopDriver |
     * Description: This method will remove the current instance of winium driver |
     * Parameters: none | Return: none
     * =============================================================================
     */
    public void unloadDesktopDriver(){
        windowDriver.remove();
    }
}
