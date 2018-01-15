package com.masomohigh.view.admin.teacher;

import com.masomohigh.controller.AdminController;
import com.masomohigh.controller.TeacherController;
import com.masomohigh.model.Administrator;
import com.masomohigh.model.Teacher;
import com.masomohigh.view.AdminUtilities;
import com.masomohigh.view.MainApp;
import com.masomohigh.view.admin.AdministartionBlockTitle;
import com.masomohigh.view.admin.administrator.UpdateAdminMore;
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
public class UpdateTeacherAchievements {

    private AdministartionBlockTitle administartionBlockTitle;
    private Label updateTeacherAchievLabel;

    private VBox updateTeacherAchievVBox;

    private GridPane updateTeacherAchievGridPane;

    private ListView<String> obligationsListView;
    private ListView<String> obligationsBackupListView;
    private ListView<String> achievementsListView;

    private Label obligationsLabel;
    private Label obligationsBackupLabel;
    private Label achievementsLabel;

    private Button addObligationButton;
    private ToggleButton updateObligationButton;
    private Button removeObligationButton;
    private TextField addObligationTextField;
    private Button addObligationTextFieldButton;

    private Button addAchievementButton;
    private ToggleButton updateAchievementButton;
    private Button removeAchievementButton;
    private TextField addAchievementTextField;
    private Button addAchievementTextFieldButton;

    private HBox addObligationHBox;
    private HBox addAchievementHBox;

    private Button doneButton;

    ObservableList<String> obligationsList;
    ObservableList<String> obligationsBackupList;
    ObservableList<String> achievementsList;

    public UpdateTeacherAchievements() {
        updateTeacherAchievVBox = new VBox();

        updateTeacherAchievGridPane = new GridPane();

        updateTeacherAchievLabel = new Label("Update Teacher Achievements and Obligations");

        doneButton = new Button("Done");

        addObligationHBox = new HBox();
        addAchievementHBox = new HBox();

        obligationsListView = new ListView<String>();
        obligationsBackupListView = new ListView<String>();
        achievementsListView = new ListView<String>();

        obligationsLabel = new Label("Obligations");
        obligationsBackupLabel = new Label("All Obligations Ever");
        achievementsLabel = new Label("Achievements");

        addObligationButton = new Button("Add");
        updateObligationButton = new ToggleButton("Update");
        removeObligationButton = new Button("Remove");
        addObligationTextField = new TextField();
        addObligationTextFieldButton = new Button("Ok");
        addObligationHBox.getChildren().addAll(addObligationTextField, addObligationTextFieldButton);
        addObligationHBox.setVisible(false);

        addAchievementButton = new Button("Add");
        updateAchievementButton = new ToggleButton("Update");
        removeAchievementButton = new Button("Remove");
        addAchievementTextField = new TextField();
        addAchievementTextFieldButton = new Button("Ok");
        addAchievementHBox.getChildren().addAll(addAchievementTextField, addAchievementTextFieldButton);
        addAchievementHBox.setVisible(false);

        updateTeacherAchievGridPane.add(obligationsLabel, 0, 0);
        updateTeacherAchievGridPane.add(obligationsBackupLabel, 1, 0);
        updateTeacherAchievGridPane.add(achievementsLabel, 2, 0);
        updateTeacherAchievGridPane.add(obligationsListView, 0, 1);
        updateTeacherAchievGridPane.add(obligationsBackupListView, 1, 1);
        updateTeacherAchievGridPane.add(achievementsListView, 2, 1);
        updateTeacherAchievGridPane.add(addObligationButton, 0, 2);
        updateTeacherAchievGridPane.add(addAchievementButton, 2, 2);
        updateTeacherAchievGridPane.add(updateObligationButton, 0, 3);
        updateTeacherAchievGridPane.add(updateAchievementButton, 2, 3);
        updateTeacherAchievGridPane.add(removeObligationButton, 0, 4);
        updateTeacherAchievGridPane.add(removeAchievementButton, 2, 4);
        updateTeacherAchievGridPane.add(addObligationHBox, 0, 5);
        updateTeacherAchievGridPane.add(addAchievementHBox, 2, 5);

        administartionBlockTitle = new AdministartionBlockTitle();
        updateTeacherAchievVBox.getChildren().addAll(administartionBlockTitle
                        .getTopAdminBarBorderPane(), updateTeacherAchievLabel, updateTeacherAchievGridPane,
                doneButton);

        beautifyView();
        setActionsForButtons();
    }

    public VBox getUpdateTeacherAchievVBox() {
        return updateTeacherAchievVBox;
    }

    public AdministartionBlockTitle getAdministartionBlockTitle() {
        return administartionBlockTitle;
    }

