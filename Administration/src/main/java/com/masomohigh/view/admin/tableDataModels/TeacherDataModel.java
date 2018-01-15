package com.masomohigh.view.admin.tableDataModels;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Kevin Kimaru Chege on 1/3/2018.
 */
public class TeacherDataModel {
    private final SimpleStringProperty idNumber;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty middleName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty phoneNumber;
    private final SimpleStringProperty subjects;
    private final SimpleStringProperty status;


    public TeacherDataModel(String idNumber, String firstName, String middleName,
                                  String lastName, String subjects, String phoneNumber, String status) {
        this.idNumber = new SimpleStringProperty(idNumber);
        this.firstName = new SimpleStringProperty(firstName);
        this.middleName =new SimpleStringProperty(middleName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.status = new SimpleStringProperty(status);
        this.subjects = new SimpleStringProperty(subjects);
    }

    public String getIdNumber() {
        return idNumber.get();
    }

    public void setIdNumber(String idNumber) {
        this.idNumber.set(idNumber);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getMiddleName() {
        return middleName.get();
    }

    public void setMiddleName(String middleName) {
        this.middleName.set(middleName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getSubjects() {
        return subjects.get();
    }

    public void setSubjects(String subjects) {
        this.subjects.set(subjects);
    }
}
