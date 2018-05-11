
package filmlogger.dao;

import filmlogger.domain.*;
import java.sql.*;

/**
 * Class implements the UserDAO interface and handles saving to and retrieving information from the User table in the database.
 * 
 * @author emmalait
 */

public class DbUserDAO implements UserDAO {
    private Database database;
    
    /**
     * Constructor of the class, which sets the database accessed by it.
     * 
     * @param database 
     */
    
    public DbUserDAO(Database database) {
        this.database = database;
    }
    
    /**
     * Method creates a new Review instance in the database.
     * 
     * @param object
     * @throws SQLException 
     */

    @Override
    public void create(User object) throws SQLException {
        Connection conn = this.database.getConnection();
        PreparedStatement stmnt = conn.prepareStatement(
                "INSERT INTO User "
                        + "(name, username) "
                        + "VALUES (?, ?);"
        );
        stmnt.setString(1, object.getName());
        stmnt.setString(2, object.getUsername());
        
        stmnt.executeUpdate();
        
        stmnt.close();
        conn.close();
    }

    /**
     * Method retrieves a user from the database based on its id.
     * 
     * @param key
     * @return User
     * @throws SQLException 
     */
    
    @Override
    public User findById(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM User "
                    + "WHERE id = ?"
        );
        statement.setInt(1, key);

        ResultSet rs = statement.executeQuery();
        boolean hasOne = rs.next();
        
        if (!hasOne) {
            return null;
        }

        User user  = new User(rs.getInt("id"), rs.getString("name"), rs.getString("username"));
        
        rs.close();
        statement.close();
        connection.close();

        return user;
    }
    
    /**
     * Method retrieves a user from the database based on its username.
     * 
     * @param username
     * @return User
     * @throws SQLException 
     */
    
    @Override
    public User findByUsername(String username) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM User "
                        + "WHERE username = ?"
        );
        statement.setString(1, username);

        ResultSet rs = statement.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        User user  = new User(rs.getInt("id"), rs.getString("name"), rs.getString("username"));
        
        rs.close();
        statement.close();
        connection.close();

        return user;
    }

}
