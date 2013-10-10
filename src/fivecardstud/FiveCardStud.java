package fivecardstud;

public class FiveCardStud {

    public static void main(String[] args) {
        Deck deck = new Deck();
        for (int i=0; i<deck.DECK_SIZE; i++){
            System.out.println((deck.deck[i].rank + " " deck.deck[i].suit));
        }
    }
}
