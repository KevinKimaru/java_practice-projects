package com.masomohigh.view.admin.teacher;

import com.masomohigh.controller.TeacherController;
import com.masomohigh.model.Teacher;
import com.masomohigh.view.MainApp;
import com.masomohigh.view.admin.AdministartionBlockTitle;
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
 * Created by Kevin Kimaru Chege on 1/3/2018.
 */
public class TeacherDetails {
    private AdministartionBlockTitle administartionBlockTitle;
    private Label viewTeacherDetailsLabel;

    private VBox viewTeacherDetailsVBox;
    private GridPane teacherDetailsGridPane;
    private HBox buttonsHBox;
    private ScrollPane mScrollPane;

    private Button backBtn;
    private Button updateBtn;
    private Button deleteTeacherBtn;

    private Label idNumberLabel;
    private Label firstNameLabel;
    private Label middleNameLabel;
    private Label lastNameLabel;
    private Label phoneNumberLabel;
    private Label addressLabel;
    private ListView<String> subjectsListView;
    private ListView<String> classesTeachingListView;
    private ListView<String> classesTeachingBackupListView;
    private ListView<String> obligationsListView;
    private ListView<String> obligationsBackupListView;
    private ListView<String> achievementsListListView;
    private Label statusLabel;
    private Label emailLabel;
    private Label dateOfBirthLabel;
    private Label dateOfStartLabel;

    private Label idNumberTitleLabel;
    private Label firstNameTitleLabel;
    private Label middleNameTitleLabel;
    private Label lastNameTitleLabel;
    private Label phoneNumberTitleLabel;
    private Label addressTitleLabel;
    private Label subjectsTitleLabel;
    private Label classesTeachingTitleLabel;
    private Label classesTeachingBackupTitleLabel;
    private Label obligationsTitleLabel;
    private Label obligationsBackupTitleLabel;
    private Label achievementsListTitleLabel;
    private Label statusTitleLabel;
    private Label emailTitleLabel;
    private Label dateOfBirthTitleLable;
    private Label dateOfStartTitleLable;

