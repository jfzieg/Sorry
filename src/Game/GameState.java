package Game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

public class GameState implements Serializable {

    public GameState(){
    }

    public void saveGameDataToFile(Controller con){
        try{
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String workingDir = System.getProperty("user.dir");

            File file = new File( workingDir + "\\" + timeStamp +".txt");
            if(openOptions() == true){

            String[] arr = loadOptions();

            saveOptions(timeStamp, arr.length);
            } else{
                saveOptions(timeStamp, 0);
            }

            FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

                    objectStream.writeObject(con);
                    objectStream.close();
                    fileStream.close();

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
        String workingDir = System.getProperty("user.dir");

        if( count <= 6){
        FileWriter fw = new FileWriter(workingDir + "\\" + "option.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        fw.append(timeStamp);
        bw.newLine();
        bw.close();
        fw.close();
        }
        else{
            FileWriter fw = new FileWriter(workingDir + "\\" + "option.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);
            fw.append(timeStamp);
            bw.newLine();
            bw.close();
            fw.close();
        }
    }

    public String[] loadOptions() throws IOException{
        String workingDir = System.getProperty("user.dir");
        BufferedReader in = new BufferedReader(new FileReader( workingDir + "\\" + "option.txt"));
        String str;

        List<String> list = new ArrayList<String>();
        while((str = in.readLine()) != null){
            list.add(str);
        }

        String[] stringArr = list.toArray(new String[0]);

        return stringArr;
    }

    public boolean openOptions(){
        String workingDir = System.getProperty("user.dir");
        try {
            BufferedReader in = new BufferedReader(new FileReader( workingDir + "\\" + "option.txt"));
            return true;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            return false;
        }
    }

}
