/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmlogger.domain;

import filmlogger.dao.TagDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emmalait
 */
public class FakeTagDAO implements TagDAO {
    List<Tag> tags;

    public FakeTagDAO() {
        this.tags = new ArrayList<>();
    }

    @Override
    public void create(Tag object) throws SQLException {
        tags.add((Tag) object);
    }

    @Override
    public Tag findById(Integer key) throws SQLException {
        Tag found = null;
        
        for (Tag tag : tags) {
            if (tag.getId() == key) {
                found = tag;
                break;
            }
        }
        
        return found;
    }

    @Override
    public Tag findByName(String name) throws SQLException {
        Tag found = null;
        
        for (Tag tag : tags) {
            if (tag.getName().equals(name)) {
                found = tag;
                break;
            }
        }
        
        return found;
    }

    @Override
    public Tag getToWatch() throws SQLException {
        Tag found = null;
        
        for (Tag tag : tags) {
            if (tag.getName().equals("toWatch")) {
                found = tag;
                break;
            }
        }
        
        return found;
    }

    @Override
    public Tag getSeen() throws SQLException {
        Tag found = null;
        
        for (Tag tag : tags) {
            if (tag.getName().equals("seen")) {
                found = tag;
                break;
            }
        }
        
        return found;
    }
    
}
