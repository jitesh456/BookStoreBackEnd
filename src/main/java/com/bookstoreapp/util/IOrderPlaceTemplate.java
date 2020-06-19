package com.bookstoreapp.util;

import com.bookstoreapp.model.Cart;
import com.bookstoreapp.model.User;

public interface IOrderPlaceTemplate {

    String placeOrderTemplate(Cart list, User user);
}
