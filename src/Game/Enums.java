package Game;

/**
 * Enums for use by other classes
 */
public class Enums {
    /**
     *
     */
    public enum CardType{
        ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SEVEN(7), EIGHT(8), NINE(9), TEN(10), ELEVEN(11), TWELVE(12), SORRY(0);

        private int number;

        CardType(int i){
            number = i;
        }

        /**
         * Get the int value of the enum.
         * @return the int value of the enum.
         */
        int getNumber(){
            return number;
        }

    }

    /**
     * Enum to hold int/Color relationships for each player's pieces.
     *
     * Integer values correspond to which side of the board they're assigned to.
     */
    public enum Color{
        RED(0), YELLOW(1), GREEN(2), BLUE(3);

        private int side;
        private javafx.scene.paint.Color c;

        Color(int i){
            side = i;
            switch(i){
                case 0:
                    c = Settings.RED;
                    break;
                case 1:
                    c = Settings.YELLOW;
                    break;
                case 2:
                    c = Settings.GREEN;
                    break;
                case 3:
                    c = Settings.BLUE;
                    break;
                default:
                    c = javafx.scene.paint.Color.WHITE;
                    break;
            }
        }

        /**
         * Get the int value of the enum.
         * @return the int value of the enum.
         */
        int getSide(){
            return side;
        }

        /**
         * The JavaFX color corresponding to the enum
         * @return the assigned color of the enum
         */
        javafx.scene.paint.Color getColor(){
            return c;
        }
    }
}