package com.bookstoreapp.util.implementation;


import com.bookstoreapp.util.IJwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;


@Component
public class JwtToken implements IJwtToken {


    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    @Value("${SECRET}")
    private String secret;


    Date date=new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000);

    public String doGenerateToken(int id) {
        return Jwts.builder().setId(String.valueOf(id)).
                setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    private boolean jwtTokenVerify(String token) {


        Boolean validate=false;
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                .parseClaimsJws(token).getBody();

        if(date==claims.getExpiration()){
            validate=true;
        }
        return validate;
    }

}




