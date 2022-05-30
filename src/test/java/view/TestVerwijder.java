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

public class TestVerwijder {

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
    public void test_confirmatie_tonen_na_verwijder_knop(){
        driver.get(url+"Web2ProjectApp_war_exploded/Controller?command=Overzicht");
        driver.findElement(By.id("verwijderKnop")).click();
        assertEquals("Verwijder Confirmatie", driver.getTitle());
    }

    @Test
    public void test_na_verwijderen_terug_op_homepage() {
        driver.get(url+"Web2ProjectApp_war_exploded/Controller?command=Overzicht");

        ArrayList<WebElement> p2 =
                (ArrayList<WebElement>) driver.findElements(By.tagName("p"));
        assertTrue(containsWebElementsWithText(p2,"Aantal afspraken dat gemaakt zijn: 4"));

        driver.findElement(By.id("verwijderKnop")).click();
        driver.findElement(By.id("verwijderConfirmatie")).click();
        assertEquals("Overzicht afspraken", driver.getTitle());

        ArrayList<WebElement> p =
                (ArrayList<WebElement>) driver.findElements(By.tagName("p"));
        assertTrue(containsWebElementsWithText(p,"Aantal afspraken dat gemaakt zijn: 3"));

    }
    @Test
    public void test_toon_overizcht_wanneer_cancel(){
        driver.get(url+"Web2ProjectApp_war_exploded/Controller?command=Overzicht");
        driver.findElement(By.id("verwijderKnop")).click();
        driver.findElement(By.id("verwijderCancel")).click();
        assertEquals("Overzicht afspraken", driver.getTitle());

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
