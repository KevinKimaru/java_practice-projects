package com.masomohigh.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Kevin Kimaru Chege on 12/10/2017.
 */
@Entity
public class Teacher extends Staff implements Serializable {
    private static final long serialVersionUID = 8942582153559426625L;

    protected List<String> subjects;
    /**in this form: subject class year term*/
    @ElementCollection
    @ManyToMany
    protected List<String> classesTeaching;
    /**in this form: subject class date year term*/
    @ElementCollection
    @ManyToMany
    protected List<String> classesTeachingBackup;

    public Teacher() {}

    /**Missing: classesTeaching, classesTeaching obligations, obligationsBackup, achievements*/
    public Teacher(int idNumber, String firstName, String middleName, String lastName, String password, Date dateOfBirth,
                   String phoneNumber, String email, Address address, Date dateOfStart, String status) {
        super(idNumber, firstName, middleName, lastName, password, dateOfBirth, phoneNumber, email, address, dateOfStart, status);
        subjects = new ArrayList<>();
        classesTeaching = new ArrayList<>();
        classesTeachingBackup = new ArrayList<>();
    }

    /**Missing: subjects, classesTeaching, classeseachingBackup*/


    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getClassesTeaching() {
        return classesTeaching;
    }

    public void setClassesTeaching(List<String> classesTeaching) {
        this.classesTeaching = classesTeaching;
    }

    public List<String> getClassesTeachingBackup() {
        return classesTeachingBackup;
    }

    public void setClassesTeachingBackup(List<String> classesTeachingBackup) {
        this.classesTeachingBackup = classesTeachingBackup;
    }
}
