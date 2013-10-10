package fivecardstud;

public class FiveCardStud {

    public static void main(String[] args) {
        Deck deck = new Deck();
        for (Card card: deck.deck) System.out.println(card. value + " " + card.rank + " " + card.suit);
    }
}
