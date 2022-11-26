package crazyEight;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;
    private List<String> cards;
    private int score;
    public Player(int id){
        this.id = id;
        cards = new ArrayList<>();
    }
    public int getScore(){
        return this.score;
    }
    public int getId(){
        return this.id;
    }
    public List<String> getCards(){
        return this.cards;
    }
}
