package fivecardstud;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    Card cardTemp;
    String handName;
    List<Card> cards;
    List<Card> cardsTemp;
    List<String> suits;
    List<Integer> values;
    boolean skipIter;
    int player;
    int rank;
    int valueTemp;
    
    public Hand() {
        cards = new ArrayList<>();
        cardsTemp = new ArrayList<>();
        suits = new ArrayList<>();
        values = new ArrayList<>();
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
        sortCards(hand);
    }
    
    void sortCards(List<Card> hand){
        List<Card> tempHand;
        List<Card> usedCards;
        tempHand = new ArrayList();
        usedCards = new ArrayList();
        for (Card card: hand) {
            tempHand.add(card);
        }

        // create array of suits and array of values for comparrisons in ranking
        for (Card card: hand) {
            suits.add(card.suit);
            values.add(card.value);
        }
        Collections.sort(suits);
        Collections.sort(values);

        // sort cards by value
        //////// NEED TO REDO ////////
        
        cards = cardsTemp;
        for (Card card: cards) System.out.println(card.value);
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
