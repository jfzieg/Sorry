package Game;

import javafx.application.Platform;
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
    public BorderPane startMenu() {
        //Initialize pane and prefs
        BorderPane pane = new BorderPane();
        pane.setBackground(new Background(new BackgroundFill(Settings.BACKGROUND, null, null)));

        //Initalize container and prefs
        VBox options = new VBox();
        options.setLayoutY(Settings.Y_SIZE);
        options.setSpacing(Settings.Y_SIZE * .05);
        options.setPadding(new Insets(Settings.X_SIZE * .01));

        //Initalize title TEXT and buttons
        Text title = makeTitle("Sorry!");
        Button start = newGameButton();
        Button load = loadButton();
        Button leaderboard = leaderboardButton();
        Button help = helpButton();
        Button quit = quitButton();

        //Add the buttons and title to the pane
        options.getChildren().addAll(title, start.getText(), load.getText(), leaderboard.getText(), help.getText(), quit.getText());
        pane.setLeft(options);

        startMenu = pane;
        menus.add(pane);

        return pane;
    }

    /**
     * @return
     */
    public BorderPane newGameMenu() {
        //Initialize pane and prefs
        BorderPane pane = new BorderPane();
        pane.setBackground(new Background(new BackgroundFill(Settings.BACKGROUND, null, null)));

        //Initalize container and prefs
        VBox options = new VBox();
        options.setLayoutY(Settings.Y_SIZE);
        options.setSpacing(Settings.Y_SIZE * .05);
        options.setPadding(new Insets(Settings.X_SIZE * .01));

        //Initalize title text and buttons
        Text title =  makeTitle("New Game!");
        Button main = startButton();

        options.getChildren().addAll(title, main.getText());
        pane.setLeft(options);

        newGameMenu = pane;
        menus.add(pane);

        return pane;
    }

    /**
     * @return
     */
    public BorderPane gameBoard() {
        //Initialize pane and prefs
        BorderPane pane = new BorderPane();
        pane.setBackground(new Background(new BackgroundFill(Settings.BACKGROUND, null, null)));

        GridPane gameboard = new GridPane();

        gameBoard = pane;
        menus.add(pane);

        return pane;
    }

    /**
     * @return
     */
    public BorderPane endMenu() {
        //Initialize pane and prefs
        BorderPane pane = new BorderPane();
        pane.setBackground(new Background(new BackgroundFill(Settings.BACKGROUND, null, null)));

        //Initalize container and prefs
        VBox options = new VBox();
        options.setLayoutY(Settings.Y_SIZE);
        options.setSpacing(Settings.Y_SIZE * .05);
        options.setPadding(new Insets(Settings.X_SIZE * .01));

        Text title = makeTitle("You Win! (Filler)");

        Button main = startButton();

        Button quit = quitButton();

        options.getChildren().addAll(title, main.getText(), quit.getText());
        pane.setLeft(options);

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
        pane.setBackground(new Background(new BackgroundFill(Settings.BACKGROUND, null, null)));
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pane.setMaxWidth(Settings.X_SIZE);
        //Initialize container and prefs
        VBox options = new VBox();
        options.setBackground(new Background(new BackgroundFill(Settings.BACKGROUND, null, null)));
        options.setPrefSize(Settings.X_SIZE, Settings.Y_SIZE);
        options.setSpacing(Settings.Y_SIZE * .05);
        options.setPadding(new Insets(Settings.X_SIZE * .01));

        //Initalize title TEXT and buttons
        Text title = makeTitle("Help!");

        Button main = startButton();

        // Instructions go here
        Text filler = new Text("Objectives\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Integer hendrerit nunc a tortor lacinia, vel cursus massa fermentum. Aenean interdum ut turpis eu bibendum. Mauris at rhoncus arcu. Sed quis faucibus quam. Donec faucibus, urna vitae lacinia volutpat, nibh nulla tincidunt massa, ultrices eleifend augue massa ac odio. Aliquam vestibulum justo vitae nisi consequat fringilla. Pellentesque nisi tortor, dictum ullamcorper aliquam ut, scelerisque eu lacus. Duis quis elit magna. Integer ipsum arcu, laoreet sit amet nisl vel, imperdiet egestas justo.\n" +
                "\n" +
                "Gameplay\nProin malesuada nulla sed purus fermentum commodo. Nullam eget erat tristique tortor cursus accumsan. Aenean quam dolor, bibendum quis orci ac, vehicula pulvinar nisl. Sed dui libero, laoreet eu bibendum at, aliquam vel ex. In sagittis tempor ante a rhoncus. Vestibulum a metus id nunc auctor finibus a vel odio. Sed tincidunt turpis dui, a mattis sapien dictum eu. Curabitur augue nisl, molestie nec tristique at, vestibulum sed nisi. Nam tincidunt elit nec iaculis vehicula. Pellentesque condimentum nibh ipsum, nec egestas sapien luctus et. Vivamus rhoncus turpis dolor, elementum dignissim nulla imperdiet in. Proin vitae venenatis massa. Sed hendrerit cursus sodales. Nulla neque nulla, fermentum et interdum vitae, pellentesque id neque. Nam nec turpis vitae sem efficitur consequat.\n" +
                "\n" +
                "Rules\nVivamus iaculis augue sed nibh vulputate tempus. Phasellus a finibus turpis. Mauris tincidunt mollis arcu in interdum. Nullam cursus viverra malesuada. Pellentesque lacinia lacinia velit, ac pellentesque nulla commodo vel. Nunc venenatis orci vitae nulla finibus, eu sollicitudin nisl elementum. Donec in mi urna. Nullam consequat libero lectus, ac iaculis ex dignissim vitae. Aliquam malesuada eleifend semper. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed iaculis nisi quis ipsum euismod lacinia.\n" +
                "\n" +
                "Cras fringilla ligula et convallis ultricies. Proin aliquet non enim vitae facilisis. Phasellus sed tempor tortor. Suspendisse nibh eros, finibus elementum pulvinar et, hendrerit eget tortor. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Fusce viverra ultricies lacus, et interdum massa vehicula a. Maecenas quis dignissim purus. Etiam tempor viverra malesuada. Etiam iaculis, felis quis pulvinar eleifend, nisi eros tempor leo, fermentum vehicula ex odio a mauris. Sed sollicitudin arcu sem, et facilisis eros tempor sit amet.");
        filler.setFont(Settings.SMALL_FONT);
        filler.setFill(Settings.TEXT);
        filler.setWrappingWidth(Settings.X_SIZE * .98);

        options.getChildren().addAll(title, filler, main.getText());
        pane.setContent(options);

        helpMenu = pane;
        menus.add(pane);

        return pane;
    }

    public ArrayList<Node> getChildren() {
        return menus;
    }

    private Text makeTitle(String text){
        Text title = new Text(text);
        title.setFont(Settings.FONT);
        title.setFill(Settings.TEXT);
        title.setStroke(Settings.TEXT);
        title.setStrokeWidth(2);

        return title;
    }

    private Button startButton(){
        Button main = new Button("Main Menu", Settings.TEXT_SIZE * .6);
        setButtonEventHandlers(main);
        setStartMenuEventHandler(main);

        return main;
    }

    private Button newGameButton(){
        Button start = new Button("New Game", Settings.TEXT_SIZE * .6);
        setButtonEventHandlers(start);
        setNewGameMenuEventHandler(start);

        return start;
    }

    private Button loadButton(){
        Button load = new Button("Load Game", Settings.TEXT_SIZE * .6);
        setButtonEventHandlers(load);

        return load;
    }

    private Button leaderboardButton(){
        Button leaderboard = new Button("Leaderboard", Settings.TEXT_SIZE * .6);
        setButtonEventHandlers(leaderboard);

        return leaderboard;
    }

    private Button helpButton(){
        Button help = new Button("Help", Settings.TEXT_SIZE * .6);
        setButtonEventHandlers(help);
        setHelpMenuEventHandler(help);

        return help;
    }

    private Button quitButton(){
        Button quit = new Button("Quit", Settings.TEXT_SIZE * .6);
        setButtonEventHandlers(quit);
        setQuitEventHandler(quit);

        return quit;
    }

    /**
     * @param button
     */
    private void setButtonEventHandlers(Button button) {
        Text text = button.getText();
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
        Text text = button.getText();
        button.getText().setOnMouseReleased(new EventHandler() {
            @Override
            public void handle(Event event) {
                startMenu.toFront();
                startMenu.requestFocus();
            }
        });
    }

    private void setNewGameMenuEventHandler(Button button) {
        Text text = button.getText();
        button.getText().setOnMouseReleased(new EventHandler() {
            @Override
            public void handle(Event event) {
                newGameMenu.toFront();
                newGameMenu.requestFocus();
            }
        });
    }

    private void setLeaderboardEventHandler(Button button) {
        Text text = button.getText();
        button.getText().setOnMouseReleased(new EventHandler() {
            @Override
            public void handle(Event event) {
//                leaderboard.toFront();
            }
        });
    }

    private void setHelpMenuEventHandler(Button button) {
        Text text = button.getText();
        button.getText().setOnMouseReleased(new EventHandler() {
            @Override
            public void handle(Event event) {
                helpMenu.setVvalue(0);  // Reorients window to top of page
                helpMenu.toFront();
            }
        });
    }

    private void setQuitEventHandler(Button button) {
        Text text = button.getText();
        button.getText().setOnMouseReleased(new EventHandler() {
            @Override
            public void handle(Event event) {
                Platform.exit();
            }
        });
    }

    public class Button {
        private Text text;
        private Color c = Settings.TEXT;
        private int size;

        public Button(String innerText, double size) {
            text = new Text(innerText);
            text.setFont(new Font("Arial Rounded MT Bold", size));
            text.setFill(Settings.TEXT);
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
