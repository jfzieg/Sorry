package Game;

import java.io.Serializable;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;

/**
 * @author Olivia Hurd
 * */
public class Controller implements Serializable{
    private static boolean TEST = false;
    private boolean game_paused;
    private boolean game_over;
    private boolean new_game;
    private ArrayList<Card> deck;
    private GameBoard board;
    private MenuControllers menus;


    /**
     * Test Constructor
     */
    public Controller(){
        if(TEST){
            setupNewGame(Enums.Color.BLUE, new GamePiece(Enums.Color.YELLOW, true, true), new GamePiece(Enums.Color.RED, false, true));
        }
    }

    /**
     * Setup for a new game
     * Set game fields appropriately
     */
    public void setupNewGame(Enums.Color color, GamePiece ... pieces) {
        this.board = new GameBoard(color, pieces);
        game_paused = false;
        game_over = false;
        new_game = true; // if false, game is a resumed game

        deck = initializeFullDeck();
    }

//    /**
//     * Main gameplay method
//     * Call this for playing a game
//     */
//    public void playGame(){
//        ArrayList<Enums.Color> colorList = new ArrayList<>();
//        ArrayList<GamePiece[]> piecesList = this.board.getOpponentsPieces();
//        for (GamePiece[] pieces : piecesList) {
//            colorList.add(pieces[0].getColor());
//        }
//        while(!game_over){
//
//            for (Enums.Color color : colorList) {
//                takeTurn(color);
//            }
//        }
//    }

    /**
     * Initialize full deck
     *
     * HAS TEST CASE
     *
     * @return deck
     */
    public ArrayList<Card> initializeFullDeck() {


        ArrayList<Card> deck = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            deck.add(new Card(Enums.CardType.ONE));
        }

        for (int i = 0; i < 4; i++) {
            deck.add(new Card(Enums.CardType.TWO));
            deck.add(new Card(Enums.CardType.THREE));
            deck.add(new Card(Enums.CardType.FOUR));
            deck.add(new Card(Enums.CardType.FIVE));
            deck.add(new Card(Enums.CardType.SEVEN));
            deck.add(new Card(Enums.CardType.EIGHT));
            deck.add(new Card(Enums.CardType.TEN));
            deck.add(new Card(Enums.CardType.ELEVEN));
            deck.add(new Card(Enums.CardType.TWELVE));
            deck.add(new Card(Enums.CardType.SORRY));
        }
        Collections.shuffle(deck);
        return deck;

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
     * Checks to see if the game is over
     * @return bool
     */
    public boolean checkGameOver() {
        int count = 0;

        for(GamePiece piece : board.getPlayerPieces()){
            if(piece.getMovesLeft() == 0){
                count++;
            }
        }
        // player win case
        if(count == 4){
            game_over = true;
            return true;
        }

        for(GamePiece[] pieces : board.getOpponentsPieces()){
            count = 0;
            for(GamePiece piece : pieces) {
                if (piece.getMovesLeft() == 0) {
                    count++;
                }
            }
            // opponent win case
            if(count == 4){
                game_over = true;
                return true;
            }
        }
        return false;
    }

