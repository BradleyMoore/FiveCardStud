package fivecardstud;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    String handName;
    List<Card> cards;
    List<Card> cardsTemp;
    List<String> suits;
    List<Integer> values;
    boolean skipIter;
    int player;
    int rank;
    int valueTemp;
    
    public Hand(int player) {
        cards = deal(Status.handSize, Status.numPlayers);
        cardsTemp = new ArrayList<>();
        this.player = player;
        
        // create array of suits and array of values for comparrisons in ranking
        for (Card card: cards) {
            suits.add(card.suit);
            values.add(card.value);
        }
        Collections.sort(suits);
        Collections.sort(values);
        
        // sort cards by value

    }
    
    static public List deal(int toDeal, int players){
        List<Card> hand;
        hand = new ArrayList();

        // check to see if card is already dealt
        for (Card card: FiveCardStud.deck.deck){
             // if card already used...iterate
            if(FiveCardStud.deck.usedCardIndex.contains(card)){
                continue;
            // if card is unused, use it, add to used list, iterate all
            } else {
                FiveCardStud.deck.usedCardIndex.add(card);
                hand.add(card);
            }
        }
        return hand;
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
        suitTemp = suits.get(0);
        cardsTemp.add(cards.get(0));
        skipIter = true;
        int i = 0;
        for (String suit: suits) {
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
        valueTemp = values.get(0);
        for (int value: values) {
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
