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

class HighCardComparator implements Comparator<Player> {
    @Override
    public int compare(Player a, Player b) {
        return a.highCard.value < b.highCard.value ? -1 : a.highCard.value == b.highCard.value ? 0 : 1;
    }
}

class Pair1Comparator implements Comparator<Player> {
    @Override
    public int compare(Player a, Player b) {
        return a.highPairCard1.value < b.highPairCard1.value ? -1 : a.highPairCard1.value == b.highPairCard1.value ? 0 : 1;
    }
}

class Pair2Comparator implements Comparator<Player> {
    @Override
    public int compare(Player a, Player b) {
        return a.highPairCard2.value < b.highPairCard2.value ? -1 : a.highPairCard2.value == b.highPairCard2.value ? 0 : 1;
    }
}

public class Player {
    boolean winner;
    Card highCard;
    Card highPairCard1;
    Card highPairCard2;
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
        rankHand();
        highCard = Ranking.getHighCard(cards);
    }
    
    public void rankHand() {
        cardSets = hand.getSets(cards);
        handRank = hand.rankHand(cardSets);
        handName = hand.nameHand(handRank);
        highPairCard1 = hand.highPairCard1;
        highPairCard2 = hand.highPairCard2;        
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
        return String.format("{rank=%s, highCard=%d, highPairCard1=%d, highPairCard2=%d}", handRank, highCard, highPairCard1, highPairCard2);
    }
}