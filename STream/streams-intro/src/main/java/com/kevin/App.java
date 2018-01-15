package com.kevin;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class NamePhoneEmail {
    String name;
    String phonenum;
    String email;

    NamePhoneEmail(String n, String p, String e) {
        name = n;
        phonenum = p;
        email = e;
    }
}

class NamePhone {
    String name;
    String phonenum;

    NamePhone(String n, String p) {
        name = n;
        phonenum = p;
    }
}

public class App {
    public static void main(String[] args) {
//        intro();
//        usingReduce();
//        paralleltreams();
//        mapping();
//        mapping2();
//        mappin3();
//        collections1();
//        iterators1();
//        iterators2();
        iterators3();
    }

    private static void iterators3() {
        ArrayList<String> myList = new ArrayList<>();
        myList.add("Alpha");
        myList.add("Beta");
        myList.add("Gamma");
        myList.add("Delta");
        myList.add("Phi");
//        myList.add("Omega");

        Stream<String> myStream = myList.stream();

        Spliterator<String> splitItr = myStream.spliterator();

        Spliterator<String> splitItr2 = splitItr.trySplit();


        if (splitItr2 != null) {
            System.out.println("Output from splitItr2: ");
            splitItr2.forEachRemaining(n -> System.out.print(n + " "));
        }
        System.out.println();

        System.out.println("\nOutput from splitItr: ");
        splitItr.forEachRemaining((n) -> System.out.println(n + " "));
    }

    private static void iterators2() {
        ArrayList<String> myList = new ArrayList<>();
        myList.add("Alpha");
        myList.add("Beta");
        myList.add("Gamma");
        myList.add("Delta");
        myList.add("Phi");
        myList.add("Omega");

        Stream<String> myStream = myList.stream();

        Spliterator<String> spliterator = myStream.spliterator();

        while (spliterator.tryAdvance(n -> System.out.println(n))) ;
    }

    private static void iterators1() {
        ArrayList<String> myList = new ArrayList<>();
        myList.add("Alpha");
        myList.add("Beta");
        myList.add("Gamma");
        myList.add("Delta");
        myList.add("Phi");
        myList.add("Omega");

        Stream<String> myStream = myList.stream();

        Iterator<String> splitItr = myStream.iterator();

        while (splitItr.hasNext()) System.out.println(splitItr.next());
    }

    private static void collections1() {
        ArrayList<NamePhoneEmail> myList = new ArrayList<>();

        myList.add(new NamePhoneEmail("Larry", "555-5555", "Larry@HerbSchildt.com"));
        myList.add(new NamePhoneEmail("James", "555-4444", "James@HerbSchildt.com"));
        myList.add(new NamePhoneEmail("Mary", "555-3333", "Mary@HerbSchildt.com"));

        // Map just the names and phone numbers to a new stream.
        Stream<NamePhone> nameAndPhone = myList.stream().map((a) -> new NamePhone(a.name, a.phonenum));

        // Use collect to create a List of the names and phone numbers.
        List<NamePhone> npList = nameAndPhone.collect(Collectors.toList());
        npList.forEach(a -> System.out.println(a.name + " " + a.phonenum));

        // Obtain another mapping of the names and phone numbers.
        nameAndPhone = myList.stream().map((a) -> new NamePhone(a.name, a.phonenum));

        // Now, create a Set by use of collect().
        Set<NamePhone> npSet = nameAndPhone.collect(Collectors.toSet());

        System.out.println("\nNames and phone numbers in a Set:");
        for (NamePhone e : npSet) System.out.println(e.name + ": " + e.phonenum);
        System.out.println();

        nameAndPhone = myList.stream().map((a) -> new NamePhone(a.name, a.phonenum));
        LinkedList<NamePhone> npList2 = nameAndPhone.collect(() -> new LinkedList<>(),
                (list, element) -> list.add(element),
                (listA, listB) -> listA.addAll(listB));
        npList2.forEach(a -> System.out.println(a.name + " " + a.phonenum));
        System.out.println();

        nameAndPhone = myList.stream().map((a) -> new NamePhone(a.name, a.phonenum));
        HashSet<NamePhone> npSet3 = nameAndPhone.collect(
                HashSet::new,
                HashSet::add,
                HashSet::addAll
        );
        npSet3.forEach(a -> System.out.println(a.name + " " + a.phonenum));
    }

    private static void mappin3() {
        ArrayList<Double> myList = new ArrayList<>();

        myList.add(1.1);
        myList.add(3.6);
        myList.add(9.2);
        myList.add(4.7);
        myList.add(12.1);
        myList.add(5.0);

        System.out.println("Original values in myList: ");
        myList.stream().forEach(a -> System.out.print(a + " "));
        System.out.println();

        IntStream iStrm = myList.stream().mapToInt(a -> (int) Math.ceil(a));
        System.out.print("The ceilings of the values in myList: ");
        iStrm.forEach((a) -> {
            System.out.print(a + " ");
        });
    }

