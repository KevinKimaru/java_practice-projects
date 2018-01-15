package com.masomohigh.view.admin.teacher;

import com.masomohigh.controller.TeacherController;
import com.masomohigh.model.Teacher;
import com.masomohigh.view.MainApp;
import com.masomohigh.view.admin.AdministartionBlockTitle;
import com.masomohigh.view.admin.mclass.ClassUtilities;
import com.masomohigh.view.admin.tableDataModels.AdministratorDataModel;
import com.masomohigh.view.admin.tableDataModels.TeacherDataModel;
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
 * Created by Kevin Kimaru Chege on 1/3/2018.
 */
public class AllTeachers {
    private AdministartionBlockTitle administartionBlockTitle;
    private Label allTeachersLabel;

    private VBox viewTeachersVBox;

    private GridPane filterGridPane;
    private Label filterLabel;
    private Label filterSubjectsLabel;
    private Label filterClassesLabel;
    private Label positionsLabel;
    private Label searchLabel;
    private ComboBox<String> searchComboBox;
    private ComboBox<String> subjectsComboBox;
    private ComboBox<String> classesComboBox;
    private ComboBox<String> positionsComboBox;
    private TextField searchTextField;
    private CheckBox subjectsCheckBox;
    private CheckBox classesCheckBox;
    private CheckBox positionsCheckBox;
    private CheckBox searchCheckBox;

    private TableView teachersTable;

    private TableColumn idNumberColumn;
    private TableColumn firstNameColumn;
    private TableColumn middleNameColumn;
    private TableColumn lastNameColumn;
    private TableColumn subjectsColumn;
    private TableColumn phoneNumberColumn;
    private TableColumn statusColumn;

    String tableSubjects = "";
    Set<Teacher> filteredTeachers;
    Set<Teacher> filteredTeachersBySubjects;
    Set<Teacher> filteredTeachersByClasses;
    Set<Teacher> filteredTeachersByPosition;
    String filterSubject = "";
    String filterClass = "";
    String filterPosition = "";

    public AllTeachers() {
        allTeachersLabel = new Label("ALL TEACHERS");

        viewTeachersVBox = new VBox();

        filterGridPane = new GridPane();
        filterLabel = new Label("Filter");
        filterSubjectsLabel = new Label("Subject");
        filterClassesLabel = new Label("Class Teaching");
        positionsLabel = new Label("Position");
        searchLabel = new Label("Search by");
        subjectsComboBox = new ComboBox<>(FXCollections.observableArrayList(MainApp.ALLSUBJECTS));
//        TODO: use ClassUtilities Instaed
//        TODO: update teachers avilable positions after every update removals and additions
        classesComboBox = new ComboBox<>(FXCollections.observableArrayList(ClassUtilities.AVAILABLE_CLASSES));
        positionsComboBox = new ComboBox<>(FXCollections.observableArrayList(TeacherUtilities.AVAILABLE_POSITIONS));
        searchComboBox = new ComboBox<>();
        searchComboBox.setItems(FXCollections.observableArrayList("ID Number", "First Name", "Middle Name", "Last Name"));
        searchTextField = new TextField();
        subjectsCheckBox = new CheckBox();
        classesCheckBox = new CheckBox();
        positionsCheckBox = new CheckBox();
        searchCheckBox = new CheckBox();

        filterGridPane.add(filterLabel, 0, 0);
        filterGridPane.add(filterSubjectsLabel, 1, 0);
        filterGridPane.add(filterClassesLabel, 3, 0);
        filterGridPane.add(positionsLabel, 5, 0);
        filterGridPane.add(subjectsComboBox, 1, 1);
        filterGridPane.add(subjectsCheckBox, 2, 1);
        filterGridPane.add(classesComboBox, 3, 1);
        filterGridPane.add(classesCheckBox, 4, 1);
        filterGridPane.add(positionsComboBox, 5, 1);
        filterGridPane.add(positionsCheckBox, 6, 1);
        filterGridPane.add(searchLabel, 0, 2);
        filterGridPane.add(searchComboBox, 1, 2);
        filterGridPane.add(searchTextField, 3, 2);
        filterGridPane.add(searchCheckBox, 4, 2);

        teachersTable = new TableView();

        idNumberColumn = new TableColumn("Id Number");
        firstNameColumn = new TableColumn("First Name");
        middleNameColumn = new TableColumn("Middle Name");
        lastNameColumn = new TableColumn("Last Name");
        subjectsColumn = new TableColumn("Subjects");
        phoneNumberColumn = new TableColumn("Phone Number");
        statusColumn = new TableColumn("Status");

        teachersTable.getColumns().setAll(idNumberColumn, firstNameColumn, middleNameColumn, lastNameColumn,
                subjectsColumn, phoneNumberColumn, statusColumn);

        administartionBlockTitle = new AdministartionBlockTitle();
        viewTeachersVBox.getChildren().addAll(administartionBlockTitle
                        .getTopAdminBarBorderPane(), allTeachersLabel,
                filterGridPane,
                teachersTable);

        beautifyView();
        addDataToTable();
        filterTeachers();
    }

