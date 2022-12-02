package Zadania;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadanie2 {

    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
    }
    @AfterEach
    public void driverQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void testIfLanguageChanged() {
        driver.navigate().to("https:wikipedia.pl");
        String expectedPolishTitle = "Wikipedia, wolna encyklopedia";
        Assertions.assertEquals(expectedPolishTitle, driver.getTitle(), "Page title is not: " + expectedPolishTitle);
       String expectedPolishURL = "https://https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna";
        Assertions.assertEquals(expectedPolishURL, driver.getCurrentUrl(), "Current URL is not: " + expectedPolishURL);
        String language = "lang= \"pl\"";
        Assertions.assertTrue(driver.getPageSource().contains(language), "Page source does not contain lang= \"pl\"");
        driver.findElement(By.cssSelector("a(title='hiszpanski')")).click();
        String expectedSpanishTitle = "Wikipedia, la enciclopedia libre";
        Assertions.assertEquals(expectedSpanishTitle, driver.getTitle(), "Page title is not: " + expectedSpanishTitle);
        String expectedSpanishURL = "https://es.wikipedia.org/wiki/Wikipedia:Portada";
        Assertions.assertEquals(expectedSpanishURL, driver.getCurrentUrl(), "Current URL is not: " + expectedSpanishURL);
        String spanishLanguage = "lang= \"es\"";
        Assertions.assertTrue(driver.getPageSource().contains(spanishLanguage), "Page source does not contain lang= \"es\"");


    }
}