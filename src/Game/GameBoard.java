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
    private GamePiece[][] tileList;

    private GamePiece[] playerPieces;
    private ArrayList<GamePiece[]> opponentsPieces;

    /**
     *
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


    //Slide method
    //tile list and a piece of a game are passed as parameters
    //Go through loop to check all the tiles. If at index 2 or 9 of a tile contains a piece and a tile is not the same
    //color as the piece, move the piece to index 5 or 13 of that tile

    /**
     * Checks whether a piece is able to slide, and adjusts its position if needed
     * @param piece the GamePiece to check for a slide
     */
    void checkSlide(GamePiece piece){
        if(piece.getBoardSide() != piece.getColor().getSide()){
            if(piece.getInnerLocation() == 1){
                piece.setInnerLocation(piece.getInnerLocation() + 4);
                piece.setMovesLeft(-4);
            }
            if(piece.getInnerLocation() == 9){
                piece.setInnerLocation(piece.getInnerLocation() + 5);
                piece.setMovesLeft(-5);
            }
        }
    }
//Move Piece method or maybe just move forward method


    public void movePieceForward(GamePiece piece, int card){
//    //temporary move variable for card
//    int move = card;
//    boolean added = false;
//   //Go through a nested loop
//   //First loop that go through 4 tiles
//   //Second loop that go through each slot in each tile
//    outerloop:
//    for(int i = 0; i < 4; i++){
//        for(int j = 0; j < 16; j++){
//           //If we find a a piece that we are looking for, we will check if it needs to change tile or if it needs to go home
//           //as you pick up the card. We are doing this by having 2 boolean variables that take boolean from checkChangeTile
//           // and checkhomeTile. We remove the piece from its current position.
//            if(tileList[i][j] == piece){
//                boolean checkChangeTile = checkChangeTile(tileList, piece, card, i, j);
//                boolean checkHomeTile = checkHomeTile(tileList, piece, i, j);
//                tileList[i][j] = null;
//                //If the piece needs to change tile
//                if(checkChangeTile == true){
//                    //We calculate the move that need to move
//                    int needMove = changePiecePos(tileList, piece, card, i, j);
//                    //Then we have to check if checkHomeTile is true and the move it needs to move is good in home
//                    if(checkHomeTile == true && needMove > 2 && needMove <= 8){
//                        //calculate home move by removing 2 index of the new tile and add the piece straight to some index
//                        //in home
//                        int homeMove = needMove - 2;
//                        homeList[piece.getColor()][homeMove - 1] = piece;
//                        break outerloop;
//                    } else{
//                        if (i == 3){
//                            tileList[0][needMove - 1] = piece;
//                            break outerloop;
//                        }
//                        else{
//                            //Else we just add the piece to an index of next tile
//                            tileList[i+1][needMove - 1] = piece;
//                            break outerloop;
//                        }
//                    }
//
//                }
//
//                else{
//                    //If everything up there is failed, we just move the piece in its current tile
//                    int needMove = j + move;
//                    tileList[i][needMove] = piece;
//
//                }
//                break outerloop;
//            }
//
//        }
//    }
        // Move specified number of places on the same side
        if(piece.getInnerLocation() + card < 16){
            piece.setMovesLeft(-card);
            piece.setInnerLocation(piece.getInnerLocation() + card);
            piece.setMovesLeft(piece.getMovesLeft() - card);
        }
        // Move piece to next side, add remaining moves left
        else{
            piece.setMovesLeft(-card);
            card -= (16 - piece.getInnerLocation());
            piece.setBoardSide(changeBoardSide(piece.getBoardSide(), true));
            piece.setInnerLocation(card);
        }
        checkSlide(piece);
}

    /**
     * Changes the side of the board a piece is on
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

    void movePieceBackWard( GamePiece piece, int card){
        // Move specified number of places on the same side
        if(piece.getInnerLocation() - card > 0){
            piece.setMovesLeft(+card);
            piece.setInnerLocation(piece.getInnerLocation() - card);
        }
        // Move piece to next side, add remaining moves left
        else{
            piece.setMovesLeft(+card);
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

    //This method check if the piece is needed to change tile
    //tile list, a piece, a card, two int variables for the nested loops are passed as parameters
    //Since each tile has 16 index, we calculate the available move in the tile that the piece is on by
    //using the last index of the tile 15 - the index that the piece is currently on.
    boolean checkChangeTile(GamePiece[][] tileList, GamePiece piece, int card, int i, int j){
      //temporary move variable for card
        int move = card;
        boolean check = false;
        int availableMove = 15 - j;
        if(move >= availableMove){
            check = true;
         } else{
            check = false;
             }

        return check;
    }

    boolean checkChangeTileBackWard( int move, int j){
        boolean check = false;
        if(move == 4 && j <= 3){
            check = true;
        } else if(move == 10 && j == 0){
            check = true;
        }

        return check;
    }

//    //This method check if the piece is about to get into home or not
//    //tile list, a piece, a card, two int variables for the nested loops are passed as parameters
//    //if the next tile or i + 1 equal the piece color or the piece is currently on the tile has the same color
//    // and the piece has to be less or equal than index 2 since after 2, it needs to move up to home, return true
//    // otherwise, false
//    boolean checkHomeTile(GamePiece[][] tileList, GamePiece piece, int i, int j){
//        boolean check = false;
//        int test = tileList[i][j].getColor();
//        if(i + 1 == tileList[i][j].getColor() || (tileList[i][j].getColor() == i && j <= 2) || (i == 3 && tileList[i][j].getColor() == 0)){
//            check = true;
//        } else{
//            check = false;
//        }
//
//        return check;
//    }

    //Temporary variable i to represent the number in card
    // if card is either 1 or 2, get the board side of the piece
    // check if the spot to get out is occupy or not, if its not
    // add the piece to the spot.
    void homeGetOut( int card, Enums.Color color, boolean player){

        if (card == 1 || card == 2) {
            if (player) {
                for (GamePiece piece : playerPieces) {
                    // Move a piece out of home if there is one
                    if (piece.getBoardSide() == -1) {
                        piece.setBoardSide(piece.getColor().getSide());
                        break;
                    }
                }
            }
            // If the piece is an opponents
            else {
                for (GamePiece[] opponent : opponentsPieces) {
                    // Find the opponent's array
                    if (opponent[0].getColor() == color) {
                        for (GamePiece piece : opponent) {
                            // Move a piece out of home if there is one
                            if (piece.getBoardSide() == -1) {
                                piece.setBoardSide(piece.getColor().getSide());
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    void checkBump(GamePiece piece, int card, boolean backWard){
//      //temproray variable for card
//        int move = card;
//        boolean checkBump = false;
//        for(int i = 0; i < 4; i++){
//            for(int j = 0; j < 16; j++){
//                //If we find a a piece that we are looking for, we will check if it needs to change tile or if it needs to go home
//                //as you pick up the card. We are doing this by having 2 boolean variables that take boolean from checkChangeTile
//                //and checkhomeTile. We remove the piece from its current position.
//                if(tileList[i][j] == piece){
//                    boolean checkChangeTile = checkChangeTile(tileList, piece, card, i, j);
//                    boolean checkHomeTile = checkHomeTile(tileList, piece, i, j);
//                    //If the piece needs to change tile
//                    if(checkChangeTile == true){
//                        //We calculate the move that need to move
//                        int needMove = changePiecePos(tileList, piece, card, i, j);
//                        //If checkHomeTile is true but the move makes the piece not get into home yet
//                        //We check if there anything ahead to bump, if yes, return true, else false
//                        if(checkHomeTile == true && needMove < 2){
//                            if(tileList[i+1][needMove] != null){
//                                checkBump = true;
//                            }
//                        }
//                        //If checkHomeTile is false
//                        //We check if there anything ahead to bump, if yes, return true, else false
//                        else if(checkHomeTile == false){
//                            if(tileList[i+1][needMove] != null){
//                                checkBump = true;
//                            }
//                        }
//
//                    }
//                    else{
//                        if((move == 4 && backWard == true) || (move == 10 && backWard == true)){
//                        int needMove = 0;
//                        int availableMove = 0;
//                        boolean checkChangeTileBackWard = checkChangeTileBackWard(move, j);
//                        if(checkChangeTileBackWard == true){
//                            switch (move){
//                                case 4:
//                                    availableMove = 4 - j;
//                                    needMove = 16 - availableMove;
//                                    if(tileList[i][needMove] != null){
//                                        checkBump = true;
//                                    }
//                                    break;
//                                case 10:
//                                    availableMove = 1 - j;
//                                    needMove = 16 - availableMove;
//                                    if(tileList[i][needMove] != null){
//                                        checkBump = true;
//                                    }
//                                    break;
//                            }
//                        } else{
//                        switch (move){
//                            case 4:
//                                needMove = j - 4;
//                                if(tileList[i][needMove] != null){
//                                    checkBump = true;
//                                }
//                                break;
//                            case 10:
//                                needMove = j - 1;
//                                if(tileList[i][needMove] != null){
//                                    checkBump = true;
//                                }
//                                break;
//                        }
//
//                    }
//                        }
//                        else{
//                            int needMove = j + move;
//                            if(tileList[i][needMove] != null){
//                                checkBump = true;
//                            }
//                        }
//                }
//                    break;
//            }
//                }
//        }

    }


    public GamePiece[][] getTileList() {
        return tileList;
    }
}





