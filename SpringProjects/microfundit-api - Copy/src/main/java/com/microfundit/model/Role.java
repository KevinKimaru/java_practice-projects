package com.microfundit.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 3/26/2018.
 */
@Entity
public class Role extends BaseEntity {
    private String name;
    @ManyToMany
    private List<User> users;

    protected Role() {
        super();
        users = new ArrayList<>();
    }

    public Role(String name) {
        this();
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
