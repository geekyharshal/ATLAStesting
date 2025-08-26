package selenium.Miscellaneous;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class TableTestNG {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("https://testautomationcentral.com/demo/table_interaction.html");
    }

    @Test
    public void testPrintTableData() {
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

    @Test
    public void testPrintTableHeaders() {
        List<WebElement> headers = driver.findElements(By.tagName("th"));
        
        System.out.println("Table Headers:");
        for (WebElement header : headers) {
            System.out.println("- " + header.getText());
        }
        System.out.println();
    }

    @Test
    public void testFindSpecificCell() {
        try {
            WebElement cell = driver.findElement(By.xpath("//table//tr[2]//td[1]"));
            System.out.println("Cell at Row 2, Column 1: " + cell.getText());
        } catch (Exception e) {
            System.out.println("Could not find specific cell: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}