package com.library.main;

import com.library.analytics.LibraryAnalytics;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        LibraryAnalytics analytics = new LibraryAnalytics();

        List<String> records = Arrays.asList(
            "B101|Clean Code|Robert Martin|Programming|145|4.8",
            "B102|Effective Java|Joshua Bloch|Programming|180|4.9",
            "B103|Atomic Habits|James Clear|SelfHelp|200|4.7",
            "B104|Java Java Mastery|ABC Author|Programming|80|4.1",
            "B105|Java Concurrency|Cal Newport|Programming|150|4.8");

        analytics.loadBooks(records);

        System.out.println("== TOP RATED BOOKS ==");
        analytics.topRatedBooks(5).forEach(System.out::println);

        System.out.println("\n== AVG RATING BY CATEGORY ==");
        analytics.averageRatingByCategory()
                .forEach((k,v) -> System.out.println(k + " -> " + v));

        System.out.println("\n== MOST BORROWED ==");
        analytics.mostBorrowedBook().ifPresent(System.out::println);

        System.out.println("\n== AUTHORS WITH MULTIPLE CATEGORIES ==");
        System.out.println(analytics.authorsWithMultipleCategories());

        System.out.println("\n== GROUP BY AUTHOR ==");
        analytics.groupBooksByAuthor().forEach((author, books) -> {
            System.out.println(author);
            books.forEach(System.out::println);
            System.out.println();
        });

        System.out.println("== SUSPICIOUS BOOKS ==");
        analytics.suspiciousBooks().forEach(System.out::println);

        System.out.println("\n== CATEGORY WISE TOP RATED BOOK BY EACH AUTHOR ==");
        analytics.categoryWiseTopRatedBookByEachAuthor().forEach((category, authorMap) -> {
            System.out.println(category);
            authorMap.forEach((author, book) ->
                System.out.println(author + " -> " + book.getTitle()));
            System.out.println();
        });
    }
}
