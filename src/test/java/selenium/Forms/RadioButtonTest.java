package selenium.Forms;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class RadioButtonTest {
    private WebDriver driver;
    private static final String URL = "https://testautomationcentral.com/demo/radiobuttons.html";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(URL);
    }

    @Test
    public void testRadioButtonSelection() {
        List<WebElement> radioButtons = driver.findElements(By.cssSelector("input[type='radio']"));
        
        Assert.assertTrue(radioButtons.size() > 0, "No radio buttons found");
        
        for (WebElement radioButton : radioButtons) {
            radioButton.click();
            Assert.assertTrue(radioButton.isSelected(), "Radio button should be selected");
        }
    }

    @Test
    public void testOnlyOneRadioButtonSelected() {
        List<WebElement> radioButtons = driver.findElements(By.cssSelector("input[type='radio']"));
        
        if (radioButtons.size() >= 2) {
            radioButtons.get(0).click();
            Assert.assertTrue(radioButtons.get(0).isSelected());
            
            radioButtons.get(1).click();
            Assert.assertTrue(radioButtons.get(1).isSelected());
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}