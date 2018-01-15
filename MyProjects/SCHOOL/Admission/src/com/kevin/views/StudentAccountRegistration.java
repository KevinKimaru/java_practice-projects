package com.kevin.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;

import static com.kevin.views.Utilities.currentStudent;
import static com.kevin.views.Utilities.launchDb;

/**
 * Created by Kevin Kimaru Chege on 11/2/2017.
 */
public class StudentAccountRegistration {

    private static GridPane afterSuccReg = new GridPane();

    private static Label successText = new Label("Student Successfully Registered.");
    private static Label promptStAccText = new Label("To enable the student access the school system, "
            + "let him or her open an account below.");
    private static Label stuUserNameLabel = new Label("Username");
    private static TextField stuUserNameTF = new TextField();
    private static Label stuPasswordLabel = new Label("Password");
    private static PasswordField stuPasswordTF = new PasswordField();
    private static Label stuConfPasswordLabel = new Label("Confirm Password");
    private static PasswordField stuConfPasswordTF = new PasswordField();
    private static TextField[] stuAccountTextFields = new TextField[]{stuUserNameTF, stuPasswordTF, stuConfPasswordTF};
    private static Label errorText = new Label("Passwords do not match. Please try again.");

    private static Button saveBtn = new Button("Save");
    private static Button cancelBtn = new Button("Cancel");

    public StudentAccountRegistration() {

    }

    public static GridPane createAfterSuccessRegGrid() {
        afterSuccReg = new GridPane();
        afterSuccReg.setAlignment(Pos.TOP_CENTER);
        afterSuccReg.setHgap(10);
        afterSuccReg.setVgap(10);
        successText.setWrapText(true);
        afterSuccReg.add(successText, 0, 0, 2, 1);
        promptStAccText.setWrapText(true);
        afterSuccReg.add(promptStAccText, 0, 1, 2, 1);
        afterSuccReg.add(stuUserNameLabel, 0, 2);
        afterSuccReg.add(stuUserNameTF, 1, 2);
        afterSuccReg.add(stuPasswordLabel, 0, 3);
        afterSuccReg.add(stuPasswordTF, 1, 3);
        afterSuccReg.add(stuConfPasswordLabel, 0, 4);
        afterSuccReg.add(stuConfPasswordTF, 1, 4);

        afterSuccReg.add(saveBtn, 0, 5);
        afterSuccReg.add(cancelBtn, 1, 5);

        saveBtn.setDisable(true);
        Utilities.disableEnableBtnsIfTFIsFilled(stuAccountTextFields, null, saveBtn);
        saveBtn.setOnAction(e -> {
            submit();
        });
        cancelBtn.setOnAction(e -> {
            cancel();
        });

        return afterSuccReg;
    }

    private static void submit() {
        if (stuPasswordTF.getText().equals(stuConfPasswordTF.getText())) {
            try {
                launchDb.addStudentAccount(currentStudent, stuUserNameTF.getText(), stuConfPasswordTF.getText());
                System.out.println("CURRENT STUDENT " + currentStudent);
            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("OOps an error occurred. Please try again later");
                alert.showAndWait();
            }
            Utilities.clear(stuAccountTextFields, new Button[]{saveBtn, cancelBtn});
            Utilities.root.setCenter(StudentRegistration.createStudentRegistration());
            Utilities.root.setRight(null);
            afterSuccReg.getChildren().remove(errorText);
        } else {
            afterSuccReg.add(errorText, 0, 6, 2,1);
        }
    }

    private static void cancel() {
        Utilities.clear(stuAccountTextFields, new Button[] {saveBtn, cancelBtn});
        Utilities.root.setCenter(StudentRegistration.createStudentRegistration());
        Utilities.root.setRight(null);
        afterSuccReg.getChildren().remove(errorText);
    }
}
