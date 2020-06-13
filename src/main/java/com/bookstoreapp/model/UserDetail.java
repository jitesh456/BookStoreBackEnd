package com.bookstoreapp.model;

import com.bookstoreapp.dto.UserDetailDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity

@Table(name="userdetails")
public class UserDetail  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public String addressType;

    public String pincode;

    public String locality;

    public String address;

    public String city;

    public String country;



    public UserDetail() {
    }

    public UserDetail(UserDetailDto userDetailDto) {
        this.addressType=userDetailDto.addressType;
        this.pincode = userDetailDto.pincode;
        this.locality = userDetailDto.locality;
        this.address = userDetailDto.address;
        this.city = userDetailDto.city;
        this.country = userDetailDto.country;
    }
}