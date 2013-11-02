package fivecardstud;
import java.util.ArrayList;
import java.util.List;

public class Player {
    int playerNumber;
    List<Card> cards;
    String name;
    Hand hand;
    
    
    public Player(int playerNumber, String name){
        cards = new ArrayList<>();
        this.playerNumber = playerNumber;
        this.name = name;
        this.hand = new Hand();
    }
    
    public void deal(){
        cards = hand.deal();
    }
    
    public void show(){
        System.out.println(this.name);
        for (Card card: cards){
                System.out.println(card.rank + " of " + card.suit);
        }
        System.out.println("");
    }
    
    public static void createAll(){
        // create players based on numPlayers
        for (int i=0; i<Status.numPlayers; i++) {
            FiveCardStud.status.players.add(new Player(i+1, "Player " + Integer.toString(i+1)));
        }
    }
}