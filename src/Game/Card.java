package Game;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Card extends Rectangle {
    protected Color c;
    protected Text number;

    public int getType() {
        return type.getNumber();
    }

    protected Enums.CardType type;

    /**
     *
     * @param card
     */
    public Card(Enums.CardType card) {
//        setType(card);
//
//        setFill(c); // set card color
//        setStroke(c.darker()); // Set border color
//        setStrokeWidth(5); // Set border size

//        loc = new Point2D(getCenterX(), getCenterY()); // Cell's center coordinates

        type = card;
    }

    /**
     *
     * @param card
     */
    protected void setType(Enums.CardType card) {
        switch (card) {
            case ONE:
                c = new Color(1, 1, 1, 1);
                number = new Text("1");
                break;
            case TWO:
                c = new Color(1, 1, 1, 1);
                number = new Text("2");
                break;
            case THREE:
                c = new Color(1, 1, 1, 1);
                number = new Text("3");
                break;
            case FOUR:
                c = new Color(1, 1, 1, 1);
                number = new Text("4");
                break;
            case FIVE:
                c = new Color(1, 1, 1, 1);
                number = new Text("5");
                break;
            case SEVEN:
                c = new Color(1, 1, 1, 1);
                number = new Text("7");
                break;
            case EIGHT:
                c = new Color(1, 1, 1, 1);
                number = new Text("8");
                break;
            case NINE:
                c = new Color(1, 1, 1, 1);
                number = new Text("9");
                break;
            case TEN:
                c = new Color(1, 1, 1, 1);
                number = new Text("10");
                break;
            case ELEVEN:
                c = new Color(1, 1, 1, 1);
                number = new Text("11");
                break;
            case TWELVE:
                c = new Color(1, 1, 1, 1);
                number = new Text("12");
                break;
            case SORRY:
                c = new Color(1, 1, 1, 1);
                number = new Text("SORRY");
                break;
        }
    }
}
