package com.bookstoreapp.repository;

import com.bookstoreapp.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<Book,Integer> {
    Optional<Book> findByIsbn(String ISBN);

    Optional<Book> findById(int id);

    @Query(value="select * from bookdetails as b where b.author_name like %:search% or b.name like %:search%",nativeQuery = true)
    List<Book> searchBook(@Param("search") String search);
}
