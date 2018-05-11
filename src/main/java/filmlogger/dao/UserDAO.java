
package filmlogger.dao;

import filmlogger.domain.User;
import java.sql.SQLException;

/**
 * Interface for accessing users.
 * 
 * @author emmalait
 */

public interface UserDAO {
    void create(User object) throws SQLException;
    User findById(Integer key) throws SQLException;
    User findByUsername(String username) throws SQLException;
}
