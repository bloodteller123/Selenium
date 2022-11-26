package crazyEight;

public class Card {
    String suit;
    String rank;
    public Card(String rank, String suit){
        this.suit = suit;
        this.rank = rank;
    }
    public String getSuit(){
        return this.suit;
    }
    public String getRank(){
        return this.rank;
    }
    @Override
    public String toString(){
        return this.rank+this.suit;
    }
}
