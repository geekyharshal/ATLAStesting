package selenium.Miscellaneous;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class TableTest {
    public static void main(String[] args) {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        
        try {
            driver.get("https://testautomationcentral.com/demo/table_interaction.html");
            
            printTableData(driver);
            findSpecificCell(driver);
            printTableHeaders(driver);
            
            System.out.println("All table tests completed successfully!");
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    public static void printTableData(WebDriver driver) {
        List<WebElement> tables = driver.findElements(By.tagName("table"));
        
        System.out.println("Found " + tables.size() + " table(s)");
        System.out.println("=".repeat(50));
        
        for (int t = 0; t < tables.size(); t++) {
            WebElement table = tables.get(t);
            System.out.println("Table " + (t + 1) + ":");
            
            List<WebElement> rows = table.findElements(By.tagName("tr"));
            
            for (int r = 0; r < rows.size(); r++) {
                WebElement row = rows.get(r);
                List<WebElement> cells = row.findElements(By.xpath(".//td | .//th"));
                
                System.out.print("Row " + (r + 1) + ": ");
                for (WebElement cell : cells) {
                    System.out.print(cell.getText() + " | ");
                }
                System.out.println();
            }
            System.out.println("-".repeat(30));
        }
    }

    public static void printTableHeaders(WebDriver driver) {
        List<WebElement> headers = driver.findElements(By.tagName("th"));
        
        System.out.println("Table Headers:");
        for (WebElement header : headers) {
            System.out.println("- " + header.getText());
        }
        System.out.println();
    }

    public static void findSpecificCell(WebDriver driver) {
        try {
            WebElement cell = driver.findElement(By.xpath("//table//tr[1]//td[3]"));
            System.out.println("Cell at Row 1, Column 3: " + cell.getText());
        } catch (Exception e) {
            System.out.println("Could not find specific cell: " + e.getMessage());
        }
    }
}