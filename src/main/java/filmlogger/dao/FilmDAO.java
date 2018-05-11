package filmlogger.dao;

import filmlogger.domain.Film;
import java.sql.SQLException;

/**
 * Interface for accessing films.
 * 
 * @author emmalait
 */

public interface FilmDAO {
    void create(Film object) throws SQLException;
    Film findById(Integer key) throws SQLException;
    Film findByName(String name) throws SQLException;
}
