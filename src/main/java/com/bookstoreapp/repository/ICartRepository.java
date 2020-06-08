package com.bookstoreapp.repository;

import com.bookstoreapp.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends JpaRepository<Cart,Integer> {


}
