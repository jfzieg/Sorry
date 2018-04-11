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
    
    public void savePieceInfo(GamePiece[][] tileList, GamePiece[][] homeList, int numberOfPause, String userName){
        
        if (numberOfPause == 1){
            try {
                Statement st = con.createStatement();
                for(int i = 0; i < 4; i++){
                    for(int j = 0; j < 16; j++){
                        if(tileList[i][j] != null){
                            int color = tileList[i][j].getBoardSide();
                            
                            String query = "insert into tblPieceInfo(pmkUserName, fldColor, fldLocation, fldTileNum, fldTileType) " + 
                            "values  (?, ?, ?, ?, ?)";
                           
                            
                            PreparedStatement preparedStmt = con.prepareStatement(query);
                            preparedStmt.setString(1, userName);
                            preparedStmt.setInt(2, color);
                            preparedStmt.setInt(3, j);
                            preparedStmt.setInt(4, i);
                            preparedStmt.setInt(5, 0);
                            
                            preparedStmt.execute();
                            
                        }
                    }
                    
                    for(int j = 0; j < 6; j++){
                        if(tileList[i][j] != null){
                            int color = tileList[i][j].getBoardSide();
                            
                            String query = "insert into tblPieceInfo(pmkUserName, fldColor, fldLocation, fldTileNum, fldTileType) " + 
                            "values  (?, ?, ?, ?, ?)";
                           
                            
                            PreparedStatement preparedStmt = con.prepareStatement(query);
                            preparedStmt.setString(1, userName);
                            preparedStmt.setInt(2, color);
                            preparedStmt.setInt(3, j);
                            preparedStmt.setInt(4, i);
                            preparedStmt.setInt(5, 1);
                            
                            preparedStmt.execute();
                            
                        }
                    }
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else{
            try {
                for(int i = 0; i < 4; i++){
                    for(int j = 0; j < 16; j++){
                        if(tileList[i][j] != null){
                            int color = tileList[i][j].getBoardSide();
                            
                            String query = "update tblPieceInfo set fldLocation = ?, fldTileNum = ?, fldTileType = ?, fldColor = ?, pmkTime = CURRENT_TIMESTAMP where"
                                    + " pmkUserName = ?";
                            
                            PreparedStatement preparedStmt = con.prepareStatement(query);
                            preparedStmt.setInt(1, j );
                            preparedStmt.setInt(2, i);
                            preparedStmt.setInt(3, 0);
                            preparedStmt.setInt(4, color);
                            preparedStmt.setString(5, userName);
                            
                            preparedStmt.executeUpdate();
                            
                        }
                        
                        }
                    for(int j = 0; j < 6; j++){
                        if(tileList[i][j] != null){
                            int color = tileList[i][j].getBoardSide();
                            
                            String query = "update tblPieceInfo set fldLocation = ?, fldTileNum = ?, fldTileType = ?, fldColor = ?, pmkTime = CURRENT_TIMESTAMP where"
                                    + "pmkUserName = ?";
                            
                            PreparedStatement preparedStmt = con.prepareStatement(query);
                            preparedStmt.setInt(1, j );
                            preparedStmt.setInt(2, i);
                            preparedStmt.setInt(3, 1);
                            preparedStmt.setInt(4, color);
                            preparedStmt.setString(5, userName);
                            
                            preparedStmt.executeUpdate();
                            
                        }
                        
                        }
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
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

