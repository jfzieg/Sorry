package Game;

import java.io.File;
import java.io.IOException;

public class Test_SaveGameState {
    
    static void TestSave(GameState gt, GameBoard gb){
        File file = new File("C:\\Users\\phand\\Desktop\\CS205\\Final Project\\Sorry\\save.txt");
        gt.saveGameDataToFile(file, gb);
    }
    
    static void TestLoad(GameState gt, GameBoard gb2) throws ClassNotFoundException, IOException{
        File file = new File("C:\\Users\\phand\\Desktop\\CS205\\Final Project\\Sorry\\save.txt");
        gb2 = gt.loadGameDataFromFile(file);
    }
    
    static void TestNewGameBoard(GameBoard gb2){
        GamePiece[][] tileListTest = gb2.getTileList();
        GamePiece[][] homeListTest = gb2.getHomeList();
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 16; j++){
                if(tileListTest != null){
                    System.out.println("Color: " + tileListTest[i][j].getColor() + ", " + "position i = " + i + ", " + 
                "position j = " + j);
                }
            }
        }
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 6; j++){
                if(homeListTest != null){
                    System.out.println("Color: " + homeListTest[i][j].getColor() + ", " + "position i = " + i + ", " + 
                "position j = " + j);
                }
            }
        }
    }
    
    public static void main (String[] args) {
        GamePiece[][] tileList = new GamePiece[4][16];
        GamePiece[][] homeList = new GamePiece[4][6];
        
        //Add some random piece to tileList and homeList
        GamePiece p = new GamePiece(Enums.Color.RED);
        GamePiece p2 = new GamePiece(Enums.Color.RED);
        GamePiece p3 = new GamePiece(Enums.Color.GREEN);
        GamePiece p4 = new GamePiece(Enums.Color.GREEN);
        tileList[2][5] = p;
        tileList[3][7] = p2;
        homeList[2][4] = p3;
        homeList[2][3] = p4;
        
        GameBoard gb = new GameBoard(tileList, homeList);
        
        GameState gt = new GameState();
        //Save game state to a text file
//        TestSave(gt, gb);
        
        //Load game state from a text file
        //Load game board to a new empty gameboard to make sure load game is working
        GamePiece[][] tileList2 = new GamePiece[4][16];
        GamePiece[][] homeList2 = new GamePiece[4][6];
        GameBoard gb2 = new GameBoard(tileList2, homeList2);
         try {
            TestLoad(gt, gb2);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
         TestNewGameBoard(gb2);
        
        
    }
}
