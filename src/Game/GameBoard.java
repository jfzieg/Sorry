package Game;

import java.util.ArrayList;

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

void homeCheck(ArrayList<ArrayList<GamePiece>> tileList, ArrayList<ArrayList<GamePiece>> homeList, GamePiece piece){
    for(int i = 0; i < 4; i++){
        if(tileList.get(i).get(2).equals(piece)){
            
        }
    }
}

void slideCheck(ArrayList<ArrayList<GamePiece>> tileList, ArrayList<ArrayList<GamePiece>> homeList, GamePiece piece){
    
}

void movePiece(ArrayList<ArrayList<GamePiece>> tileList, ArrayList<ArrayList<GamePiece>> homeList, GamePiece piece){
    
}






}
