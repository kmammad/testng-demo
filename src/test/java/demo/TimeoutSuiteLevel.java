package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TimeoutSuiteLevel {

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

    @Test (groups = "flaky")
    public void testValidLogin() throws InterruptedException {
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester", Keys.TAB,
                "test", Keys.ENTER);
        Thread.sleep(10000);
        Assert.assertEquals(driver.getTitle(), "Web Orders");
    }


}
