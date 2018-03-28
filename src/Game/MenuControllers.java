package Game;

import javafx.application.Platform;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.*;

public class MenuControllers {
    private ArrayList<Node> menus = new ArrayList<>();
    private Pane startMenu;
    private Pane newGameMenu;
    private Pane gameBoard;
    private Pane endMenu;
    private ScrollPane helpMenu;


    /**
     * @return
     */
    public Pane startMenu() {
        //Initialize pane and prefs
        BorderPane pane = new BorderPane();
        pane.setBackground(new Background(new BackgroundFill(Settings.background, null, null)));

        //Initalize container and prefs
        VBox options = new VBox();
        options.setLayoutY(Settings.Y_SIZE);
        options.setSpacing(Settings.Y_SIZE * .05);
        options.setPadding(new Insets(Settings.X_SIZE * .01));

        //Initalize title text and buttons
        Text title = new Text("Sorry!");
        title.setFont(new Font("Arial Rounded MT Bold", Settings.TEXT_SIZE));
        title.setFill(new Color(1, 1, 1, .8));
        title.setStroke(new Color(1, 1, 1, 1));
        title.setStrokeWidth(2);

        Button start = new Button("New Game", Settings.TEXT_SIZE * .6);
        setButtonEventHandlers(start);
        setNewGameMenuEventHandler(start);

        Button load = new Button("Load Game", Settings.TEXT_SIZE * .6);
        setButtonEventHandlers(load);

        Button leaderboard = new Button("Leaderboard", Settings.TEXT_SIZE * .6);
        setButtonEventHandlers(leaderboard);

        Button help = new Button("Help", Settings.TEXT_SIZE * .6);
        setButtonEventHandlers(help);

        Button quit = new Button("Quit", Settings.TEXT_SIZE * .6);
        setButtonEventHandlers(quit);
        setQuitEventHandler(quit);

        //Add the buttons and title to the pane
        options.getChildren().addAll(title, start.text, load.text, leaderboard.text, help.text, quit.text);
        pane.setLeft(options);

        startMenu = pane;
        menus.add(pane);

        return pane;
    }

    /**
     * @return
     */
    public Pane newGameMenu() {
        //Initialize pane and prefs
        BorderPane pane = new BorderPane();
        pane.setBackground(new Background(new BackgroundFill(Settings.background, null, null)));

        //Initalize container and prefs
        VBox options = new VBox();
        options.setLayoutY(Settings.Y_SIZE);
        options.setSpacing(Settings.Y_SIZE * .05);
        options.setPadding(new Insets(Settings.X_SIZE * .01));

        //Initalize title text and buttons
        Text title = new Text("New Game!");
        title.setFont(new Font("Arial Rounded MT Bold", Settings.TEXT_SIZE));
        title.setFill(new Color(1, 1, 1, .8));
        title.setStroke(new Color(1, 1, 1, 1));
        title.setStrokeWidth(2);

        options.getChildren().add(title);
        pane.setLeft(options);

        newGameMenu = pane;
        menus.add(pane);

        return new Pane();
    }

    /**
     * @return
     */
    public Pane gameBoard() {
        //Initialize pane and prefs
        BorderPane pane = new BorderPane();
        pane.setBackground(new Background(new BackgroundFill(Settings.background, null, null)));

        gameBoard = pane;
        menus.add(pane);

        return new Pane();
    }

    /**
     * @return
     */
    public Pane endMenu() {
        //Initialize pane and prefs
        BorderPane pane = new BorderPane();
        pane.setBackground(new Background(new BackgroundFill(Settings.background, null, null)));

        //Initalize container and prefs
        VBox options = new VBox();
        options.setLayoutY(Settings.Y_SIZE);
        options.setSpacing(Settings.Y_SIZE * .05);
        options.setPadding(new Insets(Settings.X_SIZE * .01));

        Text title = new Text("You Win!");
        title.setFont(new Font("Arial Rounded MT Bold", Settings.TEXT_SIZE));
        title.setFill(new Color(1, 1, 1, .8));
        title.setStroke(new Color(1, 1, 1, 1));
        title.setStrokeWidth(2);


        endMenu = pane;
        menus.add(pane);

        return pane;
    }

    /**
     * @return
     */
    public ScrollPane helpMenu() {
        //Initialize pane and prefs
        ScrollPane pane = new ScrollPane();
        pane.setBackground(new Background(new BackgroundFill(Settings.background, null, null)));

        //Initalize container and prefs
        VBox options = new VBox();
        options.setLayoutY(Settings.Y_SIZE);
        options.setSpacing(Settings.Y_SIZE * .05);
        options.setPadding(new Insets(Settings.X_SIZE * .01));

        helpMenu = pane;
        menus.add(pane);

        return pane;
    }

    public ArrayList<Node> getChildren(){
        return menus;
    }

    /**
     * @param button
     */
    private void setButtonEventHandlers(Button button) {
        Text text = button.text;
        button.getText().setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                text.setFill(button.c.darker());
                text.setStroke(button.c.darker());
            }
        });

        button.getText().setOnMouseExited(new EventHandler() {
            @Override
            public void handle(Event event) {
                text.setFill(button.c.brighter());
                text.setStroke(button.c.brighter());
            }
        });

    }

    private void setStartMenuEventHandler(Button button) {
        Text text = button.text;
        button.getText().setOnMouseReleased(new EventHandler() {
            @Override
            public void handle(Event event) {
                startMenu.toFront();
                startMenu.requestFocus();
            }
        });
    }

    private void setNewGameMenuEventHandler(Button button) {
        Text text = button.text;
        button.getText().setOnMouseReleased(new EventHandler() {
            @Override
            public void handle(Event event) {
                newGameMenu.toFront();
            }
        });
    }

//    private void setLeaderboardEventHandler(Button button) {
//        Text text = button.text;
//        button.getText().setOnMouseReleased(new EventHandler() {
//            @Override
//            public void handle(Event event) {
//                leaderboard.toFront();
//            }
//        });
//    }

    private void setQuitEventHandler(Button button) {
        Text text = button.text;
        button.getText().setOnMouseReleased(new EventHandler() {
            @Override
            public void handle(Event event) {
                Platform.exit();
            }
        });
    }

    public class Button{
        private Text text;

        private Color c = new Color(1, 1, 1, .8);

        private int size;

        public Button(String innerText, double size){
            text = new Text(innerText);
            text.setFont(new Font("Arial Rounded MT Bold", size));
            text.setFill(new Color(1, 1, 1, .8));
            text.setStroke(c.brighter());
            text.setStrokeWidth(2);

        }

        public Text getText() {
            return text;
        }

        public void setText(Text text) {
            this.text = text;
        }

        public Color getColor() {
            return c;
        }

        public void setColor(Color c) {
            this.c = c;
        }
    }
}
