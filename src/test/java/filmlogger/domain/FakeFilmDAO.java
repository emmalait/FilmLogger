/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmlogger.domain;

import filmlogger.dao.FilmDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emmalait
 */
public class FakeFilmDAO implements FilmDAO {
    List<Film> films;

    public FakeFilmDAO() {
        films = new ArrayList<>();
    }

    @Override
    public void create(Film object) throws SQLException {
        films.add((Film) object);
    }

    @Override
    public Film findById(Integer key) throws SQLException {
        Film found = null;
        
        for (Film film : films) {
            if (film.getId() == key) {
                found = film;
                break;
            }
        }
        
        return found;
    }

    @Override
    public Film findByName(String name) throws SQLException {
        Film found = null;
        
        for (Film film : films) {
            if (film.getName().equals(name)) {
                found = film;
                break;
            }
        }
        
        return found;
    }
    
}
