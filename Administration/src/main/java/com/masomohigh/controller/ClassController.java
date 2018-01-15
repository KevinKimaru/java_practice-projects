package com.masomohigh.controller;

import com.masomohigh.model.Class;
import com.masomohigh.model.Student;
import com.masomohigh.model.Teacher;

import javax.persistence.Query;

import java.util.List;

import static com.masomohigh.view.MainApp.entityManager;

/**
 * Created by Kevin Kimaru Chege on 1/5/2018.
 */
public class ClassController {

    public static List<Class> fetchAllClasses() {
        Query query = entityManager.createQuery("SELECT c FROM Class c");
        return query.getResultList();
    }

    private static void fetchClassesInYear(int year) {}

    //name of class will be automtically created
    public static void generateClasses(List<String> streams, List<Integer> forms, int classYear) {
        entityManager.getTransaction().begin();
        for (int form: forms) {
            for (String stream: streams) {
                Class aClass = new Class(stream, form, classYear);
                entityManager.persist(aClass);
            }
        }
        entityManager.getTransaction().commit();
    }

    //add as teacher obligation
    private static void addClassTeacher(Teacher teacher) {}
    private static void removeClassTeacher(int tacherPosition) {}
    private static void addSubjectTeacher(String subject, Teacher teacher) {}
    private static void reomveSubjectTeacher(String subject, Teacher teacher) {}
    private static void addStudentToClass(Student student) {}
    private static void removeStudentFromClass(int studentFileNumber) {}
    private static void deleteClass(Class mClass) {}
}
