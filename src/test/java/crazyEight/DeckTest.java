package crazyEight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    @Test
    void initDeckTest(){
        Deck d = new Deck();
        assertNotNull(d.getDeck());
        assertEquals(0, d.getInd());
        assertEquals(52, d.getCardsSize());
    }
    @Test
    void drawTest(){
        Deck d = new Deck();
        assertNotNull(d.drawCard());
        assertEquals(1, d.getInd());
    }
}
