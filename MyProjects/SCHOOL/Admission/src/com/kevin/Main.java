/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kevin;

import com.kevin.databases.Launch;
import com.kevin.views.StudentAccountRegistration;
import com.kevin.views.StudentRegistration;
import com.kevin.views.Utilities;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

import static com.kevin.views.Utilities.launchDb;

/**
 * @author Student
 */
public class Main extends Application {

    private static StudentRegistration studentRegistration;
    private static StudentAccountRegistration studentAccountRegistration;

    @Override
    public void start(Stage primaryStage) {
        Utilities.root.setPadding(new Insets(10.0));
        Utilities.root.setLeft(null);
        Utilities.root.setCenter(StudentRegistration.createStudentRegistration());

        Scene scene = new Scene(Utilities.root);
        primaryStage.setTitle("ADMISSION");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            Launch.createDatabase();
            launchDb.buildStudentsTable();
            launchDb.buildAdministratorsTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            try {
                launchDb.buildAdministratorsTable();
            } catch (SQLException se) {
                System.out.println(se.getMessage());
                se.printStackTrace();
            }
        }
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        if (launchDb.getCOn() != null)
            launchDb.getCOn().close();
    }
}
