package com.kevin.contactmgr;

import com.kevin.contactmgr.model.Contact;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 2/13/2018.
 */
public class Application {

    //Hold a reusable reference to a sessionfactory (since we need only one)
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void main(String[] args) {
        Contact contact = new Contact.ContactBuilder("Kevin", "Kimaru")
                .withEmail("kevinkimaru99@gmail.com")
                .withPhone("0727683173")
                .build();
        int id = save(contact);

        //display a list of contacts before update
        System.out.println("\n\n Before update");
        fetchAllContacts().stream().forEach(System.out::println);

        System.out.println("\n\nUpdating.....");
        Contact c = findContactById(id);
        c.setFirstName("Robert");
        update(c);
        System.out.println("\n\nupdate complete");

        System.out.println("\n\nAfeter Complete");
        //display a list of contacts before update
        fetchAllContacts().stream().forEach(System.out::println);
    }

    private static Contact findContactById(int id) {
        Session session = sessionFactory.openSession();
        Contact contact = session.get(Contact.class, id);
        session.close();
        return contact;
    }

    private static  void update(Contact contact) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(contact);
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    private static List<Contact> fetchAllContacts() {
        //Open a session
        Session session = sessionFactory.openSession();

        //Create criteria
        Criteria criteria = session.createCriteria(Contact.class);

        //Get a list of contact objects according to the criteria object
        List<Contact> contacts = criteria.list();

        //Close session
        session.close();

        return contacts;
    }

    private static int save(Contact contact) {
        //Open a session
        Session session = sessionFactory.openSession();

        //begin atransaction
        session.beginTransaction();

        //Use the session to save the contact
        int id = (int) session.save(contact);

        //commit the transaction
        session.getTransaction().commit();

        //close the session
        session.close();

        return id;
    }
}
