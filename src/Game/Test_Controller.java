package Game;
import java.util.ArrayList;

public class Test_Controller {

    // testing gameplay
    static void TestGamePlay() {

        System.out.println("Demonstrating gameplay logic");
        System.out.println();
        System.out.println("Board is empty at the beginning of the game...");

        Controller c = new Controller();

        // set up a new game
        c.setupNewGame(Enums.Color.BLUE, new GamePiece(Enums.Color.YELLOW, true, true), new GamePiece(Enums.Color.RED, false, false));

        // print board
        GameBoard b = c.getBoard();

        System.out.println();

        // Show that the board is empty before any player takes a turn
        for (GamePiece[] pieces : b.getTileList()) {
            for (GamePiece piece : pieces) {
                System.out.print(piece + " ");
            }
        }
        System.out.println();

        System.out.println();
        System.out.println("Shuffled Deck");
        // initialize deck
        ArrayList<Card> deck = c.initializeFullDeck();
        System.out.println(deck);

        System.out.println();
        // check deck empty
        boolean gameover = c.checkGameOver();
        System.out.println("Gameover: " + gameover);

        System.out.println();
        System.out.println("YELLOW draws a card");
        // draw card
        Card card = c.drawCard();
        System.out.println(2);
        System.out.println();

        // get yellow pieces
        ArrayList<GamePiece> yellowPieces = c.getPlayersPieces(Enums.Color.YELLOW);
        System.out.println("YELLOW'S PIECES");
        System.out.println(yellowPieces.size());
        System.out.println();

        // get eligible pieces
        System.out.println("ELIGIBLE PIECES");
        ArrayList<GamePiece> eligiblePieces = c.getEligiblePieces(yellowPieces, 2);
        System.out.println(eligiblePieces.size());
        System.out.println();

        // move piece
        System.out.println("MOVE PIECE");
        c.ChoosePiece(eligiblePieces, 2);
        System.out.println();

        // get board again...


        // print board
        b = c.getBoard();

        System.out.println("Board after move");

        // Show that the board has piece after player takes a turn
        for (GamePiece[] pieces : b.getTileList()) {
            for (GamePiece piece : pieces) {
                System.out.print(piece + " ");
            }
        }
        System.out.println();
        System.out.println();

        // check game over
        System.out.println("Check game over");
        gameover = c.checkGameOver();
        System.out.println(gameover);
        System.out.println();

        // RED takes a turn
        //c.takeTurn(Enums.Color.RED);

        System.out.println();
        System.out.println("YELLOW draws a card");
        // draw card
        card = c.drawCard();
        System.out.println(card.getType());
        System.out.println();

        // get yellow pieces
        yellowPieces = c.getPlayersPieces(Enums.Color.YELLOW);
        System.out.println("YELLOW'S PIECES");
        System.out.println(yellowPieces.size());
        System.out.println();

        // get eligible pieces
        System.out.println("YELLOW'S ELIGIBLE PIECES");
        eligiblePieces = c.getEligiblePieces(yellowPieces, card.getType());
        System.out.println(eligiblePieces.size());
        System.out.println();

        // move piece
        System.out.println("MOVE PIECE");
        c.ChoosePiece(eligiblePieces, card.getType());
        System.out.println();

        // get board again...


        // print board
        b = c.getBoard();

        System.out.println("Board after move");

        // Show that the board has piece after player takes a turn
        for (GamePiece[] pieces : b.getTileList()) {
            for (GamePiece piece : pieces) {
                System.out.print(piece + " ");
            }
        }

    }

    public static void main(String[] args) {
        TestGamePlay();
    }


}
