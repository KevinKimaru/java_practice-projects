package com.masomohigh.view.admin.student;

import com.masomohigh.controller.StudentController;
import com.masomohigh.model.Class;
import com.masomohigh.model.Student;
import com.masomohigh.view.MainApp;
import com.masomohigh.view.admin.AdministartionBlockTitle;
import com.masomohigh.view.admin.mclass.ClassUtilities;
import com.masomohigh.view.admin.tableDataModels.StudentDataModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Kevin Kimaru Chege on 2/3/2018.
 */
public class AllStudents {
    private AdministartionBlockTitle mAdministartionBlockTitle;

    private Label allStudentsLabel;

    private VBox viewStudentsVBox;

    private GridPane filterGridPane;
    private Label filterLabel;
    private Label filterSubjectsLabel;
    private Label filterClassesLabel;
    private Label searchLabel;
    private ComboBox<String> searchComboBox;
    private ComboBox<String> subjectsComboBox;
    private ComboBox<String> classesComboBox;
    private TextField searchTextField;
    private CheckBox subjectsCheckBox;
    private CheckBox classesCheckBox;
    private CheckBox searchCheckBox;

    private TableView studentsTable;

    private TableColumn fileNumberColumn;
    private TableColumn firstNameColumn;
    private TableColumn middleNameColumn;
    private TableColumn lastNameColumn;
    private TableColumn subjectsColumn;
    private TableColumn statusColumn;

    String tableSubjects = "";
    Set<Student> filteredStudentsBySubject;
    Set<Student> filteredStudentsByClass;
    String filterSubject = "";
    String filterClass = "";

    public AllStudents() {
        mAdministartionBlockTitle = new AdministartionBlockTitle();

        allStudentsLabel = new Label("ALL STUDENTS");

        viewStudentsVBox = new VBox();

        filterGridPane = new GridPane();
        filterLabel = new Label("Filter");
        filterSubjectsLabel = new Label("Subject");
        filterClassesLabel = new Label("Class");
        searchLabel = new Label("Search by");
        subjectsComboBox = new ComboBox<>(FXCollections.observableArrayList(MainApp.ALLSUBJECTS));
//        TODO: use ClassUtilities Instaed
//        TODO: update teachers avilable positions after every update removals and additions
        classesComboBox = new ComboBox<>(FXCollections.observableArrayList(ClassUtilities.AVAILABLE_CLASSES));
        searchComboBox = new ComboBox<>();
        searchComboBox.setItems(FXCollections.observableArrayList("File Number", "First Name", "Middle Name", "Last Name"));
        searchTextField = new TextField();
        subjectsCheckBox = new CheckBox();
        classesCheckBox = new CheckBox();
        searchCheckBox = new CheckBox();

        filterGridPane.add(filterLabel, 0, 0);
        filterGridPane.add(filterSubjectsLabel, 1, 0);
        filterGridPane.add(filterClassesLabel, 3, 0);
        filterGridPane.add(subjectsComboBox, 1, 1);
        filterGridPane.add(subjectsCheckBox, 2, 1);
        filterGridPane.add(classesComboBox, 3, 1);
        filterGridPane.add(classesCheckBox, 4, 1);
        filterGridPane.add(searchLabel, 0, 2);
        filterGridPane.add(searchComboBox, 1, 2);
        filterGridPane.add(searchTextField, 3, 2);
        filterGridPane.add(searchCheckBox, 4, 2);

        studentsTable = new TableView();

        fileNumberColumn = new TableColumn("File Number");
        firstNameColumn = new TableColumn("First Name");
        middleNameColumn = new TableColumn("Middle Name");
        lastNameColumn = new TableColumn("Last Name");
        subjectsColumn = new TableColumn("Subjects");
        statusColumn = new TableColumn("Status");

        studentsTable.getColumns().setAll(fileNumberColumn, firstNameColumn, middleNameColumn, lastNameColumn,
                subjectsColumn, statusColumn);

        viewStudentsVBox.getChildren().addAll(mAdministartionBlockTitle
                        .getTopAdminBarBorderPane(), allStudentsLabel,
                filterGridPane,
                studentsTable);

        beautifyView();
        addDataToTable();
        filterStudents();
    }

    public AdministartionBlockTitle getAdministartionBlockTitle() {
        return mAdministartionBlockTitle;
    }

    public VBox getViewStudentsVBox() {
        return viewStudentsVBox;
    }

    private void beautifyView() {
        viewStudentsVBox.getStyleClass().addAll("defaultBackground", "defaultFont", "childrenSpacing1");
        VBox.setMargin(mAdministartionBlockTitle.getTopAdminBarBorderPane(), new Insets(10.0, 0, 0, 0));
        viewStudentsVBox.setAlignment(Pos.TOP_CENTER);
        allStudentsLabel.getStyleClass().addAll("title2");

        filterGridPane.setHgap(10.0);
        filterGridPane.setVgap(10.0);
        filterGridPane.setAlignment(Pos.CENTER);
    }

