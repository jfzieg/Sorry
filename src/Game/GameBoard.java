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
        opponentsPieces = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            playerPieces[i] = new GamePiece(color);
        }

        for(GamePiece piece : pieces){
            GamePiece[] opponent = new GamePiece[4];
            for(int i = 0; i < 4; i++){
                opponent[i] = new GamePiece(piece.getColor(), piece.isSmart(), piece.isMean());
            }
            opponentsPieces.add(opponent);
        }
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
            piece.adjustMovesLeft(piece.getMovesLeft() - card);
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
            card -= (16 - piece.getInnerLocation());
            piece.setLocation(changeBoardSide(piece.getBoardSide(), true), card);
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
     * Changes the side of the board a piece is on.
     * @param boardSide the current side of the board
     * @param forward whether the piece is moving forward (true) or backwards (false)
     * @return the new boardSide
     */
    private int changeBoardSide(int boardSide, boolean forward){
        if(forward) {
            if (boardSide < 3 && boardSide >= 0) {
                return boardSide++;
            }
            return 0;
        }
        else{
            if (boardSide <= 3 && boardSide > 0) {
                return boardSide--;
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
     * Used for the #4 card only, moves piece backwards.
     * @param piece
     * @param card
     */
    void movePieceBackWard( GamePiece piece, int card){
        // Move specified number of places on the same side
        if(piece.getInnerLocation() - card > 0){
            piece.adjustMovesLeft(+card);
            piece.setInnerLocation(piece.getInnerLocation() - card);
        }
        // Move piece to next side, add remaining moves left
        else{
            piece.adjustMovesLeft(+card);
            card -= piece.getInnerLocation();
            piece.setBoardSide(changeBoardSide(piece.getBoardSide(), false));
            piece.setInnerLocation(card);
        }
//        checkBump();
        checkSlide(piece);

    }


    //Only get called if checkChangeTile return true
    //temporary variable move represent the card number
    //Since each tile has 16 index, we calculate the available move in the tile that the piece is on by
    //using the last index of the tile 15 - the index that the piece is currently on and we have availableMove
    //Then we have the number of card minus available; therefore, we will know how much index we need to move in the new tile
    int changePiecePos(GamePiece piece, int card, int i, int j){
        //temporary move variable for card
        int move = card;
        int availableMove = 15 - j;
        int needMove = move - availableMove;
        return needMove;
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
     * check for same color, bool true if bump, false otherwise
     * @param piece
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

    public GamePiece[][] getTileList() {
        return tileList;
    }

    public ArrayList<GamePiece[]> getOpponentsPieces() {
        return opponentsPieces;
    }
}





