package fivecardstud;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Status {
    static int handSize;
    static int numPlayers;
    List<Player> players;
    static List<Player> playersByRank;
    Player winner;
    
    public Status() {
        handSize = 5;
        numPlayers = 2;
        players = new ArrayList<>();
        playersByRank = new ArrayList<>();
    }
    
    public Player getWinner() {
        playersByRank = FiveCardStud.status.players;
        Collections.sort(playersByRank, new RankComparator());

        winner = playersByRank.get(playersByRank.size()-1);
        winner.setWinner();

        return winner;
    }
}
