package fivecardstud;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class RankComparator implements Comparator<Player> {
    @Override
    public int compare(Player a, Player b) {
        return a.handRank < b.handRank ? -1 : a.handRank == b.handRank ? 0 : 1;
    }
}

public class Player {
    boolean winner;
    int playerNumber;
    int handRank;
    List<List<Card>> cardSets;
    List<Card> cards;
    String handName;
    String playerName;
    Hand hand;
    
    public Player(int playerNumber, String name){
        winner = false;
        cards = new ArrayList<>();
        this.playerNumber = playerNumber;
        this.playerName = name;
        this.hand = new Hand();
        this.handRank = 0;
    }
    
    public void setWinner() {
        winner = true;
    }
    
    public void deal(){
        cards = hand.deal();
        cardSets = getSets(cards);
        rankHand();
    }
    
    public void rankHand() {
        handRank = hand.rankHand(cardSets);
        handName = hand.nameHand(handRank);
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
    
    @Override
    public String toString() {
        return String.format("{rank=%s}", handRank);
    }
}