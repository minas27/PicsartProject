package business.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class BasePage {
    public WaitUtils waitUtils;

    private WebDriver driver;


    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUtils = new WaitUtils(driver);

    }

    public WebElement getPicsartLogoBtn() {
        return picsartLogoBtn;
    }

    @FindBy(css = "button[aria-label='Picsart home']")
    private WebElement picsartLogoBtn;

    @FindBy(css = "a[aria-label='search']")
    private WebElement searchBtn;

    @FindBy(xpath = "//button[normalize-space()='Start creating']")
    private WebElement startCreatingBtn;

    @FindBy(css = "button[aria-label='Log in']")
    private WebElement loginBtn;

    @FindBy(css = "[data-testid='modal-close-icon']")
    private WebElement closeLoginPopup;
    public WebDriver getDriver() {
        return driver;
    }

    public BasePage clickOnPicsartLogoBtn(){
        waitUtils.waitUntilVisibility(picsartLogoBtn);
        picsartLogoBtn.click();
        return this;
    }

    public SearchPage clickOnSearchBtn(){
        waitUtils.waitUntilVisibility(searchBtn, 25);
        searchBtn.click();
        return new SearchPage(getDriver());
    }

    // Check if a specific element is displayed
    public boolean isElementDisplayed(WebElement element) {
        try {
            waitUtils.waitUntilVisibility(element, 10);
            return element.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public void clickOnCloseLoginPopup(){
        waitUtils.waitUntilVisibility(closeLoginPopup);
        closeLoginPopup.click();
    }
}
