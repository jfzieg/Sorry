package Game;

import java.io.Serializable;
import java.util.Random;
import java.util.ArrayList;

public class Controller implements Serializable{
    private static boolean TEST = true;
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
    public void playGame(){
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
     * Draw a card (get its value), and remove from deck
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
     * @param board
     * @return bool
     */
//    public boolean CheckGameOver(GameBoard board) {
//        boolean GameOver;
//        // if all pieces of one color in HOME, GameOver = True
//        // else, GameOver = False
//        // return GameOver
//    }

    /**
     *
     * TODO: WRITE TEST CASE
     *
     * User takes a turn: draw and discard top card from the deck, choose a piece and move it, update board state, check game over, check deck emtpy
     * @return deck after discarding
     */
    public void takeTurn(Enums.Color color) {

        // how many opponents are there? what colors are they?

        int card_num = drawCard().getType();
        // set a color for testing
        Enums.Color currentColor = color;
        ArrayList<GamePiece> opponentsPieces = getPlayersPieces(currentColor);
        ArrayList<GamePiece> opponentsEligiblePieces = getEligiblePieces(opponentsPieces, card_num);
        GamePiece chosenPiece = ChoosePiece(opponentsEligiblePieces, card_num);

        boolean over = isGame_over(opponentsPieces);
        if (over) {
            // go to game over screen
        } else {
            if (checkDeckEmpty()) {
                deck = initializeFullDeck();
                shuffleDeck(deck);
            }
            // next player's turn
        }
        // What if there are no valid moves?

    }


    /**

     * TODO: WRITE TEST CASE
     *
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
        //board.getPlayersPieces();
        // make a list (AllPieces) of all the pieces
        // for (GamePiece piece : AllPieces) {
        // if (piece.color == color) {
        // PlayersPieces.add(piece);
        //}
        //}

        return opponentPieces;
    }

    /**
     *
     * TODO: WRITE TEST CASE
     *
     * Maybe convert
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

        for (GamePiece piece2 : PlayersPieces) {
            if (EligiblePieces.size() == 0) {
                EligiblePieces.add(piece2);
            }
        }
        return EligiblePieces;
    }



    /**
     *
     * TODO: WRITE TEST CASE
     *
     * Choose piece to move
     */
    public GamePiece ChoosePiece(ArrayList<GamePiece> EligiblePieces, int card_num) {
        GamePiece ChosenPiece = EligiblePieces.get(0);
        Enums.Color color = ChosenPiece.getColor();
        boolean leaveHome = false;
        if (card_num == 1 | card_num == 2) {
            leaveHome = board.homeGetOut(card_num, color, false);
        }
        if (!leaveHome) {
            if (ChosenPiece.isSmart()) {

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

                boolean pieceMoved = board.movePieceForward(ChosenPiece, card_num);
                int index = 1;
                while (pieceMoved == false) {
                    if (index < pieceList.size()) {
                        GamePiece newPiece = pieceList.get(index);
                        pieceMoved = board.movePieceForward(newPiece, card_num);
                        index += 1;
                    }
                }

            } else {

                // choose random piece
                Random rng = new Random();
                int r = rng.nextInt(EligiblePieces.size());
                ChosenPiece = EligiblePieces.get(r);

                // move piece forward
                boolean pieceMoved = board.movePieceForward(ChosenPiece, card_num);
                while (board.movePieceForward(ChosenPiece, card_num) == false) {
                    r = rng.nextInt(EligiblePieces.size());
                    ChosenPiece = EligiblePieces.get(r);
                    pieceMoved = board.movePieceForward(ChosenPiece, card_num);
                }


                // if (card_num == 7)

                // if (card_num == 10)

                // if (card_num == 11)


            }
        }
        return ChosenPiece;
    }


    public int scoreMove(GamePiece piece, int card_num) {
        int moveScore = piece.getMovesLeft();
        return moveScore;
    }
  
    private boolean CheckValidMove(GamePiece piece, int card_num){

        return true;
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

        public void setGame_over ( boolean game_over){
            this.game_over = game_over;
        }

        public boolean isNewGame () {
            return new_game;
        }

        public void setNew_game ( boolean new_game){
            this.new_game = new_game;
        }

        /**
         * DON'T THINK WE NEED THIS METHOD --> can loop through players in main
         * @param current_player
         * @return
         */

        public String nextPlayer (String current_player){
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

        public void setMenuControllers (MenuControllers menus){
            this.menus = menus;
        }
    }
