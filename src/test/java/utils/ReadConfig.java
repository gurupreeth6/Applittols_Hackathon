package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
    Properties properties;
    public ReadConfig(){
        File src = new File("./Configuration/config.properties");
        try {
            FileInputStream fis = new FileInputStream(src);
            properties = new Properties();
            properties.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getApplicationURL(){
        return properties.getProperty("baseUrl");
    }
    public String getApplicationVersion2URL(){
        return properties.getProperty("baseUrlVersion2");
    }
    public String getGifURL(){
        return properties.getProperty("baseUrlForGif");
    }
    public String getGif2URL(){
        return properties.getProperty("baseUrl2ForGif");
    }
    public String getTitle(){
        return properties.getProperty("title");
    }
    public String getChromePath(){
        return properties.getProperty("chromepath");
    }
    public String getFirefoxPath(){
        return properties.getProperty("firefoxpath");
    }
    public String getAPIKey(){
        return properties.getProperty("APIKey");
    }


}
