package Game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Test_SaveGameState {
    
    static void TestSave(GameState gt, GameBoard gb){
        File file = new File("C:\\Users\\phand\\Desktop\\CS205\\Final Project\\Sorry\\save.txt");
        gt.saveGameDataToFile(file, gb);
    }
    
    static GameBoard TestLoadGameBoard(GameState gt) throws ClassNotFoundException, IOException{
        File file = new File("C:\\Users\\phand\\Desktop\\CS205\\Final Project\\Sorry\\save.txt");
        GameBoard gb2 = gt.loadGameBoardFromFile(file);
        return gb2;
    }
   
    
    static void TestNewGameBoard(GameBoard gb2){
        GamePiece[][] tileListTest = gb2.getTileList();
        GamePiece[][] homeListTest = gb2.getHomeList();
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 16; j++){
                if(tileListTest[i][j] != null){
                    System.out.println("Color: " + tileListTest[i][j].getColor() + ", " + "position i = " + i + ", " + 
                "position j = " + j);
                }
            }
        }
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 6; j++){
                if(homeListTest[i][j] != null){
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
        
//        Card c = new Card(Enums.CardType.EIGHT);
//        Card c2 = new Card(Enums.CardType.SEVEN);
//        ArrayList<Card> card = new ArrayList<Card>();
//        card.add(c);
//        card.add(c2);
        
        GamePiece p5 = new GamePiece(Enums.Color.BLUE);
        GamePiece p6 = new GamePiece(Enums.Color.BLUE);
        GamePiece[] player = new GamePiece[4];
        player[0] = p5;
        player[1] = p6;
        
        GamePiece[] opponent1 = new GamePiece[4];
        GamePiece[] opponent2 = new GamePiece[4];
        ArrayList<GamePiece[]> opponent = new ArrayList<GamePiece[]>();
        opponent.add(opponent1);
        opponent.add(opponent2);
        
        
        GameState gt = new GameState();
        //Save game state to a text file
        TestSave(gt, gb);
        
        //Load game state from a text file
        //Load game board to a new empty gameboard to make sure load game is working
        GamePiece[][] tileList2 = new GamePiece[4][16];
        GamePiece[][] homeList2 = new GamePiece[4][6];
        
         try {
            GameBoard gb2 = TestLoadGameBoard(gt);

            TestNewGameBoard(gb2);
            
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
         
        
        
    }
}
