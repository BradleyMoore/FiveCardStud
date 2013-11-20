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
            } else if (temp.value == card.value) {
                if (topMatches.isEmpty()) topMatches.add(temp);
                topMatches.add(card);
            } else {
                // if the first found pair is over
                if (!topMatches.isEmpty()) break;
            }
            System.out.println(skipIter);
            temp = card;
        }
        
        // find second set of matches
        temp = cards.get(0);
        for (Card card: cards) {
            // skip first iteration
            if (skipIter) {
                skipIter = false;
                continue;
            // check to see if this would duplicate topMatches
            } else if (!topMatches.contains(card)) {
                if (temp.value == card.value) {
                    if (bottomMatches.isEmpty()) bottomMatches.add(temp);
                    bottomMatches.add(card);
                } else {
                    // if second found pair is over
                    if (!bottomMatches.isEmpty()) break;
                }
            }
            temp = card;
        }
        
        // if bottomMatches is better than topMatches
        if (topMatches.size() <= bottomMatches.size()) {
            tempList = topMatches;
            topMatches = bottomMatches;
            bottomMatches = tempList;
        }

        // add non-empty lists to returned list of lists
        pairs.add(null);
        pairs.add(null);
        if (topMatches.size() > 0) {
            pairs.set(0, topMatches);
            if (bottomMatches.size() > 0) {
                pairs.set(1, bottomMatches);
            }
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

        /* 
         * rank 9 = strait flush
         * rank 8 = four of a kind
         * rank 7 = full house
         * rank 6 = flush
         * rank 5 = strait
         * rank 4 = three of a kind
         * rank 3 = two pair
         * rank 2 = one pair
         * rank 1 = high card
         */
        
        // rank matched sets
        if (pairSet1.size() > 0) {
            for (Card card: pairSet1) System.out.println(card.rank);
            System.out.println("\n");
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
                    if (pairSet2 != null) {
                        // 2 pair
                        if (pairSet2.size() == 2) {
                            rank = 3;
                        // full house
                        } else if (pairSet2.size() == 3) rank = 7;
                        break;
                    }
            }
        }
        
        // strait
        if (rank < 5 && straitSet != null) {
            rank = 5;
        }
        
        // flush
        if (rank <= 5 && flushSet != null) {
            // if a flush is made then strait flush
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
    
    static List<Card> getTopCards(List<List<Card>> sets) {
        Card topA;
        Card topB;
        Card top;
        List<Card> tops;
        List<Card> setA;
        List<Card> setB;
        
        setA = sets.get(0);
        setB = sets.get(1);
        topA = null;
        topB = null;
        tops = new ArrayList<>();
        
        top = null;
        if (setA != null) {
            topA = setA.get(setA.size()-1);
            top = topA;
            
            if (setB != null) {
                topB = setB.get(setB.size()-1);
            }
        }
        
        tops.add(topA);
        tops.add(topB);
        
        return tops;
    }
    
    static public Player getWinner(List<Player> players) {
        List<Player> shortList;
        List<Player> possibleWinners;
        Player winner;
        
        possibleWinners = new ArrayList<>();
        shortList = new ArrayList<>();
        
        Collections.sort(players, new RankComparator());
        winner = players.get(players.size()-1);
        for (Player player: players) {
            if (player.hand.rank == winner.hand.rank) possibleWinners.add(player);
        }
        
        if (possibleWinners.size() > 1) {
            Collections.sort(possibleWinners, new HighPlayedCardComparator());
            winner = possibleWinners.get(possibleWinners.size()-1);
            shortList.add(winner);
            for (Player player: possibleWinners) {
                if (player.hand.highPlayedCard.value > winner.hand.highPlayedCard.value) {
                    winner = player;
                    shortList.add(player);
                }
            }
        }
            
        if (shortList.size() > 1) {
            Collections.sort(shortList, new SecondHighPlayedCardComparator());
            winner = shortList.get(0);
            possibleWinners.clear();
            for (Player player: shortList) {
                if (player.hand.secondHighPlayedCard.value > 
                        winner.hand.secondHighPlayedCard.value) {
                    winner = player;
                    possibleWinners.add(player);
                }
            }
        }
        
        if (possibleWinners.size() > 1) {
            Collections.sort(possibleWinners, new HighCardComparator());
            winner =  possibleWinners.get(possibleWinners.size()-1);
        }

        winner.setWinner();

        return winner;
    }
}
