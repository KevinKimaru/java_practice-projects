package com.kevin.databases;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.spi.SyncProviderException;
import javax.sql.rowset.spi.SyncResolver;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Kevin Kimaru Chege on 10/31/2017.
 */
public class Launch {

    private Connection con;

    public Launch() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?&user=root&password=");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getCOn() {
        return con;
    }

    public static void createDatabase() {
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306?&user=root&password=")) {
            Statement s = con.createStatement();
            s.executeUpdate("CREATE DATABASE IF NOT EXISTS students");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void buildStudentsTable() throws SQLException {
        Statement st = con.createStatement();
        try {
            st.executeUpdate(
            "CREATE TABLE IF NOT EXISTS students (" +
                    "admission_number INTEGER NOT NULL," +
                    "first_name VARCHAR(40) NOT NULL," +
                    "middle_name VARCHAR(40) NOT NULL," +
                    "last_name VARCHAR(40) NOT NULL," +
                    "dob DATE NOT NULL," +
                    "house VARCHAR(40) NOT NULL," +
                    "stream VARCHAR(2) NOT NULL," +
                    "username VARCHAR(40) NOT NULL UNIQUE," +
                    "password VARCHAR(22) NOT NULL," +
                    "current_form INTEGER NOT NULL," +
                    "lastly_modified TIMESTAMP NOT NULL," +
                    "account_status BOOLEAN NOT NULL," +
                    "PRIMARY KEY(admission_number)" +
                    ")");
        } finally {
            if (st != null) st.close();
        }
    }

    public void buildAdministratorsTable() throws SQLException {
        Statement st = con.createStatement();
        try {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS admission_administrators (" +
                    "id_number INTEGER NOT NULL," +
                    "first_name VARCHAR(40) NOT NULL," +
                    "middle_name VARCHAR(40) NOT NULL," +
                    "last_name VARCHAR(40) NOT NULL," +
                    "dob DATE NOT NULL," +
                    "username VARCHAR(40) NOT NULL UNIQUE," +
                    "password VARCHAR(22) NOT NULL," +
                    "lastly_modified TIMESTAMP NOT NULL," +
                    "PRIMARY KEY(id_number)" +
                    ")");
        } finally {
            if (st != null) st.close();
        }
    }

    public void addStudent(int admissionNumber, String firstName, String middleName,
                           String lastName, LocalDate dob, String house, String stream,
                           int currentForm) throws SQLException {
        con.setAutoCommit(false);
        CachedRowSet crs = null;

        try {
            crs = new CachedRowSetImpl();
            crs.setUrl("jdbc:mysql://localhost:3306/students?&user=root&password=&relaxAutoCommit=true");
            crs.setCommand("SELECT * FROM students");
            crs.execute();
            int[] keys = {1};
            crs.setKeyColumns(keys);

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime last_modified = LocalDateTime.now();
            String dateOfBirth = dob.format(dateFormatter);
            String last_modified_string = last_modified.format(dateTimeFormatter);
            String username = firstName + lastName;
            String password = firstName + "";

            crs.moveToInsertRow();
            crs.updateInt(1, admissionNumber);
            crs.updateString(2, firstName);
            crs.updateString(3, middleName);
            crs.updateString(4, lastName);
            crs.updateString(5, dateOfBirth);
            crs.updateString(6, house);
            crs.updateString(7, stream);
            crs.updateString(8, username);
            crs.updateString(9, password);
            crs.updateInt(10, currentForm);
            crs.updateString(11, last_modified_string);
            crs.updateBoolean(12, false);
            crs.insertRow();
            crs.moveToCurrentRow();
            crs.acceptChanges(con);
        } catch (SyncProviderException spe) {
            spe.printStackTrace();
        } finally {
            if (crs != null) crs.close();
            con.setAutoCommit(true);
        }
    }

    public void addStudentAccount(int fileNumber, String username, String password) throws SQLException {
        con.setAutoCommit(false);
        CachedRowSet crs = null;
        try {
            crs = new CachedRowSetImpl();
            crs.setUrl("jdbc:mysql://localhost:3306/students?&user=root&password=&relaxAutoCommit=true");
            crs.setCommand("SELECT * FROM students");
            while (crs.next()) {
                System.out.println("Hello world!!");
                if (crs.getInt(1) == fileNumber) {
                    crs.updateString(8, username);
                    crs.updateString(9, password);
                    crs.updateRow();
                    crs.acceptChanges(con);
                    break;
                }
            }
        } finally {
            if (crs != null) crs.close();
            con.setAutoCommit(true);
        }
    }

    public Map<String, String> getStudentDetails(int fileNumber) {
        Map<String, String> studentDetails = new HashMap<>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                String admissionNumber = String.valueOf(rs.getInt(1));
                String firstName = rs.getString(2);
                String middleName = rs.getString(3);
                String lastName = rs.getString(4);
                String dob = rs.getTimestamp(5).toString();
                String house = rs.getString(6);
                String stream = rs.getString(7);
                String currentForm = String.valueOf(rs.getInt(10));
                String lastlyModified = rs.getString(11);
                String account_status = rs.getString(12);
                if (Integer.valueOf(admissionNumber) == fileNumber) {
                    studentDetails.put("Admission Number", admissionNumber);
                    System.out.println(admissionNumber);
                    studentDetails.put("First Name", firstName);
                    System.out.println(firstName);
                    studentDetails.put("Middle Name", middleName);
                    studentDetails.put("Last Name", lastName);
                    studentDetails.put("Date of Birth", dob);
                    studentDetails.put("House", house);
                    studentDetails.put("Stream", stream);
                    studentDetails.put("Current Form", currentForm);
                    studentDetails.put("Lastly Modified", lastlyModified);
                    studentDetails.put("Account Status", account_status);
                }
                System.out.println("Admission number: " + admissionNumber + "First Name: " + firstName);
            }
            return studentDetails;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return studentDetails;
    }
}
