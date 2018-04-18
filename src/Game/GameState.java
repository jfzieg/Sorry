package Game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GameState implements Serializable {

    public GameState(){
    }
    
    public void saveGameDataToFile(File file, GameBoard board, GamePiece[] player, ArrayList<GamePiece[]> opponent, ArrayList<Card> card){
        try{
            FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            
  
                    objectStream.writeObject(board);
                    objectStream.writeObject(player);
                    objectStream.writeObject(opponent);
                    objectStream.writeObject(card);
                    //Need to store enimies personality
                    //Game mode
                    //and Card
                    
                    objectStream.close();
                    fileStream.close();
                    
                    System.out.println("Save game state successfully");
                } catch(Exception e){
                    System.out.println("Fail to save game state");
                }
}
    
    public GameBoard loadGameBoardFromFile(File file) throws ClassNotFoundException, IOException{
        FileInputStream fileStream = new FileInputStream(file);
        ObjectInputStream objectStream = new ObjectInputStream(fileStream);
        GameBoard board = new GameBoard();
        
        board = (GameBoard) objectStream.readObject();
        
        objectStream.close();
        fileStream.close();
        System.out.println("Load game state successfully");
        return board;
    }
    
    public GamePiece[] loadPlayerPieceFromFile(File file) throws ClassNotFoundException, IOException{
        FileInputStream fileStream = new FileInputStream(file);
        ObjectInputStream objectStream = new ObjectInputStream(fileStream);
        GamePiece[] player = new GamePiece[4];
        
        player = (GamePiece[]) objectStream.readObject();
        
        objectStream.close();
        fileStream.close();
        System.out.println("Load game state successfully");
        return player;
    }
    
    public ArrayList<GamePiece[]> loadOpponentFromFile(File file) throws ClassNotFoundException, IOException{
        FileInputStream fileStream = new FileInputStream(file);
        ObjectInputStream objectStream = new ObjectInputStream(fileStream);
        ArrayList<GamePiece[]> opponent = new ArrayList<GamePiece[]>();
        
        opponent = (ArrayList<GamePiece[]>) objectStream.readObject();
        
        objectStream.close();
        fileStream.close();
        System.out.println("Load game state successfully");
        return opponent;
    }
    
    public ArrayList<Card> loadCardFromFile(File file) throws ClassNotFoundException, IOException{
        FileInputStream fileStream = new FileInputStream(file);
        ObjectInputStream objectStream = new ObjectInputStream(fileStream);
        ArrayList<Card> card = new ArrayList<Card>();
        
        card = (ArrayList<Card>) objectStream.readObject();
        
        objectStream.close();
        fileStream.close();
        System.out.println("Load game state successfully");
        return card;
    }
}
