
package filmlogger.dao;

import filmlogger.database.*;
import filmlogger.domain.*;
import java.sql.*;
import java.util.List;

public class UserDAO implements DAO<User, Integer> {
    private Database database;
    
    public UserDAO(Database database) {
        this.database = database;
    }

    @Override
    public void create(User object) throws SQLException {
        Connection conn = this.database.getConnection();
        PreparedStatement stmnt = conn.prepareStatement(
                "INSERT INTO User " +
                        "(name) " +
                        "VALUES (?);"
        );
        stmnt.setString(1, object.getName());
        stmnt.executeUpdate();
        
        stmnt.close();
        conn.close();
    }

    @Override
    public User find(Integer key) throws SQLException {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public User find(String name) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmnt = conn.prepareStatement("SELECT * FROM User WHERE name = ?");
        stmnt.setString(1, name);

        ResultSet rs = stmnt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        User user  = new User(rs.getInt("id"), rs.getString("name"));
        
        rs.close();
        stmnt.close();
        conn.close();

        return user;
    }

    @Override
    public List<User> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(User object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
