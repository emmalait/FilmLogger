
package filmlogger.dao;

import filmlogger.domain.User;
import java.sql.SQLException;

public interface UserDAO {
    void create(User object) throws SQLException;
    User findById(Integer key) throws SQLException;
//    List<T> findAll() throws SQLException;
//    void update(T object) throws SQLException;
//    void delete(K key) throws SQLException;
    User findByUsername(String username) throws SQLException;
}
