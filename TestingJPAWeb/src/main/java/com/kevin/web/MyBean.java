package com.kevin.web;

import com.kevin.ejb.ModelBean;
import com.kevin.model.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 12/2/2017.
 */

@Named("myBean")
@SessionScoped
public class MyBean {
    @EJB
    ModelBean mb;

    private String firstName;
    private String lastName;
    private int yearOfBirth;
    private List<User> users = new ArrayList<User>();
    private User user;
    private ModelBean m;

    public int getNo() {
        return mb.getNumber();
    }

    public void setNo(int no) {
        this.no = no;
    }

    private int no;

    public MyBean() {
//        m = new ModelBean();
    }

    public List<User> getUsers() {
        setUsers();
        return users;
    }

    public void setUsers() {
//        this.users = m.getUsers();
    }

    public String getFirstName() {
        return getUser().getFirstName();
//        return "Kevin";
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return getUser().getLastName();
//        return "Kimaru";
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYearOfBirth() {
        return getUser().getYearOfBirth();
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }


    public User getUser() {
        for (User user: users) {
            this.user = user;
        }
        return this.user;
    }
}
