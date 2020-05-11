package com.bookstoreapp.repository;

import com.bookstoreapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBookRepository extends JpaRepository<Book,Integer> {
    Optional<Book> findByIsbn(String isbn);
}
