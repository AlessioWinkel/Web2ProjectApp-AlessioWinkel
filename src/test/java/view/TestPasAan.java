package view;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestPasAan {

    private WebDriver driver;
    private static final String url = "http://localhost:8080/";

    @Before
    public void setup() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_overzicht_wordt_getoond_als_alles_correct_is_ingevuld(){
        driver.get(url+"Web2ProjectApp_war_exploded/Controller?command=Overzicht");
        driver.findElement(By.id("pasAanKnop")).click();

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
    public void test_errors_worden_getoond_op_scherm_bij_lege_naam(){
        driver.get(url+"Web2ProjectApp_war_exploded/Controller?command=Overzicht");
        driver.findElement(By.id("pasAanKnop")).click();

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
    public void test_errors_worden_getoond_op_scherm_als_aantalPersonen_leeg_is(){
        driver.get(url+"Web2ProjectApp_war_exploded/Controller?command=Overzicht");
        driver.findElement(By.id("pasAanKnop")).click();

        WebElement naamVak = driver.findElement(By.id("naamVak"));
        naamVak.clear();
        naamVak.sendKeys("Stijn");

        WebElement aantalPersonenVak = driver.findElement(By.id("aantalPersonenVak"));
        aantalPersonenVak.clear();
        aantalPersonenVak.sendKeys("");

        WebElement telefoonNummerVak = driver.findElement(By.id("telefoonNummerVak"));
        telefoonNummerVak.clear();
        telefoonNummerVak.sendKeys("04802380345");

        driver.findElement(By.id("verstuur")).click();
        ArrayList<WebElement> ul =
                (ArrayList<WebElement>) driver.findElements(By.tagName("ul"));
        assertTrue(containsWebElementsWithText(ul,"VUL EEN NUMMER IN VOOR HET AANTAL PERSONEN."));
    }

    @Test
    public void test_errors_worden_getoond_op_scherm_bij_lege_telefoonnummer(){
        driver.get(url+"Web2ProjectApp_war_exploded/Controller?command=Overzicht");
        driver.findElement(By.id("pasAanKnop")).click();

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
