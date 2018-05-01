package Game;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Settings {
    public static double X_SIZE = 1200;
    public static double Y_SIZE = 800;
    public static double TEXT_SIZE =  Y_SIZE / 10;
    public static double TILE_SIZE = X_SIZE / 30;
    public static double PIECE_RADIUS = TILE_SIZE / 2;
    public static double CARD_WIDTH = X_SIZE / 8; // Ratio should be 5:7 (playing card ratio)
    public static double CARD_HEIGHT = X_SIZE / 6;

    public static Color BACKGROUND = Color.GRAY;
    public static Color TEXT = new Color(1, 1, 1, .8);
    public static Font FONT = new Font("Arial Rounded MT Bold", Settings.TEXT_SIZE);
    public static Font SMALL_FONT = new Font("Arial Rounded MT Bold", Settings.TEXT_SIZE * .4);
    public static Font MEDIUM_FONT = new Font("Arial Rounded MT Bold", Settings.TEXT_SIZE * .6);
    public static Font CARD_FONT = new Font("Arial Rounded MT Bold", Settings.TEXT_SIZE * .9);

    public static Color RED = Color.ORANGERED;
    public static Color BLUE = Color.ROYALBLUE;
    public static Color YELLOW = Color.GOLD;
    public static Color GREEN = Color.GREEN;
    public static Color CARD = Color.BLUEVIOLET;
}
