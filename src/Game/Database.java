package Game;

import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Database {
    private Connection con;
    private Statement st;
    private ResultSet rs;

    /**
     * Constructor for the database Connect to the database
     *
     */
    public Database() {
        ResourceBundle properties = ResourceBundle.getBundle("MySqlConnect");
        String url = properties.getString("URL");
        String admin_user = properties.getString("ADMIN_USER");
        String admin_pass = properties.getString("ADMIN_PASS");
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, admin_user, admin_pass);
            st = con.createStatement();

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

    /**
     * Insert user name and score to the database If user name is already in the
     * database, update the score for that user
     *
     * @param userName
     * @param score
     */
    public void saveGameData(String userName, float score) {
        Map<String, Float> dictionary = new HashMap<String, Float>();
        dictionary = loadGameData();

        try {
            Statement st = con.createStatement();
            if (dictionary.containsKey(userName)) {
                float newScore = dictionary.get(userName) + score;
                String query = "UPDATE tblGameInfo SET fldScore = ?, fldTime = CURRENT_TIMESTAMP WHERE fldUserName = ?";

                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setFloat(1, newScore);
                preparedStmt.setString(2, userName);

                preparedStmt.executeUpdate();
                preparedStmt.close();
            } else {
                String query = "insert into tblGameInfo(fldUserName, fldScore) " + "values  (?, ?)";

                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1, userName);
                preparedStmt.setFloat(2, score);

                preparedStmt.execute();
                preparedStmt.close();
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Load the data by a specific user
     *
     * @param userName
     */
    public void loadGameByUser(String userName) {
        try {
            Statement st = con.createStatement();

            String query = "SELECT pmkGameId, fldUsername, fldTime, fldScore FROM tblGameInfo where fldUsername = '"
                    + userName + "'";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("pmkGameId");
                String user = rs.getString("fldUsername");
                Timestamp t = rs.getTimestamp("fldTime");
                int score = rs.getInt("fldScore");

                System.out.format(id + " " + user + " " + t.toString() + " " + score + "\n");

            }
            st.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Load all data and put the data to the map User name will be keys and
     * score will be values
     *
     * @return a LinkedHashMap that contains key as user name and value as
     *         user's score
     */
    public Map<String, Float> loadGameData() {
        Map<String, Float> dictionary = new LinkedHashMap<String, Float>();
        try {
            Statement st = con.createStatement();

            String query = "SELECT * FROM tblGameInfo ORDER BY fldScore DESC";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                dictionary.put(rs.getString("fldUsername"), rs.getFloat("fldScore"));

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return dictionary;
    }

    /**
     * Functions to print both value and key in a map
     *
     * @param mp
     */
    public static void printMap(Map mp) {
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }
}
