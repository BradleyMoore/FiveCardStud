package fivecardstud;
import java.util.ArrayList;
import java.util.List;

public class Status {
    static int handSize;
    static int numPlayers;
    List<Player> players;
    static List<Player> playersByRank;
    static Player winner;
    
    public Status() {
        handSize = 5;
        numPlayers = 2;
        players = new ArrayList<>();
        playersByRank = new ArrayList<>();
    }
    
    static public void getWinner(){
        winner = Ranking.getWinner();
    }
}
