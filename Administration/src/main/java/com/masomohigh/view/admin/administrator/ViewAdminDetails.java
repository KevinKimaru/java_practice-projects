package com.masomohigh.view.admin.administrator;

import com.masomohigh.controller.AdminController;
import com.masomohigh.model.Administrator;
import com.masomohigh.view.MainApp;
import com.masomohigh.view.AdminUtilities;
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
 * Created by Kevin Kimaru Chege on 12/25/2017.
 */
public class ViewAdminDetails {
    private AdministartionBlockTitle administartionBlockTitle;
    private Label viewAdminDetailsLabel;

    private VBox viewAdminDetailsVBox;
    private GridPane adminDetailsGridPane;
    private HBox buttonsHBox;
    private ScrollPane mScrollPane;

    private Button backBtn;
    private Button updateBtn;
    private Button deleteAdminBtn;

    private Label idNumberLabel;
    private Label firstNameLabel;
    private Label middleNameLabel;
    private Label lastNameLabel;
    private Label phoneNumberLabel;
    private Label addressLabel;
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
    private Label obligationsTitleLabel;
    private Label obligationsBackupTitleLabel;
    private Label achievementsListTitleLabel;
    private Label statusTitleLabel;
    private Label emailTitleLabel;
    private Label dateOfBirthTitleLable;
    private Label dateOfStartTitleLable;

    public ViewAdminDetails() {
        viewAdminDetailsLabel = new Label("VIEW ADMIN DETAILS");

        viewAdminDetailsVBox = new VBox();
        adminDetailsGridPane = new GridPane();
        buttonsHBox = new HBox();
        mScrollPane = new ScrollPane();

        backBtn = new Button("Ok");
        updateBtn = new Button("Update");
        deleteAdminBtn = new Button("Delete admin");

        buttonsHBox.getChildren().addAll(backBtn, updateBtn, deleteAdminBtn);

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

        adminDetailsGridPane.add(idNumberTitleLabel, 0, 0);
        adminDetailsGridPane.add(idNumberLabel, 1, 0);
        adminDetailsGridPane.add(firstNameTitleLabel, 0, 1);
        adminDetailsGridPane.add(firstNameLabel, 1, 1);
        adminDetailsGridPane.add(middleNameTitleLabel, 0, 2);
        adminDetailsGridPane.add(middleNameLabel, 1, 2);
        adminDetailsGridPane.add(lastNameTitleLabel, 0, 3);
        adminDetailsGridPane.add(lastNameLabel, 1, 3);
        adminDetailsGridPane.add(phoneNumberTitleLabel, 0, 4);
        adminDetailsGridPane.add(phoneNumberLabel, 1, 4);
        adminDetailsGridPane.add(addressTitleLabel, 0, 5);
        adminDetailsGridPane.add(addressLabel, 1, 5);
        adminDetailsGridPane.add(emailTitleLabel, 0, 6);
        adminDetailsGridPane.add(emailLabel, 1, 6);
        adminDetailsGridPane.add(dateOfBirthTitleLable, 0, 7);
        adminDetailsGridPane.add(dateOfBirthLabel, 1, 7);
        adminDetailsGridPane.add(dateOfStartTitleLable, 0, 8);
        adminDetailsGridPane.add(dateOfStartLabel, 1, 8);
        adminDetailsGridPane.add(obligationsTitleLabel, 0, 9);
        adminDetailsGridPane.add(obligationsListView, 1, 9);
        adminDetailsGridPane.add(obligationsBackupTitleLabel, 0, 10);
        adminDetailsGridPane.add(obligationsBackupListView, 1, 10);
        adminDetailsGridPane.add(achievementsListTitleLabel, 0, 11);
        adminDetailsGridPane.add(achievementsListListView, 1, 11);
        adminDetailsGridPane.add(statusTitleLabel, 0, 12);
        adminDetailsGridPane.add(statusLabel, 1, 12);

        mScrollPane.setContent(adminDetailsGridPane);

        administartionBlockTitle = new AdministartionBlockTitle();
        viewAdminDetailsVBox.getChildren().addAll(administartionBlockTitle
                        .getTopAdminBarBorderPane(), viewAdminDetailsLabel, mScrollPane,
                buttonsHBox);
        populateView();
        setBtnActions();
        beautifyAdminDetails();
    }

