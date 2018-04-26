package Game;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Database {
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public Database(){
        ResourceBundle properties = ResourceBundle.getBundle("MySqlConnect");
        String url = properties.getString("URL");
        String admin_user = properties.getString("ADMIN_USER");
        String admin_pass = properties.getString("ADMIN_PASS");
        try{

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, admin_user, admin_pass);
            st = con.createStatement();

        } catch(Exception ex){
            System.out.println("Error: " + ex);
        }
    }

    public void getData(){
        try{
            String query = "select * from class_info";
            rs = st.executeQuery(query);
            System.out.println("Records from the database");
            while(rs.next()){
                String clas = rs.getString("class");
                String time = rs.getString("time");
                System.out.println("Class: " +clas+ " Time: " +time);
            }


        } catch(Exception ex){
            System.out.println(ex);
        }
    }

    public void saveGameData(String userName, float score){
        Map<String, Float> dictionary = new HashMap<String, Float>();
        dictionary = loadGameData();

            try {
                Statement st = con.createStatement();
                            if(dictionary.containsKey(userName)){
                                float newScore = dictionary.get(userName) + score;
                                String query = "UPDATE tblGameInfo SET fldScore = ?, fldTime = CURRENT_TIMESTAMP WHERE fldUserName = ?";

                                PreparedStatement preparedStmt = con.prepareStatement(query);
                                preparedStmt.setFloat(1, newScore);
                                preparedStmt.setString(2, userName);

                                preparedStmt.executeUpdate();
                                preparedStmt.close();
                            }
                            else{
                                String query = "insert into tblGameInfo(fldUserName, fldScore) " +
                                        "values  (?, ?)";


                                        PreparedStatement preparedStmt = con.prepareStatement(query);
                                        preparedStmt.setString(1, userName);
                                        preparedStmt.setFloat(2, score);


                                        preparedStmt.execute();
                                        preparedStmt.close();
                            }


                }
             catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

}

    public void loadGameByUser(String userName){
        try {
            Statement st = con.createStatement();

            String query = "SELECT pmkGameId, fldUsername, fldTime, fldScore FROM tblGameInfo where fldUsername = '" + userName + "'";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                int id = rs.getInt("pmkGameId");
                String user = rs.getString("fldUsername");
                Timestamp t = rs.getTimestamp("fldTime");
                int score = rs.getInt("fldScore");

                System.out.format( id + " " + user + " " + t.toString() + " " + score + "\n");

            }
            st.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public Map<String, Float> loadGameData(){
        Map<String, Float> dictionary = new LinkedHashMap<String, Float>();
        try {
            Statement st = con.createStatement();

            String query = "SELECT * FROM tblGameInfo ORDER BY fldScore DESC";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
//                int id = rs.getInt("pmkGameId");
//                String user = rs.getString("fldUsername");
//                Timestamp t = rs.getTimestamp("fldTime");
//                int score = rs.getInt("fldScore");

                dictionary.put(rs.getString("fldUsername"), rs.getFloat("fldScore"));

//                System.out.format("%s, %s,\n");

            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return dictionary;
    }

    public static void printMap(Map mp) {
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }
}

