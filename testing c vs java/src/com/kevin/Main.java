package com.kevin;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
//        manageDriver();
//        dates();
        List<String> strings = new ArrayList<>();
        strings.add("KImaru");
        strings.add("Kevin");
        strings.add("Robert");
        strings.add("HJarrison");
        System.out.println(strings);

        strings.set(3, "Harrison");
        System.out.println(strings);

        String k = "";
        System.out.println("Is Empty: " + k.isEmpty());
    }

    private static void dates() {
        Date date = new Date(100, 10, 4);
        System.out.println("Date: " + date);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        System.out.println("Gregorian calendar: " + gregorianCalendar.toString());
        System.out.println("Gregorian calendar year: " + gregorianCalendar.get(Calendar.YEAR));
        System.out.println("Gregorian calendar month: " + gregorianCalendar.get(Calendar.MONTH));
        System.out.println("Gregorian calendar date: " + gregorianCalendar.get(Calendar.DAY_OF_MONTH));

        LocalDate localDate = LocalDate.of(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH) + 1,
                gregorianCalendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("Local Date: " + localDate);
    }

    private static void manageDriver() {
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "")){
            Statement s = con.createStatement();
            s.executeUpdate("CREATE DATABASE IF NOT EXISTS testing");
            s.executeUpdate("CREATE TABLE IF NOT EXISTS school"+
                    "(" +
                    "id integer NOT NULL,"+
                    "name varchar(40) NOT NULL,"+
                    "stream varchar(10) NOT NULL,"+
                    "PRIMARY KEY (id)" +
                    ");"
            );
//            s.executeUpdate("INSERT INTO school VALUES (1, 'Kevin Kimaru', 'B');");
//            s.executeUpdate("INSERT INTO school VALUES (2, 'Robert Kimani', 'A');");
//            s.executeUpdate("INSERT INTO school VALUES (3, 'Chris Kirubi', 'B');");
//            s.executeUpdate("INSERT INTO school VALUES (4, 'Mercy Liz', 'A');");

            ResultSet rs = s.executeQuery("SELECT * FROM school WHERE name LIKE 'k%'");

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String stream = rs.getString(3);
                System.out.println("[" + id +"," + "," +name +"," + stream + "]");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


