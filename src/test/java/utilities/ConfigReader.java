package utilities;

import setup.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    /**
     * =============================================================================
     * Method: loadPropertyFile | Description: This method will load the config file |
     * Parameters:filePath | Return: None
     * =============================================================================
     */
    static {
        try {
            FileInputStream fs = new FileInputStream(Constants.configPath);
            properties.load(fs);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * =============================================================================
     * Method: getValue | Description: will get sting value from properties file |
     * Parameters:key | Return: String
     * =============================================================================
     */
    public static String getValue(String key){
        String value = "";
        value = properties.getProperty(key);
        if(Objects.isNull(value)) {
            throw new NullPointerException("Property name " + key + " is not found.");
        }
        return value;
    }
}
