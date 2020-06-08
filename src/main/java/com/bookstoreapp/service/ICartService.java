package com.bookstoreapp.service;

import com.bookstoreapp.dto.AddToCartDto;
import com.bookstoreapp.response.Response;

public interface ICartService {

    Response addToCart(AddToCartDto addToCartDto, String token);

    Response getCartBook(String token);

    Response updateCart(String token);
}
