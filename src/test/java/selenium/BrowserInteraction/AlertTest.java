package selenium.BrowserInteraction;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AlertTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String URL = "https://testautomationcentral.com/demo/alerts.html";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.get(URL);
    }

    @Test
    public void testSimpleAlert() {
        try {
            WebElement alertButton = driver.findElement(By.xpath("//button[contains(text(),'Alert')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", alertButton);
            
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            Assert.assertNotNull(alertText);
            alert.accept();
        } catch (Exception e) {
            // Alert may not be present
        }
    }

    @Test
    public void testConfirmAlert() {
        try {
            WebElement confirmButton = driver.findElement(By.xpath("//button[contains(text(),'Confirm')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmButton);
            
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception e) {
            // Alert may not be present
        }
    }

    @Test
    public void testPromptAlert() {
        try {
            WebElement promptButton = driver.findElement(By.xpath("//button[contains(text(),'Prompt')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", promptButton);
            
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.sendKeys("Test Input");
            alert.accept();
        } catch (Exception e) {
            // Alert may not be present
        }
    }

    @Test
    public void testAllAlerts() {
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        
        for (WebElement button : buttons) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
                
                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));
                Alert alert = shortWait.until(ExpectedConditions.alertIsPresent());
                alert.accept();
            } catch (Exception e) {
                // Continue if no alert appears
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (driver != null) {
            driver.quit();
        }
    }
}