package com.kevin.databases;

import java.sql.*;

/**
 * Created by Kevin Kimaru Chege on 8/4/2017.
 */

public class Main {
    public static void main(String[] args) {

        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:contactmgr.db")) {

            //Create SQL statement
            Statement statement = connection.createStatement();

            //Create a db table
            statement.executeUpdate("DROP TABLE IF EXISTS contacts");
            statement.executeUpdate("CREATE TABLE contacts (id INTEGER PRIMARY KEY, firstname STRING, lastname STRING, email STRING, phone INT(10))");

            //Insert into a table
            Contact contact = new Contact("Kevin","Kimaru", "kevinkimaru99@gmail.com", 3727683173L);
            save(contact, statement);
            contact = new Contact("Peris", "Wangari", "periswangari@gmail.com", 0723367546L);
            save(contact, statement);
//            statement.executeUpdate("INSERT INTO  contacts (firstname,lastname,email,phone) VALUES('Kevin','Kimaru','kevinkimaru99@gmail.com',0727683173)");
//            statement.executeUpdate("INSERT INTO  contacts (firstname, lastname, email, phone) VALUES ('Peris', 'Wangari', 'periswangari@gmail.com', 0723367546)");

            //fetch data
            ResultSet rs = statement.executeQuery("SELECT * FROM contacts");

            //Iterate over ResultSet
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                System.out.printf("%s %s (%d)\n", firstName, lastName, id);
            }
        } catch(SQLException ex) {
            System.err.printf("THere was a database error: %S\n", ex.getMessage());
            ex.printStackTrace();
        }

    }

    public static void save(Contact contact, Statement statement) throws SQLException {
        String sql = "INSERT INTO  contacts (firstname, lastname, email, phone) VALUES ('%s', '%s', '%s', %d)";
        sql = String.format(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone());

        statement.executeUpdate(sql);
    }
}
