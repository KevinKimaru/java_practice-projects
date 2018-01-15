package com.masomohigh.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 12/10/2017.
 */
@Entity
public abstract class Staff implements Serializable {
    private static final long serialVersionUID = 8942582153559426625L;

    @Id
    protected int idNumber;
    protected String firstName;
    protected String middleName;
    protected String lastName;
    protected String password;
    @Temporal(TemporalType.DATE)
    protected Date dateOfBirth;
    protected String phoneNumber;
    protected String email;
    protected Address address;
    @Temporal(TemporalType.DATE)
    protected Date dateOfStart;
    protected List<String> obligations;
    protected List<String> obligationsBackup;
    protected List<String> achievements;
    protected String status;

    public Staff() {
    }

    /**Missing: obligations, obligationsBackup, achievements*/
    public Staff(int idNumber, String firstName, String middleName, String lastName, String password, Date dateOfBirth,
                 String phoneNumber, String email, Address address, Date dateOfStart, String status) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.dateOfStart = dateOfStart;
        this.status = status;
        obligations = new ArrayList<String>();
        obligationsBackup = new ArrayList<String>();
        achievements = new ArrayList<String>();
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Date getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(Date dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public List<String> getObligations() {
        return obligations;
    }

    public void setObligations(List<String> obligations) {
        this.obligations = obligations;
    }

    public List<String> getObligationsBackup() {
        return obligationsBackup;
    }

    public void setObligationsBackup(List<String> obligationsBackup) {
        this.obligationsBackup = obligationsBackup;
    }

    public List<String> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<String> achievements) {
        this.achievements = achievements;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
