package pageObject;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy(id="transactionsTable")
    @CacheLookup
    WebElement transactionTable;

    @FindBys({@FindBy(xpath="//table[@id='transactionsTable']/tbody/tr/td[5]")})
    List<WebElement> amountsList;

    @FindBy(id="amount")
    @CacheLookup
    WebElement amountHeader;

    @FindBy(xpath = "//div[@id='container']/following-sibling::script")
    @CacheLookup
    WebElement scriptElement;

    @FindBy(id="showExpensesChart")
    @CacheLookup
    WebElement compareExpensesButton;

    @FindBy(id = "container")
    @CacheLookup
    WebElement expensesContainer;

    @FindBy(xpath = "//button[contains(text(),'Show data for next year')]")
    @CacheLookup
    WebElement showDataElement;



    public void checkForPresenceOfTransactionTable(){
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(transactionTable));
    }




    public List<String> getamountsList(){
        List<String> amountsListToReturn= new ArrayList<String> ();
        for(WebElement amount:amountsList){
            amountsListToReturn.add(amount.getText());
        }
        return amountsListToReturn;

    }

    public void clickOnAmountHeader(){
        amountHeader.click();
    }

    public void getScriptContent(){
        String scriptText = (String) ((JavascriptExecutor)driver).executeScript(
                "return arguments[0].innerHTML", scriptElement);
        Pattern pattern = Pattern.compile("data: \\[(.*?)\\]");
        String [] result = pattern.split(scriptText);
        System.out.println(Arrays.toString(result));

    }

    public void clickOnCompareExpenses(){
        compareExpensesButton.click();
    }

    public void waitForExpensesContainerToAppear(){
        new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(expensesContainer));
    }

    public void scrollUntillEndOfPage(){
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //This will scroll the page till the element is found
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void clickShowDataForNextYear(){
        showDataElement.click();
    }

}
