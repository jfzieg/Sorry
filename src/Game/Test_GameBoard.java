package Game;

public class Test_GameBoard {
    
    static void checkNewBoard(GamePiece[][] tileList, GamePiece[][] homeList){
        System.out.println("New Tile List");
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 16; j++){
                if(tileList[i][j] != null){
                    System.out.println("Piece position [" + i + "][" + j +"]");
                }
            }
        }
        System.out.println();
        System.out.println("New Home List");
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 6; j++){
                if(homeList[i][j] != null){
                    System.out.println("Piece position [" + i + "][" + j +"]");
                }
            }
        }
    }
    
    
    public static void main (String[] args) {
        GamePiece[][] tileList = new GamePiece[4][16];
        GamePiece[][] homeList = new GamePiece[4][6];
        
        GamePiece p = new GamePiece(Enums.Color.RED);
        GamePiece p2 = new GamePiece(Enums.Color.RED);
        GamePiece p3 = new GamePiece(Enums.Color.GREEN);
        GamePiece p4 = new GamePiece(Enums.Color.GREEN);
        GamePiece p5 = new GamePiece(Enums.Color.YELLOW);
        GamePiece p6 = new GamePiece(Enums.Color.YELLOW);
        GamePiece p7 = new GamePiece(Enums.Color.BLUE);
        GamePiece p8 = new GamePiece(Enums.Color.BLUE);
        GamePiece p9 = new GamePiece(Enums.Color.BLUE);
        GamePiece p10 = new GamePiece(Enums.Color.YELLOW);

        tileList[0][9] = p4;
        tileList[3][4] = p;
        tileList[3][8] = p3;
        tileList[1][12] = p5;
        tileList[2][3] = p6;
        tileList[2][13] = p2;
        tileList[3][2] = p7;
        tileList[3][14] = p10;
        
        homeList[3][1] = p8;
        homeList[3][4] = p9;
        
        //Create a new game board
        GameBoard gb = new GameBoard(tileList, homeList);
        
        //Test slide method
        //Should be in tileList[0][13]
        gb.slide(tileList[0][9]);
        
        //Test if it has something to bump forward
        //In this case, there is something ahead
        //Should return true
        boolean testBump = gb.checkBump(tileList[3][4], 4, false);
        
        //Test if it has something to bump backward
        //In this case, there is something behind
        //Should return true
        boolean testBump2 = gb.checkBump(tileList[3][8], 4, true);
        
        //Test if it has something to bump backward
        //In this case there is nothing backward
        //Should return false
        boolean testBump3 = gb.checkBump(tileList[3][4], 10, true);
        
        //Test if it has something to bump forward
        //In this case there is nothing forward
        //Should return false
        boolean testBump4 = gb.checkBump(tileList[3][4], 2, false);
        
        //Test piece to change tile
        //Should be now in tileList[2][1]
        gb.movePieceForward(tileList[1][12], 5);
        
        //Test piece to move forward normally
        //Should be now in tileList[2][8]
        gb.movePieceForward(tileList[2][3], 5);
        
        //Test piece to change tile and also go straight to home
        //Sould be now in homeList[1][0]
        //Test Error cannot get piece home because of color conflict and index conflict
        //gb.movePieceForward(tileList[3][14], 4);
        
        //Test piece to move backward normally
        //Should be now in tileList[2][12]
        gb.movePieceBackWard(tileList[2][13], 10);
        
        //Test piece to move backward and change tile
        //Should be now in tileList[2][13]
        gb.movePieceBackWard(tileList[3][2], 4);
        
        //Test get out
        //a new green piece should be in tileList[
        gb.homeGetOut(1, Enums.Color.GREEN);
        
        //Test move in home
        //In this case there is something in the front
        //Should print out there is a piece infront of you
        gb.moveInHome(homeList[3][1], 3);
        
        //Test move in home normally
        //Should be now homeList[3][3]
        gb.moveInHome(homeList[3][1], 2);
        
        //Test move in home
        //Exceed available move
        //Should be print out cannot move
        gb.moveInHome(homeList[3][4], 3);
        
        
        checkNewBoard(tileList, homeList);
        System.out.println();
        System.out.println("Check bump 1 should be true: " + testBump);
        System.out.println("Check bump 2 should be true: " + testBump2);
        System.out.println("Check bump 3 should be false: " + testBump3);
        System.out.println("Check bump 4 should be false: " + testBump4);
    }
}
