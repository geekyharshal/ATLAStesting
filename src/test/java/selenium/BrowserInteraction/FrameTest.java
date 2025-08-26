package selenium.BrowserInteraction;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class FrameTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String URL = "https://testautomationcentral.com/demo/frames_iframes.html";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(URL);
    }

    @Test
    public void testSwitchToFrame() {
        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        
        for (WebElement frame : frames) {
            driver.switchTo().frame(frame);
            Assert.assertNotNull(driver.getPageSource());
            driver.switchTo().defaultContent();
        }
    }

    @Test
    public void testFrameByIndex() {
        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        
        for (int i = 0; i < frames.size(); i++) {
            driver.switchTo().frame(i);
            String pageSource = driver.getPageSource();
            Assert.assertNotNull(pageSource);
            driver.switchTo().defaultContent();
        }
    }

    @Test
    public void testFrameById() {
        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        
        for (WebElement frame : frames) {
            String id = frame.getAttribute("id");
            if (id != null && !id.isEmpty()) {
                driver.switchTo().frame(id);
                Assert.assertNotNull(driver.getPageSource());
                driver.switchTo().defaultContent();
            }
        }
    }

    @Test
    public void testFrameInteraction() {
        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        
        for (WebElement frame : frames) {
            try {
                driver.switchTo().frame(frame);
                
                List<WebElement> inputs = driver.findElements(By.tagName("input"));
                for (WebElement input : inputs) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].value = 'Test';", input);
                }
                
                List<WebElement> buttons = driver.findElements(By.tagName("button"));
                for (WebElement button : buttons) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
                }
                
                driver.switchTo().defaultContent();
            } catch (Exception e) {
                driver.switchTo().defaultContent();
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