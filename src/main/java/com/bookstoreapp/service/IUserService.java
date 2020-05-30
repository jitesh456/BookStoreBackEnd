package com.bookstoreapp.service;

import com.bookstoreapp.dto.UserLoginDto;
import com.bookstoreapp.dto.UserRegistrationDto;
import com.bookstoreapp.response.Response;

public interface IUserService {
    boolean addUser(UserRegistrationDto userRegistrationDto);

    Response loginUser(UserLoginDto userLoginDto);
}