package com.masomohigh.view.admin.administrator;

import com.masomohigh.controller.AdminController;
import com.masomohigh.model.Administrator;
import com.masomohigh.view.MainApp;
import com.masomohigh.view.AdminUtilities;
import com.masomohigh.view.admin.AdministartionBlockTitle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

/**
 * Created by Kevin Kimaru Chege on 12/26/2017.
 */
public class UpdateAdmin {

    private AdministartionBlockTitle administartionBlockTitle;
    private Label updateAdminLabel;

    private VBox mainAdminUpdateVBox;
    private GridPane updateViewGridPane;
    private HBox buttonsHBox;
    private ScrollPane mScrollPane;

    private Button saveBtn;
    private Button moreBtn;
    private Button cancelBtn;
    private Button changePasswordBtn;

    private Label idNumberLabel;
    private Label firstNameLabel;
    private Label middleNameLabel;
    private Label lastNameLabel;
    private Label phoneNumberLabel;
    private Label emailLabel;
    private Label addressPostalNoLabel;
    private Label addressPostalCodeLabel;
    private Label addressPostalTownLabel;
    private Label dateOfBirthLabel;
    private Label dateOfStartLabel;
    private Label statusLabel;

    private TextField idNumberTextField;
    private TextField firstNameTextField;
    private TextField middleNameTextField;
    private TextField lastNameTextField;
    private TextField phoneNumberTextField;
    private TextField emailTextField;
    private TextField addressPostalNoTextField;
    private TextField addressPostalCodeTextField;
    private TextField addressPostalTownTextField;
    private DatePicker dateOfBirthDatePicker;
    private DatePicker dateOfStartDatePicker;
    private ComboBox statusComboBox;

    public UpdateAdmin() {
        updateAdminLabel = new Label("UPDATE ADMIN");

        mainAdminUpdateVBox = new VBox();
        updateViewGridPane = new GridPane();
        buttonsHBox = new HBox();
        mScrollPane = new ScrollPane();

        saveBtn = new Button("Save");
        cancelBtn = new Button("Cancel");
        moreBtn = new Button("More");
        changePasswordBtn = new Button("Change Password");
        buttonsHBox.getChildren().addAll(saveBtn, changePasswordBtn, moreBtn, cancelBtn);

        addressPostalCodeLabel = new Label("Postal Code");
        addressPostalNoLabel = new Label("Postal Number");
        addressPostalTownLabel = new Label("Postal Town");
        idNumberLabel = new Label("Id Number");
        firstNameLabel = new Label("First Name");
        middleNameLabel = new Label("Middle Name");
        lastNameLabel = new Label("Last Name");
        phoneNumberLabel = new Label("Phone Number");
        emailLabel = new Label("Email");
        dateOfBirthLabel = new Label("Date Of Birth");
        dateOfStartLabel = new Label("Date Of Start");
        statusLabel = new Label("Status");

        addressPostalNoTextField = new TextField();
        addressPostalCodeTextField = new TextField();
        addressPostalTownTextField = new TextField();
        idNumberTextField = new TextField();
        firstNameTextField = new TextField();
        firstNameTextField.setText("Kevin");
        middleNameTextField = new TextField();
        lastNameTextField = new TextField();
        phoneNumberTextField = new TextField();
        emailTextField = new TextField();
        dateOfBirthDatePicker = new DatePicker();
        dateOfStartDatePicker = new DatePicker();
        statusComboBox = new ComboBox();
        statusComboBox.setItems(FXCollections.observableArrayList(MainApp.STAFFSTATUSES));

        updateViewGridPane.add(idNumberLabel, 0, 0);
        updateViewGridPane.add(idNumberTextField, 1, 0);
        updateViewGridPane.add(firstNameLabel, 0, 1);
        updateViewGridPane.add(firstNameTextField, 1, 1);
        updateViewGridPane.add(middleNameLabel, 0, 2);
        updateViewGridPane.add(middleNameTextField, 1, 2);
        updateViewGridPane.add(lastNameLabel, 0, 3);
        updateViewGridPane.add(lastNameTextField, 1, 3);
        updateViewGridPane.add(phoneNumberLabel, 0, 4);
        updateViewGridPane.add(phoneNumberTextField, 1, 4);
        updateViewGridPane.add(emailLabel, 0, 5);
        updateViewGridPane.add(emailTextField, 1, 5);
        updateViewGridPane.add(addressPostalNoLabel, 0, 6);
        updateViewGridPane.add(addressPostalNoTextField, 1, 6);
        updateViewGridPane.add(addressPostalCodeLabel, 0, 7);
        updateViewGridPane.add(addressPostalCodeTextField, 1, 7);
        updateViewGridPane.add(addressPostalTownLabel, 0, 8);
        updateViewGridPane.add(addressPostalTownTextField, 1, 8);
        updateViewGridPane.add(dateOfBirthLabel, 0, 9);
        updateViewGridPane.add(dateOfBirthDatePicker, 1, 9);
        updateViewGridPane.add(dateOfStartLabel, 0, 10);
        updateViewGridPane.add(dateOfStartDatePicker, 1, 10);
        updateViewGridPane.add(statusLabel, 0, 11);
        updateViewGridPane.add(statusComboBox, 1, 11);

        mScrollPane.setContent(updateViewGridPane);

        administartionBlockTitle = new AdministartionBlockTitle();
        mainAdminUpdateVBox.getChildren().addAll(administartionBlockTitle
                        .getTopAdminBarBorderPane(), updateAdminLabel,
                mScrollPane, buttonsHBox);

        beautifyView();
        setActionsForButtons();
    }

