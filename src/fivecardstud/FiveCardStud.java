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

        for (Player player: status.players) player.deal();
        
        for (Player player: status.players) player.show();
        
        status.getWinner();
        System.out.println("");
        System.out.println("The winner is " + status.winner.playerName);
    }
}
