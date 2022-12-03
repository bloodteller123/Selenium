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
        Thread.sleep(100);
        driver1 = new ChromeDriver();
        driver2 = new ChromeDriver();
        driver3 = new ChromeDriver();
        driver4 = new ChromeDriver();

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
        driver1.quit();
        driver2.quit();
        driver3.quit();
        driver4.quit();
        server.stop();
    }
    @Test
    void row41() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());

        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(100);
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
        Thread.sleep(100);
        driver1.findElement(By.id("startButton")).click();

        js1.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '1H')");
        js1.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '1H'");
        js1.executeScript("document.getElementById('discard').innerHTML = '4H'");
        js1.executeScript("cards[0] = '1H'");

        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        Thread.sleep(100);
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
        Thread.sleep(100);
        js4.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '7H')");
        js4.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '7H'");
        js4.executeScript("cards[0] = '7H'");
        Thread.sleep(100);
        driver4.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        assertEquals(3, server.getNextPlayer());
    }
    @Test
    void row44() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(100);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(100);
        js1.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', 'QC')");
        js1.executeScript("document.getElementById('cards').firstElementChild.innerHTML = 'QC'");
        js1.executeScript("document.getElementById('discard').innerHTML = '3C'");
        js1.executeScript("cards[0] = 'QC'");

        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver2)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertNotNull(alert);
        assertEquals(3, server.getNextPlayer());
    }
    @Test
    void row45() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(100);
        driver1.findElement(By.id("startButton")).click();
//        Thread.sleep(310);
        js1.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '3C')");
        js1.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '3C'");
        js1.executeScript("document.getElementById('discard').innerHTML = '3S'");
        js1.executeScript("cards[0] = '3C'");

        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        driver1.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        js2.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '3C')");
        js2.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '3C'");
        js2.executeScript("document.getElementById('discard').innerHTML = '3S'");
        js2.executeScript("cards[0] = '3C'");

        driver2.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        driver2.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        js3.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '3C')");
        js3.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '3C'");
        js3.executeScript("document.getElementById('discard').innerHTML = '3S'");
        js3.executeScript("cards[0] = '3C'");

        driver3.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        driver3.findElement(By.id("passButton")).click();
        Thread.sleep(100);

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
        Thread.sleep(100);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(100);
        js1.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '3C')");
        js1.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '3C'");
        js1.executeScript("document.getElementById('discard').innerHTML = '3S'");
        js1.executeScript("cards[0] = '3C'");

        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        driver1.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        js2.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '3C')");
        js2.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '3C'");
        js2.executeScript("document.getElementById('discard').innerHTML = '3S'");
        js2.executeScript("cards[0] = '3C'");

        driver2.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        driver2.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        js3.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '3C')");
        js3.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '3C'");
        js3.executeScript("document.getElementById('discard').innerHTML = '3S'");
        js3.executeScript("cards[0] = '3C'");

        driver3.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        driver3.findElement(By.id("passButton")).click();
        Thread.sleep(100);

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
        Thread.sleep(100);
        assertEquals(2, server.getNextPlayer());
    }
    @Test
    void row48() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(100);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(100);
        js1.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '3C')");
        js1.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '3C'");
        js1.executeScript("document.getElementById('discard').innerHTML = '3S'");
        js1.executeScript("cards[0] = '3C'");

        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        driver1.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        js2.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '3C')");
        js2.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '3C'");
        js2.executeScript("document.getElementById('discard').innerHTML = '3S'");
        js2.executeScript("cards[0] = '3C'");

        driver2.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        driver2.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        js3.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', '3C')");
        js3.executeScript("document.getElementById('cards').firstElementChild.innerHTML = '3C'");
        js3.executeScript("document.getElementById('discard').innerHTML = '3S'");
        js3.executeScript("cards[0] = '3C'");

        driver3.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        driver3.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        js4.executeScript("document.getElementById('cards').firstElementChild.setAttribute('id', 'QC')");
        js4.executeScript("document.getElementById('cards').firstElementChild.innerHTML = 'QC'");
        js4.executeScript("document.getElementById('discard').innerHTML = '3C'");
        js4.executeScript("cards[0] = 'QC'");

        driver4.findElement(By.id("cards")).findElement(By.cssSelector("div > :first-child")).click();
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver1)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertNotNull(alert);
        assertEquals(2, server.getNextPlayer());
    }
    @Test
    void row51() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(100);
        driver1.findElement(By.id("startButton")).click();
