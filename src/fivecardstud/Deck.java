package fivecardstud;

public class Deck {
    Card[] deck;
    String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack",
        "Queen", "King", "Ace"};
    String[] suites = {"Spades", "Hearts", "Diamonds", "Clubs"};
    
    public Deck() {
        this.deck = new Card[52];
        
        // iterate through values and suites to create each card
        int i = 0;
        for (String suite : suites){
            for (String value : values){
                deck[i] = new Card(value, suite);
            }
        }
    }
}
