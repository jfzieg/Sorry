package Game;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class Card {
    protected Color c;
    protected Text number;
    protected Enums.CardType type;

    /**
     *
     * @param card
     */
    public Card(Enums.CardType card) {
        type = card;
        number = new Text(Integer.toString(type.getNumber()));
        number.setFont(Settings.FONT);
        number.setFill(Settings.TEXT);
        number.setStroke(Settings.TEXT);
        number.setStrokeWidth(Settings.FONT.getSize() * .05);
        c = Settings.BACKGROUND;
    }
    public int getType() {
        return type.getNumber();
    }

    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return Integer.toString(getType());
    }
}
