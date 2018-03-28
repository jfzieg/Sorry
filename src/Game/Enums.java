package Game;

/**
 * Enums for use by other classes
 */
public class Enums {
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
     *
     */
    public enum Color{
        RED(0), YELLOW(1), GREEN(2), BLUE(3);

        private int side;
        Color(int i){
            side = i;
        }

        /**
         * Get the int value of the enum.
         * @return the int value of the enum.
         */
        int getSide(){
            return side;
        }
    }
}
