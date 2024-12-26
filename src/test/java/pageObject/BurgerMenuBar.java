package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BurgerMenuBar extends BasePage{
    public BurgerMenuBar(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "react-burger-menu-btn")
    WebElement burgerMenuIcon;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutSidebarLink;




    public boolean isBurgerMenuDisplay(){
        boolean isBurgerMenuDisplay = burgerMenuIcon.isDisplayed();
        return isBurgerMenuDisplay;
    }

    public void clickOnBurgerMenu(){
        burgerMenuIcon.click();
    }

    public boolean isLogoutSideBarLinkDisplay(){
        boolean isLogoutSideBarLinkDisplay = logoutSidebarLink.isDisplayed();
        return isLogoutSideBarLinkDisplay;
    }

    public void clickOnLogoutSideBarLink(){
        logoutSidebarLink.click();
    }


}
