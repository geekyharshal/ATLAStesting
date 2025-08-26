package selenium.Forms;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CheckboxTest {
    private WebDriver driver;
    private static final String URL = "https://testautomationcentral.com/demo/checkboxes.html";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @Test
    public void testCheckboxSelection() {
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        
        // Verify checkboxes are present
        Assert.assertTrue(checkboxes.size() > 0, "No checkboxes found");
        
        // Test checking and unchecking
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
                Assert.assertTrue(checkbox.isSelected(), "Checkbox should be selected");
            }
            
            checkbox.click();
            Assert.assertFalse(checkbox.isSelected(), "Checkbox should be unselected");
        }
    }

    @Test
    public void testSelectAllCheckboxes() {
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        
        // Select all checkboxes
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
        
        // Verify all are selected
        for (WebElement checkbox : checkboxes) {
            Assert.assertTrue(checkbox.isSelected(), "All checkboxes should be selected");
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}