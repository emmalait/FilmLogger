
package filmlogger.dao;

import filmlogger.database.*;
import filmlogger.domain.*;
import java.sql.*;
import java.util.*;

public class ReviewDAO implements DAO<Review, Integer> {
    private Database database;
    
    public ReviewDAO(Database database) {
        this.database = database;
    }

    @Override
    public void create(Review object) throws SQLException {
        Connection conn = this.database.getConnection();
        PreparedStatement stmnt = conn.prepareStatement(
                "INSERT INTO Review " +
                        "(user_id, film_id, rating) " +
                        "VALUES (?, ?, ?);"
        );
        stmnt.setInt(1, object.getUserID());
        stmnt.setInt(2, object.getFilmID());
        stmnt.setInt(3, object.getRating());
        
        stmnt.executeUpdate();
        
        stmnt.close();
        conn.close();
    }

    @Override
    public Review find(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Review find(String name) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Review> findAllByUser(Integer findID) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmnt = conn.prepareStatement("SELECT * FROM Review WHERE user_id = ?;");
        stmnt.setInt(1, findID);

        ResultSet rs = stmnt.executeQuery();
        
        List<Review> reviews = new ArrayList<>();
        
        while (rs.next()) {
            int id = rs.getInt("id");
            Integer userID = rs.getInt("user_id");
            Integer filmID = rs.getInt("film_id");
            Integer rating = rs.getInt("rating");
            reviews.add(new Review(id, userID, filmID, rating));
        }
        
        rs.close();
        stmnt.close();
        conn.close();

        return reviews;
    }

    @Override
    public List<Review> findAll() throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmnt = conn.prepareStatement("SELECT * FROM Review;");

        ResultSet rs = stmnt.executeQuery();
        
        List<Review> reviews = new ArrayList<>();
        
        while (rs.next()) {
            int id = rs.getInt("id");
            Integer userID = rs.getInt("user_id");
            Integer filmID = rs.getInt("film_id");
            Integer rating = rs.getInt("rating");
            reviews.add(new Review(id, userID, filmID, rating));
        }
        
        rs.close();
        stmnt.close();
        conn.close();

        return reviews;
    }

    @Override
    public void update(Review object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
