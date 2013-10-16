package fivecardstud;

public class Hand {
    Card[] cards;
    int player;
    int rank;
    Card highCard;
    
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
    
    // rank hands from 0 being the worst and up to the best
    public int evaluate(){
        this.highCard = this.cards[0];
        String[] suits;
        int[] values;
        
        suits = new String[Status.handSize];
        values = new int[Status.handSize];
        
        // create array of suits and array of values for comparrisons in ranking
        for (int i=0; i<Status.handSize; i++){
            suits[i] = this.cards[i].suit;
            values[i] = this.cards[i].value;
        }
        
        // find high card in cards
        for (Card card: this.cards) {
            if (card.value > this.highCard.value) this.highCard = card;
        }
        
        return this.rank;
    }
}
