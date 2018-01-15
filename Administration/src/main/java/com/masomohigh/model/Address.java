package com.masomohigh.model;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Kevin Kimaru Chege on 12/10/2017.
 */
@Embeddable
public class Address implements Serializable {

    private static final long serialVersionUID = 4685631589912848921L;

    protected int baltex;
    protected int postalCode;
    protected String city;

    public Address() {}

    public Address(int baltex, int postalCode, String city) {
        this.baltex = baltex;
        this.postalCode = postalCode;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getBaltex() {
        return baltex;
    }

    public void setBaltex(int baltex) {
        this.baltex = baltex;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "P.O. Box " + baltex +
                "-" + postalCode +
                " " + city;
    }
}
