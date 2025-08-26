package selenium.Forms;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class DropdownExample {
    private static final String URL = "https://testautomationcentral.com/demo/dropdown.html";

    public static void main(String[] args) {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        
        try {
            driver.get(URL);
            
            WebElement dropdown = driver.findElement(By.tagName("select"));
            Select select = new Select(dropdown);
            
            // Test dropdown selection
            if (select.getOptions().size() > 1) {
                select.selectByIndex(1);
                System.out.println("Selected option: " + select.getFirstSelectedOption().getText());
                
                String firstOptionText = select.getOptions().get(0).getText();
                select.selectByVisibleText(firstOptionText);
                System.out.println("Selected by text: " + select.getFirstSelectedOption().getText());
            }
            
            // Display dropdown info
            System.out.println("Total options: " + select.getOptions().size());
            System.out.println("Is multiple select: " + select.isMultiple());
            
        } finally {
            driver.quit();
        }
    }
}