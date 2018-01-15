package com.kevin;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by Kevin Kimaru Chege on 8/16/2017.
 */

class Watcher implements Observer {
    @Override
    public void update(Observable o, Object arg) {
//        System.out.println("Update called, count is: " + ((Integer)arg).intValue());
        System.out.println("Update called, count is: " + arg);
    }
}

class Watcher2 implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if ((Integer) arg == 0) System.out.println("Done" + '\7');
    }
}

class BeingWatched extends Observable {
    void counter(int period) {
        for (; period >= 0; period--) {
            setChanged();
//            notifyObservers(new Integer(period));
            notifyObservers(period);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted");
            }
        }

    }
}

class MyTimerTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("Timer task executed.");
    }
}

public class App {
    public static void main(String[] args) throws IOException {
//        string_tokenizer();

//        dates();

//        calendars();

//        randoms();

//        observing();

//        timers();

//        currencies();

//        formatters();

//        scanners();
//        scannersUsingFile();
//        scannerUsingDelimiters();
        scannerUsingFindInline();

    }

    private static void scannerUsingFindInline() {
        String instr = "Name: Tom Age: 28 ID: 77";
        Scanner conin = new Scanner(instr);
        conin.findInLine("Age:");
        if (conin.hasNext()) System.out.println(conin.next());
        else System.out.println("Error!");

        conin.close();
    }

    private static void scannerUsingDelimiters() throws IOException {
        FileWriter fw = new FileWriter("test2.txt");
        fw.write("2, 3.4,     5,6, 7.4, 9.1, 10.5, done");
        fw.close();

        FileReader fr = new FileReader("test2.txt");
//        Scanner sc = new Scanner(fr);//you can use try with resources block

        int count = 0;
        double sum = 0.0;
        try (Scanner sc = new Scanner(fr)) {
            sc.useDelimiter(", *");
            while (sc.hasNext()) {
                if (sc.hasNextDouble()) {
                    sum += sc.nextDouble();
                    count++;
                } else {
                    String str = sc.next();
                    if (str.equals("done")) break;
                    else {
                        System.out.println("Data format error!");
                        return;
                    }
                }
            }
//            sc.close();//you can use try with resources block
            System.out.println("Average is: " + (sum / count));
        }
    }

    private static void scannersUsingFile() throws IOException {
        FileWriter fw = new FileWriter("test.txt");
        fw.write("2 3.4 5.6 7.9 4 2 45 0.9 done");
        fw.close();

        FileReader fr = new FileReader("test.txt");
//        Scanner sc = new Scanner(fr);//you can use try with resources block

        int count = 0;
        double sum = 0.0;
        try (Scanner sc = new Scanner(fr)) {
            while (sc.hasNext()) {
                if (sc.hasNextDouble()) {
                    sum += sc.nextDouble();
                    count++;
                } else {
                    String str = sc.next();
                    if (str.equals("done")) break;
                    else {
                        System.out.println("Data format error!");
                        return;
                    }
                }
            }
//            sc.close();//you can use try with resources block
            System.out.println("Average is: " + (sum / count));
        }
    }

    private static void scanners() {
        Scanner sc = new Scanner(System.in);

        int count = 0;
        double sum = 0.0;

        System.out.println("Enter numbers to average: ");
        while (sc.hasNext()) {
            if (sc.hasNextDouble()) {
                sum += sc.nextDouble();
                count++;
            } else {
                String str = sc.next();
                if (str.equals("done")) break;
                else {
                    System.out.println("Data format error!");
                    return;
                }
            }
        }
        sc.close();
        System.out.println("Average is: " + (sum / count));
    }

