package fivecardstud;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ranking {

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
        Collections.sort(cards, new ValueComparator());

        boolean skipIter;
        Card temp;
        
        skipIter = true;
        temp = cards.get(0);
        
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

    static List<List<Card>> checkPairs(List<Card> cards) {
        Collections.sort(cards, new ValueComparator());
        
        boolean skipIter;
        boolean foundPair;
        Card temp;
        List<Card> matches1;
        List<Card> matches2;
        List<List<Card>> pairs;
        
        foundPair = false;
        skipIter = true;
        temp = cards.get(0);
        matches1 = new ArrayList<>();
        matches2 = new ArrayList<>();
        pairs = new ArrayList<>();
        
        for (Card card: cards) {
            // skip first iteration
            if (skipIter == true) {
                skipIter = false;
                continue;
            }
            
            // if matches2 HAS NOT been used keep adding pairs to matches1
            if (matches2.isEmpty() && temp.value == card.value) {
                if (temp == cards.get(0)) matches1.add(temp);
                matches1.add(card);
                foundPair = true;
            // if matches1 HAS been used keep adding pairs to matches 2
            } else if (foundPair == false && !matches1.isEmpty() && temp.value == card.value) {
                matches2.add(card);
            } else {
                foundPair = false;
            }
            temp = card;
        }
        
        // add non-empty lists to returned list of lists
        if (!matches1.isEmpty()) {
            pairs.add(matches1);
        } else {
            pairs.add(null);
        }
        
        if (!matches2.isEmpty()) {
            pairs.add(matches2);
        } else {
            pairs.add(null);
        }
        
        return pairs;
    }
    
    static int rankHand(List<List<Card>> sets) {
        int rank;
        List<Card> pairSet1;
        List<Card> pairSet2;
        List<Card> straitSet;
        List<Card> flushSet;
        
        rank = 1;
        pairSet1 = sets.get(0);
        pairSet2 = sets.get(1);
        straitSet = sets.get(2);
        flushSet = sets.get(3);
        
        // rank matched sets
        if (!pairSet1.isEmpty()) {
            switch (pairSet1.size())  {
                case 4:
                    rank = 8;
                    break;
                case 3:
                    rank = 4;
                    if (!pairSet2.isEmpty()) rank = 7;
                    break;
                case 2:
                    rank = 2;
                    if (!pairSet2.isEmpty()) rank = 3;;
                    break;                    
            }
        }
        
        // rank straits
        if (rank < 5 && straitSet != null) {
            rank = 5;
        }
        
        // rank flushes
        if (rank < 5 && flushSet != null) {
            if (rank == 5) {
                rank = 9;
            } else {
                rank = 6;
            }
        }
        
    return rank;
    }
    
    static String nameHand(int rank) {
        String name;
        
            switch (rank) {
                case 9:
                    name = "Strait Flush";
                    break;
                case 8:
                    name = "Four of a Kind";
                    break;
                case 7:
                    name = "Full House";
                    break;
                case 6:
                    name = "Flush";
                    break;
                case 5:
                    name = "Strait";
                    break;
                case 4:
                    name = "Three of a Kind";
                    break;
                case 3:
                    name = "Two Pair";
                    break;
                case 2:
                    name = "One Pair";
                    break;
                default:
                    name = "High Card";
                    break;
            }
        
        return name;
    }
}
