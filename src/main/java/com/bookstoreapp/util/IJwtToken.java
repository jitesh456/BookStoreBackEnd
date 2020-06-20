package com.bookstoreapp.util;

public interface IJwtToken {

    String generateToken(int id);

    Boolean validateToken(String token);

    int getUserId();
}
