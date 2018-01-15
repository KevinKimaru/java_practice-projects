package com.masomohigh.view.admin.mclass;

import com.masomohigh.controller.ClassController;
import com.masomohigh.model.Class;
import com.masomohigh.model.Teacher;
import com.masomohigh.view.MainApp;
import com.masomohigh.view.admin.AdministartionBlockTitle;
import com.masomohigh.view.admin.tableDataModels.ClassDataModel;
import com.sun.corba.se.spi.presentation.rmi.PresentationManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 1/10/2018.
 */
public class AllClasses {

    private AdministartionBlockTitle mAdministartionBlockTitle;

    private Label allClassesLabel;

    private VBox mainAllClassesVBox;

    private GridPane filterGridPane;
    private Label filterByLabel;
    private Label formsLabel;
    private Label streamsLabel;
    private Label yearLabel;
    private ComboBox<Integer> formsComboBox;
    private ComboBox<String> streamsComboBox;
    private ComboBox<Integer> yearComboBox;

    private TableView classesTable;
    private TableColumn nameColumn;
    private TableColumn formColumn;
    private TableColumn streamColumn;
    private TableColumn yearColumn;
    private TableColumn classTeacherColumn;

    public AllClasses() {
        mAdministartionBlockTitle = new AdministartionBlockTitle();

        allClassesLabel = new Label("All Classes");

        filterGridPane = new GridPane();
        filterByLabel = new Label("Filter by: ");
        formsLabel = new Label("Form");
        streamsLabel = new Label("Stream");
        yearLabel = new Label("Year");
        formsComboBox = new ComboBox<>();
        streamsComboBox = new ComboBox<>();
        yearComboBox = new ComboBox<>();

        filterGridPane.add(filterByLabel, 0, 0);
        filterGridPane.add(formsLabel, 1, 0);
        filterGridPane.add(streamsLabel, 2, 0);
        filterGridPane.add(yearLabel, 3, 0);
        filterGridPane.add(formsComboBox, 1, 1);
        filterGridPane.add(streamsComboBox, 2, 1);
        filterGridPane.add(yearComboBox, 3, 1);

        classesTable = new TableView();
        nameColumn = new TableColumn("Name");
        formColumn = new TableColumn("Form");
        streamColumn = new TableColumn("Stream");
        yearColumn = new TableColumn("Year");
        classTeacherColumn = new TableColumn("Class Teacher");
        classesTable.getColumns().setAll(nameColumn, formColumn, streamColumn, yearColumn, classTeacherColumn);

        mainAllClassesVBox = new VBox();
        mainAllClassesVBox.getChildren().addAll(mAdministartionBlockTitle.getTopAdminBarBorderPane(),
                allClassesLabel, filterGridPane, classesTable);

        populateView();
    }

    public AdministartionBlockTitle getAdministartionBlockTitle() {
        return mAdministartionBlockTitle;
    }

    public VBox getMainAllClassesVBox() {
        return mainAllClassesVBox;
    }

    public void populateView() {
        //populate combo boxes
        formsComboBox.setItems(FXCollections.observableArrayList(MainApp.FORMS));
        streamsComboBox.setItems(FXCollections.observableArrayList(MainApp.STREAMS));
        ObservableList<Integer> years = FXCollections.observableArrayList();
        for (int i = 2000; i <= 2050; i++)
            years.add(i);
        yearComboBox.setItems(years);
        yearComboBox.setValue(LocalDate.now().getYear());

        //populate table
        classesTable.setPlaceholder(new Label("Classes List is Empty"));

        List<Class> classes = ClassController.fetchAllClasses();
        ObservableList<ClassDataModel> classData = FXCollections.observableArrayList();

        for (Class mClass: classes) {
            String classTeacherString = "";
            try {
                Teacher classTeacher = mClass.getClassTeachers().get(mClass.getClassTeachers().size() - 1);
                classTeacherString = classTeacher.getFirstName() + " " + classTeacher.getMiddleName().charAt(0)
                    + " " + classTeacher.getLastName();
            } catch(NullPointerException e) {
                classTeacherString = "Null";
            } catch(ArrayIndexOutOfBoundsException e) {
                classTeacherString = "Null";
            }
            classData.add(
                    new ClassDataModel(mClass.getName(), String.valueOf(mClass.getForm()), mClass.getStream(),
                    String.valueOf(mClass.getClassYear()), classTeacherString)
            );
        }

        nameColumn.setCellValueFactory(new PropertyValueFactory<ClassDataModel, String>("name"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<ClassDataModel, Integer>("form"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<ClassDataModel, String>("stream"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<ClassDataModel, Integer>("year"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<ClassDataModel, String>("classTeacher"));

        classesTable.setItems(classData);

        classesTable.setRowFactory(new Callback<TableView, TableRow>() {
            @Override
            public TableRow call(TableView param) {
                TableRow<ClassDataModel> row = new TableRow<>();
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getClickCount() == 2 && !row.isEmpty()) {

                        }
                    }
                });
                return row;
            }
        });
    }
}

