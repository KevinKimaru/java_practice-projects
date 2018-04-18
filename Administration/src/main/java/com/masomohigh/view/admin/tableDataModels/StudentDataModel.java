package com.masomohigh.view.admin.tableDataModels;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Kevin Kimaru Chege on 2/3/2018.
 */
public class StudentDataModel {
    private final SimpleStringProperty fileNumber;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty middleName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty subjects;
    private final SimpleStringProperty status;


    public StudentDataModel(String fileNumber, String firstName, String middleName,
                            String lastName, String subjects, String status) {
        this.fileNumber = new SimpleStringProperty(fileNumber);
        this.firstName = new SimpleStringProperty(firstName);
        this.middleName =new SimpleStringProperty(middleName);
        this.lastName = new SimpleStringProperty(lastName);
        this.status = new SimpleStringProperty(status);
        this.subjects = new SimpleStringProperty(subjects);
    }

    public String getFileNumber() {
        return fileNumber.get();
    }

    public SimpleStringProperty fileNumberProperty() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber.set(fileNumber);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getMiddleName() {
        return middleName.get();
    }

    public SimpleStringProperty middleNameProperty() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName.set(middleName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getSubjects() {
        return subjects.get();
    }

    public SimpleStringProperty subjectsProperty() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects.set(subjects);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
