package Game;
import java.sql.*;


public class Database {
    private Connection cn;
    private Statement st;
    private ResultSet rs;
    public static final String ADMIN_USER = "aphan_admin";
    public static final String ADMIN_PASS = "q8zRXwQMqOpl";
    public static final String READER_USER = "aphan_reader";
    public static final String READER_PASS = "KI8o7SuBuTLe";
    public static final String WRITER_USER = "aphan_writer";
    public static final String WRITE_PASS = "ZgUXmB5wCSSg";

    public Database(){
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://webdb.uvm.edu:3306/APHAN_cs275", ADMIN_USER, ADMIN_PASS);
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
}

