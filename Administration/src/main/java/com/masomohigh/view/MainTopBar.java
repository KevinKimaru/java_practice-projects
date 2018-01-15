package com.masomohigh.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Created by Kevin Kimaru Chege on 12/22/2017.
 */
public class MainTopBar {

    VBox mainTopViewVBox;
    HBox btnsAndAddressHBox;

    Label schoolNameTitle;
    Label schoolAddressLabel;

    Button homeButton;

    public MainTopBar() {
        mainTopViewVBox = new VBox();
        btnsAndAddressHBox = new HBox();

        btnsAndAddressHBox.setAlignment(Pos.CENTER);

        mainTopViewVBox.setAlignment(Pos.CENTER);
        mainTopViewVBox.getStyleClass().add("titleViewBackground");

        schoolNameTitle = new Label(AdminUtilities.SCHOOL_NAME);
        schoolNameTitle.getStyleClass().add("title1");

        schoolAddressLabel = new Label(AdminUtilities.ADDRESS);
        schoolAddressLabel.getStyleClass().add("title2");

        homeButton = new Button("Home");
        HBox.setHgrow(homeButton, Priority.ALWAYS);

        btnsAndAddressHBox.getChildren().addAll(schoolAddressLabel, homeButton);
        mainTopViewVBox.getChildren().addAll(schoolNameTitle, schoolAddressLabel);
    }

    public VBox getMainTopViewVBox() {
        return mainTopViewVBox;
    }
}
