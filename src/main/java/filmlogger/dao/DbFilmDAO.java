
package filmlogger.dao;

import filmlogger.domain.*;
import java.sql.*;

/**
 * Class implements the FilmDAO interface and handles saving to and retrieving information from the Film table in the database.
 * 
 * @author emmalait
 */

public class DbFilmDAO implements FilmDAO {
    private Database database;
    
    /**
     * Constructor of the class, which sets the database accessed by it.
     * @param database 
     */
    
    public DbFilmDAO(Database database) {
        this.database = database;
    }

    /**
     * Method creates a new Film instance in the database.
     * @param object 
     * @throws SQLException 
     */
    
    @Override
    public void create(Film object) throws SQLException {
        Connection connection = this.database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Film "
                        + "(name, year) "
                        + "VALUES (?, ?);"
        );
        
        statement.setString(1, object.getName());
        statement.setString(2, object.getYear());
        statement.executeUpdate();
        
        statement.close();
        connection.close();
    }
    
    /**
     * Method retrieves a film from the database based on its id.
     * 
     * @param key
     * @return Film
     * @throws SQLException 
     */
    
    @Override
    public Film findById(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Film "
                        + "WHERE id = ?"
        );
        statement.setInt(1, key);

        ResultSet rs = statement.executeQuery();
        boolean hasOne = rs.next();
        
        if (!hasOne) {
            return null;
        }

        Film film  = new Film(rs.getInt("id"), rs.getString("name"), rs.getString("year"));
        
        rs.close();
        statement.close();
        connection.close();

        return film;
    }
    
    /**
     * Method retrieves a film from the database based on its name.
     * @param name
     * @return Film
     * @throws SQLException 
     */
    
    @Override
    public Film findByName(String name) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Film "
                        + "WHERE name = ?"
        );
        statement.setString(1, name);

        ResultSet rs = statement.executeQuery();
        boolean hasOne = rs.next();
        
        if (!hasOne) {
            return null;
        }

        Film film  = new Film(rs.getInt("id"), rs.getString("name"), rs.getString("year"));
        
        rs.close();
        statement.close();
        connection.close();

        return film;
    }

}
