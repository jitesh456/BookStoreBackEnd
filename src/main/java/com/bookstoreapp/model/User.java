package com.bookstoreapp.model;

import com.bookstoreapp.dto.UserRegistrationDto;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public String name;

    public String email;

    public String password;

    public String number;

    public User() { }

    public User(UserRegistrationDto userRegistrationDto) {
        this.id=this.id;
        this.name = userRegistrationDto.name;
        this.email = userRegistrationDto.email;
        this.password = userRegistrationDto.password;
        this.number = userRegistrationDto.number;
    }
}














