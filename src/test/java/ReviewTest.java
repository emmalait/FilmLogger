

import filmlogger.domain.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReviewTest {
    
    public ReviewTest() {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("12/12/2017", formatter);
        
        Review review = new Review(1, 2, 3, date, 5, "Ok");
        assertEquals((long) 1, (long) review.getId());
    }
    
    @Test
    public void constructorSetsUserIdRight() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("12/12/2017", formatter);
        
        Review review = new Review(1, 2, 3, date, 5, "Ok");
        assertEquals((long) 2, (long) review.getUserID());
    }
    
    @Test
    public void constructorSetsFilmIdRight() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("12/12/2017", formatter);
        
        Review review = new Review(1, 2, 3, date, 5, "Ok");
        assertEquals((long) 3, (long) review.getFilmID());
    }
    
    @Test
    public void constructorSetsDateRight() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("12/12/2017", formatter);
        
        Review review = new Review(1, 2, 3, date, 5, "Ok");
        assertEquals("12/12/2017", review.getDate().format(formatter));
    }
    
    @Test
    public void constructorSetsRatingRight() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("12/12/2017", formatter);
        
        Review review = new Review(1, 2, 3, date, 5, "Ok");
        assertEquals((long) 5, (long) review.getRating());
    }
    
    @Test
    public void constructorSetsReviewRight() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("12/12/2017", formatter);
        
        Review review = new Review(1, 2, 3, date, 5, "Ok");
        assertEquals("Ok", review.getReview());
    }
    
//    @Test
//    public void setterSetsIdRight() {
//        Film film = new Film(123, "Name", "Year");
//        film.setId(456);
//        assertEquals((long) 456, (long) film.getId());
//    }
//
//    @Test
//    public void setterSetsNameRight() {
//        Film film = new Film(123, "Name", "Year");
//        film.setName("Kill Bill");
//        assertEquals("Kill Bill", film.getName());
//    }
//    
//    @Test
//    public void setterSetsYearRight() {
//        Film film = new Film(123, "Name", "Year");
//        film.setYear("2003");
//        assertEquals("2003", film.getYear());
//    }
   
}
