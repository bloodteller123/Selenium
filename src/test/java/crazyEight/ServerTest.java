package crazyEight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ServerTest {
    @Test
    void checkWinTest(){
        Player p1 = new Player(1);
        Player p2 = new Player(2);
        Server s = new Server(8870);

        p1.setScore(100);
        p2.setScore(50);

        s.setPlayers(p1);
        s.setPlayers(p2);
        assertTrue(s.checkWin());
    }
    @Test
    void updateScoreTest(){
        Player p1 = new Player(1);
        Player p2 = new Player(2);
        Server s = new Server(8870);

        p1.addCard(new Card("3","S"));
        p1.addCard(new Card("6","S"));
        p2.addCard(new Card("5","S"));
        p2.addCard(new Card("8","S"));
        p2.addCard(new Card("9","S"));
        p2.addCard(new Card("10","S"));

        s.setPlayers(p1);
        s.setPlayers(p2);
        s.updateScore();

        assertEquals(9,p1.getScore());
        assertEquals(74,p2.getScore());
    }
}
