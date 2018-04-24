
package filmlogger.domain;

import filmlogger.dao.*;
import java.sql.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Logger {
    private DbUserDAO users;
    private DbFilmDAO films;
    private DbReviewDAO reviews;
    private DbTagDAO tags;
    private User loggedInUser;

    public Logger(DbUserDAO users, DbFilmDAO films, DbReviewDAO reviews, DbTagDAO tags) {
        this.users = users;
        this.films = films;
        this.reviews = reviews;
        this.tags = tags;
    }
    
    public boolean login(String username) {
        try {
            User user = users.findByUsername(username);
            if (user == null) {
                System.out.println("Username not found!");
                return false;
            } else {
                loggedInUser = user;
                return true;
            }
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public void markAsSeen(Review review) {
        try {
            reviews.markAsSeen(review);
        } catch (SQLException ex) {
            
        }
    }
    
    public boolean isToWatch(Film film) throws SQLException {
        if (reviews.findByUserAndFilm(loggedInUser, film) == null) {
            return false;
        } else if (reviews.findByUserAndFilm(loggedInUser, film).getTag().getName().equals("toWatch")) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isSeen(Film film) throws SQLException {
        if (reviews.findByUserAndFilm(loggedInUser, film) == null) {
            return false;
        } else if (reviews.findByUserAndFilm(loggedInUser, film).getTag().getName().equals("seen")) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean hasRating(Review review) {
        if (review.getReview() == null) {
            return false;
        } else {
            return true;
        }
    }
    
    public User findUserByUsername(String username) {
        try {
            return users.findByUsername(username);
        } catch (SQLException ex) {
            return null;
        } 
    }
    
    public boolean createUser(String username)  throws SQLException {
        if (users.findByUsername(username) != null) {
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
    
    public Film findFilm(String filmName) throws SQLException {
        Film found = films.findByName(filmName);
        
        if (found == null) {
            return null;                    
        }
        
        return found;
    }
    
    public boolean addFilm(String filmName, String filmYear) throws SQLException {
        if (!validateYear(filmYear)) {
            System.out.println("Enter year with 4 digits, e.g. '2018'.");
            return false;
        }
        
        if (films.findByName(filmName) == null) {
            Film film = new Film(null, filmName, filmYear);
            films.create(film);                    
        }
        
        return true;
    }

    
    public boolean addToWatchlist(String filmName, String filmYear) throws SQLException {
        if (!validateYear(filmYear)) {
            System.out.println("Enter year with 4 digits, e.g. '2018'.");
            return false;
        }
       
        if (films.findByName(filmName) == null) {
            Film film = new Film(null, filmName, filmYear);
            films.create(film);                    
        }
        
        Film film = films.findByName(filmName);
        
        if (reviews.findByUserAndFilm(loggedInUser, film) != null) {
            System.out.println("You've already reviewed this film! You can only add new films to the watchlist.");
            return false;
        }

        Review review = new Review(null, loggedInUser, film, tags.getToWatch(), null, -1, null);
        reviews.create(review);
        
        return true;
    }
    
    public List<Review> getFilmsToWatch() {
        List<Review> filmsToWatch = new ArrayList<>();
        
        try {
            filmsToWatch = reviews.findAllToWatchByUser(loggedInUser);
        } catch (SQLException ex) {
            
        }
        
        return filmsToWatch;
    }
    
    public void printWatchlist() throws SQLException {
        List<Review> allToWatch = getFilmsToWatch();
        
        for (Review review : allToWatch) {
            System.out.println(films.findById(review.getFilm().getId()));
        }
    }
    
    public boolean addToSeen(Film film, String watchDate, Integer filmRating, String filmReview) throws SQLException {
        if (!validateDate(watchDate)) {
            System.out.println("Enter date as dd/mm/yyyy.");
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(watchDate, formatter);
        
        if (films.findByName(film.getName()) == null) {
            film = new Film(null, film.getName(), film.getYear());
            films.create(film);  
        }
        
        Review review = reviews.findByUserAndFilm(loggedInUser, film);
        
        if (review == null) {
            review = new Review(null, loggedInUser, film, tags.getSeen(), date, filmRating, filmReview);
            reviews.create(review);
        } else {
            reviews.addReview(review, date, filmRating, filmReview);
        }

        return true;
    }
    
    public void printSeen() throws SQLException {
        List<Review> allReviews = reviews.findAllSeenByUser(this.loggedInUser);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            
        for (Review review : allReviews) {
            System.out.println(films.findById(review.getFilm().getId()) + " ~ " + review.getDate().format(formatter) + " ~ " + review.getStarredRating());
            System.out.println("Review: " + review.getReview());
        }
    }
    
}
