package com.masomohigh.view.admin;

import com.masomohigh.controller.AdminController;
import com.masomohigh.view.MainApp;
import com.masomohigh.view.AdminUtilities;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by Kevin Kimaru Chege on 12/30/2017.
 */
public class AdminChangePassword {
    private AdministartionBlockTitle administartionBlockTitle;

    private VBox adminChangePasswordVBox;

    private GridPane changePasswordGridPane;
    private Label viewLabel;

    private Label currentPasswordLabel;
    private Label newPasswordLabel;
    private Label confirmNewPasswordLabel;

    private PasswordField currentPaswordTextField;
    private PasswordField newPaswordTextField;
    private PasswordField confirmNewPaswordTextField;

    private Button changeButton;
    private Button cancelButton;
    private HBox buttonsHBox;

    public AdminChangePassword() {
        adminChangePasswordVBox = new VBox();

        changePasswordGridPane = new GridPane();
        viewLabel = new Label();

        currentPasswordLabel = new Label("Current Password");
        newPasswordLabel = new Label("New Password");
        confirmNewPasswordLabel = new Label("Confirm New Password");

        currentPaswordTextField = new PasswordField();
        newPaswordTextField = new PasswordField();
        confirmNewPaswordTextField = new PasswordField();

        changePasswordGridPane.add(currentPasswordLabel, 0, 0);
        changePasswordGridPane.add(currentPaswordTextField, 1, 0);
        changePasswordGridPane.add(newPasswordLabel, 0, 1);
        changePasswordGridPane.add(newPaswordTextField, 1, 1);
        changePasswordGridPane.add(confirmNewPasswordLabel, 0, 2);
        changePasswordGridPane.add(confirmNewPaswordTextField, 1, 2);

        changeButton = new Button("Change");
        cancelButton = new Button("Cancel");
        buttonsHBox = new HBox();
        buttonsHBox.getChildren().addAll(changeButton, cancelButton);

        administartionBlockTitle = new AdministartionBlockTitle();
        adminChangePasswordVBox.getChildren().addAll(administartionBlockTitle.getTopAdminBarBorderPane(), viewLabel,
                changePasswordGridPane, buttonsHBox);

        beautifyView();
        setActionsForButton();

    }

    public VBox getAdminChangePasswordVBox() {
        return adminChangePasswordVBox;
    }

    public AdministartionBlockTitle getAdministartionBlockTitle() {
        return administartionBlockTitle;
    }

    private void beautifyView() {
        adminChangePasswordVBox.getStyleClass().addAll("defaultFont", "defaultBackground",
                "childrenSpacing1", "mainViews");
        adminChangePasswordVBox.setAlignment(Pos.TOP_CENTER);

        changePasswordGridPane.setHgap(10);
        changePasswordGridPane.setVgap(10);
        changePasswordGridPane.setAlignment(Pos.CENTER);

        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(20.0);

        changeButton.getStyleClass().addAll("btnInfo2");
        cancelButton.getStyleClass().addAll("btnDanger2");

        viewLabel.getStyleClass().addAll("title3");
    }

    private void setActionsForButton() {
        changeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (isValid()) {
                    if (AdminController.changePassword(AdminUtilities.SELECTED_ADMINISTRATOR.getIdNumber(),
                            AdminUtilities.SELECTED_ADMINISTRATOR.getPassword(),
                            confirmNewPaswordTextField.getText().trim())){
                        AdminUtilities.setSelectedAdministrator();
                        AdminUtilities.getAllAdministrators();
                        confirmNewPaswordTextField.clear();
                        newPaswordTextField.clear();
                        currentPaswordTextField.clear();
                        System.out.println("New Password: " + AdminUtilities.SELECTED_ADMINISTRATOR.getPassword());
                        MainApp.allViewsAdminAdmin.getViewAdminDetails().populateView();
                        MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminAdmin.getViewAdminDetails().getViewAdminDetailsVBox());
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Change password operation failed.");
                        alert.showAndWait();
                    }
                }
            }
        });
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainApp.allViewsAdminAdmin.getViewAdminDetails().populateView();
                MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminAdmin.getViewAdminDetails().getViewAdminDetailsVBox());
            }
        });
    }

    public void setLabeltext() {
        viewLabel.setText("Change Password For: " + AdminUtilities.SELECTED_ADMINISTRATOR.getFirstName()
                + " " + AdminUtilities.SELECTED_ADMINISTRATOR.getMiddleName());
    }

    private boolean isValid() {
        boolean valid = true;
        String errMess1 = "";
        String errMess2 = "";
        if (currentPaswordTextField.getText().trim().isEmpty()) {
            errMess1 += "Current Password Field Cannot be empty\n";
            valid = false;
        }
        if (newPaswordTextField.getText().trim().isEmpty()) {
            errMess1 += "New Password Field Cannot be empty\n";
            valid = false;
        }
        if (confirmNewPaswordTextField.getText().trim().isEmpty()) {
            errMess1 += "Confirm New Password Field Cannot be empty\n";
            valid = false;
        }

        if (!AdminUtilities.SELECTED_ADMINISTRATOR.getPassword().contentEquals(currentPaswordTextField.getText().trim())) {
            errMess2 += "Wrong Password\n";
            valid = false;
        } else {
            if (!newPaswordTextField.getText().trim().contentEquals(confirmNewPaswordTextField.getText().trim())) {
                errMess2 += "New password is not same as confirmed password.\n";
                valid = false;
            }
        }

        if (!valid) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(errMess1 + "\n" + errMess2);
            alert.showAndWait();
        }
        return valid;
    }
}
