package Game;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test_SaveGameState {
    static void TestSave(GameState gt, Controller con) throws IOException{
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        
//        File file = new File("C:\\Users\\phand\\Desktop\\CS205\\Final Project\\Sorry\\" + timeStamp +".txt");
        File file = new File("./saves/"+ timeStamp +".txt");
        String[] arr = gt.loadOptions();
        
//        gt.saveOptions(timeStamp, arr.length);
                    
        gt.saveGameDataToFile(con);
    }
    
    static Controller TestLoadGameBoard(GameState gt) throws ClassNotFoundException, IOException{
//        File file = new File("C:\\Users\\phand\\Desktop\\CS205\\Final Project\\Sorry\\save.txt");
        File file = new File("./saves/" + gt.loadOptions()[0] + ".txt");
        Controller con2 = gt.loadControllerFromFile(file);
        return con2;
    }
   
    public static void main (String[] args) throws ClassNotFoundException, IOException {
         Controller con = new Controller();
         GameState gt = new GameState();
         
         TestSave(gt, con);
         Controller gt2;
         gt2 = TestLoadGameBoard(gt);

         System.out.println();
        
    }
}
