package com.masomohigh.view.admin.administrator;

import com.masomohigh.controller.AdminController;
import com.masomohigh.model.Administrator;
import com.masomohigh.view.MainApp;
import com.masomohigh.view.AdminUtilities;
import com.masomohigh.view.admin.AdministartionBlockTitle;
import com.masomohigh.view.admin.tableDataModels.AdministratorDataModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 12/24/2017.
 */
public class ViewAdmins {
    private AdministartionBlockTitle administartionBlockTitle;
    private Label allAdminsLabel;

    private VBox viewAdminsVBox;
    private TableView adminsTable;

    private TableColumn idNumberColumn;
    private TableColumn firstNameColumn;
    private TableColumn middleNameColumn;
    private TableColumn lastNameColumn;
    private TableColumn obligationColumn;
    private TableColumn phoneNumberColumn;
    private TableColumn statusColumn;

    public ViewAdmins() {
        allAdminsLabel = new Label("ALL ADMINS");

        viewAdminsVBox = new VBox();
        adminsTable = new TableView();

        idNumberColumn = new TableColumn("Id Number");
        firstNameColumn = new TableColumn("First Name");
        middleNameColumn = new TableColumn("Middle Name");
        lastNameColumn = new TableColumn("Last Name");
        obligationColumn = new TableColumn("Obligations");
        phoneNumberColumn = new TableColumn("Phone Number");
        statusColumn = new TableColumn("Status");

        adminsTable.getColumns().setAll(idNumberColumn, firstNameColumn, middleNameColumn, lastNameColumn,
                obligationColumn, phoneNumberColumn, statusColumn);

        administartionBlockTitle = new AdministartionBlockTitle();
        viewAdminsVBox.getChildren().addAll(administartionBlockTitle
                        .getTopAdminBarBorderPane(), allAdminsLabel,
                adminsTable);

        beautifyView();
        addDataToTable();
    }

    public VBox getViewAdminsVBox() {
        return viewAdminsVBox;
    }

    public AdministartionBlockTitle getAdministartionBlockTitle() {
        return administartionBlockTitle;
    }

    private void beautifyView() {
        viewAdminsVBox.getStyleClass().addAll("defaultBackground", "defaultFont", "childrenSpacing1");
        VBox.setMargin(administartionBlockTitle.getTopAdminBarBorderPane(), new Insets(10.0, 0, 0, 0));
        viewAdminsVBox.setAlignment(Pos.TOP_CENTER);
        allAdminsLabel.getStyleClass().addAll("title2");
    }

    public void addDataToTable() {
        adminsTable.setPlaceholder(new Label("Administrators List is Empty"));

        List<Administrator> administrators = AdminController.fetchAllAdministrators();
        ObservableList<AdministratorDataModel> adminData = FXCollections.observableArrayList();
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
                                AdminUtilities.SELECTED_ADMINISTRATOR_ID = Integer.valueOf(((AdministratorDataModel)row.getItem()).getIdNumber());
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
            };

        });
    }
}
