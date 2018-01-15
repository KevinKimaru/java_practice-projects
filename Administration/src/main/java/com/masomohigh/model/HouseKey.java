package com.masomohigh.model;

import java.io.Serializable;

/**
 * Created by Kevin Kimaru Chege on 12/10/2017.
 */
public class HouseKey implements Serializable {
    private static final long serialVersionUID = 4693636589912848921L;

    protected int id;
    protected String name;

    public HouseKey() {}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HouseKey houseKey = (HouseKey) o;

        if (id != houseKey.id) return false;
        return name.equals(houseKey.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "HouseKey{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
