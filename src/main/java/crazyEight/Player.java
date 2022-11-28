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
    public void setCards(Card[] cs){
        this.cards = new ArrayList<>();
        for(Card c: cs){
            addCard(c);
        }
    }
    public int getScore(){
        return this.score;
    }
    public void setScore(int val){
        this.score += val;
    }
    public int calculateScore(){
        int totalScore = 0;
        for(Card card : this.cards){
            if(card.getRank().equals("8")){
                totalScore+=50;
            }else if(card.getRank().equals("J") || card.getRank().equals("Q") || card.getRank().equals("K")){
                totalScore+=10;
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
