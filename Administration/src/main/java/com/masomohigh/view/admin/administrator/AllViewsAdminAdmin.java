package com.masomohigh.view.admin.administrator;

import com.masomohigh.view.About;
import com.masomohigh.view.LeftMainMenu;
import com.masomohigh.view.MainTopBar;
import com.masomohigh.view.admin.AdminChangePassword;
import com.masomohigh.view.admin.AdministrationBlock;
import com.masomohigh.view.admin.AdministrationLeftTree;
import com.masomohigh.view.admin.AdministrationLogin;

/**
 * Created by Kevin Kimaru Chege on 12/27/2017.
 */
public class AllViewsAdminAdmin {

    private About mAbout;
    private AddAdmin mAddAdmin;
    private AdministrationBlock mAdministrationBlock;
    private AdministrationLeftTree mAdministrationLeftTree;
    private AdministrationLogin mAdministrationLogin;
    private LeftMainMenu mLeftMainMenu;
    private MainTopBar mMainTopBar;
    private UpdateAdmin mUpdateAdmin;
    private UpdateAdminMore mUpdateAdminMore;
    private ViewAdminDetails mViewAdminDetails;
    private ViewAdmins mViewAdmins;
    private ViewAdmin mViewAdmin;
    private AdminChangePassword mAdminChangePassword;

    public AllViewsAdminAdmin() {
        mAbout = new About();
        mAddAdmin = new AddAdmin();
        mAdministrationBlock = new AdministrationBlock();
        mAdministrationLeftTree = new AdministrationLeftTree();
        mAdministrationLogin = new AdministrationLogin();
        mLeftMainMenu = new LeftMainMenu();
        mMainTopBar = new MainTopBar();
        mUpdateAdmin = new UpdateAdmin();
        mUpdateAdminMore = new UpdateAdminMore();
        mViewAdminDetails = new ViewAdminDetails();
        mViewAdmins = new ViewAdmins();
        mViewAdmin = new ViewAdmin();
        mAdminChangePassword = new AdminChangePassword();
    }

    public About getAbout() {
        return mAbout;
    }

    public AddAdmin getAddAdmin() {
        return mAddAdmin;
    }

    public AdministrationBlock getAdministrationBlock() {
        return mAdministrationBlock;
    }

    public AdministrationLeftTree getAdministrationLeftTree() {
        return mAdministrationLeftTree;
    }

    public AdministrationLogin getAdministrationLogin() {
        return mAdministrationLogin;
    }

    public LeftMainMenu getLeftMainMenu() {
        return mLeftMainMenu;
    }

    public MainTopBar getMainTopBar() {
        return mMainTopBar;
    }

    public UpdateAdmin getUpdateAdmin() {
        return mUpdateAdmin;
    }

    public UpdateAdminMore getUpdateAdminMore() {
        return mUpdateAdminMore;
    }

    public ViewAdminDetails getViewAdminDetails() {
        return mViewAdminDetails;
    }

    public ViewAdmins getViewAdmins() {
        return mViewAdmins;
    }

    public ViewAdmin getViewAdmin() {
        return mViewAdmin;
    }

    public AdminChangePassword getAdminChangePassword() {
        return mAdminChangePassword;
    }
}
