package com.bookstoreapp.service;

import com.bookstoreapp.dto.UserDetailDto;
import com.bookstoreapp.dto.UserLoginDto;
import com.bookstoreapp.dto.UserRegistrationDto;
import com.bookstoreapp.response.Response;

public interface IUserService {
    Response addUser(UserRegistrationDto userRegistrationDto);

    String loginUser(UserLoginDto userLoginDto);


    String userDetail(UserDetailDto userDetailsDto);
}