    public TeacherDetails() {
        viewTeacherDetailsLabel = new Label("VIEW TEACHER DETAILS");

        viewTeacherDetailsVBox = new VBox();
        teacherDetailsGridPane = new GridPane();
        buttonsHBox = new HBox();
        mScrollPane = new ScrollPane();

        backBtn = new Button("Ok");
        updateBtn = new Button("Update");
        deleteTeacherBtn = new Button("Delete Teacher");

        buttonsHBox.getChildren().addAll(backBtn, updateBtn, deleteTeacherBtn);

        idNumberLabel = new Label();
        firstNameLabel = new Label();
        middleNameLabel = new Label();
        lastNameLabel = new Label();
        phoneNumberLabel = new Label();
        addressLabel = new Label();
        statusLabel = new Label();
        emailLabel = new Label();
        dateOfBirthLabel = new Label();
        dateOfStartLabel = new Label();
        obligationsListView = new ListView<String>();
        obligationsBackupListView = new ListView<String>();
        achievementsListListView = new ListView<String>();
        subjectsListView = new ListView<>();
        classesTeachingListView = new ListView<>();
        classesTeachingBackupListView = new ListView<>();

        idNumberTitleLabel = new Label("Id Number");
        firstNameTitleLabel = new Label("First Name");
        middleNameTitleLabel = new Label("Middle Name");
        lastNameTitleLabel = new Label("Last Name");
        phoneNumberTitleLabel = new Label("Phone Number");
        addressTitleLabel = new Label("Address");
        obligationsTitleLabel = new Label("Current Obligations");
        obligationsBackupTitleLabel = new Label("All Obligations Ever");
        achievementsListTitleLabel = new Label("Achievements");
        statusTitleLabel = new Label("Status");
        emailTitleLabel = new Label("Email");
        dateOfBirthTitleLable = new Label("Date Of Birth");
        dateOfStartTitleLable = new Label("Date Of Start");
        subjectsTitleLabel = new Label("Subjects");
        classesTeachingTitleLabel = new Label("Classes Teaching");
        classesTeachingBackupTitleLabel = new Label("Classes Ever Taught");

        teacherDetailsGridPane.add(idNumberTitleLabel, 0, 0);
        teacherDetailsGridPane.add(idNumberLabel, 1, 0);
        teacherDetailsGridPane.add(firstNameTitleLabel, 0, 1);
        teacherDetailsGridPane.add(firstNameLabel, 1, 1);
        teacherDetailsGridPane.add(middleNameTitleLabel, 0, 2);
        teacherDetailsGridPane.add(middleNameLabel, 1, 2);
        teacherDetailsGridPane.add(lastNameTitleLabel, 0, 3);
        teacherDetailsGridPane.add(lastNameLabel, 1, 3);
        teacherDetailsGridPane.add(phoneNumberTitleLabel, 0, 4);
        teacherDetailsGridPane.add(phoneNumberLabel, 1, 4);
        teacherDetailsGridPane.add(addressTitleLabel, 0, 5);
        teacherDetailsGridPane.add(addressLabel, 1, 5);
        teacherDetailsGridPane.add(emailTitleLabel, 0, 6);
        teacherDetailsGridPane.add(emailLabel, 1, 6);
        teacherDetailsGridPane.add(dateOfBirthTitleLable, 0, 7);
        teacherDetailsGridPane.add(dateOfBirthLabel, 1, 7);
        teacherDetailsGridPane.add(dateOfStartTitleLable, 0, 8);
        teacherDetailsGridPane.add(dateOfStartLabel, 1, 8);
        teacherDetailsGridPane.add(subjectsTitleLabel, 0, 9);
        teacherDetailsGridPane.add(subjectsListView, 1, 9);
        teacherDetailsGridPane.add(classesTeachingTitleLabel, 0, 10);
        teacherDetailsGridPane.add(classesTeachingListView, 1, 10);
        teacherDetailsGridPane.add(classesTeachingBackupTitleLabel, 0, 11);
        teacherDetailsGridPane.add(classesTeachingBackupListView, 1, 11);
        teacherDetailsGridPane.add(obligationsTitleLabel, 0, 12);
        teacherDetailsGridPane.add(obligationsListView, 1, 12);
        teacherDetailsGridPane.add(obligationsBackupTitleLabel, 0, 13);
        teacherDetailsGridPane.add(obligationsBackupListView, 1, 13);
        teacherDetailsGridPane.add(achievementsListTitleLabel, 0, 14);
        teacherDetailsGridPane.add(achievementsListListView, 1, 14);
        teacherDetailsGridPane.add(statusTitleLabel, 0, 15);
        teacherDetailsGridPane.add(statusLabel, 1, 15);

        mScrollPane.setContent(teacherDetailsGridPane);

        administartionBlockTitle = new AdministartionBlockTitle();
        viewTeacherDetailsVBox.getChildren().addAll(administartionBlockTitle
                        .getTopAdminBarBorderPane(), viewTeacherDetailsLabel, mScrollPane,
                buttonsHBox);
        populateView();
        setBtnActions();
        beautifyTeacherDetails();
    }

    public VBox getViewTeacherDetailsVBox() {
        return viewTeacherDetailsVBox;
    }

    public AdministartionBlockTitle getAdministartionBlockTitle() {
        return administartionBlockTitle;
    }

