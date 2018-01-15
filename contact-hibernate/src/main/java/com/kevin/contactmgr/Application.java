package com.kevin.contactmgr;

import com.kevin.contactmgr.model.Contact;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.List;


/**
 * Created by Kevin Kimaru Chege on 8/5/2017.
 */
public class Application {
    //Hold a reusable reference to a sessionFactory since (we need only one)
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        //create a standard service registry
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void main(String[] args) {
        Contact contact = new Contact.ContactBuilder("Kevin", "Kimaru")
                .withEmail("kevinkimaru99@gmail.com")
                .withPhone(727683173L)
                .build();
        int id = save(contact);

        //Display a list of contacts
        System.out.printf("\n\nBefore update\n\n");
        fetchAllContacts().forEach(System.out::println);

        //get the persisted contact
        Contact c = findContactById(id);

        //update the contact
        c.setFirstName("Robert");

        //persist the changes
        System.out.printf("\n\nUpdating...\n\n");
        update(c);
        System.out.printf("\n\nUpdate complete!\n\n");

        //Display a list of contacts after the update
        System.out.printf("\n\nAfter update\n\n");
        fetchAllContacts().forEach(System.out::println);
    }


    private static Contact findContactById(int id) {
        //open a session
        Session session = sessionFactory.openSession();

        //retrieve the persistent object(or null if not found)
        Contact contact = session.get(Contact.class, id);

        //close session
        session.close();

        //return the object
        return contact;
    }

    private static void update(Contact contact) {
        //open a session
        Session session = sessionFactory.openSession();

        //begin a transaction
        session.beginTransaction();

        //use the session to update the contact
        session.update(contact);

        //commit the transaction
        session.getTransaction().commit();

        //close the session
        session.close();
    }

    @SuppressWarnings("unchecked")
    private static List<Contact> fetchAllContacts() {
        //open a session
        Session session = sessionFactory.openSession();

        //create criteria
        Criteria criteria = session.createCriteria(Contact.class);

        //Get a list of contact objects according to the criteria
        List<Contact> contacts = criteria.list();

        //close a session
        session.close();

        return contacts;
    }


    private static int save(Contact contact) {
        //open a session
        Session session = sessionFactory.openSession();

        //begin a transaction
        session.beginTransaction();

        //Use the session to save the contact
        int id = (int) session.save(contact);

        //Commit the transaction
        session.getTransaction().commit();

        //Close the session
        session.close();

        return id;
    }
}
