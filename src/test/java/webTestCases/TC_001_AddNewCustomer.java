package webTestCases;

import listeners.SuiteEvent;
import listeners.WebEvent;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ExcelReader;
import utilities.LogClass;
import utilities.ReportManager;
import webPages.HomePage;
import webPages.LoginPage;

@Listeners({SuiteEvent.class, WebEvent.class})
public class TC_001_AddNewCustomer {

    @Test(description = "Add New Customer")
    public void AddNew_Customer() throws Exception {
        LogClass.logClass("Add New Customer");
        ExcelReader.setMapData(ConfigReader.getValue("testDataPath"),
                "TestData", "TC_001_AddNewCustomer");
        LoginPage.login();
        HomePage.addNewCustomer();
        LogClass.logInfo("Customer Added Successfully");
        ReportManager.logPass("Customer Added Successfully");
    }

}
