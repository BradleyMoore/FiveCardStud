package fivecardstud;

public class FiveCardStud {
    static Deck deck;    
    static Status status;

    public static void main(String[] args) {
        deck = new Deck();
        deck.shuffle();
        
        status = new Status();

        for (Card card: deck.deck) {
            System.out.println(card.value + " " + card.rank + " " + card.suit);
        }
        Status.showPlayers();
    }
}
