package Game;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import java.io.Serializable;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;


public class Controller implements Serializable{

    private boolean game_paused;
    private boolean game_over;
    private boolean new_game;
    private ArrayList<Card> deck;

    private GameBoard board;
    private MenuControllers menus;

    public Controller(boolean test){
        if(test){
            setupNewGame(Enums.Color.BLUE, new GamePiece(Enums.Color.YELLOW, true, true));
        }
    }

    /**
     * Setup for a new game
     * Break this into multiple subfunctions --> how to code for user input from GUI?
     */
    public void setupNewGame(Enums.Color color, GamePiece ... pieces) {
        this.board = new GameBoard(color, pieces);
        game_paused = false;
        game_over = false;
        new_game = true; // if false, game is a resumed game
        deck = initializeFullDeck();
        shuffleDeck(deck);
    }

    /**
     * Main gameplay method call this for playing a game
     */
    public void playGame() {

        ArrayList<Enums.Color> colorList = new ArrayList<>();
        ArrayList<GamePiece[]> piecesList = this.board.getOpponentsPieces();
        for (GamePiece[] pieces : piecesList) {
            colorList.add(pieces[0].getColor());
        }

        while(!game_over){

            for (Enums.Color color : colorList) {
                takeTurn(color);
            }
        }
    }

