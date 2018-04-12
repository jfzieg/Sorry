package Game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

public class GameState {

    public GameState(){
    }
    
    public void saveGameDataToFile(File file, GameBoard board){
        try{
            FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            
  
                    objectStream.writeObject(board);
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
    
    public void loadGameDataFromFilr(File file, GamePiece[][] tileList, GamePiece[][] homeList) throws ClassNotFoundException, IOException{
        FileInputStream fileStream = new FileInputStream(file);
        ObjectInputStream objectStream = new ObjectInputStream(fileStream);
        
        tileList = (GamePiece[][]) objectStream.readObject();
        homeList = (GamePiece[][]) objectStream.readObject();
        //Need to load enemies personality
        //Game Mode
        //And Card
    }
}