//        Thread.sleep(310);
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
        Thread.sleep(100);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(100);
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
        Thread.sleep(100);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(100);
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
        Thread.sleep(100);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(100);
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
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(100);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(100);
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
        Thread.sleep(100);
        js1.executeScript("document.getElementById('cards').children[1].setAttribute('id', '6C')");
        js1.executeScript("document.getElementById('cards').children[1].innerHTML = '6C'");
        js1.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js1.executeScript("cards = ['3H','6C']");
        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(2)")).click();
        WebElement discard = driver1.findElement(By.id("discard"));
        assertEquals("6C", discard.getText());
    }
    @Test
    void row59() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(100);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(100);
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
        Thread.sleep(100);
        js1.executeScript("document.getElementById('cards').children[1].setAttribute('id', '6D')");
        js1.executeScript("document.getElementById('cards').children[1].innerHTML = '6D'");
        js1.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js1.executeScript("cards = ['3H','6D']");

        drawB.click();
        Thread.sleep(100);
        js1.executeScript("document.getElementById('cards').children[2].setAttribute('id', '5C')");
        js1.executeScript("document.getElementById('cards').children[2].innerHTML = '5C'");
        js1.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js1.executeScript("cards = ['3H','6D','5C']");
        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(3)")).click();
        WebElement discard = driver1.findElement(By.id("discard"));
        assertEquals("5C", discard.getText());
    }
    @Test
    void row60() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(100);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(100);
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
        Thread.sleep(100);
        js1.executeScript("document.getElementById('cards').children[1].setAttribute('id', '6D')");
        js1.executeScript("document.getElementById('cards').children[1].innerHTML = '6D'");
        js1.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js1.executeScript("cards = ['3H','6D']");
        js1.executeScript("renderCards()");

        drawB.click();
        Thread.sleep(100);
        js1.executeScript("document.getElementById('cards').children[2].setAttribute('id', '5S')");
        js1.executeScript("document.getElementById('cards').children[2].innerHTML = '5S'");
        js1.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js1.executeScript("cards = ['3H','6D','5S']");
        js1.executeScript("renderCards()");

        drawB.click();
        Thread.sleep(100);
        js1.executeScript("document.getElementById('cards').children[3].setAttribute('id', '7H')");
        js1.executeScript("document.getElementById('cards').children[3].innerHTML = '7H'");
        js1.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js1.executeScript("cards = ['3H','6D','5S','7H']");
        js1.executeScript("renderCards()");
        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(4)")).click();
        WebElement discard = driver1.findElement(By.id("discard"));
        assertEquals("7H", discard.getText());
    }
    @Test
    void row61() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(100);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(100);
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
        Thread.sleep(100);
        js1.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js1.executeScript("cards = ['3H','6D']");
        js1.executeScript("renderCards()");

        drawB.click();
        Thread.sleep(100);
        js1.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js1.executeScript("cards = ['3H','6D','5S']");
        js1.executeScript("renderCards()");
        js1.executeScript("updateCards()");

        drawB.click();
        Thread.sleep(100);
        js1.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js1.executeScript("cards = ['3H','6D','5S','4H']");
        js1.executeScript("renderCards()");
        js1.executeScript("updateCards()");
        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(4)")).click();
        alert = wait.until(ExpectedConditions.alertIsPresent());
        assertNotNull(alert);
        alert.accept();
        assertEquals("visible", passB.getCssValue("visibility"));
        assertEquals("hidden", drawB.getCssValue("visibility"));
    }
    @Test
    void row62() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(100);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(100);
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
        Thread.sleep(100);
        js1.executeScript("document.getElementById('cards').children[1].setAttribute('id', '6D')");
        js1.executeScript("document.getElementById('cards').children[1].innerHTML = '6D'");
        js1.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js1.executeScript("cards = ['3H','6D']");
        js1.executeScript("renderCards()");

        drawB.click();
        Thread.sleep(100);
        js1.executeScript("document.getElementById('cards').children[2].setAttribute('id', '8H')");
        js1.executeScript("document.getElementById('cards').children[2].innerHTML = '8H'");
        js1.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js1.executeScript("cards = ['3H','6D','8H']");
        js1.executeScript("renderCards()");

        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(3)")).click();
        alert = wait.until(ExpectedConditions.alertIsPresent());
        assertNotNull(alert);
        alert.sendKeys("C");
        alert.accept();
        Thread.sleep(100);
        WebElement discard = driver1.findElement(By.id("discard"));
        assertEquals("8C", discard.getText());
    }
    @Test
    void row63() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(100);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(100);

        js1.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js1.executeScript("cards =['KS','3C']");
        js1.executeScript("renderCards()");

        WebElement drawB = driver1.findElement(By.id("drawButton"));
        WebElement passB = driver1.findElement(By.id("passButton"));

        assertEquals("hidden", passB.getCssValue("visibility"));
        assertEquals("visible", drawB.getCssValue("visibility"));
        drawB.click();
        Thread.sleep(100);

        js1.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js1.executeScript("cards = ['KS','3C','6C']");
        js1.executeScript("renderCards()");

        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(3)")).click();

        Thread.sleep(100);
        WebElement discard = driver1.findElement(By.id("discard"));
        assertEquals("6C", discard.getText());
    }
    @Test
    void row67() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(100);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(100);

        js1.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js1.executeScript("cards =['2C','4D']");
        js1.executeScript("renderCards()");
        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        Thread.sleep(100);
        WebElement discard = driver1.findElement(By.id("discard"));
        assertEquals("2C", discard.getText());
        js2.executeScript("cards =['4H']");
        js2.executeScript("renderCards()");
        driver1.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        WebElement drawB = driver2.findElement(By.id("drawButton"));
        WebElement passB = driver2.findElement(By.id("passButton"));

        assertEquals("hidden", passB.getCssValue("visibility"));
        assertEquals("visible", drawB.getCssValue("visibility"));
        drawB.click();
        drawB.click();
        Thread.sleep(100);
