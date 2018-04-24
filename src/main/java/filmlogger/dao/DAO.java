
package filmlogger.dao;

import java.sql.*;
import java.util.*;

public interface DAO<T, K> {
    void create(T object) throws SQLException;
    T findById(K key) throws SQLException;
    List<T> findAll() throws SQLException;
    void update(T object) throws SQLException;
    void delete(K key) throws SQLException;
}
