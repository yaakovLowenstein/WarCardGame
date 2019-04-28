
package war;

import java.util.Comparator;


public class WarComparator implements Comparator<Card> {

    @Override
    public  int  compare(Card c1, Card c2) {
         int diff = c1.r.compareTo(c2.r);
         return diff;
    }
    
}
