package Game;

import java.util.HashMap;
import java.util.Map;

public class Test_Database {
    
    static void saveDatabase(Database db){
//        db.saveGameData("Hello", 60);
        db.saveGameData("Hello", 50);
        db.saveGameData("Test", 30);
        
        System.out.println("Database saved");
    }
    
    static void loadDatabaseByUser(Database db, String name){
        db.loadGameByUser(name);
        System.out.println("Done loading");
    }
    
    static void loadDatabase(Database db){
        db.loadGameData();
        System.out.println("Done loading");
    }
    public static void main (String[] args) {
        
        Database db = new Database();
        //Test load data by user name
//        loadDatabaseByUser(db, "Hello");
        System.out.println();
        
        
        saveDatabase(db);
        //Test load whole database
//        Map<String, Float> dictionary = new HashMap<String, Float>();
//        dictionary = db.loadGameData();
//        db.printMap(dictionary);
        
        
    }
}
