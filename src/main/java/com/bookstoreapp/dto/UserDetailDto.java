package com.bookstoreapp.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDetailDto {

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$",message="User name should start with upper case and minimum 3 character")
    public String name;

    @Pattern(regexp = "^[0-9]{10}$",message="Mobile No Only have 10 Digit")
    public String number;

    @Max(value=6,message="Pincode must be of 6 digit")
    public String pincode;

    public String locality;

    @Size(max = 200,message = "Maximum length is 200 characters")
    public String address;

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$",message="User name should start with upper case and minimum 3 character")
    public String city;

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$",message="User name should start with upper case and minimum 3 character")
    public String country;

    public UserDetailDto(String name, String number, String pincode, String locality, String address, String city, String country) {
        this.name = name;
        this.number = number;
        this.pincode = pincode;
        this.locality = locality;
        this.address = address;
        this.city = city;
        this.country = country;
    }
}
