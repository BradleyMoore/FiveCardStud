package fivecardstud;

public class Hand {
    Card[] cards;
    int player;
    
    public Hand(Card[] dealt, int player){
        this.cards = deal(Status.handSize, Status.numPlayers);
        this.player = player;
    }
    
    public static Card[] deal(int toDeal, int players){
        Card[] hand;
        hand = new Card[toDeal];

        // check to see if card is already dealt
        int i = 0, j = 0;
        while (j<toDeal){
            // if card already used...iterate
            if(Deck.usedCardIndex.contains(i)){
                i++;
            // if card is unused, use it, add to used list, iterate all
            } else {
                Deck.usedCardIndex.add(i);
                hand[j] = FiveCardStud.deck.deck[i];
                i+=players;
                j++;
            }
        }
        return hand;
    }
}
