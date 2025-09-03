package tests;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Home;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;


public class BasicTests {
    private WebDriver driver;
    private Home homePage;
    String filePath = System.getProperty("user.dir") + "/src/test/java/tests/resources/TestData.json";

    /** Task1
     * Open Google Chrome
     * Navigate to [<a href="https://duckduckgo.com/">duckduckgo</a>]
     * Assert that the page title is [Google] -- retrieve from json file
     * Close Google Chrome
     */
    @Test
    public void checkPageTitle()
    {

        try {

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) obj;
            String expectedTitle = (String) jsonObject.get("task1_pageTitle");
            homePage.navigate();
            Assert.assertEquals(driver.getTitle(), expectedTitle, "The title not the same.");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred: " + e.getMessage());
        }

    }

    /** Task2
     * Open Google Chrome
     * Navigate to [<a href="https://duckduckgo.com/">duckduckgo</a>]
     * Assert that the DuckDuckGo logo is displayed
     * Close Google Chrome
     */
    @Test
    public void checkLogoIsDisplayed()
    {
        homePage.navigate();
        Assert.assertTrue(homePage.isLogoDisplayed(), "DuckDuckGo logo is not  displayed!");
    }

    /** Task3
     * Open Google Chrome
     * Navigate to [<a href="https://duckduckgo.com/">duckduckgo</a>]
     * Search for [Selenium WebDriver]
     * Assert that the link of the first result is [<a href="https://www.selenium.dev/documentation/webdriver/">webdriver</a>]
     * Close Google Chrome
     */
    @Test
    public void searchForSelenium()
    {

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) obj;
            String searchKeyWords = (String) jsonObject.get("task3_ searchKeywords");
            homePage.navigate();
            Assert.assertEquals( homePage.searchInResults(searchKeyWords), (String) jsonObject.get("task3_ firstResult"), "No Match.");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred: " + e.getMessage());
        }
    }


    @BeforeMethod
    public void setUp()
    {

        driver = new ChromeDriver();
        homePage = new Home(driver);
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
