
package filmlogger.domain;

import filmlogger.dao.*;
import java.sql.*;
import java.util.*;

/**
 * Class responsible for application logic. 
 * 
 * @author emmalait
 */

public class Logger {
    private UserDAO users;
    private FilmDAO films;
    private ReviewDAO reviews;
    private TagDAO tags;
    public User loggedInUser;
    
    /**
     * Constructor of the class sets the UserDAO, FilmDAO, ReviewDAO and TagDAO used by the instance. 
     * 
     * @param users
     * @param films
     * @param reviews
     * @param tags 
     */

    public Logger(UserDAO users, FilmDAO films, ReviewDAO reviews, TagDAO tags) {
        this.users = users;
        this.films = films;
        this.reviews = reviews;
        this.tags = tags;
        this.loggedInUser = null;
    }
    
    /**
     * Method checks whether the username exists in the database. 
     * 
     * @param   username    
     * @return  true if username exists, false if username does not exist
     */
    
    public boolean login(String username) {
        try {
            User user = users.findByUsername(username);
            if (user == null) {
                return false;
            } else {
                this.loggedInUser = user;
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    /**
     * Method returns the logged-in user.
     * 
     * @return logged-in user
     */
    
    public User getCurrentUser() {
        return this.loggedInUser;
    }
    
    /**
     * Method logs out the current user.
     */
    
    public void logout() {
        this.loggedInUser = null;
    }
    
    /**
     * Method creates a new user and returns an error message if creation was not successful.
     * 
     * @param username
     * @return error message
     */
    
    public String createUser(String user, String username) {
        try {
            if (users.findByUsername(username) != null) {
                return "Sorry, username is already taken!";
            } else if (username.length() < 5) {
                return "Username is too short!";
            } else if (username.length() > 15) {
                return "Username is too long!";
            } else {
                users.create(new User(null, user, username));
                return "Registration successful!";  
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return "Something went wrong :(";
        }
    }
    
    /**
     * Method returns a list of film on the logged-in user's watchlist.
     * 
     * @return list of films on the logged-in user's watchlist
     */
    
    public List<Review> getWatchlist() {
        try {
            return reviews.findAllToWatchByUser(this.loggedInUser);
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    /**
     * Method adds a film to the logged-in user's watchlist.
     * 
     * @param filmName
     * @param filmYear 
     * @return error message
     * @throws SQLException 
     */
    
    public String addToWatchlist(String filmName, String filmYear) throws SQLException {
        if (!validateYear(filmYear)) {
            return "Enter year with 4 digits, e.g. '2018'.";
        }

        if (films.findByName(filmName) == null) {
            Film film = new Film(null, filmName, filmYear);
            films.create(film);                    
        }

        Film film = films.findByName(filmName);

        if (reviews.findByUserAndFilm(loggedInUser, film) != null) {
            return "You've already reviewed this film! \n You can only add new films to the watchlist.";
        }

        Review review = new Review(null, loggedInUser, film, tags.getToWatch(), null, -1, null);
        reviews.create(review);

        return "";
    }
    
    /**
     * Method validates that the year input is valid, i.e. contains 4 numbers.
     * 
     * @param filmYear
     * @return true is year is valid, false if invalid
     */
    
    public boolean validateYear(String filmYear) {
        if (filmYear.matches("[0-9]+") && filmYear.length() == 4) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Method marks the film as seen.
     * 
     * @param review
     */
    
    public void markAsSeen(Review review) {
        try {
            review.setTag(tags.findByName("seen"));
            reviews.update(review);
        } catch (SQLException ex) {
            
        }
    }
    
    /**
     * Method returns a list of the film the logged-in user has logged as seen.
     * 
     * @return list of the film the logged-in user has logged as seen
     */
    
    public List<Review> getSeen() {
        try {
            return reviews.findAllSeenByUser(this.loggedInUser);
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    /**
     * Method finds the logged-in user's review of a film.
     * 
     * @param film the film the review of which is searched for
     * @return the logged-in user's review of the film 
     */
    
    public Review getReview(Film film) {
        try {
            return reviews.findByUserAndFilm(loggedInUser, film);
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    /**
     * Method updates a review.
     * 
     * @param review the review being updated
     */
    
    public void updateReview(Review review) {
        try {
            reviews.update(review);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}