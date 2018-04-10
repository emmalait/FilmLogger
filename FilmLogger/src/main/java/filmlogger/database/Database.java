
package filmlogger.database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private String databaseAddress = "jdbc:sqlite:filmlogger.db";
    
    public Database() {
        this.createTables();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }
    
    public void createTables() {
        try {
            Connection conn = getConnection();
            Statement stmnt = conn.createStatement();
            
            stmnt.execute("CREATE TABLE IF NOT EXISTS User ("
                    + "id integer PRIMARY KEY, "
                    + "name varchar(15));");
            
            stmnt.execute("CREATE TABLE IF NOT EXISTS Film ("
                    + "id integer PRIMARY KEY, "
                    + "name varchar, "
                    + "year varchar(4));");
            
            stmnt.execute("CREATE TABLE IF NOT EXISTS Tag ("
                    + "id integer PRIMARY KEY, "
                    + "name varchar(20));");
            
            stmnt.execute("CREATE TABLE IF NOT EXISTS Review ("
                    + "id integer PRIMARY KEY, "
                    + "user_id integer, "
                    + "film_id integer, "
                    + "rating integer, "
                    + "review varchar(1000), "
                    + "FOREIGN KEY(user_id) REFERENCES User(id), "
                    + "FOREIGN KEY(film_id) REFERENCES Film(id));");
            
            stmnt.close();
            conn.close();       
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
