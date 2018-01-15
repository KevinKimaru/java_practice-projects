package com.kevin.ejb;

import com.kevin.model.User;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 12/2/2017.
 */
@Stateful
public class ModelBean {
//    @PersistenceContext
//    private EntityManager em;

    private int no = 2;

    @PostConstruct
   public void createUser() {
//       User u = new User("Kevin", "Kimaru", 1999);
//       em.persist(u);
   }

//    public List getUsers() {
////        return em.createQuery("SELECT u User u").getResultList();
//    }

    public int getNumber() {
        return no++;
    }
}
