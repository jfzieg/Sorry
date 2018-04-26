package Game;

import java.io.IOException;

public class Test_SaveGameState {

    // Test save game to text file
    static void TestSave(GameState gt, Controller con) throws IOException {
        gt.saveGameDataToFile(con);
    }

    // Test load game from a text file
    static Controller TestLoadGameBoard(GameState gt) throws ClassNotFoundException, IOException {
        String time = "2018.04.25.21.33.28";
        Controller con2 = gt.loadControllerFromFile(time);
        return con2;
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        Controller con = new Controller();
        GameState gt = new GameState();

        TestSave(gt, con);
        // Load a saved controller to a new controller
        Controller gt2;
        gt2 = TestLoadGameBoard(gt);

    }
}
