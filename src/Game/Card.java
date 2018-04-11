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
