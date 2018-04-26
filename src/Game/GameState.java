package Game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * CHANGE FILEWRITER FOR YOUR SYSTEM IF ON PC
 */
public class GameState implements Serializable {

    public GameState(){
    }
    
    public void saveGameDataToFile(Controller con){
        try{
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            File file = new File("./saves/"+ timeStamp + ".txt");
            FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            
                    objectStream.writeObject(con);
                    objectStream.close();
                    fileStream.close();
                    saveOptions(timeStamp, loadOptions().length);
                    System.out.println("Save game state successfully");
                } catch(Exception e){
                    System.out.println("Fail to save game state");
                }
}
    
    public Controller loadControllerFromFile(File file) throws ClassNotFoundException, IOException{
        FileInputStream fileStream = new FileInputStream(file);
        ObjectInputStream objectStream = new ObjectInputStream(fileStream);
        
        Controller con = (Controller) objectStream.readObject();
        
        objectStream.close();
        fileStream.close();
        System.out.println("Load game state successfully");
        return con;
    }

    public void saveOptions(String timeStamp, int count) throws IOException{

        if( count <= 6){
//        FileWriter fw = new FileWriter("C:\\Users\\phand\\Desktop\\CS205\\Final Project\\Sorry\\option.txt", true);
            FileWriter fw = new FileWriter("./saves/options.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            fw.append(timeStamp);
            bw.newLine();
            bw.close();
            fw.close();
        }
        else{
//            FileWriter fw = new FileWriter("C:\\Users\\phand\\Desktop\\CS205\\Final Project\\Sorry\\option.txt", false);
            FileWriter fw = new FileWriter("./saves/options.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);
            fw.append(timeStamp);
            bw.newLine();
            bw.close();
            fw.close();
        }
    }

    public String[] loadOptions() throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\phand\\Desktop\\CS205\\Final Project\\Sorry\\option.txt"));
        String str;

        List<String> list = new ArrayList<String>();
        while((str = in.readLine()) != null){
            list.add(str);
        }

        String[] stringArr = list.toArray(new String[0]);

        return stringArr;
    }
    
//    public GamePiece[] loadPlayerPieceFromFile(File file) throws ClassNotFoundException, IOException{
//        FileInputStream fileStream = new FileInputStream(file);
//        ObjectInputStream objectStream = new ObjectInputStream(fileStream);
//        GamePiece[] player = new GamePiece[4];
//        
//        player = (GamePiece[]) objectStream.readObject();
//        
//        objectStream.close();
//        fileStream.close();
//        System.out.println("Load game state successfully");
//        return player;
//    }
//    
//    public ArrayList<GamePiece[]> loadOpponentFromFile(File file) throws ClassNotFoundException, IOException{
//        FileInputStream fileStream = new FileInputStream(file);
//        ObjectInputStream objectStream = new ObjectInputStream(fileStream);
//        ArrayList<GamePiece[]> opponent = new ArrayList<GamePiece[]>();
//        
//        opponent = (ArrayList<GamePiece[]>) objectStream.readObject();
//        
//        objectStream.close();
//        fileStream.close();
//        System.out.println("Load game state successfully");
//        return opponent;
//    }
//    
//    public ArrayList<Card> loadCardFromFile(File file) throws ClassNotFoundException, IOException{
//        FileInputStream fileStream = new FileInputStream(file);
//        ObjectInputStream objectStream = new ObjectInputStream(fileStream);
//        ArrayList<Card> card = new ArrayList<Card>();
//        
//        card = (ArrayList<Card>) objectStream.readObject();
//        
//        objectStream.close();
//        fileStream.close();
//        System.out.println("Load game state successfully");
//        return card;
//    }
}
