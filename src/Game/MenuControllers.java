package Game;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.*;

public class MenuControllers {
    private ArrayList<Node> menus = new ArrayList<>();
    private Pane startMenu;
    private Pane newGameMenu;
    private Pane loadMenu;
    private Pane leaderboard;
    private Pane gameBoard;
    private Pane endMenu;
    private ScrollPane helpMenu;
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
     * TODO: Add button functionalities for choosing color, opponents and difficulties
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

        innerOptions.getChildren().addAll(makeOpponent(Settings.YELLOW),
                                            makeOpponent(Settings.BLUE),
                                            makeOpponent(Settings.GREEN));

        options.getChildren().addAll(makeText("New Game!", Settings.FONT), colorChoice, innerOptions, startButton().getText());
        pane.setLeft(options);

        newGameMenu = pane;
        menus.add(pane);

        return pane;
    }

    /**
     * TODO: Create gameboard from GridPane, init colors and etc
     * TODO: Add menu items
     * @return
     */
    public BorderPane gameBoard() {
        //Initialize pane and prefs
        BorderPane pane = new BorderPane();
        pane.setBackground(new Background(new BackgroundFill(Settings.BACKGROUND, null, null)));
        VBox leftMenu = new VBox();
        leftMenu.setSpacing(Settings.Y_SIZE * .05);
        leftMenu.setPadding(new Insets(Settings.X_SIZE * .01));
        GridPane gameboard = makeBoard();
        Text title = makeText("Sorry!", Settings.FONT);
        Button main = startButton();
        leftMenu.getChildren().addAll(title, main.getText());

        pane.setCenter(gameboard);
        pane.setLeft(leftMenu);

        //Add references for object retrieval
        gameBoard = pane;
        menus.add(pane);

        return pane;
    }

    /**
     * TODO: Add FileIO for loading saved serialized versions of Controller
     * @return
     */
    public BorderPane loadMenu(){
        //Initialize pane and prefs
        BorderPane pane = new BorderPane();
        pane.setBackground(new Background(new BackgroundFill(Settings.BACKGROUND, null, null)));

        //Initalize container and prefs
        VBox options = new VBox();
        options.setLayoutY(Settings.Y_SIZE);
        options.setSpacing(Settings.Y_SIZE * .05);
        options.setPadding(new Insets(Settings.X_SIZE * .01));

        Text title = makeText("Load Game", Settings.FONT);

        Button main = startButton();


        options.getChildren().addAll(title, main.getText());
        pane.setLeft(options);

        //Add references for object retrieval
        loadMenu = pane;
        menus.add(pane);

        return pane;
    }

    /**
     * TODO: Pull info from database to display for user
     * @return
     */
    public BorderPane leaderboardMenu(){
        //Initialize pane and prefs
        BorderPane pane = new BorderPane();
        pane.setBackground(new Background(new BackgroundFill(Settings.BACKGROUND, null, null)));

        //Initalize container and prefs
        VBox options = new VBox();
        options.setLayoutY(Settings.Y_SIZE);
        options.setSpacing(Settings.Y_SIZE * .05);
        options.setPadding(new Insets(Settings.X_SIZE * .01));

        Text title = makeText("Leaderboard", Settings.FONT);

        Button main = startButton();


        options.getChildren().addAll(title, main.getText());
        pane.setLeft(options);

        //Add references for object retrieval
        leaderboard = pane;
        menus.add(pane);

        return pane;
    }

    /**
     * TODO: Get game end state and display appropriate options
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
     * TODO: Write instructions for game
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


    //
    // GUI Element Constructors
    //

    /**
     *
     * @param radius
     * @param color
     * @return
     */
    private Circle makeCircle(double radius, Color color){
        Circle circle = new Circle(radius, color);
        circle.setStroke(color.darker());
        circle.setStrokeWidth(radius * .1);
        return circle;
    }

    /**
     *
     * @param color
     * @return
     */
    private Rectangle makeTile(Color color){
        Rectangle tile = new Rectangle(Settings.TILE_SIZE, Settings.TILE_SIZE, color);
        tile.setStroke(color.darker());
        tile.setStrokeWidth(Settings.TILE_SIZE * .05);
        return tile;
    }

    /**
     *
     * @param c
     * @param boardSide
     * @param length
     * @return
     */
    private Polygon makeSlide(Color c, int boardSide, int length){
        Double longSide = Settings.TILE_SIZE * (length - .1) , shortSide = Settings.TILE_SIZE - .15;
        Polygon slide = new Polygon();
        slide.setFill(c);
        slide.setStroke(c.darker());
        slide.setStrokeWidth(Settings.TILE_SIZE * .05);

        switch (boardSide) {
            //RED
            case 0:
                slide.getPoints().addAll(new Double[]{
                        0.0, 0.0,
                        longSide, Settings.TILE_SIZE / 2,
                        0.0, shortSide

                });
                break;
            //YELLOW
            case 1:
                slide.getPoints().addAll(new Double[]{
                    0.0, 0.0,
                    -longSide, Settings.TILE_SIZE / 2,
                    0.0, shortSide

                });
                break;
            //GREEN
            case 2:
                slide.getPoints().addAll(new Double[]{
                        0.0, 0.0,
                        shortSide, 0.0,
                        Settings.TILE_SIZE / 2, longSide,

                });
                break;
            //BLUE
            case 3:
                slide.getPoints().addAll(new Double[]{
                        0.0, 0.0,
                        shortSide, 0.0,
                        Settings.TILE_SIZE / 2, -longSide

                });
                default:

                break;
        }
        return slide;
    }

    /**
     *
     * @param string
     * @param font
     * @return
     */
    private Text makeText(String string, Font font){
        Text text = new Text(string);
        text.setFont(font);
        text.setFill(Settings.TEXT);
        text.setStroke(Settings.TEXT);
        text.setStrokeWidth(font.getSize() * .05);

        return text;
    }

    /**
     * TODO: Finish GUI buttons
     * @return
     */
    private GridPane makeBoard(){
        GridPane gameboard = new GridPane();
        gameboard.setPadding(new Insets(Settings.X_SIZE * .01));

//        gameboard.add(new Rectangle( Settings.TILE_SIZE * 16, Settings.TILE_SIZE * 16, Color.WHITE), 0, 1, 15, 15);
        for(int i = 0; i < 16; i++){
            gameboard.add(makeTile(Color.WHITE), i, 0);
            gameboard.add(makeTile(Color.WHITE), i, 15);
            gameboard.add(makeTile(Color.WHITE), 0, i);
            gameboard.add(makeTile(Color.WHITE), 15, i);
        }
        for(int j = 1; j < 6; j++){
            gameboard.add(makeTile(Settings.RED), 2, j);
            gameboard.add(makeTile(Settings.YELLOW), 13, 15 - j);
            gameboard.add(makeTile(Settings.BLUE), j, 13);
            gameboard.add(makeTile(Settings.GREEN), 15 - j, 2);
        }

        gameboard.add(makeCircle(Settings.TILE_SIZE, Settings.RED),2, 5 , 2, 2);
        gameboard.add(makeTile(Settings.RED), 4,1);
        gameboard.add(makeCircle(Settings.TILE_SIZE , Settings.RED),4, 1, 2, 2);

        gameboard.add(makeSlide(Settings.RED, 0, 4), 1, 0, 5, 1);
        gameboard.add(makeSlide(Settings.RED, 0, 5), 9, 0, 5, 1);


        gameboard.add(makeCircle(Settings.TILE_SIZE, Settings.YELLOW),12, 9, 2, 2);
        gameboard.add(makeTile(Settings.YELLOW), 11,14);
        gameboard.add(makeCircle(Settings.TILE_SIZE, Settings.YELLOW),10, 13, 2, 2);

        gameboard.add(makeSlide(Settings.YELLOW, 1, 4), 11, 15, 4, 1);
        gameboard.add(makeSlide(Settings.YELLOW, 1, 5), 2, 15, 5, 1);

        gameboard.add(makeCircle(Settings.TILE_SIZE, Settings.BLUE),5, 12 , 2, 2);
        gameboard.add(makeTile(Settings.BLUE), 1,11);
        gameboard.add(makeCircle(Settings.TILE_SIZE, Settings.BLUE),1, 10, 2, 2);

        gameboard.add(makeSlide(Settings.BLUE, 3, 4), 0, 11, 1, 4);
        gameboard.add(makeSlide(Settings.BLUE, 3, 5), 0, 2, 1, 5);

        gameboard.add(makeCircle(Settings.TILE_SIZE, Settings.GREEN),9, 2 , 2, 2);
        gameboard.add(makeTile(Settings.GREEN), 14,4);
        gameboard.add(makeCircle(Settings.TILE_SIZE, Settings.GREEN),13, 4, 2, 2 );

        gameboard.add(makeSlide(Settings.GREEN, 2, 4), 15, 1, 1, 4);
        gameboard.add(makeSlide(Settings.GREEN, 2, 5), 15, 9, 1, 5);

        return gameboard;
    }

    private HBox makeOpponent(Color color){
        HBox options = new HBox();
        options.setSpacing(Settings.Y_SIZE * .05);
        Circle opponent = makeCircle(Settings.MEDIUM_FONT.getSize() / 2, color);
        options.getChildren().addAll(opponent, difficultyButton().getText());

        return options;
    }

    /**
     * TODO: Update with better newgame/resumegame testing
     * @return
     */
    private Button resumeButton(){
        Button resume = new Button("Resume Game", Settings.MEDIUM_FONT);
//        setButtonEventHandlers(resume);
        setResumeEventHandler(resume);

        if(game.isNew_game()) {
            resume.getText().setFill(resume.getColor().darker());
            resume.getText().setStroke(resume.getColor().darker());
        }

        return resume;
    }

    /**
     *
     * @return
     */
    private Button startButton(){
        Button main = new Button("Main Menu", Settings.MEDIUM_FONT);
        setStartMenuEventHandler(main);

        return main;
    }

    /**
     *
     * @return
     */
    private Button newGameButton(){
        Button start = new Button("New Game", Settings.MEDIUM_FONT);
        setNewGameMenuEventHandler(start);

        return start;
    }

    /**
     *
     * @return
     */
    private Button loadButton(){
        Button load = new Button("Load Game", Settings.MEDIUM_FONT);
        setLoadMenuEventHandler(load);
        return load;
    }

    /**
     *
     * @return
     */
    private Button leaderboardButton(){
        Button leaderboard = new Button("Leaderboard", Settings.MEDIUM_FONT);
        setLeaderboardEventHandler(leaderboard);
        return leaderboard;
    }

    /**
     *
     * @return
     */
    private Button helpButton(){
        Button help = new Button("Help", Settings.MEDIUM_FONT);

        setHelpMenuEventHandler(help);

        return help;
    }

    /**
     *
     * @return
     */
    private Button quitButton(){
        Button quit = new Button("Quit", Settings.MEDIUM_FONT);
        setQuitEventHandler(quit);

        return quit;
    }

    /**
     *
     * @return
     */
    private Button difficultyButton(){
        Button difficulty = new Button("Easy", Settings.MEDIUM_FONT);
        difficulty.getText().setOnMouseClicked(new EventHandler() {
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

    private Circle circleButton(double radius, Color color){
        Circle circle = makeCircle(radius, color);
        circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                circle.setFill(color.darker());
                circle.setStroke(color.darker());
            }
        });
        circle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                circle.setFill(color.brighter());
                circle.setStroke(color.brighter());
            }
        });
        circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });

        return circle;
    }

    //
    // Event Handlers
    //

    private void setResumeEventHandler(Button button) {
        button.getText().setOnMouseReleased(new EventHandler() {
            @Override
            public void handle(Event event) {
                if(game.isNew_game()) {

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

    private void setLoadMenuEventHandler(Button button) {
        Text text = button.getText();
        button.getText().setOnMouseReleased(new EventHandler() {
            @Override
            public void handle(Event event) {
                gameBoard.toFront();
                gameBoard.requestFocus();
            }
        });
    }

    private void setLeaderboardEventHandler(Button button) {
        Text text = button.getText();
        button.getText().setOnMouseReleased(new EventHandler() {
            @Override
            public void handle(Event event) {
                leaderboard.toFront();
                leaderboard.requestFocus();
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

            text.setOnMouseEntered(new EventHandler() {
                @Override
                public void handle(Event event) {
                    text.setFill(c.darker());
                    text.setStroke(c.darker());
                }
            });

            text.setOnMouseExited(new EventHandler() {
                @Override
                public void handle(Event event) {
                    text.setFill(c.brighter());
                    text.setStroke(c.brighter());
                }
            });

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

    public ArrayList<Node> getMenus() {
        return menus;
    }
}
