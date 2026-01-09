
package lopezJohnDataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class lopezJohnDataHelperSQLite {
    private static final String DBPathConnection = "jdbc:sqlite:DataBase//EXOBOT.sqlite"; 
    private static Connection conn = null;
    
    private DateTimeFormatter   dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
    private LocalDateTime       now = LocalDateTime.now();

    protected lopezJohnDataHelperSQLite(){}
    protected static synchronized Connection openConnection() throws Exception{
        try {
            if(conn == null)
                conn = DriverManager.getConnection(DBPathConnection);
        } catch (SQLException e) {
            throw e; //new Exception(e,"SQLiteDataHelper","Fallo la conexion a la base de datos");
        } 
        return conn;
    }

    protected static void closeConnection() throws Exception{
        try {
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            throw e;//new Exception(e,"SQLiteDataHelper", "Fallo la conexion con la base de datos");
        }
    }

    protected String getDataTimeNow() {
        return dtf.format(now).toString();
    }
    
}
