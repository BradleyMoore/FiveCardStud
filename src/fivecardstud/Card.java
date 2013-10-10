package fivecardstud;
import java.util.HashMap;
        
public class Card {
    Card (String value, String suit){
        
        HashMap worth = new HashMap<String, Integer>();
        for (int i=2; i<=10; i++){
            worth.put(new Integer(i).toString(), i);
        }
        worth.put("Jack", 10);
        worth.put("Queen", 11);
        worth.put("King", 12);
        worth.put("Ace", 13);
        
    }
}
