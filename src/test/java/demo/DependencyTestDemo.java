package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class DependencyTestDemo {

    WebDriver driver;

    @BeforeClass (alwaysRun = true)
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass (alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }

    @Test (groups ="googleSearch")
    public void testValidLogin(){
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester", Keys.TAB,
                "testWrongPart", Keys.ENTER);
        Assert.assertEquals(driver.getTitle(), "Web Orders");
    }

    @Test (dependsOnMethods = "testValidLogin")
    public void testDeleteSelected() throws InterruptedException {
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            checkbox.click();
        }
        driver.findElement(By.id("ctl00_MainContent_btnDelete")).click();
        Thread.sleep(1000);
        Assert.assertTrue(driver.getPageSource().contains("List of orders is empty. In order to add new order use"));
    }
}
