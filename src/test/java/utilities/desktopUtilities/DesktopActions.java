package utilities.desktopUtilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import utilities.DriverFactory;
import utilities.LogClass;
import utilities.ReportManager;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class DesktopActions {

    public static Actions actions = new Actions(DriverFactory.getInstance().getWindowDriver());

    /**
     * =============================================================================
     * Method: waitForVisible| 
     * Description: This method will wait for element & it will check every 5 sec if its
     * present or not until 100 sec | Parameters: locator | Return: element
     * =============================================================================
     */
    @SuppressWarnings("deprecation")
    public static WebElement waitForVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getWindowDriver(), 120);
        wait.pollingEvery(Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * =============================================================================
     * Method: isClickable| 
     * Description: This method will wait for element & it will check every 5 sec if its
     * present or not until 100 sec | Parameters: locator | Return: element
     * =============================================================================
     */
    @SuppressWarnings("deprication")
    public static WebElement isClickable(By locator){
        WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getWindowDriver(), 120);
        wait.pollingEvery(Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * =============================================================================
     * Method: Click|  Description: This
     * method will perform click operation | Parameters: locator, info | Return: none
     * =============================================================================
     */
    public static void click(By locator, String info) {
        WebElement elm = waitForVisible(locator);
        isClickable(locator);
        elm.click();
        ReportManager.logInfo("Clicked on - " + info);
        LogClass.logInfo("Clicked on - " + info);
    }

    public static void click(By locator) {
        WebElement elm = waitForVisible(locator);
        isClickable(locator);
        elm.click();
    }

    /**
     * =============================================================================
     * Method: Click|  Description: This
     * method will perform double click operation | Parameters: locator, info | Return: none
     * =============================================================================
     */
    public static void double_Click(By locator, String info) {
        WebElement elm = waitForVisible(locator);
        actions.doubleClick(elm).build().perform();
        ReportManager.logInfo("Double Clicked on - " + info);
        LogClass.logInfo("Double Clicked on - " + info);
    }
    /**
     * =============================================================================
     * Method: sendKeys|  Description:
     * This method will enter given text based on the element | Parameters: locator, text |
     * Return: none
     * =============================================================================
     */
    public static void sendKeys(By locator, String text) {
        WebElement elm = waitForVisible(locator);
        elm.clear();
        elm.sendKeys(text);
        ReportManager.logInfo("Entered text -  <b style=\"color:green;\">" + text + "</b>");
        LogClass.logInfo("Entered text :" + text);
    }

    /**
     * =============================================================================
     * Method: sendKeys|  Description:
     * This method will enter given text based on the element | Parameters: locator, text |
     * Return: none
     * =============================================================================
     */
    public static void sendKeys(By locator, String text,String info) {
        WebElement elm = waitForVisible(locator);
        elm.sendKeys(text);
        ReportManager.logInfo("Entered " + info + " : <b style=\"color:green;\">" + text + "</b>");
        LogClass.logInfo("Entered " + info + " :" + text);
    }

    /**
     * =============================================================================
     * Method: clearAndSendKeys| 
     * Description: This method clear text in text box and given text based on the element |
     * Parameters: locator, text | Return: none
     * =============================================================================
     */
    public void clearAndSendKeys(By locator, String text) {
        WebElement elm = waitForVisible(locator);
        elm.clear();
        elm.sendKeys(text);
        ReportManager.logInfo("Entered text - " + text);
        LogClass.logInfo("Entered text - " + text);
    }

    /**
     * =============================================================================
     * Method: getAttributeByInfo| 
     * Description: This method returns current value of attribute using element |
     * Parameters: locator, attribute, info | Return: attribute_value string type
     * =============================================================================
     */

    public static String getAttributeByInfo(By element, String attribute, String info) throws Exception {

        WebElement ele = waitForVisible(element);
        String attribute_value = ele.getAttribute(attribute);
        ReportManager.logInfo(" " + info + "" + attribute_value);
        LogClass.logInfo(" " + info + " " + attribute_value);
        return attribute_value;
    }

    /**
     * =============================================================================
     * Method: getAttributeBy| 
     * Description: This method returns current value of attribute using element |
     * Parameters: locator, attribute | Return: attribute_value string type
     * =============================================================================
     */
    public static String getAttributeBy(By element, String attribute) {
        WebElement ele = waitForVisible(element);
        return ele.getAttribute(attribute);
    }

    /**
     * =============================================================================
     * Method: enter| 
     * Description: This method will perform keyboard enter action using
     * respective element locator | Parameters: element | Return: none
     * =============================================================================
     */

    public static void enter(By element) {
        WebElement ele = waitForVisible(element);
        ele.submit();
    }

    /**
     * =============================================================================
     * Method: textClear|  Description:
     * textClear method is used to clear the text using respective element locator |
     * Parameters: element | Return: none
     * =============================================================================
     */
    public static void textClear(By element) throws Exception {
        WebElement ele = waitForVisible(element);
        ele.clear();
        ele.clear();
        ele.clear();
        ele.clear();
        ele.clear();
        ele.clear();
        ele.clear();
        ele.clear();
        ele.clear();
        ele.clear();
    }

    /**
     * =============================================================================
     * Method: img_Click|  Description:
     * This method is used to click on respective matching image | Parameters: image |
     * Return: none
     * =============================================================================
     */
    public static void img_Click(String img) throws Exception {
        Thread.sleep(1000);
        Pattern pattern = new Pattern(img);
        Screen s = new Screen();
        s.click(pattern);
        Thread.sleep(1000);
    }

    public static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    /**
     * =============================================================================
     * Method: sendKeys_Robot|  Description:
     * This method enters given text based on the element using Robot Class |
     * Parameters: locator, text | Return: none
     * =============================================================================
     */
    public static void sendKeys_Robot(By ele,String data) throws Exception
    {
        DriverFactory.getInstance().getWindowDriver().findElement(ele).clear();
        DriverFactory.getInstance().getWindowDriver().findElement(ele).sendKeys("");
        Robot robot = new Robot();
        setClipboardData(data);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    /**
     * =============================================================================
     * Method: enter| 
     * Description: This method will perform keyboard enter action for
     * respective element locator using Robot class | Parameters: element | Return: none
     * =============================================================================
     */
    public static void enter_Robot() throws Exception
    {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
    }

    /**
     * =============================================================================
     * Method: pressKeyboardValues| 
     * Description: This method will press keyboard keys | Parameters: locator, text |
     * Return: none
     * =============================================================================
     *
     * @throws InterruptedException
     */
    public static void pressKeyboardValues(Keys value) throws InterruptedException {
        actions.sendKeys(value).build().perform();
        ReportManager.logInfo("Performed keyboard action ");
        LogClass.logInfo("Performed keyboard action ");
    }

}
