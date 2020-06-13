package com.bookstoreapp.service;

import com.bookstoreapp.dto.AddToCartDto;
import com.bookstoreapp.response.Response;

import javax.mail.MessagingException;

public interface ICartService {

    Response addToCart(AddToCartDto addToCartDto, String token);

    Response getCartBook(String token);

    Response updateCart(String token) throws MessagingException;

    Response deleteBook(int id, String token);

    Response orderDetails(String token);
}
