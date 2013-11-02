package fivecardstud;

public class FiveCardStud {
    static Deck deck;    
    static Status status;

    public static void main(String[] args) {
        deck = new Deck();
        deck.shuffle();
        
        status = new Status();
        Player.createAll();

        for (Player player: status.players) player.deal();
        
        for (Player player: status.players) player.show();
    }
}
