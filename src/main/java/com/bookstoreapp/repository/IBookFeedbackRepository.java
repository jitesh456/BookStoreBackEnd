package com.bookstoreapp.repository;

import com.bookstoreapp.model.BookFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookFeedbackRepository extends JpaRepository<BookFeedback,Integer> {

}
