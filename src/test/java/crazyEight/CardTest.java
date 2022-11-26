package crazyEight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    @Test
    void cardInitTest(){
        Card c = new Card("6", "S");
        assertEquals("6", c.getRank());
        assertEquals("S", c.getSuit());
        assertEquals("6S", c.toString());
    }
}
