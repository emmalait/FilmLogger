package filmlogger.dao;

import filmlogger.domain.Film;
import java.sql.SQLException;
import java.util.List;

public interface FilmDAO {
    void create(Film object) throws SQLException;
    Film findById(Integer key) throws SQLException;
//    List<T> findAll() throws SQLException;
//    void update(T object) throws SQLException;
//    void delete(K key) throws SQLException;
    Film findByName(String name) throws SQLException;
}
