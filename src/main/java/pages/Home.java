package pages;


import com.beust.jcommander.IStringConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home {
    WebDriver driver;
    String homeUrl="https://duckduckgo.com/";

    // Locators
     By logoLocator = By.xpath("//div[contains(@class,'Desktop')]//img");
     By searchBox =By.name("q");
     By firstResultLinkLocator = By.xpath("//article[contains(@id,'r1-0')]//h2/a");


    public boolean isLogoDisplayed(){
        return driver.findElement(logoLocator).isDisplayed();
    }
    public String searchInResults(String text){
        driver.findElement(searchBox).sendKeys(text);
        driver.findElement(searchBox).submit();
      return driver.findElement(firstResultLinkLocator).getAttribute("href");
    }

    // get url
    public void navigate(){
        this.driver.navigate().to(homeUrl);
    }
    // Constructor
    public Home(WebDriver driver) {
        this.driver = driver;
    }
}
