package Game;
import java.sql.*;
import java.util.ArrayList;
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
    
    public void saveGameData(String userName, int score){
        
            try {
                Statement st = con.createStatement();
                            
                            String query = "insert into tblGameInfo(fldUserName, fldScore) " + 
                            "values  (?, ?)";
                           
                            
                            PreparedStatement preparedStmt = con.prepareStatement(query);
                            preparedStmt.setString(1, userName);
                            preparedStmt.setInt(2, score);
                            
                            
                            preparedStmt.execute();
                                              
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
    
    public void loadGameData(){
        try {
            Statement st = con.createStatement();
            
            String query = "SELECT * FROM tblGameInfo";
            
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()){
                int id = rs.getInt("pmkGameId");
                String user = rs.getString("fldUsername");
                Timestamp t = rs.getTimestamp("fldTime");
                int score = rs.getInt("fldScore");
                
                System.out.format("%s, %s, %s, %s\n", id, user, t, score);
            
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

