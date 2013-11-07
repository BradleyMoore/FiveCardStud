package fivecardstud;

public class FiveCardStud {
    static Deck deck;    
    static Player winner;
    static Status status;

    public static void main(String[] args) {
        deck = new Deck();
        deck.shuffle();
        
        status = new Status();
        Player.createAll();

        for (Player player: status.players) player.hand.deal();
        
        for (Player player: status.players) player.show();
        
        Status.getWinner();
        System.out.println("");
        System.out.println("The winner is " + Status.winner.name);
    }
}
