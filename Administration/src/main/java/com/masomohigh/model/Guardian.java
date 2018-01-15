package com.masomohigh.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kevin Kimaru Chege on 12/10/2017.
 */
@Entity
public class Guardian implements Serializable {
    private static final long serialVersionUID = 4685631589912848921L;

    @Id
    protected int idNumber;
    protected int password;
    protected String firstName;
    protected String middleName;
    protected String lastName;
    @Temporal(TemporalType.DATE)
    protected Date dateOfBirth;
    protected String email;
    @Embedded
    protected Address address;
    protected String phoneNumber;
    @OneToOne
    @JoinColumn(name = "fileNumber")
    protected Student student;

    public Guardian() {}

    /**Missing: student*/
    public Guardian(int idNumber, int password, String firstName, String middleName, String lastName, Date dateOfBirth,
                    String email, Address address, String phoneNumber) {
        this.idNumber = idNumber;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
