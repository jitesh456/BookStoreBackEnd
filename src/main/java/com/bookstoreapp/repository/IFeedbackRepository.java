package com.bookstoreapp.repository;


import com.bookstoreapp.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFeedbackRepository extends JpaRepository<Feedback,Integer> {

    @Query(value="select user_id from feedback where id=:feedbackId",nativeQuery=true)
    int getUserFeedbackId(@Param("feedbackId") int id );


    @Query(value="select id from feedback where book_id=:bookId",nativeQuery=true)
    List<Integer> getfeedbackIds(@Param("bookId") int id );


}