    public VBox getViewTeachersVBox() {
        return viewTeachersVBox;
    }

    public AdministartionBlockTitle getAdministartionBlockTitle() {
        return administartionBlockTitle;
    }

    private void beautifyView() {
        viewTeachersVBox.getStyleClass().addAll("defaultBackground", "defaultFont", "childrenSpacing1");
        VBox.setMargin(administartionBlockTitle.getTopAdminBarBorderPane(), new Insets(10.0, 0, 0, 0));
        viewTeachersVBox.setAlignment(Pos.TOP_CENTER);
        allTeachersLabel.getStyleClass().addAll("title2");

        filterGridPane.setHgap(10.0);
        filterGridPane.setVgap(10.0);
        filterGridPane.setAlignment(Pos.CENTER);
    }

    public void addDataToTable() {
        teachersTable.setPlaceholder(new Label("Teachers List is Empty"));

        List<Teacher> teachers = TeacherController.fetchAllTeachers();
        ObservableList<TeacherDataModel> teacherData = FXCollections.observableArrayList();
        for (Teacher t : teachers) {
            String subjects = "";
            try {
                if (t.getSubjects().size() > 0)
                    subjects = t.getSubjects().toString();
                else subjects = "None";
            } catch (NullPointerException e) {
                subjects = "None";
            }
            teacherData.add(
                    new TeacherDataModel(String.valueOf(t.getIdNumber()), t.getFirstName(), t.getMiddleName(), t.getLastName(),
                            subjects, t.getPhoneNumber(), t.getStatus())
            );
        }

        idNumberColumn.setCellValueFactory(new PropertyValueFactory<TeacherDataModel, String>("idNumber"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<TeacherDataModel, String>("firstName"));
        middleNameColumn.setCellValueFactory(new PropertyValueFactory<TeacherDataModel, String>("middleName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<TeacherDataModel, String>("lastName"));
        subjectsColumn.setCellValueFactory(new PropertyValueFactory<TeacherDataModel, String>("subjects"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<TeacherDataModel, String>("phoneNumber"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<TeacherDataModel, String>("status"));

        teachersTable.setItems(teacherData);
        teachersTable.setRowFactory(new Callback<TableView, TableRow>() {
            @Override
            public TableRow call(TableView param) {
                TableRow<TeacherDataModel> row = new TableRow<>();
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getClickCount() == 2 && (!row.isEmpty())) {
                            try {
                                TeacherUtilities.SELECTED_TEACHER_ID = Integer.valueOf(((TeacherDataModel) row.getItem()).getIdNumber());
                                TeacherUtilities.SELECTED_TEACHER = TeacherController.getTeacherDetails(TeacherUtilities.SELECTED_TEACHER_ID);
                                MainApp.allViewsAdminTeacher.getTeacherDetails().populateView();
                                MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminTeacher.
                                        getTeacherDetails().getViewTeacherDetailsVBox());
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

    public void filterTeachers() {

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

        positionsComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filterPosition = newValue;
                mainFilter();
            }
        });

        positionsCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (positionsCheckBox.isSelected()) {
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

    private ObservableList<TeacherDataModel> filterUtility(Set<Teacher> filteredTeachers) {
        ObservableList<TeacherDataModel> teacherData = FXCollections.observableArrayList();
        for (Teacher t : filteredTeachers) {
            try {
                if (t.getSubjects().size() > 0)
                    tableSubjects = t.getSubjects().toString();
                else tableSubjects = "None";
            } catch (NullPointerException e) {
                tableSubjects = "None";
            }
            teacherData.add(
                    new TeacherDataModel(String.valueOf(t.getIdNumber()), t.getFirstName(),
                            t.getMiddleName(), t.getLastName(),
                            tableSubjects, t.getPhoneNumber(), t.getStatus())
            );
        }
        return teacherData;
    }

    private void mainFilter() {
        Set<Teacher> myFilter = new HashSet<>(TeacherUtilities.ALL_TEACHERS);

        if (classesCheckBox.isSelected() && !filterClass.isEmpty()) {
            myFilter = filterByClass(myFilter);
        }
        if (positionsCheckBox.isSelected() && !filterPosition.isEmpty()) {
            myFilter = filterByPosition(myFilter);
        }
        if (subjectsCheckBox.isSelected() && !filterSubject.isEmpty()) {
            myFilter = filterBySubject(myFilter);
        }
        if (searchCheckBox.isSelected() && searchComboBox.getValue()!= null && !searchTextField.getText().trim().isEmpty()) {
            myFilter = filterBySearching(myFilter);
        }
        ObservableList<TeacherDataModel> teacherData = filterUtility(myFilter);
        teachersTable.setItems(teacherData);
    }

    private Set<Teacher> filterBySubject(Set<Teacher> myFilter) {
        filteredTeachersBySubjects = new HashSet<>();
        for (Teacher t : myFilter) {
            for (String s : t.getSubjects()) {
                if (s.contentEquals(filterSubject)) {
                    filteredTeachersBySubjects.add(t);
                }
            }
        }
        myFilter.clear();
        myFilter.addAll(filteredTeachersBySubjects);
        return myFilter;
    }

    private Set<Teacher> filterByClass(Set<Teacher> myFilter) {
        filteredTeachersByClasses = new HashSet<>();
        for (Teacher t : myFilter) {
            for (String s : t.getClassesTeachingBackup()) {
                String mclass = s.substring(s.indexOf(": ") + 2, s.length());
                if (mclass.contentEquals(filterClass)) {
                    filteredTeachersByClasses.add(t);
                }
            }
        }
        myFilter.clear();
        myFilter.addAll(filteredTeachersByClasses);
        return myFilter;
    }

    private Set<Teacher> filterByPosition(Set<Teacher> myFilter) {
        filteredTeachersByPosition = new HashSet<>();
        for (Teacher t : myFilter) {
            for (String s : t.getObligations()) {
                if (s.contentEquals(filterPosition)) {
                    filteredTeachersByPosition.add(t);
                }
            }
        }
        myFilter.clear();
        myFilter.addAll(filteredTeachersByPosition);
        return myFilter;
    }

    private Set<Teacher> filterBySearching(Set<Teacher> myFilter) {
        Set<Teacher> filterBySearch = new HashSet<>();

        switch (searchTextField.getPromptText()) {
            case "First Name":
                for (Teacher t : myFilter) {
                    if (t.getFirstName().contentEquals(searchTextField.getText().trim())) {
                        filterBySearch.add(t);
                    }
                }
                break;
            case "Middle Name":
                for (Teacher t : myFilter) {
                    if (t.getMiddleName().contentEquals(searchTextField.getText().trim())) {
                        filterBySearch.add(t);
                    }
                }
                break;
            case "Last Name":
                for (Teacher t : myFilter) {
                    if (t.getLastName().contentEquals(searchTextField.getText().trim())) {
                        filterBySearch.add(t);
                    }
                }
                break;
            case "ID Number":
                for (Teacher t : myFilter) {
                    try {
                        if (t.getIdNumber() == Integer.valueOf(searchTextField.getText().trim())) {
                            filterBySearch.add(t);
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

