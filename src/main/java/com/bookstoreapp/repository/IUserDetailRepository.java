package com.bookstoreapp.repository;

import com.bookstoreapp.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDetailRepository extends JpaRepository<UserDetail,Integer> {
}
