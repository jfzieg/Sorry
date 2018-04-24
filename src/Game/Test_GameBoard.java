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
        //Testing Contructors
          GameBoard b4 = new GameBoard(Enums.Color.YELLOW, new GamePiece(Enums.Color.RED, true, true), new GamePiece(Enums.Color.BLUE, true, false), new GamePiece(Enums.Color.GREEN, false, false));
        
        
        //Testing HomeGet Out
        b4.homeGetOut(1, Enums.Color.YELLOW, true);
        b4.homeGetOut(1, Enums.Color.YELLOW, true);
        
        b4.homeGetOut(2, Enums.Color.BLUE, false);
        b4.homeGetOut(1, Enums.Color.BLUE, false);
        
        b4.movePieceForward(b4.getPlayerPieces()[0], 4);
        
        b4.movePieceForward(b4.getPlayerPieces()[0], 10);
        
        b4.movePieceBackWard(b4.getOpponentsPieces().get(1)[0], 4);
        
//        b4.movePieceBackWard(b4.getOpponentsPieces().get(1)[0], 4);
        
        b4.homeGetOut(1, Enums.Color.BLUE, false);
        
        b4.movePieceForward(b4.getOpponentsPieces().get(1)[0], 4);
        
        b4.homeGetOut(1, Enums.Color.GREEN, false);
        
        b4.movePieceForward(b4.getPlayerPieces()[0], 1);
        
        b4.movePieceBackWard(b4.getOpponentsPieces().get(1)[0], 4);
        
//        b4.movePieceForward(b4.getPlayerPieces()[0], 6);
        
        b4.movePieceBackWard(b4.getPlayerPieces()[0], 3);
        
        b4.movePieceForward(b4.getPlayerPieces()[0], 4);
        
//        b4.movePieceBackWard(b4.getOpponentsPieces().get(1)[0], 1);
        
        System.out.println();
        
        
    }
}
