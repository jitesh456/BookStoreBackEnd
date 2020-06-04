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
    public Integer id;

    public LocalDateTime createdTimeStamp=LocalDateTime.now();

    public LocalDateTime orderPlacedDate;

    public int totalPrice;

    boolean placedOrder;

    @Embedded
    List<BookCart> bookCarts;

    @OneToMany
    List<Book> bookList=new ArrayList<>();

    public int quantity;

    public Cart() {
    }


    public Cart(List<Book> bookList){
        this.bookList=bookList;

  }

}
