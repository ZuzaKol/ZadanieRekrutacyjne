import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class SeleniumTest {

    @Test
    public void searchAndOpen5thResult() {
        //Set Driver path and open google
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");

        //Accept cookies
        driver.findElement(By.xpath("//div[text()='Zaakceptuj wszystko']")).click();

        //Enter query
        WebElement p=driver.findElement(By.name("q"));
        p.sendKeys("Selenium Java");
        p.sendKeys(Keys.RETURN);

        //Find 5th result
        String fifthResultXpath = "(//h3[not(ancestor::div[@jsname])])[5]";
        WebElement fifthResult = driver.findElement(By.xpath(fifthResultXpath));

        //Validation 1
        String resultsXPath = "(//h3[not(ancestor::div[@jsname])])";
        List<WebElement> results = driver.findElements(By.xpath(resultsXPath));
        Assert.assertEquals(results.get(4).getText(), fifthResult.getText(), "Validation 1 fail");

        //Scroll to element
        Actions actions = new Actions(driver);
        actions.moveToElement(fifthResult);
        actions.perform();

        //Click
        fifthResult.click();

        //Wait for the page to load
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Validation 2
        String actualURL = driver.getCurrentUrl();
        Assert.assertFalse(actualURL.contains("google"), "Validation 2 fail.'");

        //Driver quit
        driver.quit();
    }
}



