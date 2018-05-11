
package filmlogger.dao;

import filmlogger.domain.*;
import java.sql.*;
import java.util.*;

public class DbUserDAO implements UserDAO {
    private Database database;
    
    public DbUserDAO(Database database) {
        this.database = database;
    }

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

//    @Override
//    public List<User> findAll() throws SQLException {
//        Connection connection = this.database.getConnection();
//        PreparedStatement statement = connection.prepareStatement(
//                "SELECT * FROM User;"
//        );
//        
//        ResultSet rs = statement.executeQuery();
//        List<User> users = new ArrayList<>();
//        
//        while (rs.next()) {
//            users.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("username")));
//        }
//        
//        rs.close();
//        statement.close();
//        connection.close();
//        
//        return users;
//    }
//
//    @Override
//    public void update(User object) throws SQLException {
//        Connection connection = database.getConnection();
//        PreparedStatement statement = connection.prepareStatement(
//                "UPDATE User SET "
//                        + "name = ? "
//                        + "username = ? "
//                        + "WHERE id = ?;"
//        );
//
//        statement.setString(1, object.getName());
//        statement.setString(2, object.getUsername());
//        statement.setInt(3, object.getId());
//        statement.executeUpdate();
//
//        statement.close();
//        connection.close();
//    }
//
//    @Override
//    public void delete(Integer key) throws SQLException {
//        Connection connection = database.getConnection();
//        PreparedStatement statement = connection.prepareStatement(
//                "DELETE FROM User "
//                        + "WHERE id = ?;"
//        );
//
//        statement.setInt(1, key);
//        statement.executeUpdate();
//
//        statement.close();
//        connection.close();
//    }

}
