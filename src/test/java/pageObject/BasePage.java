package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;
    protected Actions actions;

    public BasePage(WebDriver driver){
         this.driver = driver;
        PageFactory.initElements(driver,this);
        this.actions = new Actions(driver); // Initialize Actions object
    }
}
