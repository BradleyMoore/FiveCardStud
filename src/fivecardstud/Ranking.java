package fivecardstud;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ranking {

    static Card getHighCard(List<Card> cards){
        Card highCard;
        
        Collections.sort(cards, new ValueComparator());
        highCard = cards.get(cards.size()-1);
        
        return highCard;
    }
 
    static List<Card> checkFlush(List<Card> cards){
        Card temp;
        temp = cards.get(0);
        
        for (Card card: cards) {
            if (!card.suit.equals(temp.suit)) {
                cards = null;
                break;
            }
        }
        return cards;
    }
    
    static List<Card> checkStrait(List<Card> cards) {
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
                cards = null;
                break;
            }
        }
        return cards;
    }

    static List<List<Card>> checkPairs(List<Card> cards) {
        Collections.sort(cards, new ValueComparator());
        boolean onPair1;
        boolean skipIter;
        boolean onPair2;
        Card temp;
        List<Card> topMatches;
        List<Card> bottomMatches;
        List<Card> tempList;
        List<List<Card>> pairs;
        
        onPair1 = false;
        onPair2 = false;
        skipIter = true;
        bottomMatches = new ArrayList<>();
        topMatches = new ArrayList<>();
        pairs = new ArrayList<>();

        Collections.sort(cards, new ValueComparator());
        // find first set of matches
        temp = cards.get(0);
        for (Card card: cards) {
            // skip first iteration
            if (skipIter) {
                skipIter = false;
                continue;
            }
            
            if (temp.value != card.value) {
                // if the first found pair is over
                if (!topMatches.isEmpty()) break;
            } else {
                topMatches.add(card);
            }
        }
        
        // find second set of matches
        temp = cards.get(0);
        for (Card card: cards) {
            // skip first iteration
            if (skipIter) {
                skipIter = false;
                continue;
            }
            
            // if cards are not already paired
            if (!topMatches.contains(card)) {
                if (temp.value != card.value) {
                    // if second found pair is over
                    if (!bottomMatches.isEmpty()) break;
                } else {
                    bottomMatches.add(card);
                }
            }
        }
        
        // if bottomMatches was used switch bottomMatches and topMatches
        if (!bottomMatches.isEmpty()) {
            tempList = topMatches;
            topMatches = bottomMatches;
            bottomMatches = tempList;
        }

        // add non-empty lists to returned list of lists
        pairs.add(null);
        pairs.add(null);
        if (topMatches.size() > 0) {
            pairs.set(0, topMatches);
            if (bottomMatches.size() > 0) pairs.set(1, bottomMatches);
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
    
    static Card getTopCard(List<Card> a, List<Card> b) {
        Card topA;
        Card topB;
        Card top;
        
        top = null;
        if (a != null) {
            topA = a.get(0);
            top = topA;
            
            if (b != null) {
                topB = b.get(0);
                
                if (topB.value > topA.value) top = topB;
            }
        }
        
        return top;
    }
    
    static public Player getWinner() {
        List<Player> playersByRank;
        List<Player> possibleWinners;
        Player winner;
        
        possibleWinners = new ArrayList<>();
        playersByRank = FiveCardStud.status.players;
        Collections.sort(playersByRank, new RankComparator());

        winner = playersByRank.get(playersByRank.size()-1);
        
        for (Player player: playersByRank) {
            if (player.handRank == winner.handRank) possibleWinners.add(player);
        }
        
        Collections.sort(possibleWinners, new HighCardComparator());
        
        if (possibleWinners.size() > 1) {
            Collections.sort(possibleWinners, new Pair1Comparator());
        }
        
        winner = possibleWinners.get(possibleWinners.size()-1);
        winner.setWinner();

        return winner;
    }
}