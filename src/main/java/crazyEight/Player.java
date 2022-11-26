package crazyEight;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;
    private List<Card> cards;
    private int score;
    public Player(int id){
        this.id = id;
        cards = new ArrayList<>();
    }
    public void addCard(Card c){
        this.cards.add(c);
    }
    public void removeCard(String c){
        this.cards.removeIf(card -> card.toString().equals(c));
    }

    public int getScore(){
        return this.score;
    }
    public int getId(){
        return this.id;
    }
    public List<Card> getCards(){
        return this.cards;
    }
}
