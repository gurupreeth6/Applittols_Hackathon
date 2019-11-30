package testCases;

import com.applitools.eyes.*;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import pageObject.FlashsalePage;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.LoginPageVersion2;
import utils.XLUtils;

import java.io.IOException;

public class VisualAITests extends BaseClass{

    private static EyesRunner runner;
    private static Eyes eyes;
    private static BatchInfo batch;

    @BeforeClass
    public void setBatch(){
        batch=new BatchInfo("Visual AI test");
        runner = new ClassicRunner();
        eyes=new Eyes(runner);
        eyes.setApiKey(readConfig.getAPIKey());
        eyes.setBatch(batch);
    }

    @Test(priority = 1)
    public void verifyLoginPageElements() throws IOException, InterruptedException {
        //Open the base url
        driver.get(baseUrlVersion2);
        //Maximise window
        driver.manage().window().maximize();
        LoginPage login=new LoginPage(driver);
        login.scrollToLoginButtonView();
        eyes.open(driver,"Login app version2","Login page test");
        eyes.checkWindow();
        eyes.closeAsync();
    }

    @Test(dataProvider = "LoginData",priority = 2)
    public void verifyLoginFailures(String user, String password) {
        LoginPage login = new LoginPage(driver);
        HomePage home = new HomePage(driver);
        //Click on login button without entering username and password
        login.clickLoginButton();

        eyes.open(driver,"Login app version2","Login failure test");
        eyes.checkWindow();

        //Enter the username and leave the password blank
        login.setUserName(user);

        //CLick on login button without entering the password
        login.clickLoginButton();

        //Check for the presence of error message
        eyes.checkWindow();

        //Clear the username field
        login.clearUserNameField();

        // Enter the password field and enter the username blank
        login.setPassword(password);

        //CLick on login button without entering the username
        login.clickLoginButton();

        //Check for the presence of alert text
        eyes.checkWindow();

        //Clear the password field
        login.clearPasswordField();

        //Enter the valid username and password and check whether the user is logged in successfully
        login.setUserName(user);
        login.setPassword(password);

        //CLick on login button
        login.clickLoginButton();

        eyes.closeAsync();

        eyes.open(driver,"Home page version2","Home page validation test");
        eyes.checkWindow();
        eyes.closeAsync();

    }

    @Test(priority = 3)
    public void sortTransactionTable() throws IOException {
        HomePage home = new HomePage(driver);

        //Scroll until end of page
        home.scrollUntillEndOfPage();

        //Take a Eye shot
        eyes.open(driver,"Home page","Sort table validation test");
        eyes.checkWindow();
        //Click on amount header
        home.clickOnAmountHeader();

        //Take a eye shot again
        eyes.checkWindow();
        eyes.closeAsync();
    }

    @Test(priority = 4)
    public void verifyYearlyExpenses() throws IOException {
        HomePage home = new HomePage(driver);
        home.clickOnCompareExpenses();
        home.waitForExpensesContainerToAppear();

        //Take a Eye shot
        eyes.open(driver,"Chart page","Chart page validation test");
        eyes.checkWindow();

        //CLick on show data for next year and take a eye shot
        home.clickShowDataForNextYear();
        eyes.checkWindow();
        eyes.closeAsync();

    }

    @Test(dataProvider = "LoginData",priority = 5)
    public void verifyGifs(String user, String password) throws IOException {
        LoginPage login = new LoginPage(driver);
        FlashsalePage flashsale = new FlashsalePage(driver);
        //Open the base url
        driver.get(baseUrlForGif2);

        //Enter the valid username and password and check whether the user is logged in successfully
        login.setUserName(user);
        login.setPassword(password);

        //CLick on login button
        login.clickLoginButton();

        //Take a Eye shot
        eyes.open(driver,"Gif page","Gif page validation test");
        eyes.checkWindow();
        eyes.closeAsync();

    }

    @DataProvider(name="LoginData")
    Object[][] getData() throws IOException {
        String path=System.getProperty("user.dir")+"/src/test/java/testData/TestData.xlsx";
        int rowNumber= XLUtils.getRowCount(path,"Sheet1");
        int columnCount=XLUtils.getCellCount(path,"Sheet1",1);

        String loginData[][]=new String[rowNumber][columnCount];
        for (int i=1;i<=rowNumber;i++){
            for(int j=0;j<columnCount;j++){
                loginData[i-1][j]=XLUtils.getCellData(path,"Sheet1",i,j);
            }
        }
        return loginData;
    }

}