    public VBox getMainAdminUpdateVBox() {
        return mainAdminUpdateVBox;
    }

    public AdministartionBlockTitle getAdministartionBlockTitle() {
        return administartionBlockTitle;
    }

    private void beautifyView() {
        mainAdminUpdateVBox.getStyleClass().addAll("mainViews");
        mainAdminUpdateVBox.setSpacing(10.0);
        mainAdminUpdateVBox.setAlignment(Pos.TOP_CENTER);

        updateViewGridPane.getStyleClass().addAll("defaultBackground", "defaultFont", "font2",
                "nodePadding1", "childrenSpacing2");
        updateViewGridPane.setHgap(10.0);
        updateViewGridPane.setVgap(10.0);
        mScrollPane.setFitToWidth(true);

        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(20.0);

        cancelBtn.getStyleClass().add("btnDanger");
        saveBtn.getStyleClass().add("btnInfo");
        moreBtn.getStyleClass().add("btnInfo");
        changePasswordBtn.getStyleClass().add("btnInfo3");

        updateAdminLabel.getStyleClass().add("title2");
    }

    private void setActionsForButtons() {
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                MainApp.allViewsAdminAdmin.getViewAdminDetails().populateView();
                MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminAdmin.getViewAdminDetails().getViewAdminDetailsVBox());
            }
        });
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (isValid()) {
                    updateAdmin();
                }
            }
        });
        moreBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.getButtonTypes().removeAll(alert.getButtonTypes());
                alert.getButtonTypes().addAll(ButtonType.NEXT, ButtonType.CANCEL);
                alert.setContentText("Please ensure you have saved the data you want updated first as it will be lost.");
                Optional<ButtonType> results = alert.showAndWait();
                if (results.get() == ButtonType.NEXT) {
                    MainApp.allViewsAdminAdmin.getUpdateAdminMore().populateListViews();
                    MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminAdmin.getUpdateAdminMore().getUpdateAdminMoreVBox());
                } else {

                }
            }
        });
        changePasswordBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainApp.allViewsAdminAdmin.getAdminChangePassword().setLabeltext();
                MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminAdmin.getAdminChangePassword().getAdminChangePasswordVBox());
            }
        });
    }

    protected void populateTextFields() {
        AdminUtilities.setSelectedAdministrator();
        try {
            Administrator administrator = AdminUtilities.SELECTED_ADMINISTRATOR;
            System.out.println(AdminUtilities.SELECTED_ADMINISTRATOR_ID);
            idNumberTextField.setText(String.valueOf(administrator.getIdNumber()));
            firstNameTextField.setText(administrator.getFirstName());
            middleNameTextField.setText(administrator.getMiddleName());
            lastNameTextField.setText(administrator.getLastName());
            phoneNumberTextField.setText(administrator.getPhoneNumber());
            emailTextField.setText(administrator.getEmail());
            statusComboBox.setValue(administrator.getStatus());

            GregorianCalendar dobCalendar = new GregorianCalendar();
            dobCalendar.setTime(administrator.getDateOfBirth());
            dateOfBirthDatePicker.setValue(LocalDate.of(dobCalendar.get(Calendar.YEAR), dobCalendar.get(Calendar.MONTH) + 1,
                    dobCalendar.get(Calendar.DAY_OF_MONTH)));

            GregorianCalendar dosCalendar = new GregorianCalendar();
            dosCalendar.setTime(administrator.getDateOfStart());
            dateOfStartDatePicker.setValue(LocalDate.of(dosCalendar.get(Calendar.YEAR), dosCalendar.get(Calendar.MONTH) + 1,
                    dosCalendar.get(Calendar.DAY_OF_MONTH)));

            addressPostalNoTextField.setText(String.valueOf(administrator.getAddress().getBaltex()));
            addressPostalCodeTextField.setText(String.valueOf(administrator.getAddress().getPostalCode()));
            addressPostalTownTextField.setText(String.valueOf(administrator.getAddress().getCity()));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void updateAdmin() {
        int idNumber = 0;
        String firstName = "";
        String middleName = "";
        String lastName = "";
        String phoneNumber = "";
        String email = "";
        int baltex = 0;
        int postalCode = 0;
        String city = "";
        String status = "";
        LocalDate dateOfBirthLD = null;
        LocalDate dateOfStartLD = null;

        String updatedFields = "";
        Administrator administrator = AdminUtilities.SELECTED_ADMINISTRATOR;

        int updatedCount = 0;

        try {
            idNumber = Integer.valueOf(idNumberTextField.getText().trim());
            firstName = firstNameTextField.getText().trim();
            middleName = middleNameTextField.getText().trim();
            lastName = lastNameTextField.getText().trim();
            phoneNumber = phoneNumberTextField.getText().trim();
            email = emailTextField.getText().trim();
            status = (String) statusComboBox.getValue();


            dateOfBirthLD = dateOfBirthDatePicker.getValue();
            GregorianCalendar dobGregorianCalendar = new GregorianCalendar(dateOfBirthLD.getYear(),
                    dateOfBirthLD.getMonthValue() - 1, dateOfBirthLD.getDayOfMonth());
            Date dateOfBirth = dobGregorianCalendar.getTime();

            dateOfStartLD = dateOfStartDatePicker.getValue();
            GregorianCalendar doSGregorianCalendar = new GregorianCalendar(dateOfStartLD.getYear(),
                    dateOfStartLD.getMonthValue() - 1, dateOfStartLD.getDayOfMonth());
            Date dateOfStart = doSGregorianCalendar.getTime();

            baltex = Integer.valueOf(addressPostalNoTextField.getText().trim());
            postalCode = Integer.valueOf(addressPostalCodeTextField.getText().trim());
            city = addressPostalTownTextField.getText().trim();

            if (!(idNumber == administrator.getIdNumber())) {
                updatedFields += "Id Number: " + administrator.getIdNumber() + " to " + idNumber + "\n";
                updatedCount++;
            }
            if (!firstName.contentEquals(administrator.getFirstName())) {
                updatedFields += "First Name: " + administrator.getFirstName() + " to " + firstName + "\n";
                updatedCount++;
            }
            if (!middleName.contentEquals(administrator.getMiddleName())) {
                updatedFields += "Middle Name: " + administrator.getMiddleName() + " to " + middleName + "\n";
                updatedCount++;
            }
            if (!lastName.contentEquals(administrator.getLastName())) {
                updatedFields += "Last Name: " + administrator.getLastName() + " to " + lastName + "\n";
                updatedCount++;
            }
            if (!phoneNumber.contentEquals(administrator.getPhoneNumber())) {
                updatedFields += "Id Number: " + administrator.getPhoneNumber() + " to " + phoneNumber + "\n";
                updatedCount++;
            }
            if (!email.contentEquals(administrator.getEmail())) {
                updatedFields += "Id Number: " + administrator.getEmail() + " to " + email + "\n";
                updatedCount++;
            }
            if (!(baltex == administrator.getAddress().getBaltex())) {
                updatedFields += "Baltex: " + administrator.getAddress().getBaltex() + " to " + baltex + "\n";
                updatedCount++;
            }
            if (!(postalCode == (administrator.getAddress().getPostalCode()))) {
                updatedFields += "Postal Code: " + administrator.getAddress().getPostalCode() + " to " + postalCode + "\n";
                updatedCount++;
            }
            if (!city.contentEquals(administrator.getAddress().getCity())) {
                updatedFields += "Postal Town: " + administrator.getAddress().getCity() + " to " + city + "\n";
                updatedCount++;
            }
            if (!dateOfBirth.equals(administrator.getDateOfBirth())) {
                updatedFields += "Date Of Birth: " + administrator.getDateOfBirth() + " to " + dateOfBirth + "\n";
                updatedCount++;
            }
            if (!dateOfStart.equals(administrator.getDateOfStart())) {
                updatedFields += "Date Of Start: " + administrator.getDateOfStart() + " to " + dateOfStart + "\n";
                updatedCount++;
            }
            if (!status.equals(administrator.getStatus())) {
                updatedFields += "Staus: " + administrator.getStatus() + " to " + status + "\n";
                updatedCount++;
            }

            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);

            if (updatedCount <= 0) {
                confirmAlert.setContentText("None of the fields was changed");
                confirmAlert.showAndWait();
                return;
            } else {
                confirmAlert.getButtonTypes().removeAll(confirmAlert.getButtonTypes());
                confirmAlert.getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
                confirmAlert.setContentText("Are you sure you want to update the following fields: \n" + updatedFields + "\n??");
                Optional<ButtonType> results = confirmAlert.showAndWait();
                if (results.get() == ButtonType.YES && updatedCount > 0) {
                    AdminController.updateAllAdministratorDetails(AdminUtilities.SELECTED_ADMINISTRATOR_ID, idNumber, firstName, middleName, lastName, dateOfBirth, dateOfStart,
                            phoneNumber, email, baltex, postalCode, city, status);
                    AdminUtilities.setSelectedAdministrator();
                    AdminUtilities.getAllAdministrators();
                } else if (results.get() == ButtonType.CANCEL) {
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Alert errAlert = new Alert(Alert.AlertType.ERROR);
            errAlert.setContentText("An error occurred: \n" + e.getMessage());
            errAlert.showAndWait();
            return;
        }

        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.getButtonTypes().removeAll(successAlert.getButtonTypes());
        successAlert.getButtonTypes().addAll(ButtonType.NEXT, ButtonType.CANCEL);
        successAlert.setContentText("Successfully updated these fields.\n"
                + updatedFields + "\n Click next to update obligations and achievements");
        Optional<ButtonType> results = successAlert.showAndWait();
        if (results.get() == ButtonType.NEXT) {
            MainApp.allViewsAdminAdmin.getUpdateAdminMore().populateListViews();
            MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminAdmin.getUpdateAdminMore().getUpdateAdminMoreVBox());
        } else {
            MainApp.allViewsAdminAdmin.getViewAdminDetails().populateView();
            MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminAdmin.getViewAdminDetails().getViewAdminDetailsVBox());
        }
        clear();
        populateTextFields();
    }

    private boolean isValid() {
        String reqErrMessage = "";
        String validityErrMess = "";
        boolean valid = true;

        if (idNumberTextField.getText().isEmpty()) {
            reqErrMessage += "ID Number field is required\n";
            valid = false;
        }
        if (firstNameTextField.getText().isEmpty()) {
            reqErrMessage += "First Name field is required\n";
            valid = false;
        }
        if (middleNameTextField.getText().isEmpty()) {
            reqErrMessage += "Middle Name field is required\n";
            valid = false;
        }
        if (lastNameTextField.getText().isEmpty()) {
            reqErrMessage += "Last Name field is required\n";
            valid = false;
        }
        if (phoneNumberTextField.getText().isEmpty()) {
            reqErrMessage += "Phone Number field is required\n";
            valid = false;
        }
        if (emailTextField.getText().isEmpty()) {
            reqErrMessage += "Email field is required\n";
            valid = false;
        }
        if (dateOfBirthDatePicker.getValue() == null) {
            reqErrMessage += "Date Of Birth field is required\n";
            valid = false;
        }
        if (dateOfStartDatePicker.getValue() == null) {
            reqErrMessage += "Date Of Start field is required\n";
            valid = false;
        }
        if (addressPostalNoTextField.getText().isEmpty()) {
            reqErrMessage += "Address Postal NUmber field is required\n";
            valid = false;
        }
        if (addressPostalCodeTextField.getText().isEmpty()) {
            reqErrMessage += "Address Postal Code field is required\n";
            valid = false;
        }
        if (addressPostalTownTextField.getText().isEmpty()) {
            reqErrMessage += "Address Postal Town field is required\n";
            valid = false;
        }
        if (statusComboBox.getValue() == null) {
            reqErrMessage += "Status field is required\n";
            valid = false;
        }

        try {
            Integer.valueOf((addressPostalNoTextField.getText().trim()));
        } catch (Exception e) {
            valid = false;
            validityErrMess += "Postal Number must be a number\n";
        }

        try {
            Integer.valueOf((addressPostalNoTextField.getText().trim()));
        } catch (Exception e) {
            valid = false;
            validityErrMess += "Postal Code must be a number\n";
        }

        try {
            Integer.valueOf((addressPostalNoTextField.getText().trim()));
        } catch (Exception e) {
            valid = false;
            validityErrMess += "Id Number must be a number\n";
        }

        if (!valid) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText(reqErrMessage + "\n\n" + validityErrMess);
            errorAlert.getButtonTypes().add(ButtonType.CANCEL);
            errorAlert.showAndWait();
        }

        return valid;
    }

    private void clear() {
        idNumberTextField.clear();
        firstNameTextField.clear();
        middleNameTextField.clear();
        lastNameTextField.clear();
        phoneNumberTextField.clear();
        emailTextField.clear();
        dateOfBirthDatePicker.setValue(null);
        dateOfStartDatePicker.setValue(null);
        addressPostalNoTextField.clear();
        addressPostalCodeTextField.clear();
        addressPostalTownTextField.clear();
    }
}
