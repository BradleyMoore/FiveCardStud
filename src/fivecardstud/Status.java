package fivecardstud;
import java.util.ArrayList;
import java.util.List;

public class Status {
    static int handSize;
    static int numPlayers;
    static int deckSize;
    List<Player> players;
    static Player winner;
    
    public Status() {
        handSize = 5;
        numPlayers = 2;
        deckSize = FiveCardStud.deck.cards.size();
        players = new ArrayList<>();
    }
    
    static public void getWinner(){
        winner = Ranking.getWinner(FiveCardStud.status.players);
    }
}
