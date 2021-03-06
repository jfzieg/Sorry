package Game;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Anh Phan
 * @date 2018-03-14
 * @version 1.0
 */


public class GameBoard implements Serializable{
    //Variables for game board
    private static final long serialVersionUID = -862206733593296676L;
    private GamePiece[][] tileList;
    private GamePiece[][] homeList;
    private GamePiece[] playerPieces;
    private ArrayList<GamePiece[]> opponentsPieces;

    /**
     * Creates a new gameboard using the player's specified color pieces, and a placeholder GamePiece with user defined
     * settings for each opponent. Expects that only max 3 opponents will be generated.
     *
     * Containers for the player's GamePieces and the opponents' GamePieces are created with GamePieces
     * @param color
     * @param pieces
     */
    public GameBoard(Enums.Color color, GamePiece ... pieces){
        playerPieces = new GamePiece[4];
        opponentsPieces = new ArrayList<>(4);
        for(int i = 0; i < 4; i++){
            playerPieces[i] = new GamePiece(color);
        }

        for(GamePiece piece : pieces) {
            if (piece != null) {
                GamePiece[] opponent = new GamePiece[4];
                for (int i = 0; i < 4; i++) {
                    opponent[i] = new GamePiece(piece.getColor(), piece.isSmart(), piece.isMean());
                }
                opponentsPieces.add(opponent);
            }
        }

        tileList = new GamePiece[4][16];
        homeList = new GamePiece[4][5];
    }

    /**
     * Default move method for general moves. Moves the specified piece card # spaces, and checks for a bump or slide.
     * Resets piece's location if move was unsuccessful.
     * @param piece
     * @param card
     * @return true if move was successful, false otherwise
     */
    public boolean movePieceForward(GamePiece piece, int card){
        // Move specified number of places on the same side
        int oldSide = piece.getBoardSide(), oldLoc = piece.getInnerLocation(), oldMovesLeft = piece.getMovesLeft();

        if(piece.getInnerLocation() + card < 16){
            piece.adjustMovesLeft(-card);
            piece.setInnerLocation(piece.getInnerLocation() + card);

            //Check if the piece can get into home or not
            //If it can move to home, adjust the piece's info and have variable isHome to true
            if(checkHome(piece)){
                piece.setInnerLocation(Math.abs(piece.getMovesLeft() + 1));
                int boardSide = piece.getColor().getSide();
                piece.setMovesLeft( 4 - piece.getInnerLocation());
                piece.setHome(true);

                if (homeList[boardSide][piece.getInnerLocation()] == null){
                    homeList[boardSide][piece.getInnerLocation()] = piece;
                    tileList[oldSide][oldLoc] = null;
                    return true;
                } else{
                    piece.setLocation(oldSide, oldLoc);
                    piece.setMovesLeft(oldMovesLeft);
                    piece.setHome(false);
                    return false;
                }
            }

            checkSlide(piece);

            // If a bump is possible, bump the target and update tileList
            if(checkBump(piece)){
                bumpPiece(piece);
                tileList[oldSide][oldLoc] = null;
                return true;
            }
            // If a bump isn't possible, check to see if the tile is free and update tileList
            else {
                if(tileList[piece.getBoardSide()][piece.getInnerLocation()] == null) {
                    tileList[piece.getBoardSide()][piece.getInnerLocation()] = piece;
                    tileList[oldSide][oldLoc] = null;
                    return true;
                }
                piece.setLocation(oldSide, oldLoc);
                piece.setMovesLeft(oldMovesLeft);
                return false;
            }
        }
        // Move piece to next side, add remaining moves left
        else{
            piece.adjustMovesLeft(-card);
            card -= (15 - piece.getInnerLocation());
            int boardSide = changeBoardSide(piece.getBoardSide(), true);
            piece.setLocation(boardSide, card);
            checkSlide(piece);

            //Check if the piece can get into home or not
            //If it can move to home, adjust the piece's info and have variable isHome to true
            if(checkHome(piece)){
                piece.setInnerLocation(Math.abs(piece.getMovesLeft() + 1));
                int boardSide2 = piece.getColor().getSide();
                piece.setMovesLeft( 4 - piece.getInnerLocation());
                piece.setHome(true);

                if (homeList[boardSide2][piece.getInnerLocation()] == null){
                    homeList[boardSide2][piece.getInnerLocation()] = piece;
                    tileList[oldSide][oldLoc] = null;
                    return true;
                } else{
                    piece.setLocation(oldSide, oldLoc);
                    piece.setMovesLeft(oldMovesLeft);
                    piece.setHome(false);
                    return false;
                }
            }

            // If a bump is possible, bump the target and update tileList
            if(checkBump(piece)){
                bumpPiece(piece);
                tileList[oldSide][oldLoc] = null;
                return true;
            }
            // If a bump isn't possible, check to see if the tile is free and update tileList
            else {
                if(tileList[piece.getBoardSide()][piece.getInnerLocation()] == null) {
                    tileList[piece.getBoardSide()][piece.getInnerLocation()] = piece;
                    tileList[oldSide][oldLoc] = null;
                    return true;
                }
                piece.setLocation(oldSide, oldLoc);
                piece.setMovesLeft(oldMovesLeft);
                return false;
            }
        }
    }

