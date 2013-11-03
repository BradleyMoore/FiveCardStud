package fivecardstud;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    int player;
    
    public List<Card> deal(){
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
        return hand;
    }
    
    void show(List<Card> cards){
        for (Card card: cards) {
            System.out.println(card.rank + " " + card.suit);
        }
    }
}
