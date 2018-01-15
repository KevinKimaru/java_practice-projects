package com.masomohigh.view.admin.administrator;

import com.masomohigh.controller.AdminController;
import com.masomohigh.model.Administrator;
import com.masomohigh.view.AdminUtilities;
import com.masomohigh.view.MainApp;
import com.masomohigh.view.admin.AdministartionBlockTitle;
import com.masomohigh.view.admin.tableDataModels.AdministratorDataModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 12/27/2017.
 */
public class ViewAdmin {
    private AdministartionBlockTitle administartionBlockTitle;

    private VBox mainViewAdminVBox;
    private TableView adminsTable;
    private HBox filterHBox;
    private Label viewAdminLabel;

    private ChoiceBox filterChoiceBox;
    private Label filterLabel;
    private TextField filterTextField;

    private TableColumn idNumberColumn;
    private TableColumn firstNameColumn;
    private TableColumn middleNameColumn;
    private TableColumn lastNameColumn;
    private TableColumn obligationColumn;
    private TableColumn phoneNumberColumn;
    private TableColumn statusColumn;

    private ObservableList<AdministratorDataModel> adminData = null;

    public ViewAdmin() {
        administartionBlockTitle = new AdministartionBlockTitle();
        viewAdminLabel = new Label("VIEW ADMIN");

        mainViewAdminVBox = new VBox();
        adminsTable = new TableView();
        filterHBox = new HBox();

        filterChoiceBox = new ChoiceBox();
        filterLabel = new Label("Search by:");
        filterTextField = new TextField();
        filterHBox.getChildren().addAll(filterLabel, filterChoiceBox, filterTextField);

        idNumberColumn = new TableColumn("Id Number");
        firstNameColumn = new TableColumn("First Name");
        middleNameColumn = new TableColumn("Middle Name");
        lastNameColumn = new TableColumn("Last Name");
        obligationColumn = new TableColumn("Obligations");
        phoneNumberColumn = new TableColumn("Phone Number");
        statusColumn = new TableColumn("Status");

        adminsTable.getColumns().setAll(idNumberColumn, firstNameColumn, middleNameColumn, lastNameColumn,
                obligationColumn, phoneNumberColumn, statusColumn);
        mainViewAdminVBox.getChildren().addAll(administartionBlockTitle.getTopAdminBarBorderPane(), viewAdminLabel,
                filterHBox, adminsTable);

        beautifyView();
        addDataToTable();
        setFilterOperation();
    }

    public VBox getMainViewAdminVBox() {
        return mainViewAdminVBox;
    }

    public AdministartionBlockTitle getAdministartionBlockTitle() {
        return administartionBlockTitle;
    }

    private void beautifyView() {
        mainViewAdminVBox.getStyleClass().addAll("defaultBackground", "defaultFont", "childrenSpacing2");
        mainViewAdminVBox.setAlignment(Pos.TOP_CENTER);
        filterHBox.setAlignment(Pos.CENTER);
        filterHBox.setSpacing(20);
        VBox.setMargin(administartionBlockTitle.getTopAdminBarBorderPane(), new Insets(10.0, 0, 0, 0));
        viewAdminLabel.getStyleClass().add("title2");
    }

