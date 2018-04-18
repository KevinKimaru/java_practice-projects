package com.masomohigh.view.admin.student;

/**
 * Created by Kevin Kimaru Chege on 2/3/2018.
 */
public class AllViewsAdminStudent {

    private AddStudent mAddStudent;
    private AllStudents mAllStudents;
    private StudentDetails mStudentDetails;

    public AllViewsAdminStudent() {
        mAddStudent = new AddStudent();
        mAllStudents = new AllStudents();
        mStudentDetails = new StudentDetails();
    }

    public AddStudent getAddStudent() {
        return mAddStudent;
    }

    public AllStudents getAllStudents() {
        return mAllStudents;
    }

    public StudentDetails getStudentDetails() {
        return mStudentDetails;
    }
}