//        js2.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js2.executeScript("cards = ['4H','6C','9D']");
        js2.executeScript("renderCards()");

        driver2.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(2)")).click();

        Thread.sleep(100);
        discard = driver2.findElement(By.id("discard"));
        assertEquals("6C", discard.getText());
    }
    @Test
    void row68() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(100);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(100);

        js1.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js1.executeScript("cards =['2C','4D']");
        js1.executeScript("renderCards()");
        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        Thread.sleep(100);
        WebElement discard = driver1.findElement(By.id("discard"));
        assertEquals("2C", discard.getText());
        js2.executeScript("cards =['4H']");
        js2.executeScript("renderCards()");
        driver1.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        WebElement drawB = driver2.findElement(By.id("drawButton"));
        WebElement passB = driver2.findElement(By.id("passButton"));

        assertEquals("hidden", passB.getCssValue("visibility"));
        assertEquals("visible", drawB.getCssValue("visibility"));
        drawB.click();
        drawB.click();
        Thread.sleep(100);
//        js2.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js2.executeScript("cards = ['4H','6S','9D']");
        js2.executeScript("renderCards()");
        drawB.click();
        drawB.click();
        Thread.sleep(100);
//        js2.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js2.executeScript("cards = ['4H','6S','9D','9H','6C']");
        js2.executeScript("renderCards()");

        driver2.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(5)")).click();

        Thread.sleep(100);
        discard = driver2.findElement(By.id("discard"));
        assertEquals("6C", discard.getText());
    }
    @Test
    void row69() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(100);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(100);

        js1.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js1.executeScript("cards =['2C','4D']");
        js1.executeScript("renderCards()");
        js1.executeScript("updateCards()");
        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        Thread.sleep(100);
        WebElement discard = driver1.findElement(By.id("discard"));
        assertEquals("2C", discard.getText());

        js2.executeScript("cards =['4H']");
        js2.executeScript("renderCards()");
        js2.executeScript("updateCards()");
        driver1.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        WebElement drawB = driver2.findElement(By.id("drawButton"));
        WebElement passB = driver2.findElement(By.id("passButton"));

        assertEquals("hidden", passB.getCssValue("visibility"));
        assertEquals("visible", drawB.getCssValue("visibility"));
        drawB.click();
        drawB.click();
        Thread.sleep(100);
