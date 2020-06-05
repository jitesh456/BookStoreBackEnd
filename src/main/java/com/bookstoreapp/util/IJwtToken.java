package com.bookstoreapp.util;

public interface IJwtToken {
    String doGenerateToken( int id);

    Boolean validateToken(String token);

    int getUserId();
}
