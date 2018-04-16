package Game;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import sun.awt.image.ImageWatched;

import java.lang.reflect.Array;
import java.util.Random;

import java.util.ArrayList;
import java.util.Collections;


public class Controller {

    boolean game_paused = false;
    boolean game_over = false;
    boolean new_game = true; // if false, game is a resumed game
    //private MenuControllers menucontrollers;

    /**
     * Setup for a new game
     * Break this into multiple subfunctions --> how to code for user input from GUI?
     */
    public void SetupNewGame() {
        // initialize the gameboard
        GameBoard board = new GameBoard()
        GamePiece[][] tileList = new GamePiece[4][16];
        GamePiece[][] homeList = new GamePiece[4][6];
        Card card;
        // get user color
        // get opponents and settings for smart/dumb and mean/nice --> define GetOpponents() and GetSettings(opponents)
        // draw initial board setup, oriented correctly, and including pawns and cards
        // initialize and shuffle the deck
        // make a list of all pieces in the game --> return this

    }

    /**
     *
     * Gets all pieces in the game
     */
    public ArrayList<GamePiece> GetAllPieces() {
        ArrayList<GamePiece> AllPieces = new ArrayList<>();
        for (int i = 0; i < 4; ++) {
            // what if there are fewer than three opponents?
            AllPieces.add(new GamePiece(Enums.Color.BLUE));
            AllPieces.add(new GamePiece(Enums.Color.GREEN));
            AllPieces.add(new GamePiece(Enums.Color.YELLOW));
            AllPieces.add(new GamePiece(Enums.Color.RED));
        }

        return AllPieces;

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
    public boolean CheckGameOver(GameBoard board, GamePiece[][] homeList, int boardSide) {
        boolean GameOver;
        int numPieces = 0;
        for (GamePiece piece : homeList[boardSide]) {
                numPieces += 1;
        }
        if (numPieces == 4) {
            GameOver = true;
        }
        else {
            GameOver = false;
        }
        return GameOver;
    }

    /**
     * User takes a turn: draw and discard top card from the deck, choose a piece and move it, update board state, check game over, check deck emtpy
     * @param Deck
     * @return deck after discarding
     */
    public ArrayList<Card> TakeTurn(ArrayList<Card> Deck, GameBoard board, ArrayList<GamePiece> AllPieces, Enums.Color color, GamePiece[][] tileList, Card card, GamePiece[][] homeList, int boardSide) {

        // draw a card, get its value, discard it (update the deck)
        int card_num = DrawCard(Deck);
        ArrayList<Card> DeckAfterDiscard = Discard(Deck);

        // get int of board side
        int boardside = color.getSide();

        // initialize an arraylist to store the current player's pieces
        ArrayList<GamePiece> PlayersPieces = new ArrayList<>();

        // add pieces to this list
        for (GamePiece piece: AllPieces) {
            if (piece.getColor() == boardside) {
                PlayersPieces.add(piece);
            }
        }

        // get the nice/mean and smart/dumb settings of the player
        boolean mean = PlayersPieces.get(0).isMean();
        boolean smart = PlayersPieces.get(0).isSmart();

        // What if there are no valid moves?

        ArrayList<GamePiece> EligiblePieces = GetEligiblePieces(PlayersPieces, mean, card_num, board);

        GamePiece piece = ChoosePiece(EligiblePieces, smart, card_num, board, tileList, card, homeList, color);
        board.movePiece(tileList, homeList, piece, card);

        // update game state and piece locations on board --> is this done in move method?

        // check game over
        boolean gameover = CheckGameOver(board, homeList, boardSide)

        if (!gameover) {
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
        else {
            // exit and display end game screens (return an empty ArrayList<Card> ?
        }

    }


    ///**
     //* Move piece
     //*/
    //public void MovePiece(GamePiece piece, int num_spaces) {
        // update piece locations, including any pawns bumped as a result of the move
    //}

    /**
     * List player's pieces
     * @param color
     * @return ListOfPieces
     */
    public ArrayList<GamePiece> GetPlayersPieces(Enums.Color color) {

        ArrayList<GamePiece> PlayersPieces = new ArrayList<>();
        ArrayList<GamePiece> AllPieces = GetAllPieces();
        // make a list (AllPieces) of all the pieces
        for (GamePiece piece : AllPieces) {
            if (piece.getColor() == color.getSide())) {
                PlayersPieces.add(piece);
            }
        }

        return PlayersPieces;
    }

    /**
     * List eligible pieces based on opponent being nice or mean
     * @param
     * @return EligiblePieces
     */
    public ArrayList<GamePiece> GetEligiblePieces(ArrayList<GamePiece> PlayersPieces, boolean Mean, int card_num, GameBoard board) {
        ArrayList<GamePiece> EligiblePieces = new ArrayList<>();
        for (GamePiece piece : PlayersPieces) {
            boolean ValidMove = board.CheckValidMove(piece, card_num);
            if (ValidMove) {
                boolean check_bump = CheckBump(piece, card_num);
                if (!Mean && !check_bump) {
                    EligiblePieces.add(piece);
                }
                if (Mean && check_bump) {
                    EligiblePieces.add(piece);
                }
            }

            if ((!Mean) && EligiblePieces.size() == 0) {
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
     * Choose piece to move and move it
     */
    public GamePiece ChoosePiece(ArrayList<GamePiece> EligiblePieces, boolean Smart, int card_num, GameBoard board, GamePiece[][] tileList, Card card, GamePiece[][] homeList, Enums.Color color) {
        GamePiece ChosenPiece= EligiblePieces.get(0);
        //int color_id_ = ChosenPiece.getColor();

        if (Smart) {
            // score each possible move
            ArrayList<Integer> ScoreList = new ArrayList<>();

            for (GamePiece EligiblePiece : EligiblePieces) {
                int score = GetMoveScore(board, EligiblePiece, card_num, tileList, card, homeList, color);
                ScoreList.add(score);
            }

            // select move with highest score
            int max_score = Collections.min(ScoreList);
            int index_of_max_score = ScoreList.indexOf(max_score);
            ChosenPiece = EligiblePieces.get(index_of_max_score);

            // move piece
            board.movePiece(tileList, homeList, ChosenPiece,card);


        }
        else { // opponent dumb

            // choose random piece
            Random rng = new Random();
            int r = rng.nextInt(EligiblePieces.size());
            ChosenPiece = EligiblePieces.get(r);

            if (card_num == 1 || card_num == 2 || card_num == 7) {
                int r2 = rng.nextInt(2);

                if (r2 == 0) {
                    board.movePiece(tileList, homeList, ChosenPiece, card);
                }

                else {
                    // move piece from home or split move 3/4
                    if (card_num == 0 || card_num == 1) {
                        board.homeGetOut(tileList, ChosenPiece, card);
                    }
                    else {
                        board.movePiece(tileList, homeList, ChosenPiece, card);
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
    public int GetMoveScore(GameBoard board, GamePiece piece, int num_spaces, GamePiece[][] tileList, Card card, GamePiece[][] homeList, Enums.Color color) {

        int total_dist_to_home = 0;

        board.movePiece(tileList, homeList, piece, card);

        ArrayList<GamePiece> PlayersPieces = GetPlayersPieces(color);

        for (GamePiece _piece : PlayersPieces) {
            total_dist_to_home += distanceToHome(_piece);
        }

        return total_dist_to_home;

        // if moving the piece does places it on an opponent's start sport, score -= 5

        // if a piece's distance to the safe zone decreases by more than the value of the card (bc of a slide or maybe moving backward), score += number of extra spaces

        // if moving the piece would place it in the safe zone, score += 100

    }

}
