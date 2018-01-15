package com.masomohigh.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Created by Kevin Kimaru Chege on 12/21/2017.
 */
public class About {
    public static final String aboutSchool = "Masomo High school was began in the year 2006." +
            "It is the best school in Coast." +
            "It accommodates 2000 students.";
    private Label aboutSchoolLabel;

    public About() {
        aboutSchoolLabel = new Label(aboutSchool);
        beautifyAboutSchool();
    }

    public Label getAboutSchoolLabel() {
        return aboutSchoolLabel;
    }

    private void beautifyAboutSchool() {
        aboutSchoolLabel.getStyleClass().addAll("informationViews", "nodePadding1");
        aboutSchoolLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        aboutSchoolLabel.setTextAlignment(TextAlignment.LEFT);
        aboutSchoolLabel.setAlignment(Pos.TOP_LEFT);
        aboutSchoolLabel.setWrapText(true);
    }
}
