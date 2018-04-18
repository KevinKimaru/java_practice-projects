package com.microfundit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 4/4/2018.
 */
@Entity
public class User extends BaseEntity {
    private String username;
    @JsonIgnore
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade= CascadeType.ALL, mappedBy = "users")
    private List<Role> roles;

    protected User() {
        super();
        roles = new ArrayList<>();
    }

    public User(String username, String password, List<Role> roles) {
        this();
        this.username = username;
        setPassword(password);
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
