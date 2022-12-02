package Zadania;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Zadanie9 {
    WebDriver driver;
    By demoStoreBar = By.cssSelector("a[class*='dismiss-link']");
    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 730));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.navigate().to("https://fakestore.testelka.pl/metody-na-elementach");
        driver.findElement(demoStoreBar).click();
    }
    @AfterEach
    public void driverQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void checkElementsStateTest() {
        WebElement mainPageButton = driver.findElement(By.cssSelector("input[name='main-page']"));
        WebElement sailingButton = driver.findElement(By.cssSelector("[name='sailing']"));
        List<WebElement> yellowButtons = driver.findElements(By.cssSelector("a.button"));
        WebElement selectedCheckbox = driver.findElement(By.cssSelector("input[name='selected-checkbox']"));
        WebElement notSelectedCheckbox = driver.findElement(By.cssSelector("input[name='not-selected-checkbox']"));
        WebElement selectedRadiobutton = driver.findElement(By.cssSelector("input[name='selected-radio']"));
        WebElement notSelectedRadiobutton = driver.findElement(By.cssSelector("input[name='not-selected-radio']"));
        List<WebElement> elementsWithButtonClass = driver.findElements(By.cssSelector(".button"));
        Assertions.assertAll("Checking properties of elements",
                () -> Assertions.assertFalse(mainPageButton.isEnabled(), "'Main page' button is not disabled."),
                () -> Assertions.assertFalse(sailingButton.isDisplayed(), "'Sailing' button is probably displayed."),
                () -> assertThatButtonsAreYellow(yellowButtons),
                () -> Assertions.assertTrue(selectedCheckbox.isSelected(), "Checkbox is not selected."),
                () -> Assertions.assertTrue(selectedRadiobutton.isSelected(), "Radiobutton is not selected."),
                () -> Assertions.assertFalse(notSelectedCheckbox.isSelected(), "Checkbox is probably selected."),
                () -> Assertions.assertFalse(notSelectedRadiobutton.isSelected(), "Radiobutton is probably selected."),
                () -> assertElementsHaveCorrectTag(elementsWithButtonClass)
        );
    }
    public void assertThatButtonsAreYellow(List<WebElement> buttons) {
        for (WebElement button : buttons) {
            String color = button.getCssValue("background-color");
            Assertions.assertEquals("rgba(245, 233, 101, 1)", color, "Button color is not what expected.");
        }
    }
    public void assertElementsHaveCorrectTag(List<WebElement> elements) {
        for (WebElement element : elements) {
            Assertions.assertEquals("a", element.getTagName(), "Element's tag is not 'a'.");
        }

    }
}
