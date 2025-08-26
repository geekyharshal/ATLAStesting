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

public class SliderTest {
    public static void main(String[] args) {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        
        try {
            driver.get("https://testautomationcentral.com/demo/slider_controls.html");
            
            Actions actions = new Actions(driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            moveSlider(driver, actions, wait);
            
            System.out.println("Slider test completed successfully!");
            
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
    
    public static void moveSlider(WebDriver driver, Actions actions, WebDriverWait wait) {
        try {
            WebElement slider = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='range']")));
            
            System.out.println("Initial slider value: " + slider.getAttribute("value"));
            
            System.out.println("Moving slider to the right...");
            actions.clickAndHold(slider).moveByOffset(50, 0).release().perform();
            
            Thread.sleep(1000);
            
            System.out.println("Slider value after move: " + slider.getAttribute("value"));
            
            System.out.println("Moving slider to the left...");
            actions.clickAndHold(slider).moveByOffset(-30, 0).release().perform();
            
            Thread.sleep(1000);
            
            System.out.println("Final slider value: " + slider.getAttribute("value"));
            
        } catch (Exception e) {
            System.out.println("Slider test failed: " + e.getMessage());
        }
    }
}