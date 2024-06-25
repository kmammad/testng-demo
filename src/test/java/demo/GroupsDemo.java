package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class GroupsDemo {

    WebDriver driver;

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Before Suite: establishing db connection, creating a report object");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("After Suite: tearing down db connection, generate the report");
    }

    @BeforeSuite
    public void beforeSuite2(){ //execution order: alphabetical order of method names (beforeSuite2())
        System.out.println("Before Suite2: establishing db connection, creating a report object");
    }

    @AfterSuite
    public void afterSuite2(){
        System.out.println("After Suite2: tearing down db connection, generate the report");
    }

    @BeforeTest
    public void beforeTest(){ //collection of test cases (subcategory) in large test suite (see xml file)
        System.out.println("Before Test subcategory");
        //ex UI, DB or modules of large collection of test cases belongs to different modules of
        // application like banking: credit card section, debit card, home loan,
    }

    @AfterTest
    public void afterTest(){
        System.out.println("Before Test subcategory");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("Before Class");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("After Class");
    }

    @BeforeMethod (alwaysRun = true)
    public void setUp(){
        System.out.println("Before Method");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        driver.manage().window().maximize();
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(){
        System.out.println("After Method");
        driver.quit();
    }

    @Test (groups = "smoke")
    public void testGoogle(){
        System.out.println("test 1");
        driver.get("https://www.google.com/");
        String searchTerm = "coffee mug";
        driver.findElement(By.name("q")).sendKeys(searchTerm, Keys.ENTER);
        Assert.assertTrue(driver.getTitle().contains(searchTerm));
    }

    @Test
    public void testGoogle2(){
        System.out.println("test 2");
        driver.get("https://www.google.com/");
        String searchTerm = "coffee";
        driver.findElement(By.name("q")).sendKeys(searchTerm, Keys.ENTER);
        Assert.assertTrue(driver.getTitle().contains(searchTerm));
    }
}
