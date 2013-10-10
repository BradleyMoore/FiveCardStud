package fivecardstud;

public class Card {
    Card (String number, char suit){
        String cardValue = number;
        char cardSuit = suit;
        int worth;
        
        if (Integer.parseInt(number) >= 2 && Integer.parseInt(number) <= 10){
            worth = Integer.parseInt(number);
        } else {
            switch(cardValue){
                case "A":
                    worth = 14;
                    break;
                case "K":
                    worth = 13;
                    break;
                case "Q":
                    worth = 12;
                    break;
                case "J":
                    worth = 11;
                    break;
            }
        }
        
    }
}
