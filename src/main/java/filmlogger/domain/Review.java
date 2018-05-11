
package filmlogger.domain;

import java.util.*;
import java.time.*;

public class Review implements Comparable {
    private Integer id;
    private User user;
    private Film film;
    private Tag tag;
    private LocalDate date;
    private Integer rating;
    private String review;
    
    public Review(Integer id, User user, Film film, Tag tag, LocalDate date, Integer rating, String review) {
        this.id = id;
        this.user = user;
        this.film = film;
        this.tag = tag;
        this.date = date;
        this.rating = rating;
        this.review = review;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Film getFilm() {
        return film;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
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

    @Override
    public int compareTo(Object o) {
        return this.getFilm().getName().compareTo(((Review) o).getFilm().getName());
    }
}

