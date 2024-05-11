package pl.seleniumtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class GooglePage {
    protected WebDriver driver;

    //Elements
    @FindBy(name = "q")
    private WebElement searchQuery;

    private WebElement result;
    private String resultTitle;


    //Initialising PageFactory
    public GooglePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //Methods
    public void setQuery(String query) {
        searchQuery.sendKeys(query);
        searchQuery.sendKeys(Keys.RETURN);
    }

    public void findResult(Integer index) {
        //Find 5th result
        String resultXpath = String.format("(//h3[not(ancestor::div[@jsname])])[%d]", index);
        result = driver.findElement(By.xpath(resultXpath));
        //Get title
        resultTitle = result.getText();
        if (resultTitle.endsWith(" ...")) {
            resultTitle = resultTitle.substring(0, resultTitle.length() - 4).trim();
        }
        //Scroll to element
        Actions actions = new Actions(driver);
        actions.moveToElement(result);
        actions.perform();
    }

    public void openResult() {
        //Click
        result.click();
        // Wait for the page to load
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleContains(resultTitle));
        //Validation
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(resultTitle), "Validation fail");
    }

//    public void validation1() {
//        //Validate presence of desired result
//        Assert.assertTrue(result.isDisplayed(), "The desired result is not displayed");
//
//
//        String resultsXPath = "(//h3[not(ancestor::div[@jsname])])";
//        List<WebElement> results = driver.findElements(By.xpath(resultsXPath));
//        Assert.assertEquals(results.get(4).getText(), fifthResult.getText(), "Validation 1 fail");
//    }
}



