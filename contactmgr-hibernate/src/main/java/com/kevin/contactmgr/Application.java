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
        save(contact);

        //Display a list of contacts
        //fetchAllContacts().forEach(System.out::println);
        for (Contact c: fetchAllContacts()) {
            System.out.println(c);
        }
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


    private static void save(Contact contact) {
        //open a session
        Session session = sessionFactory.openSession();

        //begin a transaction
        session.beginTransaction();

        //Use the session to save the contact
        session.save(contact);

        //Commit the transaction
        session.getTransaction().commit();

        //Close the session
        session.close();
    }
}