//        js2.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js2.executeScript("cards = ['4H','6S','9D']");
        js2.executeScript("renderCards()");
        js2.executeScript("updateCards()");
        drawB.click();
        drawB.click();
        drawB.click();
        Thread.sleep(100);
//        js2.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js2.executeScript("cards = ['4H','6S','9D','9H','7S','5H']");
        js2.executeScript("renderCards()");
        js2.executeScript("updateCards()");
        assertEquals("visible", passB.getCssValue("visibility"));
        assertEquals("hidden", drawB.getCssValue("visibility"));
    }
    @Test
    void row70() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(100);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(100);

        js1.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js1.executeScript("cards =['2C','4D']");
        js1.executeScript("renderCards()");
        js1.executeScript("updateCards()");
        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        Thread.sleep(100);
        WebElement discard = driver1.findElement(By.id("discard"));
        assertEquals("2C", discard.getText());

        js2.executeScript("cards =['4H']");
        js2.executeScript("renderCards()");
        js2.executeScript("updateCards()");
        driver1.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        WebElement drawB = driver2.findElement(By.id("drawButton"));
        WebElement passB = driver2.findElement(By.id("passButton"));

        assertEquals("hidden", passB.getCssValue("visibility"));
        assertEquals("visible", drawB.getCssValue("visibility"));
        drawB.click();
        drawB.click();
        Thread.sleep(100);
//        js2.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js2.executeScript("cards = ['4H','2H','9D']");
        js2.executeScript("renderCards()");
        js2.executeScript("updateCards()");

        js3.executeScript("cards = ['7D']");
        js3.executeScript("renderCards()");
        js3.executeScript("updateCards()");
        driver2.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(2)")).click();
        passB.click();

        WebElement drawB3 = driver3.findElement(By.id("drawButton"));
        assertEquals("visible", drawB3.getCssValue("visibility"));
        drawB3.click();
        drawB3.click();
        drawB3.click();
        drawB3.click();
        js3.executeScript("cards = ['7D','5S','6D','6H','7C']");
        js3.executeScript("renderCards()");
        js3.executeScript("updateCards()");
        driver3.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(4)")).click();

        Thread.sleep(100);
        discard = driver3.findElement(By.id("discard"));
        assertEquals("6H", discard.getText());
    }
    @Test
    void row72() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(100);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(100);

        js1.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js1.executeScript("cards =['2C','4D']");
        js1.executeScript("renderCards()");
        js1.executeScript("updateCards()");
        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        Thread.sleep(100);
        WebElement discard = driver1.findElement(By.id("discard"));
        assertEquals("2C", discard.getText());

        js2.executeScript("cards =['4C','6C','9D']");
        js2.executeScript("renderCards()");
        js2.executeScript("updateCards()");
        driver1.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        WebElement drawB = driver2.findElement(By.id("drawButton"));
        WebElement passB = driver2.findElement(By.id("passButton"));

        assertEquals("hidden", passB.getCssValue("visibility"));
        assertEquals("visible", drawB.getCssValue("visibility"));
        driver2.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        Thread.sleep(100);
        driver2.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        Thread.sleep(100);
//        js2.executeScript("document.getElementById('discard').innerHTML = '7C'");
        assertEquals("visible", passB.getCssValue("visibility"));
        assertEquals("visible", drawB.getCssValue("visibility"));
        assertEquals(3,server.getNextPlayer());
        driver2.findElement(By.id("passButton")).click();
        assertEquals(4,server.getNextPlayer());
    }
    @Test
    void row73() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
