package fivecardstud;

public class Deck {
    int deckSize;
    Card[] deck;
    String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack",
        "Queen", "King", "Ace"};
    String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
    
    public Deck() {
        this.deckSize = 52;
        this.deck = new Card[deckSize];
        
        // iterate through values and suites to create each card
        int i = 0;
        for (String suit: suits){
            for (String value : values){
                deck[i] = new Card(value, suit);
                i++;
            }
        }
    }
    
    public Card[] shuffle(){
        int rand;
        Card temp;
        
        // swap the card at index i with any later card
        for (int i=0; i<deckSize; i++){
            rand = i + (int)(Math.random() * ((deckSize-1 - i) + 1));
            temp = deck[i];
            this.deck[i] = deck[rand];
            deck[rand] = temp;
        }
        return deck;
    }
    
    public Card[] deal(int toDeal){
        int rand;
        
    }
}