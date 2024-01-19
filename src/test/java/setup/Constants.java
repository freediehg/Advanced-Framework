package setup;

public interface Constants {

    String configPath = System.getProperty("user.dir")+"/src/test/resources/Config/config.properties";
    String winiumDriverPath = System.getProperty("user.dir")+"/src/test/resources/Driver/Winium.Desktop.Driver.exe";
    String appiumPath = "http://0.0.0.0:4723/wd/hub";
    String localHost = "http://localhost:9999";

}
