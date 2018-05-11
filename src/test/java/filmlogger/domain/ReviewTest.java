package filmlogger.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReviewTest {
    
    public ReviewTest() {
    }

    @Test
    public void comparatorWorksCorrectly() {
        Review review1 = new Review(null, new User(null, "Testi", "tester1"), new Film(null, "Inception", "2010"), new Tag(null, "toWatch"), null, -1, "");
        Review review2 = new Review(null, new User(null, "Testi", "tester1"), new Film(null, "Kill Bill", "2003"), new Tag(null, "toWatch"), null, -1, "");
        List<Review> list = new ArrayList<>();
        list.add(review2);
        list.add(review1);
        Collections.sort(list);
        assertEquals("Inception", list.get(0).getFilm().getName());
    }
   
}
