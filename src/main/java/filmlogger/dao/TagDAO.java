package filmlogger.dao;

import filmlogger.domain.Tag;
import java.sql.SQLException;

/**
 * Interface for accessing tags.
 * 
 * @author emmalait
 */

public interface TagDAO {
    void create(Tag object) throws SQLException;
    Tag findById(Integer key) throws SQLException;
    Tag findByName(String name) throws SQLException;
    Tag getToWatch() throws SQLException;
    Tag getSeen() throws SQLException;
}
