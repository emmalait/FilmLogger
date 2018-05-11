/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmlogger.domain;

import filmlogger.dao.ReviewDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emmalait
 */
public class FakeReviewDAO implements ReviewDAO {
    List<Review> reviews;

    public FakeReviewDAO() {
        reviews = new ArrayList<>();
    }

    @Override
    public void create(Review object) throws SQLException {
        reviews.add((Review) object);
    }
    
    @Override
    public Review findById(Integer key) throws SQLException {
       Review found = null;
        
        for (Review review : reviews) {
            if (review.getId() == key) {
                found = review;
                break;
            }
        }
        
        return found;
    }

    @Override
    public Review findByUserAndFilm(User user, Film film) throws SQLException {
        Review found = null;
        
        for (Review review : reviews) {
            if (review.getUser() == user && review.getFilm() == film) {
                found = review;
                break;
            }
        }
        
        return found;
    }

    @Override
    public void update(Review object) throws SQLException {
        Review update = (Review) object;
        
        for (Review review : reviews) {
            if (review.getId() == update.getId()) {
                review = update;
            }
        }
    }

    @Override
    public List findAllToWatchByUser(User user) throws SQLException {
        List<Review> found = new ArrayList<>();
        
        for (Review review : reviews) {
            if (review.getUser() == user && review.getTag().getName().equals("toWatch")) {
                found.add(review);
            }
        }
        
        return found;
    }
    
    @Override
    public List findAllSeenByUser(User user) throws SQLException {
        List<Review> found = new ArrayList<>();
        
        for (Review review : reviews) {
            if (review.getUser() == user && review.getTag().getName().equals("seen")) {
                found.add(review);
            }
        }
        
        return found;
    }
    
}
