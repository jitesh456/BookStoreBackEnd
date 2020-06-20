package com.bookstoreapp.model;

import com.bookstoreapp.dto.UserRegistrationDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @OneToMany()
    public  List<Cart> carts=new ArrayList<>();

    @OneToMany(targetEntity = UserDetail.class)

    public List<UserDetail> userDetail=new ArrayList<>();

    public boolean isActivate;

    public User() { }

    public User(UserRegistrationDto userRegistrationDto) {
        this.name = userRegistrationDto.name;
        this.email = userRegistrationDto.email;
        this.password = userRegistrationDto.password;
        this.number = userRegistrationDto.number;
    }
}













