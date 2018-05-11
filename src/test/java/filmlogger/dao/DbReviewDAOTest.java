/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmlogger.dao;

import filmlogger.domain.Film;
import filmlogger.domain.Review;
import filmlogger.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emmalait
 */
public class DbReviewDAOTest {
    private Database database;
    private UserDAO users;
    private FilmDAO films;
    private TagDAO tags;
    private ReviewDAO reviews;
    private User user;
    private Film film;
    private Film film2;

    public DbReviewDAOTest() {
    }
    
    @Before
    public void setUp() throws SQLException {
        database = new Database("jdbc:sqlite:fakeFilmlogger.db");
        users = new DbUserDAO(database);
        films = new DbFilmDAO(database);
        tags = new DbTagDAO(database);
        reviews = new DbReviewDAO(database, users, films, tags);
        user = new User(1, "Testi", "tester1");
        film = new Film(1, "Inception", "2010");
        film2 = new Film(2, "Kill Bill", "2003");
        users.create(user);
        films.create(film);
        films.create(film2);
        
        reviews.create(new Review(null, user, film, tags.getToWatch(), null, -1, ""));
        reviews.create(new Review(null, user, film2, tags.getSeen(), LocalDate.now(), 4, "Excellent!"));
    }
    
    @Test
    public void creationWorks() throws SQLException {
        assertEquals(1, reviews.findAllToWatchByUser(user).size()); 
    }
    
    @Test
    public void findByUserAndFilmWorks() throws SQLException {
        assertEquals("Kill Bill", reviews.findByUserAndFilm(user, film2).getFilm().getName());
    }
    
    @Test
    public void findsAllSeenByUser() throws SQLException {
        assertEquals(1, reviews.findAllSeenByUser(user).size());
    }
    
    @Test
    public void updateWorks() throws SQLException {
        Review test = reviews.findByUserAndFilm(user, film);
        test.setTag(tags.getSeen());
        reviews.update(test);
        test = reviews.findByUserAndFilm(user, film);
        assertEquals("seen", test.getTag().getName());
    }
    
    @Test
    public void updateWorksWithDate() throws SQLException {
        Review test = reviews.findByUserAndFilm(user, film);
        test.setTag(tags.getSeen());
        test.setRating(4);
        test.setDate(LocalDate.now());
        reviews.update(test);
        test = reviews.findByUserAndFilm(user, film);
        assertEquals(LocalDate.now(), test.getDate());
    }
    
    @After
    public void tearDown() throws SQLException {
        Connection connection = this.database.getConnection();
        
        PreparedStatement statement1 = connection.prepareStatement("DROP TABLE User;");
        statement1.executeUpdate();
        statement1.close();
        
        PreparedStatement statement2 = connection.prepareStatement("DROP TABLE Film;");
        statement2.executeUpdate();
        statement2.close();
        
        PreparedStatement statement3 = connection.prepareStatement("DROP TABLE Tag;");
        statement3.executeUpdate();
        statement3.close();
        
        PreparedStatement statement4 = connection.prepareStatement("DROP TABLE Review;");
        statement4.executeUpdate();
        statement4.close();
        
        connection.close();
    }

}
