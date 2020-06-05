package com.bookstoreapp.repository;

import com.bookstoreapp.model.BookCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IBookCartRepository extends JpaRepository<BookCart,Integer> {
}
