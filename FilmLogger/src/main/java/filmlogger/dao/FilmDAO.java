
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
        Connection conn = this.database.getConnection();
        PreparedStatement stmnt = conn.prepareStatement(
                "INSERT INTO Film " +
                        "(name, year) " +
                        "VALUES (?, ?);"
        );
        stmnt.setString(1, object.getName());
        stmnt.setString(2, object.getYear());
        stmnt.executeUpdate();
        
        stmnt.close();
        conn.close();
    }

    @Override
    public Film find(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmnt = conn.prepareStatement("SELECT * FROM Film WHERE id = ?");
        stmnt.setInt(1, key);

        ResultSet rs = stmnt.executeQuery();
        boolean hasOne = rs.next();
        
        if (!hasOne) {
            return null;
        }

        Film film  = new Film(rs.getInt("id"), rs.getString("name"), rs.getString("year"));
        
        rs.close();
        stmnt.close();
        conn.close();

        return film;
    }
    
    @Override
    public Film find(String name) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmnt = conn.prepareStatement("SELECT * FROM Film WHERE name = ?");
        stmnt.setString(1, name);

        ResultSet rs = stmnt.executeQuery();
        boolean hasOne = rs.next();
        
        if (!hasOne) {
            return null;
        }

        Film film  = new Film(rs.getInt("id"), rs.getString("name"), rs.getString("year"));
        
        rs.close();
        stmnt.close();
        conn.close();

        return film;
    }

    @Override
    public List<Film> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Film object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
