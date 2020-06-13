package com.bookstoreapp.repository;

import com.bookstoreapp.model.BookCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface IBookCartRepository extends JpaRepository<BookCart,Integer> {

    @Query(value="select book_quantity from book_cart where book_id=:bookId and cart_id=:cartId",nativeQuery=true)
    int getBookCartQuantity(@Param("bookId") int id,@Param("cartId") int id1);

    @Transactional
    @Modifying
    @Query(value="delete from book_cart where book_id=:bookId and cart_id=:cartId",nativeQuery=true)
    void updateBookCart(@Param("bookId") int bookid , @Param("cartId") int cartId);
}
