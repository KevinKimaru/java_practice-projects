package com.masomohigh.model;

import java.io.Serializable;

/**
 * Created by Kevin Kimaru Chege on 12/10/2017.
 */
public class ClassKey implements Serializable {
    private static final long serialVersionUID = 4693636589912848921L;

    protected int classId;
    protected String name;

    public ClassKey() {}

    public ClassKey(int classId, String name) {
        this.classId = classId;
        this.name = name;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
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

        ClassKey classKey = (ClassKey) o;

        if (classId != classKey.classId) return false;
        return name.equals(classKey.name);
    }

    @Override
    public int hashCode() {
        int result = classId;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ClassKey{" +
                "classId=" + classId +
                ", name='" + name + '\'' +
                '}';
    }
}
