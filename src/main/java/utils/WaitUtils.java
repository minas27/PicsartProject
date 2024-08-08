package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
    private  WebDriver driver;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilVisibility(WebElement element, int sec){
        new WebDriverWait(driver, sec)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilVisibility(WebElement element){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public  void waitUntilClickable(WebElement element, int sec){
        new WebDriverWait(driver, sec)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public  void waitUntilClickable(WebElement element){
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public void pause(int sec) throws InterruptedException {
        Thread.sleep(sec * 1000);
    }
}

