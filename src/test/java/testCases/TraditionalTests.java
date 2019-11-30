package testCases;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.FlashsalePage;
import pageObject.HomePage;
import pageObject.LoginPage;
import org.openqa.selenium.Dimension;
import utils.XLUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TraditionalTests extends BaseClass{




    @Test(priority = 1)
    public void verifyLoginPageElements() throws IOException, InterruptedException {

        //Open the base url
        driver.get(baseUrl);
        //Maximise window
        driver.manage().window().maximize();

        //Check if the URL is opened
        logger.info("Opened the browser");

        if(driver.getTitle().toLowerCase().equals(title.toLowerCase())){
            Assert.assertTrue(true);
        }
        else{
            captureScreenshot(driver,"verifyHomePageText");
            logger.error("Home page title validation failed");
            Assert.assertTrue(false);
        }
        LoginPage login = new LoginPage(driver);
        //Verify all the elements are present in home page
        login.checkForPresenceOfAllElementsInLoginPage();
    }


    @Test(dataProvider = "LoginData",priority = 2)
    public void verifyLoginFailures(String user, String password) {

        LoginPage login = new LoginPage(driver);
        HomePage home = new HomePage(driver);
        //Click on login button without entering username and password
        login.clickLoginButton();

        //Check for the presence of alert text
        login.checkForUserNamePasswordPresenceNeededError();

        //Enter the username and leave the password blank
        login.setUserName(user);

        //CLick on login button without entering the password
        login.clickLoginButton();

        //Check for the presence of error message
        login.checkForPasswordPresenceNeededError();

        //Clear the username field
        login.clearUserNameField();

        // Enter the password field and enter the username blank
        login.setPassword(password);

        //CLick on login button without entering the username
        login.clickLoginButton();

        //Check for the presence of alert text
        login.checkForUserNamePresenceNeededError();

        //Clear the password field
        login.clearPasswordField();

        //Enter the valid username and password and check whether the user is logged in successfully
        login.setUserName(user);
        login.setPassword(password);

        //CLick on login button
        login.clickLoginButton();

        home.checkForPresenceOfTransactionTable();

    }

    @Test(priority = 3)
    public void sortTransactionTable() throws IOException {
        HomePage home = new HomePage(driver);
        //Get the list of amounts before sorting
        List<String> amountsTextBeforeSort = home.getamountsList();
        List<Float> amountsBeforeSort = new ArrayList<Float>();

        for(String amount:amountsTextBeforeSort){
            String [] amountArray = amount.split(" ");
            amountArray[1]=amountArray[1].replaceAll(",","");
            amountsBeforeSort.add((Float.parseFloat(amountArray[0]+amountArray[1])));
        }

        Collections.sort(amountsBeforeSort);

        //Click on amount header
        home.clickOnAmountHeader();

        List<String> amountsTextAfterSort = home.getamountsList();
        List<Float> amountsAfterSort = new ArrayList<Float>();

        for (String amount:amountsTextAfterSort){
            String [] amountArrayAfterSort = amount.split(" ");
            amountArrayAfterSort[1]=amountArrayAfterSort[1].replaceAll(",","");
            amountsAfterSort.add((Float.parseFloat(amountArrayAfterSort[0]+amountArrayAfterSort[1])));
        }

        if(amountsBeforeSort.equals(amountsAfterSort)){
            Assert.assertTrue(true);
        }
        else{
            captureScreenshot(driver,"Amount is not sorted after clicking on header");
            logger.error("Sorting failed after clicking on Amount header");
            Assert.assertTrue(false);
        }


    }

    @Test(priority = 4)
    public void verifyYearlyExpenses() throws IOException {
        HomePage home = new HomePage(driver);
        home.clickOnCompareExpenses();
        home.waitForExpensesContainerToAppear();
        home.getScriptContent();
        home.clickShowDataForNextYear();
        home.getScriptContent();
    }

    @Test(dataProvider = "LoginData",priority = 5)
    public void verifyGifs(String user, String password) throws IOException {
        LoginPage login = new LoginPage(driver);
        FlashsalePage flashsale = new FlashsalePage(driver);
        //Open the base url
        driver.get(baseUrlForGif);
        //Maximise window
        driver.manage().window().maximize();

        //Check if the URL is opened
        logger.info("Opened the browser");

        if(driver.getTitle().toLowerCase().equals(title.toLowerCase())){
            Assert.assertTrue(true);
        }
        else{
            captureScreenshot(driver,"verifyHomePageText");
            logger.error("Home page title validation failed");
            Assert.assertTrue(false);
        }

        flashsale.checkPresenceOfGif();


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


