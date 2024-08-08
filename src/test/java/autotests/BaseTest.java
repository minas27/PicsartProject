package autotests;

import business.pages.BasePage;
import business.pages.EditorPage;
import business.pages.SearchPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import static business.data.CommonData.BASE_URL;

public class BaseTest {
    protected Actions actions;

    private static ChromeDriver chromeDriver;

    protected BasePage basePage;

    protected SearchPage searchPage;

    protected EditorPage editorPage;

    public BaseTest() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        basePage = new BasePage(chromeDriver);
        searchPage = new SearchPage(chromeDriver);
        editorPage = new EditorPage(chromeDriver);
        Actions actions = new Actions(chromeDriver);
    }

    @BeforeMethod
    public void open(){
        chromeDriver.manage().window().maximize();
        chromeDriver.get(BASE_URL);
    }

    @AfterSuite(alwaysRun = true)
    public void close(){
        getDriver().close();
        getDriver().quit();
    }

    public static ChromeDriver getDriver() {
        return chromeDriver;
    }

    public void setWindowSize(int width, int height) {
        getDriver().manage().window().setSize(new Dimension(width, height));
    }

}
