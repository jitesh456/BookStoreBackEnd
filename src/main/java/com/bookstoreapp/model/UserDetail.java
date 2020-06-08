package com.bookstoreapp.model;

import com.bookstoreapp.dto.UserDetailDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="userdetail")
public class UserDetail {

    @Id
    public int id;

    public String pincode;

    public String locality;

    public String address;

    public String city;

    public String country;

    @ManyToMany
    public List<User> user;

    public UserDetail(UserDetailDto userDetailDto) {
        this.pincode = userDetailDto.pincode;
        this.locality = userDetailDto.locality;
        this.address = userDetailDto.address;
        this.city = userDetailDto.city;
        this.country = userDetailDto.country;
    }
}