package com.masomohigh.view.admin.teacher;

import com.masomohigh.view.admin.administrator.AllViewsAdminAdmin;

/**
 * Created by Kevin Kimaru Chege on 1/2/2018.
 */
public class AllViewsAdminTeacher {

    private AddTeacher mAddTeacher;
    private AllTeachers mAllTeachers;
    private TeacherDetails mTeacherDetails;
    private UpdateTeacher mUpdateTeacher;
    private UpdateTeacherClasses mUpdateTeacherClasses;
    private UpdateTeacherAchievements mUpdateTeacherAchievements;

    public AllViewsAdminTeacher() {
        mAddTeacher = new AddTeacher();
        mAllTeachers = new AllTeachers();
        mTeacherDetails = new TeacherDetails();
        mUpdateTeacher = new UpdateTeacher();
        mUpdateTeacherClasses = new UpdateTeacherClasses();
        mUpdateTeacherAchievements = new UpdateTeacherAchievements();
    }

    public AddTeacher getAddTeacher() {
        return mAddTeacher;
    }

    public AllTeachers getAllTeachers() {
        return mAllTeachers;
    }

    public TeacherDetails getTeacherDetails() {
        return mTeacherDetails;
    }

    public UpdateTeacher getUpdateTeacher() {
        return mUpdateTeacher;
    }

    public UpdateTeacherClasses getUpdateTeacherClasses() {
        return mUpdateTeacherClasses;
    }

    public UpdateTeacherAchievements getUpdateTeacherAchievements() {
        return mUpdateTeacherAchievements;
    }
}
