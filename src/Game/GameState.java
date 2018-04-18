package Game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

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
    
    public GameBoard loadGameDataFromFile(File file) throws ClassNotFoundException, IOException{
        FileInputStream fileStream = new FileInputStream(file);
        ObjectInputStream objectStream = new ObjectInputStream(fileStream);
        
        GameBoard board = (GameBoard) objectStream.readObject();
        
        objectStream.close();
        fileStream.close();
        System.out.println("Load game state successfully");
        return board;
        //Need to load enemies personality
        //Game Mode
        //And Card
    }
}
