package Game;

import javafx.scene.paint.Color;
import java.io.Serializable;

/**
 *
 */
public class GamePiece implements Serializable{
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
        this.boardSide = color.getSide();
        this.c = color.getColor();
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


    public void setLocation(int boardSide, int innerLocation){
        this.boardSide = boardSide;
        this.innerLocation = innerLocation;
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
    public Color getColor() {
        return c;
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
