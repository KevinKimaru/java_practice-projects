package com.kevin.review;

import com.kevin.core.BaseEntity;
import com.kevin.course.Course;
import com.kevin.user.User;

import javax.persistence.*;

/**
 * Created by Kevin Kimaru Chege on 3/4/2018.
 */
@Entity
public class Review extends BaseEntity {
    private int rating;
    private String description;
    @ManyToOne
    private Course course;
    @ManyToOne
    private User reviewer;

    protected Review() {
        super();
    }

    public Review(int rating, String description) {
        this.rating = rating;
        this.description = description;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}