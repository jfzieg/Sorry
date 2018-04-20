package Game;

import java.util.ArrayList;
import java.util.Random;


public class TestDeck {
    public static void main(String[] args) {

        System.out.println("TESTING DECK METHODS...");
        System.out.println();
        // initialize new deck

        ArrayList<Integer> Deck = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Deck.add(1);
        }

        for (int i = 0; i < 4; i++) {
            Deck.add(2);
            Deck.add(3);
            Deck.add(4);
            Deck.add(5);
            Deck.add(7);
            Deck.add(8);
            Deck.add(10);
            Deck.add(11);
            Deck.add(12);
            Deck.add(0);
        }

        System.out.println("INITIALIZE NEW DECK");
        System.out.println(Deck);
        System.out.println();

        // shuffle deck

        Random rng = new Random();
        ArrayList<Integer> ShuffledDeck = new ArrayList<>();
        ArrayList<Integer> rng_tracker = new ArrayList<>();
        while (ShuffledDeck.size() < Deck.size()) {

            int r = rng.nextInt(Deck.size());
            if (!rng_tracker.contains(r)) {
                rng_tracker.add(r);
                ShuffledDeck.add(Deck.get(r));
            }
        }

        System.out.println("SHUFFLED DECK");
        System.out.println(ShuffledDeck);
        System.out.println();

        // draw card
        int card = ShuffledDeck.get(0);
        System.out.println("You drew a/an: " + card);

        // discard
        ShuffledDeck.remove(0);
        System.out.println("Cards remaining in deck:");
        System.out.println(ShuffledDeck);

        // check deck empty
        if (ShuffledDeck.isEmpty()) {System.out.println("DECK EMPTY");}
        else {System.out.println("DECK NOT EMPTY");}
        System.out.println();

        ShuffledDeck.removeAll(ShuffledDeck);
        System.out.println("EMPTY THE DECK");
        System.out.println(ShuffledDeck);
        if (ShuffledDeck.isEmpty()) {System.out.println("DECK EMPTY");}
        else {System.out.println("DECK NOT EMPTY");}


    }

}