package pl.seleniumtest.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {
    protected WebDriver driver;


    @BeforeMethod
    public void setup() {
        //Set Driver path and open google
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
        //Accept cookies
        driver.findElement(By.xpath("//div[text()='Zaakceptuj wszystko']")).click();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
