
package filmlogger.dao;

import filmlogger.domain.*;
import java.sql.*;

/**
 * Class implements the TagDAO interface and handles saving to and retrieving information from the Tag table in the database.
 * 
 * @author emmalait
 */

public class DbTagDAO implements TagDAO {
    private Database database;
    
    /**
     * Constructor of the class, which sets the database accessed by it.
     * 
     * @param database 
     */
    
    public DbTagDAO(Database database) {
        this.database = database;
    }

    /**
     * Method creates a new Tag instance in the database.
     * 
     * @param object
     * @throws SQLException 
     */
    
    @Override
    public void create(Tag object) throws SQLException {
        Connection connection = this.database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Tag " +
                        "(id, name) " +
                        "VALUES (?, ?);"
        );
        statement.setInt(1, object.getId());
        statement.setString(2, object.getName());
        
        statement.executeUpdate();
        
        statement.close();
        connection.close();
    }

    /**
     * Method retrieves a tag from the database based on its id.
     * 
     * @param key
     * @return Tag
     * @throws SQLException 
     */
    
    @Override
    public Tag findById(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Tag "
                        + "WHERE id = ?"
        );
        statement.setInt(1, key);

        ResultSet rs = statement.executeQuery();
        boolean hasOne = rs.next();
        
        if (!hasOne) {
            return null;
        }

        Tag tag  = new Tag(rs.getInt("id"), rs.getString("name"));
        
        rs.close();
        statement.close();
        connection.close();

        return tag;
    }
    
    /**
     * Method retrieves a tag from the database based on its name.
     * 
     * @param name
     * @return Tag
     * @throws SQLException 
     */
    
    @Override
    public Tag findByName(String name) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Tag "
                        + "WHERE name = ?"
        );
        statement.setString(1, name);

        ResultSet rs = statement.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Tag tag  = new Tag(rs.getInt("id"), rs.getString("name"));
        
        rs.close();
        statement.close();
        connection.close();

        return tag;
    }

    /**
     * Method retrieves the "to watch" tag.
     * 
     * @return "to watch" tag
     * @throws SQLException 
     */

    @Override
    public Tag getToWatch() throws SQLException {
        return findByName("toWatch");
    }
    
    /**
     * Method retrieves the "seen" tag.
     * 
     * @return "seen" tag
     * @throws SQLException 
     */

    @Override
    public Tag getSeen() throws SQLException {
        return findByName("seen");
    }
    
}
