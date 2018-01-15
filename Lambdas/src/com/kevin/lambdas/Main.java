package com.kevin.lambdas;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 8/2/2017.
 */
public class Main {
    public static void usingAnonymousInlineClass() {
        List<Book> books = Books.all();
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b1.getTitle().compareTo(b2.getTitle());
            }
        });

        for (Book book: books) {
                System.out.println(book);
        }
    }

    public static void usingLambdasInLongForm() {
        List<Book> books = Books.all();
        Collections.sort(books, (Book b1, Book b2) -> {
            return b1.getTitle().compareTo(b2.getTitle());
        });

        for (Book book: books) {
            System.out.println(book);
        }
    }

    public static void usingLambdasInShortForm() {
        List<Book> books = Books.all();
        //no return and Book b1, in one line
        Collections.sort(books, (b1, b2) -> b1.getTitle().compareTo(b2.getTitle()));

        books.forEach(book -> System.out.println(book));
    }

    public static void usingMethodReferences() {
        List<Book> books = Books.all();
//        Collections.sort(books, Comparator.comparing(b -> b.getTitle()));
        Collections.sort(books, Comparator.comparing(Book::getTitle));
        books.forEach(System.out::println);
    }

    public static void main(String[] args) {
//        usingAnonymousInlineClass();
//        usingLambdasInLongForm();
//        usingLambdasInShortForm();
        usingMethodReferences();
    }

}
