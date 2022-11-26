package crazyEight;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AcceptanceTest {
    WebDriver driver;
    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();
    }
    @AfterEach
    void teardown() {
        driver.quit();
    }
    
}
