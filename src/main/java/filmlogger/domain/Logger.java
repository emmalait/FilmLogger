
package filmlogger.domain;

import filmlogger.dao.*;
import java.sql.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Logger {
    private UserDAO users;
    private FilmDAO films;
    private ReviewDAO reviews;
    private TagDAO tags;
    private User loggedInUser;

    public Logger(UserDAO users, FilmDAO films, ReviewDAO reviews, TagDAO tags) {
        this.users = users;
        this.films = films;
        this.reviews = reviews;
        this.tags = tags;
    }
    
    public boolean login(String username) throws SQLException {
        if(users.find(username) == null) {
            System.out.println("Username not found!");
            return false;
        } else {
            this.loggedInUser = users.find(username);
            return true;
        }
    }
    
    
    public boolean createUser(String username) throws SQLException {
        if (users.find(username) != null) {
            System.out.println("Sorry, username is already taken!");
            return false;
        } else if (username.length() < 5) {
            System.out.println("Username is too short!");
            return false;
        } else if (username.length() > 15) {
            System.out.println("Username is too long!");
            return false;
        } else {
            users.create(new User(null, username));
            return true;
        }
    }
    
    public User getCurrentUser() {
        return this.loggedInUser;
    }
    
    public boolean validateYear(String filmYear) {
        if (filmYear.length() > 4) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean validateDate(String watchDate) {
        
        if (watchDate.length() > 10) {
            return false;
        } else if (watchDate.charAt(2) != '/' || watchDate.charAt(5) != '/') {
            return false;
        } else {
            return true;
        }
    }
    
    
    public boolean logFilm(String filmName, String filmYear, String watchDate, Integer filmRating, String filmReview) throws SQLException {
        if (!validateYear(filmYear)) {
            System.out.println("Enter year with 4 digits, e.g. '2018'.");
            return false;
        }
        
        if (!validateDate(watchDate)) {
            System.out.println("Enter date as dd/mm/yyyy.");
            return false;
        }
        
        if (films.find(filmName) == null) {
            Film film = new Film(null, filmName, filmYear);
            films.create(film);                    
        }
                            
        Film film = films.find(filmName);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(watchDate, formatter);

        Review review = new Review(null, this.loggedInUser.getId(), film.getId(), date, filmRating, filmReview);
        reviews.create(review);
        
        return true;
    }
    
    public void printLoggedFilms() throws SQLException {
        List<Review> allReviews = reviews.findAllByUser(this.loggedInUser.getId());
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            
        for (Review review : allReviews) {
            System.out.println(films.find(review.getFilmID()) + " ~ " + review.getDate().format(formatter) + " ~ " + review.getStarredRating());
            System.out.println("Review: " + review.getReview());
        }
    }
    
}
