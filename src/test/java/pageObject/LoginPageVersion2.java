package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageVersion2 {
    WebDriver driver;
    public LoginPageVersion2(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//img[@src='img/logo-big.png']")
    @CacheLookup
    WebElement homePageLogo;

    @FindBy(xpath="//*[@class='auth-header'][contains(text(),'Logout Form')]")
    @CacheLookup
    WebElement loginForm;

    @FindBy(xpath = "//label[text()='Remember Me']/input[@class='form-check-input']")
    @CacheLookup
    WebElement formCheckInput;

    @FindBy(xpath="//img[@src='img/social-icons/twitter.png']")
    @CacheLookup
    WebElement twitterIcon;

    @FindBy(xpath="//img[@src='img/social-icons/facebook.png']")
    @CacheLookup
    WebElement facebookIcon;


    @FindBy(id="log-in")
    @CacheLookup
    WebElement loginButton;

    @FindBy(id="username")
    @CacheLookup
    WebElement userNameField;

    @FindBy(id="password")
    @CacheLookup
    WebElement passwordField;

    @FindBy(xpath="//div[text()='Please enter both username and password']")
    @CacheLookup
    WebElement userNamePasswordPresenceNeededErrorMessage;

    @FindBy(xpath="//div[text()='Password must be present']")
    @CacheLookup
    WebElement passwordPresenceNeededErrorMessage;

    @FindBy(xpath="//div[text()='Username must be present']")
    @CacheLookup
    WebElement userNamePresenceNeededErrorMessage;


    public void checkForPresenceOfAllElementsInLoginPage(){
        new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(homePageLogo));
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(loginForm));
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(formCheckInput));
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(twitterIcon));
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(facebookIcon));
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(userNameField));
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(passwordField));
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(loginButton));
    }

    public void clickLoginButton(){
        loginButton.click();
    }
    public void setUserName(String userName){
        userNameField.sendKeys(userName);
    }

    public void setPassword(String password){
        passwordField.sendKeys(password);
    }

    public void checkForUserNamePasswordPresenceNeededError(){
        new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(userNamePasswordPresenceNeededErrorMessage));
    }

    public void checkForPasswordPresenceNeededError(){
        new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(passwordPresenceNeededErrorMessage));
    }

    public void checkForUserNamePresenceNeededError(){
        new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(userNamePresenceNeededErrorMessage));
    }

    public void clearUserNameField(){
        userNameField.clear();
    }

    public void clearPasswordField(){
        passwordField.clear();
    }

}