    private static void formatters() {
        Formatter f = new Formatter();
//        f.format("%f %e", 23.3, 24.41178);
        f.format("%g", 0.8773586865789);
        System.out.println(f);
        System.out.println(f.toString());
        f.close();

        f = new Formatter();
        Calendar c = Calendar.getInstance();
        f.format("%tr\n", c);
        f.format("%tc\n", c);
        System.out.println(f);
        f.close();

        f = new Formatter();
        f.format("My name is kevin%nand my last name is kimaru. I got 100%% in maths");
        System.out.println("%n" + f);
        f.close();

        System.out.println();

        f = new Formatter();
        f.format("|%8d|", 23);
        System.out.println();
        f.format("%08d", 23);
        System.out.println(f);
        f.close();

        for (int i = 1; i <= 10; i++) {
            f = new Formatter();
            f.format("%4d %4d %4d", i, i * i, i * i * i);
            System.out.println(f);
            f.close();
        }

        System.out.println("\n\n");

        f = new Formatter();
        f.format("%.4f", 342.096587);
        f.format("%016.2e", 23.84445);
        f.format("%.15s", "My name is kevin kimaru");
        System.out.println(f);
    }

    private static void currencies() {
        Currency c;
        c = Currency.getInstance(Locale.US);
        System.out.println(c.getSymbol());
        System.out.println(c.getDefaultFractionDigits());
        System.out.println(c.getDisplayName());
        System.out.println(c.toString());
    }

    private static void timers() {
        MyTimerTask task = new MyTimerTask();
        Timer timer = new Timer();

        //set an initial delay of 1 sec  then repeat after every half second
        timer.schedule(task, 1000, 500);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("");
        }

        timer.cancel();


    }

    private static void observing() {
        BeingWatched observed = new BeingWatched();
        Watcher observer = new Watcher();
        Watcher2 observer2 = new Watcher2();

        observed.addObserver(observer);
        observed.addObserver(observer2);

        observed.counter(10);

    }

    private static void randoms() {
        Random r = new Random();
        double val;
        double sum = 0;
        int bell[] = new int[10];

        for (int i = 0; i < 100; i++) {
            val = r.nextGaussian();
            sum += val;
            double t = -2;

            for (int x = 0; x < 10; x++, t += 0.5) {
                if (val < t) {
                    bell[x]++;
                    break;
                }
            }
        }
        System.out.println("Average of values: " + (sum / 100));

        // display bell curve, sideways
        for (int i = 0; i < 10; i++) {
            for (int x = bell[i]; x > 0; x--) System.out.print("*");
            System.out.println();
        }
    }

    private static void calendars() {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        // Create a calendar initialized with the
        // current date and time in the default
        // locale and timezone.
        GregorianCalendar gc = new GregorianCalendar();
        Calendar calendar = Calendar.getInstance();
        System.out.println(months[calendar.get(Calendar.MONTH)]);
        System.out.println(calendar.get(Calendar.DATE));
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.HOUR));
        System.out.println(calendar.get(Calendar.MINUTE));
        System.out.println(calendar.get(Calendar.SECOND));


        System.out.println();
        //set
        calendar.set(Calendar.HOUR, 10);
        calendar.set(Calendar.MINUTE, 29);
        calendar.set(Calendar.SECOND, 22);
        System.out.println(calendar.get(Calendar.HOUR));
        System.out.println(calendar.get(Calendar.MINUTE));
        System.out.println(calendar.get(Calendar.SECOND));
    }

    private static void dates() {
        Date date = new Date();
        System.out.println(date);

        long msec = date.getTime();
        System.out.println(msec);

        Calendar cal = Calendar.getInstance();
        System.out.println(cal.isSet(Calendar.HOUR_OF_DAY));
    }

    private static void string_tokenizer() {
        String in = "title=Java: The Complete Reference;" +
                "author=Schildt" +
                "publisher=McGraw-Hill" +
                "copyright=2014";
        StringTokenizer st = new StringTokenizer(in, "=;");

        while (st.hasMoreTokens()) {
            String key = st.nextToken();
            String val = st.nextToken();
            System.out.println(key + "\t" + val);
        }
    }
}
