package webPages;

import org.openqa.selenium.By;
import utilities.ConfigReader;
import utilities.LogClass;
import utilities.webUtilities.WebActions;

public class LoginPage {

    private static final By txtLogin = By.name("uid");
    private static final By txtPassword = By.name("password");
    private static final By btnLogin = By.name("btnLogin");

    public static void login(){
        LogClass.logMethod("Login");
        WebActions.sendKeys(txtLogin, ConfigReader.getValue("userId"));
        WebActions.sendKeys(txtPassword, ConfigReader.getValue("password"));
        WebActions.Click(btnLogin, "Login");
    }

}
