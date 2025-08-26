package selenium.BrowserInteraction;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.remote.Augmenter;

public class SessionStorageTest {
    private WebDriver driver;
    private static final String URL = "https://testautomationcentral.com/demo/session_storage.html";

    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(URL);
    }

    public void testSessionStorage() {
        WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
        SessionStorage sessionStorage = webStorage.getSessionStorage();
        
        sessionStorage.setItem("testKey", "testValue");
        String value = sessionStorage.getItem("testKey");
        System.out.println("Value from session storage for 'testKey': " + value);
        assert "testValue".equals(value);
        
        sessionStorage.removeItem("testKey");
        String removedValue = sessionStorage.getItem("testKey");
        assert removedValue == null;
        System.out.println("Item removed successfully");
        
        sessionStorage.setItem("key1", "value1");
        sessionStorage.setItem("key2", "value2");
        sessionStorage.clear();
        assert sessionStorage.size() == 0;
        System.out.println("Session storage cleared successfully");
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void main(String[] args) {
        SessionStorageTest test = new SessionStorageTest();
        
        test.setUp();
        try {
            test.testSessionStorage();
            System.out.println("Session storage test passed");
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        } finally {
            test.tearDown();
        }
    }
}