    public VBox getViewAdminDetailsVBox() {
        return viewAdminDetailsVBox;
    }

    public AdministartionBlockTitle getAdministartionBlockTitle() {
        return administartionBlockTitle;
    }

    public void populateView() {
        try {
            Administrator administrator = AdminUtilities.SELECTED_ADMINISTRATOR;
            idNumberLabel.setText(String.valueOf(administrator.getIdNumber()));
            firstNameLabel.setText(administrator.getFirstName());
            middleNameLabel.setText(administrator.getMiddleName());
            lastNameLabel.setText(administrator.getLastName());
            phoneNumberLabel.setText(administrator.getPhoneNumber());
            addressLabel.setText("P.O. Box " + administrator.getAddress().getBaltex() + "-" +
                    administrator.getAddress().getPostalCode() + " " + administrator.getAddress().getCity());
            statusLabel.setText(administrator.getStatus());
            emailLabel.setText(administrator.getEmail());
            dateOfBirthLabel.setText(administrator.getDateOfBirth().toString());
            dateOfStartLabel.setText(administrator.getDateOfStart().toString());

            ObservableList<String> obligationItems = FXCollections.observableArrayList();
            ObservableList<String> obligationBackupItems = FXCollections.observableArrayList();
            ObservableList<String> achievementItems = FXCollections.observableArrayList();

            try {
                obligationItems.addAll(administrator.getObligations());
            } catch (Exception e) {

            }
            try {
                obligationBackupItems.addAll(administrator.getObligationsBackup());
            } catch (Exception e) {

            }
            try {
                achievementItems.addAll(administrator.getAchievements());
            } catch (Exception e) {

            }

            obligationsListView.setItems(obligationItems);
            obligationsBackupListView.setItems(obligationBackupItems);
            achievementsListListView.setItems(achievementItems);
        } catch (Exception e) {

        }
    }

    private void setBtnActions() {
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                MainApp.allViewsAdminAdmin.getViewAdmins().addDataToTable();
                MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminAdmin.getViewAdmins().getViewAdminsVBox());
            }
        });
        updateBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                MainApp.allViewsAdminAdmin.getUpdateAdmin().populateTextFields();
                MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminAdmin.getUpdateAdmin().getMainAdminUpdateVBox());
            }
        });
        deleteAdminBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
                deleteAlert.getButtonTypes().removeAll(deleteAlert.getButtonTypes());
                deleteAlert.getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
                deleteAlert.setContentText("Are you sure you want to delete this administrator?\n" +
                        "Perhaps consider changing status of the administrator");
                Optional<ButtonType> results = deleteAlert.showAndWait();
                if (results.get() == ButtonType.YES) {
                    if (AdminController.deleteAdministrator(AdminUtilities.SELECTED_ADMINISTRATOR_ID)) {
                        MainApp.allViewsAdminAdmin.getViewAdmins().addDataToTable();
                        MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminAdmin.getViewAdmins().getViewAdminsVBox());
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Delete failed");
                        alert.showAndWait();
                    }
                } else {

                }
            }
        });
    }

    private void beautifyAdminDetails() {
        adminDetailsGridPane.getStyleClass().addAll("defaultBackground", "defaultFont", "font2",
                "nodePadding1", "childrenSpacing2");
        adminDetailsGridPane.setHgap(10.0);
        adminDetailsGridPane.setVgap(10.0);
        mScrollPane.setFitToWidth(true);

        viewAdminDetailsVBox.getStyleClass().addAll("mainViews");
        viewAdminDetailsVBox.setSpacing(10.0);
        viewAdminDetailsVBox.setAlignment(Pos.TOP_CENTER);

        backBtn.getStyleClass().add("btnInfo");
        updateBtn.getStyleClass().add("btnInfo");
        deleteAdminBtn.getStyleClass().add("btnDanger");

        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(20.0);

        viewAdminDetailsLabel.getStyleClass().addAll("title2");
    }
}
