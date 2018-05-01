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

public class GameState implements Serializable {

    /**
     * GameState Constructor
     */
    public GameState() {
    }

    /**
     * Save a whole controller to a text file Text file will be name as the
     * current timeStamp The timeStamp will also be stored in an another text
     * file to keep track of different saved file
     *
     * change fw for Mac vs Windows
     *
     * @param con
     * @param timeStamp
     */
    public void saveGameDataToFile(Controller con, String timeStamp) {
        try {
//            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String workingDir = System.getProperty("user.dir");

            File file = new File(workingDir + "/" + timeStamp + ".txt");
            //        File file = new File(workingDir + "\\" + timeStamp + ".txt");
            if (openOptions() == true) {

                String[] arr = loadOptions();

                saveOptions(timeStamp, arr.length);
            } else {
                saveOptions(timeStamp, 0);
            }

            FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

            objectStream.writeObject(con);
            objectStream.close();
            fileStream.close();

            System.out.println("Save game state successfully");
        } catch (Exception e) {
            System.out.println("Fail to save game state");
        }
    }

    /**
     * Load the whole controller to a new controller A correct timeStamp string
     * need to be passed in in order to load
     *
     * change fw for Mac vs Windows
     *
     * @param time
     * @return a new controller that contains all the saved data
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public Controller loadControllerFromFile(String time) throws ClassNotFoundException, IOException {
        String workingDir = System.getProperty("user.dir");
        File file = new File(workingDir + "/" + time + ".txt");
//        File file = new File(workingDir + "\\" + time + ".txt");
        FileInputStream fileStream = new FileInputStream(file);
        ObjectInputStream objectStream = new ObjectInputStream(fileStream);

        Controller con = (Controller) objectStream.readObject();

        objectStream.close();
        fileStream.close();
        System.out.println("Load game state successfully");
        return con;
    }

    /**
     * Save all the name of the saved file to one file If the file has more than
     * 6 saved name All the name will be deleted and a new saved name will be
     * put in
     *
     * change fw for Mac vs Windows
     *
     * @param timeStamp
     * @param count
     * @throws IOException
     */
    public void saveOptions(String timeStamp, int count) throws IOException {
        String workingDir = System.getProperty("user.dir");

        if (count <= 6) {
            FileWriter fw = new FileWriter(workingDir + "/" + "options.txt", true);
//            FileWriter fw = new FileWriter(workingDir + "\\options.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            fw.append(timeStamp);
            bw.newLine();
            bw.close();
            fw.close();
        } else {
            FileWriter fw = new FileWriter(workingDir + "\\" + "option.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);
            fw.append(timeStamp);
            bw.newLine();
            bw.close();
            fw.close();
        }
    }

    /**
     * Load the all the name for the saved file and have them in an string array
     * This array will be used to display all the save in the loadMenu
     *
     * change fw for Mac vs Windows
     *
     * @return a array of string
     * @throws IOException
     */
    public String[] loadOptions() throws IOException {
        String workingDir = System.getProperty("user.dir");
//        BufferedReader in = new BufferedReader(new FileReader(workingDir + "\\" + "options.txt"));
        BufferedReader in = new BufferedReader(new FileReader(  workingDir + "/options.txt"));
        String str;

        List<String> list = new ArrayList<String>();
        while ((str = in.readLine()) != null) {
            list.add(str);
        }

        String[] stringArr = list.toArray(new String[0]);

        return stringArr;
    }

    /**
     * Make sure if there is a option.txt file if there is, return true,
     * otherwise false
     *
     * in is
     *
     * @return a boolean
     */
    public boolean openOptions() {
        String workingDir = System.getProperty("user.dir");
        try {
//            BufferedReader in = new BufferedReader(new FileReader(workingDir + "\\" + "options.txt"));
            BufferedReader in = new BufferedReader(new FileReader(workingDir +  "/options.txt"));
            return true;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            return false;
        }
    }

}
