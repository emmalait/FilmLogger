
package filmlogger.dao;

import filmlogger.domain.Film;
import filmlogger.domain.Review;
import filmlogger.domain.User;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface ReviewDAO {
    void create(Review object) throws SQLException;
    Review findById(Integer key) throws SQLException;
    Review findByUserAndFilm(User user, Film film) throws SQLException;
//    List<T> findAll() throws SQLException;
    void update(Review object) throws SQLException;
//    void delete(K key) throws SQLException;
//    List<T> findAllByUser(User user) throws SQLException;
    List<Review> findAllToWatchByUser(User user) throws SQLException;
    List<Review> findAllSeenByUser(User user) throws SQLException;
}
