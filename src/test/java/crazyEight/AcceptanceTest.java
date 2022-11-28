package crazyEight;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class AcceptanceTest {
    WebDriver driver1;
    WebDriver driver2;
    WebDriver driver3;
    WebDriver driver4;
    String path = "/Users/god/intellij-workspace/crazy8/src/test/resources/game.html";
    String path2 = "/Users/god/intellij-workspace/crazy8/src/test/resources/game2.html";
    Path sampleFile;
    Server server;
    JavascriptExecutor js1;
    JavascriptExecutor js2;
    JavascriptExecutor js3;
    JavascriptExecutor js4;
    static int i;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
        i= 0;
    }
    @BeforeEach
    void setup() throws InterruptedException {
        Thread.sleep(1000);
        driver1 = new ChromeDriver();
//        driver2 = new ChromeDriver();
//        driver3 = new ChromeDriver();
//        driver4 = new ChromeDriver();

        js1 = (JavascriptExecutor) driver1;
        js2 = (JavascriptExecutor) driver2;
        js3 = (JavascriptExecutor) driver3;
        js4 = (JavascriptExecutor) driver4;

        sampleFile = Paths.get(path);
        System.out.println("Server1");
        server = new Server(8800);
        System.out.println("Server2");
        server.start();
        System.out.println("Server3");
    }
    @AfterEach
    void teardown() throws InterruptedException {
//        driver1.quit();
//        driver2.quit();
//        driver3.quit();
//        driver4.quit();
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
        Thread.sleep(5000);
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(1000);
        driver1.findElement(By.id("startButton")).click();

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
        Thread.sleep(1000);
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
        Thread.sleep(1000);
        driver1.findElement(By.id("startButton")).click();
//        Thread.sleep(1000);
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
        Thread.sleep(1000);
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
    @Test
    void row48() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(1000);
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

        js4.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', 'QC')");
        js4.executeScript("document.getElementById('cards').firstElementChild.innerHTML = 'QC'");
        js4.executeScript("document.getElementById('discard').innerHTML = '3C'");
        js4.executeScript("cards[0] = 'QC'");

        driver4.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        assertEquals(2, server.getNextPlayer());
    }
    @Test
    void row51() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(1000);
        driver1.findElement(By.id("startButton")).click();
//        Thread.sleep(1000);
        js1.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', 'KH')");
        js1.executeScript("document.getElementById('cards').firstElementChild.innerHTML = 'KH'");
        js1.executeScript("document.getElementById('discard').innerHTML = 'KC'");
        js1.executeScript("cards[0] = 'KH'");
        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        String val1 = driver1.findElement(By.id("discard")).getText();
        assertEquals("KH", val1);
    }
    @Test
    void row52() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(1000);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(1000);
        js1.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '7C')");
        js1.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '7C'");
        js1.executeScript("document.getElementById('discard').innerHTML = 'KC'");
        js1.executeScript("cards[0] = '7C'");
        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        String val1 = driver1.findElement(By.id("discard")).getText();
        assertEquals("7C", val1);
    }
    @Test
    void row53() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(1000);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(1000);
        js1.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '8H')");
        js1.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '8H'");
        js1.executeScript("document.getElementById('discard').innerHTML = 'KC'");
        js1.executeScript("cards[0] = '8H'");
        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver1)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertNotNull(alert);
    }
    @Test
    void row54() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(1000);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(1000);
        js1.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '8H')");
        js1.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '8H'");
        js1.executeScript("document.getElementById('discard').innerHTML = 'KC'");
        js1.executeScript("cards[0] = '5S'");
        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver1)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertNotNull(alert);
    }
    @Test
    void row58() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
//        driver2.get(sampleFile.toUri().toString());
//        driver3.get(sampleFile.toUri().toString());
//        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(1000);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(1000);
        js1.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '3H')");
        js1.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '3H'");
        js1.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js1.executeScript("cards =['3H']");
        js1.executeScript("renderCards()");
        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver1)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertNotNull(alert);
        alert.accept();
        WebElement drawB = driver1.findElement(By.id("drawButton"));
        WebElement passB = driver1.findElement(By.id("passButton"));

        assertEquals("hidden", passB.getCssValue("visibility"));
        assertEquals("visible", drawB.getCssValue("visibility"));
        drawB.click();
        Thread.sleep(1000);
        js1.executeScript("document.getElementById('cards').children[1].setAttribute('id', '6C')");
        js1.executeScript("document.getElementById('cards').children[1].innerHTML = '6C'");
        js1.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js1.executeScript("cards = ['3H','6C']");
        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(2)")).click();
        WebElement discard = driver1.findElement(By.id("discard"));
        assertEquals("6C", discard.getText());
    }
    //js4.executeScript("document.getElementById('cards').innerHTML='';cards = [7D, JH, QH, KH, 5C]");
    //js4.executeScript("renderCards()");
}
