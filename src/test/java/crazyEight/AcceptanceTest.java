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
    JavascriptExecutor js;

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

        js = (JavascriptExecutor) driver1;
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
        js.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '3C')");
        js.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '3C'");
        js.executeScript("document.getElementById('discard').innerHTML = '3S'");
        js.executeScript("cards[0] = '3C'");

        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();

        assertEquals(2, server.getNextPlayer());
    }
}
