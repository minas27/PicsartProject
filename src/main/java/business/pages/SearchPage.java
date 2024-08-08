package business.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static business.data.CommonData.SEARCH_PAGE_ENDPOINT;

public class SearchPage extends BasePage{
    private Actions actions;

    public SearchPage(WebDriver driver) {
        super(driver);
        this.actions = new Actions(driver);
    }

    @FindBy(id = "filter_icon")
    private WebElement filterBtn;

    @FindBy(css = "[data-testid='accordion-root']")
    private WebElement filterSideBar;

    @FindBy(css = "button#onetrust-accept-btn-handler")
    private WebElement acceptAllCookiesBtn;

    @FindBy(css = "[data-testid='com.picsart.social.search']")
    private WebElement filtersIframe;

    @FindBy(css = "[aria-label='licenses-All-checkbox']")
    private WebElement licenseAllCheckbox;

    @FindBy(css = "[aria-label='licenses-Commercial-checkbox']")
    private WebElement licenseCommercialCheckbox;

    @FindBy(css = "[aria-label='licenses-Personal-checkbox']")
    private WebElement licensePersonalCheckbox;

    @FindBy(id = "auth-form")
    private WebElement loginPopup;

    @FindBy(css = "[data-testid='modal-close-icon']")
    private WebElement closeLoginPopup;

    public WebElement getFilterSideBar() {
        return filterSideBar;
    }

    public WebElement getLoginPopup() {
        return loginPopup;
    }

    public SearchPage clickOnAcceptAllCookiesBtn() {
        waitUtils.waitUntilVisibility(acceptAllCookiesBtn, 25);
        if (acceptAllCookiesBtn != null) {
            acceptAllCookiesBtn.click();
        } else {
            System.out.println("Accept All Cookies button is not initialized.");
        }
        return this;
    }

    public SearchPage clickOnFilterBtn(){
        waitUtils.waitUntilVisibility(filterBtn);
        filterBtn.click();
        return this;
    }

    public SearchPage switchToFiltersIframe() {
        waitUtils.waitUntilVisibility(filtersIframe, 25);
        getDriver().switchTo().frame(filtersIframe);
        return this;
    }

    public SearchPage switchToSearchPage() {
        getDriver().switchTo().defaultContent();
        return this;
    }

    // Select a specific license type by giving argument of the it in string form
    public SearchPage clickLicenseCheckbox(String licenseType) {
        WebElement checkbox = switch (licenseType.toLowerCase()) {
            case "all" -> licenseAllCheckbox;
            case "commercial" -> licenseCommercialCheckbox;
            case "personal" -> licensePersonalCheckbox;
            default -> throw new IllegalArgumentException("Invalid license type: " + licenseType);
        };

        if (checkbox != null) {
            waitUtils.waitUntilVisibility(checkbox, 25);
            checkbox.click();
        }
        return this;
    }

    // Verify that we are on Search page
    public boolean isOnSearchPage(){
        waitUtils.waitUntilVisibility(acceptAllCookiesBtn);
        return acceptAllCookiesBtn.isDisplayed();
    }



    // Get an asset card by its index
    public WebElement getAssetCard(int index) {
        return getDriver().findElement(By.id("base_card_item" + index));
    }

    // Get the "like" button for a specific asset
    public WebElement getLikeButton(int index) {
        return getDriver().findElement(By.id("like_button_item" + index));
    }

    // Get the "save" button for a specific asset
    public WebElement getSaveButton(int index) {
        return getDriver().findElement(By.id("save_button_item" + index));
    }

    // Get the "try now" button for a specific asset
    public WebElement getTryNowButton(int index) {
        return getDriver().findElement(By.id("try_now_button_item" + index));
    }

    // Hover over a specific asset card
    public SearchPage hoverOverAssetCard(int index) throws InterruptedException {
        try {
            WebElement assetCard = getAssetCard(index);
            actions.moveToElement(assetCard).perform();
            // forced to use sleep, wait utils does not solve the issue
            Thread.sleep(1000);
            return this;
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted during hover action", e);
        } catch (Exception e) {
            System.out.println("Failed to hover over asset card at index " + index + ": " + e.getMessage());
            return this;
        }
    }

    // Verify visibility of like and save buttons for a specific asset
    public boolean verifyButtonsVisibilityOfLikeAndSave(int index) {
        try {
            hoverOverAssetCard(index);

            WebElement likeButton = getLikeButton(index);
            WebElement saveButton = getSaveButton(index);

            waitUtils.waitUntilVisibility(likeButton);
            waitUtils.waitUntilVisibility(saveButton);

            return likeButton.isDisplayed() && saveButton.isDisplayed();

        } catch (NoSuchElementException | TimeoutException | InterruptedException e) {
            // Handle the case where one of the buttons is not found
            return false;
        }
    }

    public boolean verifyOnlyTryNowButtonAppears(int index) throws InterruptedException {
        try {
            boolean likeButtonVisible;
            boolean saveButtonVisible;
            hoverOverAssetCard(index);

            boolean tryNowVisible = isElementDisplayed(getTryNowButton(index));

            try {
                WebElement likeButton = getLikeButton(index);
                likeButtonVisible = likeButton.isDisplayed();
            } catch (NoSuchElementException e) {
                likeButtonVisible = false;
            }

            try {
                WebElement saveButton = getSaveButton(index);
                saveButtonVisible = saveButton.isDisplayed();
            } catch (NoSuchElementException e) {
                saveButtonVisible = false;
            }
            return tryNowVisible && !likeButtonVisible && !saveButtonVisible;
        }
        catch (TimeoutException e) {
            // Handle timeout exceptions for waiting for the element to become visible
            System.out.println("Timeout waiting for elements to become visible for asset at index " + index + ": " + e.getMessage());
            return false;
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            System.out.println("Unexpected error while verifying buttons for asset at index " + index + ": " + e.getMessage());
            return false;
        }
    }

    // Check if it is a premium asset
    public boolean hasPremiumIcon(int index) {
        try {
            // Locate the asset card using the provided index
            WebElement assetCard = getAssetCard(index);

            // Locate the premium icon within the asset card
            WebElement premiumIcon = assetCard.findElement(By.cssSelector("div[data-testid='premium-icon-root']"));
            return premiumIcon.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public String getWebElementAttributeValueHref(WebElement element){
        try {
            // Wait for the element to be visible and interactable
            waitUtils.waitUntilVisibility(element, 10);
            // Get the href attribute
            String href = element.getAttribute("href");
            // Log or debug if href is null
            if (href == null) {
                System.out.println("Element found but href attribute is null or not present.");
            }
            return href;
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Element not found or not interactable.");
            return null;
        }
    }

    public EditorPage clickOnTryNow(int index){
        getTryNowButton(index).click();
        return new EditorPage(getDriver());
    }
}
