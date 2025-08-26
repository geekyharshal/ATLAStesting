package selenium.Forms;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ButtonTest {
    private WebDriver driver;
    private static final String URL = "https://testautomationcentral.com/demo/buttons.html";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(URL);
    }

    @Test
    public void testButtonClick() {
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        
        Assert.assertTrue(buttons.size() > 0, "No buttons found");
        
        for (WebElement button : buttons) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
    }

    @Test
    public void testInputButtons() {
        List<WebElement> inputButtons = driver.findElements(By.cssSelector("input[type='button'], input[type='submit'], input[type='reset']"));
        
        for (WebElement button : inputButtons) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
            Assert.assertTrue(button.isEnabled(), "Button should be enabled");
        }
    }

    @Test
    public void testAllClickableElements() {
        List<WebElement> clickables = driver.findElements(By.cssSelector("button, input[type='button'], input[type='submit'], input[type='reset']"));
        
        Assert.assertTrue(clickables.size() > 0, "No clickable elements found");
        
        for (WebElement element : clickables) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
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