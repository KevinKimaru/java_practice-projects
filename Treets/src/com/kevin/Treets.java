package com.kevin;

import java.io.*;

/**
 * Created by Kevin Kimaru Chege on 8/2/2017.
 */
public class Treets {
    public static void save(Treet[] treets) {
        try (
                FileOutputStream fos = new FileOutputStream("treets.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(treets);
        } catch (IOException ioe) {
            System.out.println("Problem saving treets");
            ioe.printStackTrace();
        }
    }

    public static Treet[] load() {
        Treet[] treets = new Treet[0];

        try (
                FileInputStream fis = new FileInputStream("treets.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            treets = (Treet[]) ois.readObject();
        } catch (IOException ioe) {
            System.out.println("Error reading file");
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error loading treets");
            cnfe.printStackTrace();
        }

        return treets;
    }
}
