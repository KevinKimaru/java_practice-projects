package com.kevin.lambdas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 8/2/2017.
 */
public class Books {
    public static List<Book> all() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Functional Programming in Java", "Kevin", 2008));
        books.add(new Book("Java fx essentials", "Robert", 2010));
        books.add(new Book("Effective java", "Eric", 2012));
        books.add(new Book("Pragmatic unit testing", "Peris", 2007));
        books.add(new Book("Java Generics and collections", "Rachael", 2005));
        books.add(new Book("Clean Code", "Liz", 2010));
        books.add(new Book("Java", "Rose", 2011));
        return books;
    }

}
