package com.bookstoreapp.repository;

import com.bookstoreapp.model.Book;
import com.bookstoreapp.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICartRepository extends JpaRepository<Cart,Integer> {
    Optional<Cart> findByIsbn(String ISBN);

    Optional<Cart> deleteByIsbn(String ISBN);
}
