package Game;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import sun.awt.image.ImageWatched;

import java.util.Random;

import java.util.ArrayList;


public class Controller {

    boolean game_paused = False;
    boolean game_over = False;
    boolean new_game = True; // if false, game is a resumed game
    private MenuControllers menucontrollers;

    /**
     * Setup for a new game
     * Break this into multiple subfunctions --> how to code for user input from GUI?
     */
    public void SetupNewGame() {
        // get user color --> define GetUserColor(), return type?
        // get opponents and settings for smart/dumb and mean/nice --> define GetOpponents() and GetSettings(opponents)
        // draw initial board setup, oriented correctly, and including pawns and cards
        // initialize and shuffle the deck
    }


    /**
     * Initialize full deck
     * @return deck
     */
    public ArrayList<Card> InitializeFullDeck() {


        ArrayList<Card> Deck = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Deck.add(new Card(Enums.CardType.ONE));
        }

        for (int i = 0; i < 4; i++) {
            Deck.add(new Card(Enums.CardType.TWO));
            Deck.add(new Card(Enums.CardType.THREE));
            Deck.add(new Card(Enums.CardType.FOUR));
            Deck.add(new Card(Enums.CardType.FIVE));
            Deck.add(new Card(Enums.CardType.SEVEN));
            Deck.add(new Card(Enums.CardType.EIGHT));
            Deck.add(new Card(Enums.CardType.TEN));
            Deck.add(new Card(Enums.CardType.ELEVEN));
            Deck.add(new Card(Enums.CardType.TWELVE));
            Deck.add(new Card(Enums.CardType.SORRY));
        }

        return Deck;

    }

    /**
     * Shuffle the deck
     * @param deck
     * @return shuffled deck
     */
    public ArrayList<Card> ShuffleDeck(ArrayList<Card> deck) {
        Random rng = new Random();
        ArrayList<Card> ShuffledDeck = new ArrayList<>();
        ArrayList<int> rng_tracker = new ArrayList<>();
        while (ShuffledDeck.size() < deck.size()) {

            int r = rng.nextInt(deck.size());
            if (!rng_tracker.contains(r)) {
                rng_tracker.add(r);
                ShuffledDeck.add(deck.get(r));
            }
        }

        return ShuffledDeck;
    }


    /**
     * Checks to see if the deck is empty
     * @param deck
     * @return
     */
    public boolean CheckDeckEmpty(ArrayList<Card> deck) {
        if (deck.size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Draw a card (get its value)
     * @param deck
     * @return int value on card
     */
    public int DrawCard(ArrayList<Card> deck) {
        Card card = deck.get(0);
        int card_num = card.getType();
        return card_num;
    }


    /**
     * Discard a card
     * @param deck
     * @return the updated deck
     */
    public ArrayList<Card> Discard(ArrayList<Card> deck) {
        deck.remove(0);
        return deck;
    }

    /**
     * Checks to see if the game is over
     * @param board
     * @return bool
     */
    public boolean CheckGameOver(GameBoard board) {
        boolean GameOver;
        // if all pieces of one color in HOME, GameOver = True
        // else, GameOver = False
        // return GameOver
    }

    /**
     * User takes a turn: draw and discard top card from the deck, choose a piece and move it, update board state, check game over, check deck emtpy
     * @param Deck
     * @return deck after discarding
     */
    public ArrayList<Card> TakeTurn(ArrayList<Card> Deck) {

        int card_num = DrawCard(Deck);
        ArrayList<Card> DeckAfterDiscard = Discard(Deck);

        // What if there are no valid moves?
        GamePiece piece = ChoosePiece();
        MovePiece(piece);

        // update game state and piece locations on board --> we can do this in MovePiece(piece) method

        // check game over
        // if game over --> exit and display end game screens (return an empty ArrayList<Card> ?
        // if game not over --> continue with what is below

        boolean deck_empty = CheckDeckEmpty(Deck);
        if (deck_empty) {
            ArrayList<Card> InitialDeck = InitializeFullDeck();
            ArrayList<Card> ReshuffledDeck = ShuffleDeck(InitialDeck);
            return ReshuffledDeck;
        }
        else {
            return DeckAfterDiscard;
        }

    }


    /**
     * Move piece
     */
    public void MovePiece(GamePiece piece, int num_spaces) {
        // update piece locations, including any pawns bumped as a result of the move
    }

    /**
     * List player's pieces
     * @param color
     * @return ListOfPieces
     */
    public ArrayList<GamePiece> GetPlayersPieces(Enums.Color color) {

        ArrayList<GamePiece> PlayersPieces = new ArrayList<>();
        // make a list (AllPieces) of all the pieces
        // for (GamePiece piece : AllPieces) {
            // if (piece.color == color) {
                // PlayersPieces.add(piece);
            //}
        //}

        return PlayersPieces;
    }

    /**
     * List eligible pieces based on opponent being nice or mean
     * @param PlayerColor
     * @return EligiblePieces
     */
    public ArrayList<GamePiece> GetEligiblePieces(ArrayList<GamePiece> PlayersPieces, boolean Nice, int card_num) {
        ArrayList<GamePiece> EligiblePieces = new ArrayList<>();
        for (GamePiece piece : PlayersPieces) {
            boolean ValidMove = CheckValidMove(piece, card_num);
            if (ValidMove) {
                boolean check_bump = CheckBump(piece, card_num);
                if (Nice && !check_bump) {
                    EligiblePieces.add(piece);
                }
                if (!Nice && check_bump) {
                    EligiblePieces.add(piece);
                }
            }

            if ((Nice) && EligiblePieces.size() == 0) {
                for (piece:
                     PlayersPieces) {
                    if (CheckValidMove(piece, card_num)) {
                        EligiblePieces.add(piece);
                    }
                }
            }
        }
        return EligiblePieces;
    }



    /**
     * Choose piece to move
     */
    public GamePiece ChoosePiece(ArrayList<GamePiece> EligiblePieces, boolean Smart, int card_num) {
        GamePiece ChosenPiece = EligiblePieces.get(0);
        Enums.Color color = ChosenPiece.color;
        if (Smart) {

            // score each possible move
            // select move with highest score

        }
        else { // opponent dumb

            // choose random piece
            Random rng = new Random();
            int r = rng.nextInt(EligiblePieces.size());
            ChosenPiece = EligiblePieces.get(r);

            if (card_num == 1 || card_num == 2 || card_num == 7) {              // move this functionality (deciding which option to do for 1,2,7) to main game loop
                int r2 = rng.nextInt(2);

                if (r2 == 0) {
                    // ChosenPiece.MovePieceForward(card_num)
                }

                else {
                    // move piece from home or split move 3/4
                    if (card_num == 0 || card_num == 1) {
                        // ChosenPiece.MoveFromStart()
                    }
                    else {
                        // ChosenPiece.Split3_4()
                    }
                }
            }
        }

        return ChosenPiece;

    }

    /**
     * Assigns an integer score to a move
     * @param GamePiece piece
     * @return integer score
     */
    public int GetMoveScore(GamePiece piece, int num_spaces) {

    }


    /**
     * DON'T THINK WE NEED THIS METHOD --> can loop through players in main
     * @param current_player
     * @return
     */

    public String NextPlayer(String current_player) {
        ArrayList<String> players = new ArrayList<>();
        players.add("USER");
        players.add("C1");
        players.add("C2");
        players.add("C3");
        int index = players.indexOf(current_player);
        int next_index = (index + 1) % 4;
        String next_player = players.get(next_index);
        return next_player;
    }

}
