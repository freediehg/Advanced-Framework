package utilities.mobileUtilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverFactory;
import utilities.LogClass;
import utilities.ReportManager;

import java.time.Duration;
import java.util.List;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class MobileActions {

    static Actions action;
    AndroidDriver<AndroidElement> driver= DriverFactory.getInstance().getMobileDriver();

    /**
     * =============================================================================
     * Method: hideKeyBoard |
     * Description: This method is used for hiding the keyboard
     *  | Parameters: none | Return: none
     * =============================================================================
     */
    public static void hideKeyBoard()
    {
        DriverFactory.getInstance().getMobileDriver().hideKeyboard();
    }

    /**
     * =============================================================================
     * Method: waitForVisible |
     * Description: This method will wait for element & it will check every 5 sec if its
     * present or not until 120 sec | Parameters: locator | Return: element
     * =============================================================================
     */
    @SuppressWarnings("deprecation")
    public static WebElement waitForVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getMobileDriver(), 120);
        wait.pollingEvery(Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * =============================================================================
     * Method: Click | Description: This method is used to click an element |
     * Parameters: locator, info | Return: none
     * =============================================================================
     */
    public static void Click(By locator, String info) {
        WebElement elm = waitForVisible(locator);
        elm.click();
        ReportManager.logInfo("Clicked on - " + info);
        LogClass.logInfo("Clicked on -"+ info);
    }

    public static void Click(By locator) {
        WebElement elm = waitForVisible(locator);
        elm.click();
    }

    public boolean ele_Size(By locator)
    {
        WebElement elm = waitForVisible(locator);
        boolean version = DriverFactory.getInstance().getMobileDriver().findElements(locator).size() > 0;
        return version;

    }

    /**
     * =============================================================================
     * Method: sendKeys | Description:
     * This method will enter given text based on the element | Parameters: locator, text |
     * Return: none
     * =============================================================================
     */
    public static void sendKeys(By locator, String text) {
        WebElement elm = waitForVisible(locator);
        elm.sendKeys(text);
        ReportManager.logInfo("Entered text - " + text);
        LogClass.logInfo("Entered text - " + text);
    }

    /**
     * =============================================================================
     * Method: sendKeys | Description:
     * This method will enter given text based on the element | Parameters: locator, text |
     * Return: none
     * =============================================================================
     */
    public static void sendKeys(By locator, String text,String info) {
        WebElement elm = waitForVisible(locator);
        elm.click();
        elm.sendKeys(text);
        ReportManager.logInfo(info+" <b style=\"color:green;\"> : "+text+"</b>");
        LogClass.logInfo(info+" :"+text);
    }

    /**
     * =============================================================================
     * Method: clear | 
     * Description: This method will clear text in text box |
     * Parameters: locator, text | Return: none
     * =============================================================================
     */
    public static void clear(By locator) {
        WebElement elm = waitForVisible(locator);
        elm.clear();
    }
    /**
     * =============================================================================
     * Method: clearAndSendKeys |
     * Description: This method clear will text in text box after that 
     * enter given text based on the element | Parameters: locator, text | Return: none
     * =============================================================================
     */
    public static void clearAndSendKeys(By locator, String text) {
        WebElement elm = waitForVisible(locator);
        elm.clear();
        elm.sendKeys(text);
        ReportManager.logInfo("Entered text -<b style=\"color:green;\"> " + text+"</b>");
        LogClass.logInfo("Entered text - " + text);
    }

    /**
     * =============================================================================
     * Method: clearAndSendKeys |
     * Description: This method clear will text in text box after that
     * enter given text based on the element | Parameters: locator, text | Return: none
     * =============================================================================
     */
    public static void clearAndSendKeys(By locator, String text,String info) {
        WebElement elm = waitForVisible(locator);
        elm.clear();
        elm.sendKeys(text);
        ReportManager.logInfo(info+"<b style=\"color:green;\"> :" + text+"</b>");
        LogClass.logInfo(info+" : " + text);
    }

    /**
     * =============================================================================
     * Method: getText | Description:
     * This method gets the text of element | Parameters: locator | Return: elmText
     * =============================================================================
     */
    public static String getText(By locator) {
        WebElement elm = waitForVisible(locator);
        String elmText = elm.getText();
        return elmText;
    }

    /**
     * =============================================================================
     * Method: getText | Description:
     * This method gets the text of element | Parameters: locator | Return: elmText
     * =============================================================================
     */
    public static String getText(By locator,String info) {
        WebElement elm = waitForVisible(locator);
        String elmText = elm.getText();
        ReportManager.logInfo(""+info+"<b style=\"color:green;\"> :" + elmText+"</b>");
        LogClass.logInfo(""+info+" : " + elmText);
        return elmText;
    }

    /**
     * =============================================================================
     * Method: getString |  Description:
     * This method gets the text of element | Parameters: locator | Return: elmText
     * =============================================================================
     */
    public static String getString(By locator) {
        WebElement elm = waitForVisible(locator);
        String elmText = elm.getText();
        return elmText;
    }

    /**
     * =============================================================================
     * Method: swipeUp | Description:
     * This method will perform swipe up in mobile using touch action 
     * | Parameters: howManySwipes | Return: none
     * =============================================================================
     */
    public static void swipeUp(int howManySwipes) {
        Dimension size = DriverFactory.getInstance().getMobileDriver().manage().window().getSize();
        // calculate coordinates for vertical swipe
        int startY = (int) (size.height * 0.70);
        int endY = (int) (size.height * 0.30);
        int startX = (size.width / 2);
        try {
            for (int i = 1; i <= howManySwipes; i++) {
                new TouchAction(DriverFactory.getInstance().getMobileDriver())
                        .longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(startX, endY)).release()
                        .perform();
                System.out.println("swipeUp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void swipeUp() {
        Dimension size = DriverFactory.getInstance().getMobileDriver().manage().window().getSize();
        // calculate coordinates for vertical swipe
        int startY = (int) (size.height * 0.70);
        int endY = (int) (size.height * 0.30);
        int startX = (size.width / 2);
        try {
            new TouchAction(DriverFactory.getInstance().getMobileDriver())
                    .longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(startX, endY)).release()
                    .perform();
            System.out.println("swipeUp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * =============================================================================
     * Method: swipeDown | Description:
     * This method will perform swipe down in mobile using touch action 
     * | Parameters: howManySwipes | Return: none
     * =============================================================================
     */
    public static void swipeDown(int howManySwipes) {
        // calculate coordinates for vertical swipe
        Dimension size = DriverFactory.getInstance().getMobileDriver().manage().window().getSize();
        int startY = (int) (size.height * 0.70);
        int endY = (int) (size.height * 0.30);
        int startX = (size.width / 2);
        try {
            for (int i = 1; i <= howManySwipes; i++) {
                new TouchAction(DriverFactory.getInstance().getMobileDriver())
                        .longPress(PointOption.point(startX, endY)).moveTo(PointOption.point(startX, startY)).release()
                        .perform();
                System.out.println("swipeDown");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * =============================================================================
     * Method: swipeRightToLeft |
     * Description: This method will swipe right to left in mobile using touch action | 
     * Parameters: howManySwipes | Return: none
     * =============================================================================
     */
    public static void swipeRightToLeft(int howManySwipes) {
        Dimension size = DriverFactory.getInstance().getMobileDriver().manage().window().getSize();
        // calculate coordinates for horizontal swipe
        int startY = (int) (size.height / 2);
        int startX = (int) (size.width * 0.90);
        int endX = (int) (size.width * 0.10);
        try {
            for (int i = 1; i <= howManySwipes; i++) {
                new TouchAction(DriverFactory.getInstance().getMobileDriver())
                        .longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, startY)).release()
                        .perform();
                System.out.println("swipe right to left");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * =============================================================================
     * Method: swipeLeftToRight |
     * Description: This method will swipe left to right in mobile using touch action| 
     * Parameters: howManySwipes |
     * Return: none
     * =============================================================================
     */
    public static void swipeLeftToRight(int howManySwipes) {
        Dimension size = DriverFactory.getInstance().getMobileDriver().manage().window().getSize();
        // calculate coordinates for horizontal swipe
        int startY = (int) (size.height / 2);
        int startX = (int) (size.width * 0.10);
        int endX = (int) (size.width * 0.90);
        try {
            for (int i = 1; i <= howManySwipes; i++) {
                new TouchAction(DriverFactory.getInstance().getMobileDriver())
                        .longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, startY)).release()
                        .perform();
                System.out.println("swipe left to right");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * =============================================================================
     * Method: swipeUp_FindElementClick |
     * Description: This method will swipe till element found and click the element| 
     * Parameters: howManySwipes, locator | Return: none
     * =============================================================================
     */
    public static void swipeUp_FindElementClick(int howManySwipes, By locator) throws InterruptedException {
        Dimension size = DriverFactory.getInstance().getMobileDriver().manage().window().getSize();
        // calculate coordinates for vertical swipe
        int startY = (int) (size.height * 0.70);
        int endY = (int) (size.height * 0.30);
        int startX = (size.width / 2);
        Thread.sleep(2000);
        try {
            for (int i = 1; i <= howManySwipes; i++) {
                boolean isElmPresent = DriverFactory.getInstance().getMobileDriver().findElements(locator).size() > 0;
                if (isElmPresent) {
                    DriverFactory.getInstance().getMobileDriver().findElement(locator).click();
                    break;
                }
                new TouchAction(DriverFactory.getInstance().getMobileDriver())
                        .longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(startX, endY)).release()
                        .perform();
                System.out.println("swipeUp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * =============================================================================
     * Method: pressKeyboardValues |
     * Description: This method will press keyboard keys | Parameters: locator, text |
     * Return: none
     * =============================================================================
     *
     * @throws InterruptedException
     */
    public static void pressKeyboardValues(Keys value) throws InterruptedException {
        action = new Actions(DriverFactory.getInstance().getMobileDriver());
        action.sendKeys(value).build().perform();
        ReportManager.logInfo("Performed keyboard action ");
        LogClass.logInfo("Performed keyboard action ");
    }

    /**
     * =============================================================================
     * Method: rightToLeftSwipeUsingElement |  
     * Description: This method will swipe right To Left Using Element |
     * Parameters: locator, text | Return: none
     * =============================================================================
     *
     * @param fromLocator
     * @param toLocator
     *
     * @throws InterruptedException
     */
    public static void rightToLeftSwipeUsingElement(By fromLocator, By toLocator) throws InterruptedException {
        List<AndroidElement> from_Location = DriverFactory.getInstance().getMobileDriver().findElements(fromLocator);
        List<AndroidElement> to_Location = DriverFactory.getInstance().getMobileDriver().findElements(toLocator);
        int startX = from_Location.get(0).getLocation().getX() + (from_Location.get(0).getSize().getWidth() / 2);
        int startY = from_Location.get(0).getLocation().getY() + (from_Location.get(0).getSize().getHeight() / 2);
        int endX = to_Location.get(0).getLocation().getX() + (to_Location.get(0).getSize().getWidth() / 2);
        int endY = to_Location.get(0).getLocation().getY() + (to_Location.get(0).getSize().getHeight() / 2);
        new TouchAction(DriverFactory.getInstance().getMobileDriver()).press(point(startX, startY))
                .waitAction(waitOptions(ofMillis(1000))).moveTo(point(endX, endY)).release().perform();

    }

    /**
     * =============================================================================
     * Method: rightToLeftSwipeUsingElement |  
     * Description: This method will swipe right To Left Using Element |
     * Parameters: locator, text | Return: none
     * =============================================================================
     *
     * @param fromLocator
     * @param toLocator
     *
     * @throws InterruptedException
     */
    public static void rightToLeftSwipeUsingWebElement(WebElement fromLocator, WebElement toLocator) throws InterruptedException {
        System.out.println(" swipe element");
        System.out.println("fromLocator : "+fromLocator);
        System.out.println("to locator : " +toLocator);

        WebElement from_Location = fromLocator;
        WebElement to_Location = toLocator;
        int startX = from_Location.getLocation().getX() + (from_Location.getSize().getWidth() / 2);
        int startY = from_Location.getLocation().getY() + (from_Location.getSize().getHeight() / 2);
        int endX = to_Location.getLocation().getX() + (to_Location.getSize().getWidth() / 2);
        int endY = to_Location.getLocation().getY() + (to_Location.getSize().getHeight() / 2);
        new TouchAction(DriverFactory.getInstance().getMobileDriver()).press(point(startX, startY))
                .waitAction(waitOptions(ofMillis(1000))).moveTo(point(endX, endY)).release().perform();

    }

    /**
     * =============================================================================
     * Method: getText | Description:
     * This method will get the text of element | Parameters: locator | Return: elmText
     * =============================================================================
     */
    public static int getTextByInt(By locator) {
        WebElement elm = waitForVisible(locator);
        String elmText = elm.getText();
        int elmIntTxt=Integer.parseInt(elmText);
        ReportManager.logInfo("Successfully get Integer text - <b style=\"color:green;\">" + elmIntTxt+"</b>");
        System.out.println("Successfully get Integer text- " + elmIntTxt);
        return elmIntTxt;
    }

    /**
     * =============================================================================
     * Method: clickUsingCoordinates |
     * Description: This method will perform click operation based on co-ordinates | 
     * Parameters : locator, text | Return: none
     * =============================================================================
     *
     * @param xcoOrdinate
     * @param ycoOrdinate
     * @throws InterruptedException
     */
    public static void clickUsingCoordinates(int xcoOrdinate, int ycoOrdinate) throws InterruptedException {
        new TouchAction(DriverFactory.getInstance().getMobileDriver()).tap(PointOption.point(xcoOrdinate, ycoOrdinate))
                .release().perform();
    }


    /**
     * =============================================================================
     * Method: getElementSizeUsingFindElements |  
     * Description: This method returns size of elements by using
     * find elements | Parameters: locator, text | Return: none
     * =============================================================================
     *
     * @param locator
     *
     * @throws InterruptedException
     */
    public static List<AndroidElement> getElementSizeUsingFindElements(By locator) throws InterruptedException {
        List<AndroidElement> lst_Elm = DriverFactory.getInstance().getMobileDriver().findElements(locator);
        ReportManager.logInfo("Captured element size - " + lst_Elm.size());
        return lst_Elm;
    }

}
