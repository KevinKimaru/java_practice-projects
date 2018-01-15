package com.masomohigh.controller;

import com.masomohigh.model.Address;
import com.masomohigh.model.Administrator;
import com.masomohigh.model.Teacher;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.masomohigh.view.MainApp.STAFFSTATUSES;
import static com.masomohigh.view.MainApp.entityManager;

/**
 * Created by Kevin Kimaru Chege on 1/2/2018.
 */
public class TeacherController {
    public static List<Teacher> fetchAllTeachers() {
        Query query = entityManager.createQuery("SELECT t From Teacher t");
        List<Teacher> teachers = query.getResultList();
        return teachers;
    }

    public static Teacher getTeacherDetails(int idNumber) {
        Teacher teacher = entityManager.find(Teacher.class, idNumber);
        return teacher;
    }

    private static void fetchAllTeachersInSubject(String subject) {
    }

    private static void fetchAllTeachersInForm(int form) {
    }

    private static void fetchAllTechersInClass(String className) {
    }

    private static void loginTeacher(int idNumber, String password) {
    }

    public static void createTeacher(int idNumber, String firstName, String middleName, String lastName,
                                     Date dateOfBirth, Date dateOfStart, String phoneNumber, String email, int postalNumber,
                                     int postalCode, String city, List<String> subjects) {
        entityManager.getTransaction().begin();
        Address address = new Address(postalNumber, postalCode, city);
        Teacher teacher = new Teacher(idNumber, firstName, middleName, lastName, String.valueOf(idNumber), dateOfBirth, phoneNumber, email,
                address, dateOfStart, STAFFSTATUSES[5]);
        teacher.getSubjects().addAll(subjects);
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
    }

    public static void updateTeacherDetails(int prevIdNumber, int idNumber, String firstName, String middleName,
                                            String lastName, Date dateOfBirth, Date dateOfStart, String phoneNumber,
                                            String email, int baltex, int postalCode, String city, String status,
                                            List<String> subjects) {
        entityManager.getTransaction().begin();
        Teacher teacher = entityManager.find(Teacher.class, prevIdNumber);
        teacher.setIdNumber(idNumber);
        teacher.setFirstName(firstName);
        teacher.setMiddleName(middleName);
        teacher.setLastName(lastName);
        teacher.setPhoneNumber(phoneNumber);
        teacher.setEmail(email);
        teacher.getAddress().setBaltex(baltex);
        teacher.getAddress().setPostalCode(postalCode);
        teacher.getAddress().setCity(city);
        teacher.setStatus(status);
        teacher.setDateOfBirth(dateOfBirth);
        teacher.setDateOfStart(dateOfStart);
        teacher.getSubjects().clear();
        teacher.getSubjects().addAll(subjects);
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
    }

    public static void addTeacherObligation(int idNumber, String obligation) {
        entityManager.getTransaction().begin();
        Teacher teacher = entityManager.find(Teacher.class, idNumber);
        try {
            teacher.getObligations().add(obligation);
        } catch (NullPointerException e) {
            System.out.println(e);
            List<String> obligations = new ArrayList<String>();
            obligations.add(obligation);
            teacher.setObligations(obligations);
        }
        try {
            teacher.getObligationsBackup().add(obligation);
        } catch (NullPointerException e) {
            System.out.println(e);
            List<String> obligations = new ArrayList<String>();
            obligations.add(obligation);
            teacher.setObligationsBackup(obligations);
        }
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
    }

    public static void updateTeacherObligation(int idNumber, int updatedObligationIndex, String oldObligationName,
                                                     String newObligationName) {
        entityManager.getTransaction().begin();
        Teacher teacher = entityManager.find(Teacher.class, idNumber);
        teacher.getObligations().set(updatedObligationIndex, newObligationName);

        int oblgIndex = 0;
        for (String oblg : teacher.getObligationsBackup()) {
            if (oblg.contentEquals(oldObligationName)) {
                teacher.getObligationsBackup().set(oblgIndex, newObligationName);
            }
            oblgIndex++;
        }
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
    }

    public static void removeTeacherObligation(int idNumber, int obligationIndex) {
        entityManager.getTransaction().begin();
        Teacher teacher = entityManager.find(Teacher.class, idNumber);
        teacher.getObligations().remove(obligationIndex);
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
    }

    private static void removeTeacherObligationFromBackup(int idNumber, int obligationPosition) {
    }

    public static void addTeacherAchievements(int idNumber, String achievement) {
        entityManager.getTransaction().begin();
        Teacher teacher = entityManager.find(Teacher.class, idNumber);
        try {
            teacher.getAchievements().add(achievement);
        } catch (NullPointerException e) {
            System.out.println(e);
            List<String> achievements = new ArrayList<String>();
            achievements.add(achievement);
            teacher.setAchievements(achievements);
        }
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
    }

    public static void removeTeacherAchievement(int idNumber, int achievementIndex) {
        entityManager.getTransaction().begin();
        Teacher teacher = entityManager.find(Teacher.class, idNumber);
        teacher.getAchievements().remove(achievementIndex);
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
    }

    public static void updateTeacherAchievement(int idNumber, int achievementIndex, String newAchievementName) {
        entityManager.getTransaction().begin();
        Teacher teacher = entityManager.find(Teacher.class, idNumber);
        teacher.getAchievements().set(achievementIndex, newAchievementName);
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
    }

    public static void addCurrentClassesTeaching(int idNumber, String classTeaching) {
        entityManager.getTransaction().begin();
        Teacher teacher = entityManager.find(Teacher.class, idNumber);
        try {
            teacher.getClassesTeaching().add(classTeaching);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage() + e.getCause());
            List<String> classesTeaching = new ArrayList<String>();
            classesTeaching.add(classTeaching);
            teacher.setClassesTeaching(classesTeaching);
        }
        try {
            teacher.getClassesTeachingBackup().add(classTeaching);
        } catch (NullPointerException e) {
            System.out.println(e);
            List<String> classesTeaching = new ArrayList<String>();
            classesTeaching.add(classTeaching);
            teacher.setClassesTeachingBackup(classesTeaching);
        }
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
    }

    public static void updateClassesTeaching (int idNumber, int updatedClassTeachingIndex, String oldClassTeaching,
                                                     String newClassTeaching) {
        entityManager.getTransaction().begin();
        Teacher teacher = entityManager.find(Teacher.class, idNumber);
        teacher.getClassesTeaching().set(updatedClassTeachingIndex, newClassTeaching);

        int classTeachingIndex = 0;
        for (String classTeaching : teacher.getClassesTeachingBackup()) {
            if (classTeaching.contentEquals(oldClassTeaching)) {
                teacher.getClassesTeachingBackup().set(classTeachingIndex, newClassTeaching);
            }
            classTeachingIndex++;
        }
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
    }

    public static void removeCurrentClassesTeaching(int idNumber, int classTeachingIndex) {
        entityManager.getTransaction().begin();
        Teacher teacher = entityManager.find(Teacher.class, idNumber);
        teacher.getClassesTeaching().remove(classTeachingIndex);
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
    }

    private static void removeClassesTeachingFromBackup(int classSubjectPosition) {
    }

    public static boolean deleteTeacher(int idNumber) {
        boolean deleted = false;
        entityManager.getTransaction().begin();
        Teacher teacher = entityManager.find(Teacher.class, idNumber);
        if (teacher != null) {
                entityManager.remove(teacher);
                deleted = true;
        } else {
            deleted = false;
        }
        entityManager.remove(teacher);
        entityManager.getTransaction().commit();
        return deleted;
    }
}
