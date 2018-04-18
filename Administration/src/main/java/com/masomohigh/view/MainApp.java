package com.masomohigh.view;

import com.masomohigh.view.admin.administrator.AllViewsAdminAdmin;
import com.masomohigh.view.admin.mclass.AllViewsAdminClass;
import com.masomohigh.view.admin.mclass.ClassUtilities;
import com.masomohigh.view.admin.student.AllViewsAdminStudent;
import com.masomohigh.view.admin.student.StudentUtilities;
import com.masomohigh.view.admin.teacher.AllViewsAdminTeacher;
import com.masomohigh.view.admin.teacher.TeacherUtilities;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Kevin Kimaru Chege on 12/21/2017.
 */
public class MainApp extends Application {
    public static RootScene rootScene;
    public static AllViewsAdminAdmin allViewsAdminAdmin;
    public static AllViewsAdminTeacher allViewsAdminTeacher;
    public static AllViewsAdminClass allViewsAdminClass;
    public static AllViewsAdminStudent allViewsAdminStudent;

    public static final String[] STUDENTSTATUSES = new String[5];
    public static final String[] STAFFSTATUSES = new String[6];
    public static final Integer[] FORMS = new Integer[4];
    public static final String[] ALLSUBJECTS = new String[16];
    public static final String[] STREAMS = new String[7];

    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SchoolDB");
    public static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public void start(Stage primaryStage) throws Exception {
        createConstants();

        ClassUtilities.setAvailableClasses();
        TeacherUtilities.setAvailableTeacherPositions();
        TeacherUtilities.getAllTeachers();
        StudentUtilities.getAllStudents();

        allViewsAdminAdmin = new AllViewsAdminAdmin();
        allViewsAdminTeacher = new AllViewsAdminTeacher();
        allViewsAdminClass = new AllViewsAdminClass();
        allViewsAdminStudent = new AllViewsAdminStudent();

        rootScene = new RootScene();

        Scene mainScene = new Scene(rootScene.getMainView(), 900, 700);
        mainScene.getStylesheets().add("css/main.css");

        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void createConstants() {
        STUDENTSTATUSES[0] = "EXPELLED";
        STUDENTSTATUSES[1] = "TRANSFERRED";
        STUDENTSTATUSES[2] = "COMPLETED";
        STUDENTSTATUSES[3] = "ABSENT";
        STUDENTSTATUSES[4] = "PRESENT";

        STAFFSTATUSES[0] = "RETIRED";
        STAFFSTATUSES[1] = "RESIGNED";
        STAFFSTATUSES[2] = "SACKED";
        STAFFSTATUSES[3] = "TRANSFERRED";
        STAFFSTATUSES[4] = "ABSENT";
        STAFFSTATUSES[5] = "PRESENT";

        FORMS[0] = 1;
        FORMS[1] = 2;
        FORMS[2] = 3;
        FORMS[3] = 4;

        ALLSUBJECTS[0] = "MATHEMATICS";
        ALLSUBJECTS[1] = "ENGLISH";
        ALLSUBJECTS[2] = "KISWAHILI";
        ALLSUBJECTS[3] = "BIOLOGY";
        ALLSUBJECTS[4] = "CHEMISTRY";
        ALLSUBJECTS[5] = "PHYSICS";
        ALLSUBJECTS[6] = "GEOGRAPHY";
        ALLSUBJECTS[7] = "HISTORY";
        ALLSUBJECTS[8] = "CRE";
        ALLSUBJECTS[9] = "LIFE SKILLS";
        ALLSUBJECTS[10] = "COMPUTER";
        ALLSUBJECTS[11] = "BUSINESS STUDIES";
        ALLSUBJECTS[12] = "FRENCH";
        ALLSUBJECTS[13] = "GERMAN";
        ALLSUBJECTS[14] = "MUSIC";
        ALLSUBJECTS[15] = "AGRICULTURE";

        STREAMS[0] = "A";
        STREAMS[1] = "B";
        STREAMS[2] = "C";
        STREAMS[3] = "D";
        STREAMS[4] = "E";
        STREAMS[5] = "F";
        STREAMS[6] = "G";
    }
}