    /**
     * Changes the side of the board a piece is on.
     * @param boardSide the current side of the board
     * @param forward whether the piece is moving forward (true) or backwards (false)
     * @return the new boardSide
     */
    private int changeBoardSide(int boardSide, boolean forward){
        if(forward) {
            if (boardSide < 3 && boardSide >= 0) {
                boardSide += 1;
                return boardSide;
            }
            return 0;
        }
        else{
            if (boardSide <= 3 && boardSide > 0) {
                boardSide -= 1;
                return boardSide;
            }
            return 3;
        }
    }

    /**
     * Checks whether a piece is able to slide (not on home side), and adjusts its position if needed.
     * Slides piece 4 forward if piece lands on index 1, 5 forward if index 9.
     *
     * @param piece the GamePiece to check for a slide
     */
    void checkSlide(GamePiece piece){
        if(piece.getBoardSide() != piece.getColor().getSide()){
            if(piece.getInnerLocation() == 1){
                piece.setInnerLocation(piece.getInnerLocation() + 4);
                piece.adjustMovesLeft(-4);
            }
            if(piece.getInnerLocation() == 9){
                piece.setInnerLocation(piece.getInnerLocation() + 5);
                piece.adjustMovesLeft(-5);
            }
        }
    }

    /**
     * Used for the #4 card and #10 card only, moves piece backwards.
     * @param piece
     * @param card
     * @return true if it can move backward, false otherwise
     */
    boolean movePieceBackWard( GamePiece piece, int card){
        // Move specified number of places on the same side

        int oldSide = piece.getBoardSide(), oldLoc = piece.getInnerLocation(), oldMovesLeft = piece.getMovesLeft();

        if(piece.getInnerLocation() - card > 0){
            piece.adjustMovesLeft(+card);
            piece.setInnerLocation(piece.getInnerLocation() - card);
            checkSlide(piece);

            // If a bump is possible, bump the target and update tileList
            if(checkBump(piece)){
                bumpPiece(piece);
                tileList[oldSide][oldLoc] = null;
                return true;
            }

            else {
                if(tileList[piece.getBoardSide()][piece.getInnerLocation()] == null) {
                    tileList[piece.getBoardSide()][piece.getInnerLocation()] = piece;
                    tileList[oldSide][oldLoc] = null;
                    return true;
                }

                piece.setLocation(oldSide, oldLoc);
                piece.setMovesLeft(oldMovesLeft);
                return false;
            }

        }
        // Move piece to next side, add remaining moves left
        else{
            piece.adjustMovesLeft(+card);
            card -= piece.getInnerLocation();
            if(card == 0){
                card = 15;
            }
            int boardSide = changeBoardSide(piece.getBoardSide(), false);
            piece.setBoardSide(boardSide);
            piece.setInnerLocation(card);
            checkSlide(piece);

         // If a bump is possible, bump the target and update tileList
            if(checkBump(piece)){
                bumpPiece(piece);
                tileList[oldSide][oldLoc] = null;
                return true;
            }

            // If a bump isn't possible, check to see if the tile is free and update tileList
            else {
                if(tileList[piece.getBoardSide()][piece.getInnerLocation()] == null) {
                    tileList[piece.getBoardSide()][piece.getInnerLocation()] = piece;
                    tileList[oldSide][oldLoc] = null;
                    return true;
                }

                piece.setLocation(oldSide, oldLoc);
                piece.setMovesLeft(oldMovesLeft);
                return false;
            }
        }

    }

