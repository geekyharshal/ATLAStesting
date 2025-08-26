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
import java.util.Set;

public class LinkTest {
    private WebDriver driver;
    private static final String URL = "https://testautomationcentral.com/demo/links.html";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(URL);
    }

    @Test
    public void testAllLinks() {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        
        Assert.assertTrue(links.size() > 0, "No links found");
        
        for (WebElement link : links) {
            String href = link.getAttribute("href");
            if (href != null && !href.isEmpty()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
                Assert.assertNotNull(href, "Link should have href attribute");
            }
        }
    }

    @Test
    public void testLinkClick() {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        String originalWindow = driver.getWindowHandle();
        
        for (int i = 0; i < links.size(); i++) {
            links = driver.findElements(By.tagName("a"));
            if (i >= links.size()) break;
            
            WebElement link = links.get(i);
            String href = link.getAttribute("href");
            
            if (href != null && !href.isEmpty() && !href.startsWith("mailto:") && !href.startsWith("tel:")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
                
                Set<String> windows = driver.getWindowHandles();
                if (windows.size() > 1) {
                    for (String window : windows) {
                        if (!window.equals(originalWindow)) {
                            driver.switchTo().window(window);
                            driver.close();
                        }
                    }
                    driver.switchTo().window(originalWindow);
                } else {
                    driver.navigate().back();
                }
            }
        }
    }

    @Test
    public void testExternalLinks() {
        List<WebElement> externalLinks = driver.findElements(By.cssSelector("a[target='_blank']"));
        
        for (WebElement link : externalLinks) {
            String href = link.getAttribute("href");
            String target = link.getAttribute("target");
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
            Assert.assertEquals(target, "_blank", "External link should open in new tab");
            Assert.assertNotNull(href, "External link should have href");
        }
    }

    @Test
    public void testInternalLinks() {
        List<WebElement> internalLinks = driver.findElements(By.cssSelector("a:not([target='_blank'])"));
        
        for (WebElement link : internalLinks) {
            String href = link.getAttribute("href");
            if (href != null && !href.isEmpty() && !href.startsWith("mailto:") && !href.startsWith("tel:")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
                Assert.assertNotNull(href, "Internal link should have href");
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