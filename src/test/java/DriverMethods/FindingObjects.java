package DriverMethods;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindingObjects {
    WebDriver driver;
    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1290, 730));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.navigate().to("https://wikipedia.pl");
    }
    @AfterEach
    public void driverQuit() {
        driver.close();
        driver.quit();
    }
    @Test
    public void findElementById(){
    findElement(By.id("search Input"));
    findElement(By.name("search"));
    findElement(By.className("searchButton"));


}

    private void findElement(By search_input) {
    }

    @Test
    public void findElementByLinkText() {
        findElement(By.linkText("Wikisłownik"));
    }
}
