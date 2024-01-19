package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class ScreenshotUtility {

    /**
     * =============================================================================
     * Method: takeScreenshot |
     * Description: This method takes base64 Screenshot | Parameters: Driver |
     * Return: none
     * =============================================================================
     */
    public static String takeScreenshot(WebDriver driver) throws IOException {
        return (((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64));
    }

}
