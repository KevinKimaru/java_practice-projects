package com.masomohigh.view.admin.mclass;

import com.masomohigh.controller.ClassController;
import com.masomohigh.view.MainApp;
import com.masomohigh.view.admin.AdministartionBlockTitle;
import com.masomohigh.view.admin.student.StudentUtilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Created by Kevin Kimaru Chege on 1/8/2018.
 */
public class AddClass {

    private AdministartionBlockTitle mAdministartionBlockTitle;

    private Label addClassLabel;

    private VBox mainAddClassVBox;

    private GridPane classGenGridPane;
    private Label formLabel;
    private Label streamLabel;
    private Label yearLabel;
    private ListView<Integer> formListView;
    private ListView<String> streamListView;
    private ComboBox<Integer> yearComboBox;
    private Button generateButton;
    private Button cancelButton;
    private HBox btnsHBox;

    public AddClass() {
        mAdministartionBlockTitle = new AdministartionBlockTitle();

        addClassLabel = new Label("Add Class");

        classGenGridPane = new GridPane();

        formLabel = new Label("Forms");
        streamLabel = new Label("Streams");
        yearLabel = new Label("Year");
        formListView = new ListView<>();
        //TODO: MAKE THE FORMS DYNAMIC IN CASE OF A CHANGE IN EDUCATION SYSTEM
        formListView.setItems(FXCollections.observableArrayList(MainApp.FORMS));
        streamListView = new ListView<>();
        //TODO: MAKE THE STREAMS DYNAMIC IN CASE OF A CHANGE IN STREAMS
        streamListView.setItems(FXCollections.observableArrayList(MainApp.STREAMS));
        yearComboBox = new ComboBox<>();
        //TODO: CHANGE THIS METHOD TO BOOST PERFORMANCE
        ObservableList<Integer> years = FXCollections.observableArrayList();
        for (int i = 2000; i <= 2050; i++)
            years.add(i);
        yearComboBox.setItems(years);
        yearComboBox.setValue(LocalDate.now().getYear());

        classGenGridPane.add(formLabel, 0, 0);
        classGenGridPane.add(streamLabel, 1, 0);
        classGenGridPane.add(yearLabel, 2, 0);
        classGenGridPane.add(formListView, 0, 1);
        classGenGridPane.add(streamListView, 1, 1);
        classGenGridPane.add(yearComboBox, 2, 1);

        generateButton = new Button("Generate");
        cancelButton = new Button("Cancel");
        btnsHBox = new HBox();
        btnsHBox.getChildren().addAll(generateButton, cancelButton);

        mainAddClassVBox = new VBox();
        mainAddClassVBox.getChildren().addAll(mAdministartionBlockTitle.getTopAdminBarBorderPane(),
                addClassLabel, classGenGridPane, btnsHBox);

        streamListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        formListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        beautifyView();
        setBtnActions();
    }

    public AdministartionBlockTitle getAdministartionBlockTitle() {
        return mAdministartionBlockTitle;
    }

    public VBox getMainAddClassVBox() {
        return mainAddClassVBox;
    }

    private void beautifyView() {

    }

    private void setBtnActions() {
        generateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                generateClass();
            }
        });
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainApp.allViewsAdminClass.getAllClasses().populateView();
                MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminClass.getAllClasses().getMainAllClassesVBox());
            }
        });
    }

    private void generateClass() {
        ObservableList<Integer> selectedForms = formListView.getSelectionModel().getSelectedItems();
        ObservableList<String> selectedStreams = streamListView.getSelectionModel().getSelectedItems();
        int year = yearComboBox.getValue();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().removeAll(alert.getButtonTypes());
        alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
        String newClasses = "";
        for (int form : selectedForms) {
            for (String stream : selectedStreams) {
                newClasses += form + stream + year + "\n";
            }
        }
        alert.setContentText("Generate these classes: \n" + newClasses + "??");
        Optional<ButtonType> results = alert.showAndWait();
        if (results.get() == ButtonType.YES) {
            try{
                ClassController.generateClasses(selectedStreams, selectedForms, year);
                ClassUtilities.setAvailableClasses();
                Alert succAlert = new Alert(Alert.AlertType.INFORMATION);
                succAlert.setContentText("Classes Generated Successfully.");
                succAlert.show();
                StudentUtilities.getAllStudents();
                MainApp.allViewsAdminClass.getAllClasses().populateView();
                MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminClass.getAllClasses().getMainAllClassesVBox());
            } catch(Exception e) {
                Alert errAlert = new Alert(Alert.AlertType.ERROR);
                errAlert.setContentText("Oops, an error occurred" + e + "\n\nPlease ensure the class is not already" +
                        " generated");
                errAlert.show();
            }

        } else {

        }
    }
}
