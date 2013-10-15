package fivecardstud;

public class Status {
    int numPlayers;
    Player[] players;
    
    public Status() {
        this.numPlayers = 2;
        this.players = new Player[numPlayers];
        
        for (int i=0; i<numPlayers; i++) {
            this.players[i] = new Player(i+1, "Player " + Integer.toString(i+1));
        }
    }
    
    public void showPlayers() {
        for (int i=0; i<numPlayers; i++){
            System.out.println(this.players[i].name + " " + this.players[i].num);
        }
    }
}
