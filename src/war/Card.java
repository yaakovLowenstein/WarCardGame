
package war;


public class Card {

    public enum suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

    public enum rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT,
        NINE, TEN, JACK, QUEEN, KING, ACE
    }

    protected suit s;
    protected rank r;

    public Card(suit s, rank r) {
        this.s = s;
        this.r = r;
    }
}
