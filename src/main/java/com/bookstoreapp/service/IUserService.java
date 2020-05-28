package com.bookstoreapp.service;

import com.bookstoreapp.dto.UserLoginDto;
import com.bookstoreapp.dto.UserRegistrationDto;

public interface IUserService {
    boolean addUser(UserRegistrationDto userRegistrationDto);

    boolean loginUser(UserLoginDto userLoginDto);
}