package selenium.BrowserInteraction;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class FrameContentTest {
    private static final String URL = "https://testautomationcentral.com/demo/frames_iframes.html";

    public static void main(String[] args) {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        
        try {
            driver.get(URL);
            printFrameContents(driver);
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    public static void printFrameContents(WebDriver driver) {
        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        
        System.out.println("Found " + frames.size() + " frames/iframes");
        System.out.println("=".repeat(50));
        
        for (int i = 0; i < frames.size(); i++) {
            try {
                WebElement frame = frames.get(i);
                String id = frame.getAttribute("id");
                String src = frame.getAttribute("src");
                
                System.out.println("Frame " + (i + 1) + ":");
                System.out.println("ID: " + (id != null ? id : "No ID"));
                System.out.println("SRC: " + (src != null ? src : "No SRC"));
                
                driver.switchTo().frame(frame);
                
                String title = driver.getTitle();
                String pageSource = driver.getPageSource();
                
                System.out.println("Title: " + title);
                System.out.println("Content length: " + pageSource.length() + " characters");
                
                List<WebElement> elements = driver.findElements(By.xpath("//*[text()]"));
                System.out.println("Text content:");
                for (WebElement element : elements) {
                    String text = element.getText().trim();
                    if (!text.isEmpty() && text.length() < 200) {
                        System.out.println("  - " + text);
                    }
                }
                
                driver.switchTo().defaultContent();
                System.out.println("-".repeat(30));
                
            } catch (Exception e) {
                System.out.println("Error accessing frame " + (i + 1) + ": " + e.getMessage());
                driver.switchTo().defaultContent();
            }
        }
    }
}