package com.masomohigh.view;

import javafx.scene.layout.BorderPane;

/**
 * Created by Kevin Kimaru Chege on 12/22/2017.
 */
public class RootScene {

    private LeftMainMenu leftMainMenu;

    private About mAbout;
    private MainTopBar mainTopBar;


    private BorderPane mainView;

    public RootScene() {
        leftMainMenu = new LeftMainMenu();
        mAbout = new About();

        mainView = new BorderPane();

        mainTopBar = new MainTopBar();

        mainView.setLeft(leftMainMenu.getVBox());
        mainView.setTop(mainTopBar.getMainTopViewVBox());

        setToHome();
    }

    public BorderPane getMainView() {
        return mainView;
    }

    protected LeftMainMenu getLeftMainMenu() {
        return leftMainMenu;
    }

    public void setToAdministration() {
        mainView.setLeft(MainApp.allViewsAdminAdmin.getAdministrationLeftTree().getTreeBar());
        mainView.setCenter(MainApp.allViewsAdminAdmin.getAdministrationBlock().getAdmnistrationNlockMainView());
        leftMainMenu.setHighlitedButton(leftMainMenu.getAdminButton());
    }

    public void setToHome() {
        mainView.setLeft(leftMainMenu.getVBox());
        mainView.setCenter(mAbout.getAboutSchoolLabel());
        leftMainMenu.setHighlitedButton(leftMainMenu.getAboutButton());
    }
}
