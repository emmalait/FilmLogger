
package filmlogger.dao;

import filmlogger.domain.*;
import java.sql.*;
import java.time.*;
import java.util.*;

/**
 * Class implements the ReviewDAO interface and handles saving to and retrieving information from the Review table in the database.
 * 
 * @author emmalait
 */

public class DbReviewDAO implements ReviewDAO {
    private Database database;
    private UserDAO users;
    private FilmDAO films;
    private TagDAO tags;
    
    /**
     * Constructor of the class, which sets the database, UserDAO, FilmDAO and TagDAO accessed by it.
     * 
     * @param database
     * @param users
     * @param films
     * @param tags 
     */
    
    public DbReviewDAO(Database database, UserDAO users, FilmDAO films, TagDAO tags) {
        this.database = database;
        this.users = users;
        this.films = films;
        this.tags = tags;
    }
    
    /**
     * Method creates a new Review instance in the database.
     * 
     * @param object
     * @throws SQLException 
     */

    @Override
    public void create(Review object) throws SQLException {
        Connection connection = this.database.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Review (user_id, film_id, tag_id, date, rating, review) VALUES (?, ?, ?, ?, ?, ?);");
        
        statement.setInt(1, object.getUser().getId());
        statement.setInt(2, object.getFilm().getId());
        statement.setInt(3, object.getTag().getId());
        
        if (object.getDate() == null) {
            statement.setDate(4, null);
        } else {
            statement.setDate(4, java.sql.Date.valueOf(object.getDate()));
        }
        
        statement.setInt(5, object.getRating());
        statement.setString(6, object.getReview());
        
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
    
    /**
     * Method retrieves a review from the database based on its id.
     * 
     * @param key
     * @return Review
     * @throws SQLException 
     */

    @Override
    public Review findById(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Review WHERE id = ?;");
        statement.setInt(1, key);

        ResultSet rs = statement.executeQuery();
        boolean hasOne = rs.next();
        
        if (!hasOne) {
            return null;
        }

        Review review  = new Review(rs.getInt("id"), users.findById(rs.getInt("user_id")), films.findById(rs.getInt("film_id")), tags.findById(rs.getInt("tag_id")), rs.getDate("date").toLocalDate(), rs.getInt("rating"), rs.getString("review"));
        
        rs.close();
        statement.close();
        connection.close();

        return review;
    }
    
    /**
     * Method retrieves a review from the database based on the user and film associated with it.
     * 
     * @param user
     * @param film
     * @return Review
     * @throws SQLException 
     */
    
    @Override
    public Review findByUserAndFilm(User user, Film film) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Review WHERE user_id = ? AND film_id = ?;");
        statement.setInt(1, user.getId());
        statement.setInt(2, film.getId());

        ResultSet rs = statement.executeQuery();
        boolean hasOne = rs.next();
        
        if (!hasOne) {
            return null;
        }
        
        LocalDate date = null;
            
        if (rs.getDate("date") != null) {
            date = rs.getDate("date").toLocalDate();
        }

        Review review  = new Review(rs.getInt("id"), users.findById(rs.getInt("user_id")), films.findById(rs.getInt("film_id")), tags.findById(rs.getInt("tag_id")), date, rs.getInt("rating"), rs.getString("review"));
        
        rs.close();
        statement.close();
        connection.close();

        return review;
    }
    
    /**
     * Method updates the values of a review in the database.
     * 
     * @param object
     * @throws SQLException 
     */

    @Override
    public void update(Review object) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE Review SET user_id = ?, film_id = ?, tag_id = ?, date = ?, rating = ?, review = ? WHERE id = ?;");

        statement.setInt(1, object.getUser().getId());
        statement.setInt(2, object.getFilm().getId());
        statement.setInt(3, object.getTag().getId());
        
        if (object.getDate() == null) {
            statement.setDate(4, null);
        } else {
            statement.setDate(4, java.sql.Date.valueOf(object.getDate()));
        }
        
        statement.setInt(5, object.getRating());
        statement.setString(6, object.getReview());
        statement.setInt(7, object.getId());
        statement.executeUpdate();

        statement.close();
        connection.close();    
    }
    
    /**
     * Method retrieves all reviews with the tagged with "to watch" by the user from the database.
     * 
     * @param user
     * @return list of reviews
     * @throws SQLException 
     */

    @Override
    public List<Review> findAllToWatchByUser(User user) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Review WHERE user_id = ? AND tag_id = ?;");
        statement.setInt(1, user.getId());
        statement.setInt(2, tags.getToWatch().getId());

        ResultSet rs = statement.executeQuery();
        
        List<Review> reviews = new ArrayList<>();
        
        while (rs.next()) {
            Integer id = rs.getInt("id");
            User foundUser = users.findById(rs.getInt("user_id"));
            Film film = films.findById(rs.getInt("film_id"));
            Tag tag = tags.findById(rs.getInt("tag_id"));
            
            LocalDate date = null;
            
            if (rs.getDate("date") != null) {
                date = rs.getDate("date").toLocalDate();
            }
            
            Integer rating = rs.getInt("rating");
            String review = rs.getString("review");
            reviews.add(new Review(id, foundUser, film, tag, date, rating, review));
        }
        
        rs.close();
        statement.close();
        connection.close();

        Collections.sort(reviews);
        
        return reviews;
    }

    /**
     * Method retrieves all reviews with the tagged with "seen" by the user from the database.
     * 
     * @param user
     * @return list of reviews
     * @throws SQLException 
     */
    
    @Override
    public List<Review> findAllSeenByUser(User user) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Review WHERE user_id = ? AND tag_id = ?;");
        statement.setInt(1, user.getId());
        statement.setInt(2, tags.getSeen().getId());

        ResultSet rs = statement.executeQuery();
        
        List<Review> reviews = new ArrayList<>();
        
        while (rs.next()) {
            Integer id = rs.getInt("id");
            User foundUser = users.findById(rs.getInt("user_id"));
            Film film = films.findById(rs.getInt("film_id"));
            Tag tag = tags.findById(rs.getInt("tag_id"));
            
            LocalDate date = null;
            
            if (rs.getDate("date") != null) {
                rs.getDate("date").toLocalDate();
            } 

            Integer rating = rs.getInt("rating");
            String review = rs.getString("review");
            reviews.add(new Review(id, foundUser, film, tag, date, rating, review));
        }
        
        rs.close();
        statement.close();
        connection.close();

        Collections.sort(reviews);
        
        return reviews; 
    }
}