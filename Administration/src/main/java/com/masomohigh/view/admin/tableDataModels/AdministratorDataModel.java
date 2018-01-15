package com.masomohigh.view.admin.tableDataModels;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Kevin Kimaru Chege on 12/24/2017.
 */
public class AdministratorDataModel {
    private final SimpleStringProperty idNumber;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty middleName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty phoneNumber;
    private final SimpleStringProperty obligations;
    private final SimpleStringProperty status;


    public AdministratorDataModel(String idNumber, String firstName, String middleName,
                                  String lastName, String phoneNumber, String obligations,
                                  String status) {
        this.idNumber = new SimpleStringProperty(idNumber);
        this.firstName = new SimpleStringProperty(firstName);
        this.middleName =new SimpleStringProperty(middleName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.obligations = new SimpleStringProperty(obligations);
        this.status = new SimpleStringProperty(status);
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

    public String getObligations() {
        return obligations.get();
    }

    public void setObligations(String obligations) {
        this.obligations.set(obligations);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
