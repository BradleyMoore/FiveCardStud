package fivecardstud;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    Card cardTemp;
    String handName;
    List<Card> cards;
    List<String> cardsBySuit;
    List<Integer> cardsByValue;
    List<Card> cardsTemp;
    boolean skipIter;
    int player;
    int rank;
    int valueTemp;
    
    public Hand() {
        cards = new ArrayList<>();
        cardsTemp = new ArrayList<>();
        cardsBySuit = new ArrayList<>();
        cardsByValue = new ArrayList<>();
    }
    
    public void deal(){
        List<Card> hand;
        hand = new ArrayList();

        for (Card card: FiveCardStud.deck.cards){
             // check to see if card is already dealt
            if(FiveCardStud.deck.usedCardIndex.contains(card)){
                continue;
            // if card is unused, use it, add to used list, iterate all
            } else {
                FiveCardStud.deck.usedCardIndex.add(card);
                hand.add(card);
            }
            if (hand.size() >= Status.handSize) break;
        }
        Collections.sort(hand, new ValueComparator());
        for (Card card: hand) System.out.println(card.rank + " of " + card.suit);
    }
    
    void show(){
        for (Card card: cards) {
            System.out.println(card.rank + " " + card.suit);
        }
    }
    
    Card getHighCard(){
        Card highCard;
        highCard = cards.get(0);
        for (Card card: cards) {
            if (card.value > highCard.value) highCard = card;
        }
        return highCard;
    }
 
    List checkFlush(){
        String suitTemp;
        suitTemp = cardsBySuit.get(0);
        cardsTemp.add(cards.get(0));
        skipIter = true;
        int i = 0;
        for (String suit: cardsBySuit) {
            // skip first iteration
            if (skipIter == true) {
                skipIter = false;
                continue;
            }
            i++;
            if (suitTemp.equals(suit)) {
                suitTemp = suit;
                cardsTemp.add(cards.get(i));
            }
        }
        return cardsTemp;
    }
    
    boolean checkStrait(){
        skipIter = true;
        valueTemp = cardsByValue.get(0);
        for (int value: cardsByValue) {
            // skip first iteration
            if (skipIter == true) {
                skipIter = false;
                continue;
            }
            valueTemp+=1;
            if (valueTemp == value) {
                valueTemp = value;
            } else {
                return false;
            }
        }
        return true;
    }
    
    
    
    // rank hands from 0 being the worst and up to the best
    public int evaluate(){

        return this.rank;
    }
}
