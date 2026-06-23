package com.library.analytics;

import com.library.entity.Book;
import java.util.*;
import java.util.stream.*;

public class LibraryAnalytics {
    private Map<String, Book> books = new HashMap<>();

    public void loadBooks(List<String> records) {
        for(String record : records){
            String[] p = record.split("\\|");
            if(p.length != 6) continue;
            if(Arrays.stream(p).anyMatch(String::isEmpty)) continue;

            int borrow;
            double rating;
            try{
                borrow = Integer.parseInt(p[4]);
                rating = Double.parseDouble(p[5]);
            }catch(Exception e){ continue; }

            if(rating < 0 || rating > 5 || borrow < 0) continue;

            Book newBook = new Book(p[0],p[1],p[2],p[3],borrow,rating);

            books.merge(p[0], newBook, (oldB,newB) ->
                newB.getRating() > oldB.getRating() ? newB :
                newB.getRating() == oldB.getRating() && newB.getBorrowCount() > oldB.getBorrowCount() ? newB :
                newB.getRating() == oldB.getRating() && newB.getBorrowCount() == oldB.getBorrowCount()
                && newB.getTitle().compareTo(oldB.getTitle()) < 0 ? newB : oldB
            );
        }
    }

    public List<Book> topRatedBooks(int n){
        return books.values().stream()
        .sorted(Comparator.comparing(Book::getRating).reversed()
        .thenComparing(Comparator.comparing(Book::getBorrowCount).reversed())
        .thenComparing(Book::getTitle))
        .limit(n)
        .collect(Collectors.toList());
    }

    public Map<String, Double> averageRatingByCategory(){
        return books.values().stream()
            .collect(Collectors.groupingBy(Book::getCategory, TreeMap::new,
                Collectors.collectingAndThen(Collectors.averagingDouble(Book::getRating),
                    avg -> Math.round(avg*100.0)/100.0)));
    }

    public Optional<Book> mostBorrowedBook(){
        return books.values().stream()
            .sorted(Comparator.comparing(Book::getBorrowCount).reversed()
            .thenComparing(Book::getRating, Comparator.reverseOrder())
            .thenComparing(Book::getBookId))
            .findFirst();
    }

    public Set<String> authorsWithMultipleCategories(){
        return books.values().stream()
            .collect(Collectors.groupingBy(Book::getAuthor,
                Collectors.mapping(Book::getCategory, Collectors.toSet())))
            .entrySet().stream()
            .filter(e -> e.getValue().size() > 1)
            .map(Map.Entry::getKey)
            .collect(Collectors.toCollection(TreeSet::new));
    }

    public Map<String,List<Book>> groupBooksByAuthor(){
        return books.values().stream()
            .sorted(Comparator.comparing(Book::getAuthor))
            .collect(Collectors.groupingBy(Book::getAuthor, LinkedHashMap::new,
                Collectors.collectingAndThen(Collectors.toList(), list ->
                    list.stream()
                    .sorted(Comparator.comparing(Book::getRating).reversed()
                    .thenComparing(Comparator.comparing(Book::getBorrowCount).reversed()))
                    .collect(Collectors.toList()))));
    }

    public List<String> suspiciousBooks(){
        Map<String, Double> avgBorrow = books.values().stream()
            .collect(Collectors.groupingBy(Book::getCategory,
                Collectors.averagingInt(Book::getBorrowCount)));

        Map<String, Double> avgRating = books.values().stream()
            .collect(Collectors.groupingBy(Book::getCategory,
                Collectors.averagingDouble(Book::getRating)));

        return books.values().stream()
            .filter(book -> {
                String[] words = book.getTitle().split(" ");

                boolean c1 = IntStream.range(0, words.length-1)
                    .anyMatch(i -> words[i].equalsIgnoreCase(words[i+1]));

                boolean c2 = Arrays.stream(book.getAuthor().toLowerCase().split(" "))
                    .anyMatch(w -> book.getTitle().toLowerCase().contains(w));

                boolean c3 = book.getBorrowCount() > avgBorrow.get(book.getCategory()) * 3;

                boolean c4 = book.getRating() < avgRating.get(book.getCategory()) &&
                             book.getBorrowCount() > avgBorrow.get(book.getCategory());

                return c1 || c2 || c3 || c4;
            })
            .map(Book::getTitle)
            .distinct()
            .sorted()
            .collect(Collectors.toList());
    }

    public Map<String, Map<String, Book>> categoryWiseTopRatedBookByEachAuthor(){
        return books.values().stream()
         .collect(Collectors.groupingBy(Book::getCategory,
         Collectors.groupingBy(Book::getAuthor,
         Collectors.collectingAndThen(Collectors.toList(), list ->
         list.stream()
          .sorted(Comparator.comparing(Book::getRating).reversed()
          .thenComparing(Comparator.comparing(Book::getBorrowCount).reversed())
          .thenComparing(Book::getTitle))
          .findFirst().get()))));
    }
}