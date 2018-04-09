package Game;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Settings {
    public static double X_SIZE = 1200;
    public static double Y_SIZE = 800;
    public static double TEXT_SIZE =  Y_SIZE / 10;
    public static double TILE_SIZE = 50;
    public static double CARD_WIDTH = 50; // Ratio should be 5:7 (playing card ratio)
    public static double CARD_HEIGHT = 70;

    public static Color BACKGROUND = Color.GRAY;
    public static Color TEXT = new Color(1, 1, 1, .8);
    public static Font FONT = new Font("Arial Rounded MT Bold", Settings.TEXT_SIZE);
    public static Font SMALL_FONT = new Font("Arial Rounded MT Bold", Settings.TEXT_SIZE * .4);
    public static Font MEDIUM_FONT = new Font("Arial Rounded MT Bold", Settings.TEXT_SIZE * .6);

    public static Color RED = Color.web("#F44336", 1.0);
    public static Color BLUE = Color.web("#2196F3", 1.0);
    public static Color YELLOW = Color.web("#FFEA00", 1.0);
    public static Color GREEN = Color.web("#4CAF50", 1.0);
}
