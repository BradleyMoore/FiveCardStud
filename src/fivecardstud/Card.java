package fivecardstud;
        
public class Card {
    String rank, suit;
    int value;
    
    public Card (String rank, String suit){
        this.rank = rank;
        this.suit = suit;
        
        switch (rank){
            case "Jack":
                this.value = 11;
                break;
            case "Queen":
                this.value = 12;
                break;
            case "King":
                this.value = 13;
                break;
            case "Ace":
                this.value = 14;
                break;
            default:
                this.value = Integer.parseInt(rank);
        }
    }
}
