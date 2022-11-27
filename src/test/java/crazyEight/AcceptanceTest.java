package crazyEight;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class AcceptanceTest {
    WebDriver driver1;
    WebDriver driver2;
    WebDriver driver3;
    WebDriver driver4;
    String path = "/Users/god/intellij-workspace/crazy8/src/test/resources/game.html";
    Path sampleFile;
    Server server;
    JavascriptExecutor js1;
    JavascriptExecutor js2;
    JavascriptExecutor js3;
    JavascriptExecutor js4;


    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    void setup() {
        driver1 = new ChromeDriver();
        driver2 = new ChromeDriver();
        driver3 = new ChromeDriver();
        driver4 = new ChromeDriver();

        js1 = (JavascriptExecutor) driver1;
        js2 = (JavascriptExecutor) driver2;
        js3 = (JavascriptExecutor) driver3;
        js4 = (JavascriptExecutor) driver4;
        sampleFile = Paths.get(path);
        server = new Server(8800);
        server.start();
    }
    @AfterEach
    void teardown() {
        driver1.quit();
        driver2.quit();
        driver3.quit();
        driver4.quit();
    }
    @Test
    void row41() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());

        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(1000);
        js1.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '3C')");
        js1.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '3C'");
        js1.executeScript("document.getElementById('discard').innerHTML = '3S'");
        js1.executeScript("cards[0] = '3C'");

        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();

        assertEquals(2, server.getNextPlayer());
    }
    @Test
    void row42() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());

        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(1000);
        js1.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '1H')");
        js1.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '1H'");
        js1.executeScript("document.getElementById('discard').innerHTML = '4H'");
        js1.executeScript("cards[0] = '1H'");

        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        Thread.sleep(300);
        assertEquals(4, server.getNextPlayer());
        WebElement indication1 = driver1.findElement(By.id("player_indication"));
        WebElement indication2 = driver2.findElement(By.id("player_indication"));
        WebElement indication3 = driver3.findElement(By.id("player_indication"));
        WebElement indication4 = driver4.findElement(By.id("player_indication"));

        String val1 = indication1.getText();
        System.out.println(val1);
        assertTrue(val1.contains("right"));

        String val2 = indication2.getText();
        assertTrue(val2.contains("right"));
        String val3 = indication3.getText();
        assertTrue(val3.contains("right"));
        String val4 = indication4.getText();
        assertTrue(val4.contains("right"));

        js4.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '7H')");
        js4.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '7H'");
        js4.executeScript("cards[0] = '7H'");

        driver4.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        assertEquals(3, server.getNextPlayer());
    }
}
