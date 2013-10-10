package fivecardstud;

public class Deck {
    final int DECK_SIZE;
    Card[] deck;
    String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack",
        "Queen", "King", "Ace"};
    String[] suites = {"Spades", "Hearts", "Diamonds", "Clubs"};
    
    public Deck() {
        this.DECK_SIZE = 52;
        this.deck = new Card[DECK_SIZE];
        
        // iterate through values and suites to create each card
        int i = 0;
        for (String suite : suites){
            for (String value : values){
                deck[i] = new Card(value, suite);
                i++;
            }
        }
        shuffleDeck();
    }
    
    public final Card[] shuffleDeck(){
        int rand;
        Card temp;
        
        // swap the card at index i with any later card
        for (int i=0; i<DECK_SIZE; i++){
            rand = i + (int)(Math.random() * ((DECK_SIZE-1 - i) + 1));
            temp = deck[i];
            this.deck[i] = deck[rand];
            deck[rand] = temp;
        }
        return deck;
    }
}
