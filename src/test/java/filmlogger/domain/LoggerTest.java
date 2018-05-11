/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmlogger.domain;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emmalait
 */
public class LoggerTest {
    private Logger logger;
    private FakeUserDAO users;
    private FakeFilmDAO films;
    private FakeReviewDAO reviews;
    private FakeTagDAO tags;
    private User tester1;
    private User tester2;
    private Film film1;

    public LoggerTest() {
    }
    
    @Before
    public void setUp() throws SQLException {
        this.users = new FakeUserDAO();
        this.films = new FakeFilmDAO();
        this.reviews = new FakeReviewDAO();
        this.tags = new FakeTagDAO();
        this.logger = new Logger(users, films, reviews, tags);
        
        this.tester1 = new User(null, "Tester 1", "tester1");
        this.tester2 = new User(null, "Tester 2", "tester2");
        users.create(tester1);
        
        film1 = new Film(null, "Inception", "2010");
        films.create(film1);
        
        Tag toWatch = new Tag(null, "toWatch");
        Tag seen = new Tag(null, "seen");
        
        tags.create(toWatch);
        tags.create(seen);  
    }

    @Test
    public void loginLogsRegisteredUserIn() {
        boolean login = logger.login(tester1.getUsername());
        assertEquals(true, login);
    }
    
    @Test
    public void loginDoesNotLogUnregistedUserIn() {
        boolean login = logger.login(tester2.getUsername());
        assertEquals(false, login);
    }
    
    @Test
    public void returnsLoggedInUser() {
        logger.login(tester1.getUsername());
        assertEquals(tester1, logger.getCurrentUser());
    }
    
    @Test
    public void logsOutUser() {
        logger.login(tester1.getUsername());
        logger.logout();
        assertEquals(null, logger.getCurrentUser());
    }
    
    @Test
    public void registersValidUser() {
        String msg = logger.createUser("Test", "tester");
        assertEquals("Registration successful!", msg);
    }
    
    @Test
    public void doesNotRegisterIfUsernameTaken() {
        String msg = logger.createUser("Test", "tester1");
        assertEquals("Sorry, username is already taken!", msg);
    }
    
    @Test
    public void doesNotRegisterIfUsernameTooShort() {
        String msg = logger.createUser("Test", "te");
        assertEquals("Username is too short!", msg);
    }
    
    @Test
    public void doesNotRegisterIfUsernameToo() {
        String msg = logger.createUser("Test", "testertestertester");
        assertEquals("Username is too long!", msg);
    }
    
    @Test
    public void getsWatchlist() throws SQLException {
        logger.login(tester1.getUsername());
        logger.addToWatchlist("Jurassic Park", "1993");
        List<Review> test = logger.getWatchlist();
        logger.logout();
        assertEquals(1, test.size());
    }
    
    @Test
    public void addsToWatchlistWhenFilmExists() throws SQLException {
        logger.login(tester1.getUsername());
        String msg = logger.addToWatchlist("Jurassic Park", "1993");
        logger.logout();
        assertEquals("", msg);
    }
    
    @Test
    public void addsToWatchlistWhenFilmDoesNotExist() throws SQLException {
        logger.login(tester1.getUsername());
        String msg = logger.addToWatchlist("The Godfather", "1972");
        logger.logout();
        assertEquals("", msg);
    }
    
    @Test
    public void doesNotAddToWatchlistWhenYearInvalid() throws SQLException {
        logger.login(tester1.getUsername());
        String msg = logger.addToWatchlist("Jurassic Park", "klkm");
        logger.logout();
        assertEquals("Enter year with 4 digits, e.g. '2018'.", msg);
    }
    
    @Test
    public void doesNotAddToWatchlistWhenDoubleEntry() throws SQLException {
        logger.login(tester1.getUsername());
        logger.addToWatchlist("Jurassic Park", "1993");
        String msg = logger.addToWatchlist("Jurassic Park", "1993");
        logger.logout();
        assertEquals("You've already reviewed this film! \n You can only add new films to the watchlist.", msg);
    }
    
    @Test
    public void marksAsSeen() throws SQLException {
        logger.login(tester1.getUsername());
        logger.addToWatchlist(film1.getName(), film1.getYear());
        logger.markAsSeen(reviews.findByUserAndFilm(tester1, film1));
        assertEquals("seen", reviews.findByUserAndFilm(tester1, film1).getTag().getName()); 
    }
    
    @Test
    public void getsSeen() throws SQLException {
        logger.login(tester1.getUsername());
        logger.addToWatchlist("Jurassic Park", "1993");
        logger.markAsSeen(reviews.findByUserAndFilm(tester1, films.findByName("Jurassic Park")));
        List<Review> test = logger.getSeen();
        logger.logout();
        assertEquals(1, test.size());
    }
    
    @Test
    public void getsReview() throws SQLException {
        logger.login(tester1.getUsername());
        logger.addToWatchlist("Jurassic Park", "1993");
        String test = logger.getReview(films.findByName("Jurassic Park")).getTag().getName();
        logger.logout();
        assertEquals("toWatch", test);
    }
    
    @Test
    public void updatesReview() throws SQLException {
        logger.login(tester1.getUsername());
        logger.addToWatchlist("Inception", "2010");
        Review review = logger.getReview(films.findByName("Inception"));
        review.setTag(tags.getSeen());
        review.setDate(LocalDate.now());
        review.setRating(5);
        review.setReview("Good!");
        logger.updateReview(review);
        String test = logger.getReview(films.findByName("Inception")).getReview();
        logger.logout();
        assertEquals("Good!", test);
    }
    
}
