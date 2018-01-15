package com.kevin.views;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;

import static com.kevin.views.Utilities.*;

/**
 * Created by Kevin Kimaru Chege on 11/2/2017.
 */
public class StudentRegistration {

    private static Label firstNameLabel = new Label("First Name");
    private static TextField firstNameTF = new TextField();
    private static Label middleNameLabel = new Label("Middle Name");
    private static TextField middleNameTF = new TextField("Kimaru");
    private static Label lastNameLabel = new Label("Last Name");
    private static TextField lastNameTF = new TextField("Chege");
    private static Label admnoLabel = new Label("Admission Number");
    private static TextField admnoTF = new TextField();
    private static Label dobLabel = new Label("Date of Birth");
    private static DatePicker dobTF = new DatePicker();
    private static Label houseLabel = new Label("House");
    private static TextField houseTF = new TextField("Kibaki");
    private static Label streamLabel = new Label("Stream");
    private static TextField streamTF = new TextField("B");
    private static Label currentFormLabel = new Label("Current Form");
    private static TextField currentFormTF = new TextField("4");
    private static TextField[] registrationTextFields = new TextField[]{firstNameTF, middleNameTF, lastNameTF, admnoTF, houseTF, streamTF, currentFormTF};
    private static Button submit = new Button("Submit");
    private static Button cancelReg = new Button("Cancel");


    public static GridPane createStudentRegistration() {
        GridPane studentReg = new GridPane();
        studentReg.setHgap(10);
        studentReg.setVgap(10);
        studentReg.setAlignment(Pos.TOP_CENTER);
        studentReg.add(firstNameLabel, 0, 0, 1, 1);
        studentReg.add(firstNameTF, 1, 0, 1, 1);
        studentReg.add(middleNameLabel, 0, 1, 1, 1);
        studentReg.add(middleNameTF, 1, 1, 1, 1);
        studentReg.add(lastNameLabel, 0, 2, 1, 1);
        studentReg.add(lastNameTF, 1, 2, 1, 1);
        studentReg.add(admnoLabel, 0, 3, 1, 1);
        studentReg.add(admnoTF, 1, 3, 1, 1);
        studentReg.add(dobLabel, 0, 4, 1, 1);
        studentReg.add(dobTF, 1, 4, 1, 1);
        studentReg.add(houseLabel, 0, 5, 1, 1);
        studentReg.add(houseTF, 1, 5, 1, 1);
        studentReg.add(streamLabel, 0, 6, 1, 1);
        studentReg.add(streamTF, 1, 6, 1, 1);
        studentReg.add(currentFormLabel, 0, 7, 1, 1);
        studentReg.add(currentFormTF, 1, 7, 1, 1);

        submit.setDisable(true);
        Utilities.disableEnableBtnsIfTFIsFilled(registrationTextFields, dobTF, submit);
        submit.setAlignment(Pos.CENTER);
        studentReg.add(submit, 0, 8, 1, 1);
        studentReg.add(cancelReg, 1, 8, 1, 1);

        submit.setOnAction(e -> {
            submit();
        });
        cancelReg.setOnAction(e -> {
            Utilities.clear(registrationTextFields, new Button[]{submit, cancelReg}, dobTF);
        });

        return studentReg;
    }

    private static void submit() {
        String admissNo = admnoTF.getText().trim();
        currentStudent = Integer.valueOf(admissNo);
        try {
            launchDb.addStudent(Integer.valueOf(admissNo),
                    firstNameTF.getText().trim(),
                    middleNameTF.getText().trim(),
                    lastNameTF.getText().trim(),
                    dobTF.getValue(),
                    houseTF.getText().trim(),
                    streamTF.getText().trim(),
                    Integer.valueOf(currentFormTF.getText().trim()));

            Utilities.clear(registrationTextFields, new Button[]{submit, cancelReg}, dobTF);
            Utilities.root.setCenter(StudentAccountRegistration.createAfterSuccessRegGrid());
            Utilities.root.setRight(Utilities.studentDetailsDisplay(currentStudent));
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("OOps an error occured\n." + e.getMessage());
            errorAlert.showAndWait();
        }
    }
}
