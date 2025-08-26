package selenium.BrowserInteraction;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.remote.Augmenter;

public class LocalStorageTest {
    private WebDriver driver;
    private static final String URL = "https://testautomationcentral.com/demo/local_storage.html";

    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(URL);
    }

    public void testLocalStorage() {
        WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
        LocalStorage localStorage = webStorage.getLocalStorage();
        
        localStorage.setItem("testKey", "testValue");
        String value = localStorage.getItem("testKey");
        System.out.println("Value from local storage for 'testKey': " + value);
        assert "testValue".equals(value);
        
        localStorage.removeItem("testKey");
        String removedValue = localStorage.getItem("testKey");
        assert removedValue == null;
        System.out.println("Item removed successfully");
        
        localStorage.setItem("key1", "value1");
        localStorage.setItem("key2", "value2");
        localStorage.clear();
        assert localStorage.size() == 0;
        System.out.println("Local storage cleared successfully");
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void main(String[] args) {
        LocalStorageTest test = new LocalStorageTest();
        
        test.setUp();
        try {
            test.testLocalStorage();
            System.out.println("Local storage test passed");
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        } finally {
            test.tearDown();
        }
    }
}