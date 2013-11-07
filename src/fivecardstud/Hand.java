package fivecardstud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    Card highCard;
    Card highPlayedCard;
    Card secondHighPlayedCard;
    List<Card> cards;
    List<Card> topCards;
    String name;
    int player;
    int rank;
    
    public void deal(){
        List<Card> hand;
        List<List<Card>> sets;
        hand = new ArrayList();

        for (Card card: FiveCardStud.deck.cards){
             // check to see if card is already dealt
            if(FiveCardStud.deck.usedCardIndex.contains(card)){
                continue;
            // if card is unused, use it add to usedCardIndex
            } else {
                FiveCardStud.deck.usedCardIndex.add(card);
                hand.add(card);
            }
            if (hand.size() >= Status.handSize) break;
        }
        
        Collections.sort(hand, new ValueComparator());
        cards = hand;
        
        sets = getSets(cards);
        rank = rankHand(sets);
        getTopCards(sets);
        name = nameHand(rank);
    }
    
    public int rankHand(List<List<Card>> sets) {
        rank = Ranking.rankHand(sets);
        return rank;
    }
    
    public void getTopCards(List<List<Card>> sets) {
        topCards = Ranking.getTopCards(sets);
        highPlayedCard = topCards.get(0);
        secondHighPlayedCard = topCards.get(1);
        
        highCard = Ranking.getHighCard(cards);
    }
    
    public String nameHand(int rank) {
        name = Ranking.nameHand(rank);
        return name;
    }
    
    public List<List<Card>> getSets(List<Card> cards) {
        List<Card> flushSet;
        List<Card> pairSet1;
        List<Card> pairSet2;
        List<Card> straitSet;
        List<List<Card>> pairSets;
        List<List<Card>> sets;
        
        sets = new ArrayList<>();
        
        pairSets = Ranking.checkPairs(cards);
        straitSet = Ranking.checkStrait(cards);
        flushSet = Ranking.checkFlush(cards);

        pairSet1 = pairSets.get(0);
        pairSet2 = pairSets.get(1);        
        
        if (flushSet != null) sets.add(flushSet);
        if (straitSet != null) sets.add(straitSet);        
        if (pairSet1 != null) sets.add(pairSet1);        
        if (pairSet2 != null) sets.add(pairSet2);

        if (sets.size() < 4) {
            for (int i=0; i<4; i++) {
                sets.add(null);
            }
        }
        
        return sets;
    }
    
}
