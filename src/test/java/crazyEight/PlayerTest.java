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
    @Test
    void addCardTest(){
        Player p = new Player(1);
        p.addCard(new Card("6", "S"));
        p.addCard(new Card("5", "S"));
        assertEquals(2,p.getCards().size());
    }
    @Test
    void removeCardTest(){
        Player p = new Player(1);
        p.addCard(new Card("6", "S"));
        p.addCard(new Card("5", "S"));
        p.removeCard("6S");
        assertEquals(1,p.getCards().size());
        p.removeCard("3C");
        assertEquals(1,p.getCards().size());
    }
    @Test
    void scoreTest(){
        Player p = new Player(1);
        p.addCard(new Card("6", "S"));
        p.addCard(new Card("5", "S"));
        p.addCard(new Card("J", "S"));
        assertEquals(21,p.calculateScore());
        p.addCard(new Card("A", "S"));
        p.addCard(new Card("Q", "S"));
        assertEquals(32,p.calculateScore());
    }
}
