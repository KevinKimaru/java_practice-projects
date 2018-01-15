package com.masomohigh.view.admin.teacher;

import com.masomohigh.controller.AdminController;
import com.masomohigh.controller.ClassController;
import com.masomohigh.controller.TeacherController;
import com.masomohigh.model.Class;
import com.masomohigh.model.Teacher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Kevin Kimaru Chege on 1/3/2018.
 */
public class TeacherUtilities {

    public static Teacher SELECTED_TEACHER = null;
    public static int SELECTED_TEACHER_ID = 0;
    public static Set<String> AVAILABLE_POSITIONS = new HashSet<>();
    public static List<Teacher> ALL_TEACHERS = new ArrayList<>();

    public static void setSelectedTeacher() {
        SELECTED_TEACHER = TeacherController.getTeacherDetails(SELECTED_TEACHER_ID);
        if (SELECTED_TEACHER != null) {
            SELECTED_TEACHER_ID = SELECTED_TEACHER.getIdNumber();
        }
    }

    public static List<Teacher> getAllTeachers() {
        ALL_TEACHERS = TeacherController.fetchAllTeachers();
        return TeacherController.fetchAllTeachers();
    }

    public static void setAvailableTeacherPositions() {
        List<Teacher> teachers = TeacherController.fetchAllTeachers();
        for (Teacher t: teachers) {
            AVAILABLE_POSITIONS.addAll(t.getObligationsBackup());
        }
    }

}
