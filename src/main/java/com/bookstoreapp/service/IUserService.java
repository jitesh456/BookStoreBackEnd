package com.bookstoreapp.service;

import com.bookstoreapp.dto.UserDetailDto;
import com.bookstoreapp.dto.UserLoginDto;
import com.bookstoreapp.dto.UserRegistrationDto;
import com.bookstoreapp.response.Response;


import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

public interface IUserService {
    Response addUser(UserRegistrationDto userRegistrationDto,HttpServletRequest servletRequest) throws MessagingException;

    String loginUser(UserLoginDto userLoginDto);

    Response verifyEmail(String token);

    Response forgetPassword(String emailID,HttpServletRequest servletRequest) throws MessagingException;
}