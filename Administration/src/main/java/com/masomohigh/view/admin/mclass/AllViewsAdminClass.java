package com.masomohigh.view.admin.mclass;

/**
 * Created by Kevin Kimaru Chege on 1/10/2018.
 */
public class AllViewsAdminClass {
    private AddClass mAddClass;
    private AllClasses mAllClasses;

    public AllViewsAdminClass() {
        mAddClass = new AddClass();
        mAllClasses = new AllClasses();
    }

    public AddClass getAddClass() {
        return mAddClass;
    }

    public AllClasses getAllClasses() {
        return mAllClasses;
    }
}
