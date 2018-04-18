package com.masomohigh.view.admin.student;

import com.masomohigh.controller.StudentController;
import com.masomohigh.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 2/3/2018.
 */
public class StudentUtilities {
    public static Student SELECTED_STUDENT = null;
    public static int SELECTED_STUDENT_ID = 0;
    public static List<Student> ALL_STUDENTS = new ArrayList<>();

    public static void setSelectedStudent() {
        SELECTED_STUDENT = StudentController.getStudentDetails(SELECTED_STUDENT_ID);
        if (SELECTED_STUDENT != null) {
            SELECTED_STUDENT_ID = SELECTED_STUDENT.getFileNumber();
        }
    }

    public static List<Student> getAllStudents() {
        ALL_STUDENTS = StudentController.fetchAllStudents();
        return ALL_STUDENTS;
    }

}