    private static void mapping2() {
        // A list of names, phone numbers, and e-mail addresses.
        ArrayList<NamePhoneEmail> myList = new ArrayList<>();
        myList.add(new NamePhoneEmail("Larry", "555-5555", "Larry@HerbSchildt.com"));
        myList.add(new NamePhoneEmail("James", "555-4444", "James@HerbSchildt.com"));
        myList.add(new NamePhoneEmail("Mary", "555-3333", "Mary@HerbSchildt.com"));

        System.out.println("Original values in myList");
        myList.stream().forEach(a -> {
            System.out.println(a.name + " " + a.email + " " + a.phonenum);
        });
        System.out.println();

        // Map just the names and phone numbers to a new stream.
        Stream<NamePhone> nameAndPhone = myList.stream().map(a -> new NamePhone(a.name, a.phonenum));
        System.out.println("List of names and phone numbers");
        nameAndPhone.forEach(a -> System.out.println(a.name + " " + a.phonenum));
    }

    private static void mapping() {
        ArrayList<Double> myList = new ArrayList<>();

        myList.add(7.0);
        myList.add(18.0);
        myList.add(10.0);
        myList.add(24.0);
        myList.add(17.0);
        myList.add(5.0);

        // Map the square root of the elements in myList to a new stream.
        Stream<Double> sqrRootStream = myList.stream().map(a -> Math.sqrt(a));

        // Find the product of the square roots
        double productOfSq = sqrRootStream.reduce(1.0, (a, b) -> a * b);

        System.out.println("Square root from mapping: " + productOfSq);
    }

    private static void usingReduce() {
        ArrayList<Integer> myList = new ArrayList<>();

        myList.add(7);
        myList.add(18);
        myList.add(10);
        myList.add(24);
        myList.add(17);
        myList.add(5);

        // Two ways to obtain the integer product of the elements     // in myList by use of reduce().
        Optional<Integer> productObj = myList.stream().reduce((a, b) -> a * b);
        if (productObj.isPresent()) System.out.println("Product as optional: " + productObj.get());

        int product = myList.stream().reduce(1, (a, b) -> a * b);
        System.out.println("Product as int: " + product);

        int evenProduct = myList.stream().reduce(1, (a, b) -> {
            if (b % 2 == 0) return a * b;
            else return a;
        });
        System.out.println("Even products: " + evenProduct);
    }

    private static void intro() {
        // Create a list of Integer values.
        ArrayList<Integer> myList = new ArrayList<>();
        myList.add(7);
        myList.add(18);
        myList.add(10);
        myList.add(24);
        myList.add(17);
        myList.add(5);

        System.out.println("Original list: " + myList);
        System.out.println();

        // Obtain a Stream to the array list.
        Stream<Integer> myStream = myList.stream();

        // Obtain the minimum and maximum value by use of min(),     // max(), isPresent(), and get().
        Optional<Integer> minVal = myStream.min(Integer::compare);
        if (minVal.isPresent()) System.out.println("Minimum value " + minVal.get());
        System.out.println();

        // Must obtain a new stream because previous call to min()     // is a terminal operation that consumed the stream.
        myStream = myList.stream();
        Optional<Integer> maxVal = myStream.max(Integer::compare);
        if (maxVal.isPresent()) System.out.println("Maximum vaue: " + maxVal.get());
        System.out.println();

        // Sort the stream by use of sorted().
        Stream<Integer> streamSorted = myList.stream().sorted();

        // Display the sorted stream by use of forEach().
        System.out.println("Sorted stream ");
        streamSorted.forEach(n -> System.out.print(n));
        System.out.println();
        System.out.println();

        // Display only the odd values by use of filter().
        Stream<Integer> oddVals = myList.stream().filter(n -> (n % 2) == 1);
        System.out.println("Odd vals: ");
        oddVals.forEach(System.out::print);
        System.out.println();

        // Display only the odd values that are greater than 5. Notice that     // two filter operations are pipelined.
        oddVals = myList.stream().filter(n -> (n % 2) == 1).filter(n -> n > 5);
        System.out.print("Odd values greater than 5: ");
        oddVals.forEach((n) -> System.out.print(n + " "));
        System.out.println();


    }

    private static void paralleltreams() {
        ArrayList<Integer> myList = new ArrayList<>();
        myList.add(7);
        myList.add(18);
        myList.add(10);
        myList.add(24);
        myList.add(17);
        myList.add(5);

        double productOfSqrRoots = myList.parallelStream().reduce(1.0, (a, b) -> a * Math.sqrt(b), (a, b) -> a * b);

        System.out.println("Product of square roots: " + productOfSqrRoots);
    }


}
