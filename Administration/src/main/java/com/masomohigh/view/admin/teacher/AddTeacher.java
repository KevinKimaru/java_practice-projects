package com.masomohigh.view.admin.teacher;

import com.masomohigh.controller.TeacherController;
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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

/**
 * Created by Kevin Kimaru Chege on 1/2/2018.
 */
public class AddTeacher {
    private AdministartionBlockTitle administartionBlockTitle;

    private VBox mainAdminSignUpVBox;
    private GridPane signUpViewGridPane;
    private HBox buttonsHBox;
    private ScrollPane mScrollPane;
    private Label addAdminLabel;

    private Button saveBtn;
    private Button cancelBtn;

    private Label idNumberLabel;
    private Label firstNameLabel;
    private Label middleNameLabel;
    private Label lastNameLabel;
    private Label phoneNumberLabel;
    private Label addressPostalNoLabel;
    private Label addressPostalCodeLabel;
    private Label addressPostalTownLabel;
    private Label dateOfBirthLabel;
    private Label dateOfStartLabel;
    private Label emailLabel;
    private Label subjectsLabel;

    private TextField idNumberTextField;
    private TextField firstNameTextField;
    private TextField middleNameTextField;
    private TextField lastNameTextField;
    private TextField phoneNumberTextField;
    private TextField addressPostalNoTextField;
    private TextField addressPostalCodeTextField;
    private TextField addressPostalTownTextField;
    private DatePicker dateOfBirthDatePicker;
    private DatePicker dateOfStartDatePicker;
    private TextField emailTextField;
    private ListView<String> subjectsListView;

    public AddTeacher() {
        mainAdminSignUpVBox = new VBox();
        signUpViewGridPane = new GridPane();
        buttonsHBox = new HBox();
        mScrollPane = new ScrollPane();
        addAdminLabel = new Label("ADD TEACHER");

        saveBtn = new Button("Save");
        cancelBtn = new Button("Cancel");
        buttonsHBox.getChildren().addAll(saveBtn, cancelBtn);

        addressPostalCodeLabel = new Label("Postal Code");
        addressPostalNoLabel = new Label("Postal Number");
        addressPostalTownLabel = new Label("Postal Town");
        idNumberLabel = new Label("Id Number");
        firstNameLabel = new Label("First Name");
        middleNameLabel = new Label("Middle Name");
        lastNameLabel = new Label("Last Name");
        phoneNumberLabel = new Label("Phone Number");
        dateOfBirthLabel = new Label("Date of Birth");
        dateOfStartLabel = new Label("Date of Start");
        emailLabel = new Label("Email");
        subjectsLabel = new Label("Subjects");

        addressPostalNoTextField = new TextField();
        addressPostalCodeTextField = new TextField();
        addressPostalTownTextField = new TextField();
        idNumberTextField = new TextField();
        firstNameTextField = new TextField();
        middleNameTextField = new TextField();
        lastNameTextField = new TextField();
        phoneNumberTextField = new TextField();
        dateOfBirthDatePicker = new DatePicker();
        dateOfStartDatePicker = new DatePicker();
        emailTextField = new TextField();
        subjectsListView = new ListView<String>(FXCollections.observableArrayList(MainApp.ALLSUBJECTS));
        subjectsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        signUpViewGridPane.add(idNumberLabel, 0, 0);
        signUpViewGridPane.add(idNumberTextField, 1, 0);
        signUpViewGridPane.add(firstNameLabel, 0, 1);
        signUpViewGridPane.add(firstNameTextField, 1, 1);
        signUpViewGridPane.add(middleNameLabel, 0, 2);
        signUpViewGridPane.add(middleNameTextField, 1, 2);
        signUpViewGridPane.add(lastNameLabel, 0, 3);
        signUpViewGridPane.add(lastNameTextField, 1, 3);
        signUpViewGridPane.add(phoneNumberLabel, 0, 4);
        signUpViewGridPane.add(phoneNumberTextField, 1, 4);
        signUpViewGridPane.add(emailLabel, 0, 5);
        signUpViewGridPane.add(emailTextField, 1, 5);
        signUpViewGridPane.add(dateOfBirthLabel, 0, 6);
        signUpViewGridPane.add(dateOfBirthDatePicker, 1, 6);
        signUpViewGridPane.add(dateOfStartLabel, 0, 7);
        signUpViewGridPane.add(dateOfStartDatePicker, 1, 7);
        signUpViewGridPane.add(addressPostalNoLabel, 0, 8);
        signUpViewGridPane.add(addressPostalNoTextField, 1, 8);
        signUpViewGridPane.add(addressPostalCodeLabel, 0, 9);
        signUpViewGridPane.add(addressPostalCodeTextField, 1, 9);
        signUpViewGridPane.add(addressPostalTownLabel, 0, 10);
        signUpViewGridPane.add(addressPostalTownTextField, 1, 10);
        signUpViewGridPane.add(subjectsLabel, 0, 11);
        signUpViewGridPane.add(subjectsListView, 1, 11);

        mScrollPane.setContent(signUpViewGridPane);

        administartionBlockTitle = new AdministartionBlockTitle();
        mainAdminSignUpVBox.getChildren().addAll(administartionBlockTitle
                        .getTopAdminBarBorderPane(), addAdminLabel,
                mScrollPane, buttonsHBox);

        beautifyView();
        setButtonsActions();
    }

