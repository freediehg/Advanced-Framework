package webPages;

import com.ibm.icu.impl.duration.impl.DataRecord;
import org.openqa.selenium.By;
import org.testng.Assert;
import utilities.DriverFactory;
import utilities.ExcelReader;
import utilities.LogClass;
import utilities.webUtilities.WebActions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Date;

public class HomePage {

    public static By btnNewCustomer = By.linkText("New Customer");
    public static By txtName = By.name("name");
    public static By radioBtnMale = By.xpath("//input[@value='m']");
    public static By radioBtnFemale = By.xpath("//input[@value='f']");
    public static By txtDate = By.xpath("//input[@id='dob']");
    public static By txtAddress = By.xpath("//textarea[@name='addr']");
    public static By txtCity = By.xpath("//input[@name='city']");
    public static By txtState = By.xpath("//input[@name='state']");
    public static By txtPin = By.xpath("//input[@name='pinno']");
    public static By txtMobileNo = By.xpath("//input[@name='telephoneno']");
    public static By txtEmail = By.xpath("//input[@name='emailid']");
    public static By txtPassword = By.xpath("//input[@name='password']");
    public static By btnSubmit = By.xpath("//input[@name='sub']");
    public static By btnReset = By.xpath("//input[@name='res']");
    public static By lblHeading = By.xpath("//p[@class='heading3']");
    public static By lblCustomerId = By.xpath("//td[normalize-space()='Customer ID']//following-sibling::td");
    public static By lblCustomerName = By.xpath("//td[normalize-space()='Customer Name']//following-sibling::td");
    public static By lblGender = By.xpath("//td[normalize-space()='Gender']//following-sibling::td");
    public static By lblBirthDate = By.xpath("//td[normalize-space()='Birthdate']//following-sibling::td");
    public static By lblAddress = By.xpath("//td[normalize-space()='Address']//following-sibling::td");
    public static By lblCity = By.xpath("//td[normalize-space()='City']//following-sibling::td");
    public static By lblState = By.xpath("//td[normalize-space()='State']//following-sibling::td");
    public static By lblPin = By.xpath("//td[normalize-space()='Pin']//following-sibling::td");
    public static By lblMobileNo = By.xpath("//td[normalize-space()='Mobile No.']//following-sibling::td");
    public static By lblEmail = By.xpath("//td[normalize-space()='Email']//following-sibling::td");
    public static By btnLogout = By.linkText("Log out");

    public static void addNewCustomer() throws AWTException {
        LogClass.logMethod("Add Customer");
        WebActions.Click(btnNewCustomer, "New Customer");
        if (!DriverFactory.getInstance().getWebDriver().getCurrentUrl().equals("https://demo.guru99.com/v4/manager/addcustomerpage.php")) {
            WebActions.navigateToUrl("https://demo.guru99.com/v4/manager/addcustomerpage.php");
        }
        WebActions.sendKeys(txtName, ExcelReader.dataMap.get("CustomerName"));
        if(!(ExcelReader.dataMap.get("Gender") == null) &&
                ExcelReader.dataMap.get("Gender").equalsIgnoreCase("Male")){
            WebActions.Click(radioBtnMale, "Male");
        } else if(!(ExcelReader.dataMap.get("Gender") == null) &&
                ExcelReader.dataMap.get("Gender").equalsIgnoreCase("Female")){
            WebActions.Click(radioBtnFemale, "Female");
        }
        Date date = new Date();
        WebActions.sendKeys(txtDate, String.valueOf(date));
        Robot rb = new Robot();
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        WebActions.sendKeys(txtAddress, ExcelReader.dataMap.get("Address"));
        WebActions.sendKeys(txtCity, ExcelReader.dataMap.get("City"));
        WebActions.sendKeys(txtState, ExcelReader.dataMap.get("State"));
        WebActions.sendKeys(txtPin, ExcelReader.dataMap.get("Pin"));
        WebActions.sendKeys(txtMobileNo, Long.toString((long) (Math.random() * 10000000000L)));
        String email = ExcelReader.dataMap.get("Email");
        String random_email = email.split("@")[0] +
                (long) (Math.random() * 10000L) + "@" + email.split("@")[1];
        WebActions.sendKeys(txtEmail, random_email);
        WebActions.sendKeys(txtPassword, "test" + "@" + (long) (Math.random() * 10000L));
        WebActions.Click(btnSubmit, "Submit");
        WebActions.waitForVisible(lblCustomerId);
        Assert.assertEquals(WebActions.getText(lblHeading, ""), "Customer Registered Successfully!!!");
    }

}