    /**
     * Moves a piece out of the color-specified player's home if possible
     * @param card the card, 1 or 2
     * @param color the player's color
     * @param player true if the player is human
     * @return true if move was successful, false otherwise
     */
    boolean homeGetOut( int card, Enums.Color color, boolean player){

        if (card == 1 || card == 2) {
            if (player) {
                for (GamePiece piece : playerPieces) {
                    // Move a piece out of home if there is one
                    if (piece.getBoardSide() == -1 && tileList[piece.getColor().getSide()][4] == null) {
                        piece.setLocation(piece.getColor().getSide(), 4);
                        tileList[piece.getBoardSide()][4] = piece;
                        return true;
                    }
                }
                return false;
            }
            // If the piece is an opponents
            else {
                for (GamePiece[] opponent : opponentsPieces) {
                    // Find the opponent's array
                    if (opponent[0].getColor() == color) {
                        for (GamePiece piece : opponent) {
                            // Move a piece out of home if there is one
                            if (piece.getBoardSide() == -1 && tileList[piece.getColor().getSide()][4] == null) {
                                piece.setLocation(piece.getColor().getSide(), 4);
                                tileList[piece.getBoardSide()][4] = piece;
                                return true;
                            }
                        }
                        return false;
                    }
                }
            }
        }
        return false;  // Return false if card isn't 1, 2
    }

