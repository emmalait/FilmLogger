
package filmlogger.dao;

import filmlogger.domain.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO<T, K> {
    void create(T object) throws SQLException;
    T findById(K key) throws SQLException;
    List<T> findAll() throws SQLException;
    void update(T object) throws SQLException;
    void delete(K key) throws SQLException;
    T findByUsername(String username) throws SQLException;
}
