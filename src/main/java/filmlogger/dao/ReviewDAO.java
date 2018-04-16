
package filmlogger.dao;

import filmlogger.database.*;
import filmlogger.domain.*;
import java.sql.*;
import java.time.*;
import java.util.*;

public class ReviewDAO implements DAO<Review, Integer> {
    private Database database;
    
    public ReviewDAO(Database database) {
        this.database = database;
    }

    @Override
    public void create(Review object) throws SQLException {
        Connection connection = this.database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Review "
                        + "(user_id, film_id, date, rating, review) "
                        + "VALUES (?, ?, ?, ?, ?);"
        );
        statement.setInt(1, object.getUserID());
        statement.setInt(2, object.getFilmID());
        statement.setDate(3, java.sql.Date.valueOf(object.getDate()));
        statement.setInt(4, object.getRating());
        statement.setString(5, object.getReview());
        
        statement.executeUpdate();
        
        statement.close();
        connection.close();
    }

    @Override
    public Review find(Integer key) throws SQLException {
    Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Review "
                        + "WHERE id = ?"
        );
        statement.setInt(1, key);

        ResultSet rs = statement.executeQuery();
        boolean hasOne = rs.next();
        
        if (!hasOne) {
            return null;
        }

        Review review  = new Review(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("film_id"), rs.getDate("date").toLocalDate(), rs.getInt("rating"), rs.getString("review"));
        
        rs.close();
        statement.close();
        connection.close();

        return review;
    }
    
    @Override
    public Review find(String name) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<Review> findAllByUser(Integer findID) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Review "
                        + "WHERE user_id = ?;"
        );
        statement.setInt(1, findID);

        ResultSet rs = statement.executeQuery();
        
        List<Review> reviews = new ArrayList<>();
        
        while (rs.next()) {
            Integer id = rs.getInt("id");
            Integer userID = rs.getInt("user_id");
            Integer filmID = rs.getInt("film_id");
            LocalDate date = rs.getDate("date").toLocalDate();
            Integer rating = rs.getInt("rating");
            String review = rs.getString("review");
            reviews.add(new Review(id, userID, filmID, date, rating, review));
        }
        
        rs.close();
        statement.close();
        connection.close();

        return reviews;
    }

    @Override
    public List<Review> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Review;"
        );

        ResultSet rs = statement.executeQuery();
        
        List<Review> reviews = new ArrayList<>();
        
        while (rs.next()) {
            Integer id = rs.getInt("id");
            Integer userID = rs.getInt("user_id");
            Integer filmID = rs.getInt("film_id");
            LocalDate date = rs.getDate("date").toLocalDate();
            Integer rating = rs.getInt("rating");
            String review = rs.getString("review");
            reviews.add(new Review(id, userID, filmID, date, rating, review));
        }
        
        rs.close();
        statement.close();
        connection.close();

        return reviews;
    }

    @Override
    public void update(Review object) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE Review SET "
                        + "user_id = ?, "
                        + "film_id = ?, "
                        + "date = ?, "
                        + "rating = ?, "
                        + "review = ? "
                        + "WHERE id = ?;"
        );

        statement.setInt(1, object.getUserID());
        statement.setInt(2, object.getFilmID());
        statement.setDate(3, java.sql.Date.valueOf(object.getDate()));
        statement.setInt(4, object.getRating());
        statement.setString(5, object.getReview());
        statement.setInt(6, object.getId());
        statement.executeUpdate();

        statement.close();
        connection.close();    
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM Review "
                        + "WHERE id = ?;"
        );

        statement.setInt(1, key);
        statement.executeUpdate();

        statement.close();
        connection.close();
    }
    
}
