
package filmlogger.dao;

import filmlogger.domain.Film;
import filmlogger.domain.User;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface ReviewDAO<T, K> {
    void create(T object) throws SQLException;
    T findById(K key) throws SQLException;
    T findByUserAndFilm(User user, Film film) throws SQLException;
    List<T> findAll() throws SQLException;
    void update(T object) throws SQLException;
    void delete(K key) throws SQLException;
    List<T> findAllByUser(User user) throws SQLException;
    List<T> findAllToWatchByUser(User user) throws SQLException;
    List<T> findAllSeenByUser(User user) throws SQLException;
    void markAsSeen(T object) throws SQLException;
    void markAsToWatch(T object) throws SQLException;
    void addReview(T object, LocalDate date, Integer rating, String review) throws SQLException;
}
