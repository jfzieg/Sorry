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
    private int movesLeft;
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
        this.movesLeft = 60;
    }

    /**
     *
     * @param color
     * @param isSmart
     * @param isMean
     */
    public GamePiece(Enums.Color color, boolean isSmart, boolean isMean){
        this.color = color;
        this.c = color.getColor();
        this.isSmart = isSmart;
        this.isMean = isMean;
        this.boardSide = -1; // Initial value for home location
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

    public int getMovesLeft() {
        return movesLeft;
    }

    /**
     *
     * @param deltaMoves The +/- change in moves
     */
    public void setMovesLeft(int deltaMoves) {
        this.movesLeft += movesLeft;
    }

    /**
     *
     * @return
     */
    public Enums.Color getColor() {
        return color;
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
