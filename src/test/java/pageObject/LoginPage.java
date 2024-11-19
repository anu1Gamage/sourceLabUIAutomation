package pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;


public class LoginPage extends BasePage {


    //constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //constants
    public final String inventoryPageUrl = "https://www.saucedemo.com/inventory.html";
    public final String inventoryPageTitle = "Projects";
    //userName
    @FindBy(id = "user-name")
    private WebElement userNameIn;
    //password
    @FindBy(id = "password")
    private WebElement passwordIn;
    //loginButton
    @FindBy(id = "login-button")
    private WebElement loginBtn;
    //LoginErrorMassage
    @FindBy(xpath = ("//h3[@data-test='error']"))
    private WebElement loginErrorMessage;

    private Actions actions; // Actions object


    //Action Methods
    public void setUserName(String userName){
        if (userName != null && !userName.trim().isEmpty()) {
            userNameIn.clear();
            userNameIn.sendKeys(userName);
        }
    }

    public void setPassword(String password){
        if (password != null && !password.trim().isEmpty()) {
            passwordIn.clear(); // Clear any existing text
            passwordIn.sendKeys(password);
        }
    }

    public void clickLoginBtn(){
        loginBtn.click();
    }

    public String getCurrentPageUrl(){
        return driver.getCurrentUrl();
    }

    public boolean IsOnLoginPage(){
        return driver.getCurrentUrl().contains("https://www.saucedemo.com/");
    }

    public String getErrorMessage(){
        try {
            return loginErrorMessage.getText();
        }catch (NoSuchElementException e){
            return "Error Message Not Found";
        }
    }

    public boolean isLoginButtonDisplayed() {
        return loginBtn.isDisplayed();
    }

    // Initialize Actions in the constructor
    public void initializeActions() {
        actions = new Actions(driver);
    }

    // Method to input username and press Tab
    public void enterUserNameWithTab(String userName) {
        setUserName(userName); // Set username
        actions.moveToElement(userNameIn).sendKeys(Keys.TAB).perform(); // Press Tab
    }

    // Method to input password and press Enter
    public void enterPasswordWithEnter(String password) {
        setPassword(password); // Set password
        actions.moveToElement(passwordIn).sendKeys(Keys.ENTER).perform(); // Press Enter
    }

    // Method to input username and press Enter
    public void enterUserNameWithEnter(String userName) {
        setUserName(userName); // Set username
        actions.moveToElement(userNameIn).sendKeys(Keys.ENTER).perform(); // Press Enter
    }

}