    public void populateView() {
        try {
            Teacher teacher = TeacherUtilities.SELECTED_TEACHER;
            idNumberLabel.setText(String.valueOf(teacher.getIdNumber()));
            firstNameLabel.setText(teacher.getFirstName());
            middleNameLabel.setText(teacher.getMiddleName());
            lastNameLabel.setText(teacher.getLastName());
            phoneNumberLabel.setText(teacher.getPhoneNumber());
            addressLabel.setText("P.O. Box " + teacher.getAddress().getBaltex() + "-" +
                    teacher.getAddress().getPostalCode() + " " + teacher.getAddress().getCity());
            statusLabel.setText(teacher.getStatus());
            emailLabel.setText(teacher.getEmail());
            dateOfBirthLabel.setText(teacher.getDateOfBirth().toString());
            dateOfStartLabel.setText(teacher.getDateOfStart().toString());

            ObservableList<String> obligationItems = FXCollections.observableArrayList();
            ObservableList<String> obligationBackupItems = FXCollections.observableArrayList();
            ObservableList<String> achievementItems = FXCollections.observableArrayList();
            ObservableList<String> subjectItems = FXCollections.observableArrayList();
            ObservableList<String> classesTeachingItems = FXCollections.observableArrayList();
            ObservableList<String> classesTeachingBackupItems = FXCollections.observableArrayList();

            try {
                obligationItems.addAll(teacher.getObligations());
            } catch (Exception e) {

            }
            try {
                obligationBackupItems.addAll(teacher.getObligationsBackup());
            } catch (Exception e) {

            }
            try {
                achievementItems.addAll(teacher.getAchievements());
            } catch (Exception e) {

            }
            try {
                subjectItems.addAll(teacher.getSubjects());
            } catch(Exception e) {

            }
            try {
                classesTeachingItems.addAll(teacher.getClassesTeaching());
            } catch(Exception e) {

            }
            try {
                classesTeachingBackupItems.addAll(teacher.getClassesTeachingBackup());
            } catch(Exception e) {

            }

            obligationsListView.setItems(obligationItems);
            obligationsBackupListView.setItems(obligationBackupItems);
            achievementsListListView.setItems(achievementItems);
            subjectsListView.setItems(subjectItems);
            classesTeachingListView.setItems(classesTeachingItems);
            classesTeachingBackupListView.setItems(classesTeachingBackupItems);
        } catch (Exception e) {

        }
    }

    private void setBtnActions() {
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                MainApp.allViewsAdminTeacher.getAllTeachers().addDataToTable();
                MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminTeacher.getAllTeachers().getViewTeachersVBox());
            }
        });
        updateBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                MainApp.allViewsAdminTeacher.getUpdateTeacher().populateTextFields();
                MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminTeacher.getUpdateTeacher().
                        getMainTeacherUpdateVBox());
            }
        });
        deleteTeacherBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
                deleteAlert.getButtonTypes().removeAll(deleteAlert.getButtonTypes());
                deleteAlert.getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
                deleteAlert.setContentText("Are you sure you want to delete this teacher?\n" +
                        "Perhaps consider changing status of the teacher");
                Optional<ButtonType> results = deleteAlert.showAndWait();
                if (results.get() == ButtonType.YES) {
                    try {
                        if (TeacherController.deleteTeacher(TeacherUtilities.SELECTED_TEACHER_ID)) {
                            MainApp.allViewsAdminTeacher.getAllTeachers().addDataToTable();
                            MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminTeacher.getAllTeachers()
                                    .getViewTeachersVBox());
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Delete failed. This teacher does not exist.");
                            alert.showAndWait();
                        }
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
        teacherDetailsGridPane.getStyleClass().addAll("defaultBackground", "defaultFont", "font2",
                "nodePadding1", "childrenSpacing2");
        teacherDetailsGridPane.setHgap(10.0);
        teacherDetailsGridPane.setVgap(10.0);
        mScrollPane.setFitToWidth(true);

        viewTeacherDetailsVBox.getStyleClass().addAll("mainViews");
        viewTeacherDetailsVBox.setSpacing(10.0);
        viewTeacherDetailsVBox.setAlignment(Pos.TOP_CENTER);

        backBtn.getStyleClass().add("btnInfo");
        updateBtn.getStyleClass().add("btnInfo");
        deleteTeacherBtn.getStyleClass().add("btnDanger");

        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(20.0);

        viewTeacherDetailsLabel.getStyleClass().addAll("title2");
    }
}
