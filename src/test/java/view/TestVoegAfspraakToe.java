package view;

import com.github.dockerjava.api.model.Driver;
import static org.junit.Assert.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import ucll.DomainException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestVoegAfspraakToe {

    private WebDriver driver;
    private static final String url = "http://localhost:8080/";

    @Before
    public void setup() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @After
    public void clean() {
        //driver.quit();
    }


    @Test
    public void test_Overview_is_shown_If_all_fields_are_filled_out_correctly() {
        driver.get(url+"Web2ProjectApp_war_exploded/Controller?command=VoegToeForm");
        WebElement naamVak = driver.findElement(By.id("naamVak"));
        naamVak.clear();
        naamVak.sendKeys("Stijn");

        WebElement aantalPersonenVak = driver.findElement(By.id("aantalPersonenVak"));
        aantalPersonenVak.clear();
        aantalPersonenVak.sendKeys("3");

        WebElement telefoonNummerVak = driver.findElement(By.id("telefoonNummerVak"));
        telefoonNummerVak.clear();
        telefoonNummerVak.sendKeys("04802380345");

        driver.findElement(By.id("verstuur")).click();
        assertEquals("Overzicht afspraken", driver.getTitle());
        ArrayList<WebElement> tds =
                (ArrayList<WebElement>) driver.findElements(By.tagName("td"));
        assertTrue(containsWebElementsWithText(tds,"Stijn"));
        assertTrue(containsWebElementsWithText(tds,"3"));
        assertTrue(containsWebElementsWithText(tds,"04802380345"));
    }

    @Test
    public void test_Als_Personen_Meer_Dan_4_Is() {
        driver.get(url+"Web2ProjectApp_war_exploded/Controller?command=VoegToeForm");
        WebElement naamVak = driver.findElement(By.id("naamVak"));
        naamVak.clear();
        naamVak.sendKeys("Stijn");

        WebElement aantalPersonenVak = driver.findElement(By.id("aantalPersonenVak"));
        aantalPersonenVak.clear();
        aantalPersonenVak.sendKeys("5");

        WebElement telefoonNummerVak = driver.findElement(By.id("telefoonNummerVak"));
        telefoonNummerVak.clear();
        telefoonNummerVak.sendKeys("04802380345");

        driver.findElement(By.id("verstuur")).click();
        ArrayList<WebElement> ul =
                (ArrayList<WebElement>) driver.findElements(By.tagName("ul"));
        assertTrue(containsWebElementsWithText(ul,"AANTAL MOET POSITIEF ZIJN EN MAG NIET MEER DAN 4 PERSONEN ZIJN."));

    }

    @Test
    public void test_Als_Naam_Leeg_Is() {
        driver.get(url+"Web2ProjectApp_war_exploded/Controller?command=VoegToeForm");
        WebElement naamVak = driver.findElement(By.id("naamVak"));
        naamVak.clear();
        naamVak.sendKeys("");

        WebElement aantalPersonenVak = driver.findElement(By.id("aantalPersonenVak"));
        aantalPersonenVak.clear();
        aantalPersonenVak.sendKeys("3");

        WebElement telefoonNummerVak = driver.findElement(By.id("telefoonNummerVak"));
        telefoonNummerVak.clear();
        telefoonNummerVak.sendKeys("04802380345");

        driver.findElement(By.id("verstuur")).click();
        ArrayList<WebElement> ul =
                (ArrayList<WebElement>) driver.findElements(By.tagName("ul"));
        assertTrue(containsWebElementsWithText(ul,"NAAM MAG NIET LEEG ZIJN."));
    }

    @Test
    public void test_Als_Telefoonnummer_Leeg_Is() {
        driver.get(url+"Web2ProjectApp_war_exploded/Controller?command=VoegToeForm");
        WebElement naamVak = driver.findElement(By.id("naamVak"));
        naamVak.clear();
        naamVak.sendKeys("Stijn");

        WebElement aantalPersonenVak = driver.findElement(By.id("aantalPersonenVak"));
        aantalPersonenVak.clear();
        aantalPersonenVak.sendKeys("3");

        WebElement telefoonNummerVak = driver.findElement(By.id("telefoonNummerVak"));
        telefoonNummerVak.clear();
        telefoonNummerVak.sendKeys("");

        driver.findElement(By.id("verstuur")).click();
        ArrayList<WebElement> ul =
                (ArrayList<WebElement>) driver.findElements(By.tagName("ul"));
        assertTrue(containsWebElementsWithText(ul,"TELEFOONNUMMER MAG NIET LEEG ZIJN."));
    }

    private boolean containsWebElementsWithText(ArrayList<WebElement> elements, String tekst) {
        for (WebElement element : elements) {
            if (element.getText().equals(tekst)) {
                return true;
            }
        }
        return false;
    }
}