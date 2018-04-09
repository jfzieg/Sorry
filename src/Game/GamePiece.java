package Game;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

/**
 *
 */
public class GamePiece{
    private Enums.Color color;
    private int boardSide;
    private int innerLocation;
    private boolean isSmart;
    private boolean isMean;

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
    public int getColor() {
        return color.getSide();
    }

    public boolean isSmart() {
        return isSmart;
    }

    public void setSmart(boolean smart) {
        isSmart = smart;
    }

    public boolean isMean() {
        return isMean;
    }

    public void setMean(boolean mean) {
        isMean = mean;
    }
}
