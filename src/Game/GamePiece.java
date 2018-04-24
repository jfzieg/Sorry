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
    private boolean isHome;

    /**
     * Default constructor for a GamePiece. Should only be used for player's pieces, as booleans are not defined and
     * calling isMean or isSmart will result in a null pointer exception.
     *
     * @param color
     */
    public GamePiece(Enums.Color color) {
        this.color = color;
        this.boardSide = color.getSide();
        this.movesLeft = 58;
        this.boardSide = -1; // Initial value for home location
        this.isHome = false;
    }

    public boolean isHome() {
        return isHome;
    }

    public void setHome(boolean isHome) {
        this.isHome = isHome;
    }

    /**
     * Constructor for an opponent GamePiece.
     *
     * @param color
     * @param isSmart
     * @param isMean
     */
    public GamePiece(Enums.Color color, boolean isSmart, boolean isMean){
        this(color);
        this.isSmart = isSmart;
        this.isMean = isMean;
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
     * Used to set the number of moves left for the GamePiece, use for bumping.
     * @param movesLeft The number of places left to home
     */
    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
    }

    /**
     * Used to set the number of moves left for the GamePiece, use for bumping.
     * @param deltaMoves The +/- change in moves
     */
    public void adjustMovesLeft(int deltaMoves) {
        this.movesLeft += deltaMoves;
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
