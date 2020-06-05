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


    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Cart cart) {
        carts.add(cart);
    }

    @OneToMany()
    List<Cart> carts=new ArrayList<>();



    public User() { }

    public User(UserRegistrationDto userRegistrationDto) {
        this.name = userRegistrationDto.name;
        this.email = userRegistrationDto.email;
        this.password = userRegistrationDto.password;
        this.number = userRegistrationDto.number;
    }

}














