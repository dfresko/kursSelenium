package Zadania;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadanie3 {
    WebDriver driver;
    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://apod.nasa.gov/apod/image/1809/MilkyWayTongue_Merzlyakov_1790.jpg");
    }

    @AfterEach
    public void closeAndQuit(){
        driver.close();
        driver.quit();
    }
    @Test
    public void windowSettings(){
        //Ustaw rozmiar okna przeglądarki na 854x480.
        Dimension size = new Dimension(845, 480);
        driver.manage().window().setSize(size);
        //Ustaw pozycję okna przeglądarki na 445x30.
        Point position = new Point(445, 30);
        driver.manage().window().setPosition(position);
        //Pobierz rozmiar okna i wykonaj asercję.
        Assertions.assertEquals(size, driver.manage().window().getSize(),
                "Size of the window is not: (" + size.width + ", " + size.height + "). ");
        //Pobierz pozycję okna i wykonaj asercję.
        Assertions.assertEquals(position, driver.manage().window().getPosition(),
                "Position of the window is not (" + position.x + ", " + position.y + "). ");
        //Zmaksymalizuj okno przeglądarki.
        driver.manage().window().maximize();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Ustaw przeglądarkę na fullscreen.
        driver.manage().window().fullscreen();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}