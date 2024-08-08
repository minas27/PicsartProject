package autotests.e2eTests;

import autotests.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ActionsUtils;

public class E2ETest extends BaseTest {
    //1. Navigate to Picsart Search. +
    //2. Click on the filter button and verify that the filters disappear. +
    //3. Click on the filter button again to open the filters. +
    //4. Choose the "Personal" checkbox from the “License” section and verify that there are no “PLUS” assets. Hovering over an asset should display the like, save, and try now buttons.
    //5. Click on the like button and verify that the sign-in popup appears.
    //6. Close the popup.
    //7. Remove the filter.
    //8. Hover over a “PLUS” asset and verify that only the “try now” button appears.
    //9. Click on the “try now” button and verify that the editing screen opens with the image applied to the canvas.

    @Test(testName = "1024 x 768")
    public void picsartEditorTestScenarioResolution1() throws InterruptedException {
        setWindowSize(1024, 768);
        basePage.clickOnSearchBtn();
        searchPage
                .clickOnAcceptAllCookiesBtn()
                .switchToFiltersIframe();

        // In this resolution the filter bar is initially closed when loaded compared to full desktop screen size
        Assert.assertFalse(searchPage.isElementDisplayed(searchPage.getFilterSideBar()));
        // Opening the filter sidebar
        searchPage.clickOnFilterBtn();
        Thread.sleep(1000);
        Assert.assertTrue(searchPage.isElementDisplayed(searchPage.getFilterSideBar()));
        searchPage
                .clickLicenseCheckbox("personal");
        // Closing the filter bar or assets do not appear
        searchPage.clickOnFilterBtn();
        Thread.sleep(1000);
        for (int i = 0; i < 15; i++) {
            Assert.assertTrue(searchPage.verifyButtonsVisibilityOfLikeAndSave(i));
        }

        // Click on the like button of the first appearing asset
        searchPage
                .getLikeButton(0).click();
        Thread.sleep(1000);

        // switch back to page content to check popup visibility
        searchPage.switchToSearchPage();
        Assert.assertTrue(searchPage.isElementDisplayed(searchPage.getLoginPopup()));

        // Close the popup
        searchPage.clickOnCloseLoginPopup();

        // Switch back to iframe for continuing the test case
        searchPage.switchToFiltersIframe();

        //open filter sidebar and deselect personal checkbox
        searchPage
                .clickOnFilterBtn()
                .clickLicenseCheckbox("personal")
                .clickOnFilterBtn(); // to close the filter bar and make assets appear

        // unable to avoid using sleep here due to slow applying of filters
        Thread.sleep(3000);
        for (int i = 0; i < 15; i++) {
            if (searchPage.hasPremiumIcon(i)) {
                Assert.assertTrue(searchPage.verifyOnlyTryNowButtonAppears(i));
                //Extract the href url of the plus asset to use later
                searchPage.hoverOverAssetCard(i);
                String asset_url = searchPage.getWebElementAttributeValueHref(searchPage.getAssetCard(i).findElement(By.tagName("a")));

                // Open the editor by clicking on Try now of Plus asset
                searchPage.clickOnTryNow(i);
                editorPage.switchToEditorPage();

                // Verify we are in editor
                Assert.assertTrue(editorPage.isElementDisplayed(editorPage.getExportBtn()));
                // Verify that the correct asset was applied
                Assert.assertTrue(getDriver().getCurrentUrl().equals(asset_url));
                break; // Exit the loop once the premium asset is found and verified
            }
        }
    }