    private void setActionsForButtons() {

        addObligationButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                addObligationHBox.setVisible(true);
            }
        });

        updateObligationButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue == true) {
                    obligationsListView.setEditable(true);
                    updateObligationButton.setDisable(true);
                } else {
                    obligationsListView.setEditable(false);
                }
            }
        });

        removeObligationButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
                deleteAlert.getButtonTypes().removeAll(deleteAlert.getButtonTypes());
                deleteAlert.getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
                deleteAlert.setContentText("Are you sure you want to delete this Obligation?\n");
                Optional<ButtonType> results = deleteAlert.showAndWait();
                if (results.get() == ButtonType.YES) {
                    if (obligationsListView.getSelectionModel().getSelectedIndex() != -1) {
                        TeacherController.removeTeacherObligation(TeacherUtilities.SELECTED_TEACHER_ID,
                                obligationsListView.getSelectionModel().getSelectedIndex());
                        populateListViews();
                    }
                } else {

                }
            }
        });


        addAchievementButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                addAchievementHBox.setVisible(true);
            }
        });

        updateAchievementButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue == true) {
                    achievementsListView.setEditable(true);
                    updateAchievementButton.setDisable(true);
                } else {
                    achievementsListView.setEditable(false);
                }
            }
        });

        removeAchievementButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
                deleteAlert.getButtonTypes().removeAll(deleteAlert.getButtonTypes());
                deleteAlert.getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
                deleteAlert.setContentText("Are you sure you want to delete this achievement?\n");
                Optional<ButtonType> results = deleteAlert.showAndWait();
                if (results.get() == ButtonType.YES) {
                    TeacherController.removeTeacherAchievement(TeacherUtilities.SELECTED_TEACHER_ID,
                            achievementsListView.getSelectionModel().getSelectedIndex());
                    populateListViews();
                } else {

                }
            }
        });


        addObligationTextFieldButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (!addObligationTextField.getText().isEmpty()) {
                    TeacherController.addTeacherObligation(TeacherUtilities.SELECTED_TEACHER_ID,
                            addObligationTextField.getText().trim());
                    addObligationTextField.clear();
                    addObligationHBox.setVisible(false);
                    populateListViews();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Please input obligation");
                    alert.showAndWait();
                }
            }
        });
        addAchievementTextFieldButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (!addAchievementTextField.getText().isEmpty()) {
                    TeacherController.addTeacherAchievements(TeacherUtilities.SELECTED_TEACHER_ID,
                            addAchievementTextField.getText().trim());
                    addAchievementTextField.clear();
                    addAchievementHBox.setVisible(false);
                    populateListViews();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Please input achievement");
                    alert.showAndWait();
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
        updateTeacherAchievVBox.getStyleClass().addAll("mainViews");
        updateTeacherAchievVBox.setSpacing(10.0);
        updateTeacherAchievVBox.setAlignment(Pos.TOP_CENTER);

        updateTeacherAchievGridPane.getStyleClass().addAll("mainViews", "defaultBackground", "defaultFont", "font2",
                "nodePadding1", "childrenSpacing2");
        updateTeacherAchievGridPane.setHgap(15.0);
        updateTeacherAchievGridPane.setVgap(15.0);

        addObligationButton.getStyleClass().add("btnInfo2");
        removeObligationButton.getStyleClass().add("btnDanger2");
        addAchievementButton.getStyleClass().add("btnInfo2");
        removeAchievementButton.getStyleClass().add("btnDanger2");

        obligationsLabel.getStyleClass().addAll("title4");
        achievementsLabel.getStyleClass().addAll("title4");
        obligationsBackupLabel.getStyleClass().addAll("title4");

        doneButton.getStyleClass().add("btnInfo");

        updateTeacherAchievLabel.getStyleClass().add("title2");
    }

    protected void populateListViews() {
        TeacherUtilities.setSelectedTeacher();
        TeacherUtilities.getAllTeachers();

        obligationsList = FXCollections.observableArrayList();
        obligationsBackupList = FXCollections.observableArrayList();
        achievementsList = FXCollections.observableArrayList();

        Teacher teacher = TeacherUtilities.SELECTED_TEACHER;

        try {
            obligationsList.addAll(teacher.getObligations());
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            obligationsBackupList.addAll(teacher.getObligationsBackup());
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            achievementsList.addAll(teacher.getAchievements());
        } catch (Exception e) {
            System.out.println(e);
        }

        obligationsListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            public ListCell<String> call(ListView<String> param) {
                return new UpdateTeacherAchievements.ObligationsListCellImpl(new StringConverter() {
                    @Override
                    public String toString(Object object) {
                        return object.toString();
                    }

                    @Override
                    public Object fromString(String string) {
                        return string;
                    }
                });
            }
        });
        achievementsListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            public ListCell<String> call(ListView<String> param) {
                return new UpdateTeacherAchievements.AchievementsListCellImpl(new StringConverter() {
                    @Override
                    public String toString(Object object) {
                        return object.toString();
                    }

                    @Override
                    public Object fromString(String string) {
                        return string;
                    }
                });
            }
        });

        obligationsListView.setItems(obligationsList);
        obligationsBackupListView.setItems(obligationsBackupList);
        achievementsListView.setItems(achievementsList);
    }

    private class ObligationsListCellImpl extends TextFieldListCell {
        public ObligationsListCellImpl(StringConverter converter) {
            super(converter);
        }

        @Override
        public void commitEdit(Object newValue) {
            super.commitEdit(newValue);
            TeacherController.updateTeacherObligation(TeacherUtilities.SELECTED_TEACHER_ID,
                    this.getIndex(), TeacherUtilities.SELECTED_TEACHER.getObligations().get(this.getIndex()),
                    (String) newValue);
            populateListViews();
            updateObligationButton.setDisable(false);
            obligationsListView.setEditable(false);
        }
    }

    private class AchievementsListCellImpl extends TextFieldListCell {
        public AchievementsListCellImpl(StringConverter converter) {
            super(converter);
        }

        @Override
        public void commitEdit(Object newValue) {
            super.commitEdit(newValue);
            TeacherController.updateTeacherAchievement(TeacherUtilities.SELECTED_TEACHER_ID,
                    this.getIndex(), (String) newValue);
            populateListViews();
            updateAchievementButton.setDisable(false);
            achievementsListView.setEditable(false);
        }
    }
}
