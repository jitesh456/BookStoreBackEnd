package com.bookstoreapp.repository;

import com.bookstoreapp.modal.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IBookRepository extends CrudRepository<Book,Integer> {
    @Query("select u.isbn from Book u")
    List<String> getAllISBN();
}
