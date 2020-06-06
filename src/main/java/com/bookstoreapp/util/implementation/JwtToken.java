package com.bookstoreapp.util.implementation;


import com.bookstoreapp.exception.JwtTokenException;
import com.bookstoreapp.exception.UserException;
import com.bookstoreapp.properties.ApplicationProperties;
import com.bookstoreapp.util.IJwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;


@Component
public class JwtToken implements IJwtToken {


    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Autowired
    ApplicationProperties applicationProperties;

    int userId;
    Date date=new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000);

    public String doGenerateToken(int id) {
        return Jwts.builder().setId(String.valueOf(id)).
                setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, applicationProperties.getSecretKey()).compact();
    }

    @Override
    public Boolean validateToken(String token) {
        Boolean validate=false;

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(applicationProperties.getSecretKey()))
                .parseClaimsJws(token).getBody();

        userId=Integer.parseInt(claims.getId());
//        if(date==claims.getExpiration()){
//            return true;
//        }

        //throw new JwtTokenException("Token Expired", JwtTokenException.ExceptionType.TOKEN_EXPIRED);
        return  true;
    }

    @Override
    public int getUserId() {
        return userId;
    }


}




