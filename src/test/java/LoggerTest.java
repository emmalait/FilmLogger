
import filmlogger.dao.*;
import filmlogger.dao.Database;
import filmlogger.domain.*;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoggerTest {
    DbUserDAO users;
    DbFilmDAO films;
    DbReviewDAO reviews;
    DbTagDAO tags;
    Logger logger;
    Database db;
    
    @Before
    public void setUp() throws SQLException {
        db = new Database();
        users = new DbUserDAO(db);
        films = new DbFilmDAO(db);
        
        tags = new DbTagDAO(db);
        reviews = new DbReviewDAO(db, users, films, tags);
        logger = new Logger(users, films, reviews, tags);
        
        users.create(new User(null, "tester"));
    }

    @Test
    public void existingUserCanLogIn() throws SQLException {
        assertTrue(logger.login("tester"));
    }
    
    @Test
    public void nonexistingUserCannotLogin() throws SQLException {
        assertFalse(logger.login("tester123"));
    }
    
    @Test
    public void usernameDoesNotExist() throws SQLException {
        assertFalse(logger.createUser("tester"));
    }
    
    @Test
    public void usernameIsNotTooShort() throws SQLException {
        assertFalse(logger.createUser("abc"));
    }
    
    @Test
    public void usernameIsNotTooLong() throws SQLException {
        assertFalse(logger.createUser("abcdefghijklmopqrstuvwxyzåäö"));
    }

    @Test
    public void returnsLoggedInUser() throws SQLException {
        logger.login("tester");
        assertEquals("tester", logger.getCurrentUser().getName());
    }
    
    @Test
    public void yearCannotBeWrong() throws SQLException {
        assertFalse(logger.validateYear("12345"));
    }
    
    @Test
    public void yearCanBeRight() throws SQLException {
        assertTrue(logger.validateYear("2018"));
    }
    
    @Test
    public void dateCannotBeWrong() throws SQLException {
        assertFalse(logger.validateDate("31122017"));
    }
    
    @Test
    public void dateCanBeRight() throws SQLException {
        assertTrue(logger.validateDate("31/12/2017"));
    }
    
}
