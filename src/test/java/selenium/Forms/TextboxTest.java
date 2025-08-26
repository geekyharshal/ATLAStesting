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

public class TextboxTest {
    private WebDriver driver;
    private static final String URL = "https://testautomationcentral.com/demo/textboxes.html";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(URL);
    }

    @Test
    public void testTextboxInput() {
        List<WebElement> textboxes = driver.findElements(By.cssSelector("input[type='text']"));
        
        Assert.assertTrue(textboxes.size() > 0, "No textboxes found");
        
        for (int i = 0; i < textboxes.size(); i++) {
            WebElement textbox = textboxes.get(i);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", textbox);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = 'Test Input " + (i + 1) + "';", textbox);
            Assert.assertEquals(textbox.getAttribute("value"), "Test Input " + (i + 1));
        }
    }

    @Test
    public void testAllTextboxesClear() {
        List<WebElement> allInputs = driver.findElements(By.cssSelector("input, textarea"));
        
        for (WebElement input : allInputs) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", input);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = 'Clear Me';", input);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", input);
            Assert.assertEquals(input.getAttribute("value"), "");
        }
    }

    @Test
    public void testEmailTextbox() {
        List<WebElement> emailInputs = driver.findElements(By.cssSelector("input[type='email']"));
        
        for (WebElement email : emailInputs) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", email);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = 'test@example.com';", email);
            Assert.assertEquals(email.getAttribute("value"), "test@example.com");
        }
    }

    @Test
    public void testPasswordTextbox() {
        List<WebElement> passwordInputs = driver.findElements(By.cssSelector("input[type='password']"));
        
        for (WebElement password : passwordInputs) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", password);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = 'password123';", password);
            Assert.assertEquals(password.getAttribute("value"), "password123");
        }
    }

    @Test
    public void testTextarea() {
        List<WebElement> textareas = driver.findElements(By.tagName("textarea"));
        
        for (WebElement textarea : textareas) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", textarea);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = 'Multi-line text content';", textarea);
            Assert.assertEquals(textarea.getAttribute("value"), "Multi-line text content");
        }
    }

    @Test
    public void testNumberInput() {
        List<WebElement> numberInputs = driver.findElements(By.cssSelector("input[type='number']"));
        
        for (WebElement number : numberInputs) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", number);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '123';", number);
            Assert.assertEquals(number.getAttribute("value"), "123");
        }
    }

    @Test
    public void testDateInput() {
        List<WebElement> dateInputs = driver.findElements(By.cssSelector("input[type='date']"));
        
        for (WebElement date : dateInputs) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", date);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '2024-01-01';", date);
            Assert.assertEquals(date.getAttribute("value"), "2024-01-01");
        }
    }

    @Test
    public void testTelInput() {
        List<WebElement> telInputs = driver.findElements(By.cssSelector("input[type='tel']"));
        
        for (WebElement tel : telInputs) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tel);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '123-456-7890';", tel);
            Assert.assertEquals(tel.getAttribute("value"), "123-456-7890");
        }
    }

    @Test
    public void testUrlInput() {
        List<WebElement> urlInputs = driver.findElements(By.cssSelector("input[type='url']"));
        
        for (WebElement url : urlInputs) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", url);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = 'https://example.com';", url);
            Assert.assertEquals(url.getAttribute("value"), "https://example.com");
        }
    }

    @AfterMethod
    public void tearDown() {
        try {
            Thread.sleep(2000); // Wait 2 seconds to see the results
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (driver != null) {
            driver.quit();
        }
    }
}