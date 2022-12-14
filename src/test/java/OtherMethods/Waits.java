package OtherMethods;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Waits {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 730));
        driver.manage().window().setPosition(new Point(5, 30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.navigate().to("https://fakestore.testelka.pl/product/grecja-limnos/");
        driver.findElement(By.cssSelector(".woocommerce-store-notice__dismiss-link")).click();

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void waitExample() {
        driver.findElement(By.cssSelector("[name='add-to-cart']")).click();
        driver.findElement(By.cssSelector(".woocommerce-message .wc-forward")).click();
        WebElement quantityField = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id^='quantity']")));

        quantityField.clear();
        quantityField.sendKeys("2");
        driver.findElement(By.cssSelector("[name='update_cart']")).click();

        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".blockUI"), 0));

        String amount = driver.findElement(By.cssSelector(".order-total .amount")).getText();
        String expected = "6 400,00 zł";
        Assertions.assertEquals(expected, amount, "Total price is not correct");
    }

    }

