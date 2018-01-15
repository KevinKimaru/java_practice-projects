package com.masomohigh.view.admin.teacher;

import com.masomohigh.controller.AdminController;
import com.masomohigh.controller.TeacherController;
import com.masomohigh.model.Administrator;
import com.masomohigh.model.Teacher;
import com.masomohigh.view.AdminUtilities;
import com.masomohigh.view.MainApp;
import com.masomohigh.view.admin.AdministartionBlockTitle;
import com.masomohigh.view.admin.administrator.UpdateAdminMore;
import com.masomohigh.view.admin.mclass.ClassUtilities;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.util.Optional;

/**
 * Created by Kevin Kimaru Chege on 1/4/2018.
 */
public class UpdateTeacherClasses {
    private AdministartionBlockTitle administartionBlockTitle;
    private Label updateTeacherClassLabel;

    private VBox updateTeacherClassVBox;

    private GridPane updateTeacherClassGridPane;

    private ListView<String> classesTeachingListView;
    private ListView<String> classesTaughtBackupListView;

    private Label classesTeachingLabel;
    private Label classesTeachingBackupLabel;

    private Button addClassesTeachingBtn;
    private Button updateClassesTeachingBtn;
    private Button deleteClassesTeachingBtn;
    private ComboBox<String> subjectComboBox;
    private ComboBox<String> classComboBox;
    private Button addClassSubjectBtn;

    private HBox addClassSubjectHBox;

    private Button doneButton;

    ObservableList<String> classesTeachingList;
    ObservableList<String> classesTeachingBackupList;

    public UpdateTeacherClasses() {
        updateTeacherClassVBox = new VBox();

        updateTeacherClassGridPane = new GridPane();

        updateTeacherClassLabel = new Label("UPDATE TEACHER'S CLASSES");

        doneButton = new Button("Done");

        addClassSubjectHBox = new HBox();

        classesTeachingListView = new ListView<String>();
        classesTaughtBackupListView = new ListView<String>();

        classesTeachingLabel = new Label("Classes Teaching");
        classesTeachingBackupLabel = new Label("All Classes Ever Taught");

        addClassesTeachingBtn = new Button("Add");
        updateClassesTeachingBtn = new Button("Update");
        deleteClassesTeachingBtn = new Button("Delete");
        subjectComboBox = new ComboBox<>();
        subjectComboBox.setItems(FXCollections.observableArrayList(MainApp.ALLSUBJECTS));
        classComboBox = new ComboBox<>();
        //TODO: ONCE A CLASS IS ADDED AVAILABLE CLASSES MUST BE UPDATED AS WELL AS ITS DEPENDENT CHECKBOXES
        //TODO: UPDATE OBLIGATIONS AND ALL ITS USAGES IN COMBOBOXES EG IN SEARCHING TEACHER
        classComboBox.setItems(FXCollections.observableArrayList(ClassUtilities.AVAILABLE_CLASSES));
        addClassSubjectBtn = new Button("Ok");
        addClassSubjectHBox.getChildren().addAll(subjectComboBox, classComboBox, addClassSubjectBtn);
        addClassSubjectHBox.setVisible(false);

        updateTeacherClassGridPane.add(classesTeachingLabel, 0, 0);
        updateTeacherClassGridPane.add(classesTeachingBackupLabel, 1, 0);
        updateTeacherClassGridPane.add(classesTeachingListView, 0, 1);
        updateTeacherClassGridPane.add(classesTaughtBackupListView, 1, 1);
        updateTeacherClassGridPane.add(addClassesTeachingBtn, 0, 2);
        updateTeacherClassGridPane.add(updateClassesTeachingBtn, 0, 3);
        updateTeacherClassGridPane.add(deleteClassesTeachingBtn, 0, 4);
        updateTeacherClassGridPane.add(addClassSubjectHBox, 0, 5);

        administartionBlockTitle = new AdministartionBlockTitle();
        updateTeacherClassVBox.getChildren().addAll(administartionBlockTitle
                        .getTopAdminBarBorderPane(), updateTeacherClassLabel, updateTeacherClassGridPane,
                doneButton);

        beautifyView();
        setActionsForButtons();
    }

    public VBox getUpdateTeacherClassVBox() {
        return updateTeacherClassVBox;
    }

    public AdministartionBlockTitle getAdministartionBlockTitle() {
        return administartionBlockTitle;
    }

