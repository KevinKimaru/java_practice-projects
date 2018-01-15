package com.masomohigh.view.admin;

import com.masomohigh.view.MainApp;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;

/**
 * Created by Kevin Kimaru Chege on 12/22/2017.
 */
public class AdministrationLeftTree {
    private TreeView<Label> treeBarTeeView;
    private Label treeBarLabel;

    private TreeItem<Label> rootAdministrationTreeItem;
    private Label rootAdministrationLabel;

    private TreeItem<Label> manageAdministratorsTreeItem;
    private Label manageAdministratorsLabel;
    private TreeItem<Label> manageTeachersTreeItem;
    private Label manageTeachersLabel;
    private TreeItem<Label> manageHousesTreeItem;
    private Label manageHousesLabel;
    private TreeItem<Label> manageClassesTreeItem;
    private Label manageClassesLabel;
    private TreeItem<Label> manageExamsTreeItem;
    private Label manageExamsLabel;
    private TreeItem<Label> manageAboutTreeItem;
    private Label manageAboutLabel;
    private TreeItem<Label> homeTreeItem;
    private Label homeLabel;


    private TreeItem<Label> addAdminTreeItem;
    private Label addAdminLabel;
    private TreeItem<Label> viewAdminDetailsTreeItem;
    private Label viewAdminDetailsLabel;
    private TreeItem<Label> allAdminsTreeItem;
    private Label allAdminsLabel;

    private TreeItem<Label> addTeacherTreeItem;
    private Label addTeacherLabel;
    private TreeItem<Label> allTeachersTreeItem;
    private Label allTeachersLabel;

    private TreeItem<Label> generateClassesTreeItem;
    private Label generateClassesLabel;
    private TreeItem<Label> allClassesTreeItem;
    private Label allClassesLabel;

    public AdministrationLeftTree() {
        treeBarLabel = new Label();
        rootAdministrationLabel = new Label("Administration");
        manageAdministratorsLabel = new Label("Manage Administrators");
        manageTeachersLabel = new Label("Manage Teachers");
        manageHousesLabel = new Label("Manage Houses");
        manageClassesLabel = new Label("Manage Classes");
        manageExamsLabel = new Label("Manage Exams");
        manageAboutLabel = new Label("Manage About");
        homeLabel = new Label("Home");

        addAdminLabel = new Label("Add admin");
        viewAdminDetailsLabel = new Label("View admin");
        allAdminsLabel = new Label("All Admins");

        addTeacherLabel = new Label("Add Teacher");
        allTeachersLabel = new Label("All Teachers");

        generateClassesLabel = new Label("Generate Classes");
        allClassesLabel = new Label("All Classes");

        treeBarTeeView = new TreeView<Label>();
        treeBarTeeView.getStyleClass().add("navigationBar2");

        rootAdministrationTreeItem = new TreeItem<Label>(rootAdministrationLabel);
        rootAdministrationTreeItem.setExpanded(true);

        manageAdministratorsTreeItem = new TreeItem<Label>(manageAdministratorsLabel);
        manageTeachersTreeItem = new TreeItem<Label>(manageTeachersLabel);
        manageHousesTreeItem = new TreeItem<Label>(manageHousesLabel);
        manageClassesTreeItem = new TreeItem<Label>(manageClassesLabel);
        manageExamsTreeItem = new TreeItem<Label>(manageExamsLabel);
        manageAboutTreeItem = new TreeItem<Label>(manageAboutLabel);
        homeTreeItem = new TreeItem<Label>(homeLabel);

        addAdminTreeItem = new TreeItem<Label>(addAdminLabel);
        viewAdminDetailsTreeItem = new TreeItem<Label>(viewAdminDetailsLabel);
        allAdminsTreeItem = new TreeItem<>(allAdminsLabel);

        addTeacherTreeItem = new TreeItem<>(addTeacherLabel);
        allTeachersTreeItem = new TreeItem<>(allTeachersLabel);

        generateClassesTreeItem = new TreeItem<>(generateClassesLabel);
        allClassesTreeItem = new TreeItem<>(allClassesLabel);

        manageAdministratorsTreeItem.getChildren().addAll(addAdminTreeItem, viewAdminDetailsTreeItem, allAdminsTreeItem);

        manageTeachersTreeItem.getChildren().addAll(addTeacherTreeItem, allTeachersTreeItem);

        manageClassesTreeItem.getChildren().addAll(generateClassesTreeItem, allClassesTreeItem);

        rootAdministrationTreeItem.getChildren().addAll(manageAdministratorsTreeItem, manageTeachersTreeItem, manageHousesTreeItem,
                manageClassesTreeItem, manageExamsTreeItem, manageAboutTreeItem, homeTreeItem);
        treeBarTeeView.setRoot(rootAdministrationTreeItem);

        setBtnActions();
    }

    public TreeView<Label> getTreeBar() {
        return treeBarTeeView;
    }

    private void setBtnActions() {
        allAdminsLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                        MainApp.allViewsAdminAdmin.getViewAdmins().addDataToTable();
                        MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminAdmin.getViewAdmins().getViewAdminsVBox());
                }
            }
        });

        homeLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    MainApp.rootScene.setToHome();
                }
            }
        });

        addAdminLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminAdmin.getAddAdmin().getMainAdminSignUpVBox());
                }
            }
        });

        viewAdminDetailsLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminAdmin.getViewAdmin().getMainViewAdminVBox());
                }
            }
        });

        addTeacherLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminTeacher.getAddTeacher().getMainAdminSignUpVBox());
                }
            }
        });

        allTeachersLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    MainApp.allViewsAdminTeacher.getAllTeachers().addDataToTable();
                    MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminTeacher.getAllTeachers().getViewTeachersVBox());
                }
            }
        });

        generateClassesLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminClass.getAddClass().getMainAddClassVBox());
                }
            }
        });

        allClassesLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    MainApp.allViewsAdminClass.getAllClasses().populateView();
                    MainApp.rootScene.getMainView().setCenter(MainApp.allViewsAdminClass.getAllClasses().getMainAllClassesVBox());
                }
            }
        });
    }
}
