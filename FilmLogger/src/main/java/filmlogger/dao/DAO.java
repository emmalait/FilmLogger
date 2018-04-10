
package filmlogger.dao;

import java.sql.*;
import java.util.*;

public interface DAO<T, K> {
    void create(T object) throws SQLException;
    T find(K key) throws SQLException;
    T find(String name) throws SQLException;
    List<T> findAll() throws SQLException;
    void update(T object) throws SQLException;
    void delete(K key) throws SQLException;
}
