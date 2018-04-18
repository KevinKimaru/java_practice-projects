package com.kevin.giflib.model;

import java.time.LocalDate;

/**
 * Created by Kevin Kimaru Chege on 2/12/2018.
 */
public class Gif {
    private String name;
    private int categoryId;
    private LocalDate dateuploaded;
    private String username;
    private boolean favourite;

    public Gif(String name, int categoryId, LocalDate dateuploaded, String username, boolean favourite) {
        this.name = name;
        this.categoryId = categoryId;
        this.dateuploaded = dateuploaded;
        this.username = username;
        this.favourite = favourite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDate getDateuploaded() {
        return dateuploaded;
    }

    public void setDateuploaded(LocalDate dateuploaded) {
        this.dateuploaded = dateuploaded;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
