package com.bookstoreapp.comparison;

import com.bookstoreapp.model.Book;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Comparison {

    public static List<Book> getSortedBooks(List<Book> book, String field){

        if(field.equals("authername")){
            book=book.stream().sorted(Comparator.comparing(x->x.authorName)).collect(Collectors.toList());
        }

        if(field.equals("category")){
            book=book.stream().sorted(Comparator.comparing(x->x.category)).collect(Collectors.toList());
        }

        if(field.equals("price")) {
            book=book.stream().sorted(Comparator.comparing(x->x.price)).collect(Collectors.toList());
        }
        return book;
    }
}
