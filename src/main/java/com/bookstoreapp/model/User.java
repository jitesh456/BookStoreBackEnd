package com.bookstoreapp.model;

import com.bookstoreapp.dto.UserRegistrationDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public String name;

    public String email;

    public String password;

    public String number;

    @OneToMany( cascade = CascadeType.ALL,orphanRemoval = true)
    List<Book> bookList=new ArrayList<>();


    public User() { }

    public User(UserRegistrationDto userRegistrationDto) {
        this.name = userRegistrationDto.name;
        this.email = userRegistrationDto.email;
        this.password = userRegistrationDto.password;
        this.number = userRegistrationDto.number;
}

}














