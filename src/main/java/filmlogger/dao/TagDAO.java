package filmlogger.dao;

import filmlogger.domain.Tag;
import java.sql.SQLException;
import java.util.List;

public interface TagDAO{
    void create(Tag object) throws SQLException;
    Tag findById(Integer key) throws SQLException;
//    List<T> findAll() throws SQLException;
//    void update(T object) throws SQLException;
//    void delete(K key) throws SQLException;
    Tag findByName(String name) throws SQLException;
    Tag getToWatch() throws SQLException;
    Tag getSeen() throws SQLException;
}
