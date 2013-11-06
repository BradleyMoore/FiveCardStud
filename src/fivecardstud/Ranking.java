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
        Collections.sort(cards, new SuitComparator());
        Collections.sort(cards, new ValueComparator());
        boolean foundPair;
        boolean nextList;
        boolean skipIter;
        boolean thisList;
        Card temp;
        List<Card> matches1;
        List<Card> matches2;
        List<List<Card>> pairs;
        
        foundPair = false;
        nextList = false;
        skipIter = true;
        thisList = false;
        temp = cards.get(0);
        matches1 = new ArrayList<>();
        matches2 = new ArrayList<>();
        pairs = new ArrayList<>();
        
        matches1.clear();
        matches2.clear();
        
        for (Card card: cards) {
            // skip first iteration
            if (skipIter == true) {
                skipIter = false;
                continue;
            }
            
            if (temp.value != card.value) {
                foundPair = false;
            // if matches1 HAS been used keep adding pairs to matches 2
            } else if (foundPair == false && nextList == true) {
                if (thisList == false) matches2.add(temp);
                matches2.add(card);
                thisList = true;
            // if matches2 HAS NOT been used keep adding pairs to matches1
            } else if (matches2.isEmpty()) {
                if (foundPair == false) matches1.add(temp);
                matches1.add(card);
                foundPair = true;
                nextList = true;
            }
            temp = card;
        }
        
        // add non-empty lists to returned list of lists
        if (matches1.size() > 0) {
            pairs.add(matches1);
        } else {
            pairs.add(null);
        }
        
        if (matches2.size() > 0) {
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
        if (pairSet1 != null) {
            switch (pairSet1.size())  {
                // 4 of a kind
                case 4:
                    rank = 8;
                    break;
                // 3 of a kind
                case 3:
                    rank = 4;
                    // full house
                    if (pairSet2 != null) rank = 7;
                    break;
                case 2:
                    // 1 pair
                    rank = 2;
                    // 2 pair
                    if (pairSet2 != null) rank = 3;;
                    break;                    
            }
        }
        
        // strait
        if (rank < 5 && straitSet != null) {
            rank = 5;
        }
        
        // flush
        if (rank <= 5 && flushSet != null) {
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
    
    static public Player getWinner() {
        List<Player> playersByRank;
        Player winner;
        
        playersByRank = FiveCardStud.status.players;
        Collections.sort(playersByRank, new RankComparator());

        winner = playersByRank.get(playersByRank.size()-1);
        
        winner.setWinner();

        return winner;
    }
}