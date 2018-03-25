package Game;
import java.sql.*;
import java.util.ResourceBundle;

public class Database {
    private Connection cn;
    private Statement st;
    private ResultSet rs;

    public Database(){
        ResourceBundle properties = ResourceBundle.getBundle("MySqlConnect");
        String url = properties.getString("URL");
        String admin_user = properties.getString("ADMIN_USER");
        String admin_pass = properties.getString("ADMIN_PASS");
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(url, admin_user, admin_pass);
            st = cn.createStatement();
            
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
    
    public void savePieceInfo(){
        
    }
    public void getPieceInfo(){
        
    }
    public void saveCardInfo(){
        
    }
    public void getCardInfo(){
        
    }
    public void saveHomeInfo(){
        
    }
    public void getHomeInfo(){
        
    }
}

