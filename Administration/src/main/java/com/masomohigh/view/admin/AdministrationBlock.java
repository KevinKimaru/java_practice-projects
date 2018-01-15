package com.masomohigh.view.admin;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Created by Kevin Kimaru Chege on 12/22/2017.
 */
public class AdministrationBlock {

    private VBox admnistrationNlockMainView;
    private Label adminWelcomeLabel;
    private AdministartionBlockTitle mAdministartionBlockTitle;
    private Label welcomeAdminLabel;

    public AdministrationBlock() {
        admnistrationNlockMainView = new VBox();
        mAdministartionBlockTitle = new AdministartionBlockTitle();

        welcomeAdminLabel = new Label("Welcome");
        adminWelcomeLabel = new Label("Welcome administrator");

        admnistrationNlockMainView.getChildren().addAll(mAdministartionBlockTitle
                .getTopAdminBarBorderPane(), welcomeAdminLabel, adminWelcomeLabel);

        beautifyAdministrationBlock();
    }

    public VBox getAdmnistrationNlockMainView() {
        return admnistrationNlockMainView;
    }

    public AdministartionBlockTitle getAdministartionBlockTitle() {
        return mAdministartionBlockTitle;
    }


    private void beautifyAdministrationBlock() {

        admnistrationNlockMainView.getStyleClass().addAll("mainViews", "childrenSpacing1");

        adminWelcomeLabel.getStyleClass().addAll("informationViews");
        welcomeAdminLabel.getStyleClass().addAll("title2");
        welcomeAdminLabel.setAlignment(Pos.CENTER);
    }

}
