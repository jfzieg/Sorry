package Game;

import java.util.HashMap;
import java.util.Map;

public class Test_Database {

    // Testing save database
    static void saveDatabase(Database db) {
        db.saveGameData("Hello", 60);
        db.saveGameData("Hello", 50);
        db.saveGameData("Test", 30);

        System.out.println("Database saved");
    }

    // Testing load data by user
    static void loadDatabaseByUser(Database db, String name) {
        db.loadGameByUser(name);
        System.out.println("Done loading");
    }

    // Testing load the whole data
    static Map<String, Float> loadDatabase(Database db) {
        Map<String, Float> dictionary = new HashMap<String, Float>();
        dictionary = db.loadGameData();
        System.out.println("Done loading");
        return dictionary;
    }

    public static void main(String[] args) {

        Database db = new Database();

        // Test load data by user name
        loadDatabaseByUser(db, "Hello");
        System.out.println();

        // Test save data
        saveDatabase(db);

        // Test load whole database then print the new map
        Map<String, Float> dictionary = loadDatabase(db);
        db.printMap(dictionary);

    }
}
