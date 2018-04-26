package Game;

import java.util.ArrayList;
import java.util.Collections;


public class TestMethods {

    public static void testGameMethods(){

        Controller game = new Controller();
        int numPassed = 0, numTests = 4;


        System.out.println("TESTING DECK METHODS...");
        System.out.println();
        // initialize new deck

        ArrayList<Card> sampleDeck = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            sampleDeck.add(new Card(Enums.CardType.ONE));
        }

        for (int i = 0; i < 4; i++) {
            sampleDeck.add(new Card(Enums.CardType.TWO));
            sampleDeck.add(new Card(Enums.CardType.THREE));
            sampleDeck.add(new Card(Enums.CardType.FOUR));
            sampleDeck.add(new Card(Enums.CardType.FIVE));
            sampleDeck.add(new Card(Enums.CardType.SEVEN));
            sampleDeck.add(new Card(Enums.CardType.EIGHT));
            sampleDeck.add(new Card(Enums.CardType.TEN));
            sampleDeck.add(new Card(Enums.CardType.ELEVEN));
            sampleDeck.add(new Card(Enums.CardType.TWELVE));
            sampleDeck.add(new Card(Enums.CardType.SORRY));
        }

        System.out.println("DECK METHODS\n" +
                "===================");

        ArrayList<Card> testDeck = game.initializeFullDeck();

        // Test checking whether method creates correct number of cards
        int numCorrect = 0;
        for(int i = 0; i < sampleDeck.size(); i++){
            if(sampleDeck.get(i).getType() == testDeck.get(i).getType()){
                numCorrect++;
            }
        }
        if(numCorrect == sampleDeck.size()) {
            System.out.println("initaliseNewDeck() PASSED");
            numPassed++;
        }
        else {
            System.out.println("initaliseNewDeck() FAILED");
//            System.out.println(Arrays.toString(testDeck));
        }

        Collections.shuffle(testDeck);
        numCorrect = 0;
        for(int i = 0; i < sampleDeck.size(); i++){
            if(sampleDeck.get(i).getType() != testDeck.get(i).getType()){
                numCorrect++;
            }
        }

        if(numCorrect > sampleDeck.size() / 2){
            System.out.println("shuffleDeck() PASSED");
            numPassed++;
        }
        else System.out.println("shuffleDeck() FAILED");

        Card testCard = game.drawCard();
        if(!testCard.equals(null) && testDeck.size() < sampleDeck.size()) {
            System.out.println("drawCard() PASSED");
            numPassed++;
        }
        else System.out.println("drawCard() FAILED");

        while(testDeck.size() > 0){
            game.drawCard();
        }
        if (testDeck.isEmpty()) {
            System.out.println("isEmpty PASSED");
            numPassed++;
        }
        else {System.out.println("isEmpty FAILED");}

        System.out.format("%d out of %d tests PASSED\n", numPassed, numTests);
    }



    public static void main(String[] args) {
        testGameMethods();
    }
}