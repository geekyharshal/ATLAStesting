package selenium.DynamicElements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class DynamicLoadingTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String URL = "https://testautomationcentral.com/demo/dynamic_loading.html";

    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(URL);
    }

    public void testDynamicLoading() {
        try {
            WebElement startButton = driver.findElement(By.xpath("//button[contains(text(),'Start')]"));
            startButton.click();
            
            WebElement loadingIndicator = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading")));
            System.out.println("Loading started...");
            
            WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
            System.out.println("Dynamic content loaded: " + result.getText());
            
        } catch (Exception e) {
            // Try alternative locators
            WebElement button = driver.findElement(By.tagName("button"));
            button.click();
            
            WebElement dynamicContent = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Hello World!')]")));
            System.out.println("Content found: " + dynamicContent.getText());
        }
        System.out.println("Dynamic loading test completed");
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void main(String[] args) {
        DynamicLoadingTest test = new DynamicLoadingTest();
        
        test.setUp();
        try {
            test.testDynamicLoading();
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            test.tearDown();
        }
    }
}