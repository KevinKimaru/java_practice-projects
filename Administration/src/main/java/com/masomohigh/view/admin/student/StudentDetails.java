package com.masomohigh.view.admin.student;

import com.masomohigh.controller.TeacherController;
import com.masomohigh.model.Class;
import com.masomohigh.model.Student;
import com.masomohigh.model.Teacher;
import com.masomohigh.view.MainApp;
import com.masomohigh.view.admin.AdministartionBlockTitle;
import com.masomohigh.view.admin.teacher.TeacherUtilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Optional;

/**
 * Created by Kevin Kimaru Chege on 2/3/2018.
 */
public class StudentDetails {

    private AdministartionBlockTitle mAdministartionBlockTitle;

    private Label viewStudentDetailsLabel;

    private VBox viewStudentsDetailsVBox;
    private GridPane studentDetailsGridPane;
    private HBox buttonsHBox;
    private ScrollPane mScrollPane;

    private Button backBtn;
    private Button updateBtn;
    private Button deleteStudentBtn;

    private Label fileNumberLabel;
    private Label firstNameLabel;
    private Label middleNameLabel;
    private Label lastNameLabel;
    private Label formLabel;
    private Label streamLabel;
    private ListView<String> subjectsListView;
    private ListView<String> classesListView;
    private Label houseLabel;
    private Label statusLabel;
    private Label guardianNameLabel;
    private Label dateOfBirthLabel;
    private Label dateOfAdmissionLabel;

    private Label fileNumberTitleLabel;
    private Label firstNameTitleLabel;
    private Label middleNameTitleLabel;
    private Label lastNameTitleLabel;
    private Label formTitleLabel;
    private Label streamTitleLabel;
    private Label subjectsTitleLabel;
    private Label classesTitleLabel;
    private Label houseTitleLabel;
    private Label statusTitleLabel;
    private Label guardianNameTitleLabel;
    private Label dateOfBirthTitleLable;
    private Label dateOfAdmissionTitleLabel;

    public StudentDetails() {
        mAdministartionBlockTitle = new AdministartionBlockTitle();

        viewStudentDetailsLabel = new Label("STUDENT DETAILS");

        viewStudentsDetailsVBox = new VBox();
        studentDetailsGridPane = new GridPane();
        buttonsHBox = new HBox();
        mScrollPane = new ScrollPane();

        backBtn = new Button("Ok");
        updateBtn = new Button("Update");
        deleteStudentBtn = new Button("Delete Student");

        buttonsHBox.getChildren().addAll(backBtn, updateBtn, deleteStudentBtn);

        fileNumberLabel = new Label();
        firstNameLabel = new Label();
        middleNameLabel = new Label();
        lastNameLabel = new Label();
        formLabel = new Label();
        streamLabel = new Label();
        statusLabel = new Label();
        guardianNameLabel = new Label();
        dateOfBirthLabel = new Label();
        dateOfAdmissionLabel = new Label();
        houseLabel = new Label();
        subjectsListView = new ListView<>();
        classesListView = new ListView<>();

        fileNumberTitleLabel = new Label("File Number");
        firstNameTitleLabel = new Label("First Name");
        middleNameTitleLabel = new Label("Middle Name");
        lastNameTitleLabel = new Label("Last Name");
        formTitleLabel = new Label("Form");
        streamTitleLabel = new Label("Stream");
        houseTitleLabel = new Label("House");
        statusTitleLabel = new Label("Status");
        guardianNameTitleLabel = new Label("Guardian Name");
        dateOfBirthTitleLable = new Label("Date Of Birth");
        dateOfAdmissionTitleLabel = new Label("Date Of Admission");
        subjectsTitleLabel = new Label("Subjects");
        classesTitleLabel = new Label("Classes");

        studentDetailsGridPane.add(fileNumberTitleLabel, 0, 0);
        studentDetailsGridPane.add(fileNumberLabel, 1, 0);
        studentDetailsGridPane.add(firstNameTitleLabel, 0, 1);
        studentDetailsGridPane.add(firstNameLabel, 1, 1);
        studentDetailsGridPane.add(middleNameTitleLabel, 0, 2);
        studentDetailsGridPane.add(middleNameLabel, 1, 2);
        studentDetailsGridPane.add(lastNameTitleLabel, 0, 3);
        studentDetailsGridPane.add(lastNameLabel, 1, 3);
        studentDetailsGridPane.add(formTitleLabel, 0, 4);
        studentDetailsGridPane.add(formLabel, 1, 4);
        studentDetailsGridPane.add(streamTitleLabel, 0, 5);
        studentDetailsGridPane.add(streamLabel, 1, 5);
        studentDetailsGridPane.add(guardianNameTitleLabel, 0, 6);
        studentDetailsGridPane.add(guardianNameLabel, 1, 6);
        studentDetailsGridPane.add(dateOfBirthTitleLable, 0, 7);
        studentDetailsGridPane.add(dateOfBirthLabel, 1, 7);
        studentDetailsGridPane.add(dateOfAdmissionTitleLabel, 0, 8);
        studentDetailsGridPane.add(dateOfAdmissionLabel, 1, 8);
        studentDetailsGridPane.add(subjectsTitleLabel, 0, 9);
        studentDetailsGridPane.add(subjectsListView, 1, 9);
        studentDetailsGridPane.add(classesTitleLabel, 0, 10);
        studentDetailsGridPane.add(classesListView, 1, 10);
        studentDetailsGridPane.add(houseTitleLabel, 0, 11);
        studentDetailsGridPane.add(houseLabel, 1, 11);
        studentDetailsGridPane.add(statusTitleLabel, 0, 12);
        studentDetailsGridPane.add(statusLabel, 1, 12);

        mScrollPane.setContent(studentDetailsGridPane);

        viewStudentsDetailsVBox.getChildren().addAll(mAdministartionBlockTitle
                        .getTopAdminBarBorderPane(), viewStudentDetailsLabel, mScrollPane,
                buttonsHBox);
        populateView();
        setBtnActions();
        beautifyTeacherDetails();
    }