    private void addDataToTable() {
        adminsTable.setPlaceholder(new Label("Administrators List is Empty"));

        List<Administrator> administrators = AdminController.fetchAllAdministrators();
        adminData = FXCollections.observableArrayList();
        for (Administrator a : administrators) {
            String obligation = "";
            try {
                if (a.getObligations().size() > 0)
                    obligation = a.getObligations().toString();
                else obligation = "None";
            } catch (NullPointerException e) {
                obligation = "None";
            }
            adminData.add(
                    new AdministratorDataModel(String.valueOf(a.getIdNumber()), a.getFirstName(), a.getMiddleName(), a.getLastName(),
                            a.getPhoneNumber(), obligation, a.getStatus())
            );
        }
        filterChoiceBox.setValue("All");

        idNumberColumn.setCellValueFactory(new PropertyValueFactory<AdministratorDataModel, String>("idNumber"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<AdministratorDataModel, String>("firstName"));
        middleNameColumn.setCellValueFactory(new PropertyValueFactory<AdministratorDataModel, String>("middleName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<AdministratorDataModel, String>("lastName"));
        obligationColumn.setCellValueFactory(new PropertyValueFactory<AdministratorDataModel, String>("obligations"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<AdministratorDataModel, String>("phoneNumber"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<AdministratorDataModel, String>("status"));

        adminsTable.setItems(adminData);
        adminsTable.setRowFactory(new Callback<TableView, TableRow>() {
            @Override
            public TableRow call(TableView param) {
                TableRow<AdministratorDataModel> row = new TableRow<>();
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getClickCount() == 2 && (!row.isEmpty())) {
                            try {
                                AdminUtilities.SELECTED_ADMINISTRATOR_ID = Integer.valueOf(((AdministratorDataModel) row.getItem()).getIdNumber());
                                AdminUtilities.SELECTED_ADMINISTRATOR = AdminController.getAdministratorDetails(AdminUtilities.SELECTED_ADMINISTRATOR_ID);
                                MainApp.allViewsAdminAdmin.getViewAdminDetails().populateView();
                                MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminAdmin.getViewAdminDetails().getViewAdminDetailsVBox());
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                });
                return row;
            }

            ;

        });
    }

    private void setFilterOperation() {
        List<Administrator> administrators = AdminUtilities.getAllAdministrators();

        filterChoiceBox.setItems(FXCollections.observableArrayList("All", "Id Number", "First Name",
                "Middle Name", "Last Name", "Position"));
        filterChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                adminData.clear();
                adminsTable.setItems(adminData);
                filterTextField.clear();
                filterTextField.setPromptText(newValue.toString());
            }
        });

        filterTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    adminData.clear();
                    adminsTable.setItems(adminData);
                    for (Administrator a : administrators) {
                        String obligation = "";
                        try {
                            if (a.getObligations().size() > 0)
                                obligation = a.getObligations().toString();
                            else obligation = "None";
                        } catch (NullPointerException e) {
                            obligation = "None";
                        }

                        if (filterTextField.getPromptText().contentEquals("All")) {
                            adminData.add(
                                    new AdministratorDataModel(String.valueOf(a.getIdNumber()), a.getFirstName(),
                                            a.getMiddleName(), a.getLastName(),
                                            a.getPhoneNumber(), obligation, a.getStatus())
                            );
                        }

                        if (!filterTextField.getText().isEmpty()) {
                            if (filterTextField.getPromptText().contentEquals("Id Number")) {
                                try {
                                    if (a.getIdNumber() == Integer.valueOf(filterTextField.getText().trim())) {
                                        adminData.add(
                                                new AdministratorDataModel(String.valueOf(a.getIdNumber()), a.getFirstName(),
                                                        a.getMiddleName(), a.getLastName(),
                                                        a.getPhoneNumber(), obligation, a.getStatus())
                                        );
                                    }
                                } catch (NumberFormatException e) {
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setContentText("Wrong ID Number format.");
                                    alert.showAndWait();
                                }
                            } else if (filterTextField.getPromptText().contentEquals("First Name")) {
                                if (a.getFirstName().contentEquals(filterTextField.getText().trim())) {
                                    adminData.add(
                                            new AdministratorDataModel(String.valueOf(a.getIdNumber()), a.getFirstName(),
                                                    a.getMiddleName(), a.getLastName(),
                                                    a.getPhoneNumber(), obligation, a.getStatus())
                                    );
                                }
                            } else if (filterTextField.getPromptText().contentEquals("Middle Name")) {
                                if (a.getMiddleName().contentEquals(filterTextField.getText().trim())) {
                                    adminData.add(
                                            new AdministratorDataModel(String.valueOf(a.getIdNumber()), a.getFirstName(),
                                                    a.getMiddleName(), a.getLastName(),
                                                    a.getPhoneNumber(), obligation, a.getStatus())
                                    );
                                }
                            } else if (filterTextField.getPromptText().contentEquals("Last Name")) {
                                if (a.getLastName().contentEquals(filterTextField.getText().trim())) {
                                    adminData.add(
                                            new AdministratorDataModel(String.valueOf(a.getIdNumber()), a.getFirstName(),
                                                    a.getMiddleName(), a.getLastName(),
                                                    a.getPhoneNumber(), obligation, a.getStatus())
                                    );
                                }
                            } else if (filterTextField.getPromptText().contentEquals("Position")) {
                                for (String obl : a.getObligations()) {
                                    if (obl.contentEquals(filterTextField.getText().trim())) {
                                        adminData.add(
                                                new AdministratorDataModel(String.valueOf(a.getIdNumber()), a.getFirstName(),
                                                        a.getMiddleName(), a.getLastName(),
                                                        a.getPhoneNumber(), obligation, a.getStatus())
                                        );
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}