    public void addDataToTable() {
        studentsTable.setPlaceholder(new Label("Students List is Empty"));

        List<Student> students = StudentUtilities.ALL_STUDENTS;
        ObservableList<StudentDataModel> studentData = FXCollections.observableArrayList();
        for (Student s : students) {
            String subjects = "";
            try {
                if (s.getSubjects().size() > 0)
                    subjects = s.getSubjects().toString();
                else subjects = "None";
            } catch (NullPointerException e) {
                subjects = "None";
            }
            studentData.add(
                    new StudentDataModel(String.valueOf(s.getFileNumber()), s.getFirstName(), s.getMiddleName(),
                            s.getLastName(), subjects, s.getStatus())
            );
        }

        fileNumberColumn.setCellValueFactory(new PropertyValueFactory<StudentDataModel, String>("fileNumber"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<StudentDataModel, String>("firstName"));
        middleNameColumn.setCellValueFactory(new PropertyValueFactory<StudentDataModel, String>("middleName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<StudentDataModel, String>("lastName"));
        subjectsColumn.setCellValueFactory(new PropertyValueFactory<StudentDataModel, String>("subjects"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<StudentDataModel, String>("status"));

        studentsTable.setItems(studentData);
        studentsTable.setRowFactory(new Callback<TableView, TableRow>() {
            @Override
            public TableRow call(TableView param) {
                TableRow<StudentDataModel> row = new TableRow<>();
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getClickCount() == 2 && (!row.isEmpty())) {
                            try {
                                StudentUtilities.SELECTED_STUDENT_ID = Integer.valueOf(((StudentDataModel) row.getItem()).getFileNumber());
                                StudentUtilities.SELECTED_STUDENT = StudentController.getStudentDetails(StudentUtilities.SELECTED_STUDENT_ID);
                                MainApp.allViewsAdminStudent.getStudentDetails().populateView();
                                MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminStudent.
                                        getStudentDetails().getViewStudentsDetailsVBox());
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                });
                return row;
            }

        });
    }

    public void filterStudents() {

        subjectsComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filterSubject = newValue;
                mainFilter();
            }
        });

        subjectsCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (subjectsCheckBox.isSelected()) {
                    mainFilter();
                } else {
                    mainFilter();
                }
            }
        });

        classesComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filterClass = newValue;
                mainFilter();
            }
        });

        classesCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (classesCheckBox.isSelected()) {
                    mainFilter();
                } else {
                    mainFilter();
                }
            }
        });

        searchCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (searchCheckBox.isSelected()) {
                    mainFilter();
                } else {
                    mainFilter();
                }
            }
        });

        searchComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                searchTextField.setPromptText(newValue);
                if (searchCheckBox.isSelected()) {
                    mainFilter();
                } else {
                    mainFilter();
                }
            }
        });

        searchTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (searchCheckBox.isSelected()) {
                    mainFilter();
                } else {
                    mainFilter();
                }
            }
        });

    }

    private ObservableList<StudentDataModel> filterUtility(Set<Student> filteredStudents) {
        ObservableList<StudentDataModel> studentData = FXCollections.observableArrayList();
        for (Student s : filteredStudents) {
            try {
                if (s.getSubjects().size() > 0)
                    tableSubjects = s.getSubjects().toString();
                else tableSubjects = "None";
            } catch (NullPointerException e) {
                tableSubjects = "None";
            }
            studentData.add(
                    new StudentDataModel(String.valueOf(s.getFileNumber()), s.getFirstName(),
                            s.getMiddleName(), s.getLastName(),
                            tableSubjects, s.getStatus())
            );
        }
        return studentData;
    }

    private void mainFilter() {
        Set<Student> myFilter = new HashSet<>(StudentUtilities.ALL_STUDENTS);

        if (classesCheckBox.isSelected() && !filterClass.isEmpty()) {
            myFilter = filterByClass(myFilter);
        }
        if (subjectsCheckBox.isSelected() && !filterSubject.isEmpty()) {
            myFilter = filterBySubject(myFilter);
        }
        if (searchCheckBox.isSelected() && searchComboBox.getValue()!= null && !searchTextField.getText().trim().isEmpty()) {
            myFilter = filterBySearching(myFilter);
        }
        ObservableList<StudentDataModel> studentData = filterUtility(myFilter);
        studentsTable.setItems(studentData);
    }

    private Set<Student> filterBySubject(Set<Student> myFilter) {
        filteredStudentsBySubject = new HashSet<>();
        for (Student student : myFilter) {
            for (String s : student.getSubjects()) {
                if (s.contentEquals(filterSubject)) {
                    filteredStudentsBySubject.add(student);
                }
            }
        }
        myFilter.clear();
        myFilter.addAll(filteredStudentsBySubject);
        return myFilter;
    }

    private Set<Student> filterByClass(Set<Student> myFilter) {
        filteredStudentsByClass = new HashSet<>();
        for (Student s : myFilter) {
            for (Class c : s.getClasses()) {
                if (c.getName().contentEquals(filterClass)) {
                    filteredStudentsByClass.add(s);
                }
            }
        }
        myFilter.clear();
        myFilter.addAll(filteredStudentsByClass);
        return myFilter;
    }

    private Set<Student> filterBySearching(Set<Student> myFilter) {
        Set<Student> filterBySearch = new HashSet<>();

        switch (searchTextField.getPromptText()) {
            case "First Name":
                for (Student s : myFilter) {
                    if (s.getFirstName().contentEquals(searchTextField.getText().trim())) {
                        filterBySearch.add(s);
                    }
                }
                break;
            case "Middle Name":
                for (Student s : myFilter) {
                    if (s.getMiddleName().contentEquals(searchTextField.getText().trim())) {
                        filterBySearch.add(s);
                    }
                }
                break;
            case "Last Name":
                for (Student s : myFilter) {
                    if (s.getLastName().contentEquals(searchTextField.getText().trim())) {
                        filterBySearch.add(s);
                    }
                }
                break;
            case "File Number":
                for (Student s : myFilter) {
                    try {
                        if (s.getFileNumber() == Integer.valueOf(searchTextField.getText().trim())) {
                            filterBySearch.add(s);
                        }
                    } catch(Exception e) {

                    }
                }
                break;
        }

        myFilter.clear();
        myFilter.addAll(filterBySearch);
        return myFilter;
    }
}
