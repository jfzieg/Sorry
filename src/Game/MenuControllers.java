package Game;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
    private boolean gameStart = false;
    private Controller game;

    /**
     * Start menu pane constructor
     * @return A start menu pane
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
        Text title = makeText("Sorry!", Settings.FONT);
        Button resume = resumeButton();
        Button start = newGameButton();
        Button load = loadButton();
        Button leaderboard = leaderboardButton();
        Button help = helpButton();
        Button quit = quitButton();

        Circle red = makeCircle(Settings.Y_SIZE * .1, Settings.RED);
        Circle yellow = makeCircle(Settings.Y_SIZE * .1, Settings.YELLOW);

        yellow.setCenterX(Settings.X_SIZE / 3);
        yellow.setCenterY(Settings.Y_SIZE / 3);
        red.setCenterX(Settings.X_SIZE / 3 + red.getRadius());
        red.setCenterY(Settings.Y_SIZE / 3 - red.getRadius());

        Pane graphic = new Pane();
        graphic.getChildren().addAll(red, yellow);
//        graphic.setLayoutX(300);
//        graphic.setLayoutY(300);

        //Add the buttons and title to the pane
        options.getChildren().addAll(title, resume.getText(), start.getText(), load.getText(),
                                        leaderboard.getText(), help.getText(), quit.getText());
        pane.setLeft(options);
        pane.setCenter(graphic);
        pane.setAlignment(graphic, Pos.CENTER);

        //Add references for object retrieval
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

        VBox innerOptions = new VBox();
        innerOptions.setLayoutY(Settings.Y_SIZE);
        innerOptions.setSpacing(Settings.Y_SIZE * .05);
        innerOptions.setPadding(new Insets(Settings.X_SIZE * .01));

        HBox colorChoice = new HBox();
        colorChoice.setLayoutY(Settings.Y_SIZE);
        colorChoice.setSpacing(Settings.Y_SIZE * .05);
        colorChoice.setPadding(new Insets(Settings.X_SIZE * .01));

        colorChoice.getChildren().addAll(makeText("Choose Color", Settings.MEDIUM_FONT),
                                            makeCircle(Settings.MEDIUM_FONT.getSize() / 2, Settings.RED),
                                            makeCircle(Settings.MEDIUM_FONT.getSize() / 2, Settings.YELLOW),
                                            makeCircle(Settings.MEDIUM_FONT.getSize() / 2, Settings.BLUE),
                                            makeCircle(Settings.MEDIUM_FONT.getSize() / 2, Settings.GREEN));



        innerOptions.getChildren().addAll(colorChoice,
                                            makeOpponent(Settings.YELLOW),
                                            makeOpponent(Settings.BLUE),
                                            makeOpponent(Settings.GREEN));

        //Initalize title text and buttons
        Text title =  makeText("New Game!", Settings.FONT);
        Button main = startButton();

        options.getChildren().addAll(title, innerOptions, main.getText());
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

        //Add references for object retrieval
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

        Text title = makeText("You Win! (Filler)", Settings.FONT);

        Button main = startButton();

        Button quit = quitButton();

        options.getChildren().addAll(title, main.getText(), quit.getText());
        pane.setLeft(options);

        //Add references for object retrieval
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
        Text title = makeText("Help!", Settings.FONT);

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

        //Add references for object retrieval
        helpMenu = pane;
        menus.add(pane);

        return pane;
    }

    public ArrayList<Node> getChildren() {
        return menus;
    }

    public void changeGameBool(boolean tf){
        gameStart = !tf;
    }
    //
    // GUI Element Constructors
    //

    private Circle makeCircle(double radius, Color color){
        Circle circle = new Circle(radius, color);
        circle.setStroke(color.darker());
        circle.setStrokeWidth(radius * .1);
        return circle;
    }

    private Text makeText(String string, Font font){
        Text text = new Text(string);
        text.setFont(font);
        text.setFill(Settings.TEXT);
        text.setStroke(Settings.TEXT);
        text.setStrokeWidth(font.getSize() * .05);

        return text;
    }

    private HBox makeOpponent(Color color){
        HBox options = new HBox();
        options.setSpacing(Settings.Y_SIZE * .05);
        Circle opponent = makeCircle(Settings.MEDIUM_FONT.getSize() / 2, color);
        options.getChildren().addAll(opponent, difficultyButton().getText());

        return options;
    }

    /**
     * Update with better newgame/resumegame testing
     * @return
     */
    private Button resumeButton(){
        Button resume = new Button("Resume Game", Settings.MEDIUM_FONT);
//        setButtonEventHandlers(resume);
        setResumeEventHandler(resume);

        if(!gameStart) {
            resume.getText().setFill(resume.getColor().darker());
            resume.getText().setStroke(resume.getColor().darker());
        }

        return resume;
    }

    private Button startButton(){
        Button main = new Button("Main Menu", Settings.MEDIUM_FONT);
        setButtonEventHandlers(main);
        setStartMenuEventHandler(main);

        return main;
    }

    private Button newGameButton(){
        Button start = new Button("New Game", Settings.MEDIUM_FONT);
        setButtonEventHandlers(start);
        setNewGameMenuEventHandler(start);

        return start;
    }

    private Button loadButton(){
        Button load = new Button("Load Game", Settings.MEDIUM_FONT);
        setButtonEventHandlers(load);

        return load;
    }

    private Button leaderboardButton(){
        Button leaderboard = new Button("Leaderboard", Settings.MEDIUM_FONT);
        setButtonEventHandlers(leaderboard);

        return leaderboard;
    }

    private Button helpButton(){
        Button help = new Button("Help", Settings.MEDIUM_FONT);
        setButtonEventHandlers(help);
        setHelpMenuEventHandler(help);

        return help;
    }

    private Button quitButton(){
        Button quit = new Button("Quit", Settings.MEDIUM_FONT);
        setButtonEventHandlers(quit);
        setQuitEventHandler(quit);

        return quit;
    }

    private Button difficultyButton(){
        Button difficulty = new Button("Easy", Settings.MEDIUM_FONT);
        difficulty.getText().setOnMouseReleased(new EventHandler() {
            @Override
            public void handle(Event event) {
//                if (difficulty.getText().toString().equals("Easy")) {
                    difficulty.getText().setFill(difficulty.getColor().darker());
                    difficulty.getText().setStroke(difficulty.getColor().darker());
                    difficulty.setText(makeText("Hard", Settings.SMALL_FONT));
//                } else {
//                    difficulty.getText().setFill(difficulty.getColor().brighter());
//                    difficulty.getText().setStroke(difficulty.getColor().brighter());
//                    difficulty.setText(makeText("Easy", Settings.SMALL_FONT));
//                }
            }
        });

        return difficulty;
    }
    //
    // Event Handlers
    //

    private void setButtonEventHandlers(Button button) {
        Text text = button.getText();
        button.getText().setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                text.setFill(button.getColor().darker());
                text.setStroke(button.getColor().darker());
            }
        });

        button.getText().setOnMouseExited(new EventHandler() {
            @Override
            public void handle(Event event) {
                text.setFill(button.getColor().brighter());
                text.setStroke(button.getColor().brighter());
            }
        });

    }

    private void setResumeEventHandler(Button button) {
        button.getText().setOnMouseReleased(new EventHandler() {
            @Override
            public void handle(Event event) {
                if(gameStart) {
                    setButtonEventHandlers(button);
                    gameBoard.toFront();
                    gameBoard.requestFocus();

                }
            }
        });
    }

    private void setStartMenuEventHandler(Button button) {
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

        public Button(String innerText, Font font) {
            text = new Text(innerText);
            text.setFont(font);
            text.setFill(Settings.TEXT);
            text.setStroke(c.brighter());
            text.setStrokeWidth(font.getSize() * .05);
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

    public void setController(Controller game){
        this.game = game;
    }


}