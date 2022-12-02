package Zadania;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
public class Zadanie6 {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 730));
        driver.manage().window().setPosition(new Point(5, 30));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");

    }

    @AfterEach
    public void driverQuit() {
        driver.close();
        driver.quit();
    }


    @Test
    public void testFakeStore1() {

        String userName = "user";
        String password = "haslotestowe";

        driver.findElement(By.cssSelector("input[id='username']")).sendKeys(userName);
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='login']")).click();

        String userDisplayedName = "user";
        String myAccountContent = driver.findElement(By.cssSelector("div[class='woocommerce-MyAccount-content']>p")).getText();
        Assertions.assertTrue(myAccountContent.contains(userDisplayedName),
                "My account page does not contain correct name. Expected name: " + userDisplayedName + "was not found in a string: " + myAccountContent);


    }

    @Test
    public void testFakeStore2() {

        String userEmail = "user@gmail.com";
        String password = "haslotestowe";

        driver.findElement(By.cssSelector("input[id='username']")).sendKeys(userEmail);
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='login']")).click();

        String userDisplayedName = "user";
        String myAccountContent = driver.findElement(By.cssSelector("div[class='woocommerce-MyAccount-content']>p")).getText();
        Assertions.assertTrue(myAccountContent.contains(userDisplayedName),
                "My account page does not contain correct name. Expected name: " + userDisplayedName + "was not found in a string: " + myAccountContent);

    }
    @Test
    public void testFakeStore3() {

        String userName = "user";
        String password = "wrong";

        driver.findElement(By.cssSelector("input[id='username']")).sendKeys(userName);
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='login']")).click();

        String errorMessageText = driver.findElement(By.cssSelector("ul[class='woocommerce-error']")).getText();
        String expectedMessage = "BŁĄD: Wprowadzone hasło dla nazwy użytkownika " + userName + "nie jest poprawne. Nie pamiętasz hasła?";
        Assertions.assertEquals(expectedMessage, errorMessageText,"Error message is not correct");
    }
}