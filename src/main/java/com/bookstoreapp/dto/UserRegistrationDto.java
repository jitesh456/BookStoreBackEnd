package com.bookstoreapp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRegistrationDto {


    @NotEmpty(message="User name should start with upper case and minimum 3 character")
    @NotEmpty(message="User name should start with upper case and minimum 3 character")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$",message="User name should start with upper case and minimum 3 character")
    public String name;


    @NotNull(message="Email should not be Empty")
    @NotEmpty(message ="Email should not be Empty" )
    @Pattern(regexp="^[a-zA-Z]{3,}([-|+|.|_]?[a-zA-Z0-9]+)?[@]{1}[A-Za-z0-9]+[.]{1}[a-zA-Z]{2,4}([.]{1}[a-zA-Z]+)?$",
            message="Please enter valid email")
    public String email;

    @NotEmpty(message="Atleast one uppercase,lowercase,number and atmost one special character with minimum length 8")
    @NotNull(message="Atleast one uppercase,lowercase,number and atmost one special character with minimum length 8")
    @Pattern(regexp = "^((?=[^\\W\\_]*[\\W\\_][^\\W\\_]*$)(?=.*[A-Z])(?=.*[\\d])[A-Za-z\\d\\W\\_]{8,})$",
            message ="Atleast one uppercase,lowercase,number and atmost one special character with minimum length 8")
    public String password;

    @NotNull(message="Mobile No Only have 10 Digit")
    @NotEmpty(message="Mobile No Only have 10 Digit")
    @Pattern(regexp = "^[0-9]{10}$",message="Mobile No Only have 10 Digit")

    public String number;

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String name, String email, String password, String number) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.number = number;
    }
}