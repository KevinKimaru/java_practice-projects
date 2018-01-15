package com.masomohigh.view;

import com.masomohigh.view.admin.AdministrationLeftTree;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * Created by Kevin Kimaru Chege on 12/21/2017.
 */
public class LeftMainMenu {
    AdministrationLeftTree mAdministrationLeftTree;
    private About mAbout;

    private VBox mVBox;
    private Button adminButton;
    private Button teacherButton;
    private Button studentButton;
    private Button houseButton;
    private Button classButton;
    private Button perfomanceButton;
    private Button aboutButton;

    public LeftMainMenu() {

        mVBox = new VBox();
        adminButton = new Button("Administration");
        teacherButton = new Button("Teaching Staff");
        studentButton = new Button("Student");
        houseButton = new Button("Houses");
        classButton = new Button("Classes");
        perfomanceButton = new Button("Perfomance");
        aboutButton = new Button("About");
        setButtonsToSameSize();
        beautifyButtonsAndVBox();
        setActionsForButtons();
        setHighlitedButton(aboutButton);
        mVBox.getChildren().addAll(adminButton, teacherButton, studentButton, houseButton, classButton, perfomanceButton, aboutButton);

        mAbout = new About();
        mAdministrationLeftTree = new AdministrationLeftTree();
    }

    public VBox getVBox() {
        return mVBox;
    }

    private void setButtonsToSameSize() {
        adminButton.setMaxWidth(Double.MAX_VALUE);
        adminButton.setPrefHeight(60.0);
        teacherButton.setMaxWidth(Double.MAX_VALUE);
        teacherButton.setPrefHeight(60.0);
        studentButton.setMaxWidth(Double.MAX_VALUE);
        studentButton.setPrefHeight(60.0);
        houseButton.setMaxWidth(Double.MAX_VALUE);
        houseButton.setPrefHeight(60.0);
        classButton.setMaxWidth(Double.MAX_VALUE);
        classButton.setPrefHeight(60.0);
        perfomanceButton.setMaxWidth(Double.MAX_VALUE);
        perfomanceButton.setPrefHeight(60.0);
        aboutButton.setMaxWidth(Double.MAX_VALUE);
        aboutButton.setPrefHeight(60.0);
    }

    private void beautifyButtonsAndVBox() {
        mVBox.getStyleClass().add("navigationBar1");
    }

    private void setActionsForButtons() {
        adminButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                setHighlitedButton(adminButton);
                if (!AdminUtilities.ADMINISTRATOR_LOGGED_IN) {
                    MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminAdmin.getAdministrationLogin().getLoginGridPane());
                } else {
                    MainApp.rootScene.setToAdministration();
                }
            }
        });
        teacherButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                setHighlitedButton(teacherButton);
            }
        });
        studentButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                setHighlitedButton(studentButton);
            }
        });
        houseButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                setHighlitedButton(houseButton);
            }
        });
        classButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                setHighlitedButton(classButton);
            }
        });
        perfomanceButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                setHighlitedButton(perfomanceButton);
            }
        });
        aboutButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                setHighlitedButton(aboutButton);
                MainApp.rootScene.getMainView().setCenter(mAbout.getAboutSchoolLabel());
            }
        });
    }

    public void setHighlitedButton(Button button) {
        adminButton.setId("adminButton");
        teacherButton.setId("teacherButton");
        studentButton.setId("studentButton");
        houseButton.setId("houseButton");
        classButton.setId("classButton");
        perfomanceButton.setId("perfomanceButton");
        aboutButton.setId("aboutButton");
        button.setId("selectedBtn");
    }

    public Button getAdminButton() {
        return adminButton;
    }

    public Button getTeacherButton() {
        return teacherButton;
    }

    public Button getStudentButton() {
        return studentButton;
    }

    public Button getHouseButton() {
        return houseButton;
    }

    public Button getClassButton() {
        return classButton;
    }

    public Button getPerfomanceButton() {
        return perfomanceButton;
    }

    public Button getAboutButton() {
        return aboutButton;
    }
}