    private void setActionsForButtons() {
        classesTeachingListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    String subject = newValue.substring(0, newValue.indexOf(": "));
                    String mclass = newValue.substring(newValue.indexOf(": ") + 2, newValue.length());
                    subjectComboBox.setValue(subject);
                    classComboBox.setValue(mclass);
                } catch(NullPointerException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        addClassesTeachingBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                updateClassesTeachingBtn.setDisable(false);
                subjectComboBox.setValue(null);
                classComboBox.setValue(null);
                addClassSubjectHBox.setVisible(true);
                addClassesTeachingBtn.setDisable(true);
            }
        });

        updateClassesTeachingBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addClassesTeachingBtn.setDisable(false);
                addClassSubjectHBox.setVisible(true);
                updateClassesTeachingBtn.setDisable(true);
            }
        });

        deleteClassesTeachingBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
                deleteAlert.getButtonTypes().removeAll(deleteAlert.getButtonTypes());
                deleteAlert.getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
                deleteAlert.setContentText("Are you sure you want to remove this class from the list?\n");
                Optional<ButtonType> results = deleteAlert.showAndWait();
                if (results.get() == ButtonType.YES) {
                    if (classesTeachingListView.getSelectionModel().getSelectedIndex() != -1) {
                        TeacherController.removeCurrentClassesTeaching(TeacherUtilities.SELECTED_TEACHER_ID,
                                classesTeachingListView.getSelectionModel().getSelectedIndex());
                        populateListViews();
                    }
                } else {

                }
            }
        });


        addClassSubjectBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (addClassesTeachingBtn.isDisabled()) {
                    if (!(subjectComboBox.getValue() == null) && !(classComboBox.getValue() == null)) {
                        String classTeaching = subjectComboBox.getValue() + ": " + classComboBox.getValue();
                        TeacherController.addCurrentClassesTeaching(TeacherUtilities.SELECTED_TEACHER_ID,
                                classTeaching);
                        subjectComboBox.setValue(null);
                        classComboBox.setValue(null);
                        addClassSubjectHBox.setVisible(false);
                        populateListViews();
                        addClassesTeachingBtn.setDisable(false);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Ensure both fields are filled");
                        alert.showAndWait();
                    }
                }
                if (updateClassesTeachingBtn.isDisabled()) {
                    if (classesTeachingListView.getSelectionModel().getSelectedIndices().size() > 0) {
                        if (!(subjectComboBox.getValue() == null) && !(classComboBox.getValue() == null)) {
                            String classTeaching = subjectComboBox.getValue() + ": " + classComboBox.getValue();
                            TeacherController.updateClassesTeaching(TeacherUtilities.SELECTED_TEACHER_ID,
                                    classesTeachingListView.getSelectionModel().getSelectedIndex(),
                                    TeacherUtilities.SELECTED_TEACHER.getClassesTeaching().get(
                                            classesTeachingListView.getSelectionModel().getSelectedIndex()
                                    ),
                                    classTeaching);
                            subjectComboBox.setValue(null);
                            classComboBox.setValue(null);
                            addClassSubjectHBox.setVisible(false);
                            populateListViews();
                            updateClassesTeachingBtn.setDisable(false);
                        } else {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setContentText("Ensure both fields are filled");
                            alert.showAndWait();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Please selct a field to update!");
                        alert.showAndWait();
                    }
                }
            }
        });

        doneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainApp.allViewsAdminTeacher.getTeacherDetails().populateView();
                MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminTeacher.getTeacherDetails()
                        .getViewTeacherDetailsVBox());
            }
        });
    }

    private void beautifyView() {
        updateTeacherClassVBox.getStyleClass().addAll("mainViews");
        updateTeacherClassVBox.setSpacing(10.0);
        updateTeacherClassVBox.setAlignment(Pos.TOP_CENTER);

        updateTeacherClassGridPane.getStyleClass().addAll("mainViews", "defaultBackground", "defaultFont", "font2",
                "nodePadding1", "childrenSpacing2");
        updateTeacherClassGridPane.setHgap(15.0);
        updateTeacherClassGridPane.setVgap(15.0);

        addClassesTeachingBtn.getStyleClass().add("btnInfo2");
        deleteClassesTeachingBtn.getStyleClass().add("btnDanger2");

        classesTeachingLabel.getStyleClass().addAll("title4");
        classesTeachingBackupLabel.getStyleClass().addAll("title4");

        doneButton.getStyleClass().add("btnInfo");

        updateTeacherClassLabel.getStyleClass().add("title2");
    }

    protected void populateListViews() {
        TeacherUtilities.setSelectedTeacher();
        TeacherUtilities.getAllTeachers();

        classesTeachingList = FXCollections.observableArrayList();
        classesTeachingBackupList = FXCollections.observableArrayList();

        Teacher teacher = TeacherUtilities.SELECTED_TEACHER;

        try {
            classesTeachingList.addAll(teacher.getClassesTeaching());
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            classesTeachingBackupList.addAll(teacher.getClassesTeachingBackup());
        } catch (Exception e) {
            System.out.println(e);
        }

        classesTeachingListView.setItems(classesTeachingList);
        classesTaughtBackupListView.setItems(classesTeachingBackupList);
    }

}
