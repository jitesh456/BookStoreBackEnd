package com.bookstoreapp.repository;

import com.bookstoreapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IUserRepository extends JpaRepository<User,String> {

    Optional<User> findUserByEmail(String email);
    Optional<User> findUserById(int id);

}