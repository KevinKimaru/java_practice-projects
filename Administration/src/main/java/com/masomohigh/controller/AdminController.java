package com.masomohigh.controller;

import com.masomohigh.model.Address;
import com.masomohigh.model.Administrator;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.masomohigh.view.MainApp.STAFFSTATUSES;
import static com.masomohigh.view.MainApp.entityManager;

/**
 * Created by Kevin Kimaru Chege on 12/28/2017.
 */
public class AdminController {

    public static List<Administrator> fetchAllAdministrators() {
        Query query = entityManager.createQuery("SELECT a FROM Administrator a");
        List<Administrator> administrators = query.getResultList();
        return administrators;
    }

    public static Administrator getAdministratorDetails(int idNumber) {
        Administrator administrator = entityManager.find(Administrator.class, idNumber);
        return administrator;
    }

    public static void updateAllAdministratorDetails(int prevIdNumber, int idNumber, String firstName, String middleName,
                                                     String lastName, Date dateOfBirth, Date dateOfStart, String phoneNumber, String email,
                                                     int baltex, int postalCode, String city, String status) {
        entityManager.getTransaction().begin();
        Administrator administrator = entityManager.find(Administrator.class, prevIdNumber);
        administrator.setIdNumber(idNumber);
        administrator.setFirstName(firstName);
        administrator.setMiddleName(middleName);
        administrator.setLastName(lastName);
        administrator.setPhoneNumber(phoneNumber);
        administrator.setEmail(email);
        administrator.getAddress().setBaltex(baltex);
        administrator.getAddress().setPostalCode(postalCode);
        administrator.getAddress().setCity(city);
        administrator.setStatus(status);
        administrator.setDateOfBirth(dateOfBirth);
        administrator.setDateOfStart(dateOfStart);
        entityManager.persist(administrator);
        entityManager.getTransaction().commit();
    }

    public static void createAdministrator(int idNumber, String firstName, String middleName, String lastName,
                                           Date dateOfBirth, Date dateOfStart, String phoneNumber, String email, int baltex,
                                           int postalCode, String city, String obligation) {
        entityManager.getTransaction().begin();
        Address address = new Address(baltex, postalCode, city);
        //at first password is id number
        Administrator administrator = new Administrator(idNumber, firstName, middleName, lastName, String.valueOf(idNumber),
                dateOfBirth, phoneNumber, email, address, dateOfStart, STAFFSTATUSES[5]);
        //add 1 obligation here as well as in backup
        administrator.getObligations().add(obligation);
        administrator.getObligationsBackup().add(obligation);
        entityManager.persist(administrator);
        entityManager.getTransaction().commit();
    }

    public static Administrator loginAdministrator(int idNumber, String password) {
        try {
            Administrator administrator = entityManager.find(Administrator.class, idNumber);
            if ((administrator.getPassword()).contentEquals(password)) return administrator;
            else return null;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //remember to update the backups too
    public static void addAdministratorsObligation(int idNumber, String obligation) {
        entityManager.getTransaction().begin();
        Administrator administrator = entityManager.find(Administrator.class, idNumber);
        try {
            administrator.getObligations().add(obligation);
        } catch (NullPointerException e) {
            System.out.println(e);
            List<String> obligations = new ArrayList<String>();
            obligations.add(obligation);
            administrator.setObligations(obligations);
        }
        try {
            administrator.getObligationsBackup().add(obligation);
        } catch (NullPointerException e) {
            System.out.println(e);
            List<String> obligations = new ArrayList<String>();
            obligations.add(obligation);
            administrator.setObligationsBackup(obligations);
        }
        entityManager.persist(administrator);
        entityManager.getTransaction().commit();
    }

    public static void updateAdministratorObligation(int idNumber, int updatedObligationIndex, String oldObligationName,
                                                     String newObligationName) {
        entityManager.getTransaction().begin();
        Administrator administrator = entityManager.find(Administrator.class, idNumber);
        administrator.getObligations().set(updatedObligationIndex, newObligationName);

        int oblgIndex = 0;
        for (String oblg : administrator.getObligationsBackup()) {
            if (oblg.contentEquals(oldObligationName)) {
                administrator.getObligationsBackup().set(oblgIndex, newObligationName);
            }
            oblgIndex++;
        }
        entityManager.persist(administrator);
        entityManager.getTransaction().commit();
    }

    //this wont update the backup
    public static void removeAdministratorsObligation(int idNumber, int obligationIndex) {
        entityManager.getTransaction().begin();
        Administrator administrator = entityManager.find(Administrator.class, idNumber);
        administrator.getObligations().remove(obligationIndex);
        entityManager.persist(administrator);
        entityManager.getTransaction().commit();
    }

    public static void removeAdministratorsObligationFromBackup(int idNumber, int obligationPosition) {
    }

    public static void addAdministratorAchievements(int idNumber, String achievement) {
        entityManager.getTransaction().begin();
        Administrator administrator = entityManager.find(Administrator.class, idNumber);
        try {
            administrator.getAchievements().add(achievement);
        } catch (NullPointerException e) {
            System.out.println(e);
            List<String> achievements = new ArrayList<String>();
            achievements.add(achievement);
            administrator.setAchievements(achievements);
        }
        entityManager.persist(administrator);
        entityManager.getTransaction().commit();
    }

    public static void removeAdministratorAchievement(int idNumber, int achievementIndex) {
        entityManager.getTransaction().begin();
        Administrator administrator = entityManager.find(Administrator.class, idNumber);
        administrator.getAchievements().remove(achievementIndex);
        entityManager.persist(administrator);
        entityManager.getTransaction().commit();
    }

    public static void updateAdministratorAchievement(int idNumber, int achievementIndex, String newAchievementName) {
        entityManager.getTransaction().begin();
        Administrator administrator = entityManager.find(Administrator.class, idNumber);
        administrator.getAchievements().set(achievementIndex, newAchievementName);
        entityManager.persist(administrator);
        entityManager.getTransaction().commit();
    }

    public static boolean deleteAdministrator(int idNumber) {
        boolean deleted = false;
        entityManager.getTransaction().begin();
        Administrator administrator = entityManager.find(Administrator.class, idNumber);
        if (administrator != null) {
            entityManager.remove(administrator);
            deleted = true;
        }
        entityManager.getTransaction().commit();
        return deleted;
    }

    public static boolean changePassword(int idNumber, String oldPass, String newPass) {
        boolean changed = false;
        entityManager.getTransaction().begin();
        Administrator administrator = entityManager.find(Administrator.class, idNumber);
        if (administrator.getPassword().contentEquals(oldPass)) {
            administrator.setPassword(newPass);
            changed = true;
        }
        entityManager.persist(administrator);
        entityManager.getTransaction().commit();
        return changed;
    }
}
