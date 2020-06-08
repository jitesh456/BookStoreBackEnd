package com.bookstoreapp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDetailDto {

    @NotEmpty(message="Address type must not be null")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$",message="Address type should start with upper case")
    public String addressType;

    @NotEmpty(message="Pincode must not be null")
    @Size(min=6,max = 6,message = "Pincode must be of 6 digits")
    public String pincode;

    public String locality;

    @NotEmpty(message="Address type must not be null")
    @Size(max = 200,message = "Maximum length is 200 characters")
    public String address;

    @NotEmpty(message="City type must not be null")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$",message="City name should start with upper case and minimum 3 character")
    public String city;

    @NotEmpty(message="Country type must not be null")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$",message="Country name should start with upper case and minimum 3 character")
    public String country;

    public UserDetailDto() {
    }

    public UserDetailDto(String addressType, String pincode, String locality, String address, String city, String country) {
        this.addressType = addressType;
        this.pincode = pincode;
        this.locality = locality;
        this.address = address;
        this.city = city;
        this.country = country;
    }
}
