package fivecardstud;
import java.util.ArrayList;
import java.util.List;

public class Player {
    int playerNumber;
    int handRank;
    List<List<Card>> cardSets;
    List<Card> cards;
    String handName;
    String playerName;
    Hand hand;
    
    public Player(int playerNumber, String name){
        cards = new ArrayList<>();
        this.playerNumber = playerNumber;
        this.playerName = name;
        this.hand = new Hand();
    }
    
    public void deal(){
        cards = hand.deal();
        cardSets = getSets(cards);
        handRank = rankHand(cardSets);
        handName = nameHand(handRank);
    }
    
    public List<List<Card>> getSets(List<Card> cards) {
        List<List<Card>> pairSets;
        List<Card> pairSet1;
        List<Card> pairSet2;
        List<Card> straitSet;
        List<Card> flushSet;
        List<List<Card>> sets;
        
        sets = new ArrayList<>();
        
        pairSets = Ranking.checkPairs(cards);
        straitSet = Ranking.checkStrait(cards);
        flushSet = Ranking.checkFlush(cards);

        pairSet1 = pairSets.get(0);
        pairSet2 = pairSets.get(1);        
        
        sets.add(pairSet1);
        sets.add(pairSet2);
        sets.add(straitSet);
        sets.add(flushSet);
        
        return sets;
    }
    
    public int rankHand(List<List<Card>> sets) {
        int rank;
        rank = Ranking.rankHand(sets);
        return rank;
    }
    
    public String nameHand(int rank) {
        String name;
        name = Ranking.nameHand(rank);
        return name;
    }
    
    public void show(){
        System.out.println(this.playerName);
        for (Card card: cards){
                System.out.println(card.rank + " of " + card.suit);
        }
        System.out.println(handRank + " " + handName);
        System.out.println("");
    }
    
    public static void createAll(){
        // create players based on numPlayers
        for (int i=0; i<Status.numPlayers; i++) {
            FiveCardStud.status.players.add(new Player(i+1, "Player " + Integer.toString(i+1)));
        }
    }
}