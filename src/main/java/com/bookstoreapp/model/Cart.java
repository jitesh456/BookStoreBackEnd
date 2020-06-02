package com.bookstoreapp.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public LocalDateTime createdTimeStamp=LocalDateTime.now();

    @ManyToMany
    List<Book> bookList=new ArrayList<>();

    public Cart() {
    }


    public Cart(List<Book> bookList){
        this.bookList=bookList;
  }

}
