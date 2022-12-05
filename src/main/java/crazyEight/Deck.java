package crazyEight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards = null;
    private int index;
    String[] suits = {"S", "H", "D", "C"};
    String[] ranks = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    public Deck(){
        this.cards = new ArrayList<>();
        index = 0;
        buildCards();
        Collections.shuffle(this.cards);
    }
    public void buildCards(){
        for (int i = 0; i<suits.length; i++) {
            for(int j=0; j<ranks.length; j++){
                this.cards.add(new Card(ranks[j],suits[i]));
            }
        }
    }
    public Card drawCard(){
        return this.cards.get(this.index++);
    }
    public boolean isExausted(){
        return this.index==this.cards.size();
    }
    public void resetDeck(){
        this.cards = new ArrayList<>();
        index = 0;
        buildCards();
        Collections.shuffle(this.cards);
    }
    public int getInd(){
        return this.index;
    }
    public int getCardsSize(){
        return this.cards.size();
    }
    public Deck getDeck(){return this;}
    public void insert(int position){
        Card c = this.cards.remove(--index);
        this.cards.add(position, c);
        System.out.println(this.cards);
    }
}

