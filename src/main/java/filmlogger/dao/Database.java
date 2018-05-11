
package filmlogger.dao;

import java.sql.*;


/**
 * Class initialises the database and offers a connection to it.
 * 
 * @author emmalait
 */

public class Database {
    private String databaseAddress;
    
    /**
     * Constructor for the class, which sets the database address.
     * @param databaseAddress 
     */
    
    public Database(String databaseAddress) {
        this.databaseAddress = databaseAddress;
        this.createTables();
        this.createTags();
    }
    
    /**
     * Method for getting a connection to the database.
     * @return Connection to database
     * @throws SQLException 
     */

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }
    
    /**
     * Method creates tables for the databases if they do not exist.
     */
    
    public void createTables() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            
            statement.execute("CREATE TABLE IF NOT EXISTS User (id integer PRIMARY KEY, name varchar, username varchar(15));");
            statement.execute("CREATE TABLE IF NOT EXISTS Film (id integer PRIMARY KEY, name varchar, year varchar(4));");
            statement.execute("CREATE TABLE IF NOT EXISTS Tag (id integer PRIMARY KEY, name varchar(20));");
            statement.execute("CREATE TABLE IF NOT EXISTS Review (id integer PRIMARY KEY, user_id integer, film_id integer, tag_id integer, date date, rating integer, review varchar(1000), "
                    + "FOREIGN KEY(user_id) REFERENCES User(id), "
                    + "FOREIGN KEY(film_id) REFERENCES Film(id),"
                    + "FOREIGN KEY(tag_id) REFERENCES Tag(id));");
            
            statement.close();
            connection.close(); 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    /**
     * Method adds the "to watch" and "seen" tags to the Tag table.
     */
    
    public void createTags() {              
        try {
            Connection connection = getConnection();

            if (!checkToWatch()) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO Tag (name) VALUES (?);");
                statement.setString(1, "toWatch");
                statement.executeUpdate();
                statement.close();
            }
            
            if (!checkSeen()) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO Tag (name) VALUES (?);");
                statement.setString(1, "seen");
                statement.executeUpdate();
                statement.close();
            }

            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    /**
     * Method checks if "to watch" tag is already in the Tag table.
     * 
     * @return true if exists, otherwise false
     * @throws SQLException 
     */
    
    public boolean checkToWatch() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Tag WHERE name = 'toWatch';");

        ResultSet rs = statement.executeQuery();
        boolean hasOne = rs.next();
        boolean result = true;
        
        if (!hasOne) {        
            result = false;
        }

        rs.close();
        statement.close();
        connection.close();
        
        return result;
    }
    
    /**
     * Method checks if "seen" tag is already in the Tag table.
     * 
     * @return true if exists, otherwise false
     * @throws SQLException 
     */
    
    public boolean checkSeen() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Tag WHERE name = 'seen';");

        ResultSet rs = statement.executeQuery();
        boolean hasOne = rs.next();
        boolean result = true;
        
        if (!hasOne) {        
            result = false;
        }

        rs.close();
        statement.close();
        connection.close();
        
        return result;
    }
   
}
