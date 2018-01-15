package com.kevin;

import java.io.*;


/**
 * Created by Kevin Kimaru Chege on 8/13/2017.
 */
public class App {
    public static void main(String[] args) throws IOException {
//        intro();

//        textEditor();
//        writeToConsole();

//        writeToConsoleUsingPrintWriter();

//        readFromFiles();
//        copyFromOneFileToAnther();
//        trywithresources();

        assertEx();


    }

    private static void assertEx() {
        int n;
        for (int i = 0; i < 5; i++) {
            n = AssertDemo.getNum();

            assert n > 0: "n is negative";

            System.out.println("n is" + n);
        }
    }

    static class AssertDemo {
        private static int val = 3;
        static int getNum() {
            return val--;
        }
    }

    private static void trywithresources() {
        int i;
        try (FileInputStream fis = new FileInputStream("src/test.txt");
             FileOutputStream fos = new FileOutputStream("src/copied.txt");) {
            do {
                i = fis.read();
                if (i != -1) fos.write(i);
            } while (i != -1);
        } catch (IOException e) {
            System.out.println("I/O error" + e);
        }
    }

    private static void copyFromOneFileToAnther() {
        int i;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("src/test.txt");
            fos = new FileOutputStream("src/copied.txt");
            do {
                i = fis.read();
                if (i != -1) fos.write(i);
            } while (i != -1);
        } catch (IOException e) {
            System.out.println("I/O error" + e);
        } finally {
            try {
                fis.close();
            } catch (IOException ioe) {
                System.out.println("Error closing Input file");
            }
            try {
                fos.close();
            } catch (IOException ioe) {
                System.out.println("Error closing Output file");
            }
        }

    }

    private static void readFromFiles() {
        int i;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("src/test.txt");
            do {
                i = fis.read();
                if (i != -1) System.out.println((char) i);
            } while (i != -1);
        } catch (IOException e) {
            System.out.println("I/O error" + e);
        } finally {
            try {
                fis.close();
            } catch (IOException ioe) {
                System.out.println("Error closing file");
            }
        }

    }


    private static void writeToConsoleUsingPrintWriter() {
        PrintWriter printWriter = new PrintWriter(System.out, true);
        printWriter.println("This is a string");
        int i = -7;
        printWriter.println(i);
        double d = 4.5e-7;
        printWriter.println(d);
    }

    private static void writeToConsole() {
        int b;
        b = 'A';
        System.out.write(b);
        System.out.write('\n');
    }

    private static void intro() throws IOException {
        char c;
        String s;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Read character
        do {
            c = (char) reader.read();
            System.out.println(c);
        } while (c != 'q');

        //Read string
        do {
            s = reader.readLine();
            System.out.println(s);
        } while (!s.equals("stop"));
    }

    private static void textEditor() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str[] = new String[100];

        System.out.println("Enter lines of text.");
        System.out.println("Enter 'stop' to quit.");

        for (int i = 0; i < 100; i++) {
            str[i] = reader.readLine();
            if (str[i].equals("stop")) break;
        }

        System.out.println("\nHere is your file:");
        for (int i = 0; i < 100; i++) {
            if (str[i].equals("stop")) break;
            System.out.println(str[i]);
        }
    }
}
