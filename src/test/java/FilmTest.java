
import filmlogger.domain.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FilmTest {
    
    public FilmTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void constructorSetsIdRight() {
        Film film = new Film(1, "Inception", "2009");
        assertEquals((long) 1, (long) film.getId());
    }

    @Test
    public void constructorSetNameRight() {
        Film film = new Film(1, "Inception", "2009");
        assertEquals("Inception", film.getName());
    }
    
    @Test
    public void constructorSetsYearRight() {
        Film film = new Film(1, "Inception", "2009");
        assertEquals("2009", film.getYear());
    }
    
    @Test
    public void setterSetsIdRight() {
        Film film = new Film(123, "Name", "Year");
        film.setId(456);
        assertEquals((long) 456, (long) film.getId());
    }

    @Test
    public void setterSetsNameRight() {
        Film film = new Film(123, "Name", "Year");
        film.setName("Kill Bill");
        assertEquals("Kill Bill", film.getName());
    }
    
    @Test
    public void setterSetsYearRight() {
        Film film = new Film(123, "Name", "Year");
        film.setYear("2003");
        assertEquals("2003", film.getYear());
    }
    
    @Test
    public void toStringReturnsRightString() {
        Film film = new Film(123, "Kill Bill", "2003");
        assertEquals("Kill Bill (2003)", film.toString());
    }
}
