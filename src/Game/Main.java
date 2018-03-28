package Game;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Initialize menu overlays
        MenuControllers menus = new MenuControllers();
//        Pane startMenu = menus.startMenu();
//        Pane newGameMenu = menus.newGameMenu();
//        Pane gameBoard = menus.gameBoard();
//        Pane endMenu = menus.endMenu();
//        ScrollPane helpMenu = menus.helpMenu();

        Parent root = new StackPane(menus.startMenu(), menus.newGameMenu(), menus.helpMenu(), menus.endMenu());
        menus.getChildren().get(0).toFront();

        primaryStage.setTitle("Sorry!");
        primaryStage.setScene(new Scene(root, Settings.X_SIZE, Settings.Y_SIZE));
        primaryStage.show();

//        circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
//            public void handle(MouseEvent me) {
//                System.out.println("Mouse entered");
//            }
//        });
//
//        circle.setOnMouseExited(new EventHandler<MouseEvent>() {
//            public void handle(MouseEvent me) {
//                System.out.println("Mouse exited");
//            }
//        });
//
//        circle.setOnMousePressed(new EventHandler<MouseEvent>() {
//            public void handle(MouseEvent me) {
//                System.out.println("Mouse pressed");
//            }
//        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
