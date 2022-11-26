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
    public int calculateScore(){
        int totalScore = 0;
        for(Card card : this.cards){
            if(card.getRank()=="8"){
                totalScore+=50;
            }else if(card.getRank()=="J" || card.getRank()=="Q" || card.getRank()=="K"){
                totalScore+=10;
            }else if(card.getRank()=="A"){
                totalScore+=1;
            }else{
                totalScore+=Integer.parseInt(card.getRank());
            }
        }
        return totalScore;
    }
    public int getId(){
        return this.id;
    }
    public List<Card> getCards(){
        return this.cards;
    }
}
