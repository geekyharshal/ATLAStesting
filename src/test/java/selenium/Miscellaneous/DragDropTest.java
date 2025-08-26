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

public class DragDropTest {
    public static void main(String[] args) {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        
        try {
            driver.get("https://testautomationcentral.com/demo/drag_and_drop.html");
            
            Actions actions = new Actions(driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            performDragAndDrop(driver, actions, wait);
            
            System.out.println("Drag and drop test completed successfully!");
            
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
    
    public static void performDragAndDrop(WebDriver driver, Actions actions, WebDriverWait wait) throws InterruptedException {
        WebElement sourceElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("draggable")));
        WebElement targetElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("droppable")));
        
        System.out.println("Source element text: " + sourceElement.getText());
        System.out.println("Target element text: " + targetElement.getText());
        
        System.out.println("Performing drag and drop...");
        actions.dragAndDrop(sourceElement, targetElement).perform();
        
        Thread.sleep(2000);
        
        System.out.println("Target element text after drop: " + targetElement.getText());

    }
}