    public AdministartionBlockTitle getAdministartionBlockTitle() {
        return administartionBlockTitle;
    }

    public VBox getMainAdminSignUpVBox() {
        return mainAdminSignUpVBox;
    }

    private void beautifyView() {
        mainAdminSignUpVBox.getStyleClass().addAll("mainViews");
        mainAdminSignUpVBox.setAlignment(Pos.TOP_CENTER);
        mainAdminSignUpVBox.setSpacing(10.0);

        signUpViewGridPane.getStyleClass().addAll("defaultBackground", "defaultFont", "font2",
                "nodePadding1", "childrenSpacing2");
        signUpViewGridPane.setHgap(10.0);
        signUpViewGridPane.setVgap(10.0);
        mScrollPane.setFitToWidth(true);

        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(20.0);

        cancelBtn.getStyleClass().add("btnDanger");
        saveBtn.getStyleClass().add("btnInfo");

        addAdminLabel.getStyleClass().addAll("title2");
    }

    private void setButtonsActions() {
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (isValid()) {
                    addTeacher();
                    MainApp.allViewsAdminTeacher.getAllTeachers().addDataToTable();
                    MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminTeacher.getAllTeachers().getViewTeachersVBox());
                } else {

                }
            }
        });
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clear();
                MainApp.allViewsAdminAdmin.getViewAdmins().addDataToTable();
                MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminAdmin.getViewAdmins().getViewAdminsVBox());
            }
        });
    }

    private void addTeacher() {
        int idNumber = 0;
        String firstName = "";
        String middleName = "";
        String lastName = "";
        String phoneNumber = "";
        String email = "";
        int baltex = 0;
        int postalCode = 0;
        String city = "";
        LocalDate dateOfBirthLD = null;
        LocalDate dateOfStartLD = null;
        List<String> subjectsList;
        try {
            idNumber = Integer.valueOf(idNumberTextField.getText().trim());
            firstName = firstNameTextField.getText().trim();
            middleName = middleNameTextField.getText().trim();
            lastName = lastNameTextField.getText().trim();
            phoneNumber = phoneNumberTextField.getText().trim();
            email = emailTextField.getText().trim();
            subjectsList = subjectsListView.getSelectionModel().getSelectedItems();


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

            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.getButtonTypes().removeAll(confirmAlert.getButtonTypes());
            confirmAlert.getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
            confirmAlert.setContentText("Save Administrator: \n" +
                    "Id Number: " + idNumber + "\n" +
                    "First Name: " + firstName + "\n" +
                    "Middle Name: " + middleName + "\n" +
                    "Last Name: " + lastName + "\n" +
                    "??"
            );
            Optional<ButtonType> results = confirmAlert.showAndWait();
            if (results.get() == ButtonType.YES) {
                try {
                    TeacherController.createTeacher(idNumber, firstName, middleName, lastName, dateOfBirth, dateOfStart,
                            phoneNumber, email, baltex, postalCode, city, subjectsList);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("An error occurred: \n" + e.getMessage());
                    alert.showAndWait();
                    return;
                }
                TeacherUtilities.getAllTeachers();
            } else if (results.get() == ButtonType.CANCEL) {
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            Alert errAlert = new Alert(Alert.AlertType.ERROR);
            errAlert.setContentText("An error occurred: \n" + e.getMessage());
            errAlert.showAndWait();
            return;
        }

        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setContentText("Successfully Saved Administrator: \n" +
                "Id Number: " + idNumber + "\n" +
                "First Name: " + firstName + "\n" +
                "Middle Name: " + middleName + "\n" +
                "Last Name: " + lastName + "\n"

        );
        successAlert.showAndWait();
        clear();
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
        if (subjectsListView.getSelectionModel().getSelectedIndices().size() <= 0) {
            reqErrMessage += "Subjects field is required\n";
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
        subjectsListView.getSelectionModel().clearSelection();
    }
}
