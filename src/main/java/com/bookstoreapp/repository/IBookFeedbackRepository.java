package com.bookstoreapp.repository;

import com.bookstoreapp.model.BookFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBookFeedbackRepository extends JpaRepository<BookFeedback,Integer> {

    @Query(value="select feedback_id from book_feedback where book_id=:bookId",nativeQuery=true)
    List<Integer> getfeedbackIds(@Param("bookId") int id );
}
