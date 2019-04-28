package war;

import java.util.Collections;
import java.util.LinkedList;

public class War {

    protected LinkedList<Card> player1Deck = new LinkedList<>();
    protected LinkedList<Card> player2Deck = new LinkedList<>();
    protected LinkedList<Card> player1WinningDeck = new LinkedList<>();
    protected LinkedList<Card> player2WinningDeck = new LinkedList<>();
    protected LinkedList<Card> deck = new LinkedList<>();

    public War() {
        Deck.createDeck(deck);
        Collections.shuffle(deck);
        for (int i = 0; i < 26; i++) {
            player1Deck.add(deck.get(i));
            player2Deck.add(deck.get(deck.size() - i - 1));
        }
        playWar();
    }

    private void playWar() {
        boolean gameOver = false;
        WarComparator wR = new WarComparator();
        while (!gameOver) {
            display(player1Deck.get(0));
            display(player2Deck.get(0));

            if (wR.compare(player1Deck.get(0), player2Deck.get(0)) > 0) {
                System.out.println("player1 wins that round");
                player1WinningDeck.add(player1Deck.get(0));
                player1WinningDeck.add(player2Deck.get(0));
                player1Deck.remove(0);
                player2Deck.remove(0);
            } else if (wR.compare(player1Deck.get(0), player2Deck.get(0)) < 0) {
                System.out.println("player2 wins that round");
                player2WinningDeck.add(player1Deck.get(0));
                player2WinningDeck.add(player2Deck.get(0));
                player1Deck.remove(0);
                player2Deck.remove(0);
            } else if (wR.compare(player1Deck.get(0), player2Deck.get(0)) == 0) {
                if (war()==true) {
                    gameOver = true;
                    player1Deck.addAll(player1WinningDeck);
                    player2Deck.addAll(player2WinningDeck);

                    if (player1Deck.size() > player2Deck.size()) {
                        System.out.println("player 1 wins because player 2 doesnt have enough cards for the war");
                    } else if (player2Deck.size() > player1Deck.size()) {
                        System.out.println("player 2 wins because player 1 doesnt have enough cards for the war");
                    } else {
                        System.out.println("its a tie");
                    }
                }
            }

            if (player1Deck.isEmpty()) {
                if (player1WinningDeck.isEmpty()) {
                    System.out.println("game is over, player 2 wins");
                    gameOver = true;
                } else if (!player1WinningDeck.isEmpty()) {
                    Collections.shuffle(player1WinningDeck);
                    player1Deck.addAll(player1WinningDeck);
                    player1WinningDeck.clear();
                }
            }
            if (player2Deck.isEmpty() && !gameOver) {
                if (player2WinningDeck.isEmpty()) {
                    System.out.println("game is over, player 1 wins");
                    gameOver = true;
                } else if (!player2WinningDeck.isEmpty()) {
                    Collections.shuffle(player2WinningDeck);
                    player2Deck.addAll(player2WinningDeck);
                    player2WinningDeck.clear();
                }
            }
        }
    }

    private void display(Card c) {
        System.out.println(c.r + " of " + c.s);
    }

    private boolean war() {
        boolean isTie = false;
        WarComparator wR = new WarComparator();
        int i = 0;
        do {
            isTie = false;
            if (player1Deck.size() <= i + 4) {
                if (!player1WinningDeck.isEmpty()) {
                    Collections.shuffle(player1WinningDeck);
                    player1Deck.addAll(player1WinningDeck);
                    player1WinningDeck.clear();
                }
                if (player1Deck.size() <= i + 4) {
                    return true;
                }

            }

            if (player2Deck.size() <= i + 4) {
                if (!player2WinningDeck.isEmpty()) {
                    Collections.shuffle(player2WinningDeck);
                    player2Deck.addAll(player2WinningDeck);
                    player2WinningDeck.clear();
                }
                if (player2Deck.size() <= i + 4) {
                    return true;
                }
            }
            System.out.println("1 card face down");
            System.out.println("2 cards face down");
            System.out.println("3 cards face down");

            display(player1Deck.get(i + 4));
            display(player2Deck.get(i + 4));
            if (wR.compare(player1Deck.get(i + 4), player2Deck.get(i + 4)) > 0) {
                System.out.println("player1 wins that war");
                System.out.println("player 1's face down cards:");
                display(player1Deck.get(i + 1));
                display(player1Deck.get(i + 2));
                display(player1Deck.get(i + 3));
                System.out.println("player 2's face down cards:");
                display(player2Deck.get(i + 1));
                display(player2Deck.get(i + 2));
                display(player2Deck.get(i + 3));
                System.out.println();
                
                for (int j = i; j <= i + 4; j++) {
                    player1WinningDeck.add(player2Deck.get(i));
                    player1WinningDeck.add(player1Deck.get(i));
                    player1Deck.remove(player1Deck.get(i));
                    player2Deck.remove(player2Deck.get(i));
                }
                
            } else if (wR.compare(player1Deck.get(i + 4), player2Deck.get(i + 4)) < 0) {
                System.out.println("player2 wins that war");
                System.out.println("player 1's face down cards:");
                display(player1Deck.get(i + 1));
                display(player1Deck.get(i + 2));
                display(player1Deck.get(i + 3));
                System.out.println("player 2's face down cards:");

                display(player2Deck.get(i + 1));
                display(player2Deck.get(i + 2));
                display(player2Deck.get(i + 3));
                System.out.println();
                
                for (int j = i; j <= i + 4; j++) {
                    player2WinningDeck.add(player1Deck.get(i));
                    player2WinningDeck.add(player2Deck.get(i));
                    player1Deck.remove(player1Deck.get(i));
                    player2Deck.remove(player2Deck.get(i));
                }
                
            } else if (wR.compare(player1Deck.get(i + 4), player2Deck.get(i + 4)) == 0) {
                isTie = true;
                i = i + 5;
            }
        } while (isTie);

        return false;
    }

    public static void main(String[] args) {
        War w = new War();
    }
}
