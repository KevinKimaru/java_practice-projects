package com.masomohigh.view.admin;

import com.masomohigh.view.MainApp;
import com.masomohigh.view.AdminUtilities;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Created by Kevin Kimaru Chege on 12/31/2017.
 */
public class AdministartionBlockTitle {
    private BorderPane topAdminBarBorderPane;
    private HBox userLogoutBtnHBox;
    private Label adminTitleLabel;
    private Label loggedInAdminLabel;
    private Button logOutAdminButton;
    private Button homeButton;

    public AdministartionBlockTitle() {
        userLogoutBtnHBox = new HBox();
        topAdminBarBorderPane = new BorderPane();

        adminTitleLabel = new Label("ADMINISTRATION BLOCK");
        loggedInAdminLabel = new Label("Kevin Kimaru");
        logOutAdminButton = new Button("Logout");
        homeButton = new Button("Home");

        userLogoutBtnHBox.getChildren().addAll(loggedInAdminLabel, logOutAdminButton);

        topAdminBarBorderPane.setCenter(adminTitleLabel);
        topAdminBarBorderPane.setRight(userLogoutBtnHBox);
        topAdminBarBorderPane.setLeft(homeButton);

        beautifyAdministrationBlock();
        setBtnActions();
        setLabel();
    }

    public BorderPane getTopAdminBarBorderPane() {
        return topAdminBarBorderPane;
    }

    private void beautifyAdministrationBlock() {
        topAdminBarBorderPane.getStyleClass().addAll("titleViewBackground");
        adminTitleLabel.getStyleClass().addAll("title4");

        logOutAdminButton.getStyleClass().add("btnDanger");
        loggedInAdminLabel.getStyleClass().addAll("font2", "defaultFont");
        loggedInAdminLabel.setMaxHeight(Double.MAX_VALUE);

        homeButton.getStyleClass().add("btnInfo");
    }

    private void setBtnActions() {
        logOutAdminButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                AdminUtilities.ADMINISTRATOR_LOGGED_IN = false;
                AdminUtilities.LOGGED_IN_ADMIN = null;
                MainApp.rootScene.setToHome();
            }
        });

        loggedInAdminLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AdminUtilities.SELECTED_ADMINISTRATOR = AdminUtilities.LOGGED_IN_ADMIN;
                AdminUtilities.SELECTED_ADMINISTRATOR_ID = AdminUtilities.LOGGED_IN_ADMIN.getIdNumber();
                MainApp.allViewsAdminAdmin.getViewAdminDetails().populateView();
                MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminAdmin.getViewAdminDetails().getViewAdminDetailsVBox());
            }
        });
        loggedInAdminLabel.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                loggedInAdminLabel.setStyle("-fx-background-color: #99e6ff");
            }
        });
        loggedInAdminLabel.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                loggedInAdminLabel.setStyle("-fx-background-color: #ffffff");
            }
        });

        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainApp.rootScene.setToHome();
            }
        });

    }

    protected void setLabel() {
        if (AdminUtilities.LOGGED_IN_ADMIN != null) {
            loggedInAdminLabel.setText(AdminUtilities.LOGGED_IN_ADMIN.getFirstName() + " " +
                    AdminUtilities.LOGGED_IN_ADMIN.getFirstName());
        } else {
            loggedInAdminLabel.setText("Null");
        }
    }

}
