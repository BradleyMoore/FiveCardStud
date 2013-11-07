package fivecardstud;

import java.util.Comparator;
import java.util.List;

class RankComparator implements Comparator<Player> {
    @Override
    public int compare(Player a, Player b) {
        return a.hand.rank < b.hand.rank ? 
                -1 : a.hand.rank == b.hand.rank ? 0 : 1;
    }
}

class HighPlayedCardComparator implements Comparator<Player> {
    @Override
    public int compare(Player a, Player b) {
        return a.hand.highPlayedCard.value < b.hand.highPlayedCard.value ? 
                -1 : a.hand.highPlayedCard.value == b.hand.highPlayedCard.value ?
                0 : 1;
    }
}

class SecondHighPlayedCardComparator implements Comparator<Player> {
    @Override
    public int compare(Player a, Player b) {
        return a.hand.secondHighPlayedCard.value < 
                b.hand.secondHighPlayedCard.value ? 
                -1 : a.hand.secondHighPlayedCard.value == 
                b.hand.secondHighPlayedCard.value ?
                0 : 1;
    }
}

class HighCardComparator implements Comparator<Player> {
    @Override
    public int compare(Player a, Player b) {
        return a.hand.highCard.value < b.hand.highCard.value ? 
                -1 : a.hand.highCard.value == b.hand.highCard.value ? 0 : 1;
    }
}

public class Player {
    boolean winner;
    int number;
    List<Card> cards;
    String name;
    Hand hand;
    
    public Player(int playerNumber, String name){
        winner = false;
        this.number = playerNumber;
        this.name = name;
        this.hand = new Hand();
    }
    
    public void setWinner() {
        winner = true;
    }

    public void show(){
        System.out.println(this.name);
        for (Card card: hand.cards){
                System.out.println(card.rank + " of " + card.suit);
        }
        System.out.println(hand.rank + " " + hand.name);
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
        return String.format("{rank=%s, highCard=%d, highPlayedCard=%d, "
                + "secondHighPlayedCard=%d}", 
                hand.rank, hand.highCard, hand.highPlayedCard, 
                hand.secondHighPlayedCard);
    }
}