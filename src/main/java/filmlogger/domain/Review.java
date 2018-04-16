
package filmlogger.domain;

import java.util.*;
import java.time.*;

public class Review {
    private Integer id;
    private Integer filmID;
    private Integer userID;
    private LocalDate date;
    private Integer rating;
    private String review;
    
    public Review(Integer id, Integer userID, Integer filmID, LocalDate date, Integer rating, String review) {
        this.id = id;
        this.userID = userID;
        this.filmID = filmID;
        this.date = date;
        this.rating = rating;
        this.review = review;
    }

    public Integer getId() {
        return id;
    }

    public Integer getFilmID() {
        return filmID;
    }

    public void setFilmID(Integer filmID) {
        this.filmID = filmID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}

