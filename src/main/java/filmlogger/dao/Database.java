
package filmlogger.dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private String databaseAddress = "jdbc:sqlite:filmlogger.db";
    
    public Database() {
        this.createTables();
        this.createTags();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }
    
    public void createTables() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            
            statement.execute("CREATE TABLE IF NOT EXISTS User ("
                    + "id integer PRIMARY KEY, "
                    + "name varchar(15));");
            
            statement.execute("CREATE TABLE IF NOT EXISTS Film ("
                    + "id integer PRIMARY KEY, "
                    + "name varchar, "
                    + "year varchar(4));");
            
            statement.execute("CREATE TABLE IF NOT EXISTS Tag ("
                    + "id integer PRIMARY KEY, "
                    + "name varchar(20) UNIQUE);");
            
            statement.execute("CREATE TABLE IF NOT EXISTS Review ("
                    + "id integer PRIMARY KEY, "
                    + "user_id integer, "
                    + "film_id integer, "
                    + "tag_id integer, "
                    + "date date, "
                    + "rating integer, "
                    + "review varchar(1000), "
                    + "FOREIGN KEY(user_id) REFERENCES User(id), "
                    + "FOREIGN KEY(film_id) REFERENCES Film(id),"
                    + "FOREIGN KEY(tag_id) REFERENCES Tag(id));");
            
            statement.close();
            connection.close();       
        } catch (SQLException ex) {
            
        }
    }
    
    public void createTags() {              
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Tag "
                            + "(name) "
                            + "VALUES (?);"
            );
      
            statement.setString(1, "toWatch");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Tag "
                            + "(name) "
                            + "VALUES (?);"
            );
            
            statement.setString(1, "seen");
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            
        }
    }
   
}
