package com.kevin.web.model;

import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 7/31/2017.
 */
public interface CourseIdeaDAO {
    boolean add(CourseIdea idea);

    List<CourseIdea> findAll();

    CourseIdea findBySlug(String slug);
}