    public AdministartionBlockTitle getAdministartionBlockTitle() {
        return mAdministartionBlockTitle;
    }

    public VBox getViewStudentsDetailsVBox() {
        return viewStudentsDetailsVBox;
    }

    public void populateView() {
        try {
            Student student = StudentUtilities.SELECTED_STUDENT;
            fileNumberLabel.setText(String.valueOf(student.getFileNumber()));
            firstNameLabel.setText(student.getFirstName());
            middleNameLabel.setText(student.getMiddleName());
            lastNameLabel.setText(student.getLastName());
            formLabel.setText(String.valueOf(student.getForm()));
            streamLabel.setText(student.getStream());
            statusLabel.setText(student.getStatus());
            dateOfBirthLabel.setText(student.getDateOfBirth().toString());
            dateOfAdmissionLabel.setText(student.getDateOfAdmission().toString());
            guardianNameLabel.setText(student.getGuardian().getFirstName() + " " +
                    student.getGuardian().getMiddleName() + " " +
                    student.getGuardian().getLastName());
            houseLabel.setText(student.getHouse().getName());

            ObservableList<String> subjectItems = FXCollections.observableArrayList();
            ObservableList<String> classesItems = FXCollections.observableArrayList();

            try {
                subjectItems.addAll(student.getSubjects());
            } catch(Exception e) {

            }
            try {
                for (Class aClass: student.getClasses()) {
                    classesItems.addAll(aClass.getName());
                }
            } catch(Exception e) {

            }

            subjectsListView.setItems(subjectItems);
            classesListView.setItems(classesItems);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setBtnActions() {
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                MainApp.allViewsAdminStudent.getAllStudents().addDataToTable();
                MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminStudent.getAllStudents().getViewStudentsVBox());
            }
        });
        updateBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
//                MainApp.allViewsAdminTeacher.getUpdateTeacher().populateTextFields();
//                MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminTeacher.getUpdateTeacher().
//                        getMainTeacherUpdateVBox());
            }
        });
        deleteStudentBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
                deleteAlert.getButtonTypes().removeAll(deleteAlert.getButtonTypes());
                deleteAlert.getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
                deleteAlert.setContentText("Are you sure you want to delete this student?\n" +
                        "Perhaps consider changing status of the student");
                Optional<ButtonType> results = deleteAlert.showAndWait();
                if (results.get() == ButtonType.YES) {
                    try {
//                        if (TeacherController.deleteTeacher(TeacherUtilities.SELECTED_TEACHER_ID)) {
//                            MainApp.allViewsAdminTeacher.getAllTeachers().addDataToTable();
//                            MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminTeacher.getAllTeachers()
//                                    .getViewTeachersVBox());
//                        } else {
//                            Alert alert = new Alert(Alert.AlertType.ERROR);
//                            alert.setContentText("Delete failed. This teacher does not exist.");
//                            alert.showAndWait();
//                        }
                    } catch(Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Delete failed: " + e.getMessage());
                        alert.showAndWait();
                    }
                } else {

                }
            }
        });
    }

    private void beautifyTeacherDetails() {
        studentDetailsGridPane.getStyleClass().addAll("defaultBackground", "defaultFont", "font2",
                "nodePadding1", "childrenSpacing2");
        studentDetailsGridPane.setHgap(10.0);
        studentDetailsGridPane.setVgap(10.0);
        mScrollPane.setFitToWidth(true);

        viewStudentsDetailsVBox.getStyleClass().addAll("mainViews");
        viewStudentsDetailsVBox.setSpacing(10.0);
        viewStudentsDetailsVBox.setAlignment(Pos.TOP_CENTER);

        backBtn.getStyleClass().add("btnInfo");
        updateBtn.getStyleClass().add("btnInfo");
        deleteStudentBtn.getStyleClass().add("btnDanger");

        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(20.0);

        viewStudentDetailsLabel.getStyleClass().addAll("title2");
    }
}
