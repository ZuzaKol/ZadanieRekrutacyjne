package pl.seleniumtest.tests;
import org.testng.annotations.*;
import pl.seleniumtest.pages.GooglePage;

public class GoogleTest extends BaseTest {

    @Test
    public void searchAndOpen5thResult() {
        GooglePage googlePage = new GooglePage(driver);
        googlePage.setQuery("Java Selenium");
        googlePage.findResult(5);
        googlePage.openResult();
    }
}



