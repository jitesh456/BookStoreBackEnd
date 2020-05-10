package com.bookstoreapp.repository;

import com.bookstoreapp.modal.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IBookRepository extends JpaRepository<Book,Integer> {
    Optional<Book> findByIsbn(String isbn);
}
