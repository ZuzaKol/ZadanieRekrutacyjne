import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import org.testng.annotations.Test;

public class SeleniumTest {

    @Test
    public void searchAndOpen5thResult() {
        //Set Driver path and open google
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");

        //Accept cookies
        driver.findElement(By.cssSelector("button[id='L2AGLb'] div[role='none']")).click();

        //Enter query
        WebElement p=driver.findElement(By.name("q"));
        p.sendKeys("Selenium Java");
        p.sendKeys(Keys.RETURN);

        //Find 5th result
        String fifthResultXpath = "(//h3[not(ancestor::div[@jsname])])[5]";
        WebElement fifthResult = driver.findElement(By.xpath(fifthResultXpath));

        //Scroll to element
        Actions actions = new Actions(driver);
        actions.moveToElement(fifthResult);
        actions.perform();

        //Click
        fifthResult.click();


    }
}



