/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmlogger.domain;

import filmlogger.dao.UserDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emmalait
 */
public class FakeUserDAO implements UserDAO {
    List<User> users;

    public FakeUserDAO() {
        users = new ArrayList<>();
    }

    @Override
    public void create(User object) throws SQLException {
        users.add((User) object);
    }

    @Override
    public User findById(Integer key) throws SQLException {
        User found = null;
        
        for (User user : users) {
            if (user.getId() == key) {
                found = user;
                break;
            }
        }
        
        return found;
    }

    @Override
    public User findByUsername(String username) throws SQLException {
        User found = null;
        
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                found = user;
                break;
            }
        }
        
        return found;
    }
 
}
