package selenium.Forms;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DropdownTest {
    private WebDriver driver;
    private static final String URL = "https://testautomationcentral.com/demo/dropdown.html";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(URL);
    }

    @Test
    public void testDropdownSelection() {
        WebElement dropdown = driver.findElement(By.tagName("select"));
        Select select = new Select(dropdown);
        
        if (select.getOptions().size() > 1) {
            select.selectByIndex(1);
            Assert.assertNotNull(select.getFirstSelectedOption());
            
            String firstOptionText = select.getOptions().get(0).getText();
            select.selectByVisibleText(firstOptionText);
            Assert.assertEquals(select.getFirstSelectedOption().getText(), firstOptionText);
        }
    }

    @Test
    public void testDropdownOptions() {
        WebElement dropdown = driver.findElement(By.tagName("select"));
        Select select = new Select(dropdown);
        
        Assert.assertTrue(select.getOptions().size() > 0, "No options found");
        Assert.assertFalse(select.isMultiple(), "Should be single select dropdown");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}