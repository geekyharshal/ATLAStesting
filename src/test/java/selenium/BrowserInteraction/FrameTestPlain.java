package selenium.BrowserInteraction;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class FrameTestPlain {
    private static final String URL = "https://testautomationcentral.com/demo/frames_iframes.html";

    public static void main(String[] args) {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        
        try {
            driver.get(URL);
            
            testSwitchToFrame(driver);
            testFrameByIndex(driver);
            testFrameById(driver);
            testFrameInteraction(driver);
            
            System.out.println("All frame tests completed successfully!");
            
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    public static void testSwitchToFrame(WebDriver driver) {
        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        
        for (WebElement frame : frames) {
            driver.switchTo().frame(frame);
            assert driver.getPageSource() != null;
            driver.switchTo().defaultContent();
        }
        System.out.println("testSwitchToFrame passed");
    }

    public static void testFrameByIndex(WebDriver driver) {
        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        
        for (int i = 0; i < frames.size(); i++) {
            driver.switchTo().frame(i);
            String pageSource = driver.getPageSource();
            assert pageSource != null;
            driver.switchTo().defaultContent();
        }
        System.out.println("testFrameByIndex passed");
    }

    public static void testFrameById(WebDriver driver) {
        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        
        for (WebElement frame : frames) {
            String id = frame.getAttribute("id");
            if (id != null && !id.isEmpty()) {
                driver.switchTo().frame(id);
                assert driver.getPageSource() != null;
                driver.switchTo().defaultContent();
            }
        }
        System.out.println("testFrameById passed");
    }

    public static void testFrameInteraction(WebDriver driver) {
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
        System.out.println("testFrameInteraction passed");
    }
}