package crazyEight;

//import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class PlayerTest {
    @Test
    void InitializeTest(){
        Player p = new Player(1);
        assertNotNull(p.getCards());
        assertEquals(1,p.getId());
        assertEquals(0,p.getScore());
    }
}
