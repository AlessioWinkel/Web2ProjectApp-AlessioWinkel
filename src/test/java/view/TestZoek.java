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

public class TestZoek {

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
    public void test_zoek_waar_niks_ingegeven_wordt() {
        driver.get(url+"Web2ProjectApp_war_exploded/Controller?command=zoekPagina");
        WebElement naamZoek = driver.findElement(By.id("naamZoek"));
        naamZoek.clear();
        naamZoek.sendKeys("");

        driver.findElement(By.id("zoekKnop")).click();
        ArrayList<WebElement> ul =
                (ArrayList<WebElement>) driver.findElements(By.tagName("ul"));
        assertTrue(containsWebElementsWithText(ul,"NAAM MAG NIET LEEG ZIJN."));


    }

    @Test
    public void test_zoek_waar_juist_ingegeven_wordt() {
        driver.get(url+"Web2ProjectApp_war_exploded/Controller?command=zoekPagina");
        WebElement naamZoek = driver.findElement(By.id("naamZoek"));
        naamZoek.clear();
        naamZoek.sendKeys("Alessio");

        driver.findElement(By.id("zoekKnop")).click();
        driver.findElement(By.id("resultaat")).click();
        assertEquals("Zoekresultaat", driver.getTitle());
        ArrayList<WebElement> tekst =
                (ArrayList<WebElement>) driver.findElements(By.tagName("p"));
        assertTrue(containsWebElementsWithText(tekst,"We vonden volgende afspraak met naam Alessio:"));
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
