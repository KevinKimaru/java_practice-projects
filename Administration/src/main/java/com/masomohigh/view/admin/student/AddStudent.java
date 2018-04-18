package com.masomohigh.view.admin.student;

import com.masomohigh.controller.StudentController;
import com.masomohigh.view.MainApp;
import com.masomohigh.view.admin.AdministartionBlockTitle;
import com.masomohigh.view.admin.teacher.TeacherUtilities;
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
import java.util.Optional;

/**
 * Created by Kevin Kimaru Chege on 2/3/2018.
 */
public class AddStudent {

    private AdministartionBlockTitle mAdministartionBlockTitle;

    private VBox mainStudentSignupVBox;
    private GridPane signUpViewGridPane;
    private HBox buttonsHBox;
    private ScrollPane mScrollPane;
    private Label addAdminLabel;

    private Button saveBtn;
    private Button cancelBtn;

    private Label fileNumberLabel;
    private Label firstNameLabel;
    private Label middleNameLabel;
    private Label lastNameLabel;
    private Label dateOfBirthLabel;
    private Label dateOfAdmissionLabel;

    private TextField fileNumberTextField;
    private TextField firstNameTextField;
    private TextField middleNameTextField;
    private TextField lastNameTextField;
    private DatePicker dateOfBirthDatePicker;
    private DatePicker dateOfAdmissionDatePicker;

    public AddStudent() {
        mAdministartionBlockTitle = new AdministartionBlockTitle();

        mainStudentSignupVBox = new VBox();
        signUpViewGridPane = new GridPane();
        buttonsHBox = new HBox();
        mScrollPane = new ScrollPane();
        addAdminLabel = new Label("ADD STUDENT");

        saveBtn = new Button("Save");
        cancelBtn = new Button("Cancel");
        buttonsHBox.getChildren().addAll(saveBtn, cancelBtn);

        fileNumberLabel = new Label("Id Number");
        firstNameLabel = new Label("First Name");
        middleNameLabel = new Label("Middle Name");
        lastNameLabel = new Label("Last Name");
        dateOfBirthLabel = new Label("Date of Birth");
        dateOfAdmissionLabel = new Label("Date of Start");

        fileNumberTextField = new TextField();
        firstNameTextField = new TextField();
        middleNameTextField = new TextField();
        lastNameTextField = new TextField();
        dateOfBirthDatePicker = new DatePicker();
        dateOfAdmissionDatePicker = new DatePicker();

        signUpViewGridPane.add(fileNumberLabel, 0, 0);
        signUpViewGridPane.add(fileNumberTextField, 1, 0);
        signUpViewGridPane.add(firstNameLabel, 0, 1);
        signUpViewGridPane.add(firstNameTextField, 1, 1);
        signUpViewGridPane.add(middleNameLabel, 0, 2);
        signUpViewGridPane.add(middleNameTextField, 1, 2);
        signUpViewGridPane.add(lastNameLabel, 0, 3);
        signUpViewGridPane.add(lastNameTextField, 1, 3);
        signUpViewGridPane.add(dateOfBirthLabel, 0, 4);
        signUpViewGridPane.add(dateOfBirthDatePicker, 1, 4);
        signUpViewGridPane.add(dateOfAdmissionLabel, 0, 5);
        signUpViewGridPane.add(dateOfAdmissionDatePicker, 1, 5);

        mScrollPane.setContent(signUpViewGridPane);

        mainStudentSignupVBox.getChildren().addAll(mAdministartionBlockTitle
                        .getTopAdminBarBorderPane(), addAdminLabel,
                mScrollPane, buttonsHBox);

        beautifyView();
        setButtonsActions();
    }

    public VBox getMainStudentSignupVBox() {
        return mainStudentSignupVBox;
    }

    public AdministartionBlockTitle getAdministartionBlockTitle() {
        return mAdministartionBlockTitle;
    }

    private void beautifyView() {
        mainStudentSignupVBox.getStyleClass().addAll("mainViews");
        mainStudentSignupVBox.setAlignment(Pos.TOP_CENTER);
        mainStudentSignupVBox.setSpacing(10.0);

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

    private void addStudent() {
        int fileNumber = 0;
        String firstName = "";
        String middleName = "";
        String lastName = "";
        LocalDate dateOfBirthLD = null;
        LocalDate dateOfStartLD = null;
        try {
            fileNumber = Integer.valueOf(fileNumberTextField.getText().trim());
            firstName = firstNameTextField.getText().trim();
            middleName = middleNameTextField.getText().trim();
            lastName = lastNameTextField.getText().trim();


            dateOfBirthLD = dateOfBirthDatePicker.getValue();
            GregorianCalendar dobGregorianCalendar = new GregorianCalendar(dateOfBirthLD.getYear(),
                    dateOfBirthLD.getMonthValue() - 1, dateOfBirthLD.getDayOfMonth());
            Date dateOfBirth = dobGregorianCalendar.getTime();

            dateOfStartLD = dateOfAdmissionDatePicker.getValue();
            GregorianCalendar doSGregorianCalendar = new GregorianCalendar(dateOfStartLD.getYear(),
                    dateOfStartLD.getMonthValue() - 1, dateOfStartLD.getDayOfMonth());
            Date dateOfStart = doSGregorianCalendar.getTime();

            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.getButtonTypes().removeAll(confirmAlert.getButtonTypes());
            confirmAlert.getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
            confirmAlert.setContentText("Save Student: \n" +
                    "File Number: " + fileNumber + "\n" +
                    "First Name: " + firstName + "\n" +
                    "Middle Name: " + middleName + "\n" +
                    "Last Name: " + lastName + "\n" +
                    "??"
            );
            Optional<ButtonType> results = confirmAlert.showAndWait();
            if (results.get() == ButtonType.YES) {
                try {
                    StudentController.createStudent(fileNumber, firstName, middleName, lastName, dateOfBirth, dateOfStart);
                    StudentUtilities.getAllStudents();
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
        successAlert.setContentText("Successfully Saved Student: \n" +
                "File Number: " + fileNumber + "\n" +
                "First Name: " + firstName + "\n" +
                "Middle Name: " + middleName + "\n" +
                "Last Name: " + lastName + "\n"

        );
        successAlert.showAndWait();
        clear();
    }

    private void setButtonsActions() {
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (isValid()) {
                    addStudent();
                    MainApp.allViewsAdminStudent.getAllStudents().addDataToTable();
                    MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminStudent.getAllStudents().getViewStudentsVBox());
                } else {

                }
            }
        });
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clear();
                MainApp.allViewsAdminStudent.getAllStudents().addDataToTable();
                MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminStudent.getAllStudents().getViewStudentsVBox());
            }
        });
    }

    private boolean isValid() {
        String reqErrMessage = "";
        String validityErrMess = "";
        boolean valid = true;

        if (fileNumberTextField.getText().isEmpty()) {
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
        if (dateOfBirthDatePicker.getValue() == null) {
            reqErrMessage += "Date Of Birth field is required\n";
            valid = false;
        }
        if (dateOfAdmissionDatePicker.getValue() == null) {
            reqErrMessage += "Date Of Start field is required\n";
            valid = false;
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
        fileNumberTextField.clear();
        firstNameTextField.clear();
        middleNameTextField.clear();
        lastNameTextField.clear();
        dateOfBirthDatePicker.setValue(null);
        dateOfAdmissionDatePicker.setValue(null);
    }

}
