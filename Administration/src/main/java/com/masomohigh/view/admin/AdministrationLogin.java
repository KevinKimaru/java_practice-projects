package com.masomohigh.view.admin;

import com.masomohigh.controller.AdminController;
import com.masomohigh.model.Administrator;
import com.masomohigh.view.MainApp;
import com.masomohigh.view.AdminUtilities;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by Kevin Kimaru Chege on 12/22/2017.
 */
public class AdministrationLogin {
    private AdministartionBlockTitle administartionBlockTitle;

    private GridPane loginGridPane;

    private TextField userNameTextField;
    private PasswordField mPasswordField;
    private Label userNameLabel;
    private Label passwordLabel;
    private Label titlePage;
    private Button loginButton;
    private HBox loginTitleHBox;

    public AdministrationLogin() {
        loginGridPane = new GridPane();

        loginTitleHBox = new HBox();

        userNameTextField = new TextField();
        mPasswordField = new PasswordField();
        userNameLabel = new Label("Id Number");
        passwordLabel = new Label("Password");
        titlePage = new Label("Administrator Login");
        loginButton = new Button("Login");
        loginButton.setDisable(true);

        loginGridPane.add(loginTitleHBox, 0, 0, 2, 1);
        loginGridPane.add(userNameLabel, 0, 1);
        loginGridPane.add(userNameTextField, 1, 1);
        loginGridPane.add(passwordLabel, 0, 2);
        loginGridPane.add(mPasswordField, 1, 2);
        loginGridPane.add(loginButton, 1, 3);

        setActionForButtons();
        beautifyView();
        enableDisableLoginButton();
    }

    public GridPane getLoginGridPane() {
        return loginGridPane;
    }

    private void setActionForButtons() {
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (loginAdministrator()) {
                    MainApp.rootScene.getMainView().setLeft(MainApp.allViewsAdminAdmin.getAdministrationLeftTree().getTreeBar());
                    MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminAdmin.getAdministrationBlock().getAdmnistrationNlockMainView());
                }
            }
        });
    }

    private boolean loginAdministrator() {
        String userName = userNameTextField.getText();
        String password = mPasswordField.getText();
        Administrator administrator = AdminController.loginAdministrator(Integer.valueOf(userName), password);
        if (administrator == null) {
            Alert alert = createNodeForAlert("Login failed.");
            alert.showAndWait();
            return false;
        } else {
            AdminUtilities.ADMINISTRATOR_LOGGED_IN = true;
            AdminUtilities.LOGGED_IN_ADMIN = administrator;
            userNameTextField.clear();
            mPasswordField.clear();
            setLoggedInLabel();
            return true;
        }
    }

    private void beautifyView() {
        loginGridPane.setHgap(10);
        loginGridPane.setVgap(10);
        loginGridPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        loginGridPane.setAlignment(Pos.CENTER);
        loginGridPane.getStyleClass().addAll("defaultFont", "defaultBackground", "childrenSpacing", "font2");

        loginTitleHBox.getChildren().add(titlePage);
        loginTitleHBox.setAlignment(Pos.CENTER);
        loginTitleHBox.getStyleClass().add("title3");

        titlePage.setTextFill(Color.web("#660000"));
    }

    private Alert createNodeForAlert(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(AdminUtilities.SCHOOL_NAME);

        Label errorLabel = new Label(errorMessage);
        errorLabel.setTextFill(Color.RED);
        errorLabel.setFont(Font.font("AR ESSENCE", FontWeight.BOLD, 22));

        alert.setGraphic(errorLabel);
        return alert;
    }

    private void enableDisableLoginButton() {
        userNameTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (userNameTextField.getText().length() > 0 && mPasswordField.getText().length() > 0)
                    loginButton.setDisable(false);
                else loginButton.setDisable(true);
            }
        });
        mPasswordField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (userNameTextField.getText().length() > 0 && mPasswordField.getText().length() > 0)
                    loginButton.setDisable(false);
                else loginButton.setDisable(true);
            }
        });
    }

    private void setLoggedInLabel() {
        MainApp.allViewsAdminAdmin.getAddAdmin().getAdministartionBlockTitle().setLabel();
        MainApp.allViewsAdminAdmin.getAdministrationBlock().getAdministartionBlockTitle().setLabel();
        MainApp.allViewsAdminAdmin.getAdminChangePassword().getAdministartionBlockTitle().setLabel();
        MainApp.allViewsAdminAdmin.getViewAdmin().getAdministartionBlockTitle().setLabel();
        MainApp.allViewsAdminAdmin.getViewAdminDetails().getAdministartionBlockTitle().setLabel();
        MainApp.allViewsAdminAdmin.getViewAdmins().getAdministartionBlockTitle().setLabel();
        MainApp.allViewsAdminAdmin.getUpdateAdmin().getAdministartionBlockTitle().setLabel();
        MainApp.allViewsAdminAdmin.getUpdateAdminMore().getAdministartionBlockTitle().setLabel();

        MainApp.allViewsAdminTeacher.getAllTeachers().getAdministartionBlockTitle().setLabel();
        MainApp.allViewsAdminTeacher.getAddTeacher().getAdministartionBlockTitle().setLabel();
        MainApp.allViewsAdminTeacher.getTeacherDetails().getAdministartionBlockTitle().setLabel();
        MainApp.allViewsAdminTeacher.getUpdateTeacher().getAdministartionBlockTitle().setLabel();
        MainApp.allViewsAdminTeacher.getUpdateTeacherClasses().getAdministartionBlockTitle().setLabel();
        MainApp.allViewsAdminTeacher.getUpdateTeacherAchievements().getAdministartionBlockTitle().setLabel();

        MainApp.allViewsAdminClass.getAddClass().getAdministartionBlockTitle().setLabel();
        MainApp.allViewsAdminClass.getAllClasses().getAdministartionBlockTitle().setLabel();
    }
}
