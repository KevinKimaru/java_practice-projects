package com.kevin.views;

import com.kevin.databases.Launch;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.Map;

/**
 * Created by Kevin Kimaru Chege on 11/2/2017.
 */
public class Utilities {
    public static Launch launchDb = new Launch();
    public static int currentStudent = 0;
    public static BorderPane root = new BorderPane();

    protected static void clear(TextField[] textFieldsToClear, Button[] buttons, DatePicker...datePickers) {
        for (TextField textField : textFieldsToClear) {
            textField.clear();
        }
        for (Button button : buttons) {
            button.setDisable(true);
        }
        if (datePickers.length > 0) {
            for (DatePicker datePicker: datePickers) {
                if (datePicker != null) {
                    datePicker.setValue(null);
                }
            }
        }
    }

    private static boolean checkIfTFIsFilled(TextField[] textFieldsToCheck) {
        for (TextField textField : textFieldsToCheck) {
            if (textField.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    protected static void disableEnableBtnsIfTFIsFilled(TextField[] textFieldsToMonitor, DatePicker datePicker, Button btnToEnableDisable) {
        if (datePicker != null) {
            for (TextField textField : textFieldsToMonitor) {
                textField.setOnKeyReleased(e -> {
                    if (!checkIfTFIsFilled(textFieldsToMonitor) || doEnableBtnsIfDatePickerisFilled(datePicker, btnToEnableDisable)) {
                        btnToEnableDisable.setDisable(true);
                    } else {
                        btnToEnableDisable.setDisable(false);
                    }
                });
            }
        } else {
            for (TextField textField : textFieldsToMonitor) {
                textField.setOnKeyReleased(e -> {
                    if (!checkIfTFIsFilled(textFieldsToMonitor)) {
                        btnToEnableDisable.setDisable(true);
                    } else {
                        btnToEnableDisable.setDisable(false);
                    }
                });
            }
        }
    }

    private static boolean doEnableBtnsIfDatePickerisFilled(DatePicker datePicker, Button btn) {
        final boolean[] enable = new boolean[1];
        datePicker.setOnAction(e -> {
            if (datePicker.getValue() == null) {
                enable[0] = false;
            } else {
                enable[0] = true;
            }
        });
        datePicker.setOnKeyReleased(e -> {
            if (datePicker.getValue() == null) {
                enable[0] = false;
            } else {
                enable[0] = true;
            }
        });
        return enable[0];
    }

    protected static GridPane studentDetailsDisplay(int registrationNumber) {
        GridPane rightBorderAfterSuccReg = new GridPane();
        rightBorderAfterSuccReg.setHgap(10);
        rightBorderAfterSuccReg.setVgap(10);
        rightBorderAfterSuccReg.setAlignment(Pos.TOP_CENTER);
        Map<String, String> studentDetails = launchDb.getStudentDetails(registrationNumber);
        int i = 0;
        if (studentDetails != null) {
            for (Map.Entry<String, String> entry : studentDetails.entrySet()) {
                Label key = new Label(entry.getKey());
                Label value = new Label(entry.getValue());
                System.out.println(key + "==" + value);
                rightBorderAfterSuccReg.add(key, 0, i);
                rightBorderAfterSuccReg.add(value, 1, i);
                i++;
            }
        } else {
            System.out.println("Empty");
        }
        return rightBorderAfterSuccReg;
    }
}
