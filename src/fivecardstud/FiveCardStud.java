package fivecardstud;

public class FiveCardStud {
    static Deck deck;    
    static Status status;

    public static void main(String[] args) {
        deck = new Deck();
        deck.shuffle();
        
        status = new Status();
        Player.createAll();

        // test print section
        for (Player player: Status.players) player.show();
        deck.show();
    }
}
