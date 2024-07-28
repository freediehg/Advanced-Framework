package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import utilities.ConfigReader;
import utilities.InitDriver;
import utilities.ReportManager;
import utilities.webUtilities.WebActions;

public class StepDefinitions {

    InitDriver initDriver = new InitDriver();

    public static By txtLogin = By.name("uid");
    public static By txtPassword = By.name("password");
    public static By btnLogin = By.name("btnLogin");

    @Before
    public void before(){
        ReportManager.startReport();
    }

    @Given("User has launched demo site url")
    public void user_has_launched_demo_site_url() throws Exception {
        initDriver.startWebDriver();
    }

    @Given("User has entered valid credentials")
    public void user_has_entered_valid_credentials(String UserName, String Password) {
        WebActions.sendKeys(txtLogin, ConfigReader.getValue("userId"));
        WebActions.sendKeys(txtPassword, ConfigReader.getValue("password"));
    }

    @Given("User clicks on login")
    public void user_clicks_on_login() {
        WebActions.Click(btnLogin, "Login");
    }

//    @Then("User verifies he is on login page")
//    public void user_verifies_he_is_on_login_page() {
//        throw new io.cucumber.java.PendingException();
//    }
//
//    @And("User has entered invalid credentials")
//    public void userHasEnteredInvalidCredentials() {
//    }
}
