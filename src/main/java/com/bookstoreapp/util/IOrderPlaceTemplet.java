package com.bookstoreapp.util;

import com.bookstoreapp.model.Cart;
import com.bookstoreapp.model.User;

public interface IOrderPlaceTemplet {

    String placeOrderTemplet(Cart list, User user);
}
