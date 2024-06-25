package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataProviderParametrization {

    WebDriver driver;

    @Test (dataProvider = "getData")
    public void testValidLogin(String username, String password){
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://duotify.us-east-2.elasticbeanstalk.com/register.php");

        driver.findElement(By.id("loginUsername")).sendKeys(username, Keys.TAB,
                password, Keys.ENTER);

        Assert.assertTrue(driver.getPageSource().contains("You Might Also Like"));

        driver.quit();
    }

    @DataProvider
    public Object [][] getData(){

        return new Object[][] {
                {"duotech2023", "duotech"},
                {"duotech2050", "duotech123"},
                {"coolherc2055", "coolherc"}
        };
    }


    @Test (dataProvider = "invalidData")
    public void testInvalidLogin(String username, String password){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://duotify.us-east-2.elasticbeanstalk.com/register.php");
        driver.findElement(By.id("loginUsername")).sendKeys(username, Keys.TAB,
                password, Keys.ENTER);
        Assert.assertFalse(driver.getPageSource().contains("You Might Also Like"));
        driver.quit();
    }

    @DataProvider
    public Object [][] invalidData(){
        return readData("invalid.csv");
    }

    public static Object [][] readData(String path) {

        List<String> allLines = null;
        try {
             allLines = Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException();
        }

        List<List<String>> data = new ArrayList<>();
        for (String eachLine : allLines) {
            String[] split = eachLine.split(",");
            List<String> list = Arrays.asList(split);
            data.add(list);
            //    data.add(Arrays.asList( eachLine.split(",") ) );
        }

        System.out.println(data);

        Object[][] arr = new Object[data.size()][data.get(0).size()];
        for (int i = 0; i < arr.length; i++) {
            Object[] each = data.get(i).toArray();
            arr[i] = each; // arr[i] = data.get(i).toArray();
        }
        //System.out.println(Arrays.deepToString(arr));
        return arr;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString( readData("invalid.csv")) );
    }


    }
