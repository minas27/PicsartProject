package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ActionsUtils {
    public static void scrollDown(WebDriver driver, int pixel){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(String.format("window.scrollBy(0, %s)", pixel));
    }
}
