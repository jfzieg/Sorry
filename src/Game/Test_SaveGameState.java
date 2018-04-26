package Game;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.embed.swing.JFXPanel;

public class Test_SaveGameState {
    static void TestSave(GameState gt, Controller con) throws IOException{

        gt.saveGameDataToFile(con);
    }

    static Controller TestLoadGameBoard(GameState gt) throws ClassNotFoundException, IOException{
        File file = new File("save.txt");
        Controller con2 = gt.loadControllerFromFile(file);
        return con2;
    }

    public static void main (String[] args) throws ClassNotFoundException, IOException {
         Controller con = new Controller();
         GameState gt = new GameState();

         TestSave(gt, con);
         Controller gt2;
//         gt2 = TestLoadGameBoard(gt);


         System.out.println();

    }
}