    /**
     * Initialize full deck
     *
     * HAS TEST CASE
     *
     * @return deck
     */
    public ArrayList<Card> initializeFullDeck() {


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
     *
     * HAS TEST CASE
     *
     * @param deck
     * @return shuffled deck
     */
    public ArrayList<Card> shuffleDeck(ArrayList<Card> deck) {

        Random rng = new Random();
        ArrayList<Card> ShuffledDeck = new ArrayList<>();
        ArrayList<Integer> rng_tracker = new ArrayList<>();
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
     *
     * HAS TEST CASE
     *
     * @return
     */
    private boolean checkDeckEmpty() {
        return deck.size() == 0;
    }

    /**
     * Draw a card (get its value), and remove it from the deck
     *
     * HAS TEST CASE
     *
     * @return int value on card
     */
    public Card drawCard() {
        Card card = deck.get(0);
        deck.remove(card);
        return card;
    }

    /**
     *
     * User takes a turn: draw and discard top card from the deck, choose a piece and move it, update board state, check game over, check deck emtpy
     * @return deck after discarding
     */
    public void takeTurn(Enums.Color color) {

        int card_num = drawCard().getType();
        // set a color for testing
        Enums.Color currentColor = color;
        if (card_num == 0) {
            ArrayList<GamePiece> opponentsPieces = getPlayersPieces(currentColor);
            GamePiece chosenPiece = opponentsPieces.get(0);
            int i = 1;
            while (chosenPiece.isHome()) {
                if (i < 4) {
                    chosenPiece = opponentsPieces.get(i);
                    i += 1;
                }
            }
            ArrayList<GamePiece[]> OpponentsPieces = this.board.getOpponentsPieces();
            GamePiece[] victimOpponent = OpponentsPieces.get(0);
            int j = 1;
            while (victimOpponent[0].getColor() == color) {
                if (j < 3) {
                    victimOpponent = OpponentsPieces.get(j);
                    j += 1;
                }
            }
            GamePiece victimPiece = victimOpponent[0];
            board.sorry(chosenPiece,victimPiece);

        } else {

            ArrayList<GamePiece> opponentsPieces = getPlayersPieces(currentColor);
            ArrayList<GamePiece> opponentsEligiblePieces = getEligiblePieces(opponentsPieces, card_num);
            ChoosePiece(opponentsEligiblePieces, card_num);


            boolean over = isGame_over(opponentsPieces);
            if (over) {
                setGame_over(true);
            } else {
                if (checkDeckEmpty()) {
                    deck = initializeFullDeck();
                    shuffleDeck(deck);
                }
            }
        }
    }


    /**
     * List players' pieces
     * @param color
     * @return ListOfPieces
     */
    public ArrayList<GamePiece> getPlayersPieces(Enums.Color color) {

        ArrayList<GamePiece> opponentPieces = new ArrayList<>();
        ArrayList<GamePiece[]> OpponentsPieces = this.board.getOpponentsPieces();
        for (GamePiece[] pieces : OpponentsPieces) {
            for (GamePiece piece : pieces) {
                if (piece.getColor() == color) {
                    opponentPieces.add(piece);
                }
            }
        }
        return opponentPieces;
    }

    /**
     *
     * List eligible pieces based on opponent being nice or mean
     * @param
     * @return EligiblePieces
     */
    public ArrayList<GamePiece> getEligiblePieces(ArrayList<GamePiece> PlayersPieces, int card_num) {

        ArrayList<GamePiece> EligiblePieces = new ArrayList<>();
        for (GamePiece piece : PlayersPieces) {
            boolean check_bump = checkBump(piece, card_num);
            if (!piece.isMean() && !check_bump) {
                EligiblePieces.add(piece);
            }
            if (piece.isMean() && check_bump) {
                EligiblePieces.add(piece);
            }
        }

        // if there are no eligible pieces, add a piece to the eligible piece list anyway
        if (EligiblePieces.size() == 0) {
            for (GamePiece piece2 : PlayersPieces) {
                EligiblePieces.add(piece2);
            }
        }
        return EligiblePieces;
    }

    /**
     *
     * Choose piece to move, and move it
     */
    public void ChoosePiece(ArrayList<GamePiece> EligiblePieces, int card_num) {

        GamePiece ChosenPiece = EligiblePieces.get(0);
        Enums.Color color = ChosenPiece.getColor();
        // move a piece out of home if possible
        boolean leaveHome = false;
        if (card_num == 1 | card_num == 2) {
            leaveHome = board.homeGetOut(card_num, color, false);
        }

        // if unable to leave home, or card not 1 or 2 ...
        if (!leaveHome) {
            if (ChosenPiece.isSmart()) {

                if (card_num != 4) {

                    GamePiece currentPiece = ChosenPiece;
                    ArrayList<GamePiece> pieceList = new ArrayList<>();
                    pieceList.add(currentPiece);
                    // score is cumulative distance from home, so we want to minimize it

                    int currentScore = scoreMove(ChosenPiece, card_num);
                    ArrayList<Integer> scoreList = new ArrayList<>();
                    scoreList.add(currentScore);


                    for (GamePiece piece : EligiblePieces) {
                        int newScore = scoreMove(piece, card_num);
                        if (newScore < currentScore) {
                            pieceList.add(0, piece);
                            scoreList.add(0, newScore);
                        } else {
                            pieceList.add(piece);
                            scoreList.add(newScore);
                        }
                    }
                    boolean pieceMoved = false;
                    //boolean pieceMoved = board.movePieceForward(ChosenPiece, card_num);
                    int index = 1;
                    while (pieceMoved == false) {
                        if (index < pieceList.size()) {
                            GamePiece newPiece = pieceList.get(index);
                            if (newPiece.isHome()) {
                                pieceMoved = board.moveInHome(newPiece, card_num);
                            } else {
                                pieceMoved = board.movePieceForward(newPiece, card_num);
                                index += 1;
                            }
                        }
                    }
                }

                if (card_num == 4) {

                    GamePiece currentPiece = ChosenPiece;
                    ArrayList<GamePiece> pieceList = new ArrayList<>();
                    pieceList.add(currentPiece);

                    int currentScore = scoreMove(ChosenPiece, card_num);
                    ArrayList<Integer> scoreList = new ArrayList<>();
                    scoreList.add(currentScore);


                    for (GamePiece piece : EligiblePieces) {
                        int newScore = scoreMove(piece, card_num);
                        if (newScore > currentScore) {
                            pieceList.add(0, piece);
                            scoreList.add(0, newScore);
                        } else {
                            pieceList.add(piece);
                            scoreList.add(newScore);
                        }

                    }

                    GamePiece newPiece = pieceList.get(0);
                    board.movePieceBackWard(newPiece, card_num);
                    }
            }

            } else {

                // choose random piece
                Random rng = new Random();
                int r = rng.nextInt(EligiblePieces.size());
                ChosenPiece = EligiblePieces.get(r);

                // move piece forward
                if (card_num != 4) {

                    if (ChosenPiece.isHome()) {
                        boolean pieceMoved = board.moveInHome(ChosenPiece, card_num);
                        while (board.movePieceForward(ChosenPiece, card_num) == false) {
                            r = rng.nextInt(EligiblePieces.size());
                            ChosenPiece = EligiblePieces.get(r);
                            pieceMoved = board.moveInHome(ChosenPiece, card_num);
                        }
                    } else {
                        boolean pieceMoved = board.movePieceForward(ChosenPiece, card_num);
                        while (board.movePieceForward(ChosenPiece, card_num) == false) {
                            r = rng.nextInt(EligiblePieces.size());
                            ChosenPiece = EligiblePieces.get(r);
                            pieceMoved = board.movePieceForward(ChosenPiece, card_num);
                        }
                    }
                }

                else {
                    // What if the piece can't move backward 4? (As a result of bumping its own piece)
                    board.movePieceBackWard(ChosenPiece, card_num);
                }

                // if (card_num == 7)

                // if (card_num == 10)

                // if (card_num == 11)

            }
        }

    public int scoreMove(GamePiece piece, int card_num) {
        int moveScore = piece.getMovesLeft();
        if (card_num == 4) {
            int movesLeft = piece.getMovesLeft();
            if (movesLeft < 55) {
                moveScore = 1;
            }
        }
        return moveScore;
    }

    private boolean checkBump(GamePiece piece, int card_num){
        return true;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public boolean isGame_paused() {
        return game_paused;
    }

    public void setGame_paused(boolean game_paused) {
        this.game_paused = game_paused;
    }

    public boolean isGame_over(ArrayList<GamePiece> Pieces) {
        int cumulativeDist = 0;
        for (GamePiece piece : Pieces) {
            cumulativeDist += piece.getMovesLeft();
        }
        if (cumulativeDist == 0) {
            game_over = true;
        } else {
            game_over = false;
        }
        return game_over;
    }

    public void setGame_over(boolean game_over) {
        this.game_over = game_over;
    }

    public boolean isNew_game() {
        return new_game;
    }

    public void setNew_game(boolean new_game) {
        this.new_game = new_game;
    }

    public void setMenuControllers(MenuControllers menus){
        this.menus = menus;
    }

}