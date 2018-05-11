
package filmlogger.dao;

import filmlogger.domain.*;
import java.sql.*;

public class DbTagDAO implements TagDAO {
    private Database database;
    
    public DbTagDAO(Database database) {
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

//    @Override
//    public List<Tag> findAll() throws SQLException {
//        Connection connection = this.database.getConnection();
//        PreparedStatement statement = connection.prepareStatement(
//                "SELECT * FROM Tag;"
//        );
//        
//        ResultSet rs = statement.executeQuery();
//        
//        List<Tag> tags = new ArrayList<>();
//        
//        while (rs.next()) {
//            int id = rs.getInt("id");
//            String name = rs.getString("name");
//            tags.add(new Tag(id, name));
//        }
//        
//        rs.close();
//        statement.close();
//        connection.close();
//        
//        return tags;
//    }
//
//    @Override
//    public void update(Tag object) throws SQLException {
//        Connection connection = database.getConnection();
//        PreparedStatement statement = connection.prepareStatement(
//                "UPDATE Tag SET "
//                        + "name = ? "
//                        + "WHERE id = ?;"
//        );
//
//        statement.setString(1, object.getName());
//        statement.setInt(2, object.getId());
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
//                "DELETE FROM Tag "
//                        + "WHERE id = ?;"
//        );
//
//        statement.setInt(1, key);
//        statement.executeUpdate();
//
//        statement.close();
//        connection.close();
//    }

    @Override
    public Tag getToWatch() throws SQLException {
        return findByName("toWatch");
    }

    @Override
    public Tag getSeen() throws SQLException {
        return findByName("seen");
    }
    
}
