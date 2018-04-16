
import filmlogger.dao.*;
import filmlogger.database.Database;
import filmlogger.domain.*;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoggerTest {
    UserDAO users;
    FilmDAO films;
    ReviewDAO reviews;
    TagDAO tags;
    Logger logger;
    Database db;
    
    @Before
    public void setUp() throws SQLException {
        db = new Database();
        users = new UserDAO(db);
        films = new FilmDAO(db);
        reviews = new ReviewDAO(db);
        tags = new TagDAO(db);
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
    
}
