package Game;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test_SaveGameState {
    static void TestSave(GameState gt, Controller con) throws IOException{
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        
//        File file = new File("C:\\Users\\phand\\Desktop\\CS205\\Final Project\\Sorry\\" + timeStamp +".txt");
        File file = new File("./saves/" + timeStamp +".txt");
        
        String[] arr = gt.loadOptions();
        
        gt.saveOptions(timeStamp, arr.length);
                    
        gt.saveGameDataToFile(con);
    }

    // Test load game from a text file
    static Controller TestLoadGameBoard(GameState gt) throws ClassNotFoundException, IOException {
        String ap_time = "2018.04.25.21.33.28";
        String jz_time = "2018.04.26.10.56.07";
        Controller con2 = gt.loadControllerFromFile(ap_time);
        return con2;
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        Controller con = new Controller();
        GameState gt = new GameState();

        TestSave(gt, con);
        // Load a saved controller to a new controller
        Controller gt2;
        gt2 = TestLoadGameBoard(gt);

         System.out.println();
    }
}
