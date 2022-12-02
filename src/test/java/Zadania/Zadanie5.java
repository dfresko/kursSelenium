package Zadania;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
public class Zadanie5 {
    WebDriver driver;
    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
    }

    @AfterEach
    public void driverQuit() {
        driver.close();
        driver.quit();
    }
    @Test
    public void findingElementsBy() {
        driver.findElement(By.id("woocommerce-product-search-field-0"));
        driver.findElement(By.name("s"));
       driver.findElement(By.className("search-field"));

        driver.findElement(By.id("username"));
        driver.findElement(By.name("username"));

        driver.findElement(By.id("password"));
        driver.findElement(By.name("password"));

        driver.findElement(By.id("rememberme"));
        driver.findElement(By.name("rememberme"));

        driver.findElement(By.linkText("Nie pamiętasz hasła?"));
        driver.findElement(By.partialLinkText("Nie pamiętasz hasła?"));

        driver.findElement(By.linkText("Żeglarstwo"));
        driver.findElement(By.partialLinkText("Żeglarstwo"));






    }
}
