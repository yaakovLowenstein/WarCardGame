/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package war;

import java.util.List;
import war.Card.rank;
import war.Card.suit;

/**
 *
 * @author yaakov
 */
public class Deck {
    protected static Card c;
    protected static void createDeck(List deck) {
        for (suit s : suit.values()) {
            for (rank r : rank.values()) {
                c = new Card(s, r);
                deck.add(c);
            }
        }
        
    }
}
