package com.kevin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Kevin Kimaru Chege on 9/5/2017.
 */
public class App {
    public static void main(String[] args) {
//        date1();
//        time1();
//        simpleDate1();
//        dateTimeAPI1();
//        dateTimeAPIFormated();
        parsing();
    }

    private static void parsing() {
        // Obtain a LocalDateTime object by parsing a date and time string.
        LocalDateTime dateTime = LocalDateTime.parse("June 21, 2014 12:01 AM",
                DateTimeFormatter.ofPattern("MMMM d',' yyyy hh':'mm a"));
        System.out.println(dateTime.format( DateTimeFormatter.ofPattern("MM dd',' yy h':'mm a")));
    }

    private static void dateTimeAPIFormated() {
        LocalDate curDate = LocalDate.now();
        System.out.println(curDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
        LocalTime curTime = LocalTime.now();
        System.out.println(curTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
        LocalDateTime curDateTime = LocalDateTime.now();
        System.out.println(curDateTime.format(DateTimeFormatter.ofPattern("MMMM d',' yyyy h':'mm a")));

        System.out.println(curDate.getYear());
        System.out.println(curDate.getMonth());
        System.out.println(curTime.getHour());
    }

    private static void dateTimeAPI1() {
        LocalTime curTime = LocalTime.now();
        System.out.println(curTime);

        LocalDate curDate = LocalDate.now();
        System.out.println(curDate);

        LocalDateTime curDateTime = LocalDateTime.now();
        System.out.println(curDateTime);
        System.out.println(curDateTime.toLocalDate());
        System.out.println(curDateTime.toLocalTime());
    }

    private static void simpleDate1() {
        Date date = new Date();
        SimpleDateFormat sdf;

        sdf = new SimpleDateFormat("hh:mm:ss");
        System.out.println(sdf.format(date));

        sdf = new SimpleDateFormat("dd MMM yyyy hh:mm:ss zzz");
        System.out.println(sdf.format(date));

        sdf = new SimpleDateFormat("EEEE MMMM dd yyyy");
        System.out.println(sdf.format(date));
    }

    private static void time1() {
        Date date = new Date();
        DateFormat df;

        df = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.JAPAN);
        System.out.println("Japan: " + df.format(date));

        df = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.KOREA);
        System.out.println("Korea: " + df.format(date));

        df = DateFormat.getTimeInstance(DateFormat.LONG, Locale.UK);
        System.out.println("UK: " + df.format(date));

        df = DateFormat.getTimeInstance(DateFormat.FULL, Locale.US);
        System.out.println("US: " + df.format(date));
    }

    private static void date1() {
        Date date = new Date();
        DateFormat df;

        df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.JAPAN);
        System.out.println("Japan: " + df.format(date));

        df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.KOREA);
        System.out.println("Korea: " + df.format(date));

        df = DateFormat.getDateInstance(DateFormat.LONG, Locale.UK);
        System.out.println("UK: " + df.format(date));

        df = DateFormat.getDateInstance(DateFormat.FULL, Locale.US);
        System.out.println("US: " + df.format(date));
    }
}
