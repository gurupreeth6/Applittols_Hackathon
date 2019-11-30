package testCases;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import utils.ReadConfig;

import java.io.File;
import java.io.IOException;

public class BaseClass {
    ReadConfig readConfig = new ReadConfig();
    public String baseUrl= readConfig.getApplicationURL();
    public String baseUrlVersion2 = readConfig.getApplicationVersion2URL();
    public String baseUrlForGif=readConfig.getGifURL();
    public String baseUrlForGif2 = readConfig.getGif2URL();
    public String title = "ACME demo app";
    public WebDriver driver;
    public static Logger logger;
    private static EyesRunner runner;
    private static Eyes eyes;
    private static BatchInfo batch;



    @Parameters("browser")
    @BeforeClass
    public void setup(String br) throws ClassNotFoundException, IOException {


        if(br.equals("chrome")){
            System.setProperty("webdriver.chrome.driver",readConfig.getChromePath());
            driver=new ChromeDriver();
        }
        if(br.equals("firefox")){
            System.setProperty("webdriver.gecko.driver",readConfig.getFirefoxPath());
            driver=new FirefoxDriver();
        }

        //Initialise logger
        logger = (Logger) LogManager.getLogger(this.getClass().getName());


    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public void captureScreenshot(WebDriver driver, String tname) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
        FileUtils.copyFile(source,target);
        logger.info("Screenshot taken");
    }
}
