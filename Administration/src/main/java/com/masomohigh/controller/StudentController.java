package com.masomohigh.controller;

import com.masomohigh.model.House;
import com.masomohigh.model.Student;
import com.masomohigh.view.MainApp;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

import static com.masomohigh.view.MainApp.entityManager;

/**
 * Created by Kevin Kimaru Chege on 2/3/2018.
 */
public class StudentController {

    //OPerations on students
    public static List<Student> fetchAllStudents() {
        Query query = entityManager.createQuery("SELECT s FROM Student s");
        return query.getResultList();
    }

    private static void fetchStudentsInClass(String className) {}
    private static void fetchStudentsInForm(int form) {}
    private static void fetchStudentsInHouse(String houseName) {}
    private static void fetchStudentsInStream(String stream) {}
    private static void fetchStudentsInSubject(String subject) {}
    private static void fetchStudentsInSubject(String...subjects) {}

    public static Student getStudentDetails(int fileNumber) {
        return entityManager.find(Student.class, fileNumber);
    }

    private static void updateStudentAllDetails(int fileNumber, String password, String firstName, String middleName, String lastName,
                                                Date dateOfBirth, House house, String stream, int form, Date dateOfAdmission,
                                                String status) {}
    /**update the students form and stream here*/
    private static void updateStudentsClass(String currentClass) {}
    private static void updateStudentClass(String currentClass) {}
    private static void updateStudentSubjects(int fileNumber) {}
    private static void updateStudentsStatusInForm(String form) {}

    public static void createStudent(int fileNumber, String firstName,
                                      String middleName, String lastName,
                                      Date dateOfBirth, Date dateOfAdmission) {
        entityManager.getTransaction().begin();
        Student student = new Student(fileNumber, String.valueOf(fileNumber), firstName, middleName, lastName,
                dateOfBirth, dateOfAdmission, MainApp.STUDENTSTATUSES[4]);
        entityManager.persist(student);
        entityManager.getTransaction().commit();
    }

    private static void loginStudent(int fileNumber, String password) {}
    private static void deleteStudent(int fileNumber) {}


}
