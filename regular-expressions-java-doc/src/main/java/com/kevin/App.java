package com.kevin;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kevin Kimaru Chege on 9/3/2017.
 */
public class App {
    public static void main(String[] args) {
//        intro();
//        usingFind();
//        usingFind2();
//        usingWildcards();
//        usingClassesOfCharacters();
//        usingReplaceAll();
//        usingSplit();
        twoPatternMatching();

    }

    private static void twoPatternMatching() {
        if (Pattern.matches("m.+", "mjvgd ")) System.out.println("Yesss!!!");
        else System.out.println("Noooooo");
        System.out.println();

        String str = "mjvgd";
        if (str.matches("m.+")) System.out.println("Yesss!!!");
        else System.out.println("Noooooo");
    }

    private static void usingSplit() {
        Pattern pat = Pattern.compile("[ .,!]");
        String strs[] = pat.split("one two,alpha9 12!done.");
        for (int i = 0; i < strs.length; i++) System.out.println("Next Token: " + strs[i]);
    }

    private static void usingReplaceAll() {
        String str = "Jon Jonathan Frank Ken Todd";

        Pattern pat = Pattern.compile("Jon.*? ");
        Matcher mat = pat.matcher(str);

        System.out.println("Original sequence: " + str);

        str = mat.replaceAll("Eric ");

        System.out.println("Modified sequence: " + str);
    }

    private static void usingClassesOfCharacters() {
        // Match lowercase words.
        Pattern pat = Pattern.compile("[a-z]+");
        Matcher mat = pat.matcher("this is a test.");
        while (mat.find()) System.out.println("Match: " + mat.group());
    }

    private static void usingWildcards() {
        Pattern pat = Pattern.compile("W+");
        Matcher mat = pat.matcher("W WW WWW");
        while (mat.find()) System.out.println("Match: " + mat.group());
        System.out.println();

        pat = Pattern.compile("e.+?d");
        mat = pat.matcher("extend cup end table");
        while (mat.find()) System.out.println("Match: " + mat.group());
        System.out.println();

        pat = Pattern.compile("e.+d");
        mat = pat.matcher("extend cup end table");
        while (mat.find()) System.out.println("Match: " + mat.group());
        System.out.println();
    }

    private static void usingFind2() {
        Pattern pat = Pattern.compile("test");
        Matcher mat = pat.matcher("test 1 2 3 test");

        while (mat.find()) {
            System.out.println("test found at index " + mat.start());
        }
    }

    private static void usingFind() {
        Pattern pat = Pattern.compile("Java");
        Matcher mat = pat.matcher("Java 8");

        if (mat.find()) System.out.println("Found matches");
        else System.out.println("No matches found");
    }

    private static void intro() {
        Pattern pat;
        Matcher mat;
        boolean found;

        pat = Pattern.compile("Java");
        mat = pat.matcher("Java");
        found = mat.matches();
        System.out.println("Testing Java against Java.");
        if (found) System.out.println("Matches");
        else System.out.println("No match");

        mat = pat.matcher("Java 8");
        found = mat.matches();
        System.out.println("Testing Java against Java.");
        if (found) System.out.println("Matches");
        else System.out.println("No match");
    }
}
