
package filmlogger.dao;

import filmlogger.database.*;
import filmlogger.domain.*;
import java.sql.*;
import java.util.*;

public class FilmDAO implements DAO<Film, Integer> {
    private Database database;
    
    public FilmDAO(Database database) {
        this.database = database;
    }

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

    @Override
    public Film find(Integer key) throws SQLException {
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
    
    @Override
    public Film find(String name) throws SQLException {
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

    @Override
    public List<Film> findAll() throws SQLException {
        Connection connection = this.database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Film;"
        );
        
        ResultSet rs = statement.executeQuery();
        
        List<Film> films = new ArrayList<>();
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String year = rs.getString("year");
            films.add(new Film(id, name, year));
        }
        
        rs.close();
        statement.close();
        connection.close();
        
        return films;
    }

    @Override
    public void update(Film object) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE Film SET "
                        + "name = ?, "
                        + "year = ? "
                        + "WHERE id = ?;"
        );

        statement.setString(1, object.getName());
        statement.setString(2, object.getYear());
        statement.setInt(3, object.getId());
        statement.executeUpdate();

        statement.close();
        connection.close();    
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM Film "
                        + "WHERE id = ?;"
        );

        statement.setInt(1, key);
        statement.executeUpdate();

        statement.close();
        connection.close();
    }
    
}
