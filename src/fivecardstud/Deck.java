package fivecardstud;
import java.util.ArrayList;
import java.util.List;

public class Deck {
    int deckSize;
    List<Card> usedCardIndex;
    List<Card> cards;
    
    public Deck() {
        cards = new ArrayList<>();
        usedCardIndex = new ArrayList<>();

        createDeck();
        deckSize = cards.size();
    }
    
    final public void createDeck() {
        final String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", 
            "Jack", "Queen", "King", "Ace"};
        final String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        
        // iterate values and suites to create each card; stop at deckSize
        for (String suit: suits){
            for (String value : values){
                cards.add(new Card(value, suit));
            }
        }
    }
    
    public void shuffle(){
        int rand;
        Card temp;
        
        // swap the card at index i with a random later card
        for (int i=0; i<deckSize; i++){
            rand = i + (int)(Math.random() * ((deckSize-1 - i) + 1));
            temp = cards.get(i);
            cards.set(i, cards.get(rand));
            cards.set(rand, temp);
        }
    }
}
