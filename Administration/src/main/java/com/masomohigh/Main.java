package com.masomohigh;

import com.masomohigh.model.*;
import com.masomohigh.model.Class;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Kevin Kimaru Chege on 12/10/2017.
 */
public class Main {
    public static final String[] STUDENTSTATUSES = new String[5];
    public static final String[] STAFFSTATUSES = new String[6];
    public static final int[] FORMS = new int[4];
    public static final String[] ALLSUBJECTS = new String[16];

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("SchoolDB");
    private static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        createConstants();
//        createInitialHouses();
        getHouses();
//        craeteInitialClasses();
        getClasses();
//        createInitialStudents();
        getStudents();
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

    }

    //Class(String stream, int form, int year)
    private static void craeteInitialClasses() {
        em.getTransaction().begin();
        Class A1 = new Class("A", 1, 2017);
        Class A2 = new Class("A", 2, 2017);
        Class A3 = new Class("A", 3, 2017);
        Class A4 = new Class("A", 4, 2017);
        Class B1 = new Class("B", 1, 2017);
        Class B2 = new Class("B", 2, 2017);
        Class B3 = new Class("B", 3, 2017);
        Class B4 = new Class("B", 4, 2017);
        em.persist(A1);
        em.persist(A2);
        em.persist(A3);
        em.persist(A4);
        em.persist(B1);
        em.persist(B2);
        em.persist(B3);
        em.persist(B4);
        em.getTransaction().commit();
    }

    private static void getClasses() {
        Query q = em.createQuery("SELECT c FROM Class c");
        List<Class> classes = q.getResultList();
        for(Class c: classes) {
            System.out.println(c.getClassId() + "==" + c.getName());
        }
    }

    private  static void createInitialHouses() {
        em.getTransaction().begin();
        House kibaki = new House("Kibaki", new Date(LocalDate.of(2000, 4, 20).toEpochDay()));
        House ngala = new House("Ngala", new Date(LocalDate.of(2002, 6, 14).toEpochDay()));
        em.persist(kibaki);
        em.persist(ngala);
        em.getTransaction().commit();
    }

    private static void getHouses() {
        Query q = em.createQuery("SELECT h FROM House h");
        List<House> houses = q.getResultList();
        for(House h: houses) {
            System.out.println(h.getId() + "==" + h.getName());
        }
    }

    private static void getStudents() {
        Query q = em.createQuery("SELECT s FROM Student s");
        List<Student> students = q.getResultList();
        for(Student s: students) {
            System.out.println(s.getFirstName() + " ->" + s.getLastName());
        }
    }


    //Operations on Guardian
    private static void fetchAllGuardians() {}
    //setStudent here
    private static void createGuardian(Student student, int idNumber, int password, String firstName, String middleName,
                                       String lastName, Date dateOfBirth, String email, Address address, String phoneNumber) {}
    private static void getGuardianAllDetails(int idNumber) {}
    private static void updateGuardianDetails(int idNumber, int password, String firstName, String middleName,
                                              String lastName, Date dateOfBirth, String email, Address address,
                                              String phoneNumber) {}
    private static void loginGuardian(int idNumber, String password) {}
    private static void deleteGuardian(int idNumber){}


    //Operations on class
    private static void fetchAllClasses() {}
    private static void fetchClassesInYear(int year) {}
    //name of class will be automtically created
    private static void createClass(String stream, int form, int classYear) {}
    //add as teacher obligation
    private static void addClassTeacher(Teacher teacher) {}
    private static void removeClassTeacher(int tacherPosition) {}
    private static void addSubjectTeacher(String subject, Teacher teacher) {}
    private static void reomveSubjectTeacher(String subject, Teacher teacher) {}
    private static void addStudentToClass(Student student) {}
    private static void removeStudentFromClass(int studentFileNumber) {}
    private static void deleteClass(Class mClass) {}


    //Operations on House
    private static void fetchAllHouses() {}
    private static void fetchAllHouseMasters() {}
    private static void getHouseAllDetails(String houseName) {}
    private static void updateHouseAllDetails(String name, Date openingDate) {}
    private static void createHouse(String name, Date openingDate) {}
    private static void addHouseMaster(int year, Teacher teacher) {}
    private static void addHouseCaptain(int year, Student student) {}
    private static void removeHouseCaptain(int year, Student student) {}
    private static void removeHouseMaster(int year, Student student) {}
    private static void addStudentToHouse(Student student, House house) {}
    private static void removeStudentFromHouse(Student student, House house) {}
    private static void deleteHouse(House house) {}

    //Operations on Exams
    private static void fetchAllExams() {}
    private static void fetchExamsInYear(int year) {}
    private static void fetchExamsInYearInTerm(int year, int term) {}
    private static void fetchExamsInYearInTermInForm(int year, int term, int form) {}
    private static void createExam(String name, Date examDate, int form, int year, int term) {}
    private static void getAllExamDetails(Exam exam) {}
    private static void updateExamDetails(Exam exam, String name, Date examDate, int form, int year, int term) {}


    //Operations on Record
    private static void createRecord(String subject, String marks, Teacher teacher, String remark,
                                     StudentRecords studentRecords, Exam exam) {}
    private static void updateRecordDetails(String subject, String marks, Teacher teacher, String remark,
                                            StudentRecords studentRecords, Exam exam) {}
    private static void getRecordDetails(Record record) {}


    //Operations on StudentRecords
    private static void createStudentRecord(Student student, Exam exam) {}
    private static void updateStudentREcordDetails(Student student, Exam exam) {}
    private static void addRecordToStudentRecords(Record record) {}
}
