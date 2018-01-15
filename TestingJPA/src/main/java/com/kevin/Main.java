package com.kevin;

import com.kevin.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kevin Kimaru Chege on 12/2/2017.
 */
public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testingUser");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
//        User user = new User("Robert", "Kimani");
        User user = new User();
        user.setFirstName("Kevin");
        user.setLastName("Kimaru");
        user.setYearOfBirth(1999);
        Map<String, String> cities = new HashMap<String, String>();
        cities.put("Kenya", "Nairobi");
        cities.put("Kenya", "Nairobi");
        cities.put("Kenya", "Nairobi");
        cities.put("Uganda", "Kampala");
        user.setCities(cities);
        em.persist(user);
        em.getTransaction().commit();

        Query q = em.createQuery("SELECT u FROM User u");

        List<User> users = q.getResultList();
        for (User u: users) {
            System.out.println(u.getFirstName() + " " + u.getLastName());
            for (Map.Entry<String, String> e: u.getCities().entrySet())
                System.out.println(e.getKey() + " " + e.getValue());
        }
        em.close();
    }

}
