package com.bookstoreapp.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserLoginDto {

    @NotNull(message="Please enter valid email")
    @NotNull(message="Please enter valid email")
    @Pattern(regexp="^[a-zA-Z]{3,}([-|+|.|_]?[a-zA-Z0-9]+)?[@]{1}[A-Za-z0-9]+[.]{1}[a-zA-Z]{2,4}([.]{1}[a-zA-Z]+)?$",
            message="Please enter valid email")
    public String email;

    @NotNull(message ="Atleast one uppercase,lowercase,number and atmost one special character with minimum length 8")
    @NotNull(message ="Atleast one uppercase,lowercase,number and atmost one special character with minimum length 8")
    @Pattern(regexp = "^((?=[^\\W\\_]*[\\W\\_][^\\W\\_]*$)(?=.*[A-Z])(?=.*[\\d])[A-Za-z\\d\\W\\_]{8,})$",
            message ="Atleast one uppercase,lowercase,number and atmost one special character with minimum length 8")
    public String password;

    public UserLoginDto(String email, String password) {
        this.email=email;
        this.password=password;

    }
}
