package tests;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Home;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Supplier;


public class TasksTests {
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
    public void setUp() {

        //  driver = new ChromeDriver();
        //  homePage = new Home(driver);
        /** Task12
         * Refactor all the tasks to read the “TargetBrowser” from an external .properties file,
         * with support for Chrome and Firefox, running on Windows
         */
        try {
            String configPath = System.getProperty("user.dir") + "/src/main/config.properties";
            Properties properties = new Properties();
            properties.load(new FileInputStream(configPath));
            String browser = properties.getProperty("TargetBrowser").toLowerCase();

            Map<String, Supplier<WebDriver>> drivers = new HashMap<>();
            drivers.put("chrome", ChromeDriver::new);
            drivers.put("firefox", org.openqa.selenium.firefox.FirefoxDriver::new);

            driver = drivers.getOrDefault(browser, ChromeDriver::new).get();

            homePage = new Home(driver);
        }

            catch (Exception e) {
                e.printStackTrace();
                Assert.fail("Exception occurred: " + e.getMessage());
            }
        }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