    /**
     *
     * check for same color, bool true if bump, false otherwise
     * @param piece
     * @return return true if it can bump, false otherwise
     */
    boolean checkBump(GamePiece piece){
        GamePiece bumped = tileList[piece.getBoardSide()][piece.getInnerLocation()];
        if(bumped != null && bumped.getColor() != piece.getColor()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Takes the specified GamePiece, and uses it to bump the piece at it's location in tileList.
     * @param piece The piece doing the bumping
     */
    private void bumpPiece(GamePiece piece){
        GamePiece temp = tileList[piece.getBoardSide()][piece.getInnerLocation()];
        tileList[piece.getBoardSide()][piece.getInnerLocation()] = piece;
        temp.setBoardSide(-1);
    }
    /**
     * Send a victim piece to start and have your piece at the victim's location
     * @param piece
     * @param victim
     * @return true if a piece can bump a victim, false otherwise
     * Not all victim can be bumped, you can only bump the piece that will benefit you.
     */
    public boolean sorry(GamePiece piece, GamePiece victim){
        //Calculate the distant between 2 pieces
        int count = calPieceDistant(piece, victim);
        if(piece.getMovesLeft() - count < 0){
            return false;
        }

        //Check if piece can be switched
        tileList[piece.getBoardSide()][piece.getInnerLocation()] = null;
        tileList[victim.getBoardSide()][victim.getInnerLocation()] = piece;

        //Adjust piece and victim information
        piece.setBoardSide(victim.getBoardSide());
        piece.setInnerLocation(victim.getInnerLocation());

        piece.adjustMovesLeft(-count);

        victim.setBoardSide(-1);
        victim.setMovesLeft(58);
        return true;
    }

    /**
     * Switch place between two pieces
     * Call this when card 11 is activated
     * @param piece
     * @param victim
     * @return true if you can switch place with a victim, false otherwise
     * Not all victim can be switched, you can only switch the piece that will benefit you.
     */
    public boolean switchPiece (GamePiece piece, GamePiece victim){
        //Calculate the distant between 2 pieces
        int count = calPieceDistant(piece, victim);

        //Check if piece can be switched
        if(piece.getMovesLeft() - count < 0){
            return false;
        }
        //Switch the position of the two pieces in tileList
        tileList[piece.getBoardSide()][piece.getInnerLocation()] = victim;
        tileList[victim.getBoardSide()][victim.getInnerLocation()] = piece;

        int tempBoardSide = piece.getBoardSide();
        int tempInnerLocation = piece.getInnerLocation();

        //Adjust piece and victim information
        piece.setBoardSide(victim.getBoardSide());
        piece.setInnerLocation(victim.getInnerLocation());
        piece.adjustMovesLeft(-count);

        victim.setBoardSide(tempBoardSide);
        victim.setInnerLocation(tempInnerLocation);
        victim.adjustMovesLeft(count);
        return true;

    }

    /** Go through the tile list, get the piece that want to switch
     * then calculate how many step that needs to go to the victim
     *
     * @param piece
     * @param victim
     * @return a distant between 2 piece from the piece that want to switch
     */
    int calPieceDistant(GamePiece piece, GamePiece victim){
        int count = 0;
        boolean check = false;
        boolean check2 = false;
        boolean check3 = false;

        //Go through 2 nested loop for the 2D array
        //If it can find the piece, check boolean to true and then we can increment the count
        //If i and j reach to the end and cannot find the victim, switch i back to 0
        //Go through the loop again to find the victim, keep incrementing count until victim is found
        //If victim is found, break out of the loop
        for(int i = 0; i < 4; i++){

            if(check2 == true){
                i -= 1;
            }

            for (int j = 1; j < 16; j++){
                if (check == true){
                    count ++;
                    if(tileList[i][j] == victim){
                        check3 = true;
                        break;

                    } else if(i == 3 && j == 15){
                        i = 0;
                        check2 = true;

                    } else if(i == 0 && j == 15){
                        check2 = false;
                    }
                }

                if(tileList[i][j] == piece){
                    check = true;

                }
        }
            if (check3 == true){
                break;
            }
        }
        return count;
    }

    /**
     * Check movesLeft in piece to see if it can get into homeTile or not
     * @param piece
     * @return a true if it can get to homeTile, false otherwise
     */
    boolean checkHome(GamePiece piece){
        if(piece.getMovesLeft() < 0){
            return true;
        }
        return false;
    }

    /**
     * Called when piece is in home (boolean isHome = true)
     * Piece won't move if there is a piece in front of it or the move is unavailable
     * MovesLeft is updated when a piece is get to home
     * @param piece
     * @param card
     * @return true if move was successful, false otherwise
     */
    boolean moveInHome(GamePiece piece, int card){
        // Move specified number of places on the same side
        int oldSide = piece.getBoardSide(), oldLoc = piece.getInnerLocation(), oldMovesLeft = piece.getMovesLeft();

        //If move is not exceed moveLeft
        if(piece.getInnerLocation() + card < 5){
            piece.adjustMovesLeft(-card);
            piece.setInnerLocation(piece.getInnerLocation() + card);
            //If nothing in the place, you can move
            if(homeList[piece.getBoardSide()][piece.getInnerLocation()] == null || piece.getInnerLocation() == 4){
                homeList[piece.getBoardSide()][piece.getInnerLocation()] = piece;
                homeList[oldSide][oldLoc] = null;
                return true;
            }

            piece.setLocation(oldSide, oldLoc);
            piece.setMovesLeft(oldMovesLeft);
            return false;
        }

        return false;
    }


    public GamePiece[][] getTileList() {
        return tileList;
    }

    public ArrayList<GamePiece[]> getOpponentsPieces() {
        return opponentsPieces;
    }

    public GamePiece[] getPlayerPieces() {
        return playerPieces;
    }
}





