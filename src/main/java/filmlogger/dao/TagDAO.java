
package filmlogger.dao;

import filmlogger.database.*;
import filmlogger.domain.*;
import java.sql.*;
import java.util.*;

public class TagDAO implements DAO<Tag, Integer> {
    private Database database;
    
    public TagDAO(Database database) {
        this.database = database;
    }

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

    @Override
    public Tag find(Integer key) throws SQLException {
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
    
    @Override
    public Tag find(String name) throws SQLException {
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

    @Override
    public List<Tag> findAll() throws SQLException {
        Connection connection = this.database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Tag;"
        );
        
        ResultSet rs = statement.executeQuery();
        
        List<Tag> tags = new ArrayList<>();
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            tags.add(new Tag(id, name));
        }
        
        rs.close();
        statement.close();
        connection.close();
        
        return tags;
    }

    @Override
    public void update(Tag object) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE Tag SET "
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
                "DELETE FROM Tag "
                        + "WHERE id = ?;"
        );

        statement.setInt(1, key);
        statement.executeUpdate();

        statement.close();
        connection.close();
    }
    
}
