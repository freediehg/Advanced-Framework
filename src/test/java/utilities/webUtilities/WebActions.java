package utilities.webUtilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverFactory;
import utilities.LogClass;
import utilities.ReportManager;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.NoSuchElementException;

public class WebActions {

    public static Robot rb;

    /**
     * =============================================================================
     * Method: waitForVisible |
     * Description: This method will wait for element & it will check every 5 sec if its
     * present or not until 60 sec | Parameters: locator | Return: element
     * =============================================================================
     */
    public static WebElement waitForVisible(By locator){
        WebElement element = null;
        try {
            WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getWebDriver(), 60);
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
        catch(TimeoutException e){
            throw new TimeoutException("Unable to locate Element in specified time interval");
        }
        return element;
    }

    /**
     * =============================================================================
     * Method: isClickable |
     * Description: This method will wait for element & it will check every 5 sec if its
     * clickable or not until 60 sec | Parameters: locator | Return: element
     * =============================================================================
     */
    public static WebElement isClickable(By locator) {
        WebElement element = null;
        try {
            WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getWebDriver(), 60);
            element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        }
        catch (Exception e){
            throw new TimeoutException("Unable to locate Element");
        }
        return element;
    }

    /**
     * =============================================================================
     * Method: getElementFromList |
     * Description: This method will return a webElement from a list of webElements |
     * Parameters: locator, index | Return: element
     * =============================================================================
     */
    public static WebElement getElementFromList(By locator, int index) {
        WebElement element = null;
        try {
            List<WebElement> ele = DriverFactory.getInstance().getWebDriver().findElements(locator);
            element = ele.get(index);
        }
        catch(NoSuchElementException e) {
            ReportManager.logFail("Unable to find element in list");
            throw new NoSuchElementException("Unable to find element in list");
        }
        return element;
    }

    /**
     * =============================================================================
     * Method: Click | Description: This method is used to click an element |
     * Parameters: locator, info | Return: none
     * =============================================================================
     */
    public static void Click(By locator, String info)  {
        try {
            WebElement element = isClickable(locator);
            element.click();
            LogClass.logInfo("Successfully clicked on : " + info);
            ReportManager.logInfo("Successfully clicked on : " + info);
        }
        catch(Exception e) {
            ReportManager.logFail("Unable to find element : " + info);
            throw new StaleElementReferenceException("Unable to find element : " + info + e);
        }
    }

    /**
     * =============================================================================
     * Method: Click | Description: This method is used to click an element using
     * java script executor| Parameters: locator, info | Return: none
     * =============================================================================
     */
    public static void clickWithJavaScriptExecutor(By locator, String info){
        try{
            WebElement element = waitForVisible(locator);
            ((JavascriptExecutor) DriverFactory.getInstance().getWebDriver()).executeScript("arguments[0].click();", element);
        }
        catch (Exception e){
            ReportManager.logFail("Unable to locate element " + info);
            throw new StaleElementReferenceException("Unable to locate element " + info);
        }
    }

    /**
     * =============================================================================
     * Method: getText | Description: This method returns the visible text of an element
     * based on the given locator | Parameters: locator | Return: elmText
     * =============================================================================
     */
    public static String getText(By locator, String info) {
        String elementText = "";
        try {
            WebElement element = waitForVisible(locator);
            elementText = element.getText();
            LogClass.logInfo(info + " : " + elementText);
            ReportManager.logInfo(info + " : " + elementText);
        }
        catch(StaleElementReferenceException e) {
            ReportManager.logFail("Unable to find element " + info);
            throw new StaleElementReferenceException("Unable to find element : " + info + e);
        }
        return elementText;
    }

    /**
     * =============================================================================
     * Method: sendKeys | Description:
     * This method will enter given text based on the element | Parameters: locator, text |
     * Return: none
     * =============================================================================
     */
    public static void sendKeys(By locator, String text) {
        try {
            WebElement element = waitForVisible(locator);
            element.click();
            element.sendKeys(text);
            LogClass.logInfo("Successfully entered text : " + text);
            ReportManager.logInfo("Successfully entered text : " + text);
        }
        catch(StaleElementReferenceException e) {
            ReportManager.logInfo("Unable to find element");
            throw new StaleElementReferenceException("Unable to find element " + e);
        }
        catch (NullPointerException e){
            throw new NullPointerException("Cannot enter empty text");
        }
    }

    /**
     * =============================================================================
     * Method: sendKeys | Description: This method will enter given text based on the element
     * and perform keyboard enter action| Parameters: locator, text |
     * Return: none
     * =============================================================================
     */
    public static void sendKeys_Enter(By locator, String text) {
        try {
            WebElement elm = waitForVisible(locator);
            elm.click();
            elm.sendKeys(text);
            elm.sendKeys(Keys.ENTER);
            ReportManager.logInfo("Successfully Entered text - " + text);
            LogClass.logInfo("Successfully Entered text - " + text);
        }
        catch (StaleElementReferenceException e){
            ReportManager.logFail("Unable to find element : " + locator);
            throw new StaleElementReferenceException("Unable to find element " + e);
        }
        catch (NullPointerException e){
            throw new NullPointerException("Cannot enter empty text");
        }
    }

    /**
     * =============================================================================
     * Method: navigateToUrl | Description: This method is used to navigate to given url |
     * Parameters: url | Return: none
     * =============================================================================
     */
    public static void navigateToUrl(String url){
        try {
            Thread.sleep(5000);
            System.out.println(url);
            DriverFactory.getInstance().getWebDriver().navigate().to(url);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * =============================================================================
     * Method: uploadFile | Description: This method is used to upload a file |
     * Parameters: filepath | Return: none
     * =============================================================================
     */
    public static void uploadFile(String filePath) {
        try {
            rb = new Robot();
            StringSelection ss = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
            rb.delay(2000);
            rb.keyPress(KeyEvent.VK_CONTROL);
            rb.keyPress(KeyEvent.VK_V);
            rb.delay(2000);
            rb.keyRelease(KeyEvent.VK_V);
            rb.keyRelease(KeyEvent.VK_CONTROL);
            rb.delay(2000);
            rb.keyPress(KeyEvent.VK_ENTER);
            rb.keyRelease(KeyEvent.VK_ENTER);
        }
        catch (AWTException e){
            throw new IllegalArgumentException("Please specify valid file path");
        }
    }

}
