package pl.seleniumtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GooglePage {
    protected WebDriver driver;

    //Elements
    @FindBy(name = "q")
    private WebElement searchQuery;

    private WebElement result;

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
        //Scroll to element
        Actions actions = new Actions(driver);
        actions.moveToElement(result);
        actions.perform();

    }

    public void openResult() {
        //Click
        result.click();
        //Wait for the page to load
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



