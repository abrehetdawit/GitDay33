package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
//Login tests are maintained here
// without page object approach
public class LoginTests {

    WebDriver driver;


    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
            public void loginTest1(){
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
        driver.findElement(By.id("ctl00_MainContent_login_button")).click();
        Assert.assertEquals(driver.getTitle(),"Web Orders");
    }

    @Test
    public void logOutTest(){
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test"+ Keys.ENTER);
        driver.findElement(By.id("ctl00_logout")).click();
        String title =driver.getTitle();
        Assert.assertEquals(title,"Web Orders");
        Assert.assertTrue(title.contains("error"));

    }

    @Test
    public void negativeTest(){
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("test");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test"+Keys.ENTER);

        String errormessage=driver.findElement(By.id("ctl00_MainContent_status")).getText();


        Assert.assertEquals(errormessage,"Invalid Login or Password.");
        Assert.assertTrue(errormessage.contains("Invalid Login or Password."));
    }


    @Test
    public void negativepassword(){
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester1");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("admin"+Keys.ENTER);

        String errormessage=driver.findElement(By.id("ctl00_MainContent_status")).getText();
        Assert.assertTrue(errormessage.contains("Invalid Login or Password."));
    }
    @AfterMethod
    public void cleanUp(){
        driver.close();
    }
}
