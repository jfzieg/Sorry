package Game;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Initialize menu overlays
        MenuControllers menus = new MenuControllers();
        Controller game = new Controller();

        // Initialize game from load or new game, set references for logic loop <-> event handlers
//        game.setupNewGame();
        menus.setController(game);
//        game.setMenuControllers(menus);


        Parent root = new StackPane(menus.startMenu(), menus.newGameMenu(), menus.loadMenu(), menus.leaderboardMenu(), menus.helpMenu(), menus.endMenu(), menus.gameBoard());
        menus.getMenus().get(0).toFront();

        primaryStage.setTitle("Sorry!");
        primaryStage.setScene(new Scene(root, Settings.X_SIZE, Settings.Y_SIZE));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