    /**
     *
     * TODO: WRITE TEST CASE
     *
     * User takes a turn: draw and discard top card from the deck, choose a piece and move it, update board state, check game over, check deck empty
     * @return deck after discarding
     */
    public void takeTurn(Enums.Color color, Card card) {

        int card_num = card.getType();
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
            board.sorry(chosenPiece, victimPiece);

        } else {

            ArrayList<GamePiece> opponentsPieces = getPlayersPieces(currentColor);
            ArrayList<GamePiece> opponentsEligiblePieces = getEligiblePieces(opponentsPieces, card_num);
            ChoosePiece(opponentsEligiblePieces, card_num);


            ;
            if (checkGameOver()) {
                setGameOver(true);
            } else {
                if (checkDeckEmpty()) {
                    deck = initializeFullDeck();
                }

                if (checkGameOver()) {
                    // go to game over screen
                } else {
                    if (checkDeckEmpty()) {
                        deck = initializeFullDeck();
                    }
                }
            }
        }
    }


            /**
             * List player's pieces
             * @param color
             * @return ListOfPieces
             */
            public ArrayList<GamePiece> getPlayersPieces (Enums.Color color){
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
             * List eligible pieces based on opponent being nice or mean
             * @param
             * @return EligiblePieces
             */
            public ArrayList<GamePiece> getEligiblePieces (ArrayList < GamePiece > PlayersPieces,int card_num){
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
             * Choose piece to move, and move it
             */
            public void ChoosePiece (ArrayList < GamePiece > EligiblePieces,int card_num){

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

                        if (card_num != 4 && card_num != 11) {

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

                        if (card_num == 11) {
                            ArrayList<GamePiece[]> pieces = board.getOpponentsPieces();
                            int distance = 100;
                            int max_dist = 0;
                            for (GamePiece playersPiece : board.getPlayerPieces()) {
                                if (playersPiece.getMovesLeft() > max_dist) {
                                    max_dist = playersPiece.getMovesLeft();
                                    ChosenPiece = playersPiece;
                                }
                            }
                            GamePiece VictimPiece = pieces.get(0)[0];
                            for (GamePiece[] colorPieces : pieces) {
                                if (colorPieces[0].getColor() != ChosenPiece.getColor()) {
                                    VictimPiece = colorPieces[0];
                                    for (GamePiece piece : colorPieces) {
                                        if (piece.getMovesLeft() < distance) {
                                            distance = piece.getMovesLeft();
                                            VictimPiece = piece;
                                        }
                                    }
                                }
                            }

                            board.switchPiece(ChosenPiece, VictimPiece);
                        }
                    }

                } else {

                    // choose random piece
                    Random rng = new Random();
                    int r = rng.nextInt(EligiblePieces.size());
                    ChosenPiece = EligiblePieces.get(r);

                    // move piece forward
                    if (card_num != 4 && card_num != 7) {

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

                    if (card_num == 4) {
                        // What if the piece can't move backward 4? (As a result of bumping its own piece)
                        board.movePieceBackWard(ChosenPiece, card_num);
                    }

                    if (card_num == 7) {
                        rng = new Random();
                        int r2 = rng.nextInt(EligiblePieces.size());
                        while (r2 == r) {
                            r2 = rng.nextInt(EligiblePieces.size());
                        }
                        GamePiece chosenPiece2 = EligiblePieces.get(r2);
                        boolean piece1moved;
                        if (chosenPiece2.isHome()) {
                            piece1moved = board.moveInHome(ChosenPiece, 3);
                        } else {
                            piece1moved = board.movePieceForward(ChosenPiece, 3);
                        }
                        boolean piece2moved = true;
                        if (piece1moved) {
                            if (chosenPiece2.isHome()) {
                                piece2moved = board.moveInHome(chosenPiece2, 4);
                            } else {
                                piece2moved = board.movePieceForward(chosenPiece2, 4);
                            }
                        }
                        if (!piece1moved || !piece2moved) {
                            if (ChosenPiece.isHome()) {
                                piece1moved = board.moveInHome(ChosenPiece, card_num);
                            } else {
                                piece1moved = board.movePieceForward(ChosenPiece, card_num);
                            }

                            int tryCounter = 1;
                            while (!piece1moved) {
                                if (tryCounter < 4) {
                                    rng = new Random();
                                    int r3 = rng.nextInt(EligiblePieces.size());
                                    while (r3 != r2 && r3 != r) {
                                        ChosenPiece = EligiblePieces.get(r3);
                                        r3 = rng.nextInt(EligiblePieces.size());
                                    }
                                    tryCounter += 1;
                                    if (ChosenPiece.isHome()) {
                                        piece1moved = board.moveInHome(ChosenPiece, card_num);
                                    } else {
                                        piece1moved = board.movePieceForward(ChosenPiece, card_num);
                                    }
                                }
                            }
                        }
                    }

                }
            }

            /**
             * Score a move
             * @param piece and card_num
             * @return integer score
             */
            public int scoreMove (GamePiece piece,int card_num){
                int moveScore = piece.getMovesLeft();
                if (card_num == 4) {
                    int movesLeft = piece.getMovesLeft();
                    if (movesLeft < 55) {
                        moveScore = 1;
                    }
                }
                return moveScore;
            }

            /**
             * Check if a move will result in a bum
             *
             * USED GameBoard method for this!
             *
             * @param piece and card_num
             * @return boolean
             */
            private boolean checkBump (GamePiece piece,int card_num){
                return true;
            }

            /**
             * @return the deck
             */
            public ArrayList<Card> getDeck () {
                return deck;
            }

            /**
             * @param deck
             *
             */
            public void setDeck (ArrayList < Card > deck) {
                this.deck = deck;
            }

            /**
             * Check if game is paused
             * @return boolean game over
             */
            public boolean isGamePaused() {
                return game_paused;
            }

            /**
             * Set paused game
             * @param  game_paused
             *
             */
            public void setGamePaused(boolean game_paused){
                this.game_paused = game_paused;
            }

            /**
             * checks if game is over
             * @param Pieces
             * @return boolean
             */
            public boolean isGameOver(ArrayList < GamePiece > Pieces) {
                int cumulativeDist = 0;
                int numPiecesHome = 0;
                for (GamePiece piece : Pieces) {
                    cumulativeDist += piece.getMovesLeft();
                    if (piece.isHome()) {
                        numPiecesHome += 1;
                    }
                }
                if (cumulativeDist == 0) {
                    game_over = true;
                } else {
                    game_over = false;
                }
                return game_over;
            }

            /**
             * Sets game to over
             * @param game_over
             */
            public void setGameOver(boolean game_over){
                this.game_over = game_over;
            }

            /**
             * checks to see if it's a new game
             * @return boolean new game
             */
            public boolean isNewGame () {
                return new_game;
            }

            /**
             * Set a new game
             * @param new_game
             */
            public void setNewGame(boolean new_game){
                this.new_game = new_game;
            }

            /**
             * @param menus
             */
            public void setMenuControllers (MenuControllers menus){
                this.menus = menus;
            }
            public GameBoard getVirtualBoard(){
                return board;
            }
}