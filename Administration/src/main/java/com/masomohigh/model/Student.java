package com.masomohigh.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 12/10/2017.
 */
@Entity
public class Student implements Serializable {

    private static final long serialVersionUID = 4685631589912848921L;

    @Id
    protected int fileNumber;
    protected String password;
    protected String firstName;
    protected String middleName;
    protected String lastName;
    @Temporal(TemporalType.DATE)
    protected Date dateOfBirth;
    @ElementCollection
    protected List<String> subjects;
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "HOUSE_ID", referencedColumnName = "id"),
            @JoinColumn(name = "HOUSE_NAME", referencedColumnName = "name")})
    protected House house;
    protected String stream;
    protected int form;
    @OneToOne(mappedBy = "student")
    protected Guardian guardian;
    @Temporal(TemporalType.DATE)
    protected Date dateOfAdmission;
    @ManyToMany
    @JoinTable(
            name = "STUDENT_CLASS",
            joinColumns =
            @JoinColumn(name = "STUDENT_ID", referencedColumnName = "fileNumber"),
            inverseJoinColumns =
                    {@JoinColumn(name = "CLASS_ID", referencedColumnName = "classId"),
                            @JoinColumn(name = "CLASS_NAME", referencedColumnName = "name")}
    )
    protected List<Class> classes;
    protected String status;

    public Student() {
    }

    /**
     * Missing: subjects, guardinan, classes
     */
    public Student(int fileNumber, String password, String firstName, String middleName, String lastName, Date dateOfBirth, House house, String stream,
                   int form, Date dateOfAdmission, String status) {
        this.fileNumber = fileNumber;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.house = house;
        this.stream = stream;
        this.form = form;
        this.dateOfAdmission = dateOfAdmission;
        this.status = status;
        classes = new ArrayList<Class>();
    }

    public int getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(int fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public int getForm() {
        return form;
    }

    public void setForm(int form) {
        this.form = form;
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public void setGuardian(Guardian guardian) {
        this.guardian = guardian;
    }

    public Date getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(Date dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
