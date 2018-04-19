package Game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.embed.swing.JFXPanel;

public class Test_SaveGameState {
    
    static void TestSave(GameState gt, Controller c){
        File file = new File("C:\\Users\\phand\\Desktop\\CS205\\Final Project\\Sorry\\save.txt");
        gt.saveGameDataToFile(file, c);
    }
    
    static Controller TestLoadGameBoard(GameState gt) throws ClassNotFoundException, IOException{
        File file = new File("C:\\Users\\phand\\Desktop\\CS205\\Final Project\\Sorry\\save.txt");
        Controller c2 = gt.loadGameControllerFromFile(file);
        return c2;
    }
   
    
//    static void TestNewGameBoard(GameBoard gb2){
//        GamePiece[][] tileListTest = gb2.getTileList();
//        GamePiece[][] homeListTest = gb2.getHomeList();
//        
//        for(int i = 0; i < 4; i++){
//            for(int j = 0; j < 16; j++){
//                if(tileListTest[i][j] != null){
//                    System.out.println("Color: " + tileListTest[i][j].getColor() + ", " + "position i = " + i + ", " + 
//                "position j = " + j);
//                }
//            }
//        }
//        
//        for(int i = 0; i < 4; i++){
//            for(int j = 0; j < 6; j++){
//                if(homeListTest[i][j] != null){
//                    System.out.println("Color: " + homeListTest[i][j].getColor() + ", " + "position i = " + i + ", " + 
//                "position j = " + j);
//                }
//            }
//        }
//    }
    
    public static void main (String[] args) {
        
        JFXPanel jfxPanel = new JFXPanel();
        GameState gt = new GameState();
        Controller c = new Controller(true);
        //Save game state to a text file
        TestSave(gt, c);
        
        
         try {
            Controller c2 = TestLoadGameBoard(gt);
            System.out.println();
//            TestNewGameBoard(gb2);
            
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
         
        
        
    }
}
