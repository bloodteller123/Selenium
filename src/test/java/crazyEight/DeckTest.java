package crazyEight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    @Test
    void initDeckTest(){
        Deck d = new Deck();
        assertNotNull(d.getDeck());
        assertEquals(0, d.getInd());
    }
}
