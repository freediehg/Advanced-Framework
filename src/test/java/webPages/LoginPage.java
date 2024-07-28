package webPages;

import org.openqa.selenium.By;
import utilities.ConfigReader;
import utilities.DriverFactory;
import utilities.LogClass;
import utilities.webUtilities.WebActions;

public class LoginPage {

    private static final By txtLogin = By.name("uid");
    private static final By txtPassword = By.name("password");
    private static final By btnLogin = By.name("btnLogin");
    private static final By lnkHere = By.linkText("here");
    private static final By txtEmailId = By.cssSelector("input[name='emailid']");
    private static final By btnSubmit = By.cssSelector("input[value='Submit']");
    private static final By lblUsername = By.xpath("//td[contains(text(),'User ID')]/following-sibling::td");
    private static final By lblPassword = By.xpath("//td[contains(text(),'Password')]/following-sibling::td");
    private static final By btnNewCustomer = By.linkText("New Customer");

    public static void login(){
        LogClass.logMethod("Login");
        WebActions.sendKeys(txtLogin, ConfigReader.getValue("userId"));
        WebActions.sendKeys(txtPassword, ConfigReader.getValue("password"));
        WebActions.Click(btnLogin, "Login");
        try {
            WebActions.waitForVisible(btnNewCustomer);
            Thread.sleep(1000);
        }
        catch (Exception e){
            WebActions.Click(lnkHere, "here");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            DriverFactory.getInstance().getWebDriver().navigate().to("https://www.demo.guru99.com");
            WebActions.sendKeys(txtEmailId, "test@abc.com");
            WebActions.Click(btnSubmit, "Submit");
            String userName = WebActions.getText(lblUsername, "Username");
            String password = WebActions.getText(lblPassword, "Password");
            DriverFactory.getInstance().getWebDriver().navigate().to(ConfigReader.getValue("url"));
            WebActions.sendKeys(txtLogin, userName);
            WebActions.sendKeys(txtPassword, password);
            WebActions.Click(btnLogin, "Login");
        }
    }

}
