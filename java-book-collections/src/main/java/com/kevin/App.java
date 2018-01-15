package com.kevin;

import java.io.*;
import java.util.*;

class MyComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o2.compareTo(o1);
    }
}

class MyComp2 implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}

class MyComp3 implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        int i, j, k;

        //Find index of beginning of last name
        i = o1.lastIndexOf(" ");
        j = o2.lastIndexOf(" ");
        k = o1.substring(i).compareToIgnoreCase(o2.substring(j));
        if (k == 0) return o1.compareToIgnoreCase(o2);
        return k;
    }
}

class MyComp4 implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        int i, j, k;

        //Find index of beginning of last name
        i = o1.lastIndexOf(" ");
        j = o2.lastIndexOf(" ");
        return o1.substring(i).compareToIgnoreCase(o2.substring(j));
    }
}

class MyComp4Ass implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.compareToIgnoreCase(o2);
    }
}

public class App {
    public static void main(String[] args) throws IOException {
//        intro();

//        iterators();

//        splititerators();
//        comparators();


//        collection_algorithms();

//        arrays();

//        vectors();

//        stacks();

        using_properties();
    }

    private static void using_properties() throws IOException {
        Properties ht = new Properties();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name, number;
        FileInputStream fis = null;
        boolean changed = false;

        //Try to open phonebook.dat
        try {
            fis = new FileInputStream("phonebook.dat");
        } catch (FileNotFoundException e) {
            //ignore missing file
        }

        //if phonebook  file already exists:
        try {
            if (fis != null) {
                ht.load(fis);
                fis.close();
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        //let user enter new names and numbers
        do {
            System.out.println("Enter new name" + "('quit' to stop)");
            name = br.readLine();

            if (name.equals("quit")) continue;

            System.out.println("Enter a number: ");
            number = br.readLine();

            ht.put(name, number);
            changed = true;

        } while(!name.equals("quit"));

        if (changed) {
            FileOutputStream fos = new FileOutputStream("phonebook.dat");
            ht.store(fos, "Telephone Book");
            fos.close();
        }

        //look up numbers given a name
        do {
            System.out.println("Enter name to find: ('quit') to quit");

            name = br.readLine();
            if (name.equals("quit")) continue;
            number = (String) ht.getProperty(name);
            System.out.println(number);
        } while (!name.equals("quit"));
    }


    private static void stacks() {
        Stack<Integer> st = new Stack<>();
        System.out.println("Stack: " + st);

        showPush(st, 42);
        showPush(st, 66);
        showPush(st, 99);
        showPop(st);
        showPop(st);
        showPop(st);

        try {
            showPop(st);
        } catch (EmptyStackException e) {
            System.out.println("Empty stack");
        }
    }


    static void showPush(Stack<Integer> st, int a) {
        st.push(a);
        System.out.println("push(" + a + ")");
        System.out.println("stack: " + st);
    }

    static void showPop(Stack<Integer> st) {
        System.out.print("pop -> ");
        int a = st.pop();
        System.out.println("pop(" + a + ")");
        System.out.println("stack: " + st);
    }

    private static void vectors() {
        Vector<Integer> v = new Vector<>(3, 2);
        System.out.println("Initial size is: " + v.size());
        System.out.println("Initial capacity is: " + v.capacity());

        v.addElement(1);
        v.addElement(2);
        v.addElement(3);
        v.addElement(4);
        System.out.println("Capacity after four additions: " + v.capacity());

        v.addElement(5);
        System.out.println("Capacity after the fifth addition: " + v.capacity());

        v.addElement(6);
        v.addElement(7);
        System.out.println("Capacity after sixth and seventh additions: " + v.capacity());

        System.out.println("First element: " + v.firstElement());
        System.out.println("Last element: " + v.lastElement());

        if (v.contains(3)) System.out.println("v contains 3");

        //please use iterators or for each not this enumerator
        Enumeration<Integer> en = v.elements();
        while (en.hasMoreElements()) System.out.println(en.nextElement());
    }

    private static void arrays() {
        int[] array = new int[10];
        for (int i = 0; i < 10; i++) array[i] = -3 * i;
        for (int r : array) System.out.print(r + " ");
        System.out.println();

        Arrays.sort(array);
        for (int r : array) System.out.print(r + " ");
        System.out.println();

        Arrays.fill(array, 2, 6, 5);
        for (int r : array) System.out.print(r + " ");
        System.out.println();

        Arrays.sort(array);
        for (int r : array) System.out.print(r + " ");
        System.out.println();

        int index = Arrays.binarySearch(array, -9);
        System.out.println("-9 is in position: " + index);


    }

    private static void collection_algorithms() {
        LinkedList<Integer> ll = new LinkedList<>();
        ll.add(-8);
        ll.add(20);
        ll.add(-20);
        ll.add(8);

        //create  reverse order comparator
        Comparator<Integer> comp = Collections.reverseOrder();

        //sort list by using comparator
        Collections.sort(ll, comp);
        System.out.println("Collection sorted in reverse order:");
        ll.forEach(System.out::println);
        System.out.println();

        Collections.shuffle(ll);
        System.out.println("Collection shuffled:");
        ll.forEach(System.out::println);
        System.out.println();

        System.out.println("Maximum: " + Collections.max(ll));
        System.out.println("Minimum: " + Collections.min(ll));
    }

    private static void comparators() {
        //        TreeSet<String> ts = new TreeSet<>(new MyComp());

//        Create a comparator
//        MyComp2 myComp2 = new MyComp2();
//        TreeSet<String> ts = new TreeSet<>(myComp2.reversed());

//        //create comparator using lambda
//        Comparator<String> comp = (o1, o2) -> o2.compareTo(o1);
//        TreeSet<String> ts = new TreeSet<>(comp);

        //use lambda
        TreeSet<String> ts = new TreeSet<>((o1, o2) -> o2.compareTo(o1));
        ts.add("B");
        ts.add("A");
        ts.add("C");
        ts.add("E");
        ts.add("D");
        ts.add("F");
        ts.forEach(System.out::println);

//        TreeMap<String, Double> tm = new TreeMap<>(new MyComp3());

        // Use thenComparing() to create a comparator that compares
        // last names, then compares entire name when last names match.
        MyComp4 myComp4 = new MyComp4();
        TreeMap<String, Double> tm = new TreeMap<>(myComp4.thenComparing(new MyComp4Ass()));
        tm.put("Kevin KImaru", 23.0);
        tm.put("Francis Chege", 47.98);
        tm.put("Peris Chege", 12.76);
        tm.put("Robert Kimani", 30.0);
        tm.put("Eva Wanjiku", 15.07);

        Set<Map.Entry<String, Double>> set = tm.entrySet();
        set.forEach(s -> System.out.println(s.getKey() + " " + s.getValue()));

        System.out.println();

        double balance = tm.get("Kevin Kimaru");
        tm.put("Kevin Kimaru", balance + 1000);
        set.forEach(s -> System.out.println(s.getKey() + " " + s.getValue()));
    }

    private static void splititerators() {
        ArrayList<Double> vals = new ArrayList<>();

        vals.add(1.0);
        vals.add(2.0);
        vals.add(3.0);
        vals.add(4.0);
        vals.add(5.0);
        vals.add(6.0);

        Spliterator<Double> sitr = vals.spliterator();
        while (sitr.tryAdvance(n -> System.out.print(n))) System.out.println();

        sitr = vals.spliterator();
        ArrayList<Double> sqrs = new ArrayList<>();
        while (sitr.tryAdvance(n -> sqrs.add(Math.sqrt(n)))) ;

        System.out.println();

        sitr = sqrs.spliterator();
        sitr.forEachRemaining(n -> System.out.println(n));
//        while (sitr.tryAdvance(n -> System.out.print(n))) System.out.println();
        System.out.println();

    }

    private static void iterators() {
        ArrayList<String> al = new ArrayList<>();
        al.add("A");
        al.add("B");
        al.add("C");
        al.add("D");
        al.add("E");

        Iterator<String> itr = al.iterator();
        while (itr.hasNext()) {
            String element = itr.next();
            System.out.println(element);
        }
        System.out.println();

        ListIterator<String> litr = al.listIterator();
        while (litr.hasNext()) {
            String element = litr.next();
            litr.set(element + "+");
        }

        while (litr.hasPrevious()) {
            String element = litr.previous();
            System.out.println(element);
        }
    }

    private static void intro() {
        TreeSet<Integer> arList = new TreeSet<>();
        arList.add(13);
        arList.add(14);
        arList.add(15);
        arList.add(16);
        arList.add(17);
        arList.add(18);
        arList.add(19);
        arList.add(20);

        System.out.println(arList.ceiling(10));
        System.out.println(arList.floor(34));
        System.out.println(arList.higher(16));
        System.out.println(arList.lower(14));
        System.out.println(arList.headSet(14, true));
        System.out.println(arList.tailSet(14, true));
        System.out.println(arList.subSet(14, true, 17, false));
        System.out.println(arList.descendingSet());

        ArrayDeque<String> adq = new ArrayDeque<>();
        adq.push("A");
        adq.push("B");
        adq.push("C");
        adq.push("D");
        adq.push("E");
        adq.push("F");

        while (adq.peek() != null) System.out.println(adq.pop() + " ");
        System.out.println(adq);
    }
}
