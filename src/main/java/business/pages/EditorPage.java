package business.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditorPage extends BasePage{
    public EditorPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "auth-form")
    private WebElement loginPopup;

    @FindBy(css = "[data-testid='editor-header-right']")
    private WebElement exportBtn;

    public WebElement getExportBtn() {
        return exportBtn;
    }

    public EditorPage switchToEditorPage() {
        getDriver().switchTo().defaultContent();
        return this;
    }

}
