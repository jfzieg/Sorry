package Game;

import java.io.Serializable;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class Card implements Serializable{
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
