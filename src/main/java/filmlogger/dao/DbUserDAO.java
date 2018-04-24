
package filmlogger.dao;

import filmlogger.domain.*;
import java.sql.*;
import java.util.*;

public class DbUserDAO implements UserDAO<User, Integer> {
    private Database database;
    
    public DbUserDAO(Database database) {
        this.database = database;
    }

    @Override
    public void create(User object) throws SQLException {
        Connection conn = this.database.getConnection();
        PreparedStatement stmnt = conn.prepareStatement(
                "INSERT INTO User "
                        + "(name) "
                        + "VALUES (?);"
        );
        stmnt.setString(1, object.getName());
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

        User user  = new User(rs.getInt("id"), rs.getString("name"));
        
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
                        + "WHERE name = ?"
        );
        statement.setString(1, username);

        ResultSet rs = statement.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        User user  = new User(rs.getInt("id"), rs.getString("name"));
        
        rs.close();
        statement.close();
        connection.close();

        return user;
    }

    @Override
    public List<User> findAll() throws SQLException {
        Connection connection = this.database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM User;"
        );
        
        ResultSet rs = statement.executeQuery();
        
        List<User> users = new ArrayList<>();
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            users.add(new User(id, name));
        }
        
        rs.close();
        statement.close();
        connection.close();
        
        return users;
    }

    @Override
    public void update(User object) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE User SET "
                        + "name = ? "
                        + "WHERE id = ?;"
        );

        statement.setString(1, object.getName());
        statement.setInt(2, object.getId());
        statement.executeUpdate();

        statement.close();
        connection.close();
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM User "
                        + "WHERE id = ?;"
        );

        statement.setInt(1, key);
        statement.executeUpdate();

        statement.close();
        connection.close();
    }

}
