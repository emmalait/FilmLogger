
package filmlogger.dao;

import filmlogger.domain.Film;
import filmlogger.domain.Review;
import filmlogger.domain.User;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface for accessing reviews.
 * 
 * @author emmalait
 */

public interface ReviewDAO {
    void create(Review object) throws SQLException;
    Review findById(Integer key) throws SQLException;
    Review findByUserAndFilm(User user, Film film) throws SQLException;
    void update(Review object) throws SQLException;
    List<Review> findAllToWatchByUser(User user) throws SQLException;
    List<Review> findAllSeenByUser(User user) throws SQLException;
}