//        Thread.sleep(310);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(100);

        js1.executeScript("document.getElementById('discard').innerHTML = '7C'");
        js1.executeScript("cards =['2C','4D']");
        js1.executeScript("renderCards()");
        js1.executeScript("updateCards()");
        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        Thread.sleep(100);
        WebElement discard = driver1.findElement(By.id("discard"));
        assertEquals("2C", discard.getText());

        js2.executeScript("cards =['4C','6C']");
        js2.executeScript("renderCards()");
        js2.executeScript("updateCards()");
        driver1.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        WebElement drawB = driver2.findElement(By.id("drawButton"));
        WebElement passB = driver2.findElement(By.id("passButton"));

        assertEquals("hidden", passB.getCssValue("visibility"));
        assertEquals("visible", drawB.getCssValue("visibility"));
        driver2.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        Thread.sleep(100);
        driver2.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        Thread.sleep(100);

        WebDriver[] drivers = new WebDriver[]{driver1, driver2,driver3,driver4};
        Wait<WebDriver> wait;
        Alert alert;
        for(WebDriver d : drivers){
            wait = new FluentWait<WebDriver>(d)
                    .withTimeout(Duration.ofSeconds(5))
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(NoSuchElementException.class);
            alert = wait.until(ExpectedConditions.alertIsPresent());
            assertNotNull(alert);
            alert.accept();
        }
        WebElement welcomeMsg = driver1.findElement(By.id("welcome"));
        String val1 = welcomeMsg.getText();
        System.out.println(val1);
        assertEquals("Wait for Player2 to start the game", val1);
        WebElement startB = driver2.findElement(By.id("startButton"));
        assertEquals("visible", startB.getCssValue("visibility"));
    }
    @Test
    void row77() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(100);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(100);

        js1.executeScript("document.getElementById('discard').innerHTML = '3D'");
        js1.executeScript("cards =['1S','4D']");
        js1.executeScript("renderCards()");
        js1.executeScript("updateCards()");
        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(2)")).click();
        Thread.sleep(100);
        js3.executeScript("cards =['8H','JH','6H','KH','KS']");
        js3.executeScript("renderCards()");
        js3.executeScript("updateCards()");
        Thread.sleep(100);
        js4.executeScript("cards =['8C','8D','2D']");
        js4.executeScript("renderCards()");
        js4.executeScript("updateCards()");
        Thread.sleep(100);
        js2.executeScript("cards =['5D']");
        js2.executeScript("renderCards()");
        js2.executeScript("updateCards()");

        driver1.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver2.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        Thread.sleep(100);
        WebDriver[] drivers = new WebDriver[]{driver1, driver2,driver3,driver4};
        Wait<WebDriver> wait;
        Alert alert;
        for(WebDriver d : drivers){
            wait = new FluentWait<WebDriver>(d)
                    .withTimeout(Duration.ofSeconds(5))
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(NoSuchElementException.class);
            alert = wait.until(ExpectedConditions.alertIsPresent());
            assertNotNull(alert);
            alert.accept();
        }
        WebElement table = driver2.findElement(By.id("table"));
        String val1 = table.getText();
        System.out.println(val1);
        assertTrue(val1.contains("1.0.86.102."));
    }
    @Test
    void row81() throws InterruptedException {
        driver1.get(sampleFile.toUri().toString());
        driver2.get(sampleFile.toUri().toString());
        driver3.get(sampleFile.toUri().toString());
        driver4.get(sampleFile.toUri().toString());
        Thread.sleep(100);
        driver1.findElement(By.id("startButton")).click();
        Thread.sleep(100);

        js1.executeScript("document.getElementById('discard').innerHTML = '4D'");

        js1.executeScript("cards =['4H', '7S', '5D', '6D', '9D']");
        js1.executeScript("renderCards()");
        js1.executeScript("updateCards()");
        Thread.sleep(100);
        js2.executeScript("cards =['4S', '6S', 'KC', '8H', '10D']");
        js2.executeScript("renderCards()");
        js2.executeScript("updateCards()");
        Thread.sleep(100);
        js3.executeScript("cards =['9S', '6C', '9C', 'JD', '3H']");
        js3.executeScript("renderCards()");
        js3.executeScript("updateCards()");
        Thread.sleep(100);
        js4.executeScript("cards =['7D', 'JH', 'QH', 'KH', '5C']");
        js4.executeScript("renderCards()");
        js4.executeScript("updateCards()");

        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        driver1.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver2.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        driver2.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver3.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        driver3.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        assertEquals("hidden", driver4.findElement(By.id("passButton")).getCssValue("visibility"));
        driver4.findElement(By.id("drawButton")).click();
        js4.executeScript("cards = ['7D', 'JH', 'QH', 'KH', '5C','2C']");
        js4.executeScript("renderCards()");
        js4.executeScript("updateCards()");
        assertEquals("hidden", driver4.findElement(By.id("passButton")).getCssValue("visibility"));
        driver4.findElement(By.id("drawButton")).click();
        js4.executeScript("cards = ['7D', 'JH', 'QH', 'KH', '5C','2C','3C']");
        js4.executeScript("renderCards()");
        js4.executeScript("updateCards()");
        assertEquals("hidden", driver4.findElement(By.id("passButton")).getCssValue("visibility"));
        driver4.findElement(By.id("drawButton")).click();
        js4.executeScript("cards = ['7D', 'JH', 'QH', 'KH', '5C','2C','3C','4C']");
        js4.executeScript("renderCards()");
        js4.executeScript("updateCards()");
        assertEquals("visible", driver4.findElement(By.id("passButton")).getCssValue("visibility"));
        driver4.findElement(By.id("passButton")).click();

        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        driver1.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver2.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        driver2.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver3.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        driver3.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver4.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(6)")).click();
        driver4.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver1.findElement(By.id("drawButton")).click();
        driver1.findElement(By.id("drawButton")).click();

        js1.executeScript("cards =['5D', '6D', '9D','10C','JC']");
        js1.executeScript("renderCards()");
        js1.executeScript("updateCards()");
        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(5)")).click();
        driver1.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver2.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        driver2.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver3.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        driver3.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver4.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(6)")).click();
        driver4.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver1.findElement(By.id("drawButton")).click();

        js1.executeScript("cards =['5D', '6D', '9D','10C','7C']");
        js1.executeScript("renderCards()");
        js1.executeScript("updateCards()");
        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(5)")).click();
        driver1.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver2.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        Thread.sleep(100);

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver2)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertNotNull(alert);
        alert.sendKeys("D");
        alert.accept();
        Thread.sleep(100);
        assertEquals("8D", driver2.findElement(By.id("discard")).getText());
        driver2.findElement(By.id("passButton")).click();

        driver3.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        driver3.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver4.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        driver4.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(3)")).click();
        driver1.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver2.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        Thread.sleep(100);

        WebDriver[] drivers = new WebDriver[]{driver1, driver2,driver3,driver4};
        for(WebDriver d : drivers){
            wait = new FluentWait<WebDriver>(d)
                    .withTimeout(Duration.ofSeconds(5))
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(NoSuchElementException.class);
            alert = wait.until(ExpectedConditions.alertIsPresent());
            assertNotNull(alert);
            alert.accept();
        }

        WebElement table = driver2.findElement(By.id("table"));
        String val1 = table.getText();
        assertTrue(val1.contains("21.0.3.39."));

        assertEquals(2, server.getCurrentRoundStarter());
        Thread.sleep(100);
        driver2.findElement(By.id("startButton")).click();
        js2.executeScript("document.getElementById('discard').innerHTML = '4D'");

        js2.executeScript("cards =['9D', '3S', '9C', '3H', 'JC']");
        js2.executeScript("renderCards()");
        js2.executeScript("updateCards()");
        Thread.sleep(100);
        js3.executeScript("cards =['3D', '9S', '3C', '9H', '5H']");
        js3.executeScript("renderCards()");
        js3.executeScript("updateCards()");
        Thread.sleep(100);
        js4.executeScript("cards =['4D', '7S', '4C', '5S', '8D']");
        js4.executeScript("renderCards()");
        js4.executeScript("updateCards()");
        Thread.sleep(100);
        js1.executeScript("cards =['7D', '4S', '7C', '4H', '5D']");
        js1.executeScript("renderCards()");
        js1.executeScript("updateCards()");

        driver2.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        driver2.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver3.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        driver3.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver4.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        driver4.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(2)")).click();
        driver1.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver2.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        driver2.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver3.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        driver3.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver4.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        driver4.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(2)")).click();
        driver1.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver2.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        driver2.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver3.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        driver3.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver4.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        driver4.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver1.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(2)")).click();
        driver1.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver2.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        driver2.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver3.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        driver3.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver4.findElement(By.id("drawButton")).click();

        js4.executeScript("cards =['5S', '8D','KS']");
        js4.executeScript("renderCards()");
        js4.executeScript("updateCards()");

        driver4.findElement(By.id("drawButton")).click();

        js4.executeScript("cards =['5S', '8D','KS','QS']");
        js4.executeScript("renderCards()");
        js4.executeScript("updateCards()");

        driver4.findElement(By.id("drawButton")).click();

        js4.executeScript("cards =['5S', '8D','KS','QS','KH']");
        js4.executeScript("renderCards()");
        js4.executeScript("updateCards()");

        driver4.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(5)")).click();
        driver4.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver1.findElement(By.id("drawButton")).click();

        js1.executeScript("cards =['7D', '5D','6D']");
        js1.executeScript("renderCards()");
        js1.executeScript("updateCards()");

        driver1.findElement(By.id("drawButton")).click();

        js1.executeScript("cards =['7D', '5D','6D','QD']");
        js1.executeScript("renderCards()");
        js1.executeScript("updateCards()");

        driver1.findElement(By.id("drawButton")).click();

        js1.executeScript("cards =['7D', '5D','6D','QD','JD']");
        js1.executeScript("renderCards()");
        js1.executeScript("updateCards()");

        assertEquals("hidden", driver1.findElement(By.id("drawButton")).getCssValue("visibility"));
        driver1.findElement(By.id("passButton")).click();
        Thread.sleep(100);

        driver2.findElement(By.id("drawButton")).click();

        js2.executeScript("cards =['JC','6S']");
        js2.executeScript("renderCards()");
        js2.executeScript("updateCards()");

        driver2.findElement(By.id("drawButton")).click();

        js2.executeScript("cards =['JC','6S','JS']");
        js2.executeScript("renderCards()");
        js2.executeScript("updateCards()");

        driver2.findElement(By.id("drawButton")).click();

        js2.executeScript("cards =['JC','6S','JS','10S']");
        js2.executeScript("renderCards()");
        js2.executeScript("updateCards()");

        assertEquals("hidden", driver2.findElement(By.id("drawButton")).getCssValue("visibility"));
        driver2.findElement(By.id("passButton")).click();

        Thread.sleep(100);

        driver3.findElement(By.id("cards")).findElement(By.cssSelector("div > :nth-child(1)")).click();
        Thread.sleep(100);
        for(WebDriver d : drivers){
            wait = new FluentWait<WebDriver>(d)
                    .withTimeout(Duration.ofSeconds(5))
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(NoSuchElementException.class);
            alert = wait.until(ExpectedConditions.alertIsPresent());
            assertNotNull(alert);
            alert.accept();
        }
        table = driver3.findElement(By.id("table"));
        val1 = table.getText();
        assertTrue(val1.contains("38.36.0.75."));
        Thread.sleep(100);
        WebElement finalScore = driver3.findElement(By.id("finalScore"));
        String val2 = finalScore.getText();
        assertEquals("59.36.3.114", val2);
        assertEquals("3", server.getWinner());
    }
}
