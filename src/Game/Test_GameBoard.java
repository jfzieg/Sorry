package Game;

public class Test_GameBoard {
    
    //Testing function for MoveInHome method
    static void TestMoveInHome(GameBoard b){
        
        b.homeGetOut(1, Enums.Color.YELLOW, true);
        
        //Have 2 pieces in home
        b.movePieceForward(b.getPlayerPieces()[0], 12);
        b.movePieceForward(b.getPlayerPieces()[0], 12);
        b.movePieceForward(b.getPlayerPieces()[0], 12);
        b.movePieceForward(b.getPlayerPieces()[0], 12);
        b.movePieceForward(b.getPlayerPieces()[0], 5);
        b.movePieceForward(b.getPlayerPieces()[0], 3);
        
        b.homeGetOut(2, Enums.Color.YELLOW, true);
        
        b.movePieceForward(b.getPlayerPieces()[1], 12);
        b.movePieceForward(b.getPlayerPieces()[1], 12);
        b.movePieceForward(b.getPlayerPieces()[1], 12);
        b.movePieceForward(b.getPlayerPieces()[1], 12);
        b.movePieceForward(b.getPlayerPieces()[1], 6);
        b.movePieceForward(b.getPlayerPieces()[1], 1);
        
        //Test normal move in home
        b.moveInHome(b.getPlayerPieces()[0], 1);
        //Test unvailable move in home
        b.moveInHome(b.getPlayerPieces()[0], 4);
        //Test if it can bump or not
        b.moveInHome(b.getPlayerPieces()[1], 2);
        //Test go home
        b.moveInHome(b.getPlayerPieces()[0], 2);
        
        b.moveInHome(b.getPlayerPieces()[1], 4);
        
        System.out.println();
    }
    
    //Testing function for MoveBackWard method
    static void TestMoveBackWard(GameBoard b){
        b.homeGetOut(1, Enums.Color.YELLOW, true);
        //Test normal move backward if it changes tile
        b.movePieceBackWard(b.getPlayerPieces()[0], 4);
        
        b.homeGetOut(2, Enums.Color.BLUE, false);
        
        b.movePieceForward(b.getOpponentsPieces().get(1)[0], 12);
        
        b.movePieceForward(b.getOpponentsPieces().get(1)[0], 11);
        //Test slide in move backward
        b.movePieceBackWard(b.getOpponentsPieces().get(1)[0], 4);
        //Test normal move backward
        b.movePieceBackWard(b.getOpponentsPieces().get(1)[0], 1);
        //Test bump in move backward
        b.movePieceBackWard(b.getOpponentsPieces().get(1)[0], 4);
    }
    
    //Testing function for MoveForward method
    static void TestMoveForward(GameBoard b){
        b.homeGetOut(1, Enums.Color.YELLOW, true);
        //Test normal move forward in a tile
        b.movePieceForward(b.getPlayerPieces()[0], 4);
        //Test move forward change tile
        b.movePieceForward(b.getPlayerPieces()[0], 9);
        //Test slide in move forward
        b.movePieceForward(b.getPlayerPieces()[0], 7);
        
        b.homeGetOut(2, Enums.Color.BLUE, false);
        //Test bump in move forward
        b.movePieceForward(b.getPlayerPieces()[0], 5);
        //Test if a piece can get out if there is a piece in the place
        b.homeGetOut(1, Enums.Color.BLUE, false);
        
    }

    public static void main (String[] args) {
        //Testing Contructors
          GameBoard b1 = new GameBoard(Enums.Color.YELLOW, new GamePiece(Enums.Color.RED, true, true), new GamePiece(Enums.Color.BLUE, true, false), new GamePiece(Enums.Color.GREEN, false, false));
          TestMoveInHome(b1);
          GameBoard b2 = new GameBoard(Enums.Color.YELLOW, new GamePiece(Enums.Color.RED, true, true), new GamePiece(Enums.Color.BLUE, true, false), new GamePiece(Enums.Color.GREEN, false, false));
          TestMoveBackWard(b2);
          GameBoard b3 = new GameBoard(Enums.Color.YELLOW, new GamePiece(Enums.Color.RED, true, true), new GamePiece(Enums.Color.BLUE, true, false), new GamePiece(Enums.Color.GREEN, false, false));
          TestMoveForward(b3);
        
       
        
//        b4.movePieceForward(b4.getPlayerPieces()[0], 4);
//        
//        b4.movePieceForward(b4.getPlayerPieces()[0], 10);
//        
//        b4.movePieceBackWard(b4.getOpponentsPieces().get(1)[0], 4);
        
        
        
//        b4.movePieceBackWard(b4.getOpponentsPieces().get(1)[0], 4);
        
        
        
//        b4.homeGetOut(1, Enums.Color.BLUE, false);
//        
//        b4.movePieceForward(b4.getOpponentsPieces().get(1)[0], 4);
//        
//        b4.homeGetOut(1, Enums.Color.GREEN, false);
//        
//        b4.movePieceForward(b4.getPlayerPieces()[0], 1);
//        
//        b4.movePieceBackWard(b4.getOpponentsPieces().get(1)[0], 4);
        
        
        
//        b4.movePieceForward(b4.getPlayerPieces()[0], 6);
        
        
        
//        b4.movePieceBackWard(b4.getPlayerPieces()[0], 3);
//        
//        b4.movePieceForward(b4.getPlayerPieces()[0], 4);
        
        
        
//        b4.movePieceBackWard(b4.getOpponentsPieces().get(1)[0], 1);
        
        System.out.println();
        
        
    }
}
