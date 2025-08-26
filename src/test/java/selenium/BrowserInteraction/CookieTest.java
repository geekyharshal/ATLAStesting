package selenium.BrowserInteraction;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Set;

public class CookieTest {
    private static final String URL = "https://testautomationcentral.com/demo/cookies.html";

    public static void main(String[] args) {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        
        try {
            driver.get(URL);
            
            testAddCookie(driver);
            testGetAllCookies(driver);
            testGetCookieByName(driver);
            testDeleteCookie(driver);
            testDeleteAllCookies(driver);
            
            System.out.println("All cookie tests completed successfully!");
            
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    public static void testAddCookie(WebDriver driver) {
        Cookie cookie = new Cookie("testCookie", "testValue");
        driver.manage().addCookie(cookie);
        
        Cookie retrievedCookie = driver.manage().getCookieNamed("testCookie");
        assert retrievedCookie != null;
        assert "testValue".equals(retrievedCookie.getValue());
        
        System.out.println("testAddCookie passed");
    }

    public static void testGetAllCookies(WebDriver driver) {
        Set<Cookie> cookies = driver.manage().getCookies();
        assert cookies != null;
        
        System.out.println("Found " + cookies.size() + " cookies");
        for (Cookie cookie : cookies) {
            System.out.println("Cookie: " + cookie.getName() + " = " + cookie.getValue());
        }
        
        System.out.println("testGetAllCookies passed");
    }

    public static void testGetCookieByName(WebDriver driver) {
        Cookie cookie = driver.manage().getCookieNamed("testCookie");
        if (cookie != null) {
            assert "testValue".equals(cookie.getValue());
            System.out.println("Found cookie: " + cookie.getName() + " = " + cookie.getValue());
        }
        
        System.out.println("testGetCookieByName passed");
    }

    public static void testDeleteCookie(WebDriver driver) {
        driver.manage().deleteCookieNamed("testCookie");
        
        Cookie deletedCookie = driver.manage().getCookieNamed("testCookie");
        assert deletedCookie == null;
        
        System.out.println("testDeleteCookie passed");
    }

    public static void testDeleteAllCookies(WebDriver driver) {
        // Add a test cookie first
        Cookie cookie = new Cookie("tempCookie", "tempValue");
        driver.manage().addCookie(cookie);
        
        driver.manage().deleteAllCookies();
        
        Set<Cookie> cookies = driver.manage().getCookies();
        assert cookies.isEmpty();
        
        System.out.println("testDeleteAllCookies passed");
    }
}