package Game;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

/**
 *
 */
public class GamePiece extends Circle{
    protected Enums.Color color;
    protected int boardSide;
    protected int innerLocation;

    protected Color c;

    /**
     *
     * @param color
     */
    public GamePiece(Enums.Color color) {
        this.color = color;
    }

    /**
     * Check to determine whether a colored piece is on the same side as its home.
     * @return True if it is on the same side, False otherwise.
     */
    public boolean isHomeSide(){
        return color.getSide() == boardSide;
    }

    /**
     *
     * @return
     */
    public int getBoardSide() {
        return boardSide;
    }

    /**
     *
     * @param boardSide
     */
    public void setBoardSide(int boardSide) {
        this.boardSide = boardSide;
    }

    /**
     *
     * @return
     */
    public int getInnerLocation() {
        return innerLocation;
    }

    /**
     *
     * @param innerLocation
     */
    public void setInnerLocation(int innerLocation) {
        this.innerLocation = innerLocation;
    }

    /**
     *
     * @return
     */
    public int getC() {
        return color.getSide();
    }

}
