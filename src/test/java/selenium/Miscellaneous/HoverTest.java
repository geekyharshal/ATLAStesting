package selenium.Miscellaneous;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class HoverTest {
    public static void main(String[] args) {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        
        try {
            driver.get("https://testautomationcentral.com/demo/hover.html");
            
            Actions actions = new Actions(driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            hoverOverElement(driver, actions, wait);
            
            System.out.println("Hover test completed successfully!");
            
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
    
    public static void hoverOverElement(WebDriver driver, Actions actions, WebDriverWait wait) {
        try {
            WebElement hoverElement = wait.until(ExpectedConditions.elementToBeClickable(By.className("hover-element")));
            
            System.out.println("Hovering over element...");
            actions.moveToElement(hoverElement).perform();
            
            WebElement tooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("tooltip")));
            System.out.println("Tooltip appeared: " + tooltip.getText());
            
            Thread.sleep(2000);
            
        } catch (Exception e) {
            System.out.println("Hover test failed: " + e.getMessage());
        }
    }
}