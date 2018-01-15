package com.kevin.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by Kevin Kimaru Chege on 12/2/2017.
 */

@Entity
@Table(name = "USERS")
public class User implements Serializable {

    private static final long serialVersionUID = -825634229676522580L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;
    @NotNull
    protected String firstName;
    @NotNull
    protected String lastName;
    @NotNull
    protected int yearOfBirth;

    @ElementCollection
    protected Map<String, String> cities;

    public User() {

    }

    public User(String firstName, String lastName, int yearOfBirth, Map<String, String> cities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.cities = cities;
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Map<String, String> getCities() {
        return cities;
    }

    public void setCities(Map<String, String> cities) {
        this.cities = cities;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (yearOfBirth != user.yearOfBirth) return false;
        if (!firstName.equals(user.firstName)) return false;
        return lastName.equals(user.lastName);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + yearOfBirth;
        return result;
    }
}
