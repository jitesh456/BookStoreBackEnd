package com.bookstoreapp.repository;

import com.bookstoreapp.modal.BookStore;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IBookStoreRepository extends CrudRepository<BookStore,Integer> {
    @Query("select u.isbn from BookStore u")
    List<String> getAllISBN();
}