    @Test(testName = "1440 x 900")
    public void picsartEditorTestScenarioResolution2() throws InterruptedException {
        setWindowSize(1440, 900);
        basePage.clickOnSearchBtn();
        searchPage
                .clickOnAcceptAllCookiesBtn()
                .switchToFiltersIframe();

        // Verify that the sidebar is open initially
        Assert.assertTrue(searchPage.isElementDisplayed(searchPage.getFilterSideBar()));

        // Closing the filter sidebar
        searchPage.clickOnFilterBtn();

        Thread.sleep(1000);

        // Verify that the sidebar is closed
        Assert.assertFalse(searchPage.isElementDisplayed(searchPage.getFilterSideBar()));

        searchPage
                .clickOnFilterBtn() // open the sidebar again
                .clickLicenseCheckbox("personal"); // select personal

        Thread.sleep(1000);

        // Go over the first 15 assets and verify that all of them are Personal assets(this number can be changed)

        for (int i = 0; i < 15; i++) {
            Thread.sleep(1500);
            if(i % 5==0) ActionsUtils.scrollDown(getDriver(), 200);
            Assert.assertTrue(searchPage.verifyButtonsVisibilityOfLikeAndSave(i));
        }
        searchPage
                .getLikeButton(0).click();
        Thread.sleep(1500);

        // switch back to page content to check popup visibility
        searchPage.switchToSearchPage();
        Assert.assertTrue(searchPage.isElementDisplayed(searchPage.getLoginPopup()));

        // Close the popup
        searchPage.clickOnCloseLoginPopup();

        // Switch back to iframe of assets Deselect personal checkbox
        searchPage
                .switchToFiltersIframe()
                .clickLicenseCheckbox("personal");

        // Unable to avoid using sleep here due to slow applying of filters
        Thread.sleep(3000);
        for (int i = 0; i < 15; i++) {
            if (searchPage.hasPremiumIcon(i)) {
                Assert.assertTrue(searchPage.verifyOnlyTryNowButtonAppears(i));
                //Extract the href url of the plus asset to use later
                searchPage.hoverOverAssetCard(i);
                String asset_url = searchPage.getWebElementAttributeValueHref(searchPage.getAssetCard(i).findElement(By.tagName("a")));

                // Open the editor by clicking on Try now of Plus asset
                searchPage.clickOnTryNow(i);
                editorPage.switchToEditorPage();

                // Verify we are in editor
                Assert.assertTrue(editorPage.isElementDisplayed(editorPage.getExportBtn()));
                // Verify that the correct asset was applied
                Assert.assertTrue(getDriver().getCurrentUrl().equals(asset_url));
                break; // Exit the loop once the premium asset is found and verified
            }
        }
    }

    @Test(testName = "1366 x 768")
    public void picsartEditorTestScenarioResolution3() throws InterruptedException {
        setWindowSize(1366, 768);
        basePage.clickOnSearchBtn();
        searchPage
                .clickOnAcceptAllCookiesBtn()
                .switchToFiltersIframe();

        // Verify that the sidebar is open initially
        Assert.assertTrue(searchPage.isElementDisplayed(searchPage.getFilterSideBar()));

        // Closing the filter sidebar
        searchPage.clickOnFilterBtn();

        Thread.sleep(1000);

        // Verify that the sidebar is closed
        Assert.assertFalse(searchPage.isElementDisplayed(searchPage.getFilterSideBar()));



        // Open the Sidebar again and Select license "Personal"
        searchPage
                .clickOnFilterBtn()
                .clickLicenseCheckbox("personal");

        for (int i = 0; i < 15; i++) {
            Assert.assertTrue(searchPage.verifyButtonsVisibilityOfLikeAndSave(i));
        }

        // Click on the like button of the first appearing asset
        searchPage
                .getLikeButton(0).click();
        Thread.sleep(1000);

        // Switch back to page content to check popup visibility
        searchPage.switchToSearchPage();
        Assert.assertTrue(searchPage.isElementDisplayed(searchPage.getLoginPopup()));

        // Close the popup
        searchPage.clickOnCloseLoginPopup();

        // Switch back to iframe for continuing the test case
        searchPage.switchToFiltersIframe();

        // Deselect personal checkbox
        searchPage.clickLicenseCheckbox("personal");

        // Unable to avoid using sleep here due to slow applying of filters
        Thread.sleep(3000);

        // Find the first instance of Plus asset, click on Try now and open the editor
        for (int i = 0; i < 15; i++) {
            if (searchPage.hasPremiumIcon(i)) {
                Assert.assertTrue(searchPage.verifyOnlyTryNowButtonAppears(i));
                //Extract the href url of the plus asset to use later
                searchPage.hoverOverAssetCard(i);
                String asset_url = searchPage.getWebElementAttributeValueHref(searchPage.getAssetCard(i).findElement(By.tagName("a")));

                // Open the editor by clicking on Try now of Plus asset
                searchPage.clickOnTryNow(i);
                editorPage.switchToEditorPage();

                // Verify we are in the editor
                Assert.assertTrue(editorPage.isElementDisplayed(editorPage.getExportBtn()));
                // Verify that the correct asset was applied
                Assert.assertTrue(getDriver().getCurrentUrl().equals(asset_url));
                break; // Exit the loop once the premium asset is found and verified
            }
        }
    }
}
