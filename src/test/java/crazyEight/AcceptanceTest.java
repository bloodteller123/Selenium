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
//        System.out.println(val1);
        assertTrue(val1.contains("right"));

        String val2 = indication2.getText();
        assertTrue(val2.contains("right"));
        String val3 = indication3.getText();
        assertTrue(val3.contains("right"));
        String val4 = indication4.getText();
        assertTrue(val4.contains("right"));
        driver1.findElement(By.id("passButton")).click();
        Thread.sleep(300);
        js4.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '7H')");
        js4.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '7H'");
        js4.executeScript("cards[0] = '7H'");
        Thread.sleep(300);
        driver4.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        assertEquals(3, server.getNextPlayer());
    }
    @Test
    void row44() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());

        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(1000);
        js1.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', 'QC')");
        js1.executeScript("document.getElementById('cards').firstElementChild.innerHTML = 'QC'");
        js1.executeScript("document.getElementById('discard').innerHTML = '3C'");
        js1.executeScript("cards[0] = 'QC'");

        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();

        assertEquals(3, server.getNextPlayer());
    }
    @Test
    void row45() throws InterruptedException {
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
        driver1.findElement(By.id("passButton")).click();
        Thread.sleep(300);

        js2.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '3C')");
        js2.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '3C'");
        js2.executeScript("document.getElementById('discard').innerHTML = '3S'");
        js2.executeScript("cards[0] = '3C'");

        driver2.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        driver2.findElement(By.id("passButton")).click();
        Thread.sleep(300);

        js3.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '3C')");
        js3.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '3C'");
        js3.executeScript("document.getElementById('discard').innerHTML = '3S'");
        js3.executeScript("cards[0] = '3C'");

        driver3.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        driver3.findElement(By.id("passButton")).click();
        Thread.sleep(300);

        js4.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '3C')");
        js4.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '3C'");
        js4.executeScript("document.getElementById('discard').innerHTML = '3S'");
        js4.executeScript("cards[0] = '3C'");

        driver4.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();

        assertEquals(1, server.getNextPlayer());
    }
    @Test
    void row46() throws InterruptedException {
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
        driver1.findElement(By.id("passButton")).click();
        Thread.sleep(300);

        js2.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '3C')");
        js2.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '3C'");
        js2.executeScript("document.getElementById('discard').innerHTML = '3S'");
        js2.executeScript("cards[0] = '3C'");

        driver2.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        driver2.findElement(By.id("passButton")).click();
        Thread.sleep(300);

        js3.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '3C')");
        js3.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '3C'");
        js3.executeScript("document.getElementById('discard').innerHTML = '3S'");
        js3.executeScript("cards[0] = '3C'");

        driver3.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        driver3.findElement(By.id("passButton")).click();
        Thread.sleep(300);

        js4.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '1H')");
        js4.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '1H'");
        js4.executeScript("document.getElementById('discard').innerHTML = '3H'");
        js4.executeScript("cards[0] = '1H'");

        driver4.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        WebElement indication4 = driver4.findElement(By.id("player_indication"));

        String val4 = indication4.getText();
        assertTrue(val4.contains("right"));
        assertEquals(3, server.getNextPlayer());
        driver4.findElement(By.id("passButton")).click();
        
        js3.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '7H')");
        js3.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '7H'");
        js3.executeScript("document.getElementById('discard').innerHTML = '1H'");
        js3.executeScript("cards[0] = '7H'");

        driver3.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        Thread.sleep(300);
        assertEquals(2, server.getNextPlayer());
    }
    //js4.executeScript("document.getElementById('cards').innerHTML='';cards = [7D, JH, QH, KH, 5C]");
    //js4.executeScript("renderCards()");
}
