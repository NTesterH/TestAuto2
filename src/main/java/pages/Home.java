package pages;


import com.beust.jcommander.IStringConverter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class Home {
    WebDriver driver;
    String homeUrl="https://duckduckgo.com/";

    // Locators
     By logoLocator = By.xpath("//div[contains(@class,'Desktop')]//img");
     By searchBox =By.name("q");
     By firstResultLinkLocator = By.xpath("//article[contains(@id,'r1-0')]//h2/a");
    Wait<WebDriver> wait;

    public boolean isLogoDisplayed() {

        wait =returnExplicitWait();
        wait.until(d -> {
            d.findElement(logoLocator).isDisplayed();
            return true;
        });
        return driver.findElement(logoLocator).isDisplayed();
    }
    public String searchInResults(String searchValue){

        wait =returnExplicitWait();
        wait.until(d -> {
            d.findElement(searchBox).sendKeys(searchValue);
            d.findElement(searchBox).submit();
            d.findElement(firstResultLinkLocator).getAttribute("href");
            return true;
        });
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
     private Wait<WebDriver>  returnExplicitWait()
     {
         wait = new FluentWait<>(driver)
                 .withTimeout(Duration.ofSeconds(10))
                 .pollingEvery(Duration.ofMillis(300))
                 .ignoring(ElementNotInteractableException.class)
                 .ignoring(NoSuchElementException.class)
                 .ignoring(StaleElementReferenceException.class)
         ;
         return wait;
     }
}
