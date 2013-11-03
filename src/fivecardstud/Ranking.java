package fivecardstud;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ranking {
    
    public Ranking() {
}

    static Card getHighCard(List<Card> cards){
        Card highCard;
        highCard = cards.get(0);
        for (Card card: cards) {
            if (card.value > highCard.value) highCard = card;
        }
        return highCard;
    }
 
    static List<Card> checkFlush(List<Card> cards){
        Card temp;
        temp = cards.get(0);
        
        for (Card card: cards) {
            if (!card.suit.equals(temp.suit)) {
                return null;
            }
        }
        return cards;
    }
    
    static List<Card> checkStrait(List<Card> cards){
        boolean skipIter;
        Card temp;
        skipIter = true;
        temp = cards.get(0);
        
        Collections.sort(cards, new ValueComparator());
        
        for (Card card: cards) {
            // skip first iteration
            if (skipIter == true) {
                skipIter = false;
                continue;
            }

            if (card.value - temp.value != 1) {
                return null;
            }
        }
        return cards;
    }

    
    
    
    
    // rank hands from 0 being the worst and up to the best
    public int evaluate(List<Card> cards){

        return this.rank;
    }
}
