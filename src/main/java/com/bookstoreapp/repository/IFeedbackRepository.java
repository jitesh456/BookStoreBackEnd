package com.bookstoreapp.repository;


import com.bookstoreapp.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFeedbackRepository extends JpaRepository<Feedback,Integer> {


}
