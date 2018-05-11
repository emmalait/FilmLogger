package filmlogger.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class FilmTest {
    
    public FilmTest() {
    }
    
    @Test
    public void toStringReturnsRightString() {
        Film film = new Film(123, "Kill Bill", "2003");
        assertEquals("Kill Bill (2003)", film.toString());
    }
}
