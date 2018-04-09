package Game;

import java.util.ArrayList;

/**
 * @author Anh Phan
 * @date 2018-03-14
 * @version 1.0	
 */


public class GameBoard {
    //Variables for game board
    private GamePiece[][] tileList;
    private GamePiece[][] homeList;
    
    //Constructor for GameBoard
    public GameBoard(GamePiece[][] tileList, GamePiece[][] homeList){
        this.tileList = tileList;
        this.homeList = homeList;
    }
    
    
//void homeMove(GamePiece[][] tileList, GamePiece[][] homeList, GamePiece piece){
//        int homeIndex = piece.getBoardSide();
//        int i = 0;
//        
//}

//Slide method
//tile list and a piece of a game are passed as parameters
//Go through loop to check all the tiles. If at index 2 or 9 of a tile contains a piece and a tile is not the same
//color as the piece, move the piece to index 5 or 13 of that tile
void slide(GamePiece[][] tileList, GamePiece piece){
    for(int i = 0; i < 4; i++){
        if(tileList[i][2].equals(piece) && piece.getBoardSide() != i){
            tileList[i][2] = null;
            tileList[i][5] = piece;
        }
        if(tileList[i][9].equals(piece) && piece.getBoardSide() != i){
            tileList[i][9] = null;
            tileList[i][13] = piece;
        }
    }
}
//Move Piece method or maybe just move forward method
void movePiece(GamePiece[][] tileList, GamePiece[][] homeList, GamePiece piece, Card card){
    //temporary move variable for card
    int move = 0;
   //Go through a nested loop
   //First loop that go through 4 tiles
   //Second loop that go through each slot in each tile
    for(int i = 0; i < 4; i++){
        for(int j = 0; j < 16; j++){
           //If we find a a piece that we are looking for, we will check if it needs to change tile or if it needs to go home
           //as you pick up the card. We are doing this by having 2 boolean variables that take boolean from checkChangeTile
           // and checkhomeTile. We remove the piece from its current position.
            if(tileList[i][j].equals(piece)){
                boolean checkChangeTile = checkChangeTile(tileList, piece, card, i, j);
                boolean checkHomeTile = checkHomeTile(tileList, piece, i, j);
                tileList[i][j] = null;
                //If the piece needs to change tile
                if(checkChangeTile == true){
                    //We calculate the move that need to move 
                    int needMove = changeTile(tileList, piece, card, i, j);
                    //Then we have to check if checkHomeTile is true and the move it needs to move is good in home
                    if(checkHomeTile == true && needMove > 2 && needMove <= 8){
                        //calculate home move by removing 2 index of the new tile and add the piece straight to some index
                        //in home
                        int homeMove = needMove - 2;
                        homeList[i][homeMove - 1] = piece;
                    } else{
                     //Else we just add the piece to an index of next tile           
                    tileList[i+1][needMove] = piece;
                    }
                }
                else{
                    //If everything up there is failed, we just move the piece in its current tile
                    int needMove = j + move;
                    tileList[i][needMove] = piece;
                }
            }
        }
    }
}

//Only get called if checkChangeTile return true
//temporary variable move represent the card number
//Since each tile has 16 index, we calculate the available move in the tile that the piece is on by
//using the last index of the tile 15 - the index that the piece is currently on and we have availableMove
//Then we have the number of card minus available; therefore, we will know how much index we need to move in the new tile
int changeTile(GamePiece[][] tileList, GamePiece piece, Card card, int i, int j){
  //temporary move variable for card
    int move = 0;
    int needMove = 0;
            if(tileList[i][j].equals(piece)){
                int availableMove = 15 - j;
                    needMove = move - availableMove;
        }
    return needMove;
}

//This method check if the piece is needed to change tile
//tile list, a piece, a card, two int variables for the nested loops are passed as parameters
//Since each tile has 16 index, we calculate the available move in the tile that the piece is on by
//using the last index of the tile 15 - the index that the piece is currently on. 
boolean checkChangeTile(GamePiece[][] tileList, GamePiece piece, Card card, int i, int j){
  //temporary move variable for card
    int move = 0;
    boolean check = false;
            if(tileList[i][j].equals(piece)){
                int availableMove = 15 - j;
                if(move >= availableMove){
                    check = true;
                } else{
                    check = false;
                }
            }
    
    return check;
}

//This method check if the piece is about to get into home or not
//tile list, a piece, a card, two int variables for the nested loops are passed as parameters
//if the next tile or i + 1 equal the piece color or the piece is currently on the tile has the same color
// and the piece has to be less or equal than index 2 since after 2, it needs to move up to home, return true
// otherwise, false
boolean checkHomeTile(GamePiece[][] tileList, GamePiece piece, int i, int j){
    boolean check = false;
    if(i + 1 == tileList[i][j].color.side || (tileList[i][j].color.side == i && j <= 2)){
        check = true;
    } else{
        check = false;
    }
    
    return check;
}
/**
 * @param tileList
 * @param piece
 * @param card
 */
//Temporary variable i to represent the number in card
// if card is either 1 or 2, get the board side of the piece
// check if the spot to get out is occupy or not, if its not
// add the piece to the spot.
void homeGetOut(GamePiece[][] tileList, GamePiece piece, Card card){
    int i = 1;
    if(i == 1 || i == 2){
        int homeIndex = piece.getBoardSide();
        if(tileList[homeIndex][5] == null){
            tileList[homeIndex][5] = piece;
        }
        else{
            System.out.println("There is a piece in the place");
    }
}
    
}


boolean bump(GamePiece[][] tileList, GamePiece piece, Card card){
    //temproray variable for card
    int move = 0;
    boolean checkBump = false;
    //Go through a nested loop
    //First loop that go through 4 tiles
    //Second loop that go through each slot in each tile
    for(int i = 0; i < 4; i++){
        for(int j = 0; j < 16; j++){
            //If we find a a piece that we are looking for, we will check if it needs to change tile or if it needs to go home
            //as you pick up the card. We are doing this by having 2 boolean variables that take boolean from checkChangeTile
            //and checkhomeTile. We remove the piece from its current position.
            if(tileList[i][j].equals(piece)){
                boolean checkChangeTile = checkChangeTile(tileList, piece, card, i, j);
                boolean checkHomeTile = checkHomeTile(tileList, piece, i, j);
                //If the piece needs to change tile
                if(checkChangeTile == true){
                    //We calculate the move that need to move 
                    int needMove = changeTile(tileList, piece, card, i, j);
                    //If checkHomeTile is true but the move makes the piece not get into home yet
                    //We check if there anything ahead to bump, if yes, return true, else false
                    if(checkHomeTile == true && needMove < 2){
                        if(tileList[i+1][needMove] != null){
                            checkBump = true;
                            tileList[i+1][needMove] = null;
                            tileList[i+1][needMove] = piece;
                        } else{
                            tileList[i+1][needMove] = piece;
                        }
                        }
                    //If checkHomeTile is false
                    //We check if there anything ahead to bump, if yes, return true, else false
                    else if(checkHomeTile == false){
                        if(tileList[i+1][needMove] != null){
                            checkBump = true;
                            tileList[i+1][needMove] = null;
                            tileList[i+1][needMove] = piece;
                        } else{
                            tileList[i+1][needMove] = piece;
                        }
                    }
                }
                //Else check if bump in the same tile
                else{
                    int needMove = j + move;
                    if(tileList[i][needMove] != null){
                        checkBump = true;
                        tileList[i][needMove] = null;
                        tileList[i][needMove] = piece;
                    } else{
                        tileList[i][needMove] = piece;
                    }
                }
            }
        }
    }
    return checkBump;
}

//void add(GamePiece[][] tileList, GamePiece[][] homeList, GamePiece piece){
//    tileList[2][6] = piece;
//}
//void add2(GamePiece[][] tileList, GamePiece[][] homeList, GamePiece piece){
//    tileList[1][5] = piece;
//}
}





