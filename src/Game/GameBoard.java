package Game;

import java.util.ArrayList;

/**
 * @author phand
 * @date 2018-03-14
 * @version 1.0
 */

public class GameBoard {

    ArrayList<ArrayList<GamePiece>> createTilesList(){
        ArrayList<ArrayList<GamePiece>> tileList = new ArrayList<ArrayList<GamePiece>>(4);
        for(int i = 0; i < 4; i++){
            ArrayList<GamePiece> tile = new ArrayList<GamePiece>(16);
            tileList.add(tile);
        }
        return tileList;
    }

    ArrayList<ArrayList<GamePiece>> createHomeList(){
        ArrayList<ArrayList<GamePiece>> homeList = new ArrayList<ArrayList<GamePiece>>();
        for(int i = 0; i < 4; i++){
            ArrayList<GamePiece> home = new ArrayList<GamePiece>(6);
            homeList.add(home);
        }
        return homeList;
    }

    void homeMove(ArrayList<ArrayList<GamePiece>> tileList, ArrayList<ArrayList<GamePiece>> homeList, GamePiece piece){
        int homeIndex = piece.getBoardSide();
        int i = 0;

    }

    void slideCheck(ArrayList<ArrayList<GamePiece>> tileList, ArrayList<ArrayList<GamePiece>> homeList, GamePiece piece){
        for(int i = 0; i < 4; i++){
            if(tileList.get(i).get(2).equals(piece) && piece.getBoardSide() == i){
                tileList.get(i).remove(2);
                tileList.get(i).add(5, piece);
            }
            if(tileList.get(i).get(9).equals(piece) && piece.getBoardSide() == i){
                tileList.get(i).remove(9);
                tileList.get(i).add(13, piece);
            }
        }
    }

    void movePiece(ArrayList<ArrayList<GamePiece>> tileList, ArrayList<ArrayList<GamePiece>> homeList, GamePiece piece){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 16; j++){
                if(tileList.get(i).get(j).equals(piece)){
                    tileList.get(i).remove(j);
//                tileList.add(index, piece);
                }
            }
        }
    }

    int changeTile(ArrayList<ArrayList<GamePiece>> tileList, GamePiece piece, Card card){
        int move = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 16; j++){
                if(tileList.get(i).get(j).equals(piece)){
                    int availableMove = 16 - j;
                    if(move >= availableMove){
                        tileList.get(i).remove(j);
                        tileList.get(i+1).add(0, piece);
                        int needMove = move - availableMove;
                        return needMove;
                    }
                } else{
                    return move;
                }
            }
        }
        return move;
    }

    /**
     * @param tileList
     * @param piece
     * @param card
     */
    void homeGetOut(ArrayList<ArrayList<GamePiece>> tileList, GamePiece piece, Card card){
        int i = 1;
        if(i == 1 || i == 2){
            int homeIndex = piece.getBoardSide();
            if(tileList.get(homeIndex).get(5) == null){
                tileList.get(homeIndex).add(5, piece);
            }
            else{
                System.out.println("There is a piece in the place");
            }
        }
    }
}





