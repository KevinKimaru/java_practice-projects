package com.masomohigh.view.admin.administrator;

import com.masomohigh.controller.AdminController;
import com.masomohigh.model.Administrator;
import com.masomohigh.view.MainApp;
import com.masomohigh.view.AdminUtilities;
import com.masomohigh.view.admin.AdministartionBlockTitle;
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
 * Created by Kevin Kimaru Chege on 12/26/2017.
 */
public class UpdateAdminMore {

    private AdministartionBlockTitle administartionBlockTitle;
    private Label updateAdminMoreLabel;

    private VBox updateAdminMoreVBox;

    private GridPane updateAdminMoreGridPane;

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

    public UpdateAdminMore() {
        updateAdminMoreVBox = new VBox();

        updateAdminMoreGridPane = new GridPane();

        updateAdminMoreLabel = new Label("UPDATE ADMIN");

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

        updateAdminMoreGridPane.add(obligationsLabel, 0, 0);
        updateAdminMoreGridPane.add(obligationsBackupLabel, 1, 0);
        updateAdminMoreGridPane.add(achievementsLabel, 2, 0);
        updateAdminMoreGridPane.add(obligationsListView, 0, 1);
        updateAdminMoreGridPane.add(obligationsBackupListView, 1, 1);
        updateAdminMoreGridPane.add(achievementsListView, 2, 1);
        updateAdminMoreGridPane.add(addObligationButton, 0, 2);
        updateAdminMoreGridPane.add(addAchievementButton, 2, 2);
        updateAdminMoreGridPane.add(updateObligationButton, 0, 3);
        updateAdminMoreGridPane.add(updateAchievementButton, 2, 3);
        updateAdminMoreGridPane.add(removeObligationButton, 0, 4);
        updateAdminMoreGridPane.add(removeAchievementButton, 2, 4);
        updateAdminMoreGridPane.add(addObligationHBox, 0, 5);
        updateAdminMoreGridPane.add(addAchievementHBox, 2, 5);

        administartionBlockTitle = new AdministartionBlockTitle();
        updateAdminMoreVBox.getChildren().addAll(administartionBlockTitle
                        .getTopAdminBarBorderPane(), updateAdminMoreLabel, updateAdminMoreGridPane,
                doneButton);

        beautifyView();
        setActionsForButtons();
    }

    public VBox getUpdateAdminMoreVBox() {
        return updateAdminMoreVBox;
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
                        AdminController.removeAdministratorsObligation(AdminUtilities.SELECTED_ADMINISTRATOR_ID,
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
                    AdminController.removeAdministratorAchievement(AdminUtilities.SELECTED_ADMINISTRATOR_ID,
                            achievementsListView.getSelectionModel().getSelectedIndex());
                    populateListViews();
                } else {

                }
            }
        });


        addObligationTextFieldButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (!addObligationTextField.getText().isEmpty()) {
                    AdminController.addAdministratorsObligation(AdminUtilities.SELECTED_ADMINISTRATOR_ID,
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
                    AdminController.addAdministratorAchievements(AdminUtilities.SELECTED_ADMINISTRATOR_ID,
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
                MainApp.allViewsAdminAdmin.getViewAdminDetails().populateView();
                MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminAdmin.getViewAdminDetails().getViewAdminDetailsVBox());
            }
        });
    }

    private void beautifyView() {
        updateAdminMoreVBox.getStyleClass().addAll("mainViews");
        updateAdminMoreVBox.setSpacing(10.0);
        updateAdminMoreVBox.setAlignment(Pos.TOP_CENTER);

        updateAdminMoreGridPane.getStyleClass().addAll("mainViews", "defaultBackground", "defaultFont", "font2",
                "nodePadding1", "childrenSpacing2");
        updateAdminMoreGridPane.setHgap(15.0);
        updateAdminMoreGridPane.setVgap(15.0);

        addObligationButton.getStyleClass().add("btnInfo2");
        removeObligationButton.getStyleClass().add("btnDanger2");
        addAchievementButton.getStyleClass().add("btnInfo2");
        removeAchievementButton.getStyleClass().add("btnDanger2");

        obligationsLabel.getStyleClass().addAll("title4");
        achievementsLabel.getStyleClass().addAll("title4");
        obligationsBackupLabel.getStyleClass().addAll("title4");

        doneButton.getStyleClass().add("btnInfo");

        updateAdminMoreLabel.getStyleClass().add("title2");
    }

    protected void populateListViews() {
        AdminUtilities.setSelectedAdministrator();
        AdminUtilities.getAllAdministrators();

        obligationsList = FXCollections.observableArrayList();
        obligationsBackupList = FXCollections.observableArrayList();
        achievementsList = FXCollections.observableArrayList();

        Administrator administrator = AdminUtilities.SELECTED_ADMINISTRATOR;

        try {
            obligationsList.addAll(administrator.getObligations());
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            obligationsBackupList.addAll(administrator.getObligationsBackup());
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            achievementsList.addAll(administrator.getAchievements());
        } catch (Exception e) {
            System.out.println(e);
        }

        obligationsListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            public ListCell<String> call(ListView<String> param) {
                return new ObligationsListCellImpl(new StringConverter() {
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
                return new AchievementsListCellImpl(new StringConverter() {
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
            AdminController.updateAdministratorObligation(AdminUtilities.SELECTED_ADMINISTRATOR_ID,
                    this.getIndex(),
                    AdminUtilities.SELECTED_ADMINISTRATOR.getObligations().get(this.getIndex()), (String) newValue);
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
            AdminController.updateAdministratorAchievement(AdminUtilities.SELECTED_ADMINISTRATOR_ID,
                    this.getIndex(), (String) newValue);
            populateListViews();
            updateAchievementButton.setDisable(false);
            achievementsListView.setEditable(false);
        }
    }
}
