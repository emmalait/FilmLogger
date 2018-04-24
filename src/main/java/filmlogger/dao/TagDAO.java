package filmlogger.dao;

import java.sql.SQLException;
import java.util.List;

public interface TagDAO<T, K> {
    void create(T object) throws SQLException;
    T findById(K key) throws SQLException;
    List<T> findAll() throws SQLException;
    void update(T object) throws SQLException;
    void delete(K key) throws SQLException;
    T findByName(String name) throws SQLException;
    T getToWatch() throws SQLException;
    T getSeen() throws SQLException;
}
